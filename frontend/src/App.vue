<template>
  <div id="app">
    <div class="sticky-top match-parent">
      <MainHeader/>
      <router-view></router-view>
    </div>
    <RoomSettingDialog/>
    <ShowCreateDialog/>
  </div>
</template>

<script>
import MainHeader from './views/MainHeader.vue'
import RoomSettingDialog from './views/room/components/RoomSettingDialog.vue'
import ShowCreateDialog from './views/profile/components/ShowCreateDialog.vue'

export default ({
  name: "App",
  components: {
    MainHeader,
    RoomSettingDialog,
    ShowCreateDialog,
  },
  data: function () {
    return {
      isLogin: false,
    }
  },methods: {
    getVideos() {
      this.$store.dispatch('getVideos')
    },
  },
  computed: {

  },
  created: function () {
    const token = localStorage.getItem('accessToken')
    if (token) {
      this.isLogin = true
    }
  },
  mounted: function () {
    // this.getVideos()
    const code = this.$route.query.code
    if(code == "" || code == null || code == undefined) this.$router.push({ name: "Main" })
    else {
      this.$store.dispatch('doKakaoLogin', code)
    }
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

textarea {
  resize: none;
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
.middle-button {
  width: 180px;
  height: 40px;
  background: none;
  font-size: 18px;
  font-weight: bold;
  color: white;
}
.small-button {
  width: 130px;
  height: 40px;
  background: none;
  font-size: 15px;
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
  max-height: 700px;
  min-width: 500px;
  width: 70%;
  background-color: #242424;
  color: white;
}
.modal-content-m {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  pointer-events: auto;
  outline: 0;
}
.no-border {
  border: 0px;
}
.modal-footer-m {    
  display: flex;
  justify-content: center;
  padding: 1.2rem;
}
form {
  margin-top: 20px;
}
.custom-select-control {
  background-color: #595959;
  padding: .375rem 2.25rem .375rem .75rem;
  width: 100%;
  font-size: 1rem;
  font-weight: 400;
  color: white;
  background-repeat: no-repeat;
  background-position: right .75rem center;
  background-size: 16px 12px;
  border: 0px;
  border-radius: .25rem;
  transition: border-color .15s ease-in-out,box-shadow .15s ease-in-out;
}
.custom-form-control {
  display: block;
  width: 100%;
  background-color: #595959;
  border: 0px;
  color: white;
  border-radius: .25rem;
  padding: .375rem .75rem;
}
.btn-close {
  background-image: url('~@/assets/icon-x.png');
  opacity: 1;
}
.btn-close:hover {
  background-image: url('~@/assets/icon-x.png');
}
.label-alignment {
  text-align: left;
}
label:hover {
  color: white;
  cursor: pointer;
}
.content {
  position: absolute;
  top: 50px; right: 0; bottom: 0; left: 0;
  padding: 30px;
  display: none;
}
</style>
