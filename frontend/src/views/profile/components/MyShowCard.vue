<template>
  <div>
    <img :src="show.thumbnailUrl" @click="ShowDetail">
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
    },
  },
}
</script>

<style>
</style>