package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.response.video.VideoFindByIdGetRes;
import com.ssafy.spotlive.db.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

//import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

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
        VideoFindByIdGetRes videoById = videoService.findVideoById(7L);
        // 잘 가져오는지 확인
        assertThat(videoById.getVideoTitle()).isEqualTo("뮤지컬 [캣츠]");
    }

    @Test
    void 소통영상정보조회테스트(){
        // 비디오 id로 영상정보 조회
        VideoFindByIdGetRes videoById = videoService.findVideoById(1L);
        // 잘 가져오는지 확인
        assertThat(videoById.getVideoTitle()).isEqualTo("심심해요1");
    }

}