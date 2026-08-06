package com.travelmate.service;

import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import com.travelmate.entity.Travel;
import com.travelmate.exception.TravelNotFoundException;
import com.travelmate.repository.MemoryTravelRepository;
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
    public List<TravelResponse> getTravels() {
        List<Travel> travels = travelRepository.findAll();

        List<TravelResponse> foundTravels = new ArrayList<>();
        for(Travel travel: travels){
            foundTravels.add(
                    new TravelResponse(
                            travel.getId(),
                            travel.getTitle(),
                            travel.getDestination(),
                            travel.getStartDate().toString(),
                            travel.getEndDate().toString(),
                            travel.getBudget()));
        }
        return foundTravels;
    }

    public TravelResponse getTravel(Long id) {
        Optional<Travel> travel = travelRepository.findById(id);

        if(travel.isPresent()){
            return new TravelResponse(
                    travel.get().getId(),
                    travel.get().getTitle(),
                    travel.get().getDestination(),
                    travel.get().getStartDate().toString(),
                    travel.get().getEndDate().toString(),
                    travel.get().getBudget()
            );
        }
        throw new TravelNotFoundException();
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
        return new TravelResponse(
                savedTravel.getId(),
                savedTravel.getTitle(),
                savedTravel.getDestination(),
                savedTravel.getStartDate().toString(),
                savedTravel.getEndDate().toString(),
                savedTravel.getBudget()
        );
    }
    public void deleteTravel(Long id){
        Optional<Travel> travel = travelRepository.findById(id);
        if(!travel.isPresent()) throw new TravelNotFoundException();
        travelRepository.deleteById(id);
    }

    public TravelResponse updateTravel(Long id, TravelRequest request){
        Optional<Travel> travel = travelRepository.findById(id);

        if(!travel.isPresent()){
            throw new TravelNotFoundException();
        }
        Travel foundTravel = travel.get();

        foundTravel.setTitle(request.getTitle());
        foundTravel.setDestination(request.getDestination());
        foundTravel.setStartDate(LocalDate.parse(request.getStartDate()));
        foundTravel.setEndDate(LocalDate.parse(request.getEndDate()));
        foundTravel.setBudget(request.getBudget());

        Travel savedTravel = travelRepository.save(foundTravel);

        return new TravelResponse(
                savedTravel.getId(),
                savedTravel.getTitle(),
                savedTravel.getDestination(),
                savedTravel.getStartDate().toString(),
                savedTravel.getEndDate().toString(),
                savedTravel.getBudget()
        );
    }

}
