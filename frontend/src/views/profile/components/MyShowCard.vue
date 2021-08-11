<template>
  <div class="carousel-card-box">
    <img :src="show.posterUrl" @click="goShowDetail" class="carousel-card-img">
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
  created:function() {
    console.log(this.show.showInfoId)
    // console.log(this.show.showInfoDescription)
    // console.log(this.show.price)
    // console.log(this.show.runningTime)
    // console.log(this.show.showInfoTitle)
  },
  methods: {
    goShowDetail: function () {
      if (this.inMyProfile) {
        // 내 프로필인 경우 ShowDetailDialog.vue
      } else {
        // 타인 프로필인 경우 ShowReservationDialogInProfile.vue
      }      
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