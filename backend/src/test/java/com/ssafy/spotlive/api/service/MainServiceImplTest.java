package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.repository.VideoRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MainServiceImplTest {
    @Mock
    private VideoRepository videoRepository;

    @InjectMocks
    private MainServiceImpl mainServiceImpl;
}