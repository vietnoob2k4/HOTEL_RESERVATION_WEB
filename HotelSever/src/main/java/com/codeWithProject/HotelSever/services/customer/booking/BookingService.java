package com.codeWithProject.HotelSever.services.customer.booking;

import com.codeWithProject.HotelSever.dto.ReservationDto;
import com.codeWithProject.HotelSever.dto.ReservationResponseDto;


public interface BookingService {
    boolean postReservation(ReservationDto reservationDto);
    ReservationResponseDto getAllReservationByUserId(Long userId,int pageNumber);
}
