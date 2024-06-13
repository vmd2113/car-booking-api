package com.duongw.carbookingapi.DTO;

import com.duongw.carbookingapi.entity.Sheet;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class BookingDetailDTO {

    private Integer id;
    @NotEmpty
    @Size(min = 2, message = "Booking first name should have at least 2 characters")
    private String firstName;
    @NotEmpty
    @Size(min = 2, message = "Booking last name should have at least 2 characters")
    private String lastName;
    @NotEmpty
    @Size(min = 5, message = "Booking pick destination should have at least 5 characters")
    private String pickDestination;
    @NotEmpty
    @Size(min = 5, message = "Booking drop destination should have at least 5 characters")
    private String dropDestination;
    @NotEmpty
    private String phone;
    private Sheet sheet;
    private Date dateBooking;

    public Date getDateBooking() {
        return dateBooking;
    }

    public void setDateBooking(Date dateBooking) {
        this.dateBooking = dateBooking;
    }

    private Integer tripId;

    public Integer getTripId() {
        return tripId;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    private boolean status;

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

    public BookingDetailDTO(String firstName, String lastName, String pickDestination, String dropDestination,
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

    public BookingDetailDTO() {
        super();
    }


}
