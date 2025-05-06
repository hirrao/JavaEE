import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vueDevtools from 'vite-plugin-vue-devtools'
import { resolve } from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  build: {
    outDir: 'dist'
  },
  plugins: [vue(), vueDevtools()],
  resolve: {
    alias: {
      '@': resolve('./src')
    }
  },
  server: {
    port: 8080,
    open: true
  }
})
