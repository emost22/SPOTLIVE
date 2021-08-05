package com.ssafy.spotlive.api.service;

/**
 * @FileName : FollowService
 * @작성자 : 권영린
 * @Class 설명 : 팔로우 관련 기능을 위한 Service 정의.
 */
public interface FollowService {
    void insertFollowByAccountEmail(String artistEmail, String fanEmail);
}
