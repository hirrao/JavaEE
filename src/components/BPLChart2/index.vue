<template>
  <div class="ring-chart">
    <div ref="echartsContainer" class="echarts-container" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from 'vue'
import * as echarts from 'echarts'

const echartsContainer = ref(null)
const risklevel = ref({ nomal: 1, low: 1, light: 2, middle: 1, high: 2 })

onMounted(() => {
  if (echartsContainer.value) {
    const myChart = echarts.init(echartsContainer.value)

    window.addEventListener('resize', () => {
      if (myChart) {
        myChart.resize()
      }
    })

    const option = {
      title: {
        text: '异常血压统计',
        left: 'center',
        top: 'center'
      },
      series: [
        {
          type: 'pie',
          data: [
            {
              value: risklevel.value.nomal,
              name: '正常高值',
              itemStyle: {
                color: '#F1C226'
              }
            },
            {
              value: risklevel.value.low,
              name: '偏低',
              itemStyle: {
                color: '#76A0D4'
              }
            },
            {
              value: risklevel.value.light,
              name: '轻度',
              itemStyle: {
                color: '#E7A029'
              }
            },
            {
              value: risklevel.value.middle,
              name: '中度',
              itemStyle: {
                color: '#F27E10'
              }
            },
            {
              value: risklevel.value.high,
              name: '重度',
              itemStyle: {
                color: '#CC5557'
              }
            }
          ],
          radius: ['65%', '80%']
        }
      ]
    }

    myChart.setOption(option)
  }
})
</script>

<style scoped>
.ring-chart {
  width: 100%;
  height: 100%;
}

.echarts-container {
  width: 100%;
  height: 100%;
}
</style>
