<template>
  <div class="container px-0">
    <div class="col">
      <div class="card" @click="RoomDetail" style="height:200px; width:400px;">
        <img src="~@/assets/icon-profile.png" style="height:300px;">
        <div class="card-body" style="overflow:hidden;">
          <p class="card-title fw-bold text-start text-nowrap overflow-hidden">{{ video.videoTitle }} 제목</p>
          <p class="card-title text-start">
            {{ video.user.profileNickname }} 송출자
            {{ video.videoDescription }}
            {{ video.mode }}
          </p> 
        </div>
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
      if (localStorage.getItem('jwt')) {
        this.$router.push({ name: 'RoomDetail', query: { video: this.video } })
      } else {
        this.$router.push({name: 'Login'})
      }
    },
  },
  computed: {
  },
}
</script>

<style>

</style>