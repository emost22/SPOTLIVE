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

import java.util.ArrayList;
import java.util.List;
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
    static final int MAX = 2147483647;
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
    void findPageVideosByMode(){
        // given
        String insertVideoTitle = "알고리즘 잘하는법";
        String insertVideoDescription = "kmk님이 알려주실겁니다";
        String insertMode = "소통";
        Long insertCategoryId = 2L;
        Long insertShowInfoId = 6L;
        String insertAccountEmail = "emoney96@naver.com";
        String insertSessionId = "123123";
        String insertThumbnailUrl = "solvedac.png";

        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(insertVideoTitle);
        videoInsertPostReq.setVideoDescription(insertVideoDescription);
        videoInsertPostReq.setMode(insertMode);
        videoInsertPostReq.setCategoryId(insertCategoryId);
        videoInsertPostReq.setShowInfoId(insertShowInfoId);
        videoInsertPostReq.setAccountEmail(insertAccountEmail);
        videoInsertPostReq.setSessionId(insertSessionId);

        int page = 0;
        int size = MAX;

        Long categoryId = 2L;
        String mode = "소통";
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Video video = videoRepository.save(videoInsertPostReq.toVideo(insertThumbnailUrl));
        Page<Video> pageVideo = videoRepository.findVideosByMode(pageRequest, mode);
        Page<Video> pageVideo2 = videoRepository.findVideosByModeAndCategory_CategoryId(pageRequest, mode, categoryId);

        // then
        assertThat(pageVideo.getContent().stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
        assertThat(pageVideo2.getContent().stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
    }

    @Test
    void findPageVideosByIsLive(){
        // given
        String insertVideoTitle = "알고리즘 잘하는법";
        String insertVideoDescription = "kmk님이 알려주실겁니다";
        String insertMode = "소통";
        Long insertCategoryId = 2L;
        Long insertShowInfoId = 6L;
        String insertAccountEmail = "emoney96@naver.com";
        String insertSessionId = "123123";
        String insertThumbnailUrl = "solvedac.png";

        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(insertVideoTitle);
        videoInsertPostReq.setVideoDescription(insertVideoDescription);
        videoInsertPostReq.setMode(insertMode);
        videoInsertPostReq.setCategoryId(insertCategoryId);
        videoInsertPostReq.setShowInfoId(insertShowInfoId);
        videoInsertPostReq.setAccountEmail(insertAccountEmail);
        videoInsertPostReq.setSessionId(insertSessionId);

        int page = 0;
        int size = MAX;
        Boolean isLive = true;
        Sort sort = Sort.by(Sort.Direction.DESC, "hit");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Video video = videoRepository.save(videoInsertPostReq.toVideo(insertThumbnailUrl));
        Page<Video> pageVideo = videoRepository.findVideosByIsLive(pageRequest, isLive);

        // then
        assertThat(pageVideo.getContent().stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
    }

    @Test
    void findVideosByIsLiveAndCategory_CategoryId(){
        // given
        String insertVideoTitle = "알고리즘 잘하는법";
        String insertVideoDescription = "kmk님이 알려주실겁니다";
        String insertMode = "소통";
        Long insertCategoryId = 2L;
        Long insertShowInfoId = 6L;
        String insertAccountEmail = "emoney96@naver.com";
        String insertSessionId = "123123";
        String insertThumbnailUrl = "solvedac.png";

        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(insertVideoTitle);
        videoInsertPostReq.setVideoDescription(insertVideoDescription);
        videoInsertPostReq.setMode(insertMode);
        videoInsertPostReq.setCategoryId(insertCategoryId);
        videoInsertPostReq.setShowInfoId(insertShowInfoId);
        videoInsertPostReq.setAccountEmail(insertAccountEmail);
        videoInsertPostReq.setSessionId(insertSessionId);

        int page = 0;
        int size = MAX;
        Boolean isLive = true;
        Long categoryId = 2L;
        Sort sort = Sort.by(Sort.Direction.DESC, "hit");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Video video = videoRepository.save(videoInsertPostReq.toVideo(insertThumbnailUrl));
        Page<Video> pageVideo = videoRepository.findVideosByIsLiveAndCategory_CategoryId(pageRequest, isLive, categoryId);

        // then
        assertThat(pageVideo.getContent().stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
    }

    @Test
    void findPageVideosByUser_AccountEmailIn(){
        // given
        String insertVideoTitle = "알고리즘 잘하는법";
        String insertVideoDescription = "kmk님이 알려주실겁니다";
        String insertMode = "소통";
        Long insertCategoryId = 2L;
        Long insertShowInfoId = 6L;
        String insertAccountEmail = "emoney96@naver.com";
        String insertSessionId = "123123";
        String insertThumbnailUrl = "solvedac.png";

        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(insertVideoTitle);
        videoInsertPostReq.setVideoDescription(insertVideoDescription);
        videoInsertPostReq.setMode(insertMode);
        videoInsertPostReq.setCategoryId(insertCategoryId);
        videoInsertPostReq.setShowInfoId(insertShowInfoId);
        videoInsertPostReq.setAccountEmail(insertAccountEmail);
        videoInsertPostReq.setSessionId(insertSessionId);

        int page = 0;
        int size = MAX;
        List<String> accountEmailList = new ArrayList<>();
        accountEmailList.add("sqk8657@naver.com");
        accountEmailList.add("ahyen@naver.com");
        accountEmailList.add("emoney96@naver.com");
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Video video = videoRepository.save(videoInsertPostReq.toVideo(insertThumbnailUrl));
        Page<Video> pageVideo = videoRepository.findVideosByUser_AccountEmailIn(pageRequest, accountEmailList);

        // then
        assertThat(pageVideo.getContent().stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
    }

    @Test
    void findVideosByCategory_CategoryIdAndUser_AccountEmailIn(){
        // given
        String insertVideoTitle = "알고리즘 잘하는법";
        String insertVideoDescription = "kmk님이 알려주실겁니다";
        String insertMode = "소통";
        Long insertCategoryId = 2L;
        Long insertShowInfoId = 6L;
        String insertAccountEmail = "emoney96@naver.com";
        String insertSessionId = "123123";
        String insertThumbnailUrl = "solvedac.png";

        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(insertVideoTitle);
        videoInsertPostReq.setVideoDescription(insertVideoDescription);
        videoInsertPostReq.setMode(insertMode);
        videoInsertPostReq.setCategoryId(insertCategoryId);
        videoInsertPostReq.setShowInfoId(insertShowInfoId);
        videoInsertPostReq.setAccountEmail(insertAccountEmail);
        videoInsertPostReq.setSessionId(insertSessionId);

        int page = 0;
        int size = MAX;
        List<String> accountEmailList = new ArrayList<>();
        accountEmailList.add("sqk8657@naver.com");
        accountEmailList.add("ahyen@naver.com");
        accountEmailList.add("emoney96@naver.com");
        Long categoryId = 2L;
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Video video = videoRepository.save(videoInsertPostReq.toVideo(insertThumbnailUrl));
        Page<Video> pageVideo = videoRepository.findVideosByCategory_CategoryIdAndUser_AccountEmailIn(pageRequest, categoryId, accountEmailList);

        // then
        assertThat(pageVideo.getContent().stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
    }

    @Test
    void findVideosByMode(){
        // given
        String insertVideoTitle = "알고리즘 잘하는법";
        String insertVideoDescription = "kmk님이 알려주실겁니다";
        String insertMode = "소통";
        Long insertCategoryId = 2L;
        Long insertShowInfoId = 6L;
        String insertAccountEmail = "emoney96@naver.com";
        String insertSessionId = "123123";
        String insertThumbnailUrl = "solvedac.png";

        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(insertVideoTitle);
        videoInsertPostReq.setVideoDescription(insertVideoDescription);
        videoInsertPostReq.setMode(insertMode);
        videoInsertPostReq.setCategoryId(insertCategoryId);
        videoInsertPostReq.setShowInfoId(insertShowInfoId);
        videoInsertPostReq.setAccountEmail(insertAccountEmail);
        videoInsertPostReq.setSessionId(insertSessionId);

        String mode = "소통";

        // when
        Video video = videoRepository.save(videoInsertPostReq.toVideo(insertThumbnailUrl));
        List<Video> videoList = videoRepository.findVideosByMode(mode).orElse(null);

        // then
        assertThat(videoList.stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
    }

    @Test
    void findVideosByIsLive(){
        // given
        String insertVideoTitle = "알고리즘 잘하는법";
        String insertVideoDescription = "kmk님이 알려주실겁니다";
        String insertMode = "소통";
        Long insertCategoryId = 2L;
        Long insertShowInfoId = 6L;
        String insertAccountEmail = "emoney96@naver.com";
        String insertSessionId = "123123";
        String insertThumbnailUrl = "solvedac.png";

        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(insertVideoTitle);
        videoInsertPostReq.setVideoDescription(insertVideoDescription);
        videoInsertPostReq.setMode(insertMode);
        videoInsertPostReq.setCategoryId(insertCategoryId);
        videoInsertPostReq.setShowInfoId(insertShowInfoId);
        videoInsertPostReq.setAccountEmail(insertAccountEmail);
        videoInsertPostReq.setSessionId(insertSessionId);

        Boolean isLive = true;

        // when
        Video video = videoRepository.save(videoInsertPostReq.toVideo(insertThumbnailUrl));
        List<Video> videoList = videoRepository.findVideosByIsLive(isLive).orElse(null);

        // then
        assertThat(videoList.stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
    }

    @Test
    void findVideosByUser_AccountEmailIn(){
        // given
        String insertVideoTitle = "알고리즘 잘하는법";
        String insertVideoDescription = "kmk님이 알려주실겁니다";
        String insertMode = "소통";
        Long insertCategoryId = 2L;
        Long insertShowInfoId = 6L;
        String insertAccountEmail = "emoney96@naver.com";
        String insertSessionId = "123123";
        String insertThumbnailUrl = "solvedac.png";

        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(insertVideoTitle);
        videoInsertPostReq.setVideoDescription(insertVideoDescription);
        videoInsertPostReq.setMode(insertMode);
        videoInsertPostReq.setCategoryId(insertCategoryId);
        videoInsertPostReq.setShowInfoId(insertShowInfoId);
        videoInsertPostReq.setAccountEmail(insertAccountEmail);
        videoInsertPostReq.setSessionId(insertSessionId);

        List<String> accountEmailList = new ArrayList<>();
        accountEmailList.add("emoney96@naver.com");

        // when
        Video video = videoRepository.save(videoInsertPostReq.toVideo(insertThumbnailUrl));
        List<Video> videoList = videoRepository.findVideosByUser_AccountEmailIn(accountEmailList).orElse(null);

        // then
        assertThat(videoList.stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
    }

    @Test
    void findVideosByVideoTitleContainsOrVideoDescriptionContains(){
        // given
        String insertVideoTitle = "알고리즘 잘하는법";
        String insertVideoDescription = "kmk님이 알려주실겁니다";
        String insertMode = "소통";
        Long insertCategoryId = 2L;
        Long insertShowInfoId = 6L;
        String insertAccountEmail = "emoney96@naver.com";
        String insertSessionId = "123123";
        String insertThumbnailUrl = "solvedac.png";

        VideoInsertPostReq videoInsertPostReq = new VideoInsertPostReq();
        videoInsertPostReq.setVideoTitle(insertVideoTitle);
        videoInsertPostReq.setVideoDescription(insertVideoDescription);
        videoInsertPostReq.setMode(insertMode);
        videoInsertPostReq.setCategoryId(insertCategoryId);
        videoInsertPostReq.setShowInfoId(insertShowInfoId);
        videoInsertPostReq.setAccountEmail(insertAccountEmail);
        videoInsertPostReq.setSessionId(insertSessionId);

        int page = 0;
        int size = MAX;
        String videoTitle = "kmk";
        String videoDescription = "kmk";
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Video video = videoRepository.save(videoInsertPostReq.toVideo(insertThumbnailUrl));
        Page<Video> pageVideo = videoRepository.findVideosByVideoTitleContainsOrVideoDescriptionContains(pageRequest, videoTitle, videoDescription);

        // then
        assertThat(pageVideo.getContent().stream().anyMatch(newVideo -> newVideo.getVideoId() == video.getVideoId())).isEqualTo(true);
    }

    @Test
    void findVideosByCategory_CategoryIdAndVideoTitleContainsOrVideoDescriptionContains(){
        // given
        int page = 0;
        int size = MAX;
        Long categoryId = 6L;
        String videoTitle = "1";
        String videoDescription = "1";
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Page<Video> pageVideo = videoRepository.findVideosByCategory_CategoryIdAndVideoTitleContainsOrVideoDescriptionContains(pageRequest, categoryId, videoTitle, videoDescription);

        // then
        assertThat(pageVideo.getTotalElements()).isEqualTo(0);
    }
}
