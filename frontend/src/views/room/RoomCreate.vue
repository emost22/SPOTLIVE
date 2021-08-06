<template>
  <div class="wrapper">
    <div class="full-wide-screen"> 
      <user-video />
    </div>
    <div class="btn-wrapper">
      <button class="bdcolor-bold-ngreen extra-big-button" data-bs-toggle="modal" data-bs-target="#exampleModal"> 설정 </button>
      <router-link :to="{ name: 'RoomDetail' }"><button class="bdcolor-bold-npink extra-big-button"> 스트리밍 시작 </button></router-link>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { OpenVidu } from 'openvidu-browser'
import UserVideo from './components/UserVideo.vue';

export default {
  components: {
    UserVideo,
  },
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
      await this.$store.dispatch("requestGetSessionAndTokenForOpenvidu")
      .then((response) => {
        this.setSessionIdAndTokenForOpenvidu(response.data.sessionId, response.data.token)
        this.setAllDevices()
        this.addEventInSession()
        this.connectSession()
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
  },
  computed: {
    ...mapGetters(['loginUser', 'ovSessionId', 'ovToken', 'OV', 'ovSession', 'audioDevices', 'videoDevices']),
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