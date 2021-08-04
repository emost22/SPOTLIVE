<template>
  <div class="bgcolor-deep-grey nav-sidebar close-sidebar">
    <nav class="nav flex-column">
      <div >
        <div v-if="!open" @click="openSidebar">
          <img src="~@/assets/icon-sidebar.png" class="sidebar-icon-img">
        </div>
        <div v-if="open" @click="closeSidebar" class="sidebar-close-item">
          <img src="~@/assets/icon-sidebar-back.png" class="sidebar-icon-img">
        </div>
      </div>
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
      open: false,
    }
  },
  methods: {
    getFollowings: function () {
    },
    openSidebar: function () {
      const nav = document.querySelectorAll('.nav-sidebar')[0]
      if (nav.classList.contains('close-sidebar')) {
        nav.classList.remove('close-sidebar')
      } 
      nav.classList.add('open-sidebar')
      this.open = true
    },
    closeSidebar: function () {
      const nav = document.querySelectorAll('.nav-sidebar')[0]
      if (nav.classList.contains('open-sidebar')) {
        nav.classList.remove('open-sidebar')
      } 
      nav.classList.add('close-sidebar')
      this.open = false
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
.open-sidebar {
  width: 300px;
  display: block;
  padding: 5px;
}
.close-sidebar {
  width: 65px;
  display: block;
  padding: 5px;
}
.sidebar-close-item {
  display: flex;
  flex-direction: row-reverse;
  justify-content: end;
  margin: 5px;
}
.sidebar-icon-img {
  width: 15px;
  height: 15px;
  margin: 5px;
}
</style>