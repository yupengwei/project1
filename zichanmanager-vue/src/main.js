// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Global from './components/util/Global'
// import VueAxios from 'vue-axios'
import axios from 'axios'

Vue.prototype.GLOBAL = Global
Vue.config.productionTip = false
Vue.prototype.$ajax = axios;
// Vue.use(VueAxios,axios);

// 设置请求头部content-type:application/x-www-form-urlencoded
// Vue.http.options.emulateJSON = true
// Vue.http.options.emulateHTTP = true
// Vue.http.options.xhr = { withCredentials: true }

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App },
  axios
})
