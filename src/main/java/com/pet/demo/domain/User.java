package com.pet.demo.domain;

import com.pet.demo.dto.UserInfoDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "active", nullable = false)
    private String active;

    @Builder
    public User(String name, String email, String password, String phone, String active) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.active = active;
    }

    public void changeUserInfo(UserInfoDto userInfoDto){
        this.name = userInfoDto.name();
        this.phone = userInfoDto.phone();
    }
}