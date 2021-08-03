package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @FileName : UserController
 * @작성자 : 김민권
 * @Class 설명 : OAuth, User 관련 인증을 매핑하는 REST Controller
 */
@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequestMapping("/api/auth/")
@CrossOrigin( value = {"*"}, maxAge = 6000)
public class UserController {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoRestApiKey;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoRestSecretKey;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String kakaoRedirectUrl;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String kakaoUserInfoUrl;

    private final String KAKAO_URL = "https://kauth.kakao.com";

    @Autowired
    UserService userService;

    @GetMapping("/kakao/showlogin")
    @ApiOperation(value = "카카오 로그인을 위한 요청 URL 전송", notes = "카카오 로그인을 위한 요청 URL을 전송한다. 해당 URL로 GET 요청을 보내면 된다.")
    public ResponseEntity<String> showLogin() {
        /**
         * @Method Name : showLogin
         * @작성자 : 김민권
         * @Method 설명 : 카카오 로그인을 위한 요청 URL을 전송하는 Method, 해당 URL로 GET 요청을 전송 시 카카오톡 로그인 페이지로 이동된다.
         */

        return new ResponseEntity<>(userService.getKakaoLoginUrl(), HttpStatus.OK);
    }

    @GetMapping("/kakao/login")
    @ApiOperation(value = "카카오 로그인 이후 token을 얻어온다.", notes = "카카오 로그인 이후 해당 Method로 자동 redirect 된다.")
    public ResponseEntity<HashMap> getTokenAndJoinOrLogin(@ApiParam(value="Token 생성에 사용될 Code", required = true) @RequestParam("code") String code) {
        /**
         * @Method Name : getTokenAndJoinOrLogin
         * @작성자 : 김민권
         * @Method 설명 : 카카오톡 로그인 이후 Token을 얻어온 후, 회원가입 또는 로그인을 처리하는 Method
         */

        // 1. Token을 발급받는다.
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();
        httpBody.add("grant_type", "authorization_code");
        httpBody.add("client_id", kakaoRestApiKey);
        httpBody.add("redirect_uri", kakaoRedirectUrl);
        httpBody.add("code", code);
        httpBody.add("client_secret", kakaoRestSecretKey);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(httpBody, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<HashMap> tokenResponseEntity = restTemplate.exchange(KAKAO_URL + "/oauth/token", HttpMethod.POST, kakaoTokenRequest, HashMap.class);

        // 2. Token 값을 통해 UserInfo를 받아온다.


        // 3. UserInfo의 내용이 회원 DB에 존재하는가?

        // 3-1. 존재한다면 Token 값을 갱신하고 반환한다.

        // 3-2. 존재하지 않는다면 회원 가입 시키고 반환한다.

        return tokenResponseEntity;
    }

}
