package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.request.showInfo.ShowInfoInsertPostReq;
import com.ssafy.spotlive.api.request.timetable.TimetableInsertPostReq;
import com.ssafy.spotlive.db.entity.ShowInfo;
import com.ssafy.spotlive.db.repository.ShowInfoRepository;
import com.ssafy.spotlive.db.repository.TimetableRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowInfoServiceTest {

    @Mock
    ShowInfoRepository showInfoRepository;

    @Mock
    MultipartFile multipartFile;

    @Mock
    TimetableRepository timetableRepository;

    @InjectMocks
    ShowInfoService showInfoService = new ShowInfoServiceImpl();

    @Test
    void insertShowInfoTest() {
        // given
        ShowInfoInsertPostReq showInfoInsertPostReq = new ShowInfoInsertPostReq();
        TimetableInsertPostReq timetableInsertPostReq1 = new TimetableInsertPostReq();
        TimetableInsertPostReq timetableInsertPostReq2 = new TimetableInsertPostReq();
        TimetableInsertPostReq timetableInsertPostReq3 = new TimetableInsertPostReq();
        showInfoInsertPostReq.setTimetableInsertPostReq(new ArrayList<>());
        showInfoInsertPostReq.getTimetableInsertPostReq().add(timetableInsertPostReq1);
        showInfoInsertPostReq.getTimetableInsertPostReq().add(timetableInsertPostReq2);
        showInfoInsertPostReq.getTimetableInsertPostReq().add(timetableInsertPostReq3);

        // when
        ShowInfo showInfo = new ShowInfo();
        showInfo.setShowInfoId(1L);

        doReturn(showInfo).when(showInfoRepository).save(showInfoInsertPostReq.toShowInfo());
        doReturn(showInfo).when(showInfoRepository).getById(1L);
        doReturn("filename.png").when(multipartFile).getOriginalFilename();

//        when(showInfoRepository.save(showInfoInsertPostReq.toShowInfo())).thenReturn(showInfo);
//        when(showInfoRepository.getById(1L)).thenReturn(showInfo);
//        when(multipartFile.getOriginalFilename()).thenReturn("filename.png");
        showInfoService.insertShowInfo(showInfoInsertPostReq, multipartFile);

        // then
        verify(showInfoRepository).save(showInfoInsertPostReq.toShowInfo());
        verify(showInfoRepository).getById(1L);
        verify(timetableRepository).save(timetableInsertPostReq1.toTimetable(showInfo));
    }

    @Test
    void findShowInfoByIdTest() {
//        ShowInfo showInfo = new ShowInfo();
//        showInfo.setShowInfoId(1L);
//        given(showInfoRepository.findShowInfoByShowInfoId(-1L)).willReturn(Optional.of(showInfo));
//        ShowInfoFindByIdGetRes expected = showInfoService.findShowInfoById(-1L);
//        assertThat(expected).isNotNull();

        long showInfoId = 1L;
        showInfoService.findShowInfoById(showInfoId);
        verify(showInfoRepository).findShowInfoByShowInfoId(showInfoId);
    }

    @Test
    void deleteShowInfoByIdTest() {
        long showInfoId = 1;
        showInfoService.deleteShowInfoById(showInfoId);
        verify(showInfoRepository).deleteShowInfoByShowInfoId(showInfoId);
    }
}