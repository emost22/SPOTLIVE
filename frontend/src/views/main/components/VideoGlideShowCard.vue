<template>
  <div>
    <div class="card glide-card-box">
      <div 
        class="glide-card-img-box" 
        v-bind:style="{ backgroundImage: 'url(' + video.thumbnailUrl + ')' }"
        @click="goReservationConfirm"  
      >
        <div class="live-badge bdcolor-bold-npink" v-if="video.isLive"></div>
        <div class="time-badge" v-if="!video.isLive">{{ videoLength }}</div>
      </div>
      
      <div class="glide-card-info-box main-bgcolor-black txtcolor-white" style="overflow:hidden;">
        <div>
          <img :src="video.user.profileImageUrl" class="glide-card-img-profile" @click="goProfile">
        </div>
        <div>
          <p class="text-nowrap overflow-hidden fw-bold glide-card-info-detail">
            {{ video.videoTitle }}
          </p>
          <p class="glide-card-info-detail"> {{ video.user.profileNickname }} </p> 
        </div>           
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: "VideoGlideShowCard",
  props: {
    video: {
      type: Object,
      required: true
    },
  },
  data: function() {
    return {
      videoLength: 0, 
      reservation: false,
      alert: false,
    }
  },
  created: function() {
    this.getVideoLength()
  },
  methods: {
    getVideoLength() {
      var seconds = Number(this.video.videoLength)
      var hour = parseInt(seconds / 3600)
      var min = parseInt((seconds % 3600) / 60) < 10 ? '0'+ parseInt((seconds % 3600) / 60) : parseInt((seconds % 3600) / 60)
      var sec = seconds % 60 < 10 ? '0'+seconds % 60 : seconds % 60
      this.videoLength = hour + ":" + min + ":" + sec
    },
    goProfile() {
      this.$router.push({ name: 'Profile', query: { profileId : this.video.user.accountEmail } })
      this.$router.go()
    },
    goRoomDetail() {
      if(this.video.isLive) this.$router.push({ name: 'RoomDetailForGuest', params: { videoId : this.video.videoId } })
      else this.$router.push({ name: 'RoomDetailForReplay', params: { videoId : this.video.videoId } })
    },
    goShowReservationDialogInProfile() {
      let showData = {
        userId: this.video.user.accountEmail,
        profileNickname: this.video.user.profileNickname,
        profileImageUrl: this.video.user.profileImageUrl,
        showId: this.video.showInfoRes.showInfoId,
        title: this.video.showInfoRes.showInfoTitle,
        description: this.video.showInfoRes.showInfoDescription,
        posterUrl: this.video.showInfoRes.posterUrl,
        price: this.video.showInfoRes.price,
        runningTime: this.video.showInfoRes.runningTime,
        dateTime: this.video.timetableRes.dateTime,
        timetableId: this.video.timetableRes.timetableId,
      }
      this.$store.dispatch('requestGetShowData', showData)
      // ShowReservationDialogInMain.vue 
    },
    goAlert() {
      alert("예약된 공연이 아닙니다.") 
      // 노란 팝업창 off canvas "예약된 공연이 아닙니다."
    },
    goReservationConfirm() {
      console.log(this.video)

      const myReservationsList = this.loginUser.reservationResList
      const timetableIdOfAccessVideo = this.video.timetableRes.timetableId

      let isEnter = false
      myReservationsList.forEach(reservation => { 
        if(reservation.timetableFindByReservationRes.timetableId == timetableIdOfAccessVideo) 
          isEnter = true 
      })
      
      if(isEnter) this.goRoomDetail()
      else this.goRoomDetail()
      // this.goAlert()
      // this.goShowReservationDialogInProfile()
    },
  },
  computed: {
    ...mapGetters(['loginUser', ]),
  },
}
</script>

<style>
.glide-card-box {
  width:300px;
  height:250px;
  border: none;
  border-radius: 0%;
}
.glide-card-img-box {
  width:300px;
  height:187.5px;
  overflow:hidden;
  background-size: cover;
  margin:0;
  cursor: pointer;
}
.glide-card-info-box {
  width:300px;
  height:62.5px;
  display: flex;
  flex-direction: row;
  align-items: center;
  cursor: pointer;
}
.glide-card-img-profile {
  width: 40px;
  height: 40px;
  border-radius: 100%;
  margin-left: 10px;
}
.glide-card-info-detail {
  margin-bottom: 0;
  margin-left: 10px;
  text-align: start;
}
</style>