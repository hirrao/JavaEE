<script lang="ts" setup>
import { RouterLink, useRoute, useRouter } from 'vue-router'
import { NLayoutHeader, NFlex, NButton, type MenuOption, NMenu } from 'naive-ui'
import { computed, h } from 'vue'
import { Locator } from '@/data'

const router = useRouter()
const route = useRoute()
const userRepository = Locator.userRepository()

const renderLabel = (text: string, href: string) => () =>
  h(RouterLink, { to: href }, { default: () => text })
const menuOptions = computed<MenuOption[]>(() => {
  const options: MenuOption[] = [
    {
      label: renderLabel('首页', '/'),
      key: '/'
    }
  ]
  /* 
      <template v-if="isLoggedIn">
        <el-menu-item index="/profile">{{ userName }}</el-menu-item>
        <el-menu-item @click="logout">登出</el-menu-item>
      </template>
      <template v-else>
        <el-menu-item index="/login">登录</el-menu-item>
        <el-menu-item index="/register">注册</el-menu-item>
      </template>*/
  if (userRepository.isAdmin()) {
    options.push(
      {
        label: renderLabel('账号管理', '/accountManagement'),
        key: '/accountManagement'
      },
      {
        label: renderLabel('文章管理', '/healthAdviceAdmin'),
        key: '/healthAdviceAdmin'
      }
    )
  } else {
    options.push(
      {
        label: renderLabel('血压管理', '/bloodPressure'),
        key: '/bloodPressure'
      },
      {
        label: renderLabel('药物管理', '/medicine'),
        key: '/medicine'
      },
      {
        label: renderLabel('健康一言', '/healthAdvice'),
        key: '/healthAdvice'
      }
    )
  }
  if (userRepository.isLoggedIn()) {
    options.push(
      {
        label: renderLabel(`${userRepository.ref.value.username}`, '/profile'),
        key: '/profile'
      },
      {
        label: renderLabel('登出', '/logout'),
        key: '/logout',
        onclick: () => {
          userRepository.clear()
          router.push('/')
        }
      }
    )
  } else {
    options.push(
      {
        label: renderLabel('登录', '/login'),
        key: '/login'
      },
      {
        label: renderLabel('注册', '/register'),
        key: '/register'
      }
    )
  }
  return options
})
const menuKey = computed(() => route.path)
</script>

<template>
  <n-layout-header>
    <n-flex justify="center">
      <n-button @click="() => router.push('/')"> 智慧心康 </n-button>
      <n-menu mode="horizontal" :options="menuOptions" :value="menuKey"></n-menu>
    </n-flex>
  </n-layout-header>
</template>
