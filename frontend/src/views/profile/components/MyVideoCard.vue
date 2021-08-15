<template>
  <div class="card my-video-card-box">
    <div 
      class="my-video-card-img-box" 
      v-bind:style="{ backgroundImage: 'url(' + video.thumbnailUrl + ')' }"
      @click="goRoomDetail"  
    >
      <!-- <div class="time-badge" v-if="!video.isLive">{{ Number(video.videoLength)/60 }}m</div> -->
      <div v-if="inMyProfile" @click="deleteReplayVideo" type="button" class="btn-close"></div>
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
  methods: {
    goRoomDetail() {
      if(this.video.isLive) this.$router.push({ name: 'RoomDetailForGuest', params: { videoId : this.video.videoId } })
      else this.$router.push({ name: 'RoomDetailForReplay', params: { videoId : this.video.videoId } })
    },
    deleteReplayVideo() {
      // videoResList 내 동영상 삭제 axios
    },
  },
  computed: {
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
</style>