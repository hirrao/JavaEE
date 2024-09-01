// src/router/index.ts
import { createRouter, createWebHistory, type RouteRecordRaw } from 'vue-router';
import Home from '../components/Home.vue';
import Login from '../components/Login.vue';
import BloodPressure from '../components/BloodPressure.vue';
import Register from '@/components/Register.vue';
import ForgetPassword from '@/components/ForgetPassword.vue';
import HealthAdvice from '@/components/HealthAdvice.vue';
import Medicine from '@/components/Medicine.vue';
import SetPassword from '@/components/SetPassword.vue';
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
  },
  {
    path: '/bloodPressure',
    name: 'BloodPressure',
    component:BloodPressure
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
  }
];

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes,
  });

export default router;