package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.entity.UserVideo;
import com.ssafy.spotlive.db.repository.UserVideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @FileName : UserVideoService
 * @작성자 : 김민권
 * @Class 설명 : UserVideo 관련 비즈니스 로직을 처리하는 Service
 */
@Service
public class UserVideoServiceImpl implements UserVideoService {

    @Autowired
    UserVideoRepository userVideoRepository;

    @Override
    public UserVideoRes joinUserVideo(String accountEmail, long videoId) {
        /**
         * @Method Name : joinUserVideo
         * @작성자 : 김민권
         * @Method 설명 : accountEmail, videoId를 UserVideo Table에 저장한다.
         */
        UserVideoReq userVideoReq = makeUserVideoReq(accountEmail, videoId);
        return UserVideoRes.of(userVideoRepository.save(userVideoReq.toUserVideo()));
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
