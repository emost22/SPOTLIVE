<template>
  <div>
    <img :src="video.thumbnailUrl" @click="RoomDetail">
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
    RoomDetail: function () {
      this.$router.push({ name: 'RoomDetail', query: { videoId : this.video.videoId } })
    },
  },
  computed: {
  },
}
</script>

<style>
</style>