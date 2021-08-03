package com.ssafy.spotlive.api.response.main;

import com.ssafy.spotlive.db.entity.Video;
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

    public static VideoFindMainVideoGetRes of(Video video, String videoLength){
        /**
         * @Method Name : of
         * @작성자 : 강용수
         * @Method 설명 : Video Entity와 videoLength를 VideoFindMainVideoGetResponseDto로 변환하는 메소드
         */
        return VideoFindMainVideoGetRes.builder()
                .videoId(video.getVideoId())
                .videoTitle(video.getVideoTitle())
                .videoDescription(video.getVideoDescription())
                .mode(video.getMode())
                .thumbnailUrl(video.getThumbnailUrl())
                .videoUrl(video.getVideoUrl())
                .videoLength(videoLength)
                .isLive(video.getIsLive())
                .hit(video.getHit())
                .user(VideoFindUserRes.of(video.getUser()))
                .startTime(video.getStartTime())
                .categoryName(video.getCategory().getCategoryName())
                .build();
    }
}