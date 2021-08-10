<template>
  <div class="my-video-grid-box container-fluid">
    <div
      class="d-flex flex-row flex-wrap row-cols-auto" 
      v-if="videos.length"
    >
      <div
        class="col mx-4"
        v-for="(video, idx) in videos"
        :key="idx">
        <MyVideoCard
          :video="video"
        />
      </div>
    </div>
    <div v-if="!videos.length">
      <p class="txtcolor-white-nyellow main-title">저장된 동영상이 존재하지 않습니다.</p>
    </div>
  </div>
</template>

<script>
import MyVideoCard from '@/views/profile/components/MyVideoCard.vue'

export default {
  name: "MyVideo",
  props: {
    videos: {
      type: Array,
      required: true
    }
  },
  components: {
    MyVideoCard,
  },
  methods: {
  }
}
</script>

<style>
.my-video-grid-box {
  display: flex;
  flex-direction: row;
}
</style>