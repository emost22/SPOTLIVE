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
            <span class="watching-people"><img src="~@/assets/icon-people-watching.png"> {{ hit }}</span>
            <span class="current-time"> {{ takenTime.h }}:{{ takenTime.m }}:{{ takenTime.s }} </span>
          </div>
        </div>
      </div>
    </div>
    <div class="right-side d-flex flex-column flex-end">
      <div class="chatting-part" style="position: relative;">
        <div class="chatting-screen">
          <div class="row" v-for="(chat, index) in chatList" :key="index">
            <div class="col-md-2" style="text-align: center">
              <img :src="chat.profileImg" class="profile-img bdcolor-bold-ngreen">
            </div>
            <div class="col-md-6 profile-detail">
              <p> 
                <span class="txtcolor-nyellow"> {{ chat.userName }}</span> 님 <br>
                {{ chat.charStr }}
              </p>
            </div>
          </div>
        </div>
        <div class="row" style="position: absolute; bottom: 0px;">
          <div class="input-part col-md-7">
            <input type="text" v-model="chatMsg">
          </div>
          <div class="col-md-2">
            <button class="small-button col-md-5" @click="sendChat()"> 전송 </button>
          </div>
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
import { mapGetters } from "vuex"

export default {
  name: 'RoomDetail',
  data: function () {
    return {
      videoId:"",
      videoDescription: "",
      category: "",
      videoTitle: "",
      startTime: "",
      takenTime: {
        h: '',
        m: '',
        s: '',
      },
      hit: 0,
      chatMsg: "",
      chatList: [
          {
            userName: "김민권1",
            profileImg: "https://spotlive-img-bucket.s3.ap-northeast-2.amazonaws.com/8d67d654ab214180bb5aacb1ecb62a93.jpeg",
            charStr: "안녕하세요, 채팅입니다! 1"
          },
          {
            userName: "김민권2",
            profileImg: "https://spotlive-img-bucket.s3.ap-northeast-2.amazonaws.com/8d67d654ab214180bb5aacb1ecb62a93.jpeg",
            charStr: "안녕하세요, 채팅입니다! 2"
          },
      ],
      recordName: "",
      recordURL: "",
      isRecord: true,
    }
  },
  methods: {
    openRoomSettingDialog() {
      this.$store.dispatch('requestSetIsOpenSettingDialog', 2)
    },
    closeStreaming() {
      this.$router.push({ name: 'Main' })
    },
    startTimer() {
      setInterval(() => {
        let total = (new Date().getTime() - new Date(this.startTime).getTime()) / 1000
        this.takenTime.h = parseInt(total / 3600).toString().padStart(2, '0')
        this.takenTime.m = parseInt((total % 3600) / 60).toString().padStart(2, '0')
        this.takenTime.s = parseInt(((total % 3600) % 60)).toString().padStart(2, '0')
      }, 1000);
    },
    addEventForChat() {
      this.ovSession.on('signal:my-chat', (event) => {
        let givenCharStr = event.data
        let userId = JSON.parse(event.from.data).clientData
        console.log('[OPENVIDU] Get Chat data: ' + givenCharStr + ', UserId: ' + userId)
        this.$store.dispatch("requestGetUserByAccountEmail", { accountEmail: userId })
        .then((response) => {
          this.chatList.push({
            userName: response.data.userName,
            profileImg: response.data.profileImageUrl,
            charStr: givenCharStr,
          })
        }).catch((error) => {
          console.log(error)
        })
      })
    },
    addEventForJoinAndExit() {
      this.ovSession.on('signal:join-video', (event) => {
        let eventAccountEmail = event.data
        console.log('[OPENVIDU] JOIN ACCESSED: ' + eventAccountEmail)
        this.updateVideoInfo()
      })
      
      this.ovSession.on('signal:exit-video', (event) => {
        let eventAccountEmail = event.data
        console.log('[OPENVIDU] EXIT ACCESSED: ' + eventAccountEmail)
        this.updateVideoInfo()
      })
    },
    sendChat() {
      this.$store.dispatch("requestSendChat", { chatMsg: this.chatMsg })
    },
    startRecoding() {
      let today = new Date();
      this.recordName = "REC_" + this.ovSessionId + "_" + today.getFullYear() + (today.getMonth() + 1) + today.getDate()
      console.log(this.recordName)
      const recReq = {
        session: this.ovSessionId,
        name: this.recordName,
        outputMode: "COMPOSED",
        hasAudio: true,
        hasVideo: true,
        recordingLayout:"BEST_FIT",
        resolution: this.RESOLUTION,
      }
      this.$store.dispatch("requestStartRecording", recReq)
      .then(response => {
        console.log("the then in startRecoding()...")
        console.log(response)
      }).catch(error => {
        console.log("the error in startRecoding()...")	
        console.log(error)	
			})
    },
    insertVideoUrlAndCloseStreaming() {
      console.log("insertVideoUrlAndCloseStreaming() RUN!!!")
      this.$store.dispatch('requestInsertVideoUrl', { videoId: this.videoId, videoUrl: this.recordURL })
      .then((response) => {
        console.log("the then in insertVideoUrl()...")
        console.log(response)
        this.$store.dispatch('requestCloseVideo', this.videoId)
        .then(res => {
          this.$store.dispatch('requestSetDefaultForOpenvidu')
          console.log(res)
        }).catch((error) => {
          console.log(error)
        })
      }).catch((error) => {
        console.log("the error in insertVideoUrl()...")
        console.log(error)
      })
    },
    updateVideoInfo() {
      this.$store.dispatch('requestGetRoomDetail', this.videoId)
      .then((response) => {
        console.log(response)
        this.videoDescription = response.data.videoDescription
        this.category = response.data.categoryRes.categoryName
        this.videoTitle = response.data.videoTitle
        this.startTime = response.data.startTime
        this.hit = response.data.hit
      })
    },
  },
  mounted() {
    this.videoId = this.$route.params.videoId
    this.$store.dispatch('requestGetRoomDetail', this.videoId)
    .then((response) => {
      console.log(response)
      this.videoDescription = response.data.videoDescription
      this.category = response.data.categoryRes.categoryName
      this.videoTitle = response.data.videoTitle
      this.startTime = response.data.startTime
      this.hit = response.data.hit
    })
    if(this.mainStreamManager != undefined) {
      this.mainStreamManager.addVideoElement(this.$refs.myVideo)
      this.startRecoding()
    }
    this.startTimer()
    this.addEventForChat()
    this.addEventForJoinAndExit()
  },
  beforeRouteLeave(to, from, next) {
    this.$store.dispatch("requestEndRecording", { ovSessionId: this.ovSessionId })
    .then(response => {
      console.log("the then in endRecoding()...")
      console.log(response)
      this.recordURL = response.data.url
      console.log("저장된 URL: " + this.recordURL)
      if(this.isRecord) this.insertVideoUrlAndCloseStreaming()
      else {
        this.$store.dispatch('requestCloseVideo', this.videoId)
        .then(res => {
          console.log(res)
          this.$store.dispatch('requestLeaveSession')
        }).catch((error) => {
          console.log(error)
        })
      }
      next()
    }).catch(error => {
      console.log("the error in endRecoding()...")	
      console.log(error)	
		}) 
  },
  watch: {
    mainStreamManager: function(val, oldVal) {
      if(this.mainStreamManager != undefined) {
        console.log("MAIN STREAM MANAGER: WATCH CALL...")
        this.mainStreamManager.addVideoElement(this.$refs.myVideo)
      }
    }
  },
  computed: {
    ...mapGetters(['loginUser', 'ovSessionId', 'ovToken', 'OV', 'ovSession', 'audioDevices', 'videoDevices', 'createdVideoData', 'mainStreamManager', 'subscribers', 'RESOLUTION']),
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