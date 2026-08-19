package com.travelmate.service;

import com.travelmate.dto.ScheduleRequest;
import com.travelmate.dto.ScheduleResponse;
import com.travelmate.entity.Schedule;
import com.travelmate.entity.Travel;
import com.travelmate.entity.User;
import com.travelmate.exception.ScheduleNotFoundException;
import com.travelmate.exception.TravelAccessDeniedException;
import com.travelmate.exception.TravelNotFoundException;
import com.travelmate.exception.UserNotFoundException;
import com.travelmate.repository.ScheduleRepository;
import com.travelmate.repository.TravelRepository;
import com.travelmate.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final TravelRepository travelRepository;
    private final UserRepository userRepository;

    private ScheduleResponse toResponse(Schedule schedule){
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTravel().getId(),
                schedule.getTitle(),
                schedule.getStartTime().toString(),
                schedule.getEndTime().toString(),
                schedule.getTransportation(),
                schedule.getPlace(),
                schedule.getMeal(),
                schedule.getAccommodation()
        );
    }

    //Schedule 객체가 있는지 확인
    private Schedule findScheduleById(Long id){
        return scheduleRepository
                .findById(id)
                .orElseThrow(ScheduleNotFoundException::new);
    }

    //User 검증
    private User findUser(String loginId){
        return userRepository
                .findByLoginId(loginId)
                .orElseThrow(UserNotFoundException::new);
    }

    //Travel 검증
    private Travel findTravelById(Long travelId){
        return travelRepository
                .findById(travelId)
                .orElseThrow(TravelNotFoundException::new);
    }

    //Travel 소유권 검증
    private void validateTravelOwner(Travel travel, User user){
        if(!travel.getUser().equals(user))
            throw new TravelAccessDeniedException();
    }

    //Schedule 소유권 검증
    private void validateScheduleOwner(Schedule schedule, Travel travel){
        if(!schedule.getTravel().equals(travel))
            throw new TravelAccessDeniedException();
    }

    public List<ScheduleResponse> getSchedulesByTravel(String loginId, Long travelId){
        Travel travel = findTravelById(travelId);
        User user = findUser(loginId);
        validateTravelOwner(travel, user);

        List<Schedule> schedules = scheduleRepository.findByTravel(travel);

        List<ScheduleResponse> foundSchedules = new ArrayList<>();
        for(Schedule schedule: schedules){
            foundSchedules.add(toResponse(schedule));
        }
        return foundSchedules;
    }

    public ScheduleResponse getScheduleById(String loginId, Long travelId, Long scheduleId) {
        Schedule schedule=findScheduleById(scheduleId);

        Travel travel = findTravelById(travelId);
        User user = findUser(loginId);
        validateTravelOwner(travel, user);
        validateScheduleOwner(schedule, travel);

        return toResponse(schedule);
    }

    public ScheduleResponse createSchedule(String loginId, Long travelId, ScheduleRequest request){
        Travel travel = findTravelById(travelId);
        User user = findUser(loginId);
        validateTravelOwner(travel, user);

        Schedule schedule = new Schedule(
                travel,
                request.getTitle(),
                LocalDateTime.parse(request.getStartTime()),
                LocalDateTime.parse(request.getEndTime()),
                request.getTransportation(),
                request.getPlace(),
                request.getMeal(),
                request.getAccommodation()
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);

        return toResponse(savedSchedule);
    }

    public ScheduleResponse updateSchedule(String loginId,
                                           Long travelId,
                                           Long id,
                                           ScheduleRequest request){

        Schedule foundSchedule = findScheduleById(id);

        Travel travel = findTravelById(travelId);
        User user = findUser(loginId);
        validateTravelOwner(travel, user);
        validateScheduleOwner(foundSchedule, travel);

        foundSchedule.setTitle(request.getTitle());
        foundSchedule.setStartTime(LocalDateTime.parse(request.getStartTime()));
        foundSchedule.setEndTime(LocalDateTime.parse(request.getEndTime()));
        foundSchedule.setTransportation(request.getTransportation());
        foundSchedule.setPlace(request.getPlace());
        foundSchedule.setMeal(request.getMeal());
        foundSchedule.setAccommodation(request.getAccommodation());

        Schedule savedSchedule = scheduleRepository.save(foundSchedule);

        return toResponse(savedSchedule);
    }

    public void deleteSchedule(String loginId, Long travelId, Long id){

        Schedule foundSchedule = findScheduleById(id);
        Travel travel = findTravelById(travelId);
        User user = findUser(loginId);
        validateTravelOwner(travel, user);
        validateScheduleOwner(foundSchedule, travel);

        scheduleRepository.delete(foundSchedule);
    }


}
