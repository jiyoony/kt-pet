package com.pet.demo.service;

import com.pet.demo.domain.User;
import com.pet.demo.dto.LoginRequestDto;
import com.pet.demo.dto.UserInfoDto;
import com.pet.demo.dto.UserRequestDto;
import com.pet.demo.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional // 데이터 베이스에 transaction 적용하기 위해 사용
@Slf4j
@RequiredArgsConstructor //  final 붙은 아이들 생성자 만들어주는 annotation
@Service
public class UserService {

    private final UserRepository userRepository;

    public String registerUser(UserRequestDto requestDto, HttpServletRequest request, String role) {
        // 이메일 중복 체크
        userRepository.findByEmail(requestDto.email()).ifPresent(user -> {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        });

        // 유저 저장
        User user = User.builder()
                .name(requestDto.name())
                .email(requestDto.email())
                .password(requestDto.password())
                .phone(requestDto.phone())
                .active("active")
                .role(role)
                .build();

        userRepository.save(user);
        HttpSession session = request.getSession(true); // 세션이 없으면 새로 생성
        session.setAttribute("email", user.getEmail());
        return "회원가입 성공";
    }



    public String login(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        User user = userRepository.findByEmail(loginRequestDto.email())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));

        if (!loginRequestDto.password().equals(user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        HttpSession session = request.getSession(true); // 세션이 없으면 새로 생성
        session.setAttribute("email", user.getEmail());

        log.info("세션 아이디:"+ session.getId());
        return "로그인 성공";
    }

    public UserInfoDto getUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
    
        // 현재 로그인한 사용자 정보 가져오기
        String userEmail = (String) session.getAttribute("email");
    
        // 데이터베이스에서 사용자 조회
        return userRepository.findByEmail(userEmail)
                .map(UserInfoDto::from)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    
    }

    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "로그아웃 성공!";
    }

    public String removeUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
    
        // 현재 로그인한 사용자 정보 가져오기
        String userEmail = (String) session.getAttribute("email");
    
        // 데이터베이스에서 사용자 조회
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    
        // 사용자 삭제
        userRepository.delete(user);
    
        // 세션 무효화
        session.invalidate();
    
        return "탈퇴 성공";
    }
    
    public User putUser(HttpServletRequest request, UserInfoDto userInfoDto ) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("email") == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
    
        // 현재 로그인한 사용자 정보 가져오기
        String userEmail = (String) session.getAttribute("email");
    
        // 데이터베이스에서 사용자 조회
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        
        user.changeUserInfo(userInfoDto);
        return user;
    }
}
