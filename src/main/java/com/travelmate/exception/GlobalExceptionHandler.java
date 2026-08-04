package com.travelmate.exception;


import com.travelmate.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(TravelNotFoundException.class)
//    public ErrorResponse handleTravelNotFoundException(TravelNotFoundException e){
//        return new ErrorResponse(e.getMessage());
//    }
    public ResponseEntity<ErrorResponse> handleTravelNotFoundException(TravelNotFoundException e){
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}
