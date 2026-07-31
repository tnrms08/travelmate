package com.travelmate.service;

import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import com.travelmate.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor  //생성자 자동 생성
@Service
public class TravelService {
    private final TravelRepository travelRepository;
    public List<TravelResponse> getTravels() {
        return travelRepository.findAll();
    }

    public TravelResponse createTravel(TravelRequest request){
        TravelResponse travel = new TravelResponse(
                3L,
                request.getTitle(),
                request.getDestination(),
                request.getStartDate(),
                request.getEndDate(),
                request.getBudget()
        );
        return travelRepository.save(travel);
    }

}
