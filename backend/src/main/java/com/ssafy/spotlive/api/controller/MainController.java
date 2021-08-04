package com.ssafy.spotlive.api.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

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

}