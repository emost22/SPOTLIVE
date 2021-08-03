package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @FileName : VideoRepository
 * @작성자 : 권영린
 * @Class 설명 : Video 관련 query문 생성을 위한 JPARepository 정의.
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {

}
