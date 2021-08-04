<template>
  <div>
    <router-link class="nav-link" :to="{ name: 'Profile', param: { user_id : following.id } }">
      <li class="nav-item">
        <div><img src="~@/assets/icon-streaming.png" class="iconimg"></div>
      </li>
    </router-link>
  </div>
</template>

<script>
export default {
  name: "FollowingList",
  props: {
    following: {
      type: Object,
      required: true
    },
  },
  data: function () {
  },
  methods: {
  },
}
</script>

<style>

</style>