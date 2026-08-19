package com.travelmate.controller;

import com.travelmate.dto.ScheduleRequest;
import com.travelmate.dto.ScheduleResponse;
import com.travelmate.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    @GetMapping("/travels/{travelId}/schedules")
    public List<ScheduleResponse> getSchedulesByTravel(Authentication authentication,
                                     @PathVariable Long travelId){
        return scheduleService.getSchedulesByTravel(authentication.getName(), travelId);
    }

    @GetMapping("/travels/{travelId}/schedules/{scheduleId}")
    public ScheduleResponse getSchedulesById(Authentication authentication,
                                             @PathVariable Long travelId,
                                             @PathVariable Long scheduleId){
        return scheduleService.getScheduleById(authentication.getName(), travelId, scheduleId);
    }

    @PostMapping("/travels/{travelId}/schedules")
    public ScheduleResponse createSchedule(Authentication authentication,
                                           @PathVariable Long travelId,
                                           @RequestBody ScheduleRequest request){
        return scheduleService.createSchedule(authentication.getName(), travelId, request);   
    }

    @PutMapping("/travels/{travelId}/schedules/{scheduleId}")
    public ScheduleResponse updateSchedule(Authentication authentication,
                                           @PathVariable Long travelId,
                                           @PathVariable Long scheduleId,
                                           @RequestBody ScheduleRequest request){
        return scheduleService.updateSchedule(authentication.getName(), travelId, scheduleId, request);
    }

    @DeleteMapping("/travels/{travelId}/schedules/{scheduleId}")
    public void deleteSchedule(Authentication authentication,
                               @PathVariable Long travelId,
                               @PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(authentication.getName(), travelId, scheduleId);

    }
}
