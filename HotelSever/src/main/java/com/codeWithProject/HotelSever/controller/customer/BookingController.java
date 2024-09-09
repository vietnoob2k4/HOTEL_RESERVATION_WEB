package com.codeWithProject.HotelSever.controller.customer;


import com.codeWithProject.HotelSever.dto.ReservationDto;
import com.codeWithProject.HotelSever.services.customer.booking.BookingService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

@PostMapping("/book")
    public ResponseEntity<?> postBooking(@RequestBody ReservationDto reservationDto){
        boolean success = bookingService.postReservation(reservationDto);
        if(success) {
            return ResponseEntity.status(HttpStatus.OK).build();

        }
        else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/booking/{userId}/{pageNumber}")
    public ResponseEntity<?> getAllBookingByUserId(@PathVariable Long userId, @PathVariable int pageNumber) {
        try {
            System.out.println("User ID: " + userId); // In giá trị userId
            System.out.println("Page Number: " + pageNumber); // In giá trị pageNumber
            return ResponseEntity.ok(bookingService.getAllReservationByUserId(userId, pageNumber));
        } catch (Exception e) {
            e.printStackTrace(); // In thông tin lỗi ra console
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
