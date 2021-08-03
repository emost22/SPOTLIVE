package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.response.video.VideoFindByIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoInsertPostRes;
import com.ssafy.spotlive.db.entity.Video;
import com.ssafy.spotlive.db.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @FileName : VideoService
 * @작성자 : 권영린
 * @Class 설명 : Video관련 기능을 위한 ServiceImpl 정의.
 */
@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    VideoRepository videoRepository;

    public VideoInsertPostRes insertVideo(VideoInsertPostReq videoInsertPostReq, MultipartFile thumbnailImage){
        /**
         * @Method Name : insertVideo
         * @작성자 : 권영린
         * @Method 설명 : 영상 시작시 썸네일을 비디오객체를 추가
         */
        String saveFileName = null;
        try {
            String separ = File.separator;
            String today = new SimpleDateFormat("yyMMdd").format(new Date());

            /* 절대경로를 가져오기 위한 file객체 생성 */
            File file = new File("");
            String rootPath = file.getAbsolutePath().split("backend")[0];
            /* 저장 경로 설정 후 없으면 생성 */

            String savePath = rootPath + "frontend" + separ + "src" + separ + "assets" + separ + "thumbnails";
            if (!new File(savePath).exists()) {
                try {
                    new File(savePath).mkdirs();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }

            /* 파일 이름을 중복없이 만들어 생성. */
            String origFilename = thumbnailImage.getOriginalFilename();
            saveFileName = UUID.randomUUID().toString() + origFilename.substring(origFilename.lastIndexOf('.')); // 이상함.. 이거 했는데 왜 확장자 포함해서 파일이름이 저장될까
            /* 최종 이미지파일의 경로(Thumbnail) 생성 후 conferenceInsertPostReq에 셋팅*/
            String filePath = savePath + separ + saveFileName;
            thumbnailImage.transferTo(new File(filePath));
        } catch(Exception e) {
            e.printStackTrace();
        }
        return VideoInsertPostRes.of(videoRepository.save(videoInsertPostReq.toVideo(saveFileName)));
    }

    public VideoFindByIdGetRes findVideoById(Long id) {
        /**
         * @Method Name : findVideoById
         * @작성자 : 권영린
         * @Method 설명 : 영상 id로 영상을 조회
         */
        return VideoFindByIdGetRes.of(videoRepository.findById(id).get());
    }

}
