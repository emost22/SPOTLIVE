<template>
  <div class="wrapper">
    <div class="left-side">
      <div class="wide-screen"></div> 
      <div class="d-flex flex-row mt-3">
        <div class="d-flex flex-column justify-content-center align-items-center">
          <img src="~@/assets/icon-profile.png" class="profile-img bdcolor-npink">
          <img src="~@/assets/icon-live-badge.png" class="badge-design">
        </div>
        <div class="d-flex flex-row justify-content-between detail-top ms-3">
          <div class="d-flex flex-column">
            <h3>{{ videoTitle }}</h3>
            <div class="category bdcolor-npurple txtcolor-npurple my-2">{{ category }}</div>
            <div>{{ videoDescription }}</div>
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
        <button class="bdcolor-nyellow extra-big-button m-1">스트리밍 종료</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'RoomDetail',
  data: function () {
    return {
      videoDescription: "testtesttesttesttesttesttesttesttesttesttest",
      category: "뮤지컬",
      videoTitle: "뷔 닮은 슈퍼 댄서와 소통해요!",
      currentTime: "02:22:01",
      peopleWatching: "23,035"
    }
  },
  methods: {
    openRoomSettingDialog: function () {
      this.$store.dispatch('requestSetIsOpenSettingDialog', 2)
    }
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
.detail-top > div > h3 {
  font-size: 24px;
  color: white;
  margin-bottom: 1px;
}
.detail-top > div > div {
  font-size: 15px;
  font-weight: bold;
  color: white;
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
  font-size: 14px;
}
.badge-design {
  width: 45px;
  height: 20px;
  margin-top: -10px;
  margin-left: -1px;
}
</style>