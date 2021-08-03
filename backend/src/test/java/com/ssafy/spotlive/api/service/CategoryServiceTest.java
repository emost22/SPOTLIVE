package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.category.CategoryGetRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;

    @Test
    void findAllCategory() {
        List<CategoryGetRes> categoryGetResList = categoryService.findAllCategory();

        assertEquals(categoryGetResList.size(), 6);
    }

    @Test
    void findCategoryByCategoryName() {
        String categoryName = "소통";
        CategoryGetRes categoryGetRes = categoryService.findCategoryByCategoryName(categoryName);

        assertEquals(categoryGetRes.getCategoryName(), categoryName);
    }
}