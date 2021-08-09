<template>
  <div>
    <div class="card glide-card-box">
      <div 
        class="glide-card-img-box" 
        style="background-image: url('video.thumbnailUrl');"
        @click="goRoomDetail"  
      >
        <!-- <div class="live-badge" v-if="video.isLive"></div> -->
        <!-- <img :src="video.thumbnailUrl" class="glide-card-img-thumbnail"> -->
        <!-- {{ video.videoLength }} -->
        <!-- {{ video.startTime }} -->
      </div>
      
      <div class="glide-card-info-box" style="overflow:hidden;">
        <div>
          <img :src="video.user.profileImageUrl" class="glide-card-img-profile" @click="goProfile">
        </div>
        <div>
          <p class="text-start text-nowrap overflow-hidden fw-bold">
            {{ video.videoTitle }}
          </p>
          <p class="text-start"> {{ video.user.profileNickname }} </p> 
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
  methods: {
    goRoomDetail() {
      this.$router.push({ name: 'RoomDetail', query: { videoId : this.video.videoId } })
    },
    goProfile() {
      this.$router.push({ name: 'Profile', query: { profileId : this.video.user.accountEmail } })
    },
  },
  computed: {
  },
}
</script>

<style>
.glide-card-box {
  height:300px;
  width:400px;
}
.glide-card-img-box {
  width:380px;
  height:250px;
  overflow:hidden;
  margin:0;
}
.glide-card-img-thumbnail {
  width:100%;
  height:100%;
  object-fit:cover;
}
.glide-card-info-box {
  width:380px;
  height:100px;
  display: flex;
  flex-direction: row;
}
.glide-card-img-profile {
  width: 50px;
  height: 50px;
  border-radius: 100%;
}
.live-badge{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 40px;
  height: 40px;
  background-color: #C4C4C4;
  border: none;
  border-radius: .25rem;
  background-image: url('~@/assets/icon-live-badge.png');
  background-repeat: no-repeat;
  background-position: center;
}
</style>