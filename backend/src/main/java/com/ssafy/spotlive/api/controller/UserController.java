package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.response.user.KakaoUserRes;
import com.ssafy.spotlive.api.response.user.UserRes;
import com.ssafy.spotlive.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation(value = "kakao token을 얻어와 유저정보 조회 후, 회원가입 혹은 로그인 수행 후 정보 반환", notes = "Token은 매 실행 시 갱신된다.")
    public ResponseEntity<UserRes> getTokenAndJoinOrLogin(@ApiParam(value="Token 생성에 사용될 Code", required = true) @RequestParam("code") String code) {
        /**
         * @Method Name : getTokenAndJoinOrLogin
         * @작성자 : 김민권
         * @Method 설명 : 카카오톡 로그인 이후 Token을 얻어온 후, 회원가입 또는 로그인을 처리하는 Method
         */

        // 1. Token을 발급받는다.
        HashMap<String, String> kakaoTokens = userService.getKakaoTokens(code);

        // 2. Token 값을 통해 UserInfo를 받아온다.
        KakaoUserRes kakaoUserRes = userService.getKakaoUserInfo(kakaoTokens.get("token_type"), kakaoTokens.get("access_token"));

        // 3. UserInfo의 내용이 회원 DB에 존재하는가?
        UserRes userResForCheck = userService.findUserByAccountEmail(kakaoUserRes.getKakao_account().getEmail());

        UserRes userRes;
        if(userResForCheck != null) {
            // 존재한다면 Token 값을 갱신하고 반환한다.
            userRes = userService.refreshTokensForExistUser(kakaoUserRes.getKakao_account().getEmail(), kakaoTokens.get("access_token"), kakaoTokens.get("refresh_token"));
        } else {
            // 존재하지 않는다면 회원 가입 시키고 반환한다.
            userRes = userService.insertUser(kakaoUserRes.toUser(kakaoTokens.get("access_token"), kakaoTokens.get("refresh_token")));
        }

        return new ResponseEntity<>(userRes, HttpStatus.OK);
    }

    @GetMapping("/kakao/update/{accountEmail}")
    @ApiOperation(value = "Access Token을 재발급한다.", notes = "재발급 된 Token을 반환한다.")
    public ResponseEntity<String> accessTokenUpdate(@ApiParam(value="Token을 재발급할 User email", required = true) @PathVariable("accountEmail") String accountEmail) {
        /**
         * @Method Name : accessTokenUpdate
         * @작성자 : 김민권
         * @Method 설명 : AccessToken이 만료되었음을 확인 시, refresh token을 통해 재발급을 요청하는 Method
         */
        String newToken = userService.accessTokenUpdate(accountEmail);

        return new ResponseEntity<>(newToken, HttpStatus.OK);
    }
}
