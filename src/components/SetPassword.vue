<template>
    <div class="login-form">
      <form>
        <div class="form-group">
          <label for="password">请输入密码:</label>
          <input type="password" id="password" v-model="password" required />
        </div>
  
        <div class="form-group">
          <label for="password2">请再次确认密码:</label>
          <input type="password" id="password2" v-model="password2" required />
        </div>
  
        <button class="button1" @click="next">提交</button>
      </form>
    </div>
  </template>
  
  <script setup lang="ts">
  import { ref } from 'vue'
  import router from '../router'
  let password = ref('')
  let password2 = ref('')
  
  const next = () => {
    if (password.value !== password2.value) {
      alert("两次密码不一致，请重新输入")
    } else {
      alert("密码设置成功")
      router.push('/login')
    }
  }
  </script>
  
  <style scoped>
  .login-form {
    margin: 100px auto;
    padding: 40px;
    width: 350px;
    border-radius: 8px;
    background-color: #ffffff;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
    color: #333333;
  }
  
  input {
    width: 100%;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #cccccc;
    border-radius: 5px;
    background-color: #f9f9f9;
    box-shadow: inset 0 1px 3px rgba(0, 0, 0, 0.1);
  }
  
  input:focus {
    border-color: #007bff;
    background-color: #ffffff;
    box-shadow: 0 0 5px rgba(0, 123, 255, 0.25);
    outline: none;
  }
  
  .button1 {
    width: 100%;
    padding: 12px;
    font-size: 16px;
    background-color: #007bff;
    color: #ffffff;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    box-shadow: 0 4px 6px rgba(0, 123, 255, 0.3);
  }
  
  .button1:hover {
    background-color: #0056b3;
  }
  
  .button1:active {
    background-color: #004085;
    box-shadow: 0 2px 4px rgba(0, 123, 255, 0.3);
  }
  </style>
  