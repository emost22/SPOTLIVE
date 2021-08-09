package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.entity.Category;
import com.ssafy.spotlive.db.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    void findAllCategory(){
        // given x

        // when
        categoryServiceImpl.findAllCategory();

        // then
        verify(categoryRepository).findAll();
    }

    @Test
    void findCategoryByCategoryName(){
        // given
        String categoryName = "ㅁㄴㅇㄹ";
        Category category = new Category();

        // when
        when(categoryRepository.findCategoryByCategoryName(categoryName)).thenReturn(Optional.ofNullable(category));
        categoryServiceImpl.findCategoryByCategoryName(categoryName);

        // then
        verify(categoryRepository).findCategoryByCategoryName(categoryName);
    }
}