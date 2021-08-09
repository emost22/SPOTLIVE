package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.request.video.VideoUpdateByIdPatchReq;
import com.ssafy.spotlive.api.response.user.UserRes;
import com.ssafy.spotlive.api.response.video.VideoFindAllByUserIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoFindByIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoInsertPostRes;
import com.ssafy.spotlive.api.response.video.VideoOpenViduSessionGetRes;
import com.ssafy.spotlive.api.service.AuthService;
import com.ssafy.spotlive.api.service.UserService;
import com.ssafy.spotlive.api.service.UserVideoService;
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
    @Autowired
    UserVideoService userVideoService;

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
            userVideoService.joinUserVideo(userRes.getAccountEmail(), videoInsertPostRes.getVideoId());
            return new ResponseEntity<>(videoInsertPostRes, HttpStatus.CREATED);
        } else if(vaildTokenStatusValue == 401) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/openvidu/session")
    @ApiOperation(value = "Openvidu를 위한 세션을 생성", notes = "openvidu를 위한 세션을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청 성공"),
            @ApiResponse(code = 400, message = "올바르지 않은 접근"),
            @ApiResponse(code = 401, message = "올바르지 않은 Token이거나, 만료된 Token, 재발급 요청이 필요"),
            @ApiResponse(code = 500, message = "서버 오류 발생"),
    })
    public ResponseEntity<String> createSessionIdForOpenVidu(
            @ApiIgnore @RequestHeader("Authorization") String accessToken) {
        /**
         * @Method Name : createSessionAndMakeTokenForOpenVidu
         * @작성자 : 김민권
         * @Method 설명 : openvidu를 위한 세센을 생성한다.
         */
        int vaildTokenStatusValue = authService.isValidToken(accessToken);

        if(vaildTokenStatusValue == 200) {
            String sessionId = videoService.createSession();
            return new ResponseEntity<>(sessionId, HttpStatus.OK);
        } else if(vaildTokenStatusValue == 401) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/openvidu/token/{sessionId}")
    @ApiOperation(value = "Openvidu를 위한 토큰을 생성", notes = "openvidu를 위한 토큰을 생성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요청 성공"),
            @ApiResponse(code = 400, message = "올바르지 않은 접근"),
            @ApiResponse(code = 401, message = "올바르지 않은 Token이거나, 만료된 Token, 재발급 요청이 필요"),
            @ApiResponse(code = 500, message = "서버 오류 발생"),
    })
    public ResponseEntity<VideoOpenViduSessionGetRes> createTokenForOpenVidu(
            @ApiIgnore @RequestHeader("Authorization") String accessToken,
            @PathVariable("sessionId") String sessionId) {
        /**
         * @Method Name : createSessionAndMakeTokenForOpenVidu
         * @작성자 : 김민권
         * @Method 설명 : openvidu를 위한 토큰을 생성한다.
         */
        int vaildTokenStatusValue = authService.isValidToken(accessToken);

        if(vaildTokenStatusValue == 200) {
            VideoOpenViduSessionGetRes videoOpenViduSessionGetRes = videoService.createToken(sessionId);
            return new ResponseEntity<>(videoOpenViduSessionGetRes, HttpStatus.OK);
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
            @RequestPart(value = "thumbnailImage", required = false) MultipartFile thumbnailImage,
            @RequestPart(value = "showInfoInsertPostReq", required = false) VideoUpdateByIdPatchReq videoUpdateByIdPatchReq) {
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
}
