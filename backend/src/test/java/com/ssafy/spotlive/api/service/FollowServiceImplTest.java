package com.ssafy.spotlive.api.service;

import com.ssafy.spotlive.api.response.follow.FollowFindByArtistAccountEmailGetRes;
import com.ssafy.spotlive.api.response.follow.FollowFindByFanAccountEmailGetRes;
import com.ssafy.spotlive.db.entity.Follow;
import com.ssafy.spotlive.db.entity.User;
import com.ssafy.spotlive.db.repository.FollowRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class FollowServiceImplTest {

    @Mock
    private FollowRepository followRepository;

    @InjectMocks
    private FollowServiceImpl followService;

    @Test
    void insertFollowByAccountEmail() {
        //given
        String fanEmail = "ssss@naver.com";
        String artistEmail = "aaaa@naver.com";

        //when
        followService.insertFollowByAccountEmail(artistEmail, fanEmail);

        //then
        Mockito.verify(followRepository).save(Mockito.any());
    }

    @Test
    void deleteFollowByAccountEmail() {
        //given
        String fanEmail = "ssss@naver.com";
        String artistEmail = "aaaa@naver.com";

        //when
        followService.deleteFollowByAccountEmail(artistEmail, fanEmail);

        //then
        Mockito.verify(followRepository).deleteById(Mockito.any());
    }

    @Test
    void findArtistByFanAccountEmail() {
        //given
        String fanEmail = "sqk8657@naver.com";
        List<Follow> followList = new ArrayList<>();
        Follow follow = new Follow();
        User fan = new User();
        fan.setAccountEmail(fanEmail);
        User artist = new User() ;
        artist.setAccountEmail("23213@#2");
        follow.setFan(fan);
        follow.setArtist(artist);
        followList.add(follow);

        //when
        Mockito.when(followRepository.findFollowsByFanAccountEmail(fanEmail))
                .thenReturn(Optional.ofNullable(followList));
        List<FollowFindByFanAccountEmailGetRes> artistByFanAccountEmail = followService.findArtistByFanAccountEmail(fanEmail);

        //then
        Assertions.assertThat(artistByFanAccountEmail.size()).isEqualTo(1);
        Assertions.assertThat(artistByFanAccountEmail.get(0).getAccountEmail()).isEqualTo(artist.getAccountEmail());
    }

    @Test
    void findFanByArtistAccountEmail() {
        //given
        String artistEmail = "sqk8657@naver.com";
        List<Follow> followList = new ArrayList<>();
        Follow follow = new Follow();
        User artist = new User();
        artist.setAccountEmail(artistEmail);
        User fan = new User() ;
        fan.setAccountEmail("artist");
        follow.setFan(fan);
        follow.setArtist(artist);
        followList.add(follow);

        //when
        Mockito.when(followRepository.findFollowsByArtistAccountEmail(artistEmail)).thenReturn(Optional.of(followList));
        List<FollowFindByArtistAccountEmailGetRes> fanByArtistAccountEmail = followService.findFanByArtistAccountEmail(artistEmail);

        //then
        Assertions.assertThat(fanByArtistAccountEmail.size()).isEqualTo(1);
        Assertions.assertThat(fanByArtistAccountEmail.get(0).getAccountEmail()).isEqualTo(fan.getAccountEmail());
    }

}