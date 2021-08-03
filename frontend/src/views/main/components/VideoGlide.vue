<template>
  <div class="container">
    <div class="row">
      <vue-glide
        v-if="videos.length"
        class="glide__track"
        data-glide-el="track"
        ref="slider"
        type="slider"
        :autoplay="false"
        :breakpoints="{10000: {perView: 9}, 4000: {perView: 7}, 3000: {perView: 6}, 1500: {perView: 5}, 1200: {perView: 4}, 991: {perView: 3}, 800: {perView: 2}}"
      >
        <vue-glide-slide
          v-for="(video, idx) in videos"
          :key="idx">
          <VideoCard
            :video="video"
          />
        </vue-glide-slide>
      </vue-glide>
    </div>
  </div>
</template>

<script>
import { Glide, GlideSlide } from 'vue-glide-js'
import VideoCard from '@/views/main/components/VideoCard.vue'

export default {
  name: "VideoGlide",
  props: {
    videos: {
      type: Array,
      required: true
    }
  },
  components: {
    [Glide.name]: Glide,
    [GlideSlide.name]: GlideSlide,
    VideoCard,
  },
  methods: {
  }
}
</script>

<style>

</style>