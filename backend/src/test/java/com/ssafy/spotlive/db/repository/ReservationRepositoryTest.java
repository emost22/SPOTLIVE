package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Reservation;
import com.ssafy.spotlive.db.entity.Timetable;
import com.ssafy.spotlive.db.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class ReservationRepositoryTest {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    TimetableRepository timetableRepository;

    @Autowired
    UserRepository userRepository;

    String accountEmail = "newUser@new.com";
    User user;
    Timetable timetable;
    Reservation reservation;
    Long timetableId;

    @BeforeEach
    void setUp() {
        //given
        user = new User();
        user.setAccountEmail(accountEmail);
        userRepository.save(user);

        timetable = new Timetable();
        timetableId = timetableRepository.save(timetable).getTimetableId();

        reservation = new Reservation();
        reservation.setTimetable(timetable);
        reservation.setUser(user);
        reservationRepository.save(reservation);
    }

    @Test
    void deleteReservationByTimetable_TimetableIdTest() {
        //when
        Long exist = reservationRepository.deleteReservationByTimetable_TimetableIdAndUser_AccountEmail(timetableId, accountEmail);
        Long notExist = reservationRepository.deleteReservationByTimetable_TimetableIdAndUser_AccountEmail(-1L, "");

        //then
        assertThat(exist).isEqualTo(1);
        assertThat(notExist).isEqualTo(0);

    }

    @Test
    void existsByTimetable_TimetableIdAndUser_AccountEmailTest() {
        Long existTimetableId = 25L;
        String userAccountEmail = "ahyen@naver.com";
        Boolean isExist1 = reservationRepository.existsByTimetable_TimetableIdAndUser_AccountEmail(existTimetableId, userAccountEmail);
        assertThat(isExist1).isTrue();

        Long notExistTimetableId = -1L;
        Boolean isExist2 = reservationRepository.existsByTimetable_TimetableIdAndUser_AccountEmail(notExistTimetableId, userAccountEmail);
        assertThat(isExist2).isFalse();

        String notExistUserAccountEmail = "none";
        Boolean isExist3 = reservationRepository.existsByTimetable_TimetableIdAndUser_AccountEmail(notExistTimetableId, notExistUserAccountEmail);
        assertThat(isExist3).isFalse();
    }
}