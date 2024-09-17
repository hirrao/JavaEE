<template>
  <div class="ring-chart">
    <div ref="echartsContainer" class="echarts-container" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watch, type PropType } from 'vue'
import * as echarts from 'echarts'

// 接收来自父组件的 chartData prop
const props = defineProps({
  chartData: {
    type: Object as PropType<{
      重度: number
      中度: number
      轻度: number
      正常高值: number
      偏低: number
    }>,
    required: true
  }
})

const echartsContainer = ref(null)

onMounted(() => {
  if (echartsContainer.value) {
    const myChart = echarts.init(echartsContainer.value)

    window.addEventListener('resize', () => {
      if (myChart) {
        myChart.resize()
      }
    })

    // 定义更新图表的方法
    const updateChart = () => {
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
                value: props.chartData.正常高值 || 0,
                name: '正常高值',
                itemStyle: {
                  color: '#F1C226'
                }
              },
              {
                value: props.chartData.偏低 || 0,
                name: '偏低',
                itemStyle: {
                  color: '#76A0D4'
                }
              },
              {
                value: props.chartData.轻度 || 0,
                name: '轻度',
                itemStyle: {
                  color: '#E7A029'
                }
              },
              {
                value: props.chartData.中度 || 0,
                name: '中度',
                itemStyle: {
                  color: '#F27E10'
                }
              },
              {
                value: props.chartData.重度 || 0,
                name: '重度',
                itemStyle: {
                  color: '#CC5557'
                }
              }
            ],
            radius: ['55%', '70%']
          }
        ]
      }
      myChart.setOption(option)
    }

    // 初次挂载时更新图表
    updateChart()

    // 监听 chartData 的变化并更新图表
    watch(() => props.chartData, updateChart, { deep: true })
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
