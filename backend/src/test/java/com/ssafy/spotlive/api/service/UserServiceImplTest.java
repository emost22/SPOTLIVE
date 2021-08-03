package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.user.UserRes;
import com.ssafy.spotlive.db.entity.User;
import com.ssafy.spotlive.db.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findUserByAccountEmailTest() {
        // given
        String accountEmail = "kmk130519@naver.com";

        // when
        userService.findUserByAccountEmail(accountEmail);

        // then
        verify(userRepository).findUserByAccountEmail(accountEmail);
    }

    @Test
    void refreshTokensForExistUserTest() {
        // given
        String accountEmail = "kmk130519@naver.com";
        String accessToken = "someAccessTokenValue";
        String refreshToken = "someRefreshTokenValue";
        User user = new User();

        // when
        when(userRepository.findUserByAccountEmail(accountEmail)).thenReturn(user); // Mock 객체의 특정 Method 실행시 반환 값 지정
        UserRes userRes = userService.refreshTokensForExistUser(accountEmail, accessToken, refreshToken);

        // then
        verify(userRepository).findUserByAccountEmail(accountEmail);
        verify(userRepository).save(user);
        assertThat(userRes.getAccessToken()).isEqualTo(accessToken);
        assertThat(userRes.getRefreshToken()).isEqualTo(refreshToken);
    }

    @Test
    void insertUserTest() {
        // given
        User user = new User();

        // when
        userService.insertUser(user);

        // then
        verify(userRepository).save(user);
    }
}