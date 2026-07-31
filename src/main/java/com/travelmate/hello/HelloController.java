package com.travelmate.hello;

import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import com.travelmate.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class HelloController {


    @GetMapping("/hello")
    public String hello() {
        return " Hello TravelMate!!";
    }

    private final TravelService travelService;

    @GetMapping("/travels")
    public List<TravelResponse> travels(){
        return travelService.getTravels();
    }

    @PostMapping("/travels")
    public TravelResponse createTravel(@RequestBody TravelRequest request){
        return travelService.createTravel(request);

    }

}
