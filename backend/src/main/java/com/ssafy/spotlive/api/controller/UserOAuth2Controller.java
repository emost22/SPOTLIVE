package com.ssafy.spotlive.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @FileName : UserOAuth2Controller
 * @작성자 : 김민권
 * @Class 설명 : Kakao OAuth 관련 인증을 매핑하는 REST Controller
 */
@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequestMapping("/api/auth/kakao/")
@CrossOrigin( value = {"*"}, maxAge = 6000)
public class UserOAuth2Controller {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoRestApiKey;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoRestSecretKey;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUrl;

    @GetMapping("/showlogin")
    @ApiOperation(value = "카카오 로그인을 위한 요청 URL 전송", notes = "카카오 로그인을 위한 요청 URL을 전송한다. 해당 URL로 GET 요청을 보내면 된다.")
    public ResponseEntity<String> showLogin() {
        /**
         * @Method Name : showLogin
         * @작성자 : 김민권
         * @Method 설명 : 카카오 로그인을 위한 요청 URL을 전송하는 Method, 해당 URL로 GET 요청을 전송 시 카카오톡 로그인 페이지로 이동된다.
         */
        StringBuilder reqUrl = new StringBuilder()
                .append("https://kauth.kakao.com/oauth/authorize?client_id=").append(kakaoRestApiKey)
                .append("&redirect_uri=").append(kakaoRedirectUrl)
                .append("&response_type=code");

        return new ResponseEntity<>(reqUrl.toString(), HttpStatus.OK);
    }

}
