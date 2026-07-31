package com.travelmate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TravelResponse {
    private Long id;
    private String title;
    private String destination;
    private String startDate;
    private String endDate;
    private Integer budget;
}
