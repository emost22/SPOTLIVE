package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.request.video.VideoUpdateByIdPatchReq;
import com.ssafy.spotlive.api.response.video.VideoFindByIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoInsertPostRes;
import org.springframework.web.multipart.MultipartFile;

/**
 * @FileName : VideoService
 * @작성자 : 권영린
 * @Class 설명 : Video관련 기능을 위한 Service 정의.
 */
public interface VideoService {
    VideoInsertPostRes insertVideo(VideoInsertPostReq videoInsertPostReq, MultipartFile thumbnailImage);
    VideoFindByIdGetRes findVideoById(Long id);
    Boolean updateVideoById(Long videoId, MultipartFile thumbnailImage, VideoUpdateByIdPatchReq videoUpdateByIdPatchReq);
    Boolean updateVideoEndTimeById(Long videoId);
    String createSession();
    String createToken(String sessionId);
}
