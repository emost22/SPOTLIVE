package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.ShowInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.util.Optional;


@SpringBootTest
class ShowInfoRepositoryTest {

    @Autowired
    private  ShowInfoRepository showInfoRepository;

    @Test
    void findShowInfoByExistIdTest() {
        Long existShowInfoId = 1L;
        Optional<ShowInfo> showInfo = showInfoRepository.findShowInfoByShowInfoId(existShowInfoId);
        assertThat(showInfo).isNotEmpty();
    }

    @Test
    void findShowInfoByNotExistIdTest() {
        Long notExistShowInfoId = -1L;
        Optional<ShowInfo> showInfo = showInfoRepository.findShowInfoByShowInfoId(notExistShowInfoId);
        assertThat(showInfo).isEmpty();
    }
}