<template>
  <div class="main-div">
    <MainSidebar/>
    <div class="main-video">
      <div>
        <VideoCarousel :videos="carousel_videos"/>
      </div>
      <div>
        <FilterGlide 
          :filters="filter_buttons"
          @categoryId="getFilterCategoryId"
        />
      </div>
      <div>
        <p class="txtcolor-white-ngreen main-title"> 홍보용 영상</p>
        <VideoGlide :videos="ad_videos"/>
        <p class="txtcolor-white-npink main-title"> 공연용 영상</p>
        <VideoGlideShow :videos="show_videos"/>
        <p class="txtcolor-white-nyellow main-title"> 소통용 영상</p>
        <VideoGlide :videos="talk_videos"/>
        <p class="txtcolor-white-npurple main-title"> 실시간 시청자 높은 영상</p>
        <VideoGlide :videos="live_videos"/>
        <p class="txtcolor-white-ngreen main-title"> 다시보기 조회순 높은 영상</p>
        <VideoGlide :videos="replay_videos"/>
        <p class="txtcolor-white-npink main-title"> 내가 팔로우 하는 사람들의 최신 영상</p>
        <VideoGlide :videos="follow_videos"/>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import MainSidebar from '@/views/main/components/MainSidebar.vue'
import VideoCarousel from '@/views/main/components/VideoCarousel.vue'
import VideoGlide from '@/views/main/components/VideoGlide.vue'
import VideoGlideShow from '@/views/main/components/VideoGlideShow.vue'
import FilterGlide from '@/views/main/components/FilterGlide.vue'

export default {
  name: "Main",
  components: {
    MainSidebar,
    VideoCarousel,
    VideoGlide,
    VideoGlideShow,
    FilterGlide,
  },
  data: function () {
    return {
      carousel_videos: [],
      filter_buttons: [],
      filter_category_id: '',
      ad_videos: [],
      show_videos: [],
      talk_videos: [],
      live_videos: [],
      replay_videos: [],
      follow_videos: [],
    }
  },
  created: function () {
    this.getCarouselVideos()
    this.getFilterButtons()
    // this.getAdVideos(0, 20)
    // this.getShowVideos(0, 20)
    // this.getTalkVideos(0, 20)
    // this.getLiveVideos(0, 20)
    // this.getReplayVideos(0, 20)
    // this.getFollowVideos(0, 20)
    this.getTotalMainVideos(0, 20)
  },
  methods: {
    getCarouselVideos() {
      this.$store.dispatch('requestGetCarouselVideos')
      .then((response) => {
        console.log("getCarouselVideos() SUCCESS!!")
        console.log(response.data)
        this.carousel_videos = response.data
      })
      .catch((error) => {
        console.log(error)
      })
    },

    getFilterButtons() {
      this.$store.dispatch('requestGetFilterButtons')
      .then((response) => {
        console.log("getFilterButtons() SUCCESS!!")
        console.log(response.data)
        this.filter_buttons = response.data
      })
      .catch((error) => {
        console.log(error)
      })
    },
    
    getFilterCategoryId(categoryId) {
      this.filter_category_id = Number(categoryId)
      if (Number(categoryId) == 0) {
        this.getTotalMainVideos(0, 20)
      } else {
        this.getTotalMainVideos(0, 20, this.filter_category_id)
      }
    },

    getTotalMainVideos(pageValue, sizeValue, categoryId) {
      this.$store.dispatch('requestGetTotalMainVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
        categoryId: categoryId,
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
        console.log(this.live_videos)
      }).catch((error) => {
        console.log(error)
      })
    },
    
    getAdVideos(pageValue, sizeValue) {
      this.$store.dispatch('requestGetAdVideos', { 
        pageValue: pageValue,
        sizeValue: sizeValue,
      }).then((response) => {
        console.log("getAdVideos() SUCCESS!!")
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
  computed: {
    ...mapGetters(['loginUser', 'isLogin']),
    // FilterButton.vue에서 클릭 이벤트가 일어나면 카테고리 아이디를 받아서 인자를 넣어보자
  },
}
</script>

<style>
.main-div{
  display: flex;
  align-items: stretch;
  min-height: 100vh;
  min-width: 100vh;
}
.main-video {
  margin-right: 75px;
  margin-left: 75px;
  padding: 0px;
  min-height: 100vh;
  min-width: 100vh;
}
.main-title {
  font-size: 20px;
  text-align: start;
}
</style>