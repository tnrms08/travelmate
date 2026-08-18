package com.travelmate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String transportation;
    private String place;
    private String meal;
    private String accommodation;

    @ManyToOne
    private Travel travel;

    public Schedule(
            Travel travel,
            String title,
            LocalDateTime startTime,
            LocalDateTime endTime,
            String transportation,
            String place,
            String meal,
            String accommodation
    ){
        this.travel = travel;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.transportation = transportation;
        this.place = place;
        this.meal =meal;
        this.accommodation = accommodation;
    }
}
