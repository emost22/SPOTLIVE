package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.repository.ReservationRepository;
import com.ssafy.spotlive.db.repository.TimetableRepository;
import com.ssafy.spotlive.db.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TimetableRepository timetableRepository;

    @InjectMocks
    private ReservationService reservationService = new ReservationServiceImpl();

    @Test
    void insertReservationTest() {
        String accountEmail = "kmk130519@naver.com";
        Long timetableId = 10L;

//        User user = new User();
//        user.setAccountEmail(accountEmail);
//        Timetable timetable = new Timetable();
//        timetable.setTimetableId(timetableId);
//        Reservation reservation = new Reservation();
//        reservation.setUser(user);

//        when(userRepository.findById(accountEmail)).thenReturn(Optional.ofNullable(user));
//        when(timetableRepository.findById(timetableId)).thenReturn(Optional.ofNullable(timetable));
        reservationService.insertReservation(accountEmail, timetableId);

        verify(userRepository).findById(accountEmail);
        verify(timetableRepository).findById(timetableId);
//        verify(reservationRepository).save(reservation);
    }

    @Test
    void deleteReservationByIdTest() {

        long timetableId = -1L;
        String accountEmail = "none";
        reservationService.deleteReservationById(timetableId, accountEmail);
        verify(reservationRepository).deleteReservationByTimetable_TimetableIdAndUser_AccountEmail(timetableId, accountEmail);
    }
}