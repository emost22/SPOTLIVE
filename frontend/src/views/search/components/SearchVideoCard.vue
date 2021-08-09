<template>
  <div class="card search-card-box">
    <div 
      class="search-card-img-box" 
      style="background-image: url('video.thumbnailUrl');"
      @click="goRoomDetail"  
    >
      <div class="live-badge" v-if="video.isLive"></div>
      <!-- <img :src="video.thumbnailUrl" class="search-card-img-thumbnail"> -->
      <div>{{ video.videoLength }}</div>
      <!-- {{ video.startTime }} -->
    </div>
    
    <div class="search-card-info-box" style="overflow:hidden;">
      <div>
        <img :src="video.user.profileImageUrl" class="search-card-img-profile" @click="goProfile">
      </div>
      <div>
        <p class="text-nowrap overflow-hidden fw-bold search-card-info-detail">
          {{ video.videoTitle }}
        </p>
        <p class="search-card-info-detail"> {{ video.user.profileNickname }} </p> 
      </div>           
    </div>
  </div>
</template>

<script>
export default {
  name: "SearchVideoCard",
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
.search-card-box {
  width:300px;
  height:225px;
}
.search-card-img-box {
  width:300px;
  height:187.5px;
  overflow:hidden;
  margin:0;
}
.search-card-img-thumbnail {
  width:100%;
  height:100%;
  object-fit:cover;
}
.search-card-info-box {
  width:300px;
  height:37.5px;
  display: flex;
  flex-direction: row;
}
.search-card-img-profile {
  width: 40px;
  height: 40px;
  border-radius: 100%;
  margin-left: 10px;
}
.search-card-info-detail {
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
</style>