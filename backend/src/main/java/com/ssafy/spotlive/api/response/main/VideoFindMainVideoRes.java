package com.ssafy.spotlive.api.response.main;

import com.ssafy.spotlive.db.entity.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @FileName : VideoFindMainVideoRes
 * @작성자 : 강용수
 * @Class 설명 : 메인 화면 내 Video 조회 요청에 대한 VideoFindMainVideoResponseDto 정의
 *              VideoFindAllVideoGetRes에 데이터 전송용
 */
@Getter
@Setter
@Builder
@ToString
public class VideoFindMainVideoRes {
    Long videoId;
    String videoTitle;
    String videoDescription;
    String mode;
    String thumbnailUrl;
    String videoUrl;
    Long videoLength;
    Boolean isLive;
    Long hit;
    Long hitLive;

    VideoFindUserRes user;
    LocalDateTime startTime;
    String categoryName;

    public static VideoFindMainVideoRes of(Video video){
        /**
         * @Method Name : of
         * @작성자 : 강용수
         * @Method 설명 : Video Entity와 videoLength를 VideoFindMainVideoResponseDto로 변환하는 메소드
         */
        Duration duration = null;
        Long videoLength = null;

        if(video.getEndTime() != null){
            duration = Duration.between(video.getEndTime(), video.getStartTime());
            videoLength = duration.getSeconds();
        }

        return VideoFindMainVideoRes.builder()
                .videoId(video.getVideoId())
                .videoTitle(video.getVideoTitle())
                .videoDescription(video.getVideoDescription())
                .mode(video.getMode())
                .thumbnailUrl(video.getThumbnailUrl())
                .videoUrl(video.getVideoUrl())
                .videoLength(videoLength)
                .isLive(video.getIsLive())
                .hit(video.getHit())
                .hitLive(video.getUserVideoList().stream().count())
                .user(VideoFindUserRes.of(video.getUser()))
                .startTime(video.getStartTime())
                .categoryName(video.getCategory().getCategoryName())
                .build();
    }
}