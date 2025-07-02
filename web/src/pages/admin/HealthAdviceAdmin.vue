<template>
  <div>
    <el-card class="card" body-style="justify-content: center;">
      <h2 class="tital">文章管理</h2>

      <div class="searchConditionDiv">
        <p style="margin-top: auto; margin-bottom: auto">查询条件：</p>
        <el-select
          class="selectCon"
          v-model="searchCondition"
          placeholder="查询条件"
          size="large"
          style="width: 10%; margin-top: auto; margin-bottom: auto"
          clearable
        >
          <el-option value="id" label="ID" />
          <el-option value="title" label="标题" />
          <el-option value="description" label="描述" />
        </el-select>
        <el-input
          class="conditionInput"
          v-model="conditionValue"
          placeholder=""
          size="small"
          style="margin-top: auto; margin-bottom: auto"
          clearable
        ></el-input>
        <el-button
          type="primary"
          style="height: 40px; margin-right: 5px; margin-top: auto; margin-bottom: auto"
          @click="searchByCondition"
          >搜索</el-button
        >
        <el-button
          type="danger"
          style="height: 40px; margin-top: auto; margin-bottom: auto"
          @click="clearSearchCondition"
          >清空</el-button
        >
        <el-button
          type="primary"
          style="height: 40px; margin-left: 50px; margin-top: auto; margin-bottom: auto"
          @click="addArticlesDialog"
          >添加文章</el-button
        >
      </div>

      <el-dialog title="添加文章" v-model="addArticlesDialogVisible" width="50%">
        <el-form
          ref="form"
          :model="addArticle"
          label-width="150px"
          style="margin-left: auto; margin-right: auto"
        >
          <el-form-item class="dialogInput" label="标题" prop="title">
            <el-input v-model="addArticle.title" placeholder="请输入标题"></el-input>
          </el-form-item>
          <el-form-item class="dialogInput" label="描述" prop="description">
            <el-input v-model="addArticle.description" placeholder="请输入描述"></el-input>
          </el-form-item>
          <el-form-item class="dialogInput" label="图片链接" prop="image">
            <el-input v-model="addArticle.image" placeholder="请输入图片链接"></el-input>
          </el-form-item>
          <el-form-item class="dialogInput" label="内容" prop="content">
            <el-input v-model="addArticle.content" placeholder="请输入内容"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" size="small" @click="saveAddArticle">添加</el-button>
            <el-button type="info" size="small" @click="closeAddDialog">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>

      <el-table class="table" :data="articles">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="title" label="标题" width="180" />
        <el-table-column prop="description" label="描述" width="300" />
        <el-table-column prop="image" label="图片" width="180">
          <template v-slot="scoped">
            <img
              :src="scoped.row.image"
              alt="Article Image"
              style="width: 100px; height: 60px; object-fit: cover"
            />
          </template>
        </el-table-column>
        <el-table-column prop="content" label="内容" />
        <el-table-column header-align="center" align="center" label="操作">
          <template v-slot="scoped">
            <el-button
              class="tableButton"
              type="primary"
              icon="el-icon-edit"
              @click="updateArticle(scoped.row)"
              >更新</el-button
            >
            <el-button
              class="tableButton"
              type="danger"
              icon="el-icon-delete"
              @click="deleteArticle(scoped.row.id)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        class="paging"
        :page-size="pageSize"
        :current-page="currentPage"
        :page-sizes="[5, 10, 15, 20]"
        layout="sizes,prev,pager,next,jumper,->,total"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        :total="total"
      >
      </el-pagination>
    </el-card>

    <el-dialog title="修改文章信息" v-model="dialogVisible" width="50%">
      <el-form
        ref="form"
        :model="modifyArticle"
        label-width="150px"
        style="margin-left: auto; margin-right: auto"
      >
        <el-form-item class="dialogInput" label="ID" prop="id">
          <el-input v-model="modifyArticle.id" placeholder="请输入ID" disabled></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="标题" prop="title">
          <el-input v-model="modifyArticle.title" placeholder="请输入标题"></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="描述" prop="description">
          <el-input v-model="modifyArticle.description" placeholder="请输入描述"></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="图片链接" prop="image">
          <el-input v-model="modifyArticle.image" placeholder="请输入图片链接"></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="内容" prop="content">
          <el-input v-model="modifyArticle.content" placeholder="请输入内容"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" size="small" @click="saveArticle">更改</el-button>
          <el-button type="info" size="small" @click="closeDialog">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { Client } from '@/data'
import { onMounted, ref } from 'vue'

let articles = ref([])
const pageSize = ref(5)
const currentPage = ref(1)
const total = ref(0)
const dialogVisible = ref(false)
const searchCondition = ref('')
const conditionValue = ref('')
const addArticlesDialogVisible = ref(false)
const modifyArticle = ref({
  id: ref(''),
  title: ref(''),
  description: ref(''),
  image: ref(''),
  content: ref('')
})
const addArticle = ref({
  title: ref(''),
  description: ref(''),
  image: ref(''),
  content: ref('')
})

function updateArticle(row: {
  id: string
  title: string
  description: string
  image: string
  content: string
}) {
  dialogVisible.value = true
  modifyArticle.value = row
}

function deleteArticle(id: string) {
  let conf = confirm('是否删除这篇文章？')
  if (conf) {
    Client.post(
      '/articles/deleteArticle',
      {
        id: id
      },
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    )
      .then(() => {
        ElMessage.success('删除成功')
        searchByPage()
      })
      .catch((error) => {
        ElMessage.error(error)
      })
  }
}

function searchByPage() {
  Client.post(
    '/articles/searchArticleByCondition',
    {
      curPage: currentPage.value,
      size: pageSize.value,
      searchCondition: searchCondition.value,
      conditionValue: conditionValue.value
    },
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  ).then((response) => {
    articles.value = response.data.data.records
    total.value = response.data.data.total
  })
}

function searchByCondition() {
  if (!searchCondition.value) ElMessage.error('请选择查询条件')
  else if (!conditionValue.value) ElMessage.error('请输入搜索值')
  else {
    searchByPage()
  }
}

function clearSearchCondition() {
  searchCondition.value = ''
  conditionValue.value = ''
  searchByPage()
}

onMounted(() => {
  searchByPage()
})

function handleSizeChange(newSize: number) {
  pageSize.value = newSize
  currentPage.value = 1
  searchByPage()
}

function handleCurrentChange(newPage: number) {
  currentPage.value = newPage
  searchByPage()
}

async function saveArticle() {
  await Client.post(
    '/articles/modifyArticleInfo',
    {
      id: modifyArticle.value.id,
      title: modifyArticle.value.title,
      description: modifyArticle.value.description,
      image: modifyArticle.value.image,
      content: modifyArticle.value.content
    },
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  )
    .then(() => {
      ElMessage.success('更新成功')
      searchByPage()
      dialogVisible.value = false
    })
    .catch((error) => {
      ElMessage.error(error)
    })
}

function closeDialog() {
  dialogVisible.value = false
}

function addArticlesDialog() {
  addArticlesDialogVisible.value = true
}

async function saveAddArticle() {
  await Client.post(
    '/articles/add',
    {
      title: addArticle.value.title,
      description: addArticle.value.description,
      image: addArticle.value.image,
      content: addArticle.value.content
    },
    {
      headers: {
        'Content-Type': 'application/json'
      }
    }
  )
    .then(() => {
      ElMessage.success('添加成功')
      searchByPage()
      dialogVisible.value = false
    })
    .catch((error) => {
      ElMessage.error(error)
    })
}

function closeAddDialog() {
  addArticlesDialogVisible.value = false
}
</script>

<style>
.tital {
  text-align: center;
}
.card {
  margin-left: auto;
  margin-right: auto;
  width: 90%;
  min-height: 80%;
}
.table {
  margin-left: auto;
  margin-right: auto;
  width: 100%;
}
.tableButton {
  width: 30%;
  height: 30%;
}
.paging {
  margin-left: auto;
  margin-right: auto;
  width: 100%;
}
.searchConditionDiv {
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
}
.selectCon {
  width: 10%;
}
.conditionInput {
  width: 20%;
  margin-left: 10px;
}
.el-input.el-input--small.conditionInput {
  width: 20%;
  height: 40px;
  margin-left: 10px;
  margin-right: 10px;
}
.dialogInput {
  width: 70%;
}
</style>
