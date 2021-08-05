package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.response.user.UserRes;
import com.ssafy.spotlive.api.service.AuthService;
import com.ssafy.spotlive.api.service.ReservationService;
import com.ssafy.spotlive.api.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @FileName : ReservationController
 * @작성자 : 금아현
 * @Class 설명 : 예약 정보(Reservation)에 대한 CRUD를 수행하는 컨트롤러
 */
@Api(value = "예약 정보 API", tags = {"ReservationController"}, description = "예약 정보 컨트롤러")
@RestController
@CrossOrigin(value = {"*"}, maxAge = 6000)
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    ReservationService reservationService;

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/{timetableId}")
    @ApiOperation(value = "공연 예약 정보 등록", notes = "사용자의 예약 정보를 등록한다.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "성공"),
            @ApiResponse(code = 404, message = "해당하는 timetable이 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Object> insertReservation(
            @ApiIgnore @RequestHeader("Authorization") String accessToken,
            @PathVariable @ApiParam(value = "예약할 공연 시간의 id", required = true) long timetableId){
        /**
         * @Method Name : insertReservation
         * @작성자 : 금아현
         * @Method 설명 : 사용자의 예약 정보를 등록한다.
         */
        int validTokenStatusValue = authService.isValidToken(accessToken);
        if(validTokenStatusValue == 200){
            String[] splitToken = accessToken.split(" ");
            UserRes userRes = userService.findUserByAccessToken(splitToken[1]);
            Boolean isSuccess = reservationService.insertReservation(userRes.getAccountEmail(), timetableId);
            if(!isSuccess) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else if(validTokenStatusValue == 401) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
