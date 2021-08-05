<template>
  <div>
    <div class="d-flex flex-row">
      <MainSidebar/>
      <div class="main-video">
        <div>
          상단 카테고리 추천
          <VideoCarousel :videos="carousel_videos"/>
        </div>
        <div>
          카테고리 버튼
          <FilterGlide :filters="filter_btns"/>
        </div>
        <div class="row">
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
  </div>
</template>

<script>
import { mapGetters } from "vuex";
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
    getCarouselVideos() {
    },
    getFilterBtn() {
    },
    
    getTotalMainVideos(pageValue, sizeValue) {
      this.$store.dispatch('requestGetTotalMainVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
      }).then((response) => {
        console.log("getTotalMainVideos() SUCCESS!!")
        console.log(response.data)

        // 6개의 비디오 GET
        this.ad_videos = response.data.adVideoGetRes.videoResList
        this.follow_videos = response.data.followVideoGetRes.videoResList
        this.replay_videos = response.data.replayVideoGetRes.videoResList
        this.show_videos = response.data.showVideoGetRes.videoResList
        this.talk_videos = response.data.talkVideoGetRes.videoResList
        this.live_videos = response.data.liveVideoGetRes.videoResList
      }).catch((error) => {
        console.log(error)
      })
    },
    
    getTalkVideos(pageValue, sizeValue) {
      this.$store.dispatch('requestGetTalkVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
      }).then((response) => {
        console.log("getTalkVideos() SUCCESS!!")
        console.log(response.data)
        this.talk_videos = response.data.videoResList
      }).catch((error) => {
        console.log(error)
      })
    },

    getShowVideos(pageValue, sizeValue) {
      this.$store.dispatch('requestGetShowVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
      }).then((response) => {
        console.log("getShowVideos() SUCCESS!!")
        console.log(response.data)
        this.show_videos = response.data.videoResList
      }).catch((error) => {
        console.log(error)
      })
    },
    
    /*
    getReplayVideos(pageValue, sizeValue) {
      this.$store.dispatch('requestGetReplayVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
      }).then((response) => {
        console.log("getReplayVideos() SUCCESS!!")
        console.log(response.data)
        this.replay_videos = response.data.videoResList
      }).catch((error) => {
        console.log(error)
      })
    },
    getLiveVideos(pageValue, sizeValue) {
      this.$store.dispatch('requestGetLiveVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
      }).then((response) => {
        console.log("getLiveVideos() SUCCESS!!")
        console.log(response.data)
        this.live_videos = response.data.videoResList
      }).catch((error) => {
        console.log(error)
      })
    },
    getUserVideos(pageValue, sizeValue) {
      this.$store.dispatch('requestGetUserVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
      }).then((response) => {
        console.log("getUserVideos() SUCCESS!!")
        console.log(response.data)
        // follow 리스트는 어디로?
      }).catch((error) => {
        console.log(error)
      })
    },
    */
  },
  created: function () {
    this.getCarouselVideos()
    this.getFilterBtn()
    this.getTalkVideos(0, 20)
    this.getShowVideos(0, 20)
    this.getTotalMainVideos(0, 20);
  },
  computed: {
    ...mapGetters(['loginUser', 'isLogin']),
  },
}
</script>

<style>
.main-video {
  width: 100%;
  margin: 20px;
}
</style>