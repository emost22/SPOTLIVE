<template>
  <carousel-3d 
    v-if="shows.length"
    :width="200" 
    :height="300"
    :autoplay="false"
    :autoplayTimeout="1200"
    :space="300"
    :display="5"
  >
    <slide 
      v-for="(show, i) in shows" 
      :index="i" 
      :key="i"
    >
      <template slot-scope="{ index, isCurrent, leftIndex, rightIndex }">
          <MyShowCard
            :data-index="index" 
            :class="{ showCurrent: isCurrent, showOnLeft: (leftIndex >= 0), showOnRight: (rightIndex >= 0) }" 
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
  }
}
</script>

<style>
.showCurrent{
  border: 2px solid #F84ABF;
  box-shadow: 
    0 0 9px #F84ABF,
    0 0 12px #F84ABF,
    0 0 20px #F84ABF,
    0 0 30px #F84ABF,
    0 0 50px #F84ABF;
}
.showOnLeft{
  border: 2px solid #04F7CA;
  box-shadow: 
    0 0 9px #04F7CA,
    0 0 12px #04F7CA,
    0 0 20px #04F7CA,
    0 0 30px #04F7CA,
    0 0 50px #04F7CA;
}
.showOnFinalLeft{
  border: 2px solid #C752FE;
  box-shadow: 
    0 0 9px #C752FE,
    0 0 12px #C752FE,
    0 0 20px #C752FE,
    0 0 30px #C752FE,
    0 0 50px #C752FE;
}
.showOnRight{
  border: 2px solid #FEF279;
  box-shadow: 
    0 0 9px #FEF279,
    0 0 12px #FEF279,
    0 0 20px #FEF279,
    0 0 30px #FEF279,
    0 0 50px #FEF279;
}
.showOnFinalRight{
  border: 2px solid #F84ABF;
  box-shadow: 
    0 0 9px #F84ABF,
    0 0 12px #F84ABF,
    0 0 20px #F84ABF,
    0 0 30px #F84ABF,
    0 0 50px #F84ABF;
}
</style>