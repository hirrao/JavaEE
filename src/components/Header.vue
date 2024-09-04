<template>
  <div class="header-nav-container">
    <header>
      <h2>智慧心康</h2>
    </header>

    <el-menu :default-active="currentRoute" class="el-menu-demo" mode="horizontal" :ellipsis="false"
      @select="handleSelect" unique-opened router>
      <el-menu-item index="/">首页</el-menu-item>
      <template v-if="isAdmin()">
        <el-menu-item index="/accountManagement">账号管理</el-menu-item>
        <el-menu-item index="/healthAdviceAdmin">文章管理</el-menu-item>
      </template>
      <template v-else>
        <el-menu-item index="/bloodPressure">血压管理</el-menu-item>
        <el-menu-item index="/medicine">药物管理</el-menu-item>
        <el-menu-item index="/healthAdvice">健康一言</el-menu-item>
      </template>
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
import { ref, computed } from 'vue';
import { useRouter, useRoute } from 'vue-router';
import instance from '@/axios';

const router = useRouter();
const route = useRoute();

const isLoggedIn = ref<boolean | null>(null);

(async () => {
  try {
    const response = await instance.get('/user/userInfo', {
      headers: {
        'Content-Type': 'application/json',
      },
    });
    isLoggedIn.value = response.status === 200;
  } catch (error) {
    isLoggedIn.value = false;
  }
})();


const currentRoute = computed(() => route.path);

function handleSelect(index: string) {
  router.push(index);
}

function logout() {
  localStorage.clear();
  window.location.href='/';
}

function isAdmin() {
  console.log("permission" + localStorage.getItem('permission'))
  console.log("token" + localStorage.getItem('token'))
  if (localStorage.getItem('permission') == null) {
    console.log("localStorage.getItem('permission')==null")
    return false
  }
  let temp = localStorage.getItem('permission')
  console.log(temp)
  if (temp === '1') {
    console.log("temp==='1'")
    return true
  }
  return false
}
</script>

<style>
.header-nav-container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: rgb(0, 0, 0);
  ;
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
  ;
  --el-menu-text-color: white;
}

/* 去掉下划线 */
.el-menu.el-menu--horizontal.el-menu-demo {
  border-bottom: none;
}
</style>