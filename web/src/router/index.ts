// src/router/index.ts
import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router'
import { ElMessage } from 'element-plus'
import Home from '../components/Home.vue'
import Login from '../components/Login.vue'
import BloodPressure from '../components/BloodPressure.vue'
import Register from '@/components/Register.vue'
import ForgetPassword from '@/components/ForgetPassword.vue'
import HealthAdvice from '@/components/HealthAdvice.vue'
import Medicine from '@/components/Medicine.vue'
import SetPassword from '@/components/SetPassword.vue'
import Profile from '@/components/Profile.vue'
import AccountManagement from '../components/AccountManagement.vue'
import HealthAdviceAdmin from '@/components/HealthAdviceAdmin.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/*',
    component: () => import('@/pages/MainLayout.vue'),
    children: [
      {
        path: '/',
        name: 'Home',
        component: Home
      },
      {
        path: '/login',
        name: 'Login',
        component: Login
      },
      {
        path: '/bloodPressure',
        name: 'BloodPressure',
        component: BloodPressure
      },
      {
        path: '/register',
        name: 'Register',
        component: Register
      },
      {
        path: '/forgetPassword',
        name: 'ForgetPassword',
        component: ForgetPassword
      },
      {
        path: '/healthAdvice',
        name: 'HealthAdvice',
        component: HealthAdvice
      },
      {
        path: '/medicine',
        name: 'Medicine',
        component: Medicine
      },
      {
        path: '/setPassword',
        name: 'SetPassword',
        component: SetPassword
      },
      {
        path: '/profile',
        name: 'Profile',
        component: Profile
      },
      {
        path: '/accountManagement',
        name: 'AccountManagement',
        component: AccountManagement
      },
      {
        path: '/healthAdviceAdmin',
        name: 'HealthAdviceAdmin',
        component: HealthAdviceAdmin
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  const messageCode = localStorage.getItem('messageCode')
  const token = localStorage.getItem('token')
  // 检查用户访问注册页面时是否存在验证码
  if (to.name === 'SetPassword' && !messageCode) {
    ElMessage.error('未检测到验证码，请重新获取验证码。')
    next({ name: 'Home' }) // 重定向到首页或其他页面
  }
  // 检查用户是否已登录
  else if (token === null) {
    if (to.name === 'Profile' || to.name === 'BloodPressure' || to.name === 'Medicine') {
      ElMessage.error('请先登录')
      next({ name: 'Login' })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
