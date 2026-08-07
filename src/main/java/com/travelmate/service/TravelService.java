package com.travelmate.service;

import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import com.travelmate.entity.Travel;
import com.travelmate.exception.TravelNotFoundException;
import com.travelmate.repository.TravelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor  //생성자 자동 생성
@Service
public class TravelService {
    private final TravelRepository travelRepository;

    //Travel to TravelResponse
    private TravelResponse toResponse(Travel travel){
        return new TravelResponse(
                travel.getId(),
                travel.getTitle(),
                travel.getDestination(),
                travel.getStartDate().toString(),
                travel.getEndDate().toString(),
                travel.getBudget()
        );
    }

    //Trvel 객체가 있는지 확인
    private Travel findTravelById(Long id){
        return travelRepository.findById(id).orElseThrow(TravelNotFoundException::new);
    }

    public List<TravelResponse> getTravels() {
        List<Travel> travels = travelRepository.findAll();

        List<TravelResponse> foundTravels = new ArrayList<>();
        for(Travel travel: travels){
            foundTravels.add(toResponse(travel));
        }
        return foundTravels;
    }

    public TravelResponse getTravel(Long id) {
        Travel travel = findTravelById(id);
        return toResponse(travel);
    }

    public TravelResponse createTravel(TravelRequest request){
        Travel travel = new Travel(
                request.getTitle(),
                request.getDestination(),
                LocalDate.parse(request.getStartDate()),
                LocalDate.parse(request.getEndDate()),
                request.getBudget()
        );
        Travel savedTravel = travelRepository.save(travel);
        return toResponse(savedTravel);
    }
    public void deleteTravel(Long id){
        findTravelById(id);
        travelRepository.deleteById(id);
//        Travel travel = findTravelById(id);
//        travelRepository.delete(travel);
    }

    public TravelResponse updateTravel(Long id, TravelRequest request){
        Travel foundTravel = findTravelById(id);

        foundTravel.setTitle(request.getTitle());
        foundTravel.setDestination(request.getDestination());
        foundTravel.setStartDate(LocalDate.parse(request.getStartDate()));
        foundTravel.setEndDate(LocalDate.parse(request.getEndDate()));
        foundTravel.setBudget(request.getBudget());

        Travel savedTravel = travelRepository.save(foundTravel);

        return toResponse(savedTravel);
    }

}
