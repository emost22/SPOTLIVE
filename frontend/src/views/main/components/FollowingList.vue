<template>
  <div>    
    <li class="nav-item">
      <router-link class="nav-link" :to="{ name: 'Profile', param: { profileId : following.accountEmail } }">
        <div class="sidebar-following-info">
          <div><img src="~@/assets/icon-streaming.png" class="sidebar-following-img"></div>
          <div v-if="open" class="sidebar-following-nickname txtcolor-white">
            <p>{{following.profileNickname}}</p>
          </div>
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
}
</script>

<style>
.sidebar-following-img {
  width: 50px;
  height: 50px;
  border-radius: 100%;
}
.sidebar-following-nickname {
  font-size: 15px;
}
.sidebar-following-info {
  display: flex;
  flex-direction: row;
}
</style>