package com.travelmate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {
    private String title;
    private String startTime;
    private String endTime;
    private String transportation;
    private String place;
    private String meal;
    private String accommodation;
}
