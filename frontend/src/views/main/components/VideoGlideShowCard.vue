<template>
  <div>
    <div class="card glide-card-box">
      <div 
        class="glide-card-img-box" 
        v-bind:style="{ backgroundImage: 'url(' + video.thumbnailUrl + ')' }"
        @click="goRoomDetail"  
      >
        <div class="live-badge bdcolor-bold-npink" v-if="video.isLive"></div>
        <div class="time-badge" v-if="!video.isLive">{{ videoLength }}</div>
        <!-- {{ video.startTime }} -->
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
    }
  },
  created: function() {
    this.getVideoLength()
  },
  methods: {
    getVideoLength() {
      var seconds = Number(this.video.videoLength)
      var min = parseInt((seconds%3600)/60) < 10 ? '0'+ parseInt((seconds%3600)/60) : parseInt((seconds%3600)/60)
      var sec = seconds % 60 < 10 ? '0'+seconds % 60 : seconds % 60
      this.videoLength = min+":" + sec
    },
    goRoomDetail() {
      console.log('공연용 로그인 정보 확인')
      const reservations = this.loginUser.reservationResList
      console.log(this.video)
      // 비디오의 showInfoRes의 showInfoId
      reservations.forEach((reservation) => { 
        console.log(reservation.timetableFindByReservationRes.dateTime)
        console.log(reservation.timetableFindByReservationRes.showInfoRes.showInfoId)
      })
      // 내 예약내역의 showInfoId
      // 내 예약내역의 dateTime과 now 시간의 30분 전후 이면 입장 가능 

      // 입장불가1 같은 showInfoId이지만 dateTime이 타임테이블 정확히 다른 경우 (다른 시간대의 공연에 예약했습니다) 
      // 노란 팝업창 off canvas
      
      // 입장불가2 같은 showInfoId가 아예 없는 경우 (예약 모달)
      // ShowReservationDialogInMain.vue 
      // 예약 모달을 띄우고 데이터 showInfoId로 axios로 요청해서 데이터 받기 
      
      if(this.video.isLive) this.$router.push({ name: 'RoomDetailForGuest', params: { videoId : this.video.videoId } })
      else this.$router.push({ name: 'RoomDetailForReplay', params: { videoId : this.video.videoId } })
    },
    goProfile() {
      this.$router.push({ name: 'Profile', query: { profileId : this.video.user.accountEmail } })
      this.$router.go()
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
.live-badge{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 60px;
  height: 30px;
  border-radius: 15px;
  background-color: none;
  border: none;
  background-image: url('~@/assets/icon-live-badge.png');
  background-repeat: no-repeat;
  background-position: center;
  margin: 10px;
}
.time-badge{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 60px;
  height: 30px;
  border-radius: 15px;
  background-color: #242424;
  color: #FFFFFF;
  border: none;
  margin: 10px;
}
</style>