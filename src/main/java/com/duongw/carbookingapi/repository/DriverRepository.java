package com.duongw.carbookingapi.repository;

import com.duongw.carbookingapi.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
