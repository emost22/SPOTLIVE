package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.ShowInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class ShowInfoRepositoryTest {

    @Autowired
    private  ShowInfoRepository showInfoRepository;

    @Test
    @Transactional
    void findShowInfoByExistIdTest() {
        //given
        ShowInfo showInfo = new ShowInfo();
        Long showInfoId = showInfoRepository.save(showInfo).getShowInfoId();

        //when
        Optional<ShowInfo> optionalShowInfo = showInfoRepository.findShowInfoByShowInfoId(showInfoId);

        //then
        assertThat(optionalShowInfo.get()).isEqualTo(showInfo);
    }

    @Test
    void findShowInfoByNotExistIdTest() {
        //given
        Long notExistShowInfoId = -1L;

        //when
        Optional<ShowInfo> showInfo = showInfoRepository.findShowInfoByShowInfoId(notExistShowInfoId);

        //then
        assertThat(showInfo).isEmpty();
    }

    @Test
    @Transactional
    void deleteShowInfoByExistIdTest() {
        //given
        ShowInfo showInfo = new ShowInfo();
        Long showInfoId = showInfoRepository.save(showInfo).getShowInfoId();

        //given
        Long id = showInfoRepository.deleteShowInfoByShowInfoId(showInfoId);

        //then
        assertThat(id).isEqualTo(1);
    }

    @Test
    void deleteShowInfoByNotExistIdTest(){
        //given
        Long notExistShowInfoId = -1L;

        //when
        Long id = showInfoRepository.deleteShowInfoByShowInfoId(notExistShowInfoId);

        //then
        assertThat(id).isEqualTo(0);
    }
}