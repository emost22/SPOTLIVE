package com.ssafy.spotlive.api.response.video;

import com.ssafy.spotlive.api.response.category.CategoryRes;
import com.ssafy.spotlive.api.response.showInfo.ShowInfoRes;
import com.ssafy.spotlive.db.entity.Video;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @FileName : VideoRes
 * @작성자 : 권영린
 * @Class 설명 : 유저가 저장한 영상정보
 */
@Getter
@Setter
@Builder
public class VideoRes {
    long videoId;
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
    CategoryRes categoryRes;

    public static VideoRes of(Video video) {
        if(video.getShowInfo() != null) {
            return VideoRes.builder()
                    .videoId(video.getVideoId())
                    .videoTitle(video.getVideoTitle())
                    .videoDescription(video.getVideoDescription())
                    .mode(video.getMode())
                    .startTime(video.getStartTime())
                    .endTime(video.getEndTime())
                    .thumbnailUrl(video.getThumbnailUrl())
                    .videoUrl(video.getVideoUrl())
                    .isLive(video.getIsLive())
                    .hit(video.getHit())
                    .sessionId(video.getSessionId())
                    .showInfoRes(ShowInfoRes.of(video.getShowInfo()))
                    .categoryRes(CategoryRes.of(video.getCategory()))
                    .build();
        } else {
            return VideoRes.builder()
                    .videoId(video.getVideoId())
                    .videoTitle(video.getVideoTitle())
                    .videoDescription(video.getVideoDescription())
                    .mode(video.getMode())
                    .startTime(video.getStartTime())
                    .endTime(video.getEndTime())
                    .thumbnailUrl(video.getThumbnailUrl())
                    .videoUrl(video.getVideoUrl())
                    .isLive(video.getIsLive())
                    .hit(video.getHit())
                    .sessionId(video.getSessionId())
//                    .showInfoRes(ShowInfoRes.of(video.getShowInfo()))
                    .categoryRes(CategoryRes.of(video.getCategory()))
                    .build();
        }
    }
}
