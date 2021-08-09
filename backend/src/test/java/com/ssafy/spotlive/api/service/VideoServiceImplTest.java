package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.video.VideoFindAllByUserIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoFindByIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoOpenViduSessionGetRes;
import com.ssafy.spotlive.db.entity.Video;
import com.ssafy.spotlive.db.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//import static org.mockito.Mockito.*;

@SpringBootTest
@Transactional
class VideoServiceImplTest {

    @Autowired
    VideoService videoService;
    @Autowired
    VideoRepository videoRepository;

    @Test
    void 공연영상정보조회테스트(){
        // 비디오 id로 영상정보 조회
        VideoFindByIdGetRes videoById = videoService.findVideoById(17L);
        // 잘 가져오는지 확인
        assertThat(videoById.getVideoTitle()).isEqualTo("심심해요1");
    }

    @Test
    void 소통영상정보조회테스트(){
        // 비디오 id로 영상정보 조회
        VideoFindByIdGetRes videoById = videoService.findVideoById(1L);
        // 잘 가져오는지 확인
        assertThat(videoById.getVideoTitle()).isEqualTo("심심해요1");
    }

    @Test
    void 스트리밍종료테스트(){
        // video id로 종료 테스트
        videoService.updateVideoEndTimeById(1L, "sqk8657@naver.com");
        Optional<Video> videoById = videoRepository.findById(1L);
        assertThat(videoById.get().getEndTime()).isEqualTo(LocalDateTime.now());
    }

    @Test
    void 오픈비두_세션생성테스트() {
        // given

        // when
        String sessionId = videoService.createSession();

        // then
        assertThat(sessionId).contains("session");
    }

    @Test
    void 오픈비두_토큰발급테스트() {
        // given
        String sessionId = videoService.createSession();

        // when
        VideoOpenViduSessionGetRes videoOpenViduSessionGetRes = videoService.createToken(sessionId);

        // then
        assertThat(videoOpenViduSessionGetRes.getToken()).contains("wss://i5a405.p.ssafy.io");
        assertThat(videoOpenViduSessionGetRes.getSessionId()).isEqualTo(sessionId);
    }

    @Test
    void 오픈비두_세션종료테스트() {
        // given
        String sessionId = videoService.createSession();

        // when
        int statusCodeForSuccess = videoService.closeSession(sessionId);
        int statusCodeForFail = videoService.closeSession(sessionId);

        // then
        assertThat(statusCodeForSuccess).isEqualTo(204);
        assertThat(statusCodeForFail).isEqualTo(404);
    }

    void 특정유저영상조회테스트() {
        String accountEmail = "sqk8657@naver.com";
        List<VideoFindAllByUserIdGetRes> videoByAccountEmail = videoService.findVideoByAccountEmail(accountEmail);
        assertThat(videoByAccountEmail.size()).isEqualTo(9);
    }
}