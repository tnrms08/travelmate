package com.travelmate.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer budget;

    public Travel(
            String title,
            String destination,
            LocalDate startDate,
            LocalDate endDate,
            Integer budget
    ){
        this.title=title;
        this.destination=destination;
        this.startDate=startDate;
        this.endDate=endDate;
        this.budget=budget;
    }
}
