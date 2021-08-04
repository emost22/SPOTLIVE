package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.repository.ShowInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ShowInfoServiceTest {

    @Mock
    ShowInfoRepository showInfoRepository;

    @InjectMocks
    ShowInfoService showInfoService = new ShowInfoServiceImpl();

    @Test
    void insertShowInfo() {

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