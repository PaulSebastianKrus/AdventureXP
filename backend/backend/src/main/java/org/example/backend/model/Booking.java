package org.example.backend.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "bookings")
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingID;

    @Column(name = "activityname")
    private String activityName;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "people")
    private Long people;

    @Column(name = "instructor")
    private String instructor;

    @Column(name = "personname")
    private String personName;

    @Column(name = "phonenumber")
    private Long phonenumber;

    public Long getBookingID() {
        return bookingID;
    }

    public void setBookingID(Long bookingID) {
        this.bookingID = bookingID;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getPeople() {
        return people;
    }

    public void setPeople(Long people) {
        this.people = people;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Long getPhoneNumber() {
        return phonenumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phonenumber = phoneNumber;
    }

}
