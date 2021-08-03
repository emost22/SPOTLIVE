package com.ssafy.spotlive.api.response.showInfo;

import com.ssafy.spotlive.api.response.timetable.TimetableRes;
import com.ssafy.spotlive.db.entity.ShowInfo;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @FileName : ShowInfoFindByIdGetRes
 * @작성자 : 금아현
 * @Class 설명 : 단일 공연 정보 조회 API 요청에 대한 ResponseDto 정의
 */
@Getter
@Setter
@ToString
@Builder
@ApiModel("ShowInfoFindByIdGetRes")
public class ShowInfoFindByIdGetRes {
    Long showInfoId;
    String showInfoTitle;
    String showInfoDescription;
    String posterUrl;
    Long price;
    int runningTime;
    List<TimetableRes> timetables = new ArrayList<>();

    public static ShowInfoFindByIdGetRes of(ShowInfo showInfo){
        /**
         * @Method Name : of
         * @작성자 : 금아현
         * @Method 설명 : ShowInfo Entity를 ResponseDto로 변환하는 메소드
         */
        return ShowInfoFindByIdGetRes.builder()
                .showInfoId(showInfo.getShowInfoId())
                .showInfoTitle(showInfo.getShowInfoTitle())
                .showInfoDescription(showInfo.getShowInfoDescription())
                .posterUrl(showInfo.getPosterUrl())
                .price(showInfo.getPrice())
                .runningTime(showInfo.getRunningTime())
                .timetables(showInfo.getTimetableList().stream().map(timetable -> TimetableRes.of(timetable)).collect(Collectors.toList()))
                .build();
    }

}
