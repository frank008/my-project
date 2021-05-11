import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import MyEditor from '@/components/MyEditor'
import UploadImageVideo from '@/components/UploadImageVideo'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },
    { path: '/MyEditor',
      name: 'MyEditor',
      component: MyEditor
    }, {
      path: '/UploadImageVideo',
      name: 'UploadImageVideo',
      component: UploadImageVideo
    }
  ]
})
