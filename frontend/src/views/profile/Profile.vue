<template>
  <div class="txtcolor-white profile-box"> 

    <div> 
      <div class="profile-btn-line" v-if="inMyProfile">
        <div><button type="button" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-nyellow" data-bs-toggle="modal" data-bs-target="#ticketModal">예매 내역</button></div>
        <div><button type="button" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-ngreen" data-bs-toggle="modal" data-bs-target="#showCreateModal">공연 생성</button></div>
        <div><button type="button" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-npink" data-bs-toggle="modal" data-bs-target="#profileUpdateModal">프로필 수정</button></div>
      </div>
      <div class="profile-btn-line" v-if="!inMyProfile">
        <button type="button" @click="clickFollowButton" v-if="!follow" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-npurple">follow</button>
        <button type="button" @click="clickUnfollowButton" v-if="follow" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-npurple">unfollow</button>
      </div>
    </div>

    <div class="profile-info">
      <div><img :src="myProfile.profileImageUrl" class="profile-img"></div>
      <div class="profile-detail d-flex flex-column justify-content-evenly">
        <div class="profile-txt"> <span class="txtcolor-nyellow"> {{ myProfile.profileNickname }}</span> 님</div>
        <div class="profile-txt"> {{ myProfile.profileDescription }} </div>
        <div divclass="profile-txt"> {{ myProfile.accountEmail }} </div>
      </div>
      <div class="follow-number">
        <p>FOLLOWING </p>
        <p>{{ following.length }}</p>
      </div>
      <div class="follow-number">
        <p>FOLLOWER</p>
        <p>{{ follower.length }} </p>
      </div>
    </div>

    <div>
      <p class="txtcolor-white-npurple main-title">나의 공연 정보</p>
      <MyShow
        :shows="myShows"
      />
    </div>

    <div>
      <p class="txtcolor-white-ngreen main-title">나의 동영상</p>
      <MyVideo
        :videos="myVideos"
      />
    </div>

  </div>
</template>

<script>
import { mapGetters } from "vuex"
import MyShow from './components/MyShow.vue'
import MyVideo from './components/MyVideo.vue'

export default {
  name: 'Profile',
  components: { 
    MyShow,
    MyVideo, 
  },
  data: function() {
    return {
      inMyProfile: true,
      follow: false,
      userId: '',
      profileId: this.$route.query.profileId,
      // 타인의 프로필에 진입하고 내 프로필을 메인헤더에서 누르면 이동하지 않음 초기화 문제
      // 검색 뷰에서도 초기화 안 되서 2번째 검색 실패 (computed)
      following: '',
      follower: '',
      myProfile: [],
      myShows: [],
      myVideos: [],
      myReservations: [],
    }
  },
  created: function () {
    this.getUser()
    console.log(this.profileId)
    if (this.inMyProfile) {
      this.getMyProfile()
    } else {
      this.getProfile()
    }    
  },
  methods: {
    getUser() {
      this.userId = this.loginUser.accountEmail
      if (this.userId == this.profileId) {
        this.inMyProfile = true
      } else {
        this.inMyProfile = false
      }
    },
    getMyProfile() {
      this.$store.dispatch('requestGetMyProfile')
      .then((response) => {
        console.log("getMyProfile() SUCCESS!!")
        console.log(response.data)
        this.myProfile = response.data
        this.following = response.data.followMyArtistResList
        this.follower = response.data.followMyFanResList
        this.myShows = response.data.showInfoResList
        this.myVideos = response.data.videoResList
        this.myReservations = response.data.reservationResList
      })
      .catch((error) => {
        console.log(error)
      })
    },
    getProfile() {
      this.$store.dispatch('requestGetProfile', { profileId : this.profileId})
      .then((response) => {
        console.log("getProfile() SUCCESS!!")
        console.log(response.data)
        this.myProfile = response.data
        this.following = response.data.followMyArtistResList
        this.follower = response.data.followMyFanResList
        this.myShows = response.data.showInfoResList
        this.myVideos = response.data.videoResList
        this.myReservations = response.data.reservationResList
      })
      .catch((error) => {
        console.log(error)
      })
    },
    clickFollowButton() {
      this.$store.dispatch('requestClickFollowButton', { profileId : this.profileId})
      .then((response) => {
        console.log("getClickFollowButton() SUCCESS!!")
        console.log(response.data)
        this.follow = true
        this.getProfile()
        // 실시간 팔로워수 변경 비동기화 문제
      })
      .catch((error) => {
        console.log(error)
      })
    },
    clickUnfollowButton() {
      this.$store.dispatch('requestClickUnfollowButton', { profileId : this.profileId})
      .then((response) => {
        console.log("getClickUnfollowButton() SUCCESS!!")
        console.log(response.data)
        this.follow = false
        this.getProfile()
        // 실시간 팔로워수 변경 비동기화 문제
      })
      .catch((error) => {
        console.log(error)
      })
    },
  },
  computed: {
    ...mapGetters(['loginUser']),
  },
}
</script>

<style>
.profile-box {
  margin: 25px;
  margin-right: 25px;
}
.profile-btn-line {
  display: flex;
  flex-direction: row-reverse;
  justify-content: end;
}
.profile-btn {
  width: 110px;
  height: 30px;
  border-radius: 15px;
  margin-left: 20px;
}
.profile-info {
  display: flex;
  flex-direction: row;
  justify-content: center;
  margin: 20px;
}
.profile-img {
  width: 150px;
  height: 150px;
  border-radius: 100%;
}
.profile-detail {
  width: 300px;
  height: 150px;
  text-align: left;
  margin-left: 30px;
}
.follow-number {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 30px;
}
</style>