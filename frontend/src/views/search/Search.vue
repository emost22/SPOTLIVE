<template>
  <div> 검색결과 {{ input }} {{ search_videos }} </div>
</template>

<script>
export default {
  name: "Search",
  data: function () {
    return {
      input: this.$route.query.input,
      search_videos: []
    }
  },
  components: {
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
        this.search_videos = response.data
        // input 초기화 필요
      })
      .catch((error) => {
        console.log(error)
      })
    },
  },
  computed: {

  },
}
</script>

<style>

</style>