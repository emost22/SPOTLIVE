package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void findCategoryByCategoryName(){
        String categoryName = "소통";
        Optional<Category> category = categoryRepository.findCategoryByCategoryName(categoryName);

        if (category.isPresent())
            assertEquals(category.get().getCategoryName(), categoryName);
        else
            assertEquals(category, Optional.empty());
    }
}