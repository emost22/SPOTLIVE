<template>
  <div class="carousel-card-box">
    <img :src="show.posterUrl" @click="ShowDetail" class="carousel-card-img">
  </div>
</template>

<script>
export default {
  name: "MyShowCard",
  props: {
    show: {
      type: Object,
      required: true
    },
  },
  methods: {
    ShowDetail: function () {
      // 내 프로필인 경우 ShowDetailDialog.vue
      // 타인 프로필인 경우 ShowReservationDialog.vue
    },
  },
}
</script>

<style>
.carousel-card-box {
  background-color: none;
  width: 100%;
  height: 100%;
  overflow:hidden;
}
.carousel-card-img {
  min-width: 100%;
  min-height: 100%;
  border-color: none;
}
</style>