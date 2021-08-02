package com.ssafy.spotlive.api.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName : CategoryController
 * @작성자 : 강용수
 * @Class 설명 : 카테고리 조회를 수행하는 컨트롤러
 */
@RestController
@RequestMapping("/api/category")
@CrossOrigin(value = {"*"}, maxAge = 6000)
@Api(value = "CategoryController", tags = "CategoryController", description = "카테고리 컨트롤러")
public class CategoryController {

}