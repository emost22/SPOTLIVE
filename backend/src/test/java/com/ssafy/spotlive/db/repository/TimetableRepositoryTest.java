package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.entity.Timetable;
import com.ssafy.spotlive.db.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class TimetableRepositoryTest {

    @Autowired
    private TimetableRepository timetableRepository;
    @Autowired
    private ShowInfoRepository showInfoRepository;
    @Autowired
    private  UserRepository userRepository;

    String accountEmail;
    User user;
    ShowInfo showInfo;
    Long showInfoId;

    @BeforeEach
    void setUp() {
        //given
        accountEmail = "newUser@new.com";
        user = new User();
        user.setAccountEmail(accountEmail);
        userRepository.save(user);

        showInfo = new ShowInfo();
        showInfo.setUser(user);
        showInfoId = showInfoRepository.save(showInfo).getShowInfoId();

        for(int i = 0; i < 3; i++){
            Timetable timetable = new Timetable();
            timetable.setShowInfo(showInfo);
            timetable.setDateTime(LocalDateTime.now().plusHours(i));
            timetableRepository.save(timetable);
        }
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