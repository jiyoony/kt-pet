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
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Table(name = "users")
@Getter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "role", nullable = false, columnDefinition = "VARCHAR(255) DEFAULT 'user'") // Default 값은 user
    private String role;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Petsitter> petsitters = new ArrayList<>();

    @Builder
    public User(String name, String email, String password, String phone, String active, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.active = active;
        this.role = role;
    }

    public void changeUserInfo(UserInfoDto userInfoDto){
        this.name = userInfoDto.name();
        this.phone = userInfoDto.phone();
    }
}