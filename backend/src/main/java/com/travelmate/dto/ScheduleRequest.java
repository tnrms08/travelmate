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

    @AssertTrue(message = "일정 종료시간은 시작시간보다 늦어야 합니다.")
    public boolean isValidTimeRange(){
        return startTime == null
                || endTime == null
                || startTime.isBefore(endTime);
    }

    @NotBlank(message = "일정명은 필수입니다.")
    private String title;

    @NotNull(message = "시작 시간은 필수입니다.")
    private LocalDateTime startTime;

    @NotNull(message = "종료 시간은 필수입니다.")
    private LocalDateTime endTime;

    @NotBlank(message = "이동 수단은 필수입니다.")
    private String transportation;

    @NotBlank(message = "장소는 필수입니다.")
    private String place;

    private String meal;
    private String accommodation;
}
