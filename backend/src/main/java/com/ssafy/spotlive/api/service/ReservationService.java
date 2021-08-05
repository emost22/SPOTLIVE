package com.ssafy.spotlive.api.service;

public interface ReservationService {
    Boolean insertReservation(String accountEmail, Long timetableId);
}
