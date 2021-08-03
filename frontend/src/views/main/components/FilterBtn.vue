<template>
  <div class="container px-0">
    <div class="col">
      <button @click="Filter">{{ filter.title }} 버튼 </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "FilterBtn",
  props: {
    filter: {
      type: Object,
      required: true
    },
  },
  methods: {
    Filter: function () {
    },
  },
  computed: {
  },
}
</script>

<style>

</style>