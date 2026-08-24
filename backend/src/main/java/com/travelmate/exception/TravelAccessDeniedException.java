package com.travelmate.exception;

public class TravelAccessDeniedException extends RuntimeException{
    public TravelAccessDeniedException(){
        super("해당 여행에 접근할 권한이 없습니다.");
    }
}
