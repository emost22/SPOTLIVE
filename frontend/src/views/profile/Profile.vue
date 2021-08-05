<template>
  <div class="txtcolor-white"> 

    <div> 
      <div class="profile-btn-line" v-if="!inMyProfile">
        <div><button type="button" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-nyellow">예매 내역</button></div>
        <div><button type="button" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-ngreen" data-bs-toggle="modal" data-bs-target="#showCreateModal">공연 생성</button></div>
        <div><button type="button" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-npink">프로필 수정</button></div>
      </div>
      <div class="profile-btn-line" v-if="!inMyProfile">
        <button type="button" v-if="!follow" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-npurple">follow</button>
        <button type="button" v-if="follow" class="profile-btn main-bgcolor-black txtcolor-white bdcolor-npurple">unfollow</button>
      </div>
    </div>

    <div class="profile-info">
      <div><img src="~@/assets/icon-profile.png" class="profile-img bdcolor-bold-npink"></div>
      <div class="profile-detail">
        <p> <span class="txtcolor-nyellow">나예뽀</span> 님</p>
        <p> 안녕하세요 나예뽀입니다. 제가 세상의 중심이죠. SPOT LIVE에 오신 것을 환영합니다 </p>
        <p> email@naver.com </p>
      </div>
      <div class="follow-number">
        <p>FOLLOWING</p>
        <p>12</p>
      </div>
      <div class="follow-number">
        <p>FOLLOWER</p>
        <p>10</p>
      </div>
    </div>

    <div>
      <p>나의 공연 정보</p>
      <MyPoster/>
    </div>

    <div>
      <p>나의 동영상</p>
      <MyVideo/>
    </div>

  </div>
</template>

<script>
import MyPoster from './components/MyPoster.vue'
import MyVideo from './components/MyVideo.vue'

export default {
  name: 'Profile',
  components: { 
    MyPoster,
    MyVideo, 
  },
  data: function() {
    return {
      inMyProfile: true,
      follow: false,
      userId: '',
      profileUserId: Number(this.$route.param.profileUserId),
      // 희진님과 프로필 눌러서 param의 프로필 유저 pk번호 데이터 이름 맞추기
      myProfile: [],
      myShows: [],
      myVideos: [],
    }
  },
  methods: {
    getUser: function() {
    },
    getMyProfile: function () {
    },
    getMyShows: function () {
    },
    getMyVideos: function () {
    },
  },
  created: function () {
    this.getMyVideos()
    this.getMyShows()
    this.getMyProfile()
    this.getUser()
  },
  computed: function () {
  },
}
</script>

<style>
.profile-btn-line {
  display: flex;
  flex-direction: row-reverse;
  justify-content: end;
  margin: 20px;
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
.profile-detail{
  width: 300px;
  height: 150px;
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 30px;
  text-align: left;
}
.follow-number {
  margin-top: auto;
  margin-bottom: auto;
  margin-left: 30px;
}
</style>