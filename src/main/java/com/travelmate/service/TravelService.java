package com.travelmate.service;

import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import com.travelmate.entity.Travel;
import com.travelmate.entity.User;
import com.travelmate.exception.TravelAccessDeniedException;
import com.travelmate.exception.TravelNotFoundException;
import com.travelmate.exception.UserNotFoundException;
import com.travelmate.repository.TravelRepository;
import com.travelmate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor  //생성자 자동 생성
@Service
public class TravelService {
    private final TravelRepository travelRepository;
    private final UserRepository userRepository;

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

    public List<TravelResponse> getTravelsByUser(String loginId){
        User user = userRepository.findByLoginId(loginId).orElseThrow(UserNotFoundException::new);
        List<Travel> travels = travelRepository.findByUser(user);

        List<TravelResponse> foundTravels = new ArrayList<>();
        for(Travel travel: travels){
            foundTravels.add(toResponse(travel));
        }
        return foundTravels;
    }

    public TravelResponse createTravel(TravelRequest request){
        User user = userRepository
                .findByLoginId(request.getLoginId())
                .orElseThrow(UserNotFoundException::new);
        Travel travel = new Travel(
                user,
                request.getTitle(),
                request.getDestination(),
                LocalDate.parse(request.getStartDate()),
                LocalDate.parse(request.getEndDate()),
                request.getBudget()
        );
        Travel savedTravel = travelRepository.save(travel);
        return toResponse(savedTravel);
    }

    public TravelResponse updateTravel(Long id, TravelRequest request){
        Travel foundTravel = findTravelById(id);
        User user = userRepository
                .findByLoginId(request.getLoginId())
                .orElseThrow(UserNotFoundException::new);
        if(!foundTravel.getUser().equals(user)){
            throw new TravelAccessDeniedException();
        }

        foundTravel.setTitle(request.getTitle());
        foundTravel.setDestination(request.getDestination());
        foundTravel.setStartDate(LocalDate.parse(request.getStartDate()));
        foundTravel.setEndDate(LocalDate.parse(request.getEndDate()));
        foundTravel.setBudget(request.getBudget());
        Travel savedTravel = travelRepository.save(foundTravel);
        return toResponse(savedTravel);
    }

    public void deleteTravel(Long id, String loginId){
        User user = userRepository
                .findByLoginId(loginId)
                .orElseThrow(UserNotFoundException::new);
        Travel foundTravel = findTravelById(id);

        if(!foundTravel.getUser().equals(user)){
            throw new TravelAccessDeniedException();
        }
        travelRepository.deleteById(id);
    }

}
