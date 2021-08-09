<template>
  <div class="txtcolor-white"> 

    <div> 
      <div class="profile-btn-line" v-if="inMyProfile">
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
      <div><img :src="myProfile.profileImageUrl" class="profile-img bdcolor-bold-ngreen"></div>
      <div class="profile-detail">
        <p> <span class="txtcolor-nyellow"> {{ myProfile.profileNickname }}</span> 님</p>
        <p> 
          {{ myProfile.profileDescription }}
        </p>
        <p> {{ myProfile.accountEmail }} </p>
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
  },
  computed: {
    ...mapGetters(['loginUser']),
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