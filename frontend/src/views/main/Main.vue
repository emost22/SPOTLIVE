<template>
  <div class="txtcolor-white">
    <MainSidebar/>
    <div>
      상단 카테고리 추천
      <VideoCarousel :videos="top_videos"/>
    </div>
    <div>
      카테고리 버튼
      <button>뮤지컬</button>
      <button>뮤지컬</button>
      <button>뮤지컬</button>
      <button>뮤지컬</button>
      <button>뮤지컬</button>
      <button>뮤지컬</button>
      <button>뮤지컬</button>
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

export default {
  name: "Main",
  components: {
    MainSidebar,
    VideoCarousel,
    VideoGlide,
  },
  data: function () {
    return {
      top_videos: [],
      top_videos: [],
      top_videos: [],
      top_videos: [],
      top_videos: [],
      top_videos: [],
    }
  },
  methods: {
    getRecommend: function () {
    },
  },
  created: function () {
    this.getRecommend()
  },
  computer: function () {
  },
}
</script>

<style>

</style>