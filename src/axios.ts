// src/axios.ts
import axios from 'axios';
import JSONbig from 'json-bigint'

const JSONbigToString=JSONbig({storeAsString:true})

const instance = axios.create({
  baseURL: 'https://api.javaee.hirrao.cn', // 你的API基础URL
  timeout: 10000, // 请求超时的时间限制
  headers: { 'Content-Type': 'application/json' },
  transformResponse:[function(data){
    try{
      //转换
      return JSONbigToString.parse(data)
    }catch(error){
      //转换失败按元数据返回
      return data
    }
  }]
});


// // 请求拦截器
// instance.interceptors.request.use(
//   config => {
//     // 在发送请求之前可以做一些处理，例如添加token
//     // config.headers.Authorization = `Bearer ${token}`;
//     return config;
//   },
//   error => {
//     return Promise.reject(error);
//   }
// );

// // 响应拦截器
// instance.interceptors.response.use(
//   response => {
//     return response.data;
//   },
//   error => {
//     // 处理响应错误
//     return Promise.reject(error);
//   }
// );

export default instance;
