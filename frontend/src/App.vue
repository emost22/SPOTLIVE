<template>
  <div id="app">
    <div v-if="!isLogin">
      <Login/>
    </div>
    <div v-else class="sticky-top match-parent">
      <MainHeader/>
      <router-view></router-view>
    </div>
    <RoomSettingDialog/>
  </div>
</template>

<script>
import MainHeader from './views/MainHeader.vue'
import Login from './views/login/Login.vue'
import RoomSettingDialog from './views/room/components/RoomSettingDialog.vue'

export default ({
  name: "App",
  components: {
    Login,
    MainHeader,
    RoomSettingDialog,
  },
  data: function () {
    return {
      isLogin: true,
    }
  },methods: {
    getVideos() {
      this.$store.dispatch('getVideos')
    },
  },
  computed: {
    // isLogin: function () {
    //   return this.$store.getters.isLogin
    // },
  },
  created: function () {
    const token = localStorage.getItem('jwt')
    if (token) {
      this.isLogin = true
    }
  },
  mounted: function () {
    this.getVideos()
    this.$router.push({ name: "Main" })
  },
})
</script>

<style>
html { 
  height:100%; 
}
body {
  /* 메인 배경 */
  background-color: #121212;
  height:100%;
  widows: 100%;
}
.match-parent {
  width: 100%;
  height:100%;
}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale; 
  text-align: center;
  color: #2c3e50;
  height: 100%;
  width: 100%;
}
/* 메인 배경 */
.main-bgcolor-black {
  background-color: #121212;
}
/* 헤더, 사이드바, 필터, 모달 배경 */
.bgcolor-deep-grey {
  background-color: #242424;
}
/* 검색창, 알림 */
.bgcolor-mid-deep-grey {
  background-color: #6A6A6A;
}
/* 모달 인풋*/
.input-bgcolor-light-grey {
  background-color: #595959;
}
/* 카메라 인풋 */
.camera-input-bgcolor-light-grey {
  background-color: #C4C4C4;
}
.txtcolor-white {
  color: #FFFFFF;
}
.txtcolor-npink {
  color: #F84ABF;
  text-shadow:
      0 0 9px #F84ABF;
}
.txtcolor-ngreen {
  color: #04F7CA;
  text-shadow:
      0 0 9px #04F7CA;
}
.txtcolor-nyellow {
  color: #FEF279;
  text-shadow:
      0 0 9px #FEF279;
}
.txtcolor-npurple {
  color: #C752FE;
  text-shadow:
      0 0 9px #C752FE;
}
.bdcolor-ngreen {
  border: 2px solid #04F7CA;
  box-shadow: 
    0 0 9px #04F7CA;
}
.bdcolor-bold-ngreen {
  border: 2px solid #04F7CA;
  box-shadow: 
    0 0 9px #04F7CA,
    0 0 12px #04F7CA;
}
.bdcolor-npink {
  border: 2px solid #F84ABF;
  box-shadow: 
    0 0 9px #F84ABF;
}
.bdcolor-bold-npink {
  border: 2px solid #F84ABF;
  box-shadow: 
    0 0 9px #F84ABF,
    0 0 12px #F84ABF;
}
.bdcolor-nyellow {
  border: 2px solid #FEF279;
  box-shadow: 
    0 0 9px #FEF279;
}
.bdcolor-bold-nyellow {
  border: 2px solid #FEF279;
  box-shadow: 
    0 0 9px #FEF279,
    0 0 12px #FEF279;
}
.bdcolor-npurple {
  border: 2px solid #C752FE;
  box-shadow: 
    0 0 9px #C752FE;
}
.bdcolor-bold-npurple {
  border: 2px solid #C752FE;
  box-shadow: 
    0 0 9px #C752FE,
    0 0 12px #C752FE;
}
.extra-big-button {
  width: 300px;
  height: 50px;
  line-height: 50px;
  background: none;
  font-size: 25px;
  font-weight: bold;
  color: white;
  text-align: center;
}
.big-button {
  width: 250px;
  height: 40px;
  background: none;
  font-size: 24px;
  font-weight: bold;
  color: white;
}
.full-wide-screen {
  margin-left: 30px;
  margin-right: 30px;
  height: 80%;
  background-color: lightgrey;
}
.modal-design {
  width: 60%;
  height: 90%;
  background-color: #242424;
  color: white;
}
</style>
