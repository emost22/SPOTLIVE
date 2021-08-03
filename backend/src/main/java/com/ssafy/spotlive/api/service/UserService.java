package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.KakaoUserRes;
import com.ssafy.spotlive.api.response.UserRes;
import com.ssafy.spotlive.db.entity.User;

import java.util.HashMap;

/**
 * @FileName : UserService
 * @작성자 : 김민권
 * @Class 설명 : User 관련 비즈니스 로직을 처리하는 Service의 Interface
 */
public interface UserService {

    String getKakaoLoginUrl();
    HashMap<String, String> getKakaoTokens(String code);
    KakaoUserRes getKakaoUserInfo(String tokenType, String accessToken);
    UserRes findUserByAccountEmail(String accountEmail);
    // User insertUser(UserPostReq userPostReq);
}
