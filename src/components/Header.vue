<template>
  <div class="header-nav-container">
    <header>
      <h2>智慧心康</h2>
    </header>

    <el-menu
      :default-active="currentRoute"
      class="el-menu-demo"
      mode="horizontal"
      :ellipsis="false"
      @select="handleSelect"
      unique-opened
      router
    >
      <el-menu-item index="/">首页</el-menu-item>
      <el-menu-item index="/bloodPressure">血压管理</el-menu-item>
      <el-menu-item index="/medicine">药物管理</el-menu-item>
      <el-menu-item index="/healthAdvice">健康贴士</el-menu-item>
      <template v-if="isLoggedIn">
        <el-menu-item index="/profile">个人中心</el-menu-item>
        <el-menu-item @click="logout">登出</el-menu-item>
      </template>
      <template v-else>
        <el-menu-item index="/login">登录</el-menu-item>
        <el-menu-item index="/register">注册</el-menu-item>
      </template>
    </el-menu>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()

const isLoggedIn = ref(!!localStorage.getItem('token'))

const currentRoute = computed(() => route.path)

function handleSelect(index: string) {
  router.push(index)
}

function logout() {
  // 删除 token 并更新登录状态
  localStorage.removeItem('token')
  isLoggedIn.value = false
  router.push('/')
}
</script>

<style>
.header-nav-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: rgb(0, 0, 0);
  color: white;
  padding: 0px 20px;
}

nav {
  display: flex;
}

nav a {
  margin-left: 20px;
  text-decoration: none;
  color: white;
}

.el-menu {
  --el-menu-bg-color: rgba(20, 1, 1);
  --el-menu-text-color: white;
  /* margin: 0 auto;  */
}

/* 去掉下划线 */
.el-menu.el-menu--horizontal.el-menu-demo {
  border-bottom: none;
  /* border-bottom:solid 2px rgba(20, 1, 1); */
}

/* .el-menu-item.is-active{
background: rgba(20, 1, 1);;
} */

/* .el-menu--horizontal>.el-menu-item.is-active{
border-bottom: none;

} */
/* .el-menu--horizontal > .el-menu-item{
border-bottom: none;
text-decoration: none;
} */
</style>
