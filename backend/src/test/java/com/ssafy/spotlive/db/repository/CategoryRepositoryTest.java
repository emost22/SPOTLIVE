package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void findCategoryByCategoryName(){
        // given
        String categoryName = "소통";

        // when
        Optional<Category> category = categoryRepository.findCategoryByCategoryName(categoryName);

        // then
        if (category.isPresent())
            assertThat(category.get().getCategoryName()).isEqualTo(categoryName);
        else
            assertThat(category).isEqualTo(Optional.empty());
    }
}