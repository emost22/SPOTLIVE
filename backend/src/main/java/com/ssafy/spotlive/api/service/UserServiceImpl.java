package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.user.UserUpdatePatchReq;
import com.ssafy.spotlive.api.response.user.KakaoUserRes;
import com.ssafy.spotlive.api.response.user.UserRes;
import com.ssafy.spotlive.db.entity.User;
import com.ssafy.spotlive.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @FileName : UserServiceImpl
 * @작성자 : 김민권
 * @Class 설명 : User 관련 비즈니스 로직을 처리하는 Service
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserRes findUserByAccountEmail(String accountEmail) {
        /**
         * @Method Name : findUserByAccountEmail
         * @작성자 : 김민권
         * @Method 설명 : accountEmail을 통해 user 정보를 반환한다.
         */
        User user = userRepository.findUserByAccountEmail(accountEmail);
        return user == null ? null : UserRes.of(user);
    }

    @Override
    public UserRes findUserByAccessToken(String accessToken) {
        /**
         * @Method Name : findUserByAccessToken
         * @작성자 : 김민권
         * @Method 설명 : accessToken을 통해 user 정보를 반환한다.
         */
        User user = userRepository.findUserByAccessToken(accessToken);
        return user == null ? null : UserRes.of(user);
    }

    @Override
    public UserRes insertUser(User newUser) {
        /**
         * @Method Name : insertUser
         * @작성자 : 김민권
         * @Method 설명 : 존재하지않는 회원에 대해서 각 토큰 값을 포함한 user를 삽입하고 반환한다.
         */
        userRepository.save(newUser);
        return UserRes.of(newUser);
    }

    @Override
    public UserRes updateUser(UserUpdatePatchReq userUpdatePatchReq) {
        /**
         * @Method Name : updateUser
         * @작성자 : 김민권
         * @Method 설명 : 회원의 정보를 업데이트한다.
         */
        User updateUser = userRepository.findUserByAccountEmail(userUpdatePatchReq.getAccountEmail());
        User updatedUser = userUpdatePatchReq.toUser(updateUser);
        userRepository.save(updatedUser);

        return UserRes.of(updatedUser);
    }
}
