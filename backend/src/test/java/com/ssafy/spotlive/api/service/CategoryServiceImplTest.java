package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.db.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

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
}