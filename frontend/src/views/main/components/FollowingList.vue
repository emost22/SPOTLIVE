<template>
  <div>    
    <li class="nav-item">
      <router-link class="nav-link" :to="{ name: 'Profile', query: { profileId : following.accountEmail } }">
        <div class="sidebar-following-info">
          <div><img :src="following.profileImageUrl" class="sidebar-following-img"></div>
          <!-- 라이브 중인 경우 핑크색 점 추가 -->
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
  width: 40px;
  height: 40px;
  border-radius: 100%;
  margin-left: 10px;
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