<template>
  <div class="bgcolor-deep-grey nav-sidebar">
    <nav class="nav flex-column">
      <li class="nav-item side-item">
        <div v-if="!sidebarOpen"><img src="~@/assets/icon-sidebar.png" class="sidebar-icon-img"></div>
        <div v-if="sidebarOpen"><img src="~@/assets/icon-sidebar-back.png" class="sidebar-icon-img"></div>
      </li>
      <ul class="navbar-nav" v-for="(following, idx) in followings" :key="idx">
        <FollowingList
          :following="following"
        />
      </ul>
    </nav>
  </div>
</template>

<script>
import FollowingList from '@/views/main/components/FollowingList.vue'

export default {
  name: "MainSidebar",
  components: {
    FollowingList,
  },
  data: function () {
    return {
      followings: [],
      sidebarOpen: true,
    }
  },
  methods: {
    getFollowings: function () {
    },
  },
  created: function () {
    this.getFollowings()
  },
  computed: function () {
  },
}
</script>

<style>
.nav-sidebar {
  width: 65px;
  display: block;
  padding: 5px;
}
.sidebar-item {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
.sidebar-icon-img {
  width: 15px;
  height: 15px;
  margin: 5px;
}
</style>