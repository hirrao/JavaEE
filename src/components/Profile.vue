<template>
  <div class="main-profile">
    <div class="intro">
      <img src="https://s2.loli.net/2024/04/14/uDARibvM2fJZ5Fx.jpg" alt="Profile Picture" />
      <div class="detail">
        <h2>用户名</h2>
        <p>性别：男</p>
        <p>年龄：55</p>
      </div>
    </div>
    <section class="profile">
      <div class="sidebar">
        <button>编辑个人资料</button>
        <p>所在地：北京</p>
        <p>
          个性签名：<br />
          关注健康，享受生活。
        </p>
      </div>

      <div class="main-content">
        <div class="stats">
          <h3>我的日志</h3>
          <button>增加日志</button>
        </div>
        <div class="main">
          <div class="editor">
            <quill-editor
              ref="editorRef"
              v-model:content="content"
              :options="options"
              contentType="html"
            ></quill-editor>
          </div>
          <div>
            <!-- <button @click="print">click me</button> -->
          </div>
        </div>
        <!-- <div v-html="content" style="width: 100px;"></div> -->
      </div>
    </section>
  </div>
</template>

<script setup lang="ts">
import router from '../router'
import { onMounted, ref, toRaw, nextTick } from 'vue'
//   import filesApi from '@/api/files';
import { ElMessage } from 'element-plus'
import { Quill, QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'

const content = ref()
const editorRef = ref()

//   // 处理富文本图片上传
//   const imageHandler = () => {
//     // 创建一个文件输入元素
//     const input = document.createElement('input');
//     input.setAttribute('type', 'file');
//     input.setAttribute('accept', 'image/*');
//     // 模拟点击，打开文件选择对话框
//     input.click();

//     // 当用户选择文件后触发的事件
//     input.onchange = async () => {
//       // 获取用户选择的文件
//       const file = input.files ? input.files[0] : null;
//       if (file) {
//         // 创建一个 FormData 对象，用于文件上传
//         const formData = new FormData();
//         formData.append('file', file);

//         try {
//           // 使用 axios 发送 POST 请求，将文件上传到服务器
//           const response = await axios.post(filesApi.url(), formData, { headers: { 'Content-Type': 'multipart/form-data' } });

//           // 确保获取到 Quill 编辑器实例
//           const quill = toRaw(editorRef.value).getQuill()
//           if (quill) {
//             // 获取当前光标位置
//             const range = quill.getSelection(true);
//             // 在当前光标位置插入上传的图片
//             quill.insertEmbed(range.index, 'image', response.data.data);
//           }
//         } catch (error) {
//           ElMessage.error("图片上传失败");
//         }
//       }
//     };
//   }

let options = {
  modules: {
    toolbar: {
      container: [
        ['bold', 'italic', 'underline', 'strike'], // 加粗 斜体 下划线 删除线
        [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
        [{ align: [] }], // 对齐方式
        [{ size: ['small', false, 'large', 'huge'] }], // 字体大小
        [{ font: [] }], // 字体种类
        [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
        // [{ direction: 'ltl' }], // 文本方向
        // [{ direction: 'rtl' }], // 文本方向
        [{ indent: '-1' }, { indent: '+1' }], // 缩进
        [{ list: 'ordered' }, { list: 'bullet' }], // 有序、无序列表
        [{ script: 'sub' }, { script: 'super' }], // 上标/下标
        ['blockquote', 'code-block'], // 引用  代码块
        ['clean'], // 清除文本格式
        ['link', 'image', 'video'] // 链接、图片、视频
      ],
      handlers: {
        //   image: imageHandler,
      }
    }
  }
}

// toolbar标题
const titleConfig = [
  { Choice: '.ql-insertMetric', title: '跳转配置' },
  { Choice: '.ql-bold', title: '加粗' },
  { Choice: '.ql-italic', title: '斜体' },
  { Choice: '.ql-underline', title: '下划线' },
  { Choice: '.ql-header', title: '段落格式' },
  { Choice: '.ql-strike', title: '删除线' },
  { Choice: '.ql-blockquote', title: '块引用' },
  { Choice: '.ql-code', title: '插入代码' },
  { Choice: '.ql-code-block', title: '插入代码段' },
  { Choice: '.ql-font', title: '字体' },
  { Choice: '.ql-size', title: '字体大小' },
  { Choice: '.ql-list[value="ordered"]', title: '编号列表' },
  { Choice: '.ql-list[value="bullet"]', title: '项目列表' },
  { Choice: '.ql-direction', title: '文本方向' },
  { Choice: '.ql-header[value="1"]', title: 'h1' },
  { Choice: '.ql-header[value="2"]', title: 'h2' },
  { Choice: '.ql-align', title: '对齐方式' },
  { Choice: '.ql-color', title: '字体颜色' },
  { Choice: '.ql-background', title: '背景颜色' },
  { Choice: '.ql-image', title: '图像' },
  { Choice: '.ql-video', title: '视频' },
  { Choice: '.ql-link', title: '添加链接' },
  { Choice: '.ql-formula', title: '插入公式' },
  { Choice: '.ql-clean', title: '清除字体格式' },
  { Choice: '.ql-script[value="sub"]', title: '下标' },
  { Choice: '.ql-script[value="super"]', title: '上标' },
  { Choice: '.ql-indent[value="-1"]', title: '向左缩进' },
  { Choice: '.ql-indent[value="+1"]', title: '向右缩进' },
  { Choice: '.ql-header .ql-picker-label', title: '标题大小' },
  { Choice: '.ql-header .ql-picker-item[data-value="1"]', title: '标题一' },
  { Choice: '.ql-header .ql-picker-item[data-value="2"]', title: '标题二' },
  { Choice: '.ql-header .ql-picker-item[data-value="3"]', title: '标题三' },
  { Choice: '.ql-header .ql-picker-item[data-value="4"]', title: '标题四' },
  { Choice: '.ql-header .ql-picker-item[data-value="5"]', title: '标题五' },
  { Choice: '.ql-header .ql-picker-item[data-value="6"]', title: '标题六' },
  { Choice: '.ql-header .ql-picker-item:last-child', title: '标准' },
  { Choice: '.ql-size .ql-picker-item[data-value="small"]', title: '小号' },
  { Choice: '.ql-size .ql-picker-item[data-value="large"]', title: '大号' },
  { Choice: '.ql-size .ql-picker-item[data-value="huge"]', title: '超大号' },
  { Choice: '.ql-size .ql-picker-item:nth-child(2)', title: '标准' },
  { Choice: '.ql-align .ql-picker-item:first-child', title: '居左对齐' },
  { Choice: '.ql-align .ql-picker-item[data-value="center"]', title: '居中对齐' },
  { Choice: '.ql-align .ql-picker-item[data-value="right"]', title: '居右对齐' },
  { Choice: '.ql-align .ql-picker-item[data-value="justify"]', title: '两端对齐' }
]

// 给富文本框工具栏加上鼠标悬浮中文提示
const initTitle = () => {
  for (let item of titleConfig) {
    // .editor 是富文本编辑器的类名
    let tip = document.querySelector('.editor ' + item.Choice)
    if (tip) {
      tip.setAttribute('title', item.title)
    }
  }
}

onMounted(async () => {
  // 确保 Quill 编辑器已完全加载
  await nextTick()
  // 初始化工具栏的中文提示
  initTitle()
})
</script>

<style>
.main-profile {
  font-family: Arial, sans-serif;
  margin: 0;
  padding: 0;
  background-color: #1e1e1e;
  color: #ffffff;
}

.editor {
  background-color: white;
  overflow: auto;
  resize: both;
  max-width: 99%;
  min-height: 600px;
  color: #000;
}

.profile {
  display: flex;
  margin: 20px;
  align-items: stretch;
}

.profile .sidebar {
  width: 25%;
  background-color: #2b2b2b;
  padding: 20px;
  border-radius: 10px;
}

.intro {
  width: 25%;
  margin: 20px;
  display: flex;
}

.intro .detail h2 {
  margin: 5px;
}

.intro .detail p {
  margin: 5px;
}

.intro img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
}

.profile .sidebar h2 {
  font-size: 20px;
  margin: 10px 0;
}

.profile .sidebar p {
  margin: 5px 0;
}

.profile .sidebar a {
  font-size: large;
  color: #000;
  margin-bottom: 10px;
}

.profile .sidebar button {
  background-color: #9b9b9b;
  color: white;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  width: 100%;
  margin-top: 10px;
}

.profile .main-content {
  width: 70%;
  margin-left: 5%;
  min-height: 500px;
}

.stats {
  background-color: #2b2b2b;
  padding: 20px;
  border-radius: 10px;
  margin-bottom: 20px;
  display: flex;
  justify-content: space-between;
}

.stats h3 {
  margin: 0 0 10px 0;
  font-size: 18px;
}

.stats button {
  background-color: #d6e105;
  color: rgb(7, 1, 1);
  font-weight: bolder;
  border: none;
  padding: 10px 15px;
  border-radius: 5px;
  cursor: pointer;
  width: 20%;
  margin-top: 10px;
}

.footer {
  text-align: center;
  padding: 10px;
  background-color: #2c2c2c;
  margin-top: 20px;
}

.fav {
  margin: 10px;
  display: flex;
  align-items: center;
  padding: 20px, 20px;
  justify-content: center;
}

.fav .u {
  text-decoration: none;
}

.fav .sep {
  color: #9b9b9b;
  font-size: 40px;
}

#container {
  width: 90%;
  height: 500px;
  margin: 10px;
  padding: 10px;
}
</style>
