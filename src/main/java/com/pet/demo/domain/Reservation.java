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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "petsitter_id", nullable = false)
    private Petsitter petsitter;

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
    public Reservation(User user, Petsitter petsitter, LocalDateTime startAt, LocalDateTime endAt, String status, Integer price, String location) {
        this.user = user;
        this.petsitter = petsitter;
        this.startAt = startAt;
        this.endAt = endAt;
        this.status = status;
        this.price = price;
        this.location = location;
    }

    public Long getPetsitterId() {
        return petsitter.getId();
    }
} 