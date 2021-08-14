package com.ssafy.spotlive.api.service;

import com.amazonaws.services.s3.AmazonS3Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@Transactional
@ExtendWith(MockitoExtension.class)
class FileUploadServiceTest {

    @Mock
    AmazonS3Client amazonS3Client;

    @InjectMocks
    FileUploadService fileUploadService;

    @Test
    void uploadTest() throws Exception {
        //given
        String fileName = "test.jpg";
        final byte[] content = "hello world".getBytes();
        MultipartFile multipartFile = new MockMultipartFile("content", fileName, "multipart/mixed", content);
        //when
        String url = fileUploadService.upload(multipartFile);
        //then
        assertThat(url).contains("jpg");
    }

}