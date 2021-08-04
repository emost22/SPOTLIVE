<template>
  <div>    
    <li class="nav-item">
      <router-link class="nav-link" :to="{ name: 'Profile', param: { userId : following.id } }">
        <div>
          <div><img src="~@/assets/icon-streaming.png" class="sidebar-nav-img"></div>
          <div v-if="open" class="sidebar-nav-name">{{following.Nickname}}</div>
        </div>
      </router-link>
    </li>
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
    open: {
      type: Boolean,
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
.sidebar-nav-img {
  width: 40px;
  height: 40px;
  border-radius: 100%;
}
.sidebar-nav-name{
  font-size: 10px;
}
</style>