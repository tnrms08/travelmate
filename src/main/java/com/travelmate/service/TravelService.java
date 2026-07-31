package com.travelmate.service;

import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelService {
    public List<TravelResponse> getTravels() {
        return List.of(
                new TravelResponse(
                        1L,
                        "후쿠오카 여행",
                        "후쿠오카",
                        "2026-09-01",
                        "2026-09-04",
                        800000
                ),
                new TravelResponse(
                        2L,
                        "도쿄 여행",
                        "도쿄",
                        "2026-10-01",
                        "2026-10-05",
                        1200000
                )
        );
    }

    public TravelResponse createTravel(TravelRequest request){
        return new TravelResponse(
                3L,
                request.getTitle(),
                request.getDestination(),
                request.getStartDate(),
                request.getEndDate(),
                request.getBudget()
        );
    }

}
