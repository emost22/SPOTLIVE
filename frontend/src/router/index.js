import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login/Login.vue'
import Main from '../views/main/Main.vue'
import Profile from '../views/profile/Profile.vue'
import RoomCreate from '../views/room/RoomCreate.vue'
import RoomDetail from '../views/room/RoomDetail.vue'
import Search from '../views/search/Search.vue'
import state from '../store/state'

Vue.use(VueRouter)

const requireAuth = (to, from, next) => {
  console.log("ROUTER===============================================")
  const accessToken = localStorage.getItem("accessToken");
  if (accessToken == null) {
    console.log("NO LOGIN===============================================")
    alert("로그인을 먼저 해주세요.");
    state.isLogin = false;
    next('/login');
  } else {
    console.log("YES LOGIN===============================================")
    state.isLogin = true;
    next();
  }
}

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/',
    name: 'Main',
    beforeEnter: requireAuth,
    component: Main
  },
  {
    path: '/main',
    name: 'Main',
    beforeEnter: requireAuth,
    component: Main
  },
  {
    path: '/profile',
    name: 'Profile',
    beforeEnter: requireAuth,
    component: Profile
  },
  {
    path: '/room/create',
    name: 'RoomCreate',
    beforeEnter: requireAuth,
    component: RoomCreate
  },
  {
    path: '/room/detail',
    name: 'RoomDetail',
    beforeEnter: requireAuth,
    component: RoomDetail
  },
  {
    path: '/search',
    name: 'Search',
    beforeEnter: requireAuth,
    component: Search
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
