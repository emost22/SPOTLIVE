<template>
  <div>
    <carousel-3d
    v-if="videos.length"
    :autoplay="true"
    :autoplayTimeout="1500"
    :space="300"
    :height="350"
    :width="500"
    >
      <slide 
        v-for="(video, i) in videos" 
        :index="i" 
        :key="i" 
      >
        <template slot-scope="{ index, isCurrent, leftIndex, rightIndex }">
          <VideoCarouselCard
            :data-index="index" 
            :class="{ current: isCurrent, onLeft: (leftIndex >= 0), onRight: (rightIndex >= 0) }" 
            :video="video"
          />
        </template>
      </slide>
    </carousel-3d>
  </div>
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
.carousel-3d-container[data-v-07917306]  {
  padding-top: 50px;
  padding-bottom: 50px;
  min-height: 450px;
  max-height: 450px;
  border: 0 none;
}
.current{
  border: 2px solid #F84ABF;
  box-shadow: 
    0 0 9px #F84ABF,
    0 0 12px #F84ABF,
    0 0 20px #F84ABF,
    0 0 30px #F84ABF,
    0 0 50px #F84ABF;
}
</style>