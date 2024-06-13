package com.duongw.carbookingapi.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "trips")
public class Trip {


    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @OneToOne()
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<BookingDetail> bookingDetails  = new HashSet<>();




    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
    public Driver getDriver() {
        return driver;
    }
    public void setDriver(Driver driver) {
        this.driver = driver;
    }
    public Trip() {
        super();
    }
    public Trip(Date startTime, Date endTime, Driver driver) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.driver = driver;
    }
    public Set<BookingDetail> getBookingDetails() {
        return bookingDetails;
    }
    public void setBookingDetails(Set<BookingDetail> bookingDetails) {
        this.bookingDetails = bookingDetails;
    }


    @Override
    public String toString() {
        return "Trip{" +
                "Id=" + id +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", driver=" + driver +
                ", bookingDetails=" + bookingDetails +
                '}';
    }


}
