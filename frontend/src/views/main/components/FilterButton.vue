<template>
  <div>
    <button class="filter-btn bgcolor-deep-grey txtcolor-white no-border" @click="clickFilter">{{ filter.categoryName }}</button>
  </div>
</template>

<script>
export default {
  name: "FilterButton",
  props: {
    filter: {
      type: Object,
      required: true
    },
  },
  methods: {
    clickFilter: function () {
    // Main.vue의 axios요청 다시 받아오도록 computed
    },
  },
  computed: {
  },
}
</script>

<style>
.filter-btn {
  width: 110px;
  height: 30px;
  border-radius: 15px;
  margin: 20px;
}
.filter-btn:hover {
  border: 2px solid #04F7CA;
  box-shadow: 
    0 0 9px #04F7CA,
    0 0 12px #04F7CA;
}
</style>