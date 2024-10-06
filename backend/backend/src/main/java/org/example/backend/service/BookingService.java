package org.example.backend.service;

import org.example.backend.model.Booking;
import org.example.backend.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.ZoneId;
import java.util.List;


@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public void addBooking(Booking booking) {
        // Check if the booking for the same activity, date, and time exists
        if (bookingRepository.isBookingExists(booking.getActivityName(), booking.getDate())) {
            throw new IllegalArgumentException("This time slot is already booked for the selected activity.");
        }

        bookingRepository.addBooking(booking);
    }

    // Delete a booking by its ID
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteBooking(bookingId);
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.getAllBookings();
    }

    // Get a booking by its ID
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.getBookingById(bookingId);
    }

    // Update an existing booking
    public Booking updateBooking(Booking booking) {
        return bookingRepository.updateBooking(booking);
    }
}
