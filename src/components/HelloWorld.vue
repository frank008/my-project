<template>
  <div>
    <h1>{{ msg }}</h1>
    <router-link to="/MyEditor">MyEditor</router-link>
    <router-link to="/UploadImageVideo">UploadImageVideo</router-link>
    <router-view></router-view>
    <button name="button" @click="postMassage">发送请求</button>
  </div>

</template>

<script>
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: '欢迎来到皓思百微！'
    }
  },
  methods: {
    postMassage () {
      alert('发送请求')
      const jsonData = {
        'page': '1',
        'limit': '3',
        'docId': '',
        'docTitle': '',
        'docRight': ' ',
        'docAuthorId': ' ',
        'doc_content': ' ',
        'publishMark': ' ',
        'orderMark': 'updateTime' // createTime   updateTime  docOrderNumber
      }
      this.$api.post('http://gq.athspiring.com/pcindex/appDocQueryPage', jsonData, null, r => {
        console.log(r)
      })
    }
  }

}
</script>
