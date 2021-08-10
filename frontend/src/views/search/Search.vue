<template>
  <div class="search-box"> 
    <div class="txtcolor-white-ngreen main-title"> '{{ input }}' 에 대한 검색 결과입니다  </div>
    <SearchVideoGrid :videos="search_videos"/>
  </div>
</template>

<script>
import SearchVideoGrid from '@/views/search/components/SearchVideoGrid.vue'

export default {
  name: "Search",
  data: function () {
    return {
      input: this.$route.query.input,
      search_videos: []
    }
  },
  components: {
    SearchVideoGrid,
  },
  created: function () {
    this.getSearchs(this.input, 0, 20)
  },
  methods: {
    getSearchs(keywordValue, pageValue, sizeValue) {
      this.$store.dispatch('requestGetSearchVideos', { 
        keywordValue: keywordValue,
        pageValue: pageValue,
        sizeValue: sizeValue,
      })
      .then((response) => {
        console.log("getGetSearchVideos() SUCCESS!!")
        console.log(response.data)
        this.search_videos = response.data.videoResList
      })
      .catch((error) => {
        console.log(error)
      })
    },
  },
  computed: {
    // input 결과 값 지속적으로 관찰
  },
}
</script>

<style>
.search-box {
  margin: 50px;
}
</style>