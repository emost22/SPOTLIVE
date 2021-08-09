<template>
  <div>    
    <li class="nav-item">
      <router-link class="nav-link" :to="{ name: 'Profile', query: { profileId : following.accountEmail } }">
        <div class="sidebar-following-info">
          <div 
            class="sidebar-img-box" 
            v-bind:style="{ backgroundImage: 'url(' + following.profileImageUrl + ')' }"
          >
          </div>
          <div v-if="following.isLive" class="live-circle-badge bdcolor-npink"></div>
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
.live-circle-badge {
  width: 12px;
  height: 12px;
  border-radius: 100%;
  background-color: #F84ABF;
  margin-left: -7px;
  margin-top: -20px;
}
.sidebar-img-box {
  width: 40px;
  height: 40px;
  border-radius: 100%;
  margin-left: 10px;
  overflow:hidden;
  background-size: cover;
}
.sidebar-following-nickname {
  font-size: 15px;
  margin-left: 15px;
}
.sidebar-following-info {
  display: flex;
  flex-direction: row;
  align-items: center;
}
</style>