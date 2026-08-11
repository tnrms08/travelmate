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
    @ExceptionHandler(DuplicateLoginIdException.class)
    public ResponseEntity<ErrorResponse> handleDublicateLoginException(DuplicateLoginIdException e){
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(LoginFailedException.class)
    public ResponseEntity<ErrorResponse> handleLoginFailedException(LoginFailedException e){
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e){
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(TravelAccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleTravelAccessDeniedException(TravelAccessDeniedException e){
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse(e.getMessage()),
                HttpStatus.FORBIDDEN
        );
    }
}
