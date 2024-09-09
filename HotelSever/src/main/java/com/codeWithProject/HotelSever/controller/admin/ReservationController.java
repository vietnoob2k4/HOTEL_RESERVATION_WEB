package com.codeWithProject.HotelSever.controller.admin;


import com.codeWithProject.HotelSever.entity.Reservation;
import com.codeWithProject.HotelSever.services.admin.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ReservationController {


    private final ReservationService reservationService;


    @GetMapping("/reservations/{pageNumber}")
    public ResponseEntity<?> getAllReservations(@PathVariable int pageNumber) {


        try {
            return ResponseEntity.ok(reservationService.getAllReservations(pageNumber));


        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("sth wrong");


        }
    }

@PutMapping("/reservation/{id}/{status}")
    public ResponseEntity<?> changeReservation(@PathVariable Long id, @PathVariable String status) {
        boolean success = reservationService.changeReservationStatus(id, status);
        if(success){
            return  ResponseEntity.ok().build();
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("sth wrong");
        }
    }
}
