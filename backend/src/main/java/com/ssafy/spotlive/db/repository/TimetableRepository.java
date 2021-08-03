package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @FileName : TimetableRepository
 * @작성자 : 금아현
 * @Class 설명 : Timetable 모델 관련 디비 쿼리 생성을 위한 JPA Query Method 인터페이스 정의.
 */
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
}
