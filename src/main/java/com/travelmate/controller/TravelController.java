package com.travelmate.controller;

import com.travelmate.dto.TravelDetailResponse;
import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import com.travelmate.service.TravelService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TravelController {

    private final TravelService travelService;

    @GetMapping("/travels/{id}")
    public TravelResponse getTravel(Authentication authentication,
                                    @PathVariable Long id){
        return travelService.getTravel(authentication.getName(), id);
    }

    @GetMapping("/travels/{id}/detail")
    public TravelDetailResponse getTravelDetail(Authentication authentication,
                                                @PathVariable Long id){
        return travelService.getTravelDetail(authentication.getName(), id);
    }

//    @GetMapping("/travels")
//    public List<TravelResponse> getTravelsByUser(Authentication authentication){
//        return travelService.getTravelsByUser(authentication.getName());
//    }

    @GetMapping("/travels")
    public List<TravelResponse> getTravels(Authentication authentication,
                                           @RequestParam(required = false) String sort){
        if("status".equals(sort))
            return travelService.getTravelsByStatus(authentication.getName());
        return travelService.getTravelsByUser(authentication.getName());
    }

    @PostMapping("/travels")
    public TravelResponse createTravel(Authentication authentication,
                                       @Valid @RequestBody TravelRequest request){
        return travelService.createTravel(authentication.getName(), request);
    }

    @DeleteMapping("/travels/{id}")
    public void deleteTravel(Authentication authentication, @PathVariable Long id){
        travelService.deleteTravel(id, authentication.getName());
    }

    @PutMapping("/travels/{id}")
    public TravelResponse updateTravel(Authentication authentication,
                                       @PathVariable Long id,
                                       @Valid @RequestBody TravelRequest request){
        return travelService.updateTravel(id, authentication.getName(), request);
    }


}
