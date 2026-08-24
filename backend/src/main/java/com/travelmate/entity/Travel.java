package com.travelmate.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    private User user;

    //Schedule Entity의 "travel"필드 기준으로 연결
    @OneToMany(mappedBy = "travel", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules = new ArrayList<>();

    public Travel(
            User user,
            String title,
            String destination,
            LocalDate startDate,
            LocalDate endDate,
            Integer budget
    ){
        this.user=user;
        this.title=title;
        this.destination=destination;
        this.startDate=startDate;
        this.endDate=endDate;
        this.budget=budget;
    }
}
