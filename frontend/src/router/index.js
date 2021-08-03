import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login/Login.vue'
import Main from '../views/main/Main.vue'
import Profile from '../views/profile/Profile.vue'
import RoomCreate from '../views/room/RoomCreate.vue'
import RoomDetail from '../views/room/RoomDetail.vue'
import Search from '../views/search/Search.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/main',
    name: 'Main',
    component: Main
  },
  {
    path: '/profile',
    name: 'Profile',
    component: Profile
  },
  {
    path: '/room/create',
    name: 'RoomCreate',
    component: RoomCreate
  },
  {
    path: '/room/detail',
    name: 'RoomDetail',
    component: RoomDetail
  },
  {
    path: '/search',
    name: 'Search',
    component: Search
  },
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
