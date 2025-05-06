<script lang="ts" setup>
import { computed, onMounted, ref } from 'vue'
import instance from '@/utils/axios'

interface Article {
  id: number
  title: string
  description: string
  image: string
  content: string
}

const articles = ref<Article[]>([]) // 文章数据数组
const currentPage = ref(1) // 当前页码
const pageSize = 5 // 每页显示的文章数
const dialogVisible = ref(false) // 控制弹窗的可见性
const selectedArticle = ref<Article | null>(null) // 选中的文章

// 计算当前页显示的文章
const paginatedArticles = computed(() => {
  const start = (currentPage.value - 1) * pageSize
  const end = start + pageSize
  return articles.value.slice(start, end)
})

// 获取文章列表数据
async function fetchArticles() {
  try {
    const response = await instance.get('/articles')
    articles.value = response.data.reverse()
  } catch (error) {
    console.error('获取文章失败', error)
  }
}

// 在组件挂载时调用API获取数据
onMounted(() => {
  fetchArticles()
})

// 切换页面
function handlePageChange(page: number) {
  currentPage.value = page
}

// 查看文章详情，弹出弹窗
function viewArticle(article: Article) {
  console.log('Article selected:', article) // 调试输出
  selectedArticle.value = article
  dialogVisible.value = true
  console.log('Dialog visible:', dialogVisible.value) // 调试输出
}
</script>

<template>
  <div class="article-list-container">
    <el-row class="article-list" :gutter="20">
      <el-col v-for="article in paginatedArticles" :key="article.id" :span="24">
        <el-card class="article-item" shadow="hover">
          <div class="article-content-wrapper" @click="viewArticle(article)">
            <img alt="Article Image" class="article-image" :src="article.image" />
            <div class="article-content">
              <h3>{{ article.title }}</h3>
              <p>{{ article.description }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-pagination
      v-model="currentPage"
      background
      class="pagination"
      hide-on-single-page
      layout="prev, pager, next"
      :page-size="pageSize"
      :total="articles.length"
      @current-change="handlePageChange"
    />

    <el-dialog v-model="dialogVisible" append-to-body title="一言详情" width="50%">
      <div v-if="selectedArticle">
        <h2 style="text-align: center">{{ selectedArticle.title }}</h2>
        <!-- <h2>{{ selectedArticle.title }}</h2> -->
        <img alt="Article Image" class="dialog-article-image" :src="selectedArticle.image" />
        <p class="indented-text">{{ selectedArticle.content }}</p>
      </div>
    </el-dialog>
  </div>
</template>

<style scoped>
.article-list-container {
  max-width: 450px;
  margin-left: auto;
  margin-right: auto;
  padding: 30px;
  background-color: transparent;
  border-radius: 10px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
}

.article-list {
  margin-top: 20px;
}

.article-item {
  cursor: pointer;
  transition:
    transform 0.3s ease,
    box-shadow 0.3s ease;
  margin-bottom: 20px;
  border-radius: 12px;
  background-color: #ffffff;
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
}

.article-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 12px 24px rgba(0, 0, 0, 0.2);
}

.article-content-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.article-image {
  width: 100%;
  height: auto;
  object-fit: cover;
  border-radius: 12px 12px 0 0;
}

.article-content {
  padding: 15px;
  flex: 1;
}

h3 {
  font-size: 22px;
  margin-bottom: 10px;
  color: #333;
}

p {
  color: #777;
  font-size: 16px;
  line-height: 1.6;
}

.pagination {
  margin-top: 20px;
  margin-left: auto;
  margin-right: auto;
  width: 100%; /* 设置为100%以占满父容器 */
  text-align: center; /* 确保内容居中 */
  background-color: transparent;
}

.dialog-article-image {
  width: 40%;
  height: auto;
  object-fit: cover;
  margin: 10px auto 10px 250px;
  border-radius: 10px;
}

.indented-text {
  text-indent: 2em;
  text-align: center;
}
</style>
