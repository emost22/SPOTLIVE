package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.entity.User;

import java.util.HashMap;

/**
 * @FileName : UserService
 * @작성자 : 김민권
 * @Class 설명 : User 관련 비즈니스 로직을 처리하는 Service의 Interface
 */
public interface UserService {

    String getKakaoLoginUrl();
    // HashMap getKakaoTokens();
    // User findUser(String email);
    // User insertUser(UserPostReq userPostReq);
}
