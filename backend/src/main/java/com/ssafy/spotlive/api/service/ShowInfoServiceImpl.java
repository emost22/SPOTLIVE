package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.showInfo.ShowInfoInsertPostReq;
import com.ssafy.spotlive.api.request.timetable.TimetableInsertPostReq;
import com.ssafy.spotlive.api.response.showInfo.ShowInfoFindByIdGetRes;
import com.ssafy.spotlive.api.response.showInfo.ShowInfoRes;
import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.entity.Timetable;
import com.ssafy.spotlive.db.repository.ShowInfoRepository;
import com.ssafy.spotlive.db.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @FileName : ShowInfoServiceImpl
 * @작성자 : 금아현
 * @Class 설명 : 공연 정보 관련 비즈니스 로직처리를 위한 서비스 구현 정의
 */
@Service
public class ShowInfoServiceImpl implements ShowInfoService{

    @Autowired
    ShowInfoRepository showInfoRepository;

    @Autowired
    TimetableRepository timetableRepository;

    @Override
    public ShowInfo insertShowInfo(ShowInfoInsertPostReq showInfoInsertPostReq, MultipartFile posterImage) {
        /**
         * @Method Name : insertShowInfo
         * @작성자 : 금아현
         * @Method 설명 : 공연정보 추가
         */
        try {
            String separator = File.separator;
            String today = new SimpleDateFormat("yyMMdd").format(new Date());

            File file = new File("");
            String rootPath = file.getAbsolutePath().split("backend")[0];
            String savePath = rootPath + "frontend" + separator + "src" + separator + "assets" + separator + "thumbnails" + separator + today;
            if (!new File(savePath).exists()) {
                try{
                    new File(savePath).mkdirs();
                } catch(Exception e){
                    e.getStackTrace();
                }
            }
            String origFilename = posterImage.getOriginalFilename();
            String saveFileName = UUID.randomUUID().toString() + origFilename.substring(origFilename.lastIndexOf('.'));
            String filePath = savePath + separator + saveFileName;
            posterImage.transferTo(new File(filePath));
            showInfoInsertPostReq.setPosterUrl(filePath);
        } catch(Exception e) {
            e.printStackTrace();
        }
        Long id = showInfoRepository.save(showInfoInsertPostReq.toShowInfo()).getShowInfoId();
        ShowInfo showInfo = showInfoRepository.getById(id);
        showInfoInsertPostReq.getTimetableInsertPostReq().forEach(timetableInsertPostReq -> timetableRepository.save(timetableInsertPostReq.toTimetable(showInfo)));
        return null;
    }

    @Override
    public ShowInfoFindByIdGetRes findShowInfoById(long id) {
        /**
         * @Method Name : findShowInfoById
         * @작성자 : 금아현
         * @Method 설명 : 공연 정보를 ID로 조회
         */
        Optional<ShowInfo> optionalShowInfo = showInfoRepository.findShowInfoByShowInfoId(id);
        return optionalShowInfo.map(ShowInfoFindByIdGetRes::of).orElse(null);
    }

    @Override
    public Long deleteShowInfoById(long id) {
        /**
         * @Method Name : deleteShowInfoById
         * @작성자 : 금아현
         * @Method 설명 : 공연정보를 id로 삭제
         */
        return showInfoRepository.deleteShowInfoByShowInfoId(id);
    }
}