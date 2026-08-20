package com.travelmate.repository;

import com.travelmate.entity.Schedule;
import com.travelmate.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
//    List<Schedule> findByTravel(Travel travel);
    List<Schedule> findByTravelOrderByStartTimeAsc(Travel travel);
}
