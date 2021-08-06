<template>
  <div>
    <vue-glide
      v-if="videos.length"
      class="glider_track"
      data-glide-el="track"
      ref="carousel"
      type="carousel"
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
.glide{position:relative;width:100%;-webkit-box-sizing:border-box;box-sizing:border-box}.glide *{-webkit-box-sizing:inherit;box-sizing:inherit}.glide__slides,.glide__track{overflow:hidden}.glide__slides{position:relative;width:100%;list-style:none;-webkit-backface-visibility:hidden;backface-visibility:hidden;-webkit-transform-style:preserve-3d;transform-style:preserve-3d;-ms-touch-action:pan-Y;touch-action:pan-Y;padding:0;white-space:nowrap;display:-webkit-box;display:-ms-flexbox;display:flex;-ms-flex-wrap:nowrap;flex-wrap:nowrap;will-change:transform}.glide__slide,.glide__slides—dragging{-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}.glide__slide{width:100%;height:100%;-ms-flex-negative:0;flex-shrink:0;white-space:normal;-webkit-touch-callout:none;-webkit-tap-highlight-color:transparent}.glide__slide a{-webkit-user-select:none;user-select:none;-webkit-user-drag:none;-moz-user-select:none;-ms-user-select:none}.glide__arrows,.glide__bullets{-webkit-touch-callout:none;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}.glide—rtl{direction:rtl}
</style>