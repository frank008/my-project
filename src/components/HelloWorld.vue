<template>
  <div>
    <h1>{{ msg }}</h1>
    <router-link to="/MyEditor">MyEditor</router-link>
    <router-link to="/UploadImageVideo">UploadImageVideo</router-link>
    <router-view></router-view>
  </div>

</template>

<script>

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: '欢迎来到皓思百微！'
    }
  }
}
</script>
