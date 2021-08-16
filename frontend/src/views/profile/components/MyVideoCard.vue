<template>
  <div class="card my-video-card-box">
    <div 
      class="my-video-card-img-box" 
      v-bind:style="{ backgroundImage: 'url(' + video.thumbnailUrl + ')' }"
      @click="goRoomDetail"  
    >
      <div class="d-flex flex-row justify-content-between">
        <div class="my-video-live-badge bdcolor-bold-npink" v-if="video.isLive"></div>
        <div class="my-video-time-badge" v-if="!video.isLive">{{ videoLength }}</div>
        <div class="btn-close my-video-delete-badge" v-if="inMyProfile" @click="deleteReplayVideo" type="button"></div>
      </div>
    </div>
    <div class="my-video-card-info-box main-bgcolor-black txtcolor-white" style="overflow:hidden;">
      <div>
        <p class="text-nowrap overflow-hidden fw-bold my-video-card-info-detail">
          {{ video.videoTitle }}
          {{ video.categoryRes.categoryName }}
        </p>
      </div>           
    </div>
  </div>
</template>

<script>
export default {
  name: "MyVideoCard",
  props: {
    video: {
      type: Object,
      required: true
    },
    inMyProfile: {
      type: Boolean,
      required: true
    }
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
      // var min = parseInt((seconds%3600)/60) < 10 ? '0'+ parseInt((seconds%3600)/60) : parseInt((seconds%3600)/60)
      var min = parseInt(seconds / 60) < 10 ? '0'+ parseInt(seconds / 60) : parseInt(seconds / 60)
      var sec = seconds % 60 < 10 ? '0'+seconds % 60 : seconds % 60
      this.videoLength = min+":" + sec
    },
    goRoomDetail() {
      if(this.video.isLive) this.$router.push({ name: 'RoomDetailForGuest', params: { videoId : this.video.videoId } })
      else this.$router.push({ name: 'RoomDetailForReplay', params: { videoId : this.video.videoId } })
    },
    deleteReplayVideo() {
      // videoResList 내 동영상 삭제 axios
    },
  },
}
</script>

<style>
.my-video-card-box {
  width:300px;
  height:250px;
  border: none;
  border-radius: 0%;
}
.my-video-card-img-box {
  width:300px;
  height:187.5px;
  overflow:hidden;
  background-size: cover;
  margin:0;
  cursor: pointer;
}
.my-video-card-info-box {
  width:300px;
  height:62.5px;
  display: flex;
  flex-direction: row;
  align-items: center;
  cursor: pointer;
}
.my-video-card-img-profile {
  width: 40px;
  height: 40px;
  border-radius: 100%;
  margin-left: 10px;
}
.my-video-card-info-detail {
  margin-bottom: 0;
  margin-left: 10px;
  text-align: start;
}
.my-video-live-badge{
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
.my-video-time-badge{
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
.my-video-delete-badge {
  display: flex;
  justify-content: end;
  margin-top: 10px;
  margin-right: 10px;
}
</style>