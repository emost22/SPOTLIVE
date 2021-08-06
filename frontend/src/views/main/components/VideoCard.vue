<template>
  <div>
    <div class="card" @click="RoomDetail" style="height:150px; width:400px;">
      <img :src="video.thumbnailUrl" style="height:100px;">
      <div class="card-body" style="overflow:hidden;">
        <p class="card-title fw-bold text-start text-nowrap overflow-hidden">{{ video.videoTitle }}</p>
        <p class="card-title text-start">
          {{ video.thumbnailUrl }}
          {{ video.user.profileNickname }} 
          {{ video.user.profileImageUrl }} 
          {{ video.user.accountEmail }} 
          <!-- 사용자 pk -->
          <!-- {{ video.videoDescription }}
          {{ video.mode }}
          
          {{ video.videoLength }}
          {{ video.startTime }}
          {{ video.isLive }}
          {{ video.hitLive }}
          {{ video.hit }} -->
        </p> 
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "VideoCard",
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