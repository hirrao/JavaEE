import axios from 'axios'
import { Locator } from '@/data'
const UserRepository = Locator.userRepository()
const instance = axios.create({
  baseURL: 'http://localhost:8088/api', // API基础URL
  timeout: 10000, // 请求超时的时间限制
  headers: { 'Content-Type': 'application/json' }
})

const unAuthUrls = ['/auth', '/healthAdvice']

export const Client = () => {
  const get = (url: string) => {
    if (unAuthUrls.some((unAuthUrl) => url.startsWith(unAuthUrl))) {
      return instance.get(url)
    }
    const token = UserRepository.ref.value.token
    if (token) {
      return instance.get(url, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
    } else {
      return Promise.reject(new Error('未登录或token失效'))
    }
  }
  const post = (url: string, data: unknown) => {
    if (unAuthUrls.some((unAuthUrl) => url.startsWith(unAuthUrl))) {
      return instance.post(url, data)
    }
    const token = UserRepository.ref.value.token
    if (token !== '') {
      return instance.post(url, data, {
        headers: {
          Authorization: `Bearer ${token}`
        }
      })
    } else {
      return Promise.reject(new Error('未登录或token失效'))
    }
  }
  return { get, post }
}
