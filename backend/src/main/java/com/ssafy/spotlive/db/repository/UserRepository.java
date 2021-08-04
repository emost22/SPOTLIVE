package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @FileName : UserRepository
 * @작성자 : 강용수
 * @Class 설명 : User 관련 query문 생성을 위한 JPARepository 정의.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @Method Name : findUserByAccountEmail
     * @작성자 : 김민권
     * @Method 설명 : PK인 UserEmail을 통해 user를 조회한다.
     */
    User findUserByAccountEmail(String accountEmail);

    /**
     * @Method Name : findUserByAccessToken
     * @작성자 : 김민권
     * @Method 설명 : accessToken을 통해 user를 조회한다.
     */
    User findUserByAccessToken(String accessToken);
}