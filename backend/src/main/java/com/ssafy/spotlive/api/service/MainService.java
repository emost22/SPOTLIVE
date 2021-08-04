package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.main.VideoFindByModeGetRes;

/**
 * @FileName : MainService
 * @작성자 : 강용수
 * @Class 설명 : 메인화면 데이터 조회를 위한 Service 정의.
 */
public interface MainService {
    VideoFindByModeGetRes findAllVideoByModeAndCategoryId(int page, int size, Long categoryId, String mode);
    VideoFindByModeGetRes findAllVideoByIsLiveAndCategoryId(int page, int size, Long categoryId);
}