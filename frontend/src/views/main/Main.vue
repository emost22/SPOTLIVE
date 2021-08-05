<template>
  <div class="d-flex flex-row">
    <MainSidebar/>
    <div class="main-video">
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
        <p> 소통용 영상</p>
        <VideoGlide :videos="bottom_communication_videos"/>
        <p> 공연용 영상</p>
        <VideoGlide :videos="bottom_show_videos"/>
        <p> 홍보용 영상</p>
        <VideoGlide :videos="bottom_ad_videos"/>
        <p> 조회순 높은 영상</p>
        <VideoGlide :videos="bottom_views_videos"/>
        <p> 내 구독자들의 영상</p>
        <VideoGlide :videos="bottom_follow_videos"/>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
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
  computed: {
    ...mapGetters(['loginUser', 'isLogin']),
  },
}
</script>

<style>
.main-video {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
}
</style>