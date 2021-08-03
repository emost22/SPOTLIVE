package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.request.video.VideoInsertPostReq;
import com.ssafy.spotlive.api.request.video.VideoUpdateByIdPatchReq;
import com.ssafy.spotlive.api.response.video.VideoFindAllByUserIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoFindByIdGetRes;
import com.ssafy.spotlive.api.response.video.VideoInsertPostRes;
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

    @PostMapping("/insert")
    @ApiOperation(value = "스트리밍 시작", notes = "스트리밍을 시작하면 영상정보가 DB에 저장된다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "스트리밍 시작 성공"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<VideoInsertPostRes> insertVideo(
            @RequestParam("thumbnailImage") MultipartFile thumbnailImage,
            VideoInsertPostReq videoInsertPostReq) {
        /**
         * @Method Name : insertVideo
         * @작성자 : 권영린
         * @Method 설명 : 비디오를 추가한다.
         */
        VideoInsertPostRes videoInsertPostRes = videoService.insertVideo(videoInsertPostReq, thumbnailImage);
        return new ResponseEntity<>(videoInsertPostRes, HttpStatus.CREATED);
    }

//    @GetMapping("/{videoId}")
//    @ApiOperation(value = "영상 정보 조회", notes = "videoId로 조회를 하면 해당 영상에 대한 정보를 응답한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 400, message = "조회 오류"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<VideoFindByIdGetRes> findVideoById(
//            /*@ApiIgnore Authentication authentication,*/ @PathVariable long videoId) {
//        /**
//         * @Method Name : insertVideo
//         * @작성자 : 권영린
//         * @Method 설명 : 비디오 정보를 조회한다.
//         */
//        VideoFindByIdGetRes videoFindByIdGetRes = null;
//        return ResponseEntity.status(200).body(videoFindByIdGetRes);
//    }
//
//    @PatchMapping("/{videoId}")
//    @ApiOperation(value = "스트리밍 정보 수정", notes = "videoId와 수정정보를 요청받아 해당 영상에 대한 정보를 응답한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 400, message = "수정 실패"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<?> updateVideoById(
////            @ApiIgnore Authentication authentication,
//            @RequestParam MultipartFile thumbnail, VideoUpdateByIdPatchReq videoUpdateByIdPatchReq) {
//        /**
//         * @Method Name : updateVideoById
//         * @작성자 : 권영린
//         * @Method 설명 : 비디오를 수정한다.
//         */
//        return ResponseEntity.status(200).body(null);
//    }
//
//    @PatchMapping("/close/{videoId}")
//    @ApiOperation(value = "스트리밍 종료", notes = "videoId로 해당 스트리밍의 end_time을 DB에 기록한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 400, message = "종료 실패"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<?> closeVideoById(
//            /*@ApiIgnore Authentication authentication,*/ @PathVariable long videoId) {
//        /**
//         * @Method Name : closeVideoById
//         * @작성자 : 권영린
//         * @Method 설명 : 비디오를 종료한다.
//         */
//        return ResponseEntity.status(200).body(null);
//    }
//
//    @GetMapping("/list/{userId}")
//    @ApiOperation(value = "특정 유저 영상 리스트 조회", notes = "userId(email 아님)를 요청받아 해당 유저의 저장된 영상리스트를 응답한다.")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공"),
//            @ApiResponse(code = 400, message = "조회 오류"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<List<VideoFindAllByUserIdGetRes>> findVideoListByUserId(
//            /*@ApiIgnore Authentication authentication,*/ @PathVariable long userId) {
//            /**
//             * @Method Name : findVideoListByUserId
//             * @작성자 : 권영린
//             * @Method 설명 : 특정 유저의 내동영상 리스트를 조회한다.
//             */
//            List<VideoFindAllByUserIdGetRes> videoFindAllByUserIdGetRes = null;
//            return ResponseEntity.status(200).body(videoFindAllByUserIdGetRes);
//    }
}
