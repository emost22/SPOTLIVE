<template>
  <div class="wrapper">
    <div class="left-side">
      <div class="wide-screen">
        <video controls width="1000" v-if="videoUrl != ''">
          <source :src="videoUrl" type="video/mp4">
        </video>
      </div> 
      <div class="d-flex flex-row mt-3">
        <div class="d-flex flex-column justify-content-center align-items-center">
          <img src="~@/assets/icon-profile.png" class="profile-img bdcolor-npink">
          <img src="~@/assets/icon-live-badge.png" class="badge-design">
        </div>
        <div class="d-flex flex-row justify-content-between detail-top ms-3">
          <div class="d-flex flex-column">
            <div class="videoTitle">{{ videoTitle }}</div>
            <div class="category bdcolor-npurple txtcolor-npurple my-2">{{ category }}</div>
            <div class="videoDescription">{{ videoDescription }}</div>
          </div>
          <div>
            
          </div>
        </div>
      </div>
    </div>
    <div class="right-side d-flex flex-column flex-end">
      <div class="chatting-part" style="position: relative;">

      </div>
      <div class="d-flex flex-column align-items-center mt-3">
        <button class="bdcolor-nyellow extra-big-button m-1" @click="exitReplay()"> 나가기 </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'RoomDetail',
  data: function () {
    return {
      videoId:"",
      videoDescription: "",
      mainStreamAccountEmail: "",
      category: "",
      videoTitle: "",
      videoUrl: "",
    }
  },
  methods: {
    openRoomSettingDialog() {
      this.$store.dispatch('requestSetIsOpenSettingDialog', 2)
    },
    exitReplay() {
      this.$router.push({ name: 'Main' })
    },
  },
  
  created() {
    console.log("MOUNTED!!!")
    console.log(this.loginUser)
    this.videoId = this.$route.params.videoId
    this.$store.dispatch('requestGetRoomDetail', this.videoId)
    .then((response) => {
      console.log(response)
      this.videoDescription = response.data.videoDescription
      this.category = response.data.categoryRes.categoryName
      this.videoTitle = response.data.videoTitle
      this.mainStreamAccountEmail = response.data.userRes.mainStreamAccountEmail
      this.videoUrl = response.data.videoUrl
    })
  },
  computed: {
    ...mapGetters(['loginUser']),
  },
}
</script>

<style scoped>
.left-side {
  flex-grow: 10;
  height: 100%;
  margin-right: 10px;
}
.wide-screen {
  height: 80%;
  background-color: lightgrey;
}
.chatting-part {  
  background-color: #242424;
  height: 80%;
}
.chatting-screen {
  
}
.wrapper {
  width: 100%;
  height: 95%;
  padding: 30px;
  display: flex;
}
.right-side {
  flex-grow: 1;
  height: 100%;
  margin-left: 10px;
}
.input-part > input {
  width: 100%;
  margin: 5px;
}
.profile-img {
  width: 50px;
  height: 50px;
  border-radius: 100%;
}
.watching-people > img {
  width: 30px;
  height: 20px;
}
.watching-people {
  color: #FEF279;
  text-shadow:
      0 0 9px #FEF279;
  margin-right: 10px;
}
.detail-top {
  width: 100%;
}
.detail-top > div > .videoTitle {
  font-size: 24px;
  color: white;
  font-weight: bold;
  text-align: left;
  margin-bottom: 1px;
}
.detail-top > div > .videoDescription {
  font-size: 15px;
  font-weight: bold;
  color: white;
  text-align: left;
  margin-bottom: 1px;
}
.current-time {
  color: white
}
.category {
  width: 110px;
  height: 26px;
  line-height: 24px;
  border-radius: 13px;
  text-align: center;
  font-size: 14px;
}
.badge-design {
  width: 45px;
  height: 20px;
  margin-top: -10px;
  margin-left: -1px;
}
</style>