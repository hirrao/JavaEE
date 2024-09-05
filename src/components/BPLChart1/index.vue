<template>
  <div class="pressure">
    <div ref="pressurechart" class="pressurechart" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch } from 'vue'
import * as echarts from 'echarts'

const pressurechart = ref(null)
const props = defineProps({
  chartData: {
    type: Object,
    default: () => ({ categories: [], data: [] })
  }
})

onMounted(() => {
  if (pressurechart.value) {
    const myChart = echarts.init(pressurechart.value)
    window.addEventListener('resize', () => {
      if (myChart) {
        myChart.resize()
      }
    })

    // 初次挂载时更新图表
    updateChart(props.chartData)
    console.log(props.chartData)

    // 监听 chartData 的变化并更新图表
    watch(
      () => props.chartData,
      (newData) => {
        if (myChart) {
          myChart.clear() // 清空图表
          updateChart(newData)
        }
      },
      { deep: true } // 深度监听，以捕获对象内部的变化
    )

    function updateChart(chartData) {
      if (!chartData || chartData.categories.length === 0 || chartData.data.length === 0) {
        return
      }

      const categories = chartData.categories
      const sbpData = chartData.data.map((item) => item[0]) // 提取高压数据
      const dbpData = chartData.data.map((item) => item[1]) // 提取低压数据

      const option = {
        grid: {
          x: 40, // 左
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
          data: categories
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
            data: sbpData
          },
          {
            name: '低压',
            type: 'line',
            data: dbpData
          }
        ]
      }

      myChart.setOption(option)
    }
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
