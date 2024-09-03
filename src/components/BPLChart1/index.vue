<template>
  <div class="pressure">
    <div ref="pressurechart" class="pressurechart" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import * as echarts from 'echarts'

const pressurechart = ref(null)
const pressuretype = ref({
  categories: [
    '2024-07-15',
    '2024-07-16',
    '2024-07-17',
    '2024-07-18',
    '2024-07-19',
    '2024-07-20',
    '2024-07-21'
  ],
  data: [
    [140, 90], // 高压，低压
    [136, 88], // 高压，低压
    [144, 92], // 高压，低压
    [136, 90], // 高压，低压
    [130, 84], // 高压，低压
    [135, 90], // 高压，低压
    [140, 90] // 高压，低压
  ]
})

onMounted(() => {
  if (pressurechart.value) {
    const myChart = echarts.init(pressurechart.value)
    window.addEventListener('resize', () => {
      if (myChart) {
        myChart.resize()
      }
    })

    const option = {
      grid: {
        x: 20, // 左
        y: 20, // 上
        x2: 20, // 右
        y2: 20 // 下
      },
      tooltip: {
        trigger: 'axis'
      },
      legend: {
        data: ['高压', '低压'],
        top: 5,
        right: 0
      },

      xAxis: {
        data: pressuretype.value.categories
      },
      yAxis: [
        {
          type: 'value'
        }
      ],
      series: [
        {
          name: '高压',
          type: 'line',
          data: pressuretype.value.data.map((item) => item[0])
        },
        {
          name: '低压',
          type: 'line',
          data: pressuretype.value.data.map((item) => item[1])
        }
      ]
    }

    myChart.setOption(option)
  }
})
</script>

<style scoped>
.pressure {
  width: 100%;
  height: 100%;
}

.pressurechart {
  width: 100%;
  height: 100%;
}
</style>
