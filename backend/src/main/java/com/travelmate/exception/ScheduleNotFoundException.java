package com.travelmate.exception;

public class ScheduleNotFoundException extends RuntimeException{
    public ScheduleNotFoundException(){
        super("해당 일정이 없습니다.");
    }
}
