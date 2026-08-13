package com.travelmate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelRequest {
//    private String loginId;
    private String title;
    private String destination;
    private String startDate;
    private String endDate;
    private Integer budget;
}
