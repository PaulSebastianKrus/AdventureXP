package org.example.backend.repository;

import org.example.backend.model.Activity;
import org.example.backend.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;

@Repository
public class BookingRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public BookingRepository ( JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isBookingExists(String activityName, LocalDateTime date) {
        String query = "SELECT COUNT(*) FROM bookings WHERE activityname = ? AND date = ?";
        Integer count = jdbcTemplate.queryForObject(query, new Object[]{activityName, Timestamp.valueOf(date)}, Integer.class);
        return count != null && count > 0;
    }


    public void addBooking(Booking booking) {
        String insertQuery = "INSERT INTO bookings (activityname, date, people, instructor, personname, phonenumber) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertQuery, booking.getActivityName(), booking.getDate(), booking.getPeople(), booking.getInstructor(), booking.getPersonName(), booking.getPhoneNumber());
    }

    public void deleteBooking(Long bookingId) {
        String deleteQuery = "DELETE FROM bookings WHERE booking_id = ?";
        jdbcTemplate.update(deleteQuery, bookingId);
    }

    public List<Booking> getAllBookings() {
        String query = "SELECT * FROM bookings";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
            Booking booking = new Booking();
            booking.setBookingID(rs.getLong("booking_id"));
            booking.setActivityName(rs.getString("activityname"));
            booking.setDate(rs.getTimestamp("date").toLocalDateTime());
            booking.setPeople(rs.getLong("people"));
            booking.setInstructor(rs.getString("instructor"));
            booking.setPersonName(rs.getString("personname"));
            booking.setPhoneNumber(rs.getLong("phonenumber"));
            return booking;
        });
    }

    public Booking getBookingById(Long id) {
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, (rs, rowNum) -> {
            Booking booking = new Booking();
            booking.setBookingID(rs.getLong("booking_id"));
            booking.setActivityName(rs.getString("activityname"));
            booking.setDate(rs.getTimestamp("date").toLocalDateTime());
            booking.setPeople(rs.getLong("people"));
            booking.setInstructor(rs.getString("instructor"));
            booking.setPersonName(rs.getString("personname"));
            booking.setPhoneNumber(rs.getLong("phonenumber"));
            return booking;
        });
    }

    public Booking updateBooking(Booking booking) {
        StringBuilder query = new StringBuilder("UPDATE bookings SET ");
        List<Object> parameters = new ArrayList<>();

        if (booking.getActivityName() != null) {
            query.append("activityname = ?, ");
            parameters.add(booking.getActivityName());
        }
        if (booking.getDate() != null) {
            query.append("date = ?, ");
            parameters.add(booking.getDate());
        }

        if (booking.getPeople() != null) {
            query.append("people = ?, ");
            parameters.add(booking.getPeople());
        }
        if (booking.getInstructor() != null) {
            query.append("instructor = ?, ");
            parameters.add(booking.getInstructor());
        }
        if (booking.getPersonName() != null) {
            query.append("personname = ?, ");
            parameters.add(booking.getPersonName());
        }
        if (booking.getPhoneNumber() != null) {
            query.append("phonenumber = ?, ");
            parameters.add(booking.getPhoneNumber());
        }

        if (!parameters.isEmpty()) {
            query.setLength(query.length() - 2); // Remove last comma and space
            query.append(" WHERE booking_id = ?");
            parameters.add(booking.getBookingID());

            int rowsUpdated = jdbcTemplate.update(query.toString(), parameters.toArray());
            if (rowsUpdated > 0) {
                return booking;
            }
        }
        return null;
    }






}
