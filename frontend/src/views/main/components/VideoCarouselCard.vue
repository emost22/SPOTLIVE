<template>
  <div class="carousel-card-box">
    <img :src="video.thumbnailUrl" class="carousel-card-img" @click="goRoomDetail" >
  </div>
</template>

<script>
export default {
  name: "VideoCarouselCard",
  props: {
    video: {
      type: Object,
      required: true
    },
  },
  methods: {
    goRoomDetail: function () {
      this.$router.push({ name: 'RoomDetail', params: { videoId : this.video.videoId } })
    },
  },
  computed: {
  },
}
</script>

<style>
.carousel-card-box {
  background-color: none;
  width: 100%;
  height: 100%;
  overflow:hidden;
}
.carousel-card-img {
  min-width: 100%;
  min-height: 100%;
  border-color: none;
}
</style>