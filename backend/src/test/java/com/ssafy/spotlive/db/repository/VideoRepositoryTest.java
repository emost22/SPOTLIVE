package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.response.video.VideoInsertPostRes;
import static org.assertj.core.api.Assertions.*;

import com.ssafy.spotlive.db.entity.Video;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle("뮤지컬 [캣츠]");
        videoInsertPostReq.setVideoDescription("캣츠 단돈 5천원!");
        videoInsertPostReq.setMode("공연");
        videoInsertPostReq.setCategoryId(2L);
        videoInsertPostReq.setShowInfoId(6L);
        videoInsertPostReq.setAccountEmail("sqk8657@naver.com");
        videoInsertPostReq.setSessionId("12341");

        // 위의 객체를 저장
        VideoInsertPostRes videoInsertPostRes = VideoInsertPostRes.of(videoRepository
                .save(videoInsertPostReq.toVideo("penguin.png")));

        // 넣은 값을 꺼내 제대로 들어갔는지 확인
        Optional<Video> videoById = videoRepository.findById(videoInsertPostRes.getVideoId());
        assertThat(videoById.get().getVideoTitle()).isEqualTo("뮤지컬 [캣츠]");
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
}
