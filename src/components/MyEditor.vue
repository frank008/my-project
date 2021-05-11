<template>
  <div>
<quill-editor
  v-model="content"
  ref="myQuillEditor"
  class="ql-editor-class"
  :options="editorOption">
</quill-editor>

<el-upload   style="display: none"
class="quill-picture-uploader"
action="http://localhost:8080/project/uploadPic"
:headers="myHeaders"
:before-upload="bfUpload"
:on-success="uploadSuccess">
</el-upload>
<el-dialog
  title="视频上传"
  :visible.sync="dialogVideoVisible"
  width="40%"
  :close-on-click-modal="false">
  <MyVideo></MyVideo>
</el-dialog>
  </div>
</template>

<script>
import MyVideo from '../components/MyVideo'
const toolbarOptions = [
  ['bold', 'italic', 'underline', 'strike'],
  ['blockquote', 'code-block'],
  [{'header': 1}, { 'header': 2 }],
  [{'list': 'ordered'}, {'list': 'bullet'}],
  [{'script': 'sub'}, {'script': 'super'}],
  [{'indent': '-1'}, {'indent': '+1'}],
  [{'direction': 'rtl'}], // text direction
  [{'size': ['small', false, 'large', 'huge']}],
  [{'header': [1, 2, 3, 4, 5, 6, false]}],
  [{'color': []}, {'background': []}],
  [{'font': []}],
  [{'align': []}],
  ['image', 'video'],
  ['link'],
  ['clean']
]

export default {
  name: 'MyEditor',
  data () {
    return {
      dialogVideoVisible: false,
      myHeaders: { Authorization: localStorage.getItem('token') },
      // 图片url
      urlList: [],
      // 正文
      content: '',
      // 富文本配置
      editorOption: {
        placeholder: '正文内容支持上传图片和视频',
        theme: 'snow',
        modules: {
          toolbar: {
            container: toolbarOptions, // 自定义工具栏，略
            handlers: {
              image: function (value) { // 替换原图片上传功能
                if (value) {
                  document.querySelector('.quill-picture-uploader input').click() // 核心
                } else {
                  this.quill.format('image', false)
                }
              }
            }
          }
        }
      }
    }
  },
  components: {
    MyVideo
  },
  methods: {

    // 上传图片之前校验
    bfUpload (file) {
      console.log(file)
      if (file.type === 'image/png' || file.type === 'image/jpeg') {
      } else {
        this.$message.error('图片插入失败,请检查文件格式')
      }
    },
    // 正文插入图片上传成功调用
    uploadSuccess (response, file, fileList) {
      this.urlList.push(response.url)
      let quill = this.$refs.myQuillEditor.quill
      if (response.url != null) {
        // 获取光标所在位置
        let length = quill.getSelection().index
        // 插入图片
        quill.insertEmbed(length, 'image', response.url)
        // 移动光标到图片后
        quill.setSelection(length + 1)
      }
    }

  }
}

</script>
