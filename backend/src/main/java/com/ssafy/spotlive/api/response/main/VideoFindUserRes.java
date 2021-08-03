package com.ssafy.spotlive.api.response.main;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @FileName : VideoFindUserRes
 * @작성자 : 강용수
 * @Class 설명 : 메인 화면 내 Video 송출자 정보용 Dto
 */
@Getter
@Setter
@Builder
@ToString
public class VideoFindUserRes {
    String accountEmail;
    String profileNickname;
    String profileImageUrl;
}