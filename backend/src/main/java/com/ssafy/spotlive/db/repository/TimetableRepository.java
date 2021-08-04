package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * @FileName : TimetableRepository
 * @작성자 : 금아현
 * @Class 설명 : Timetable 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    /**
     * @Method Name : deleteAllByShowInfo_ShowInfoId
     * @작성자 : 금아현
     * @Method 설명 : show_info_id가 id인 timetable 전부 삭제
     */
    @Transactional
    void deleteAllByShowInfo_ShowInfoId(Long id);
}
