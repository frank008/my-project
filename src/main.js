// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
/* 引入下面三行 */
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import 'font-awesome/css/font-awesome.min.css'

// 引用API文件
import api from './api/index.js'
// 将API方法绑定到全局
Vue.prototype.$api = api
Vue.use(ElementUI)
Vue.use(VueQuillEditor)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
