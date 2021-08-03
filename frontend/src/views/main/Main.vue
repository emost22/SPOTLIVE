<template>
  <div class="txtcolor-white">
    <MainSidebar/>
    <div>
      상단 카테고리 추천
      <!-- npm install -S vue-carousel-3d -->
      <VideoCarousel :videos="top_videos"/>
    </div>
    <div>
      카테고리 버튼
      <FilterGlide :filters="filter_btns"/>
    </div>
    <div>
      추천 5개의 줄
      <VideoGlide :videos="top_videos"/>
      <VideoGlide :videos="top_videos"/>
      <VideoGlide :videos="top_videos"/>
      <VideoGlide :videos="top_videos"/>
      <VideoGlide :videos="top_videos"/>
    </div>
  </div>
</template>

<script>
import MainSidebar from '@/views/main/components/MainSidebar.vue'
import VideoCarousel from '@/views/main/components/VideoCarousel.vue'
import VideoGlide from '@/views/main/components/VideoGlide.vue'
import FilterGlide from '@/views/main/components/FilterGlide.vue'

export default {
  name: "Main",
  components: {
    MainSidebar,
    VideoCarousel,
    VideoGlide,
    FilterGlide,
  },
  data: function () {
    return {
      top_videos: [],
      filter_btns: [],
      bottom_communication_videos: [],
      bottom_show_videos: [],
      bottom_ad_videos: [],
      bottom_views_videos: [],
      bottom_follow_videos: [],
    }
  },
  methods: {
    getTopVideos: function () {
    },
    getFilterBtn: function () {
    },
    getBottomVideos: function () {
    },

  },
  created: function () {
    this.getTopVideos()
    this.getFilterBtn()
    this.getBottomVideos()
  },
  computed: function () {
  },
}
</script>

<style>

</style>