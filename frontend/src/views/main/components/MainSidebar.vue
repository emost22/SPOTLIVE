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
      <ul class="navbar-nav" v-for="(following, idx) in following_list" :key="idx">
        <FollowingList
          :following="following"
          :open="open"
        />
      </ul>
    </nav>
  </div>
</template>

<script>
import { mapGetters } from "vuex"
import FollowingList from '@/views/main/components/FollowingList.vue'

export default {
  name: "MainSidebar",
  components: {
    FollowingList,
  },
  data: function () {
    return {
      following_list: [],
      open: false,
    }
  },
  methods: {
    getFollowingList() {
      this.$store.dispatch('requestGetFollowingList')
      .then((response) => {
        console.log("getFollowingList() SUCCESS!!")
        console.log(response.data)
        this.following_list = response.data
      })
      .catch((error) => {
        console.log(error)
      })
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
    this.getFollowingList()
  },
  computed: {
    ...mapGetters(['loginUser', 'isLogin']),
  },
}
</script>

<style>
.open-sidebar {
  min-width: 300px;
  max-width: 300px;
  display: block;
  padding: 5px;
}
.close-sidebar {
  min-width: 65px;
  max-width: 65px;
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