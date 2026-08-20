package com.travelmate.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TravelRequest {

    @AssertTrue(message = "여행 종료일은 시작일보다 빠를 수 없습니다.")
    public boolean isValidDateRange() {
        return startDate == null
                || endDate == null
                || !startDate.isAfter(endDate);
    }

    @NotBlank(message="여행명은 필수입니다.")
    private String title;

    @NotBlank(message = "여행지는 필수입니다.")
    private String destination;

    @NotNull(message = "여행 시작일은 필수입니다.")
    private LocalDate startDate;

    @NotNull(message = "여행 종료일은 필수입니다.")
    private LocalDate endDate;

    @Min(value = 0, message = "예산은 0 이상이어야 합니다.")
    private Integer budget;
}
