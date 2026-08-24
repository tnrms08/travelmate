package com.travelmate.repository;

import com.travelmate.entity.Travel;
import com.travelmate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {
//    List<Travel> findByUser(User user);
    List<Travel> findByUserOrderByStartDateAsc(User user);
}
