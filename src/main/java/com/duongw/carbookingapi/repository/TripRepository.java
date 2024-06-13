package com.duongw.carbookingapi.repository;

import com.duongw.carbookingapi.entity.Driver;
import com.duongw.carbookingapi.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    List<Trip> findAllByDriverId(Integer driverId);

}
