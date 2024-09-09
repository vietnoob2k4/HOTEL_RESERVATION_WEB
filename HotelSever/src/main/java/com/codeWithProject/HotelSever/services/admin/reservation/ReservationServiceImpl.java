package com.codeWithProject.HotelSever.services.admin.reservation;

import com.codeWithProject.HotelSever.dto.ReservationDto;
import com.codeWithProject.HotelSever.dto.ReservationResponseDto;
import com.codeWithProject.HotelSever.entity.Reservation;
import com.codeWithProject.HotelSever.entity.Room;
import com.codeWithProject.HotelSever.enums.ReservationStatus;
import com.codeWithProject.HotelSever.repository.ReservationRepository;
import com.codeWithProject.HotelSever.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    public static final int SEARCH_RESULT_PER_PAGE = 30;


    public ReservationResponseDto getAllReservations(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);

        Page<Reservation> reservationsPage = reservationRepository.findAll(pageable);
        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setReservationDtoList(reservationsPage.stream().map(Reservation::getReservationDto).collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationsPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPage(reservationsPage.getTotalPages());

        return reservationResponseDto;

    }

    public boolean changeReservationStatus(Long id, String status) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()) {
            Reservation existingReservation = optionalReservation.get();
            if(Objects.equals(status,"APPROVED")){
                existingReservation.setReservationStatus(ReservationStatus.APPROVED);
            }else {
                existingReservation.setReservationStatus(ReservationStatus.REJECTED);
            }
            reservationRepository.save(existingReservation);
            Room existingRoom = existingReservation.getRoom();
            existingRoom.setAvailable(false);
            roomRepository.save(existingRoom);
            return true;
        }
        return false;
    }


}
