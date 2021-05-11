<template>
  <div>
    <div id="toolbar">
      <span class="ql-formats">
      <select class="ql-font"></select>
      <select class="ql-size"></select>
    </span>
      <span class="ql-formats">
      <button class="ql-link"></button>
      <button class="ql-image"></button>
      <button class="ql-video"></button>
      <button class="ql-audio"></button>
      <button class="ql-formula"></button>
    </span>
      <span class="ql-formats">
      <button class="ql-bold"></button>
      <button class="ql-italic"></button>
      <button class="ql-underline"></button>
      <button class="ql-strike"></button>
    </span>
      <span class="ql-formats">
      <select class="ql-color"></select>
      <select class="ql-background"></select>
    </span>
      <span class="ql-formats">
      <button class="ql-script" value="sub"></button>
      <button class="ql-script" value="super"></button>
    </span>
      <span class="ql-formats">
      <button class="ql-header" value="1"></button>
      <button class="ql-header" value="2"></button>
      <button class="ql-blockquote"></button>
      <button class="ql-code-block"></button>
    </span>
      <span class="ql-formats">
      <button class="ql-list" value="ordered"></button>
      <button class="ql-list" value="bullet"></button>
      <button class="ql-indent" value="-1"></button>
      <button class="ql-indent" value="+1"></button>
    </span>
      <span class="ql-formats">
      <button class="ql-direction" value="rtl"></button>
      <select class="ql-align"></select>
    </span>
      <span class="ql-formats">
      <button class="ql-clean"></button>
    </span>
    </div>
    <div id="editor">
      <p>Hello World!</p>
      <p>Some initial <strong>bold</strong> text</p>
      <p><br></p>
    </div>
    <input id="uploadImg" type="file" style="display:none" accept="image/png, image/jpeg, image/gif" @change="uploadImage">
    <input id="uploadVideo" type="file" style="display:none" accept="video/*" @change="uploadVideo">
    <input id="uploadVoice" type="file" style="display:none" accept="audio/*" @change="uploadVoice">
    <input id="uploadFile" type="file" style="display:none" accept="application/*" @change="uploadFile">
  </div>
</template>

<script>
import {quillEditor, Quill} from 'vue-quill-editor'
import audio from '../../static/myVoice'
import file from '../../static/myFile'

Quill.register(file)
Quill.register(audio)

export default {
  name: 'QuillEditor',
  components: {
    quillEditor
  },
  mounted () {
    const voiceButton = document.querySelector('.ql-audio')
    voiceButton.classList.add('fa')
    voiceButton.classList.add('fa-volume-up')
    voiceButton.classList.add('fa-lg')
    this.initQuill()
  },
  beforeDestroy () {
    this.quill = null
    delete this.quill
  },
  methods: {
    initQuill () {
      const quill = new Quill('#editor', {
        theme: 'snow',
        modules: {
          toolbar: '#toolbar'
        }
      })
      this.quill = quill
      this.quill.getModule('toolbar').addHandler('image', this.uploadImageHandler)
      this.quill.getModule('toolbar').addHandler('video', this.uploadVideoHandler)
      this.quill.getModule('toolbar').addHandler('audio', this.uploadVoiceHandler)
      this.quill.getModule('toolbar').addHandler('link', this.uploadFileHandler)
      // this.quill.getModule('toolbar').addHandler('link', this.SnowThemeLinkHandler)
    },
    uploadVoiceHandler () {
      const input = document.querySelector('#uploadVoice')
      input.value = ''
      input.click()
    },
    uploadImageHandler () {
      const input = document.querySelector('#uploadImg')
      input.value = ''
      input.click()
    },
    uploadVideoHandler () {
      const input = document.querySelector('#uploadVideo')
      input.value = ''
      input.click()
    },
    uploadFileHandler () {
      const input = document.querySelector('#uploadFile')
      input.value = ''
      input.click()
    },
    async uploadVideo (event) {
      const form = new FormData()
      form.append('upload_file', event.target.files[0])
      const url = 'https://player.vimeo.com/video/377060550?title=0&portrait=0&byline=0&autoplay=1' // await $.ajax(...)
      const addImageRange = this.quill.getSelection()
      const newRange = 0 + (addImageRange !== null ? addImageRange.index : 0)
      this.quill.insertEmbed(newRange, 'video', url)
      this.quill.setSelection(1 + newRange)
    },
    async uploadImage (event) {
      const form = new FormData()
      form.append('upload_file', event.target.files[0])
      const url = 'https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3228549874,2173006364&fm=26&gp=0.jpg' // await $.ajax(...)
      const addImageRange = this.quill.getSelection()
      const newRange = 0 + (addImageRange !== null ? addImageRange.index : 0)
      this.quill.insertEmbed(newRange, 'image', url)
      this.quill.setSelection(1 + newRange)
    },
    async uploadVoice (event) {
      const form = new FormData()
      form.append('upload_file', event.target.files[0])
      const myurl = 'http://www.443w.com/tts/mp3/yan.mp3' // await $.ajax(...)
      const addImageRange = this.quill.getSelection()
      const newRange = 0 + (addImageRange !== null ? addImageRange.index : 0)
      this.quill.insertEmbed(newRange, 'audio', {url: myurl}, 'api')
      this.quill.setSelection(1 + newRange)
    },
    async uploadFile (event) {
      const form = new FormData()
      form.append('upload_file', event.target.files[0])
      // var fileName = event.target.files[0].name
      const url = 'http://www.gsk.com.cn/uploadfiles/2017/09/GSK988Ti%E7%B3%BB%E5%88%97%20%E7%BC%96%E7%A8%8B%E6%93%8D%E4%BD%9C%E6%89%8B%E5%86%8C2017%E5%B9%B41%E6%9C%88%E7%AC%AC1%E7%89%8810%E5%AE%9A.pdf' // await $.ajax(...)
      const addImageRange = this.quill.getSelection()
      const newRange = 0 + (addImageRange !== null ? addImageRange.index : 0)
      this.quill.insertEmbed(newRange, 'a', url)
      this.quill.setSelection(1 + newRange)
    }
  }
}
</script>
<style>
  .ql-link{}
  .ql-stroke{}
  .ql-even{}
</style>
