package com.duongw.carbookingapi.repository;

import com.duongw.carbookingapi.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer> {
    List<BookingDetail> findAllByOrderByDateBookingDesc();

}
