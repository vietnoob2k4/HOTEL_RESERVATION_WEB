package com.codeWithProject.HotelSever.services.admin.reservation;

import com.codeWithProject.HotelSever.dto.ReservationResponseDto;
import org.springframework.stereotype.Service;


public interface ReservationService {
    ReservationResponseDto getAllReservations(int pageNumber);
    boolean changeReservationStatus(Long id, String status);
}
