package com.ssafy.spotlive.api.response.main;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @FileName : UserFindMainFollowGetRes
 * @작성자 : 강용수
 * @Class 설명 : 메인 화면 사이드바의 내 구독자 채널 리스트 조회 요청에 대한 UserFindMainFollowGetResponseDto 정의
 */
@Getter
@Setter
@Builder
@ToString
public class UserFindMainFollowGetRes {
    String accountEmail;
    String profileNickname;
    String profileImageUrl;
    Boolean isLive;
}