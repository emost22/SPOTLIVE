package com.ssafy.spotlive.api.request.timetable;

import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.entity.Timetable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @FileName : TimetableInsertPostReq
 * @작성자 : 금아현
 * @Class 설명 : 공연 정보 등록 API 요청에 필요한 리퀘스트 바디 정의
 */
@Getter
@Setter
@ToString
@ApiModel("TimetableInsertPostReq")
public class TimetableInsertPostReq {
    /**
     * @Method Name : toTimetable
     * @작성자 : 금아현
     * @Method 설명 : Timetable DTO를 Entity로 변환
     */
    @ApiModelProperty(name = "dateTime", example = "공연 날짜, 시간")
    LocalDateTime dateTime;
    Long showInfoId;

    public Timetable toTimetable(){
        Timetable timetable = new Timetable();
        timetable.setDateTime(this.dateTime);

        ShowInfo showInfo  = new ShowInfo();
        showInfo.setShowInfoId(this.showInfoId);

        timetable.setShowInfo(showInfo);
        return timetable;
    }

}
