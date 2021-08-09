package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.showInfo.ShowInfoInsertPostReq;
import com.ssafy.spotlive.api.request.timetable.TimetableInsertPostReq;
import com.ssafy.spotlive.api.response.showInfo.ShowInfoFindByIdGetRes;
import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.repository.ShowInfoRepository;
import com.ssafy.spotlive.db.repository.TimetableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowInfoServiceImplTest {

    @Mock
    ShowInfoRepository showInfoRepository;

    @Mock
    FileUploadService fileUploadService;

    @Mock
    MultipartFile multipartFile;

    @Mock
    TimetableRepository timetableRepository;

    @InjectMocks
    ShowInfoService showInfoService = new ShowInfoServiceImpl();

    @Test
    void insertShowInfoTest() throws IOException {
        // given
        TimetableInsertPostReq timetableInsertPostReq1 = new TimetableInsertPostReq();
        TimetableInsertPostReq timetableInsertPostReq2 = new TimetableInsertPostReq();
        TimetableInsertPostReq timetableInsertPostReq3 = new TimetableInsertPostReq();

        ShowInfoInsertPostReq showInfoInsertPostReq = new ShowInfoInsertPostReq();
        showInfoInsertPostReq.setTimetableInsertPostReq(new ArrayList<>());

        showInfoInsertPostReq.getTimetableInsertPostReq().add(timetableInsertPostReq1);
        showInfoInsertPostReq.getTimetableInsertPostReq().add(timetableInsertPostReq2);
        showInfoInsertPostReq.getTimetableInsertPostReq().add(timetableInsertPostReq3);

        // when
        ShowInfo showInfo = new ShowInfo();
        showInfo.setShowInfoId(1L);
        String posterImageUrl = "posterUrl.png";

        doReturn(showInfo).when(showInfoRepository).save(anyObject());
        doReturn(showInfo).when(showInfoRepository).getById(1L);
        doReturn(posterImageUrl).when(fileUploadService).upload(multipartFile);

        showInfoService.insertShowInfo(showInfoInsertPostReq, multipartFile);

        // then
        verify(showInfoRepository).save(anyObject());
        verify(showInfoRepository).getById(1L);
        verify(timetableRepository, times(3)).save(anyObject());
    }

    @Test
    void findShowInfoByIdTest() {
        //given
        ShowInfo showInfo = new ShowInfo();
        showInfo.setShowInfoId(1L);
        given(showInfoRepository.findShowInfoByShowInfoId(-1L)).willReturn(Optional.of(showInfo));
        //when
        ShowInfoFindByIdGetRes expected = showInfoService.findShowInfoById(-1L);
        //then
        assertThat(expected).isNotNull();
    }

    @Test
    void deleteShowInfoByIdTest() {
        //given
        long showInfoId = 1L;
        //when
        showInfoService.deleteShowInfoById(showInfoId);
        //then
        verify(showInfoRepository, times(1)).deleteShowInfoByShowInfoId(showInfoId);
    }


    @Test
    void updateShowInfoById() {
        showInfoService.updateShowInfoById(anyObject(), anyObject(), anyObject());
        verify(showInfoRepository).save(anyObject());
    }

    @Test
    void findShowInfoByUser() {
        //when
        showInfoService.findShowInfoByUser(anyObject());
        //then
        verify(showInfoRepository).findShowInfosByUser_AccountEmail(anyObject());
    }
}