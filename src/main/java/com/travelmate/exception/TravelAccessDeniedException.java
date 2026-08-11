package com.travelmate.exception;

public class TravelAccessDeniedException extends RuntimeException{
    public TravelAccessDeniedException(){
        super("해당 여행을 수정하거나 삭제할 권한이 없습니다.");
    }
}
