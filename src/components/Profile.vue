<template>
    <div class="main-profile">
        <div class="intro">
            <img src="https://s2.loli.net/2024/04/14/uDARibvM2fJZ5Fx.jpg" alt="Profile Picture">
            <div class="detail">
                <h2>{{ userName }}</h2>
                <p>性别：{{ sex }}</p>
                <p>生日：{{ birthday }}</p>
            </div>
        </div>
        <section class="profile">
            <div class="sidebar">
                <button>编辑个人资料</button>
                <p>uid：{{ uid }}</p>
                <p>
                    个性签名：
                    <br>
                    {{  }}
                </p>
            </div>

            <div v-if="status_" class="main-content">
                <div class="stats">
                    <h3>我的日志</h3>
                    <button @click="switch_status">增加日志</button>
                </div>
                <div>
                    <el-card class="card">
                    <el-table class="table" :data="Blog">
                        <el-table-column prop="uid" label="uid" width="200" />

                        <el-table-column header-align="center" align="center" label="操作">
                        <template v-slot="scoped">
                            <el-button class="tableButton" type="primary" icon="el-icon-edit" @click="(scoped.row)">查看</el-button>
                        </template>
                        </el-table-column>
                    </el-table>
                    <el-pagination
                        class="paging"
                        v-model:page-size="pageSize"
                        v-model:current-page="currentPage"
                        :page-sizes="[5,10,15,20]"
                        layout="sizes,prev,pager,next,jumper,->,total"
                        @current-change="handleCurrentChange"
                        @size-change="handleSizeChange"
                        v-model:total="total">
                    </el-pagination>
                    </el-card>
                    <el-dialog title="修改账号信息" v-model="dialogVisble" width="50%">
                        
                    </el-dialog>
                </div>
            </div>
            <div class="main-content" v-else>
                <div class="stats2">
                    <div style="height: 50px;">
                        <span>我的日志</span>
                        <button @click="switch_status" style="float: right;">返回</button>
                        <button @click="handleSubmit" style="float: right;">提交</button>
                    </div>
                </div>
                <div class="main">
                    <div>
                        <h2>
                            请输入标题：
                        </h2>
                        <input type="text" v-model="title" class="title" maxlength="30"/>
                    </div>
                    <h2>
                        请输入正文：
                    </h2>
                    <div class="editor">
                        <quill-editor ref="editorRef" v-model:content="content" :options="options"
                            contentType="html"></quill-editor>
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
import router from '../router';
import { onMounted, ref, toRaw, nextTick } from 'vue'
//   import filesApi from '@/api/files';
import { ElMessage } from 'element-plus'
import instance from '../axios';
import { Quill, QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
import axios from 'axios';

let uid = localStorage.getItem('uid')
const userName = localStorage.getItem('userName')
const sex = localStorage.getItem('sex')
const birthday = localStorage.getItem('birthday')
onMounted(async () => {
  const user = await instance.post('/profile/userInfo', {
    'uid': uid
  }, {
    headers: {
      'Content-Type': 'application/json',
    },
  });
  console.log(user.data.data)
  searchByPage()
});


const status_ = ref(true)
const content = ref()
const editorRef = ref()
const title = ref()
let Blog=ref([]);
const pageSize=ref(5);
const currentPage=ref(1);
const total=ref(0);
const dialogVisble=ref(false);

function switch_status() {
    status_.value = !status_.value
}

//提交个人日志
const handleSubmit = async () => {
  try {
    uid = localStorage.getItem('uid')
    const response = await instance.post('/profile/add', {
      uid: uid,
      content: content.value,
      title: title.value
    }, {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    alert('提交成功')
  } catch (error) {
    alert('提交失败')
  }
};


async function searchByPage(){
        const res = await instance.post("/profile/search",{
            curPage: currentPage.value,
            size: pageSize.value,
            uid: uid
        },{
            headers: {
            'Content-Type': 'application/json',
            },
        }).then((response)=>{
          console.log(response.data)
          Blog.value=response.data.data.records;
          total.value=response.data.data.total;
        })
    }
    function handleSizeChange(newSize: number) {
      // handle the size change logic here
      currentPage.value=1
      searchByPage()
    }
    
    function handleCurrentChange(newPage: number) {
      // handle the current change logic here
      searchByPage()
    }

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
                ['link', 'image', 'video'], // 链接、图片、视频
            ],
            handlers: {
                //   image: imageHandler,
            },
        },
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
        let tip = document.querySelector('.editor ' + item.Choice);
        if (tip) {
            tip.setAttribute('title', item.title);
        }
    }
}

onMounted(async () => {
    // 确保 Quill 编辑器已完全加载
    await nextTick();
    // 初始化工具栏的中文提示
    initTitle();
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

.card {
    margin: 0;
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

.title  {
    max-width: 97%;
}
.stats {
    background-color: #2b2b2b;
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 20px;
    display: flex;
    justify-content: space-between;
}

h3 {
    margin: 0 0 10px 0;
    font-size: 18px;
}

.stats2 {
    background-color: #2b2b2b;
    padding: 20px;
    border-radius: 10px;
    margin-bottom: 20px;
    justify-content: space-between;
}

button {
    background-color: #e08b02;
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