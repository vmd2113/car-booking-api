package com.duongw.carbookingapi.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.IdGeneratorType;

import java.util.Date;

@Entity
@Table(name = "booking_details")

public class BookingDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "pick_destination", nullable = false)
    private String pickDestination;
    @Column(name = "drop_destination", nullable = false)
    private String dropDestination;


    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sheet sheet;
    private boolean status;
    @Column(name = "date_booking")
    private Date dateBooking;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;



    public Date getDateBooking() {
        return dateBooking;
    }
    public void setDateBooking(Date dateBooking) {
        this.dateBooking = dateBooking;
    }
    public Trip getTrip() {
        return trip;
    }
    public void setTrip(Trip trip) {
        this.trip = trip;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPickDestination() {
        return pickDestination;
    }
    public void setPickDestination(String pickDestination) {
        this.pickDestination = pickDestination;
    }
    public String getDropDestination() {
        return dropDestination;
    }
    public void setDropDestination(String dropDestination) {
        this.dropDestination = dropDestination;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Sheet getSheet() {
        return sheet;
    }
    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }


    public BookingDetail(Integer id, String firstName, String lastName, String pickDestination, String dropDestination,
                         String phone, Sheet sheet, boolean status) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickDestination = pickDestination;
        this.dropDestination = dropDestination;
        this.phone = phone;
        this.sheet = sheet;
        this.status = status;
    }
    public BookingDetail(String firstName, String lastName, String pickDestination, String dropDestination,
                         String phone, Sheet sheet, boolean status) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.pickDestination = pickDestination;
        this.dropDestination = dropDestination;
        this.phone = phone;
        this.sheet = sheet;
        this.status = status;
    }
    public BookingDetail() {
        super();
    }
}
