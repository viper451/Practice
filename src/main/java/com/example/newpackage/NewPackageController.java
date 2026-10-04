package com.example.newpackage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class NewPackageController {

    private final SeatReservationService seatReservationService;

    public NewPackageController(SeatReservationService seatReservationService) {
        this.seatReservationService = seatReservationService;
    }

    @GetMapping("/hello")
    public Map<String, String> getHello() {
        return Map.of("message", "Hello from GET endpoint");
    }

    @PostMapping("/hello")
    public Map<String, String> postHello() {
        return Map.of("message", "Hello from POST endpoint");
    }

    @GetMapping("/seats")
    public Map<String, Object> getSeatStatus() {
        return seatReservationService.getSeatStatus();
    }

    @PostMapping("/seats/book")
    public ResponseEntity<Map<String, Object>> bookSeat(@RequestBody SeatBookingRequest request) {
        if (request == null || request.userId() == null || request.userId().isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                    "status", "ERROR",
                    "message", "userId is required"
            ));
        }

        Map<String, Object> response = seatReservationService.bookSeat(request.userId(), request.seatNumber());
        String status = String.valueOf(response.get("status"));

        if ("SUCCESS".equals(status) || "ALREADY_BOOKED".equals(status)) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
