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

    @PostMapping("travels/{travelId}/schedules")
    public ScheduleResponse createSchedule(Authentication authentication,
                                           @PathVariable Long travelId,
                                           @RequestBody ScheduleRequest request){
        return scheduleService.createSchedule(authentication.getName(), travelId, request);   
    }
}
