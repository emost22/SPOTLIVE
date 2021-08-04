package com.ssafy.spotlive.api.response.main;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @FileName : VideoFindByModeGetRes
 * @작성자 : 강용수
 * @Class 설명 : 메인 화면 내 Video 조회 요청에 대한 VideoFindByModeGetResponseDto 정의
 *              실제로 데이터가 전송되는 ResponseDto
 */
@Getter
@Setter
@ToString
@Builder
public class VideoFindByModeGetRes {
    List<VideoFindMainVideoRes> videoList;
    boolean empty; // 데이터가 없는지
    boolean first; // 첫 페이지인지
    boolean last; // 마지막 페이지인지
    int number; // 현재 페이지 번호 (1페이지 -> 0으로 표시됨)
    int numberOfElements; // 요청 페이지에서 조회 된 데이터의 갯수
    Pageable pageable;
    int size; // 페이지 당 출력 갯수
    Sort sort;
    long totalElements; // 조회된 데이터의 전체 갯수
    int totalPages; // 전체 페이지의 갯수 (ex. 조회된 데이터가 10개, 페이지 당 출력 갯수가 3개 -> totalPages = 4)
}