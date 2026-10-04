package com.example.newpackage;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SeatReservationService {

    private static final int TOTAL_SEATS = 40;
    private final Set<Integer> bookedSeats = ConcurrentHashMap.newKeySet();
    private final Map<String, Integer> userSeatMap = new ConcurrentHashMap<>();
    private final AtomicInteger nextSeat = new AtomicInteger(20);

    public Map<String, Object> getSeatStatus() {
        int available = TOTAL_SEATS - bookedSeats.size();
        return Map.of(
                "totalSeats", TOTAL_SEATS,
                "bookedSeats", bookedSeats.size(),
                "availableSeats", available,
                "seatNumbers", buildSeatNumbers()
        );
    }

    public synchronized Map<String, Object> bookSeat(String userId, Integer requestedSeat) {
        if (userSeatMap.containsKey(userId)) {
            return Map.of(
                    "status", "ALREADY_BOOKED",
                    "message", "User already booked a seat",
                    "userId", userId,
                    "seatNumber", userSeatMap.get(userId)
            );
        }

        if (requestedSeat == null) {
            int assignedSeat = allocateNextSeat();
            if (assignedSeat == -1) {
                return Map.of(
                        "status", "FULL",
                        "message", "No seats available",
                        "userId", userId
                );
            }

            bookedSeats.add(assignedSeat);
            userSeatMap.put(userId, assignedSeat);
            return Map.of(
                    "status", "SUCCESS",
                    "message", "Seat reserved successfully",
                    "userId", userId,
                    "seatNumber", assignedSeat
            );
        }

        if (requestedSeat < 1 || requestedSeat > TOTAL_SEATS) {
            return Map.of(
                    "status", "INVALID",
                    "message", "Seat number must be between 1 and " + TOTAL_SEATS,
                    "userId", userId,
                    "seatNumber", requestedSeat
            );
        }

        if (bookedSeats.contains(requestedSeat)) {
            return Map.of(
                    "status", "UNAVAILABLE",
                    "message", "Seat already taken",
                    "userId", userId,
                    "seatNumber", requestedSeat
            );
        }

        bookedSeats.add(requestedSeat);
        userSeatMap.put(userId, requestedSeat);
        return Map.of(
                "status", "SUCCESS",
                "message", "Seat reserved successfully",
                "userId", userId,
                "seatNumber", requestedSeat
        );
    }

    private int allocateNextSeat() {
        for (int i = 0; i < TOTAL_SEATS; i++) {
            int candidate = nextSeat.getAndUpdate(current -> current == TOTAL_SEATS ? 1 : current + 1);
            if (!bookedSeats.contains(candidate)) {
                return candidate;
            }
        }
        return -1;
    }

    private java.util.List<Integer> buildSeatNumbers() {
        java.util.List<Integer> seats = new java.util.ArrayList<>();
        for (int i = 1; i <= TOTAL_SEATS; i++) {
            seats.add(i);
        }
        return seats;
    }
}

