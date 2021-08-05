package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @FileName : VideoRepository
 * @작성자 : 권영린
 * @Class 설명 : Video 관련 query문 생성을 위한 JPARepository 정의.
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    /**
     * @Method Name : findVideosByMode
     * @작성자 : 강용수
     * @Method 설명 : mode(홍보 / 소통 / 공연)를 기준으로 Video를 검색하는 메소드
     */
    Page<Video> findVideosByMode(Pageable pageable, String mode);

    /**
     * @Method Name : findVideosByModeAndCategoryId
     * @작성자 : 강용수
     * @Method 설명 : mode(홍보 / 소통 / 공연)와 카테고리 id 기준으로 Video를 검색하는 메소드
     */
    Page<Video> findVideosByModeAndCategory_CategoryId(Pageable pageable, String mode, Long categoryId);

    /**
     * @Method Name : findVideosByIsLive
     * @작성자 : 강용수
     * @Method 설명 : Video를 조회수(다시보기) / 시청자(라이브) 순으로 검색하는 메소드
     */
    Page<Video> findVideosByIsLive(Pageable pageable, Boolean isLive);

    /**
     * @Method Name : findVideosByIsLiveAndCategory_CategoryId
     * @작성자 : 강용수
     * @Method 설명 : Video를 카테고리 id 기준과 조회수(다시보기) / 시청자(라이브) 순으로 검색하는 메소드
     */
    Page<Video> findVideosByIsLiveAndCategory_CategoryId(Pageable pageable, Boolean isLive, Long categoryId);

    /**
     * @Method Name : findVideosByUser_AccountEmailIn
     * @작성자 : 강용수
     * @Method 설명 : 자신이 팔로우한 유저의 Video를 검색하는 메소드
     */
    Page<Video> findVideosByUser_AccountEmailIn(Pageable pageable, List<String> accountEmailList);

    /**
     * @Method Name : findVideosByCategory_CategoryIdAndUser_AccountEmailIn
     * @작성자 : 강용수
     * @Method 설명 : 자신이 팔로우한 유저의 Video를 카테고리 id 기준으로 검색하는 메소드
     */
    Page<Video> findVideosByCategory_CategoryIdAndUser_AccountEmailIn(Pageable pageable, Long categoryId, List<String> accountEmailList);

    /**
     * @Method Name : findVideosByUserAccountEmail
     * @작성자 : 권영린
     * @Method 설명 : 이메일로 해당 유저의 Video를 검색하는 메소드
     */
    List<Video> findVideosByUserAccountEmail(String accountEmail);

    /**
     * @Method Name : findVideosByMode
     * @작성자 : 강용수
     * @Method 설명 : mode(홍보 / 소통 / 공연)를 기준으로 Video를 검색하는 메소드
     */
    Optional<List<Video>> findVideosByMode(String mode);
}