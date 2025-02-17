package com.pet.demo.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Builder;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String user_id;

    @Column(name = "petsitter_id", nullable = false)
    private String petsitter_id;

    @Column(name = "start_at", nullable = false)
    private LocalDateTime startAt;

    @Column(name = "end_at", nullable = false)
    private LocalDateTime endAt;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "location", nullable = false)
    private String location;

    @Builder
    public Reservation(String user_id, String petsitter_id, LocalDateTime startAt, LocalDateTime endAt, String status, Integer price, String location) {
        this.user_id = user_id;
        this.petsitter_id = petsitter_id;
        this.startAt = startAt;
        this.endAt = endAt;
        this.status = status;
        this.price = price;
        this.location = location;
    }
} 