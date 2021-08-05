package com.ssafy.spotlive.db.repository;


import com.ssafy.spotlive.db.entity.UserVideo;
import com.ssafy.spotlive.db.entity.UserVideoId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest // 복합키를 통한 Repository 생성 시, JpaRespository 상속이 올바르게 되었는지 기본 기능으로 테스트해본다.
class UserVideoRepositoryTest {

    @Autowired
    UserVideoRepository userVideoRepository;

    @Test
    void findAllTest() {
        // given
        // when
        List<UserVideo> userVideoList = userVideoRepository.findAll();

        // then
        assertThat(userVideoList.size()).isEqualTo(3);
    }

    @Test
    void findByIdTest() {
        // given
        String accountEmail = "emoney96@naver.com";
        long videoId = 8;
        UserVideoId userVideoId = new UserVideoId();
        userVideoId.setVideo(videoId);
        userVideoId.setUser(accountEmail);

        // when
        Optional<UserVideo> optionalUserVideo = userVideoRepository.findById(userVideoId);

        // then
        assertThat(optionalUserVideo).isNotEqualTo(Optional.empty());
        assertThat(optionalUserVideo.get().getUser().getAccountEmail()).isEqualTo(accountEmail);
        assertThat(optionalUserVideo.get().getVideo().getVideoId()).isEqualTo(videoId);
    }
}