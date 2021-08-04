package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.main.VideoFindByModeGetRes;
import com.ssafy.spotlive.db.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @FileName : MainServiceImpl
 * @작성자 : 강용수
 * @Class 설명 : 메인화면 데이터 조회를 위한 Service 정의.
 */
@Service
public class MainServiceImpl implements MainService {
    @Autowired
    VideoRepository videoRepository;

    @Override
    public VideoFindByModeGetRes findAllVideoByModeAndCategoryId(int page, int size, Long categoryId, String mode){
        /**
         * @Method Name : findAllVideoByModeAndCategoryId
         * @작성자 : 강용수
         * @Method 설명 : Query Parameter 조건에 맞는 홍보 / 공연 / 소통 영상들을 검색하는 메소드
         */
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        if (categoryId == null)
            return VideoFindByModeGetRes.of(videoRepository.findVideosByMode(pageRequest, mode), pageRequest, sort);
        else
            return VideoFindByModeGetRes.of(videoRepository.findVideosByModeAndCategory_CategoryId(pageRequest, mode, categoryId), pageRequest, sort);
    }

    @Override
    public VideoFindByModeGetRes findAllReplayVideoByIsLiveAndCategoryId(int page, int size, Long categoryId){
        /**
         * @Method Name : findAllReplayVideoByIsLiveAndCategoryId
         * @작성자 : 강용수
         * @Method 설명 : Query Parameter 조건에 맞는 다시보기 영상들을 조회수 순으로 검색하는 메소드
         */
        Sort sort = Sort.by(Sort.Direction.DESC, "hit");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        if (categoryId == null)
            return VideoFindByModeGetRes.of(videoRepository.findVideosByIsLive(pageRequest, false), pageRequest, sort);
        else
            return VideoFindByModeGetRes.of(videoRepository.findVideosByIsLiveAndCategory_CategoryId(pageRequest, false, categoryId), pageRequest, sort);
    }
}