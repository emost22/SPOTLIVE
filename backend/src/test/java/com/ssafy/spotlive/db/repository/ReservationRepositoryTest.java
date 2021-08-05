package com.ssafy.spotlive.db.repository;

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

    @Test
    void deleteReservationByTimetable_TimetableIdTest() {
        Long timetableId = 9L;
        String accountEmail = "kmk130519@naver.com";
        Long exist = reservationRepository.deleteReservationByTimetable_TimetableIdAndUser_AccountEmail(timetableId, accountEmail);
        assertThat(exist).isEqualTo(1);
    }
}