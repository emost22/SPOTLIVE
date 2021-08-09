package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class ShowInfoRepositoryTest {

    @Autowired
    private  ShowInfoRepository showInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    void findShowInfoByExistIdTest() {
        //given
        ShowInfo showInfo = new ShowInfo();
        Long showInfoId = showInfoRepository.save(showInfo).getShowInfoId();

        //when
        Optional<ShowInfo> optionalShowInfo = showInfoRepository.findShowInfoByShowInfoId(showInfoId);

        //then
        optionalShowInfo.ifPresent(info -> assertThat(info).isEqualTo(showInfo));
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

    @Test
    void findShowInfosByUser_AccountEmailNotTest() {
        //given
        String accountEmail = "newUser@new.com";
        User user = new User();
        user.setAccountEmail(accountEmail);
        userRepository.save(user);

        //when
        Optional<List<ShowInfo>> optionalShowInfoList = showInfoRepository.findShowInfosByUser_AccountEmailNot(accountEmail);

        //then
        optionalShowInfoList.ifPresent(showInfos -> showInfos.forEach(showInfo -> assertThat(showInfo.getUser()).isNotEqualTo(user)));
    }

}