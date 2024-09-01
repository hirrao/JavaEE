<template>
    <div class="login-form">
      <!--element版-->
      <el-form ref="form" label-width="80px">
        <el-form-item label="用户名" prop="username">
            <el-input v-model="username" type="text" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phonenumber">
          <div class="form-group">
            <div class="input-container">
              <el-input id="phonenumber" v-model="phonenumber" type="text" placeholder="请输入手机号"></el-input>
              <el-button class="button1" type="primary" :disabled="isButtonDisabled" @click="sendVerificationCode">
                {{ isButtonDisabled ? `${countdown}秒后重发` : '发送验证码' }}
              </el-button>
            </div>
          </div>
        </el-form-item>
        <el-form-item label="验证码" prop="verificationCode">
            <el-input v-model="verificationCode" type="text" placeholder="请输入验证码"></el-input>
        </el-form-item>
        <el-form-item align="center">
          <el-button class="button2" type="primary" size="mini" @click="next">下一步</el-button>
        </el-form-item>
      </el-form>

      <!-- <form>
        <div class="form-group">
          <label for="username">用户名:</label>
          <input type="text" id="username" v-model="username" required />
        </div>
  
        <div class="form-group">
          <label for="phonenumber">手机号:</label>
          <div class="input-container">
            <input type="text" id="phonenumber" v-model="phonenumber" required />
            <button class="button1" :disabled="isButtonDisabled" @click="sendVerificationCode">
              {{ isButtonDisabled ? `${countdown}秒后重发` : '发送验证码' }}
            </button>
          </div>
        </div>
  
        <div class="form-group">
          <label for="verification-code">验证码:</label>
          <input type="text" id="verification-code" v-model="verificationCode" required />
          <button class="button2" @click="next">下一步</button>
        </div>
      </form> -->
    </div>
  </template>
  
  <script setup lang="ts">
  import { ref } from 'vue'
  import router from '../router'
  import instance from '../axios';
  // 定义响应式数据
  const username = ref('')
  const phonenumber = ref('')
  const verificationCode = ref('')
  const isButtonDisabled = ref(false)
  const countdown = ref(60)
  let timer: ReturnType<typeof setInterval> | undefined
  
  const sendVerificationCode = async () => {
    if (isButtonDisabled.value) return
  

    const response = await instance.post('user/auth/messageSend', {
        phoneNumber: phonenumber.value,
    },{
        headers: {
          'Content-Type': 'application/json',
        },
    })
    console.log(phonenumber);
    alert(`${response.data.message}`)
  
    // 禁用按钮并开始倒计时
    isButtonDisabled.value = true
    countdown.value = 60
    timer = setInterval(() => {
      if (countdown.value > 0) {
        countdown.value--
      } else {
        clearInterval(timer)
        isButtonDisabled.value = false
      }
    }, 1000)
  }

  const next =() => {
    router.push('/setPassword')
  }

  // let loginMessage={
  //   username:"",
  //   phonenumber : "",
  //   verificationCode : ""
  // }

  // const rules={
  //   username:[{required:true,message:"用户名不能为空",trigger:"blur"}],
  //   phonenumber:[{required:true,message:"手机号不能为空",trigger:"blur"}],
  //   verificationCode:[{required:true,message:"验证码不能为空",trigger:"blur"}]
  // }
  </script>
  
  <style scoped>
  .login-form {
    margin: 150px auto;
    padding: 60px;
    width: 400px;
    border-radius: 10px;
    background-color: #ffffff;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }
  
  .form-group {
    margin-bottom: 20px;
  }
  
  .input-container {
    display: flex;
    align-items: center;
    gap:10px;
  }
  
  .input-container input {
    flex: 10;
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
    width:40%;
    padding: 10px 10px;
    padding-left: 20px;
    font-size: 16px;
    background-color: #007bff;
    color: #ffffff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    box-shadow: 0 4px 6px rgba(0, 123, 255, 0.3);
  }
  
  .button1:disabled {
    background-color: #cccccc;
    cursor: not-allowed;
  }
  
  .button2 {
    margin-top: 10px;
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
  
  .button2:hover {
    background-color: #0056b3;
  }
  
  .button2:active {
    background-color: #004085;
    box-shadow: 0 2px 4px rgba(0, 123, 255, 0.3);
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
  </style>
  