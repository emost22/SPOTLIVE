package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.showInfo.ShowInfoInsertPostReq;
import com.ssafy.spotlive.api.request.showInfo.ShowInfoUpdatePatchReq;
import com.ssafy.spotlive.api.response.showInfo.ShowInfoFindByIdGetRes;
import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.repository.ShowInfoRepository;
import com.ssafy.spotlive.db.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @FileName : ShowInfoServiceImpl
 * @작성자 : 금아현
 * @Class 설명 : 공연 정보 관련 비즈니스 로직처리를 위한 서비스 구현 정의
 */
@Service
public class ShowInfoServiceImpl implements ShowInfoService {

    @Autowired
    ShowInfoRepository showInfoRepository;

    @Autowired
    TimetableRepository timetableRepository;

    @Override
    public void insertShowInfo(ShowInfoInsertPostReq showInfoInsertPostReq, MultipartFile posterImage) {
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
                try {
                    new File(savePath).mkdirs();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            String origFilename = posterImage.getOriginalFilename();
            String saveFileName = UUID.randomUUID().toString() + origFilename.substring(origFilename.lastIndexOf('.'));
            String filePath = savePath + separator + saveFileName;
            posterImage.transferTo(new File(filePath));
            showInfoInsertPostReq.setPosterUrl(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long id = showInfoRepository.save(showInfoInsertPostReq.toShowInfo()).getShowInfoId();
        ShowInfo showInfo = showInfoRepository.getById(id);
        showInfoInsertPostReq.getTimetableInsertPostReq().forEach(timetableInsertPostReq -> timetableRepository.save(timetableInsertPostReq.toTimetable(showInfo)));
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

    @Override
    public Boolean updateShowInfo(long id, ShowInfoUpdatePatchReq showInfoUpdatePatchReq, MultipartFile posterImage) {
        /**
         * @Method Name : updateShowInfo
         * @작성자 : 금아현
         * @Method 설명 : 공연정보를 id로 수정
         */
        Optional<ShowInfo> optionalShowInfo = showInfoRepository.findShowInfoByShowInfoId(id);
        ShowInfo showInfo = null;
        if (optionalShowInfo.isPresent()) showInfo = optionalShowInfo.get();
        else return Boolean.FALSE;

        if (!posterImage.isEmpty()) {
            String posterUrl = showInfo.getPosterUrl();
            File file = new File(posterUrl);
            file.delete();
            try {
                posterImage.transferTo(new File(posterUrl));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (showInfoUpdatePatchReq.getShowInfoTitle() != null)
            showInfo.setShowInfoTitle(showInfoUpdatePatchReq.getShowInfoTitle());
        if (showInfoUpdatePatchReq.getShowInfoDescription() != null)
            showInfo.setShowInfoDescription(showInfoUpdatePatchReq.getShowInfoDescription());
        if (showInfoUpdatePatchReq.getPrice() != null)
            showInfo.setPrice(showInfoUpdatePatchReq.getPrice());
        if (showInfoUpdatePatchReq.getRunningTime() != 0)
            showInfo.setRunningTime(showInfoUpdatePatchReq.getRunningTime());

        if (showInfoUpdatePatchReq.getTimetableInsertPostReq() != null) {
            ShowInfo finalShowInfo = showInfo;
            timetableRepository.deleteAllByShowInfo_ShowInfoId(id);
            showInfoUpdatePatchReq.getTimetableInsertPostReq().forEach(timetableInsertPostReq -> timetableRepository.save(timetableInsertPostReq.toTimetable(finalShowInfo)));
        }
        return Boolean.TRUE;
    }
}