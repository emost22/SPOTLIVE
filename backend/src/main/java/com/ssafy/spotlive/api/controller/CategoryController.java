package com.ssafy.spotlive.api.controller;

import com.ssafy.spotlive.api.response.CategoryGetRes;
import com.ssafy.spotlive.api.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "모든 카테고리 조회", notes = "모든 카테고리를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러 발생")
    })
    @GetMapping("/")
    public ResponseEntity<List<CategoryGetRes>> findAllCategory(){
        /**
         * @Method Name : findAllCategory
         * @작성자 : 강용수
         * @Method 설명 : 모든 카테고리를 조회하는 메소드
         */
        List<CategoryGetRes> categoryGetResList = categoryService.findAllCategory();

        if (categoryGetResList == null || categoryGetResList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(categoryGetResList, HttpStatus.OK);
    }
}