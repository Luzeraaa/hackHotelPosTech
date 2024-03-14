package com.roomManagement.service;

import com.roomManagement.entity.Booking;
import com.roomManagement.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {
    @Autowired
    private final BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking findByUserId(final Long userId) {
        return this.bookingRepository.findByUserId(userId).orElseThrow(() -> new RuntimeException("Booking not found."));
    }

    public Booking save(final Booking booking) {
        return this.bookingRepository.save(booking);
    }
}
