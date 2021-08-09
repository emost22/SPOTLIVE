<template>
  <div class="wrapper">
    <div class="left-side">
      <div class="wide-screen">
        <video class="userVideo" ref="myVideo" autoplay/>
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
            <span class="watching-people"><img src="~@/assets/icon-people-watching.png"> {{ peopleWatching }}</span>
            <span class="current-time"> {{ currentTime }} </span>
          </div>
        </div>
      </div>
    </div>
    <div class="right-side d-flex flex-column flex-end">
      <div class="chatting-part">
        <div class="chatting-screen">
        </div>
        <div class="input-part">
        </div>
      </div>
      <div class="d-flex flex-column align-items-center mt-3">
        <button class="bdcolor-ngreen extra-big-button m-1" data-bs-toggle="modal" data-bs-target="#roomSettingDialog" @click="openRoomSettingDialog">스트리밍 수정</button>
        <button class="bdcolor-nyellow extra-big-button m-1" @click="closeStreaming()">스트리밍 종료</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RoomDetail',
  data: function () {
    return {
      videoId:"",
      videoDescription: "",
      category: "",
      videoTitle: "",
      currentTime: "02:22:01",
      peopleWatching: "0"
    }
  },
  methods: {
    openRoomSettingDialog: function () {
      this.$store.dispatch('requestSetIsOpenSettingDialog', 2)
    },
    closeStreaming: function () {
      this.$store.dispatch('requestCloseVideo', this.videoId)
      .then(res => {
        console.log(res)
      })
    }
  },
  mounted() {
    this.videoId = this.$route.query.videoId
    this.$store.dispatch('requestGetRoomDetail', this.videoId)
    .then((response) => {
      console.log(response)
      this.videoDescription = response.data.videoDescription
      this.category = response.data.categoryId
      this.videoTitle = response.data.videoTitle
    })
  }
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
  width: 70px;
  height: 70px;
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