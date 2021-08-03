<template>
  <!-- v-if="!isLogin" -->
  <div class="">
    <nav class="navbar navbar-expand-lg sticky-top" :class="{'navbar-dark': isDark,'bg-light': isLight , 'navbar-light': isLight, 'bg-dark': isDark }">
      <div class="container-fluid" id="nav-div">
        <router-link class="navbar-brand fw-bold" :to="{ name: 'Main' }">SPOT LIVE</router-link>
          <ul class="navbar-nav">
            <!-- <li v-if="!isLogin" class="nav-item"> -->
            <li class="nav-item">
              <router-link class="nav-link fw-bold" :to="{ name: 'RoomCreate' }">Straming</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link fw-bold" :to="{ name: 'Profile' }">Profile</router-link>
            </li>
            <li class="nav-item">
              <img src="frontend\src\assets\icon-alarm.png" alt=""> alarm
            </li>
            <li class="nav-item">
              <router-link class="nav-link fw-bold" @click.native="logout" :to="{ name: 'Login' }">Logout</router-link>
            </li>
          </ul>
      </div>
    </nav>
  </div>
</template>

<script>

export default {

}
</script>

<style>

</style>