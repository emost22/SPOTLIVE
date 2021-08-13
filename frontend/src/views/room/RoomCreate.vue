<template>
  <div class="wrapper">
    <div class="full-wide-screen"> 
      <video class="user-video" ref="myVideo" autoplay/>
    </div>
    <div class="btn-wrapper">
      <button class="bdcolor-bold-ngreen extra-big-button" data-bs-toggle="modal" data-bs-target="#roomSettingDialog" @click="openRoomSettingDialog"> 설정 </button>
      <button class="bdcolor-bold-npink extra-big-button" @click="startStreaming()"> 스트리밍 시작 </button>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import { OpenVidu } from 'openvidu-browser'

export default {
  name:'RoomCreate',
  data() {
    return  {
      
    }
  },
  beforeMount() {
    this.$store.dispatch("requestSetUserOnCreateVideo", true)
  },
  
  beforeRouteLeave (to, from, next) {
    if (to.name != "RoomDetail") {
      this.$store.dispatch("requestSetUserOnCreateVideo", false)
      this.$store.dispatch("requestSetCreatedVideoData", {})
      this.$store.dispatch("requestSetFileNameOfVideo", "")
      this.$store.dispatch('requestLeaveSession')
    }
    next()
  },
  created() {
    this.initSession(new OpenVidu())
    this.doOpenviduCall()
    this.$store.dispatch("requestGetLoginUser")
  },
  methods: {
    async initSession(openvidu) {
      this.$store.dispatch("requestInitSession", openvidu)
    },
    async doOpenviduCall() {
      await this.$store.dispatch("requestGetSessionForOpenvidu")
      .then((response) => {
        console.log(response.data)
        this.$store.dispatch("requestGetTokenForOpenvidu", { sessionId: response.data })
        .then((response) => {
          this.setSessionIdAndTokenForOpenvidu(response.data.sessionId, response.data.token)
          this.setAllDevices()
          this.addEventInSession()
          this.connectSession()
        }).catch((error) => {
          console.log(error)
        })         
      }).catch((error) => {
        console.log(error)
      })
    },
    setSessionIdAndTokenForOpenvidu(sessionId, token) {
      this.$store.dispatch("requestSetSessionIdAndTokenForOpenvidu", {
        ovSessionId: sessionId,
        ovToken: token,
      })
    },
    setAllDevices() {
      this.$store.dispatch("requestSetAllDevices")
    }, 
    connectSession() {
      this.$store.dispatch("requestConnectSession")
    },
    addEventInSession() {
      this.$store.dispatch("requestAddEventInSession")
    },
    openRoomSettingDialog() {
      this.$store.dispatch('requestSetIsOpenSettingDialog', 1)
    },
    startStreaming () {
      let formData = this.makeFormDataForStartStreaming()
      this.$store.dispatch('requestStartStreaming', formData)
      .then((response) => {
        this.$store.dispatch('requestSetVideoId', response.data.videoId)
        this.$router.push({name: 'RoomDetail', params: { videoId : response.data.videoId }})
      })
    },
    makeFormDataForStartStreaming() {
      let formData = new FormData()
      let videoInsertPostReq = {
        "videoTitle": this.createdVideoData.videoTitle,
        "videoDescription": this.createdVideoData.videoDescription,
        "mode": this.createdVideoData.mode,
        "categoryId": this.createdVideoData.categoryId,
        "showInfoId": this.createdVideoData.showInfoId,
        "accountEmail": this.loginUser.accountEmail,
        "sessionId": this.ovSessionId,
      }
      formData.append('posterImage', this.createdVideoData.thumbnailImage)
      formData.append('videoInsertPostReq', new Blob([JSON.stringify(videoInsertPostReq)] , {type: "application/json"}))
      console.log(formData)
      return formData
    },
    clickToast: function () {
      console.log('clickToast')
      var myToast = bootstrap.Toast.getOrCreateInstance(this.$refs.myToast)
      myToast.show()
    }
  },
  watch: {
    mainStreamManager: function(val, oldVal) {
      this.mainStreamManager.addVideoElement(this.$refs.myVideo);
    }
  },
  computed: {
    ...mapGetters(['loginUser', 'ovSessionId', 'ovToken', 'OV', 'ovSession', 'audioDevices', 'videoDevices', 'createdVideoData', 'mainStreamManager']),
  },
}
</script>

<style scoped>

.wrapper {
  width: 100%;
  height: 90%;
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.btn-wrapper {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  margin-top: 30px;
  margin-right: 50px;
  margin-left: 50px;
}
.user-video {
  width: 100%;
  height: 100%;
}

</style>