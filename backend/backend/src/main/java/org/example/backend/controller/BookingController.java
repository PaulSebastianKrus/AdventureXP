package org.example.backend.controller;


import org.example.backend.model.Activity;
import org.example.backend.model.Booking;
import org.example.backend.service.ActivityService;
import org.example.backend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ActivityService activityService;

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addBooking(@RequestBody Booking booking){
        bookingService.addBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body("Booking added successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id){
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> updateBooking(@PathVariable Long id, @RequestBody Booking newBooking) {
        Booking oldBooking = bookingService.getBookingById(id);

        if (oldBooking == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found");
        }


        // Only update fields that are not null
        if (newBooking.getActivityName() != null) {
            oldBooking.setActivityName(newBooking.getActivityName());
        }
        if (newBooking.getDate() != null) {
            oldBooking.setDate(newBooking.getDate());
        }
        if (newBooking.getTime() != null) {
            oldBooking.setTime(newBooking.getTime());
        }
        if (newBooking.getPeople() != null) {
            oldBooking.setPeople(newBooking.getPeople());
        }
        if (newBooking.getInstructor() != null) {
            oldBooking.setInstructor(newBooking.getInstructor());
        }
        if (newBooking.getPersonName() != null) {
            oldBooking.setPersonName(newBooking.getPersonName());
        }
        if (newBooking.getPhoneNumber() != null) {
            oldBooking.setPhoneNumber(newBooking.getPhoneNumber());
        }

        bookingService.updateBooking(oldBooking);
        return ResponseEntity.ok("Booking updated");

    }









}
