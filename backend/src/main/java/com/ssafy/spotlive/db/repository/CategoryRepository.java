package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @FileName : CategoryRepository
 * @작성자 : 강용수
 * @Class 설명 : Category 조회 query문 생성을 위한 JPARepository 정의.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * @Method Name : findAll
     * @작성자 : 강용수
     * @Method 설명 : 모든 카테고리를 조회하는 메소드
     */
    List<Category> findAll();
}