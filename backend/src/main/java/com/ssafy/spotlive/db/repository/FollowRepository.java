package com.ssafy.spotlive.db.repository;

import com.ssafy.spotlive.db.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @FileName : FollowRepository
 * @작성자 : 권영린
 * @Class 설명 : 팔로우 관련 기능을 위한 Repository 정의.
 */
@Repository
public interface FollowRepository extends JpaRepository<Follow, String> {
    Optional<Follow> findFollowsByArtistAccountEmailAndFanAccountEmail(String artistEmail, String fanEmail);
}
