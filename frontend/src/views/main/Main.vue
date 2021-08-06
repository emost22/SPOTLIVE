<template>
  <div>
    <div class="main-div">
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
      ad_videos: [],
      show_videos: [],
      talk_videos: [],
      live_videos: [],
      replay_videos: [],
      follow_videos: [],
    }
  },
  
  methods: {
    getCarouselVideos() {
    },

    getFilterBtns() {
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
        this.show_videos = response.data.showVideoGetRes.videoResList
        this.talk_videos = response.data.talkVideoGetRes.videoResList
        this.live_videos = response.data.liveVideoGetRes.videoResList
        this.replay_videos = response.data.replayVideoGetRes.videoResList
        this.follow_videos = response.data.followVideoGetRes.videoResList
      }).catch((error) => {
        console.log(error)
      })
    },
    
    getAdVideos(pageValue, sizeValue) {
      this.$store.dispatch('requestGetadVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
      }).then((response) => {
        console.log("getadVideos() SUCCESS!!")
        console.log(response.data)
        this.ad_videos = response.data.videoResList
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

    getFollowVideos(pageValue, sizeValue) {
      this.$store.dispatch('requestGetFollowVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
      }).then((response) => {
        console.log("getFollowVideos() SUCCESS!!")
        console.log(response.data)
        this.ad_videos = response.data.videoResList
      }).catch((error) => {
        console.log(error)
      })
    },

  },
  created: function () {
    this.getCarouselVideos()
    this.getFilterBtns()
    this.getAdVideos(0, 20)
    this.getShowVideos(0, 20)
    this.getTalkVideos(0, 20)
    this.getLiveVideos(0, 20)
    this.getReplayVideos(0, 20)
    this.getFollowVideos(0, 20)
    this.getTotalMainVideos(0, 20)
  },
  computed: {
    ...mapGetters(['loginUser', 'isLogin']),
  },
}
</script>

<style>
.main-div{
  display: flex;
  align-items: stretch;
  min-height: 100vh;
}
.main-video {
  width: 100%;
  min-height: 100vh;
  margin: 20px;
}
</style>