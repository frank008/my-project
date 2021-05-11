<template>
  <el-row :gutter="20">
    <el-col :span="24">
      <div class="videoArea">
        <el-upload
          class="avatar-uploader"
          action="http://localhost:8080/project/uploadVideo"
          :headers="myHeaders"
          :before-upload="beforeUploadVideo"
          :on-success="handleVideoSuccess"
          :on-progress="uploadVideoProcess"
          :show-file-list="false">
          <video
            v-if="videoForm.showVideoPath != '' && !videoFlag"
            :src="videoForm.showVideoPath"
            controls
            width="400"
            height="240">
          </video>
          <i v-else-if="videoForm.showVideoPath == '' && !videoFlag"
            class="el-icon-plus avatar-uploader-icon"></i>
          <!-- 进度条 -->
          <el-progress
            v-if="videoFlag == true"
            type="circle"
            :percentage="videoUploadPercent"
            style="margin-top: 7px"
          ></el-progress>
        </el-upload>
      </div>
    </el-col>
    <el-col :span="24">
      <div class="textArea">
        <p>
          <span>单次最多上传1个视频，大小不要超过50M，上传后返回的URL用于正文插入视频</span>
        </p>
        <p>
          <span v-if="videoForm.showVideoPath != ''">视频URL：
            <span class="urlArea">
              {{ videoForm.showVideoPath }}
            </span>
          </span>
        </p>
      </div>
    </el-col>
  </el-row>
</template>
<script>
export default {
  name: 'MyVideo',
  data () {
    return {
      // token
      myHeaders: { Authorization: localStorage.getItem('token') },
      // 视频
      videoFlag: false,
      // 是否显示进度条
      videoUploadPercent: '',
      // 进度条的进度，
      isShowUploadVideo: false,
      // 显示上传按钮
      videoForm: {
        showVideoPath: ''
      }
    }
  },
  methods: {
    // 上传前回调
    beforeUploadVideo (file) {
      var fileSize = file.size / 1024 / 1024 < 50
      if (
        [
          'video/mp4',
          'video/ogg',
          'video/flv',
          'video/avi',
          'video/wmv',
          'video/rmvb',
          'video/mov'
        ].indexOf(file.type) === -1
      ) {
        this.$message.error('请上传正确的视频格式')
        return false
      }
      if (!fileSize) {
        this.$message.error('视频大小不能超过50MB')
        return false
      }
      this.isShowUploadVideo = false
    },
    // 进度条
    uploadVideoProcess (event, file, fileList) {
      this.videoFlag = true
      this.videoUploadPercent = file.percentage.toFixed(0) * 1
    },
    // 上传成功回调
    handleVideoSuccess (res, file) {
      this.isShowUploadVideo = true
      this.videoFlag = false
      this.videoUploadPercent = 0
      if (res.code === 200) {
        this.videoForm.showVideoPath = res.url
      } else {
        this.$message.error(res.msg)
      }
    }
  }
}
</script>
