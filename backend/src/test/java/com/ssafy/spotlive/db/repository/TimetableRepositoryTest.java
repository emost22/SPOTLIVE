package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.entity.Timetable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class TimetableRepositoryTest {

    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private ShowInfoRepository showInfoRepository;
    @Autowired
    private  UserRepository userRepository;
    @Test
    void deleteAllByShowInfo_ShowInfoId() {
    }

    @Test
    void findByDateTimeBetween() {
    }

    @Test
    @Transactional
    void findTimetableByShowInfo_ShowInfoIdAndDateTimeBetweenTest() {
        LocalDateTime start = LocalDateTime.now().minusMinutes(30);
        LocalDateTime end = LocalDateTime.now().plusMinutes(30);

        Long showInfoId = 59L;
        ShowInfo showInfo = showInfoRepository.getById(showInfoId);
        timetableRepository.deleteAllByShowInfo_ShowInfoId(showInfoId);
        Timetable newTimetable = new Timetable();
        newTimetable.setShowInfo(showInfo);
        newTimetable.setDateTime(LocalDateTime.now());
        timetableRepository.save(newTimetable);
        Optional<Timetable> timetable = timetableRepository.findTimetableByShowInfo_ShowInfoIdAndDateTimeBetween(showInfoId, start, end);

        assertThat(timetable).isEqualTo(newTimetable);


    }
}