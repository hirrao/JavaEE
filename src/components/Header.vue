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
          <a href="/login">登录</a>  
          <a href="/register">注册</a>  
        </nav>  
      </div>  
</template>

<script setup>
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