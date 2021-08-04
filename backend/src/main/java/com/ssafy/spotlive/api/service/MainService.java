package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.main.VideoGetRes;

/**
 * @FileName : MainService
 * @작성자 : 강용수
 * @Class 설명 : 메인화면 데이터 조회를 위한 Service 정의.
 */
public interface MainService {
    VideoGetRes findAllVideoByModeAndCategoryId(int page, int size, Long categoryId, String mode);
    VideoGetRes findAllReplayVideoByIsLiveAndCategoryId(int page, int size, Long categoryId);
    VideoGetRes findAllLiveVideoByIsLiveAndCategoryId(int page, int size, Long categoryId);
    VideoGetRes findAllFollowVideoByCategoryId(int page, int size, Long categoryId, String accountEmail);
}