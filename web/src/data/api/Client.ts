import axios from 'axios'
import JSONbig from 'json-bigint'

const JSONbigToString = JSONbig({ storeAsString: true })

export const Client = axios.create({
  baseURL: 'http://localhost:5173/api',
  timeout: 10000,
  headers: { 'Content-Type': 'application/json' },
  transformResponse: [
    function (data) {
      try {
        // 转换
        return JSONbigToString.parse(data)
      } catch (error) {
        // 转换失败按元数据返回
        return data
      }
    }
  ]
})

// 请求拦截器
Client.interceptors.request.use(
  (config) => {
    // 从 localStorage 获取 token
    const token = localStorage.getItem('token')
    if (token !== null) {
      // 如果 token 存在，直接将 token 添加到请求头中
      config.headers.Authorization = token
    }
    return config
  },
  (error) => {
    return Promise.reject(error)
  }
)
