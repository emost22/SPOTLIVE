<template>
  <div class="container">
    <div class="row">
      <vue-glide
        v-if="filters.length"
        class="glide__track"
        data-glide-el="track"
        ref="slider"
        type="slider"
        :autoplay="false"
        :breakpoints="{10000: {perView: 8}, 4000: {perView: 7}, 3000: {perView: 6}, 1500: {perView: 5}, 1200: {perView: 4}, 991: {perView: 3}, 800: {perView: 2}}"
      >
        <vue-glide-slide
          v-for="(filter, idx) in filters"
          :key="idx">
          <FilterBtn
            :filter="filter"
          />
        </vue-glide-slide>
      </vue-glide>
    </div>
  </div>
</template>

<script>
import { Glide, GlideSlide } from 'vue-glide-js'
import FilterBtn from '@/views/main/components/FilterBtn.vue'

export default {
  name: "FilterGlide",
  props: {
    filters: {
      type: Array,
      required: true
    }
  },
  components: {
    [Glide.name]: Glide,
    [GlideSlide.name]: GlideSlide,
    FilterBtn,
  },
  methods: {
  }
}
</script>

<style>

</style>