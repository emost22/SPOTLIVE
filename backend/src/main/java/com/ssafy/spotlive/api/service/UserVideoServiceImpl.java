package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.entity.UserVideo;
import com.ssafy.spotlive.db.repository.UserVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @FileName : UserVideoService
 * @작성자 : 김민권
 * @Class 설명 : UserVideo 관련 비즈니스 로직을 처리하는 Service
 */
public class UserVideoServiceImpl implements UserVideoService {

    @Autowired
    UserVideoRepository userVideoRepository;

    @Override
    public boolean isInOtherVideo(String accountEmail) {

        return false;
    }

    @Override
    public UserVideo joinUserVideo(String accountEmail, Long videoId) {
        return null;
    }

    @Override
    public UserVideo leaveUserVideo(String accountEmail, Long videoId) {
        return null;
    }
}
