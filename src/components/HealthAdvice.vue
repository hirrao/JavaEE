<template>
  <div class="article-list-container">
    <el-row :gutter="20" class="article-list">
      <el-col v-for="article in paginatedArticles" :key="article.id" :span="24">
        <el-card shadow="hover" class="article-item">
          <div class="article-content-wrapper" @click="viewArticle(article)">
            <img :src="article.image" alt="Article Image" class="article-image" />
            <div class="article-content">
              <h3>{{ article.title }}</h3>
              <p>{{ article.description }}</p>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 分页控件 -->
    <el-pagination
      v-model="currentPage"
      :page-size="pageSize"
      :total="articles.length"
      layout="prev, pager, next"
      @current-change="handlePageChange"
      background
      hide-on-single-page
      class="pagination"
    />

    <!-- 弹窗展示文章详情 -->
    <el-dialog
      v-model="dialogVisible"
      width="50%"
      title="一言详情"
      append-to-body
    >
      <div v-if="selectedArticle">
        <h2 style="text-align: center;">{{ selectedArticle.title }}</h2>
        <!-- <h2>{{ selectedArticle.title }}</h2> -->
        <img :src="selectedArticle.image" alt="Article Image" class="dialog-article-image" />
        <p class="indented-text">{{ selectedArticle.content }}</p>
      </div>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue';
import instance from '../axios';

interface Article {
  id: number;
  title: string;
  description: string;
  image: string;
  content: string;
}

const articles = ref<Article[]>([]); // 文章数据数组
const currentPage = ref(1); // 当前页码
const pageSize = 5; // 每页显示的文章数
const dialogVisible = ref(false); // 控制弹窗的可见性
const selectedArticle = ref<Article | null>(null); // 选中的文章

// 计算当前页显示的文章
const paginatedArticles = computed(() => {
  const start = (currentPage.value - 1) * pageSize;
  const end = start + pageSize;
  return articles.value.slice(start, end);
});

// 获取文章列表数据
async function fetchArticles() {
  try {
    const response = await instance.get('/articles');
    articles.value = response.data;
  } catch (error) {
    console.error('Error fetching articles:', error);
  }
}

// 在组件挂载时调用API获取数据
onMounted(() => {
  fetchArticles();
});

// 切换页面
function handlePageChange(page: number) {
  currentPage.value = page;
}

// 查看文章详情，弹出弹窗
function viewArticle(article: Article) {
  console.log('Article selected:', article); // 调试输出
  selectedArticle.value = article;
  dialogVisible.value = true;
  console.log('Dialog visible:', dialogVisible.value); // 调试输出
}
</script>

<style scoped>
.article-list-container {
  max-width: 900px;
  margin-left: 450px;
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
  transition: transform 0.3s ease, box-shadow 0.3s ease;
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
  margin-left: 130px;
  width: 100%; /* 设置为100%以占满父容器 */
  text-align: center; /* 确保内容居中 */
  background-color: transparent;
}



.dialog-article-image {
  width: 40%;
  height: auto;
  object-fit: cover;
  margin-top: 10px;
  margin-left: 250px;
  margin-right: auto;
  margin-bottom: 10px;
  border-radius: 10px;
}

.indented-text {
  text-indent: 2em; 
  text-align: center;
}
</style>
