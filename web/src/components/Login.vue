<template>
  <div class="login-form">
    <el-form ref="form" label-width="80px" @submit.prevent="handleSubmit">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="username" type="text" placeholder="请输入用户名" required></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="password" type="password" placeholder="密码" required></el-input>
      </el-form-item>
      <el-form-item align="center">
        <el-button type="primary" size="mini" @click="handleSubmit">登录</el-button>
      </el-form-item>

      <div class="tips" style="float: left">
        <el-link underline="true" @click="retrievePWD">忘记密码</el-link>
      </div>
      <div class="tips" style="float: right">
        <el-link @click="regis">还没有账号？点击注册</el-link>
      </div>
    </el-form>
  </div>
</template>

<script setup lang="ts">
import router from '../router'
import { ref } from 'vue'
import instance from '../axios'
import md5 from 'crypto-js/md5'
import { ElMessage } from 'element-plus'

const username = ref('')
const password = ref('')
const token = ref('')
const permission = ref('')
const uid = ref('')

// 定义提交处理函数
const handleSubmit = async () => {
  try {
    const newPwd = md5(password.value).toString()
    const response = await instance.post(
      '/user/auth/login',
      {
        userName: username.value,
        userPassword: newPwd
      },
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    )
    const data = response.data.data
    if (response.data.code == 106) {
      ElMessage.error('该账号已被封禁！')
      return
    } else if (response.data.code == 104) {
      ElMessage.error('用户名或密码错误')
      return
    }
    token.value = data.token
    permission.value = data.permission
    uid.value = data.uid
    localStorage.setItem('token', token.value)
    localStorage.setItem('permission', permission.value)
    const response2 = await instance.get('/user/userInfo')
    console.log(response2)
    localStorage.setItem('uid', response2.data.data.uid)
    localStorage.setItem('userName', response2.data.data.userName)
    localStorage.setItem('sex', response2.data.data.sex == '0' ? '女' : '男')
    localStorage.setItem('birthday', response2.data.data.birthday)
    window.location.href = '/'
  } catch (error) {
    console.log(error)
    ElMessage.error('网络错误！')
    username.value = ''
    password.value = ''
  }
}

const retrievePWD = () => {
  router.push('/forgetpassword')
}

const regis = () => {
  router.push('/register')
}
</script>

<style>
.login-form {
  margin: 150px auto;
  padding: 60px;
  width: 350px;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  background-color: #ffffff;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.form-group {
  margin-bottom: 20px;
}

label {
  display: block;
  margin-bottom: 8px;
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

button {
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

button:hover {
  background-color: #0056b3;
}

button:active {
  background-color: #004085;
  box-shadow: 0 2px 4px rgba(0, 123, 255, 0.3);
}

.tips {
  margin-top: 20px;
  font-size: 14px;
}

.tips el-link {
  color: #000000;
}

.el-link .el-icon--right.el-icon {
  vertical-align: text-bottom;
}
</style>
