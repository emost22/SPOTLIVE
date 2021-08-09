<template>
  <div>
    <div 
      v-for="(video, idx) in videos"
      :key="idx">
      <SearchVideoCard
        :video="video"
      />
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
</style>