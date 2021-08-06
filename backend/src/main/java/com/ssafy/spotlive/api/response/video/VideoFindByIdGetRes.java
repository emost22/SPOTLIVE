package com.ssafy.spotlive.api.response.video;

import com.ssafy.spotlive.api.response.showInfo.ShowInfoRes;
import com.ssafy.spotlive.db.entity.Video;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @FileName : VideoFindByIdGetRes
 * @작성자 : 권영린
 * @Class 설명 : 비디오 아이디로 영상 정보 조회 요청에 응답할 리스폰스 정의.
 */
@Getter
@Setter
@ToString
@Builder
@ApiModel("VideoFindByIdGetRes")
public class VideoFindByIdGetRes {
    String videoTitle;
    String videoDescription;
    String mode;
    LocalDateTime startTime;
    LocalDateTime endTime;
    String thumbnailUrl;
    String videoUrl;
    Boolean isLive;
    Long hit;
    String sessionId;

    ShowInfoRes showInfoRes;
    Long categoryId;
    String accountEmail;
    public static VideoFindByIdGetRes of(Video video){
        /**
         * @Method Name : of
         * @작성자 : 권영린
         * @Method 설명 : Video Entity를 VideoFindByIdGetResDto로 변환하는 메소드
         */
        // 소통의 경우 ShowInfo()이 null이기 때문에 .showInfoId(video.getShowInfo().getShowInfoId())를 빼야함
        if(video.getShowInfo() == null){
            return VideoFindByIdGetRes.builder()
                    .videoTitle(video.getVideoTitle())
                    .videoDescription((video.getVideoDescription()))
                    .mode(video.getMode())
                    .startTime(video.getStartTime())
                    .endTime(video.getEndTime())
                    .thumbnailUrl(video.getThumbnailUrl())
                    .videoUrl(video.getVideoUrl())
                    .isLive(video.getIsLive())
                    .hit(video.getHit())
                    .accountEmail(video.getUser().getAccountEmail())
                    .categoryId(video.getCategory().getCategoryId())
                    .build();
        } else {
            return VideoFindByIdGetRes.builder()
                    .videoTitle(video.getVideoTitle())
                    .videoDescription((video.getVideoDescription()))
                    .mode(video.getMode())
                    .startTime(video.getStartTime())
                    .endTime(video.getEndTime())
                    .thumbnailUrl(video.getThumbnailUrl())
                    .videoUrl(video.getVideoUrl())
                    .isLive(video.getIsLive())
                    .hit(video.getHit())
                    .accountEmail(video.getUser().getAccountEmail())
                    .showInfoRes(ShowInfoRes.of(video.getShowInfo()))
                    .sessionId(video.getSessionId())
                    .categoryId(video.getCategory().getCategoryId())
                    .build();
        }


    }
}
