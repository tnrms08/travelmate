package com.travelmate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class ScheduleResponse {
    private Long id;
    private Long travelId;
    private String title;
    private String startTime;
    private String endTime;
    private String transportation;
    private String place;
    private String meal;
    private String accommodation;
}
