package com.travelmate.service;

import com.travelmate.dto.TravelRequest;
import com.travelmate.dto.TravelResponse;
import com.travelmate.entity.Travel;
import com.travelmate.entity.User;
import com.travelmate.enums.TravelStatus;
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
                travel.getStartDate(),
                travel.getEndDate(),
                travel.getBudget(),
                calculateStatus(travel)
        );
    }

    //Trvel 객체가 있는지 확인
    private Travel findTravelById(Long id){
        return travelRepository
                .findById(id)
                .orElseThrow(TravelNotFoundException::new);
    }

    //User 검증
    private User findUser(String loginId){
        return userRepository
                .findByLoginId(loginId)
                .orElseThrow(UserNotFoundException::new);
    }

    //Travel 소유권 검즘
    private void validateTravelOwner(Travel travel, User user){
        if(!travel.getUser().equals(user))
            throw new TravelAccessDeniedException();
    }

    //Travel 상태값 설정
    private TravelStatus calculateStatus(Travel travel){
        LocalDate today = LocalDate.now();

        if(today.isBefore(travel.getStartDate()))
            return TravelStatus.PLANNED;

        if(today.isAfter(travel.getEndDate()))
            return TravelStatus.COMPLETED;

        return TravelStatus.ONGOING;
    }


    public TravelResponse getTravel(String loginId, Long id) {
        Travel travel = findTravelById(id);
        User user = findUser(loginId);
        validateTravelOwner(travel, user);
        return toResponse(travel);
    }

    public List<TravelResponse> getTravelsByUser(String loginId){
        User user = findUser(loginId);
        List<Travel> travels =
                travelRepository.findByUserOrderByStartDateAsc(user);

        List<TravelResponse> foundTravels = new ArrayList<>();
        for(Travel travel: travels){
            foundTravels.add(toResponse(travel));
        }
        return foundTravels;
    }

    public TravelResponse createTravel(String loginId, TravelRequest request){
        User user = findUser(loginId);
        Travel travel = new Travel(
                user,
                request.getTitle(),
                request.getDestination(),
                request.getStartDate(),
                request.getEndDate(),
                request.getBudget()
        );
        Travel savedTravel = travelRepository.save(travel);
        return toResponse(savedTravel);
    }

    public TravelResponse updateTravel(Long id, String loginId, TravelRequest request){
        Travel foundTravel = findTravelById(id);
        User user = findUser(loginId);
        validateTravelOwner(foundTravel, user);

        foundTravel.setTitle(request.getTitle());
        foundTravel.setDestination(request.getDestination());
        foundTravel.setStartDate(request.getStartDate());
        foundTravel.setEndDate(request.getEndDate());
        foundTravel.setBudget(request.getBudget());
        Travel savedTravel = travelRepository.save(foundTravel);
        return toResponse(savedTravel);
    }

    public void deleteTravel(Long id, String loginId){
        Travel foundTravel = findTravelById(id);
        User user = findUser(loginId);
        validateTravelOwner(foundTravel, user);
        travelRepository.deleteById(id);
    }



}
