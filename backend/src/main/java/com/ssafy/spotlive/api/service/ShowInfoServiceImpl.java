package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.showInfo.ShowInfoInsertPostReq;
import com.ssafy.spotlive.api.request.timetable.TimetableInsertPostReq;
import com.ssafy.spotlive.api.response.showInfo.ShowInfoFindByIdGetRes;
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
    TimetableRepository timetableRepository;

    @Override
    public ShowInfo insertShowInfo(ShowInfoInsertPostReq showInfoInsertPostReq) {
        /**
         * @Method Name : insertShowInfo
         * @작성자 : 금아현
         * @Method 설명 : 공연정보 추가
         */
//        System.out.println("showInfo Timetable: " + showInfoInsertPostReq.getTimetableInsertPostReq().get(0).getDateTime());
//        try {
//            String separator = File.separator;
//            String today = new SimpleDateFormat("yyMMdd").format(new Date());
//
//            File file = new File("");
//            String rootPath = file.getAbsolutePath().split("backend")[0];
//            String savePath = rootPath + "frontend" + separator + "src" + separator + "assets" + separator + "thumbnails" + separator + today;
//            if (!new File(savePath).exists()) {
//                try{
//                    new File(savePath).mkdirs();
//                }
//                catch(Exception e){
//                    e.getStackTrace();
//                }
//            }
//            System.out.println("fsdfsdfsdf: " + showInfoInsertPostReq.getFile().getOriginalFilename());
//            System.out.println("fsdfsdfsffffffdf: " + showInfoInsertPostReq.getFile().getName());
//            String origFilename = showInfoInsertPostReq.getFile().getOriginalFilename();
//            String saveFileName = UUID.randomUUID().toString() + origFilename.substring(origFilename.lastIndexOf('.'));
//            String filePath = savePath + separator + saveFileName;
//            showInfoInsertPostReq.getFile().transferTo(new File(filePath));
//            showInfoInsertPostReq.setPosterUrl(filePath);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        Long id = showInfoRepository.save(showInfoInsertPostReq.toShowInfo()).getShowInfoId();
        System.out.println("fsdf " + id);
//        showInfo.setTimetableList(timetableInsertPostReq.stream().map(TimetableInsertPostReq::toTimetable).collect(Collectors.toList()));

        showInfoInsertPostReq.getTimetableInsertPostReq().forEach(timetableInsertPostReq -> timetableInsertPostReq.setShowInfoId(id));
        System.out.println("showInfoInsertPostReq: " + showInfoInsertPostReq.getTimetableInsertPostReq());
        System.out.println("확인 : " + showInfoInsertPostReq.getTimetableInsertPostReq().get(0).toTimetable());
        System.out.println("확인 : " + showInfoInsertPostReq.getTimetableInsertPostReq().get(0));
        System.out.println("확인 : " + showInfoInsertPostReq.getTimetableInsertPostReq().get(1));
//        showInfoInsertPostReq.getTimetableInsertPostReq().forEach(timetableInsertPostReq -> timetableRepository.save(timetableInsertPostReq));
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
}