package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @FileName : CategoryRepository
 * @작성자 : 강용수
 * @Class 설명 : Category 조회 query문 생성을 위한 JPARepository 정의.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}