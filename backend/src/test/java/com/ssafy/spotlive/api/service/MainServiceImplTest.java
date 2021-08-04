package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.entity.Video;
import com.ssafy.spotlive.db.repository.VideoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainServiceImplTest {
    @Mock
    private VideoRepository videoRepository;

    @Mock
    private Page<Video> pageVideo;

    @InjectMocks
    private MainServiceImpl mainServiceImpl;

    @Test
    void findAllVideoByModeAndCategoryId() {
        // given
        int page = 0;
        int size = 3;
        Long categoryId1 = 6L;
        Long categoryId2 = null;
        Sort sort = Sort.by(Sort.Direction.DESC, "videoId");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        String mode = "소통";

        // when
        when(videoRepository.findVideosByMode(pageRequest, mode)).thenReturn(pageVideo);
        when(videoRepository.findVideosByModeAndCategory_CategoryId(pageRequest, mode, categoryId1)).thenReturn(pageVideo);
        mainServiceImpl.findAllVideoByModeAndCategoryId(page, size, categoryId1, mode);
        mainServiceImpl.findAllVideoByModeAndCategoryId(page, size, categoryId2, mode);

        // then
        verify(videoRepository).findVideosByMode(pageRequest, mode);
        verify(videoRepository).findVideosByModeAndCategory_CategoryId(pageRequest, mode, categoryId1);
    }

    @Test
    void findAllReplayVideoByIsLiveAndCategoryId() {
        // given
        int page = 0;
        int size = 3;
        Long categoryId1 = 6L;
        Long categoryId2 = null;
        Boolean isLive = false;
        Sort sort = Sort.by(Sort.Direction.DESC, "hit");
        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // when
        when(videoRepository.findVideosByIsLive(pageRequest, isLive)).thenReturn(pageVideo);
        when(videoRepository.findVideosByIsLiveAndCategory_CategoryId(pageRequest, isLive, categoryId1)).thenReturn(pageVideo);
        mainServiceImpl.findAllReplayVideoByIsLiveAndCategoryId(page, size, categoryId1);
        mainServiceImpl.findAllReplayVideoByIsLiveAndCategoryId(page, size, categoryId2);

        // then
        verify(videoRepository).findVideosByIsLive(pageRequest, isLive);
        verify(videoRepository).findVideosByIsLiveAndCategory_CategoryId(pageRequest, isLive, categoryId1);
    }

    @Test
    void findAllLiveVideoByIsLiveAndCategoryId() {
        // given
        int page = 0;
        int size = 3;
        Long categoryId1 = 6L;
        Long categoryId2 = null;
        Boolean isLive = true;
        PageRequest pageRequest = PageRequest.of(page, size);

        // when
        when(videoRepository.findVideosByIsLive(pageRequest, isLive)).thenReturn(pageVideo);
        when(videoRepository.findVideosByIsLiveAndCategory_CategoryId(pageRequest, isLive, categoryId1)).thenReturn(pageVideo);
        mainServiceImpl.findAllLiveVideoByIsLiveAndCategoryId(page, size, categoryId1);
        mainServiceImpl.findAllLiveVideoByIsLiveAndCategoryId(page, size, categoryId2);

        // then
        verify(videoRepository).findVideosByIsLive(pageRequest, isLive);
        verify(videoRepository).findVideosByIsLiveAndCategory_CategoryId(pageRequest, isLive, categoryId1);
    }
}