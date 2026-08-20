package com.travelmate.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {

    @AssertTrue
    public boolean isValidTimeRange(){
        return startTime == null
                || endTime == null
                || startTime.isBefore(endTime);
    }

    @NotBlank
    private String title;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @NotBlank
    private String transportation;

    @NotBlank
    private String place;

    @NotBlank
    private String meal;

    @NotBlank
    private String accommodation;
}
