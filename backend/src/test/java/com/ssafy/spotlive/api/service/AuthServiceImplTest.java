package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.user.KakaoUserRes;
import com.ssafy.spotlive.api.response.user.UserRes;
import com.ssafy.spotlive.db.entity.User;
import com.ssafy.spotlive.db.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class AuthServiceImplTest { // Test 작업은 kmk130519@naver.com 유저의 AccessToken이 현재 Vaild해야만 한다.

    @Autowired
    AuthServiceImpl authService;

    @Autowired
    UserRepository userRepository;

    @Test
    void getKakaoLoginUrlTest() {
        // given
        String kakaoLoginUrl = new StringBuilder()
                .append("https://kauth.kakao.com").append("/oauth/authorize?client_id=").append("68c5cb3f22fe800fc5606851a8bed982")
                .append("&redirect_uri=").append("http://localhost:8080/api/auth/kakao/login")
                .append("&response_type=code")
                .toString();

        // when
        String result = authService.getKakaoLoginUrl();

        // then
        assertThat(result).isEqualTo(kakaoLoginUrl);
    }

    @Test
    void getKakaoUserInfoTest() {
        // given
        String accountEmail = "kmk130519@naver.com";
        User myUser = userRepository.findUserByAccountEmail(accountEmail);
        String accessToken = myUser.getAccessToken();

        // when
        KakaoUserRes kakaoUserInfo = authService.getKakaoUserInfo(accessToken);

        // then
        assertThat(kakaoUserInfo.getKakao_account().getEmail()).isEqualTo(accountEmail);
    }

    @Test
    @Transactional // 자동 Rollback
    void refreshTokensForExistUserTest() {
        // given
        String accountEmail = "kmk130519@naver.com";
        String newAccessToken = "newAccessToken";
        String newRefreshToken = "newRefreshToken";

        // when
        UserRes userRes = authService.refreshTokensForExistUser(accountEmail, newAccessToken, newRefreshToken);

        // then
        User user = userRepository.findUserByAccountEmail(accountEmail);
        assertThat(user.getAccountEmail()).isEqualTo(accountEmail);
        assertThat(user.getAccessToken()).isEqualTo(newAccessToken);
        assertThat(user.getRefreshToken()).isEqualTo(newRefreshToken);
    }

    @Test
    @Transactional
    void accessTokenUpdateTest() {
        // given
        String accountEmail = "kmk130519@naver.com";
        User user = userRepository.findUserByAccountEmail(accountEmail);
        String beforeAccessToken = user.getAccessToken();
        String refreshToken = user.getRefreshToken();

        // when
        String afterAccessToken = authService.accessTokenUpdate(accountEmail);

        // then
        User newUser = userRepository.findUserByAccountEmail(accountEmail);
        assertThat(user.getAccountEmail()).isEqualTo(accountEmail);
        assertThat(user.getAccessToken()).isEqualTo(afterAccessToken);
        assertThat(user.getAccessToken()).isNotEqualTo(beforeAccessToken);
    }

    @Test
    void isValidTokenTest() {
        // given
        String accountEmail = "kmk130519@naver.com";
        User user = userRepository.findUserByAccountEmail(accountEmail);
        String accessToken = user.getAccessToken();

        // when
        int validTokenCode = authService.isValidToken(accessToken);

        // then
        assertThat(validTokenCode).isIn(200, 401);
    }
}