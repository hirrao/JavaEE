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
    if(phonenumber.value.length != 11 || isNaN(Number(phonenumber.value)) || phonenumber.value[0] != '1'){
      alert("请输入正确的手机号")
      return
    }

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

  const next = async () => {
    if(username.value == ""){
      alert("请输入用户名")
      return
    }
    if(verificationCode.value == ""){
      alert("请输入验证码")
      return
    }
    try{
      await instance.post('/user/auth/find',{
        userName: username.value,
        phoneNumber: phonenumber.value,
      },{
        headers: {
          'Content-Type': 'application/json',
        },
      })
    } catch(error){
      alert('用户名或手机号已存在')
      username.value = ''
      phonenumber.value = ''
      verificationCode.value = ''
      return
    }
    try{
      const response = await instance.post('/user/auth/message', {
        userName: username.value,
        phoneNumber: phonenumber.value,
        messageCode: verificationCode.value,
      },{
        headers: {
          'Content-Type': 'application/json',
        },
      });
      localStorage.setItem('userName', username.value)
      localStorage.setItem('phoneNumber', phonenumber.value)
      localStorage.setItem('messageCode', verificationCode.value)
      router.push('/setPassword')
    }
      catch(error){
        alert('验证码错误')
        verificationCode.value = ''
    }
  }
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
  