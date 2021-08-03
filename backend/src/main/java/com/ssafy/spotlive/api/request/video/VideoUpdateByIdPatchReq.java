package com.ssafy.spotlive.api.request.video;

import com.ssafy.spotlive.db.entity.Video;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @FileName : VideoUpdateByIdPatchReq
 * @작성자 : 권영린
 * @Class 설명 : 스트리밍 수정 API 요청에 필요한 리퀘스트 바디 정의.
 */
@Getter
@Setter
@ToString
@ApiModel("VideoUpdateByIdPatchReq")
public class VideoUpdateByIdPatchReq {
    @ApiModelProperty(name="videoTitle : 라이브 방송 타이틀", example="옥탑방 고냥쓰 홍보합니당")
    String videoTitle;
    @ApiModelProperty(name="videoDescription : 라이브 방송 설명", example="전세계를 폭풍강타한 화재의 연극 옥탑방 고냥쓰")
    String videoDescription;
    @ApiModelProperty(name="categoryId : 카테고리 ID", example="2")
    Long categoryId;

    public Video toVideo(String thumbnailUrl) {
        /**
         * @Method Name : toVideo
         * @작성자 : 권영린
         * @Method 설명 : VideoUpdateByIdPatchReqDto를 Entity로 변환하는 메소드
         */
        Video video = new Video();
        video.setVideoTitle(this.videoTitle);
        video.setVideoDescription(this.videoDescription);
        video.getCategory().setCategoryId(this.categoryId);

        return video;
    }
}
