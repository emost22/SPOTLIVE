<template>
  <div class="bgcolor-deep-grey navside">
    <nav class="nav flex-column">
      <li class="nav-item side-item">
        <div v-if="!sidebarOpen"><img src="~@/assets/icon-sidebar.png" class="sideiconimg"></div>
        <div v-if="sidebarOpen"><img src="~@/assets/icon-sidebar-back.png" class="sideiconimg"></div>
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
.sideiconimg {
  width: 15px;
  height: 15px;
  margin: 5px;
}
.navside {
  width: 45px;
  display: block;
  padding: 5px;
}
.side-item {
  display: flex;
  flex-direction: column;
  justify-content: center;
}
</style>