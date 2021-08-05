package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.entity.Follow;
import com.ssafy.spotlive.db.entity.User;
import com.ssafy.spotlive.db.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @FileName : FollowServiceImpl
 * @작성자 : 권영린
 * @Class 설명 : 팔로우 관련 기능을 위한 ServiceImpl 정의.
 */
@Service
public class FollowServiceImpl implements FollowService{
    @Autowired
    FollowRepository followRepository;

    @Override
    public void insertFollowByAccountEmail(String artistEmail, String fanEmail){
        Follow follow = new Follow();
        User artist = new User();
        User fan = new User();
        artist.setAccountEmail(artistEmail);
        fan.setAccountEmail(fanEmail);
        follow.setArtist(artist);
        follow.setFan(fan);
        followRepository.save(follow);
    }

//    @Override
//    public void deleteFollowByAccountEmail(String artistEmail, String fanEmail){
//        Follow follow = new Follow();
//        User artist = new User();
//        User fan = new User();
//        artist.setAccountEmail(artistEmail);
//        fan.setAccountEmail(fanEmail);
//        follow.setArtist(artist);
//        follow.setFan(fan);
//        followRepository.deleteByUser(follow).;
//    }
}
