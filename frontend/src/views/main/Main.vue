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
      <!-- 버튼 글라이드 만들기 FilterGlide.vue FilterBtn.vue-->
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
      filter_btn: [],
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