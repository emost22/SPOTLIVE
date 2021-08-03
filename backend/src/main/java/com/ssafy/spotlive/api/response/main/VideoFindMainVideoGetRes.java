package com.ssafy.spotlive.api.response.main;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @FileName : VideoFindMainVideoGetRes
 * @작성자 : 강용수
 * @Class 설명 : 메인 화면 내 Video 조회 요청에 대한 VideoFindMainVideoGetResponseDto 정의
 */
@Getter
@Setter
@Builder
@ToString
public class VideoFindMainVideoGetRes {
    Long videoId;
    String videoTitle;
    String videoDescription;
    String mode;
    String thumbnailUrl;
    String videoUrl;
    String videoLength;
    Boolean isLive;
    Long hit;

    VideoFindUserRes user;
    LocalDateTime startTime;
    String categoryName;
}