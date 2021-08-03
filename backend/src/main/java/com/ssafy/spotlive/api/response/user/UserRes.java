package com.ssafy.spotlive.api.response.user;

import com.ssafy.spotlive.db.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @FileName : UserRes
 * @작성자 : 김민권
 * @Class 설명 : User에 대해서 어떠한 목적성(반환, 요청 등) 없는 데이터 운반용 DTO Class
 */
@Getter
@Setter
@ToString
@Builder
public class UserRes {

    String accountEmail;
    String profileNickname;
    String userName;
    String phoneNumber;
    String profileImageUrl;
    String profileDescription;
    String gender;
    String ageRange;
    String accessToken;
    String refreshToken;

    // showInfo, Reservation, Follow(Fan, artist), userVideo, video 리스트가 필드와 of method의 빌더에 추가되어야 한다.

    public static UserRes of(User user) {
        return UserRes.builder()
                .accountEmail(user.getAccountEmail())
                .profileNickname(user.getProfileNickname())
                .userName(user.getUserName())
                .phoneNumber(user.getPhoneNumber())
                .profileImageUrl(user.getProfileImageUrl())
                .profileDescription(user.getProfileDescription())
                .gender(user.getGender())
                .ageRange(user.getAgeRange())
                .accessToken(user.getAccessToken())
                .refreshToken(user.getRefreshToken())
                .build();
    }
}
