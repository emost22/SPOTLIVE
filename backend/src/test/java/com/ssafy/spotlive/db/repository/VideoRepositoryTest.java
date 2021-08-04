package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.request.video.VideoUpdateByIdPatchReq;
import com.ssafy.spotlive.api.response.video.VideoInsertPostRes;
import com.ssafy.spotlive.db.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @FileName : VideoRepositoryTest
 * @작성자 : 권영린
 * @Class 설명 : Video 테스트를 위한 Test클래스
 */
@SpringBootTest
@Transactional
public class VideoRepositoryTest {
    @Autowired
    VideoRepository videoRepository;

    @Test
    void 스트리밍시작_영상저장테스트() {

        // 테스트 객체 생성 and 셋팅
        String videoTitle = "뮤지컬 [캣츠]";
        String videoDescription = "캣츠 단돈 5천원!";
        String mode = "공연";
        Long categoryId = 2L;
        Long showInfoId = 6L;
        String accountEmail = "sqk8657@naver.com";
        String sessionId = "12341";
        String thumbnailUrl = "penguin.png";


        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(videoTitle);
        videoInsertPostReq.setVideoDescription(videoDescription);
        videoInsertPostReq.setMode(mode);
        videoInsertPostReq.setCategoryId(categoryId);
        videoInsertPostReq.setShowInfoId(showInfoId);
        videoInsertPostReq.setAccountEmail(accountEmail);
        videoInsertPostReq.setSessionId(sessionId);

        // 위의 객체를 저장
        VideoInsertPostRes videoInsertPostRes = VideoInsertPostRes.of(videoRepository
                .save(videoInsertPostReq.toVideo(thumbnailUrl)));

        // 넣은 값을 꺼내 제대로 들어갔는지 확인
        Optional<Video> videoById = videoRepository.findById(videoInsertPostRes.getVideoId());
        assertThat(videoById.get().getVideoTitle()).isEqualTo(videoTitle);
    }

    @Test
    void 영상수정테스트(){
        //id에 해당하는 비디오 정보를 수정
        Long id = 6L;
        String videoTitle = "뮤지컬 [갯츠]";
        String videoDescription = "캣츠에서 갯츠로";
        Long categoryId = 6L;
        VideoUpdateByIdPatchReq videoUpdateByIdPatchReq = new VideoUpdateByIdPatchReq();
        videoUpdateByIdPatchReq.setCategoryId(categoryId);
        videoUpdateByIdPatchReq.setVideoTitle(videoTitle);
        videoUpdateByIdPatchReq.setVideoDescription(videoDescription);

        // 위의 객체를 수정(저장)
        videoRepository.save(videoUpdateByIdPatchReq.toVideo(id));
        // 저장 된 정보 확인
        Optional<Video> videoById = videoRepository.findById(id);
        assertThat(videoById.get().getVideoTitle()).isEqualTo(videoTitle);
    }

    @Test
    void findVideosByMode(){
        // given
        int page = 0;
        int size = 6;
        Long categoryId = 6L;
        String mode = "소통";
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Page<Video> pageVideo = videoRepository.findVideosByMode(pageRequest, mode);
        Page<Video> pageVideo2 = videoRepository.findVideosByModeAndCategory_CategoryId(pageRequest, mode, categoryId);

        // then
        assertThat(pageVideo.getTotalElements()).isEqualTo(6);
        assertThat(pageVideo2.getTotalElements()).isEqualTo(4);
    }

    @Test
    void findVideosByIsLive(){
        // given
        int page = 0;
        int size = 3;
        Boolean isLive = false;
        Sort sort = Sort.by(Sort.Direction.DESC, "hit");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Page<Video> pageVideo = videoRepository.findVideosByIsLive(pageRequest, isLive);

        // then
        assertThat(pageVideo.getTotalElements()).isEqualTo(5);
    }
}
