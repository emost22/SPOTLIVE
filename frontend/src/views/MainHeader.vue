<template>
  <!-- v-if="!isLogin" -->
  <div class="bgcolor-deep-grey">
    <nav class="navbar navbar-expand">
      <div class="container-fluid">
        <router-link class="navbar-brand" :to="{ name: 'Main' }">
          <!-- <img class="logoimg" src="~@/assets/logo.png"> -->
          <span class="txtcolor-ngreen logo">SPOT</span>
          <span class="txtcolor-npink logo">LIVE</span>
        </router-link>
        <div class="search"> 
          <input class="bgcolor-mid-deep-grey txtcolor-white searchinput" type="text" id="" v-model.trim="input" placeholder="검색할 내용을 입력하세요">
          <button 
            class="bgcolor-mid-deep-grey txtcolor-white searchbtn" 
            :disabled="!this.validSearch" 
            @click="clickSearchBtn">
            Search
          </button>
        </div>        
        <ul class="navbar-nav">
          <!-- 송출자가 라이브인 경우 스트리밍 버튼 제거 -->
          <li class="nav-item">
            <div><router-link class="nav-link fw-bold" :to="{ name: 'RoomCreate' }"><img src="~@/assets/icon-streaming.png" class="iconimg"></router-link></div>
          </li>
          <li class="nav-item">
            <div><router-link class="nav-link fw-bold" :to="{ name: 'Profile' }"><img src="~@/assets/icon-profile.png" class="iconimg"></router-link></div>
          </li>
          <li class="nav-item">
            <div><img src="~@/assets/icon-alarm.png" class="iconimg"></div>
          </li>
          <li class="nav-item">
            <div><router-link class="nav-link" @click.native="logout" :to="{ name: 'Login' }"><img src="~@/assets/icon-logout.png" class="iconimg"></router-link></div>
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
      validSearch: true,
      input: '',
    }
  },methods: {
    logout: function () {
      this.isLogin = false
      this.$store.dispatch('logout', this.isLogin)
      localStorage.removeItem('jwt')
      this.$router.push({ name: 'Login' })
    },
    clickSearchBtn: function () {
      if (this.input) {
        console.log(this.input)
        // Search.vue로 이동하고 검색 결과 axios
        this.$router.push({ name: 'Search', param: { input: this.input } })
      }
      else {
        console.log('검색할 내용을 입력하세요')
      }
    }
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
.logoimg {
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
.searchinput{
  margin: 10px;
  width: 400px;
}
.searchbtn{
  width: 75px;
  /* font-weight: bold; */
}
.nav-item {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.iconimg {
  width: 35px;
  height: 35px;
}
</style>