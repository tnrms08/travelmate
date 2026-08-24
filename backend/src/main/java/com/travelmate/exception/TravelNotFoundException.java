package com.travelmate.exception;

public class TravelNotFoundException extends RuntimeException{
    public TravelNotFoundException() {
        super("여행 정보를 찾을 수 없습니다.");
    }
}
