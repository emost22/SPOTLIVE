package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.ShowInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @FileName : ShowInfoRepository
 * @작성자 : 금아현
 * @Class 설명 : ShowInfo 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
@Repository
public interface ShowInfoRepository extends JpaRepository<ShowInfo, Long> {

    /**
     * @Method Name : findShowInfoByShowInfoId
     * @작성자 : 금아현
     * @Method 설명 : 아이디가 id인 공연정보 반환
     */
    Optional<ShowInfo> findShowInfoByShowInfoId(Long id);

    /**
     * @Method Name : deleteShowInfoByShowInfoId
     * @작성자 : 금아현
     * @Method 설명 : 아이디가 id인 공연정보 삭제
     */
    @Transactional
    Long deleteShowInfoByShowInfoId(Long id);
}
