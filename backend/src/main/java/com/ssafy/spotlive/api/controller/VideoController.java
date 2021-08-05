package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.request.video.VideoUpdateByIdPatchReq;
import com.ssafy.spotlive.api.response.user.UserRes;
import com.ssafy.spotlive.api.response.video.VideoFindAllByUserIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoFindByIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoInsertPostRes;
import com.ssafy.spotlive.api.service.AuthService;
import com.ssafy.spotlive.api.service.UserService;
import com.ssafy.spotlive.api.service.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * @FileName : VideoController
 * @작성자 : 권영린
 * @Class 설명 : 영상에 대한 CRUD를 수행하는 컨트롤러
 */
@Api(value = "Video API", tags = {"VideoController"}, description = "영상 컨트롤러")
@RestController
@CrossOrigin(value = {"*"}, maxAge = 6000)
@RequestMapping("/api/video")
public class VideoController {

    @Autowired
    VideoService videoService;
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;

    @PostMapping("/insert")
    @ApiOperation(value = "스트리밍 시작", notes = "스트리밍을 시작하면 영상정보가 DB에 저장된다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "스트리밍 시작 성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<VideoInsertPostRes> insertVideo(
            @ApiIgnore @RequestHeader("Authorization") String accessToken,
            @RequestParam("thumbnailImage") MultipartFile thumbnailImage,
            VideoInsertPostReq videoInsertPostReq) {
        /**
         * @Method Name : insertVideo
         * @작성자 : 권영린
         * @Method 설명 : 비디오를 추가한다.
         */
        int vaildTokenStatusValue = authService.isValidToken(accessToken);

        if(vaildTokenStatusValue == 200) {
            String[] spitToken = accessToken.split(" ");
            UserRes userRes = userService.findUserByAccessToken(spitToken[1]);
            videoInsertPostReq.setAccountEmail(userRes.getAccountEmail());
            VideoInsertPostRes videoInsertPostRes = videoService.insertVideo(videoInsertPostReq, thumbnailImage);
            return new ResponseEntity<>(videoInsertPostRes, HttpStatus.CREATED);
        } else if(vaildTokenStatusValue == 401) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{videoId}")
    @ApiOperation(value = "영상 정보 조회", notes = "videoId로 조회를 하면 해당 영상에 대한 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "조회 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<VideoFindByIdGetRes> findVideoById(
            @ApiIgnore @RequestHeader("Authorization") String accessToken,
            @PathVariable long videoId) {
        /**
         * @Method Name : findVideoById
         * @작성자 : 권영린
         * @Method 설명 : 비디오 정보를 조회한다.
         */
        int vaildTokenStatusValue = authService.isValidToken(accessToken);

        if(vaildTokenStatusValue == 200) {
            String[] spitToken = accessToken.split(" ");
            UserRes userRes = userService.findUserByAccessToken(spitToken[1]);
            VideoFindByIdGetRes videoFindByIdGetRes = videoService.findVideoById(videoId);
            return new ResponseEntity<>(videoFindByIdGetRes, HttpStatus.OK);
        } else if(vaildTokenStatusValue == 401) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{videoId}")
    @ApiOperation(value = "스트리밍 정보 수정", notes = "videoId와 수정정보를 요청받아 해당 영상에 대한 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "수정 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> updateVideoById(
            @ApiIgnore @RequestHeader("Authorization") String accessToken,
            @PathVariable long videoId,
            @RequestParam(name = "thumbnailImage", required = false) MultipartFile thumbnailImage,
            VideoUpdateByIdPatchReq videoUpdateByIdPatchReq) {
        /**
         * @Method Name : updateVideoById
         * @작성자 : 권영린
         * @Method 설명 : 비디오를 수정한다.
         */
        int vaildTokenStatusValue = authService.isValidToken(accessToken);

        if(vaildTokenStatusValue == 200) {
            String[] spitToken = accessToken.split(" ");
            UserRes userRes = userService.findUserByAccessToken(spitToken[1]);
            Boolean isSuccess = videoService.updateVideoById(videoId, thumbnailImage, videoUpdateByIdPatchReq, userRes.getAccountEmail());
            if(isSuccess)
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<String>("없는 영상이거나 남의 영상이거나 아무튼 실패함", HttpStatus.BAD_REQUEST);
        } else if(vaildTokenStatusValue == 401) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/close/{videoId}")
    @ApiOperation(value = "스트리밍 종료", notes = "videoId로 해당 스트리밍의 end_time을 DB에 기록한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "종료 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<?> closeVideoById(
            @ApiIgnore @RequestHeader("Authorization") String accessToken,
            @PathVariable long videoId) {
        /**
         * @Method Name : closeVideoById
         * @작성자 : 권영린
         * @Method 설명 : 비디오를 종료한다.
         */
        int vaildTokenStatusValue = authService.isValidToken(accessToken);

        if(vaildTokenStatusValue == 200) {
            String[] spitToken = accessToken.split(" ");
            UserRes userRes = userService.findUserByAccessToken(spitToken[1]);
            if(videoService.updateVideoEndTimeById(videoId, userRes.getAccountEmail()))
                return new ResponseEntity<>(HttpStatus.OK);
            else
                return new ResponseEntity<String>("이미 종료됐거나 남의 영상이거나 아무튼 실패함.", HttpStatus.BAD_REQUEST);
        } else if(vaildTokenStatusValue == 401) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list/{accountEmail}")
    @ApiOperation(value = "특정 유저 영상 리스트 조회", notes = "user email를 요청받아 해당 유저의 저장된 영상리스트를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "조회 오류"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<List<VideoFindAllByUserIdGetRes>> findVideoListByUserId(
            @ApiIgnore @RequestHeader("Authorization") String accessToken,
            @PathVariable String accountEmail) {
            /**
             * @Method Name : findVideoListByUserId
             * @작성자 : 권영린
             * @Method 설명 : 특정 유저의 내동영상 리스트를 조회한다.
             */
        int vaildTokenStatusValue = authService.isValidToken(accessToken);

        if(vaildTokenStatusValue == 200) {
            String[] spitToken = accessToken.split(" ");
            UserRes userRes = userService.findUserByAccessToken(spitToken[1]);
            List<VideoFindAllByUserIdGetRes> videoFindAllByUserIdGetRes = videoService.findVideoByAccountEmail(accountEmail);
            return new ResponseEntity<>(videoFindAllByUserIdGetRes, HttpStatus.OK);
        } else if(vaildTokenStatusValue == 401) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/delete/{videoId}")
    @ApiOperation(value = "본인의 '내동영상' 영상중 하나 삭제", notes = "videoId를 입력받아 해당 유저의 영상파일 삭제, DB에 videoUrl을 비운다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 400, message = "삭제 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<String> updateVideoUrlByVideoId(
            @ApiIgnore @RequestHeader("Authorization") String accessToken,
            @PathVariable long videoId) {
        /**
         * @Method Name : findVideoListByUserId
         * @작성자 : 권영린
         * @Method 설명 : 특정 유저의 내동영상 리스트를 조회한다.
         */
        int vaildTokenStatusValue = authService.isValidToken(accessToken);

        if(vaildTokenStatusValue == 200) {
            String[] spitToken = accessToken.split(" ");
            UserRes userRes = userService.findUserByAccessToken(spitToken[1]);
            if(videoService.updateVideoUrlByVideoId(videoId, userRes.getAccountEmail())) {
                return new ResponseEntity<>(HttpStatus.OK);
            }else{
                return new ResponseEntity<String>("비디오가 없거나, 저장된 Url이 없거나, 다른 사람의 영상을 삭제하려 하거나, 암튼 실패", HttpStatus.BAD_REQUEST);
            }
        } else if(vaildTokenStatusValue == 401) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
