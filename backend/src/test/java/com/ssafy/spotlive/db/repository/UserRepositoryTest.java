package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findUserByAccountEmailTest() {
        // given
        String accountEmail = "kmk130519@naver.com";
        String userName = "김민권";

        // then
        User userByAccountEmail = userRepository.findUserByAccountEmail(accountEmail);

        // when
        assertThat(userByAccountEmail.getAccountEmail()).isEqualTo(accountEmail);
        assertThat(userByAccountEmail.getUserName()).isEqualTo(userName);
    }

    @Test
    void findUserByAccountEmailTestIfNull() {
        // given
        String accountEmail = "expectNullId@younsubabo.com";

        // then
        User userByAccountEmail = userRepository.findUserByAccountEmail(accountEmail);

        // when
        assertThat(userByAccountEmail).isNull();
    }
}