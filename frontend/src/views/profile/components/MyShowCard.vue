<template>
  <div class="carousel-card-box">    
    <div 
      class="carousel-card-box"
      data-bs-toggle="modal" 
      data-bs-target="#showDetailModal"
      v-if="inMyProfile"
      @click="goShowDetail" 
    >
      <img :src="show.posterUrl" class="carousel-card-img">
    </div>
    <div 
      class="carousel-card-box"
      data-bs-toggle="modal" 
      data-bs-target="#showReservationInProfileModal"
      v-if="!inMyProfile"
      @click="goShowDetail" 
    >
      <img :src="show.posterUrl" class="carousel-card-img">
    </div>
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
    inMyProfile: {
      type: Boolean,
      required: true
    }
  },
  methods: {
    goShowDetail: function () {
      var showData = {
        showId: this.show.showInfoId,
        title: this.show.showInfoTitle,
        description: this.show.showInfoDescription,
        posterUrl: this.show.posterUrl,
        price: this.show.price,
        runningTime: this.show.runningTime,
        // 공연시간 datetime
      }
      this.$store.dispatch('requestGetShowData', showData)
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