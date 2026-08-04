package com.travelmate.repository;

import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import com.travelmate.exception.TravelNotFoundException;
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

    public TravelResponse findById(Long id) {
        for (TravelResponse travel : travels) {
            if (travel.getId().equals(id)) {
                return travel;
            }
        }
        throw new TravelNotFoundException();
    }

    public void deleteById(Long id){
        for(int i=0;i< travels.size();i++){
            if(travels.get(i).getId().equals(id)) {
                travels.remove(i);
                return;
            }
        }
        throw new TravelNotFoundException();
    }
    public TravelResponse update(Long id, TravelRequest request){
        TravelResponse updatedTravel = new TravelResponse(
                id,
                request.getTitle(),
                request.getDestination(),
                request.getStartDate(),
                request.getEndDate(),
                request.getBudget()
        );

        for(int i=0;i< travels.size();i++) {
            if (travels.get(i).getId().equals(id)) {
                travels.set(i, updatedTravel);

                return updatedTravel;
            }
        }
        throw new TravelNotFoundException();
    }
}
