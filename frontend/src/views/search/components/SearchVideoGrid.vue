<template>
  <div class="search-grid-box">
    <div v-if="videos.length">
      <div
        v-for="(video, idx) in videos"
        :key="idx">
        <SearchVideoCard
          :video="video"
        />
      </div>
    </div>
    <div v-if="!videos.length">
      <p class="txtcolor-white-nyellow main-title">해당 검색 결과가 존재하지 않습니다.</p>
    </div>
  </div>
</template>

<script>
import SearchVideoCard from '@/views/search/components/SearchVideoCard.vue'

export default {
  name: "SearchVideoGrid",
  props: {
    videos: {
      type: Array,
      required: true
    }
  },
  components: {
    SearchVideoCard,
  },
  methods: {
  }
}
</script>

<style>
.search-grid-box {
  display: flex;
  flex-direction: row;
  margin-top: 50px;
}
</style>