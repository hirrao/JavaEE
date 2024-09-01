<template>
     <div class="header-nav-container">  
        <header>  
          <h2>智慧心康</h2>  
        </header>  
  
        <nav>  
          <a href="/">首页</a>  
          <a href="/bloodPressure">血压管理</a>  
          <a href="/medicine">药物管理</a>  
          <a href="/healthAdvice">健康贴士</a>  
          <template v-if="isLoggedIn">
          <a href="/profile">个人中心</a>
          <a href="/" @click="logout">登出</a>
          </template>
          <template v-else>
          <a href="/login">登录</a>  
          <a href="/register">注册</a>
          </template>
        </nav>  
      </div>  
</template>

<script setup>
  import router from '@/router';
  import { ref } from 'vue';
  let isLoggedIn = ref(!!localStorage.getItem('token'));

  function logout() {
    // 删除 token 并更新登录状态
    localStorage.removeItem('token');
    isLoggedIn.value = false;
    router.push('/');
  }
</script>
<style>
    .header-nav-container {  
      display: flex;  
      justify-content: space-between; 
      align-items: center;  
      background-color: rgb(0, 0, 0);;  
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
</style>