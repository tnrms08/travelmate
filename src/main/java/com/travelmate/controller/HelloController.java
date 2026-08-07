package com.travelmate.controller;

import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import com.travelmate.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HelloController {

    private final TravelService travelService;

    @GetMapping("/hello")
    public String hello() {
        return " Hello TravelMate!!";
    }

    @GetMapping("/travels")
    public List<TravelResponse> travels(){
        return travelService.getTravels();
    }

    @GetMapping("travels/{id}")
    public TravelResponse getTravel(@PathVariable Long id){
        return travelService.getTravel(id);
    }

    @PostMapping("/travels")
    public TravelResponse createTravel(@RequestBody TravelRequest request){
        return travelService.createTravel(request);
    }
    @DeleteMapping("/travels/{id}")
    public void deleteTravel(@PathVariable Long id){
        travelService.deleteTravel(id);
    }
    @PutMapping("/travels/{id}")
    public TravelResponse updateTravel(@PathVariable Long id, @RequestBody TravelRequest request){
        return travelService.updateTravel(id,request);
    }


}
