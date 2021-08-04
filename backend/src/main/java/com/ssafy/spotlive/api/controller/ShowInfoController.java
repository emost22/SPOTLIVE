package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.request.showInfo.ShowInfoInsertPostReq;
import com.ssafy.spotlive.api.request.showInfo.ShowInfoUpdatePatchReq;
import com.ssafy.spotlive.api.response.showInfo.ShowInfoFindByIdGetRes;
import com.ssafy.spotlive.api.service.ShowInfoService;
import com.ssafy.spotlive.db.entity.ShowInfo;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @FileName : ShowInfoController
 * @작성자 : 금아현
 * @Class 설명 : 공연 정보(ShowInfo)에 대한 CRUD를 수행하는 컨트롤러
 */
@Api(value = "공연 정보 API", tags = {"ShowInfo"})
@RestController
@CrossOrigin(value = {"*"}, maxAge = 6000)
@RequestMapping("/api/showinfo")
public class ShowInfoController {

    @Autowired
    ShowInfoService showInfoService;

    @PostMapping("/")
    @ApiOperation(value = "공연 정보 등록", notes = "새로운 공연 정보를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "등록 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Object> insertShowInfo(
            @ApiIgnore Authentication authentication,
            @RequestParam("posterImage") MultipartFile posterImage,
            @ApiParam(value = "새로 등록할 회의의 정보", required = true) ShowInfoInsertPostReq showInfoInsertPostReq
    ){
        /**
         * @Method Name : insertShowInfo
         * @작성자 : 금아현
         * @Method 설명 : 새로운 공연 정보를 등록한다.
         */
        // user 이메일 부분 추가해야함
        ShowInfo showInfo = showInfoService.insertShowInfo(showInfoInsertPostReq, posterImage);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "공연 정보 단일 조회", notes = "공연 id로 해당 공연을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 204, message = "조회할 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    public ResponseEntity<ShowInfoFindByIdGetRes> findShowInfoById(
            //@ApiIgnore Authentication authentication,
            @PathVariable @ApiParam(value = "조회할 공연 정보의 id", required = true) long id){
        /**
         * @Method Name : findShowInfoById
         * @작성자 : 금아현
         * @Method 설명 : 공연 id로 공연 정보를 조회한다.
         */
        ShowInfoFindByIdGetRes showInfoFindByIdGetRes = showInfoService.findShowInfoById(id);
        if(showInfoFindByIdGetRes == null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(showInfoFindByIdGetRes, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    @ApiOperation(value = "공연 정보 수정", notes = "공연 id로 해당 공연을 수정한다.")
    public ResponseEntity<Object> updateShowInfo(
            //@ApiIgnore Authentication authentication,
            @PathVariable @ApiParam(value = "수정할 공연 정보의 id", required = true) long id,
            @RequestBody @ApiParam(value = "수정할 회의의 정보", required = true) ShowInfoUpdatePatchReq showInfoInsertPReq){
        /**
         * @Method Name : updateShowInfo
         * @작성자 : 금아현
         * @Method 설명 : 공연 id로 해당 공연을 수정한다.
         */
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "공연 정보 삭제", notes = "공연 id로 해당 공연을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 204, message = "삭제 성공"),
            @ApiResponse(code = 404, message = "id에 해당하는 데이터가 없음"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    public ResponseEntity<Object> deleteShowInfoById(
            //@ApiIgnore Authentication authentication,
            @PathVariable @ApiParam(value = "삭제할 공연 정보의 id", required = true) long id){
        /**
         * @Method Name : deleteShowInfo
         * @작성자 : 금아현
         * @Method 설명 : 공연 id로 해당 공연을 삭제한다.
         */
        Long exist = showInfoService.deleteShowInfoById(id);
        if(exist == 1) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
