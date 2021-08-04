package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.service.AuthService;
import com.ssafy.spotlive.api.service.FollowService;
import com.ssafy.spotlive.api.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @FileName : FollowController
 * @작성자 : 권영린
 * @Class 설명 : 팔로우 관련 기능을 수행하는 컨트롤러
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(value = {"*"}, maxAge = 6000)
@Api(value = "FollowController", tags = "FollowController", description = "팔로우 컨트롤러")
public class FollowController {
    @Autowired
    FollowService followService;
    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;

    @ApiOperation(value = "팔로우", notes = "팔로우 버튼 클릭시 해당유저를 팔로우하는 기능 수행 | 임시로 토큰 말고 그냥 자기 이메일 입력으로 만들어놓음")
    @ApiResponses({
            @ApiResponse(code = 200, message = "팔로우 성공"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @PostMapping("/follow/{artistEmail}")
    public ResponseEntity<?> followArtist(
//            @ApiIgnore @RequestHeader("Authorization") String accessToken,
            @RequestParam String fanEmail,
            @PathVariable String artistEmail){
//        int vaildTokenStatusValue = authService.isValidToken(accessToken);
//
//        if(vaildTokenStatusValue == 200) {
//            String[] spitToken = accessToken.split(" ");
//            UserRes userRes = userService.findUserByAccessToken(spitToken[1]);

//            followService.insertFollowByAccountEmail(artistEmail, userRes.getAccountEmail());
            followService.insertFollowByAccountEmail(artistEmail, fanEmail);
            return new ResponseEntity<>(HttpStatus.CREATED);
//        } else if(vaildTokenStatusValue == 401) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        } else {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
    }
}
