<template>
  <div> 검색결과 {{ input }} </div>
</template>

<script>
export default {
  name: "Search",
  data: function () {
    return {
      input: this.$route.query.input,
    }
  },
  components: {
  },
  methods: {
    // setToken: function () {
    //   const token = localStorage.getItem('jwt')
    //   const config = {
    //     Authorization: `JWT ${token}`
    //   }
    //   return config
    // },
    getSearchs: function () {
    },
  },
  created: function () {
    this.getSearchs()
  },
}
</script>

<style>

</style>