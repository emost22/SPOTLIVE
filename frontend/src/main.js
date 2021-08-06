import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueGlide from 'vue-glide-js'
import '../node_modules/vue-glide-js/dist/vue-glide.css'
import Carousel3d from 'vue-carousel-3d'

Vue.use(Carousel3d)
Vue.use(VueGlide)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
