package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.ShowInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @FileName : ShowInfoRepository
 * @작성자 : 금아현
 * @Class 설명 : ShowInfo 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ShowInfoRepository extends JpaRepository<ShowInfo, Long> {

}
