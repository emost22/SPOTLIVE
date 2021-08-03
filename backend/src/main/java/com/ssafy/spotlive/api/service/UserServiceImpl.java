package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @FileName : UserServiceImpl
 * @작성자 : 김민권
 * @Class 설명 : User 관련 비즈니스 로직을 처리하는 Service
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoRestApiKey;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoRestSecretKey;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUrl;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String kakaoUserInfoUrl;

    private final String KAKAO_URL = "https://kauth.kakao.com";

    @Override
    public String getKakaoLoginUrl() {
        /**
         * @Method Name : getKakaoLoginUrl
         * @작성자 : 김민권
         * @Method 설명 : 카카오 로그인을 위한 요청 URL을 반환하는 Method, 해당 URL로 GET 요청을 전송 시 카카오톡 로그인 페이지로 이동된다.
         */
        return new StringBuilder()
                .append(KAKAO_URL).append("/oauth/authorize?client_id=").append(kakaoRestApiKey)
                .append("&redirect_uri=").append(kakaoRedirectUrl)
                .append("&response_type=code")
                .toString();
    }
}
