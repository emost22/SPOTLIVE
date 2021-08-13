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
      <div><img :src="createdProfileData.myProfile.profileImageUrl" class="profile-big-img"></div>
      <div class="profile-detail d-flex flex-column justify-content-evenly">
        <div class="profile-txt"> <span class="txtcolor-nyellow"> {{ createdProfileData.myProfile.profileNickname }}</span> 님</div>
        <div class="profile-txt"> {{ createdProfileData.myProfile.profileDescription }} </div>
        <div divclass="profile-txt"> {{ createdProfileData.myProfile.accountEmail }} </div>
      </div>
      <div class="follow-number">
        <p>FOLLOWING </p>
        <p>{{ createdProfileData.following.length }}</p>
      </div>
      <div class="follow-number">
        <p>FOLLOWER</p>
        <p>{{ createdProfileData.follower.length }} </p>
      </div>
    </div>

    <div class="mx-5">
      <div class="txtcolor-white-npurple main-title">나의 공연 정보</div>
      <MyShow
        :shows="createdProfileData.myShows"
        :inMyProfile="inMyProfile"
      />
    </div>

    <div class="mx-5">
      <div class="txtcolor-white-ngreen main-title mb-5">나의 동영상</div>
      <MyVideo
        :videos="createdProfileData.myVideos"
        :inMyProfile="inMyProfile"
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
      profileId: '',
      following: '',
      follower: '',
      myProfile: [],
      myShows: [],
      myVideos: [],
      myReservations: [],
    }
  },
  created() {
    this.getUser()
    console.log('크리에이트')
    console.log(this.profileId)
    if (this.inMyProfile) {
      this.getMyProfile()
      console.log('내프로필')
    } else {
      this.getProfile()
      console.log('타인프로필')
    }    
  },
  mounted() {
    this.profileId = this.$route.params.profileId
    console.log(this.profileId)
    this.getUser()
    console.log(this.inMyProfile)
    if (this.inMyProfile) {
      this.getMyProfile()
      console.log('내프로필')
    } else {
      this.getProfile()
      console.log('타인프로필')
    }    
  },
  beforeRouteLeave (to, from, next) {
    this.$store.dispatch("requestSetCreatedProfileData", {})
    this.profileId = ''
    next()
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
        var ProfileData = {
          myProfile : response.data,
          following : response.data.followMyArtistResList,
          follower : response.data.followMyFanResList,
          myShows : response.data.showInfoResList,
          myVideos : response.data.videoResList,
          myReservations : response.data.reservationResList,
        }
        this.$store.dispatch('requestSetCreatedProfileData', ProfileData)
        console.log('뷰엑스에 내 프로필 데이터 보내기')
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
        var ProfileData = {
          myProfile : response.data,
          following : response.data.followMyArtistResList,
          follower : response.data.followMyFanResList,
          myShows : response.data.showInfoResList,
          myVideos : response.data.videoResList,
          myReservations : response.data.reservationResList,
        }
        this.$store.dispatch('requestSetCreatedProfileData', ProfileData)
        console.log('뷰엑스에 타인 프로필 데이터 보내기')
      })
      .catch((error) => {
        console.log(error)
      })
    },
    clickFollowButton() {
      // 팔로잉 리스트 중에 있는지 여부부터 확인
      this.$store.dispatch('requestClickFollowButton', { profileId : this.profileId})
      .then((response) => {
        console.log("getClickFollowButton() SUCCESS!!")
        console.log(response.data)
        this.follow = true
        this.getProfile()
      })
      .catch((error) => {
        console.log(error)
      })
    },
    clickUnfollowButton() {
      // 팔로잉 리스트 중에 있는지 여부부터 확인
      this.$store.dispatch('requestClickUnfollowButton', { profileId : this.profileId})
      .then((response) => {
        console.log("getClickUnfollowButton() SUCCESS!!")
        console.log(response.data)
        this.follow = false
        this.getProfile()
      })
      .catch((error) => {
        console.log(error)
      })
    },
  },
  computed: {
    ...mapGetters(['loginUser', 'createdProfileData', ]),
  },
  watch: {
    loginUser: function(val, oldVal) {
      this.getUser()
    },
    createdProfileData(val, oldVal) {
      console.log("수정하자 watch 프로필")
      this.getUser()
      console.log(this.profileId)
      if (this.inMyProfile) {
        this.getMyProfile()
        console.log('내프로필 변화')
      } else {
        this.getProfile()
        console.log('타인프로필 변화')
      }    
    },
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
.profile-big-img {
  width: 150px;
  height: 150px;
  border-radius: 100%;
  border: none;
  box-shadow: 
    0 0 9px #FFFFFF,
    0 0 12px #FFFFFF,
    0 0 20px #FFFFFF,
    0 0 35px #FFFFFF;
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