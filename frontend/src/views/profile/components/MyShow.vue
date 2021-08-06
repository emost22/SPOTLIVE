<template>
  <carousel-3d>
    <slide v-for="(show, i) in shows" :index="i" :key="i">
      <template slot-scope="{ index, isCurrent, leftIndex, rightIndex }">
          <MyShowCard
            :data-index="index" 
            :class="{ current: isCurrent, onLeft: (leftIndex >= 0), onRight: (rightIndex >= 0) }" 
            :show="show"
          />
      </template>
    </slide>
  </carousel-3d>
</template>

<script>
import { Carousel3d, Slide } from 'vue-carousel-3d'
import MyShowCard from '@/views/profile/components/MyShowCard.vue'

export default {
  name: "MyShow",
  props: {
    shows: {
      type: Array,
      required: true
    }
  },
  components: {
    Carousel3d,
    Slide,
    MyShowCard,
  },
  methods: {
    getMyShows() {
      this.$store.dispatch('requestGetMyShows')
      .then((response) => {
        console.log("getMyShows() SUCCESS!!")
        console.log(response.data)
        this.show_videos = response.data
      })
      .catch((error) => {
        console.log(error)
      })
    },
  }
}
</script>

<style>

</style>