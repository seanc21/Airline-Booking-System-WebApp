package com.springProject;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 100)
    private Long userId;

    @Column(name = "passenger_firstname", nullable = false, length = 100)
    private String passFirstName;

    @Column(name = "passenger_lastname", nullable = false, length = 100)
    private String passLastName;

    @Column(name = "travel_from", nullable = false, length = 100)
    private String travelFrom;

    @Column(name = "travel_to", nullable = false, length = 100)
    private String travelTo;

    @Column(name="departing_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateOfDeparture;

    @Column(name="returning_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateOfReturning;

//    @Column(name = "returning", nullable = false, length = 100)
//    private String passReturning;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassFirstName() {
        return passFirstName;
    }

    public void setPassFirstName(String passFirstName) {
        this.passFirstName = passFirstName;
    }

    public String getPassLastName() {
        return passLastName;
    }

    public void setPassLastName(String passLastName) {
        this.passLastName = passLastName;
    }

    public String getTravelFrom() {
        return travelFrom;
    }

    public void setTravelFrom(String travelFrom) {
        this.travelFrom = travelFrom;
    }

    public String getTravelTo() {
        return travelTo;
    }

    public void setTravelTo(String travelTo) {
        this.travelTo = travelTo;
    }

    public LocalDateTime getDateOfDeparture() {
        return dateOfDeparture;
    }

    public void setDateOfDeparture(LocalDateTime dateOfDeparture) {
        this.dateOfDeparture = dateOfDeparture;
    }

    public LocalDateTime getDateOfReturning() {
        return dateOfReturning;
    }

    public void setDateOfReturning(LocalDateTime dateOfReturning) {
        this.dateOfReturning = dateOfReturning;
    }

//    public String getPassDeparting() {
//        return passDeparting;
//    }
//
//    public void setPassDeparting(String passDeparting) {
//        this.passDeparting = passDeparting;
//    }
//
//    public String getPassReturning() {
//        return passReturning;
//    }
//
//    public void setPassReturning(String passReturning) {
//        this.passReturning = passReturning;
//    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
