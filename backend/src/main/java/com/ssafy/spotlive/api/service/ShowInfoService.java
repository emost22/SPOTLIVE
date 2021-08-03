package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.showInfo.ShowInfoInsertPostReq;
import com.ssafy.spotlive.db.entity.ShowInfo;
import org.springframework.web.multipart.MultipartFile;

public interface ShowInfoService {
    ShowInfo insertShowInfo(ShowInfoInsertPostReq showInfoInsertPostReq);
}
