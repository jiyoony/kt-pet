package com.pet.demo.controller;

import org.springframework.web.bind.annotation.RestController;

import com.pet.demo.dto.LoginRequestDto;
import com.pet.demo.dto.UserInfoDto;
import com.pet.demo.dto.UserRequestDto;
import com.pet.demo.service.UserService;
import com.pet.demo.domain.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;


    // 유저 회원가입
    @PostMapping("/register")
    public String register(@RequestBody UserRequestDto requestDto,  HttpServletRequest request) {
        return userService.registerUser(requestDto, request, "user");
        
    }

    // 펫시터 회원가입
    @PostMapping("/petsitter/register")
    public String registerPetsitter(@RequestBody UserRequestDto requestDto, HttpServletRequest request) {
        return userService.registerUser(requestDto, request, "petsitter");
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        log.info("ss");
        log.info("로그인 요청"+loginRequestDto);
        return userService.login(loginRequestDto, request);
        
    }

    @GetMapping("/check-login")
    public ResponseEntity<?> checkLogin(HttpSession session) {
        String email = (String) session.getAttribute("email");
        if (email != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoDto> getUser(HttpServletRequest request) {
        try {
            UserInfoDto userResponseDto = userService.getUser(request);

            return ResponseEntity.ok(userResponseDto);  // 성공적인 응답 반환
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);  // 로그인 필요 or 사용자 존재하지 않음 처리
        }
    }
    

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        return userService.logout(request);
        
    }

    @PostMapping("/withdrawal")
    public String withdrawal(HttpServletRequest request) {
        return userService.removeUser(request);
    
    }

    // 회원 정보 수정
    @PutMapping("/info")
    public ResponseEntity<User> info(HttpServletRequest request, @RequestBody UserInfoDto userInfoDto) {
        return ResponseEntity.ok(userService.putUser(request, userInfoDto));
    
    }

    // 비밀번호 리셋
    // @PostMapping("/withdrawal")
    // public String withdrawal(HttpServletRequest request) {
    //     return userService.removeUser(request);
    
    // }
}