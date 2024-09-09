package com.codeWithProject.HotelSever.services.customer.booking;

import com.codeWithProject.HotelSever.dto.ReservationDto;
import com.codeWithProject.HotelSever.dto.ReservationResponseDto;
import com.codeWithProject.HotelSever.entity.Reservation;
import com.codeWithProject.HotelSever.entity.Room;
import com.codeWithProject.HotelSever.entity.User;
import com.codeWithProject.HotelSever.enums.ReservationStatus;
import com.codeWithProject.HotelSever.repository.ReservationRepository;
import com.codeWithProject.HotelSever.repository.RoomRepository;
import com.codeWithProject.HotelSever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements  BookingService{
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final ReservationRepository reservationRepository;
public  static final int SEARCH_RESULT_PER_PAGE  = 90;
    public boolean postReservation(ReservationDto reservationDto) {
        Optional<User> optionalUser = userRepository.findById(reservationDto.getUserId());
        Optional<Room> optionalRoom = roomRepository.findById(reservationDto.getRoomId());

        // Kiểm tra xem ngày check-in có muộn hơn ngày hôm nay không
        if (reservationDto.getCheckInDate().isBefore(LocalDate.now())) {
            // Ngày check-in không hợp lệ
            return false;
        }
        if (reservationDto.getCheckOutDate().isBefore(LocalDate.now())) {
            // Ngày check-out không hợp lệ, không làm phòng bị chiếm
            return false;
        }

        if (optionalRoom.isPresent() && optionalUser.isPresent()) {
            Reservation reservation = new Reservation();
            reservation.setRoom(optionalRoom.get());
            reservation.setUser(optionalUser.get());
            reservation.setCheckInDate(reservationDto.getCheckInDate());
            reservation.setCheckOutDate(reservationDto.getCheckOutDate());
            reservation.setReservationStatus(ReservationStatus.PENDING);

            Long days = ChronoUnit.DAYS.between(reservationDto.getCheckInDate(), reservationDto.getCheckOutDate());
            reservation.setPrice(optionalRoom.get().getPrice() * days);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }
    public ReservationResponseDto getAllReservationByUserId(Long userId, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, SEARCH_RESULT_PER_PAGE);

        // Gọi phương thức với tham số đúng thứ tự
        Page<Reservation> reservationsPage = reservationRepository.findAllByUserId(userId, pageable);

        ReservationResponseDto reservationResponseDto = new ReservationResponseDto();
        reservationResponseDto.setReservationDtoList(reservationsPage.stream()
                .map(Reservation::getReservationDto)
                .collect(Collectors.toList()));
        reservationResponseDto.setPageNumber(reservationsPage.getPageable().getPageNumber());
        reservationResponseDto.setTotalPage(reservationsPage.getTotalPages());

        return reservationResponseDto;
    }

}
