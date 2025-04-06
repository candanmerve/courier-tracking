package com.couriertracking.repository;

import com.couriertracking.model.CourierLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CourierLocationRepository extends JpaRepository<CourierLocation, Long> {
    List<CourierLocation> findByCourierIdOrderByCreateTimeDesc(Long courierId);

}