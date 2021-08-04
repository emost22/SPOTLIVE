package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.entity.Follow;
import com.ssafy.spotlive.db.repository.FollowRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class FollowServiceImplTest {

    @Autowired
    FollowService followService;
    @Autowired
    FollowRepository followRepository;

    @Test
    void insertUserByUser() {
//        followRepository.deleteAll();
        followService.insertFollowByAccountEmail("sqk8657@naver.com",
                "kmk130519@naver.com");
        Optional<Follow> follow = followRepository
                .findFollowsByArtistAccountEmailAndFanAccountEmail("sqk8657@naver.com", "kmk130519@naver.com");
        assertThat(follow.get().getFan().getAccountEmail()).isEqualTo("kmk130519@naver.com");
    }
}