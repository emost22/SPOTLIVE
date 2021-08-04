package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.user.UserUpdatePatchReq;
import com.ssafy.spotlive.api.response.user.KakaoUserRes;
import com.ssafy.spotlive.api.response.user.UserRes;
import com.ssafy.spotlive.db.entity.User;
import org.springframework.http.ResponseEntity;

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
    UserRes refreshTokensForExistUser(String email, String accessToken, String refreshToken);
    UserRes insertUser(User newUser);
    String accessTokenUpdate(String accountEmail);
    int isValidToken(String accessToken);
    UserRes updateUser(UserUpdatePatchReq userUpdatePatchReq);
}
