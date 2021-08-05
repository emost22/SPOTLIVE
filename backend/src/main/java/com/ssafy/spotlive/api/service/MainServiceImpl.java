package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.main.UserFindFollowGetRes;
import com.ssafy.spotlive.api.response.main.VideoFindMainVideoRes;
import com.ssafy.spotlive.api.response.main.VideoGetRes;
import com.ssafy.spotlive.db.entity.Follow;
import com.ssafy.spotlive.db.entity.User;
import com.ssafy.spotlive.db.repository.UserRepository;
import com.ssafy.spotlive.db.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @FileName : MainServiceImpl
 * @작성자 : 강용수
 * @Class 설명 : 메인화면 데이터 조회를 위한 Service 정의.
 */
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public VideoGetRes findAllVideoByModeAndCategoryId(int page, int size, Long categoryId, String mode){
        /**
         * @Method Name : findAllVideoByModeAndCategoryId
         * @작성자 : 강용수
         * @Method 설명 : Query Parameter 조건에 맞는 홍보 / 공연 / 소통 영상들을 검색하는 메소드
         */
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        if (categoryId == null)
            return VideoGetRes.of(videoRepository.findVideosByMode(pageRequest, mode), pageRequest, sort);
        else
            return VideoGetRes.of(videoRepository.findVideosByModeAndCategory_CategoryId(pageRequest, mode, categoryId), pageRequest, sort);
    }

    @Override
    public VideoGetRes findAllReplayVideoByIsLiveAndCategoryId(int page, int size, Long categoryId){
        /**
         * @Method Name : findAllReplayVideoByIsLiveAndCategoryId
         * @작성자 : 강용수
         * @Method 설명 : Query Parameter 조건에 맞는 다시보기 영상들을 조회수 순으로 검색하는 메소드
         */
        Sort sort = Sort.by(Sort.Direction.DESC, "hit");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        if (categoryId == null)
            return VideoGetRes.of(videoRepository.findVideosByIsLive(pageRequest, false), pageRequest, sort);
        else
            return VideoGetRes.of(videoRepository.findVideosByIsLiveAndCategory_CategoryId(pageRequest, false, categoryId), pageRequest, sort);
    }

    @Override
    public VideoGetRes findAllLiveVideoByIsLiveAndCategoryId(int page, int size, Long categoryId){
        /**
         * @Method Name : findAllLiveVideoByIsLiveAndCategoryId
         * @작성자 : 강용수
         * @Method 설명 : Query Parameter 조건에 맞는 라이브 영상들을 시청자 수 순으로 검색하는 메소드
         */
        PageRequest pageRequest = PageRequest.of(page, size);

        VideoGetRes videoGetRes = null;
        if (categoryId == null)
            videoGetRes = VideoGetRes.of(videoRepository.findVideosByIsLive(pageRequest, true), pageRequest, null);
        else
            videoGetRes = VideoGetRes.of(videoRepository.findVideosByIsLiveAndCategory_CategoryId(pageRequest, true, categoryId), pageRequest, null);

        Collections.sort(videoGetRes.getVideoResList(), new Comparator<VideoFindMainVideoRes>() {
            @Override
            public int compare(VideoFindMainVideoRes v1, VideoFindMainVideoRes v2) {
                long hit1 = v1.getHitLive();
                long hit2 = v2.getHitLive();

                if (hit1 < hit2)
                    return 1;
                else if (hit1 == hit2)
                    return 0;
                else
                    return -1;
            }
        });

        return videoGetRes;
    }

    @Override
    public VideoGetRes findAllFollowVideoByCategoryId(int page, int size, Long categoryId, String accountEmail){
        /**
         * @Method Name : findAllFollowVideoByCategoryId
         * @작성자 : 강용수
         * @Method 설명 : 자신이 팔로우한 유저의 Video를 조회하는 메소드
         */
        List<String> accountEmailList = userRepository.findById(accountEmail).map(User::getFanList).orElse(null).stream()
                .map(fan -> followToString(fan)).collect(Collectors.toList());

        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        if (categoryId == null)
            return VideoGetRes.of(videoRepository.findVideosByUser_AccountEmailIn(pageRequest, accountEmailList), pageRequest, sort);
        else
            return VideoGetRes.of(videoRepository.findVideosByCategory_CategoryIdAndUser_AccountEmailIn(pageRequest, categoryId, accountEmailList), pageRequest, sort);
    }

    public String followToString(Follow follow){
        /**
         * @Method Name : followToString
         * @작성자 : 강용수
         * @Method 설명 : Follow 객체에서 artist의 accountEmail만 가져오는 메소드
         */
        return follow.getArtist().getAccountEmail();
    }

    @Override
    public List<UserFindFollowGetRes> findAllFollowByFan(String accountEmail){
        /**
         * @Method Name : findAllFollowByFan
         * @작성자 : 강용수
         * @Method 설명 : 자신이 팔로우한 유저 리스트를 조회하는 메소드
         */
        return userRepository.findById(accountEmail).map(User::getFanList).orElse(null).stream()
                .map(fan -> UserFindFollowGetRes.of(fan.getArtist())).collect(Collectors.toList());
    }
}