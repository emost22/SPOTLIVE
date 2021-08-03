<template>
  <!-- v-if="!isLogin" -->
  <div class="bgcolor-deep-grey">
    <nav class="navbar navbar-expand sticky-top">
      <div class="container-fluid">
        <!-- MainSidebar toggle MainSidebar.vue에서 추가 -->
        <router-link class="navbar-brand" :to="{ name: 'Main' }">
          <span class="txtcolor-ngreen logo">SPOT</span>
          <span class="txtcolor-npink logo">LIVE</span>
        <!-- 검색창 input 및 검색버튼 button-->
        </router-link>
        <div class="search"> 
          <input class="bgcolor-mid-deep-grey searchinput" type="text" id="">
          <button class="bgcolor-mid-deep-grey txtcolor-white searchbtn" :disabled="this.validSearch">검색</button>
        </div>        
        <ul class="navbar-nav">
          <!-- <li v-if="!isLogin" class="nav-item"> -->
          <li class="nav-item">
            <div><router-link class="nav-link fw-bold" :to="{ name: 'RoomCreate' }"><img src="~@/assets/icon-streaming.png"></router-link></div>
          </li>
          <li class="nav-item">
            <div><router-link class="nav-link fw-bold" :to="{ name: 'Profile' }"><img src="~@/assets/icon-profile.png"></router-link></div>
          </li>
          <li class="nav-item">
            <div><img src="~@/assets/icon-alarm.png"></div>
          </li>
          <li class="nav-item">
            <div><router-link class="nav-link" @click.native="logout" :to="{ name: 'Login' }"><img src="~@/assets/icon-logout.png" alt=""></router-link></div>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
export default ({
  name: "MainHeader",
  components: {
  },
  data: function () {
    return {
      isLogin: false,
      validSearch: false,
    }
  },methods: {
    logout: function () {
      this.isLogin = false
      this.$store.dispatch('logout', this.isLogin)
      localStorage.removeItem('jwt')
      this.$router.push({ name: 'Login' })
    },
  },
  computed: {
    isLogin: function () {
      return this.$store.getters.isLogin
    },
  },
  created: function () {
  },
  mounted: function () {
  },
})
</script>

<style>
.search {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.searchinput{
  margin: 10px;
  width: 300px;
}
.searchbtn{
  width: 75px;
  /* font-weight: bold; */
}
.nav-item {
  display: flex;
  flex-direction: row;
  align-items: center;
  margin: 5px;
}
.logo {
  font-weight: bold;
  font-size: x-large;
  margin: 5px;
}
</style>