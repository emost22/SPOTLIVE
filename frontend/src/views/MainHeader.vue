<template>
  <!-- v-if="!isLogin" -->
  <div class="nav-header bgcolor-deep-grey">
    <nav class="navbar navbar-expand">
      <div class="container-fluid">
        <router-link class="navbar-brand" :to="{ name: 'Main' }">
          <!-- <img class="logoimg" src="~@/assets/logo.png"> -->
          <span class="txtcolor-ngreen logo">SPOT</span>
          <span class="txtcolor-npink logo">LIVE</span>
        </router-link>
        <div class="search">
          <input type="text" class="bgcolor-mid-deep-grey txtcolor-white search-input" v-model.trim="input" placeholder="검색할 내용을 입력하세요">
          <button type="button"
            class="bgcolor-mid-deep-grey txtcolor-white search-btn text-align-center" 
            :disabled="!this.validSearch" 
            @click="clickSearchBtn">
            Search
          </button>
        </div>        
        <ul class="navbar-nav">
          <!-- 송출자가 라이브인 경우 스트리밍 버튼 제거 -->
          <li class="nav-item header-item">
            <div><router-link class="nav-link fw-bold" :to="{ name: 'RoomCreate' }"><img src="~@/assets/icon-streaming.png" class="header-icon-img"></router-link></div>
          </li>
          <li class="nav-item header-item">
            <div><router-link class="nav-link fw-bold" :to="{ name: 'Profile', query: { profileId : this.loginUser.accountEmail } }"><img src="~@/assets/icon-profile.png" class="header-icon-img"></router-link></div>
          </li>
          <li class="nav-item header-item">
            <div><img src="~@/assets/icon-alarm.png" class="header-icon-img"></div>
          </li>
          <li class="nav-item header-item">
            <div><router-link class="nav-link" @click.native="logout" :to="{ name: 'Login' }"><img src="~@/assets/icon-logout.png" class="header-icon-img"></router-link></div>
          </li>
        </ul>
      </div>
    </nav>
  </div>
</template>

<script>
import { mapGetters } from "vuex"

export default ({
  name: "MainHeader",
  components: {
  },
  data: function () {
    return {
      validSearch: true,
      input: '',
    }
  },
  methods: {
    logout: function () {
      this.$store.dispatch('requestLogout')
      this.$router.push({ name: 'Login' })
    },
    clickSearchBtn: function () {
      if (this.input) {
        this.$router.push({ name: 'Search', query: { input: this.input } })
        this.input = ''
      }
      else {
        console.log('검색할 내용을 입력하세요')
      }
    }
  },
  computed: {
    ...mapGetters(['loginUser']),
    // FilterButton.vue에서 클릭 이벤트가 일어나면 카테고리 아이디를 받아서 인자를 넣어보자
  },
})
</script>

<style>
.nav-header {
  width: 100%;
  height: 65px;
  display: block;
}
.header-item {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;
  width: 50px;
  height: 50px;
}
.header-icon-img {
  width: 35px;
  height: 35px;
}
.logo-img {
  width: 180px;
  height: 80px;
}
.logo {
  font-weight: bold;
  font-size: 25px;
  margin: 5px;
}
.search {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.search-input{
  margin: 10px;
  width: 400px;
  height: 25px;
  border: #6A6A6A;
}
.search-btn{
  width: 70px;
  height: 25px;
  border: #6A6A6A;
}
input:focus {
  box-shadow: 
    0 0 9px #ffffff,
    0 0 12px #ffffff;
}
</style>