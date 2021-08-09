<template>
  <div class="wrapper">
    <div class="full-wide-screen"> 
      <video class="userVideo" ref="myVideo" autoplay/>
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
  created() {
    this.initSession(new OpenVidu())
    this.doOpenviduCall()
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
        this.$router.push({name: 'RoomDetail', query: { videoId : response.data.videoId }})
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

      return formData
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

</style>