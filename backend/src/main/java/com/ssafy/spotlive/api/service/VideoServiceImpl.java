package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.request.video.VideoUpdateByIdPatchReq;
import com.ssafy.spotlive.api.response.video.VideoFindByIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoInsertPostRes;
import com.ssafy.spotlive.db.entity.Category;
import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.entity.Video;
import com.ssafy.spotlive.db.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

/**
 * @FileName : VideoService
 * @작성자 : 권영린
 * @Class 설명 : Video관련 기능을 위한 ServiceImpl 정의.
 */
@Service
@Transactional
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

    public Boolean updateVideoById(Long videoId, MultipartFile thumbnailImage, VideoUpdateByIdPatchReq videoUpdateByIdPatchReq) {
        /* 원래 정보를 꺼내옴 */
        Optional<Video> videoById = videoRepository.findById(videoId);
        /* 정보가 없다면 FALSE */
        if(!videoById.isPresent()){
            return Boolean.FALSE;
        }
        /* 썸네일이 있다면 원래 썸네일 파일을 현재 썸네일 파일로 바꿈 */
        if(thumbnailImage != null){
            String separ = File.separator;
            String originalThumbnailName = videoById.get().getThumbnailUrl();
            String rootPath = new File("").getAbsolutePath().split("backend")[0];
            String originalThumbnailUrl = rootPath + "frontend" + separ + "src" + separ + "assets" + separ + "thumbnails" + separ  + originalThumbnailName;
            File file = new File(originalThumbnailUrl);
            file.delete(); //원래 파일 삭제
            try {
                thumbnailImage.transferTo(new File(originalThumbnailUrl));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Video video = videoById.get();
        /* 원래 정보에 바뀐 정보를 업데이트 */
        if(videoUpdateByIdPatchReq != null) {
            if (videoUpdateByIdPatchReq.getVideoTitle() != null) {
                video.setVideoTitle(videoUpdateByIdPatchReq.getVideoTitle());
            }
            if (videoUpdateByIdPatchReq.getVideoDescription() != null) {
                video.setVideoDescription(videoUpdateByIdPatchReq.getVideoDescription());
            }
            if (videoUpdateByIdPatchReq.getCategoryId() != null) {
                Category category = new Category();
                category.setCategoryId(videoUpdateByIdPatchReq.getCategoryId());
                video.setCategory(category);
            }
            if (videoUpdateByIdPatchReq.getShowInfoId() != null) {
                ShowInfo showInfo = new ShowInfo();
                showInfo.setShowInfoId(videoUpdateByIdPatchReq.getShowInfoId());
                video.setShowInfo(showInfo);
            }
        }
        videoRepository.save(video);
        return Boolean.TRUE;
    }

    public Boolean updateVideoEndTimeById(Long videoId){
        Video video = videoRepository.findById(videoId).get();
        if(video.getEndTime()!=null)
            return Boolean.FALSE;
        video.setEndTime(LocalDateTime.now());
        videoRepository.save(video);
        return Boolean.TRUE;
    }

}
