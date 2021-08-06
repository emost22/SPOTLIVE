<template>
  <carousel-3d>
    <slide v-for="(video, i) in videos" :index="i" :key="i">
      <template slot-scope="{ index, isCurrent, leftIndex, rightIndex }">
          <!-- <img 
            :data-index="index" 
            :class="{ current: isCurrent, onLeft: (leftIndex >= 0), onRight: (rightIndex >= 0) }" 
            src="~@/assets/icon-streaming.png"
          > -->
          <VideoCarouselCard
            :data-index="index" 
            :class="{ current: isCurrent, onLeft: (leftIndex >= 0), onRight: (rightIndex >= 0) }" 
            :video="video"
          />
      </template>
    </slide>
  </carousel-3d>
</template>

<script>
import { Carousel3d, Slide } from 'vue-carousel-3d'
import VideoCarouselCard from '@/views/main/components/VideoCarouselCard.vue'

export default {
  name: "VideoCarousel",
  props: {
    videos: {
      type: Array,
      required: true
    }
  },
  components: {
    Carousel3d,
    Slide,
    VideoCarouselCard,
  },
  methods: {
  }
}
</script>

<style>

</style>