package com.duongw.carbookingapi.DTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TripDTO {
    private Integer Id;
    private Date startTime;
    private Date endTime;
    private Integer driverId;
    private Set<BookingDetailDTO> bookingDetails = new HashSet<>();


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Set<BookingDetailDTO> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(Set<BookingDetailDTO> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }

    public TripDTO(Integer id, Date startTime, Date endTime, Integer driverId, Set<BookingDetailDTO> bookingDetails) {
        Id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.driverId = driverId;
        this.bookingDetails = bookingDetails;
    }

    @Override
    public String toString() {
        return "TripDTO{" +
                "Id=" + Id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", driverId=" + driverId +
                ", bookingDetails=" + bookingDetails +
                '}';
    }
}
