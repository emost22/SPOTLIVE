package com.ssafy.spotlive.api.response.main;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @FileName : VideoGetRes
 * @작성자 : 강용수
 * @Class 설명 : 메인 화면 진입 시 모든 Video 조회 요청에 대한 VideoFindAllGetResponseDto 정의
 *              실제로 데이터가 전송되는 ResponseDto
 */
@Getter
@Setter
@ToString
@Builder
public class VideoFindAllGetRes {
    List<VideoGetRes> adVideoGetResList;
    List<VideoGetRes> talkVideoGetResList;
    List<VideoGetRes> showVideoGetResList;
    List<VideoGetRes> replayVideoGetResList;
    List<VideoGetRes> liveVideoGetResList;
    List<VideoGetRes> followVideoGetResList;
}