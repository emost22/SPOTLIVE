<template>
  <div class="d-flex flex-row">
    <MainSidebar/>
    <div class="main-video">
      <div>
        상단 카테고리 추천
        <!-- npm install -S vue-carousel-3d -->
        <VideoCarousel :videos="carousel_videos"/>
      </div>
      <div>
        카테고리 버튼
        <FilterGlide :filters="filter_btns"/>
      </div>
      <div>
        <p> 소통용 영상</p>
        <VideoGlide :videos="talk_videos"/>
        <p> 공연용 영상</p>
        <VideoGlide :videos="show_videos"/>
        <p> 홍보용 영상</p>
        <VideoGlide :videos="ad_videos"/>
        <p> 다시보기 조회순 높은 영상</p>
        <VideoGlide :videos="replay_videos"/>
        <p> 실시간 시청자 높은 영상</p>
        <VideoGlide :videos="live_videos"/>
        <p> 내가 팔로우 하는 사람들의 최신 영상</p>
        <VideoGlide :videos="follow_videos"/>
      </div>
    </div>
  </div>
</template>

<script>
import MainSidebar from '@/views/main/components/MainSidebar.vue'
import VideoCarousel from '@/views/main/components/VideoCarousel.vue'
import VideoGlide from '@/views/main/components/VideoGlide.vue'
import FilterGlide from '@/views/main/components/FilterGlide.vue'
import axios from 'axios'

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
      carousel_videos: [],
      filter_btns: [],
      talk_videos: [],
      show_videos: [],
      ad_videos: [],
      replay_videos: [],
      live_videos: [],
      follow_videos: [],
    }
  },
  
  methods: {
    getCarouselVideos: function () {
    },
    getFilterBtn: function () {
    },
    getTalkVideos: function () {
      const SERVER_URL = "http://localhost:8080"
      axios({
        method: "GET",
        url: `${SERVER_URL}/api/main/talk`,
        params: { page: 0, size: 20 },
        // data: {},
        headers: {
          "Content-type": "application/json",
        },
      })
      .then((res) => {
        console.log(res.data.videoResList[0])
        this.talk_videos = res.data.videoResList;
      })
      .catch((err) => {
        console.log(err);
      })
    },
    getShowVideos: function () {
      const SERVER_URL = "http://localhost:8080"
      axios({
        method: "GET",
        url: `${SERVER_URL}/api/main/show`,
        params: { page: 0, size: 20 },
        // data: {},
        headers: {
          "Content-type": "application/json",
        },
      })
      .then((res) => {
        console.log(res.data.videoResList[0])
        this.show_videos = res.data.videoResList;
      })
      .catch((err) => {
        console.log(err);
      })
    }
  },
  created: function () {
    this.getCarouselVideos()
    this.getFilterBtn()
    this.getTalkVideos()
    this.getShowVideos()
  },
  computed: function () {
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