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
export default {
  name: "VideoGlideCard",
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
      if(this.video.isLive) this.$router.push({ name: 'RoomDetailForGuest', params: { videoId : this.video.videoId } })
      else this.$router.push({ name: 'RoomDetailForReplay', params: { videoId : this.video.videoId } })
    },
    goProfile() {
      this.$router.push({ name: 'Profile', query: { profileId : this.video.user.accountEmail } })
      this.$router.go()
    },
  },
  computed: {
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