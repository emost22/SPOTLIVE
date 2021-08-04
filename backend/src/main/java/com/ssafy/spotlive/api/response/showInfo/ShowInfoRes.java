package com.ssafy.spotlive.api.response.showInfo;

import com.ssafy.spotlive.db.entity.ShowInfo;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class ShowInfoRes {
    Long showInfoId;
    String showInfoTitle;
    String showInfoDescription;
    String posterUrl;
    int runningTime;
    Long price;


    public static ShowInfoRes of(ShowInfo showInfo){
        return ShowInfoRes.builder()
                .showInfoId(showInfo.getShowInfoId())
                .showInfoTitle(showInfo.getShowInfoTitle())
                .showInfoDescription(showInfo.getShowInfoDescription())
                .posterUrl(showInfo.getPosterUrl())
                .runningTime(showInfo.getRunningTime())
                .price(showInfo.getPrice())
                .build();
    }
}
