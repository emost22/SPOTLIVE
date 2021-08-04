package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.showInfo.ShowInfoInsertPostReq;
import com.ssafy.spotlive.api.response.showInfo.ShowInfoFindByIdGetRes;
import com.ssafy.spotlive.db.entity.ShowInfo;
import com.sun.org.apache.xpath.internal.operations.Mult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @FileName : ShowInfoService
 * @작성자 : 금아현
 * @Class 설명 : 공연 정보 관련 비즈니스 로직처리를 위한 서비스 인터페이스 정의
 */
public interface ShowInfoService {
    ShowInfo insertShowInfo(ShowInfoInsertPostReq showInfoInsertPostReq, MultipartFile posterImage);
    ShowInfoFindByIdGetRes findShowInfoById(long id);
    Long deleteShowInfoById(long id);
}
