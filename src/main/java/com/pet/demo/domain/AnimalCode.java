package com.pet.demo.domain;

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

@Table(name = "animal_code")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AnimalCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "main_category", nullable = false)
    private String mainCategory;

    @Column(name = "sub_category", nullable = false)
    private String subCategory;

    @Column(name = "active", nullable = false)
    private String active;

    // Getters
    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    @Builder
    public AnimalCode(String code, String mainCategory, String subCategory, String active) {
        this.code = code;
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.active = active;
    }

    public void update(String mainCategory, String subCategory, String code) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
        this.code = code;
    }
}