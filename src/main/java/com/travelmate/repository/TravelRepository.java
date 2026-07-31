package com.travelmate.repository;

import com.travelmate.dto.TravelResponse;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TravelRepository {

    private final List<TravelResponse> travels = new ArrayList<>();

    public TravelRepository() {
        travels.add(
                new TravelResponse(
                        1L,
                        "후쿠오카 여행",
                        "후쿠오카",
                        "2026-09-01",
                        "2026-09-04",
                        800000
                )
        );
        travels.add(
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
    public List<TravelResponse> findAll(){
        return new ArrayList<>(travels);
    }
    public TravelResponse save(TravelResponse travel){
        travels.add(travel);
        return travel;
    }
}
