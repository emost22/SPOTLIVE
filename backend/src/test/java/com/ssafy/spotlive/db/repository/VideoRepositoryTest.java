package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Category;
import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.entity.User;
import com.ssafy.spotlive.db.entity.Video;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @FileName : VideoRepositoryTest
 * @작성자 : 권영린
 * @Class 설명 : Video 테스트를 위한 Test클래스
 */
@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VideoRepositoryTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    VideoRepository videoRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ShowInfoRepository showInfoRepository;

    static String testAccountEmail1 = "test_account_1@gmail.com";
    static User user = new User();
    static Category category = new Category();
    static ShowInfo showInfo = new ShowInfo();
    static Video video1 = new Video();
    static Video video2 = new Video();

    @BeforeAll
    void initTestData() {
        //첫번 째 유저 추가
        user.setAccountEmail(testAccountEmail1);
        user.setGender("테스트MAN");
        user.setAgeRange("테스트10~19");
        user.setProfileDescription("테스트프로필");
        user.setUserName("테스트이름");
        user.setPhoneNumber("테스트전화");
        user.setProfileImageUrl("테스트URL");

        category.setCategoryName("카테고리");

        showInfo.setShowInfoTitle("테스트공연제목");
        showInfo.setShowInfoDescription("테스트공연내용");
        showInfo.setUser(user);
        showInfo.setPosterUrl("테스트포스터URL");
        showInfo.setPrice(4000L);
        showInfo.setRunningTime(300);

        video1.setVideoTitle("테스트비디오1");
        video1.setVideoDescription("테스트내용1");
        video1.setShowInfo(showInfo);
        video1.setThumbnailUrl("테스트썸네일1");
        video1.setMode("테스트소통1");
        video1.setCategory(category);
        video1.setUser(user);

        video2.setVideoTitle("테스트비디오2");
        video2.setVideoDescription("테스트내용2");
        video2.setShowInfo(showInfo);
        video2.setThumbnailUrl("테스트썸네일2");
        video2.setMode("테스트소통2");
        video2.setCategory(category);
        video2.setUser(user);
    }

    @Test
    void findPageVideosByMode(){
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
    void findPageVideosByIsLive(){
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

    @Test
    void findVideosByIsLiveAndCategory_CategoryId(){
        // given
        int page = 0;
        int size = 3;
        Boolean isLive = false;
        Long categoryId = 6L;
        Sort sort = Sort.by(Sort.Direction.DESC, "hit");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Page<Video> pageVideo = videoRepository.findVideosByIsLiveAndCategory_CategoryId(pageRequest, isLive, categoryId);

        // then
        assertThat(pageVideo.getTotalElements()).isEqualTo(4);
    }

    @Test
    void findPageVideosByUser_AccountEmailIn(){
        // given
        int page = 0;
        int size = 3;
        List<String> accountEmailList = new ArrayList<>();
        accountEmailList.add("sqk8657@naver.com");
        accountEmailList.add("ahyen@naver.com");
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Page<Video> pageVideo = videoRepository.findVideosByUser_AccountEmailIn(pageRequest, accountEmailList);

        // then
        assertThat(pageVideo.getTotalElements()).isEqualTo(11);
    }

    @Test
    void findVideosByCategory_CategoryIdAndUser_AccountEmailIn(){
        // given
        int page = 0;
        int size = 3;
        List<String> accountEmailList = new ArrayList<>();
        accountEmailList.add("sqk8657@naver.com");
        accountEmailList.add("ahyen@naver.com");
        Long categoryId = 6L;
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Page<Video> pageVideo = videoRepository.findVideosByCategory_CategoryIdAndUser_AccountEmailIn(pageRequest, categoryId, accountEmailList);

        // then
        assertThat(pageVideo.getTotalElements()).isEqualTo(4);
    }

    @Test
    void findVideosByUserAccountEmail() {
        // given
        userRepository.save(user);
        categoryRepository.save(category);
        showInfoRepository.save(showInfo);
        videoRepository.save(video1);
        videoRepository.save(video2);

        //when
        List<Video> videosByUserAccountEmail = videoRepository.findVideosByUserAccountEmail(testAccountEmail1);

        //then
        boolean isPresentTest2 = videosByUserAccountEmail.stream().anyMatch(video -> video.getThumbnailUrl().contains("테스트썸네일2"));
        boolean isPresentTest3 = videosByUserAccountEmail.stream().anyMatch(video -> video.getThumbnailUrl().contains("테스트썸네일3"));
        assertThat(isPresentTest2).isEqualTo(true);
        assertThat(isPresentTest3).isEqualTo(false);
    }

    @Test
    void findVideosByMode(){
        // given
        String mode = "소통";

        // when
        List<Video> videoList = videoRepository.findVideosByMode(mode).orElse(null);

        // then
        assertThat(videoList.size()).isEqualTo(6);
    }

    @Test
    void findVideosByIsLive(){
        // given
        Boolean isLive = false;

        // when
        List<Video> videoList = videoRepository.findVideosByIsLive(isLive).orElse(null);

        // then
        assertThat(videoList.size()).isEqualTo(5);
    }

    @Test
    void findVideosByUser_AccountEmailIn(){
        // given
        List<String> accountEmailList = new ArrayList<>();
        accountEmailList.add("sqk8657@naver.com");
        accountEmailList.add("ahyen@naver.com");

        // when
        List<Video> videoList = videoRepository.findVideosByUser_AccountEmailIn(accountEmailList).orElse(null);

        // then
        assertThat(videoList.size()).isEqualTo(10);
    }

    @Test
    void findVideosByVideoTitleContainsOrVideoDescriptionContains(){
        // given
        int page = 0;
        int size = 3;
        String videoTitle = "1";
        String videoDescription = "1";
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        Page<Video> pageVideo = videoRepository.findVideosByVideoTitleContainsOrVideoDescriptionContains(pageRequest, videoTitle, videoDescription);

        // then
        assertThat(pageVideo.getTotalElements()).isEqualTo(2);
    }

    @Test
    void findVideosByCategory_CategoryIdAndVideoTitleContainsOrVideoDescriptionContains(){
        // given
        int page = 0;
        int size = 3;
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
