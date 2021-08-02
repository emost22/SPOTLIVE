package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.request.showInfo.ShowInfoInsertPostReq;
import com.ssafy.spotlive.api.request.showInfo.ShowInfoUpdatePatchReq;
import com.ssafy.spotlive.api.response.showInfo.ShowInfoFindByIdGetRes;
import com.ssafy.spotlive.common.model.response.BaseResponseBody;
import com.ssafy.spotlive.db.entity.ShowInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @Autowired
//    ShowInfoService showInfoService;

    @PostMapping("/")
    @ApiOperation(value = "공연 정보 등록", notes = "새로운 공연 정보를 등록한다.")
    public ResponseEntity<? extends BaseResponseBody> insertShowInfo(
            //@ApiIgnore Authentication authentication,
            @RequestBody @ApiParam(value = "새로 등록할 회의의 정보", required = true) ShowInfoInsertPostReq showInfoInsertPostReq){
        /**
         * @Method Name : insertShowInfo
         * @작성자 : 금아현
         * @Method 설명 : 새로운 공연 정보를 등록한다.
         */

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "공연 정보 단일 조회", notes = "공연 id로 해당 공연을 조회한다.")
    public ResponseEntity<ShowInfoFindByIdGetRes> findShowInfoById(
            //@ApiIgnore Authentication authentication,
            @PathVariable @ApiParam(value = "조회할 공연 정보의 id", required = true) long id){
        /**
         * @Method Name : findShowInfoById
         * @작성자 : 금아현
         * @Method 설명 : 공연 id로 공연 정보를 조회한다.
         */
        ShowInfo showInfo = new ShowInfo();
        return ResponseEntity.status(200).body(ShowInfoFindByIdGetRes.of(showInfo));
    }


    @PatchMapping("/{id}")
    @ApiOperation(value = "공연 정보 수정", notes = "공연 id로 해당 공연을 수정한다.")
    public ResponseEntity<? extends BaseResponseBody> updateShowInfo(
            //@ApiIgnore Authentication authentication,
            @PathVariable @ApiParam(value = "수정할 공연 정보의 id", required = true) long id,
            @RequestBody @ApiParam(value = "수정할 회의의 정보", required = true) ShowInfoUpdatePatchReq showInfoInsertPReq){
        /**
         * @Method Name : updateShowInfo
         * @작성자 : 금아현
         * @Method 설명 : 공연 id로 해당 공연을 수정한다.
         */
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "공연 정보 삭제", notes = "공연 id로 해당 공연을 삭제한다.")
    public ResponseEntity<? extends BaseResponseBody> deleteShowInfo(
            //@ApiIgnore Authentication authentication,
            @PathVariable @ApiParam(value = "삭제할 공연 정보의 id", required = true) long id){
        /**
         * @Method Name : deleteShowInfo
         * @작성자 : 금아현
         * @Method 설명 : 공연 id로 해당 공연을 삭제한다.
         */
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
    }
}
