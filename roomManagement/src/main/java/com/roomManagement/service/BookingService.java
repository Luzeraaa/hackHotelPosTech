package com.roomManagement.service;

import com.roomManagement.dto.SummaryResponse;
import com.roomManagement.entity.Booking;
import com.roomManagement.gateway.AccommodationGateway;
import com.roomManagement.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

  private final BookingRepository bookingRepository;
  private final AccommodationGateway accommodationGateway;

  public BookingService(BookingRepository bookingRepository, AccommodationGateway accommodationGateway) {
    this.bookingRepository = bookingRepository;
    this.accommodationGateway = accommodationGateway;
  }

  public SummaryResponse getSummary(Long userId) {
    return accommodationGateway.getSummaryResponse(userId);
  }

  public Booking save(final Booking booking) {
    return this.bookingRepository.save(booking);
  }
}
