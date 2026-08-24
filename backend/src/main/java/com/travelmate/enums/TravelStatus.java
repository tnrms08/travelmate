package com.travelmate.enums;

import lombok.Getter;

@Getter
public enum TravelStatus {
    PLANNED(2),    //시작전
    ONGOING(1),    //여행중
    COMPLETED(3);   //여행종료

    private final int order;

    TravelStatus(int order){
        this.order=order;
    }
}
