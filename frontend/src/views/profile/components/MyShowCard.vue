<template>
  <div>
    <img :src="show.posterUrl" @click="ShowDetail" >
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
</style>