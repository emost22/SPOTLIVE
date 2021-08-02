package com.ssafy.spotlive.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @FileName : CategoryGetRes
 * @작성자 : 강용수
 * @Class 설명 : 카테고리 조회 API 요청에 대한 CategoryGetResponseDto 정의
 */
@Getter
@Setter
@Builder
@ToString
public class CategoryGetRes {
    Long categoryId;
    String categoryName;
}