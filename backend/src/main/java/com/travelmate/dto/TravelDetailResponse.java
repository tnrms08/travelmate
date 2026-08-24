package com.travelmate.dto;

import com.travelmate.enums.TravelStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class TravelDetailResponse {
    private Long id;
    private String title;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer budget;
    private TravelStatus status;

    private List<ScheduleResponse> schedules;
}
