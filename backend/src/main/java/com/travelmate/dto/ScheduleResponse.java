package com.travelmate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
public class ScheduleResponse {
    private Long id;
    private Long travelId;
    private String title;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String transportation;
    private String place;
    private String meal;
    private String accommodation;
}
