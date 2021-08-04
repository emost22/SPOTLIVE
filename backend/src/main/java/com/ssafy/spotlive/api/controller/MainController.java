package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.response.main.VideoFindByModeGetRes;
import com.ssafy.spotlive.api.response.main.VideoFindMainVideoRes;
import com.ssafy.spotlive.api.service.MainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @FileName : MainController
 * @작성자 : 강용수
 * @Class 설명 : 메인화면 데이터 조회를 수행하는 컨트롤러
 */
@RestController
@RequestMapping("/api/main")
@CrossOrigin(value = {"*"}, maxAge = 6000)
@Api(value = "MainController", tags = "MainController", description = "메인화면 컨트롤러")
public class MainController {
    @Autowired
    MainService mainService;

    @ApiOperation(value = "홍보 Video 조회", notes = "mode(홍보 / 소통 / 공연)와 카테고리 id 기준으로 검색된 홍보 Video를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/ad")
    public ResponseEntity<VideoFindByModeGetRes> findAllAdVideo(
            @RequestParam("size") int size, @RequestParam("page") int page, @RequestParam(name = "categoryId", required = false) Long categoryId){
        /**
         * @Method Name : findAllAdVideo
         * @작성자 : 강용수
         * @Method 설명 : mode(홍보 / 소통 / 공연)와 카테고리 id 기준으로 홍보 Video를 검색하는 메소드
         */
        VideoFindByModeGetRes videoFindByModeGetRes = mainService.findAllVideoByModeAndCategoryId(page, size, categoryId, "홍보");

        List<VideoFindMainVideoRes> videoFindMainVideoResList = videoFindByModeGetRes.getVideoResList();

        if (videoFindMainVideoResList == null || videoFindMainVideoResList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(videoFindByModeGetRes, HttpStatus.OK);
    }

    @ApiOperation(value = "소통 Video 조회", notes = "mode(홍보 / 소통 / 공연)와 카테고리 id 기준으로 검색된 소통 Video를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/talk")
    public ResponseEntity<VideoFindByModeGetRes> findAllTalkVideo(
            @RequestParam("size") int size, @RequestParam("page") int page, @RequestParam(name = "categoryId", required = false) Long categoryId){
        /**
         * @Method Name : findAllTalkVideo
         * @작성자 : 강용수
         * @Method 설명 : mode(홍보 / 소통 / 공연)와 카테고리 id 기준으로 소통 Video를 검색하는 메소드
         */
        VideoFindByModeGetRes videoFindByModeGetRes = mainService.findAllVideoByModeAndCategoryId(page, size, categoryId, "소통");

        List<VideoFindMainVideoRes> videoFindMainVideoResList = videoFindByModeGetRes.getVideoResList();

        if (videoFindMainVideoResList == null || videoFindMainVideoResList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(videoFindByModeGetRes, HttpStatus.OK);
    }

    @ApiOperation(value = "공연 Video 조회", notes = "mode(홍보 / 소통 / 공연)와 카테고리 id 기준으로 검색된 공연 Video를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/show")
    public ResponseEntity<VideoFindByModeGetRes> findAllShowVideo(
            @RequestParam("size") int size, @RequestParam("page") int page, @RequestParam(name = "categoryId", required = false) Long categoryId){
        /**
         * @Method Name : findAllShowVideo
         * @작성자 : 강용수
         * @Method 설명 : mode(홍보 / 소통 / 공연)와 카테고리 id 기준으로 공연 Video를 검색하는 메소드
         */
        VideoFindByModeGetRes videoFindByModeGetRes = mainService.findAllVideoByModeAndCategoryId(page, size, categoryId, "공연");

        List<VideoFindMainVideoRes> videoFindMainVideoResList = videoFindByModeGetRes.getVideoResList();

        if (videoFindMainVideoResList == null || videoFindMainVideoResList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(videoFindByModeGetRes, HttpStatus.OK);
    }
}