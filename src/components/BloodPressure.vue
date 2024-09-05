<template>
  <div style="display: flex; flex-direction: column; align-items: center">
    <div style="background: white; width: 99vw; text-align: center; margin-top: 2.5%">
      <h2>这是您最近七天的血压数据</h2>
      <el-button
        id="dialogVisbleBtn"
        type="primary"
        size="large"
        style="width: 10%"
        @click="DialogVisble"
        >上传新的血压数据</el-button
      >
      <el-dialog title="添加血压记录" v-model="addDialogVisble" width="80%">
        <el-form ref="form" :model="addBloodPressure" label-width="80px">
          <el-form-item label="高压（收缩压）" prop="SBP">
            <el-input-number
              v-model="addBloodPressure.SBP"
              placeholder="请输入高压（收缩压）"
            ></el-input-number>
          </el-form-item>
          <el-form-item label="低压（舒张压）" prop="DBP">
            <el-input-number
              v-model="addBloodPressure.DBP"
              placeholder="请输入低压（舒张压）"
            ></el-input-number>
          </el-form-item>
          <el-form-item label="测量时间" prop="" recordTime>
            <el-date-picker
              v-model="addBloodPressure.recordTime"
              type="datetime"
              value-format="YYYY-MM-DD HH:mm:ss"
              placeholder="请选择测量时间"
            ></el-date-picker>
          </el-form-item>
          <el-form-item align="center">
            <el-button type="primary" size="mini" @click="AddRecord">添加</el-button>
            <el-button type="info" size="mini" @click="CloseDialog">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>

    <div style="display: flex; align-items: center; width: 99vw">
      <div class="blood-pressure-log-chart" style="width: 80%">
        <BPLChart1 :chartData="chartData1" />
      </div>
      <div class="blood-pressure-log-chart" style="width: 20%">
        <BPLChart2 :chartData="riskLevel" />
      </div>
    </div>
    <div class="blood-pressure-log-detail-table">
      <table>
        <thead>
          <tr>
            <th>指标</th>
            <th>最高</th>
            <th>最低</th>
            <th>平均</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>收缩压</td>
            <td>{{ maxSBP }}</td>
            <td>{{ minSBP }}</td>
            <td>{{ avgSBP }}</td>
          </tr>
          <tr>
            <td>舒张压</td>
            <td>{{ maxDBP }}</td>
            <td>{{ minDBP }}</td>
            <td>{{ avgDBP }}</td>
          </tr>
          <tr>
            <td>脉压差</td>
            <td>{{ maxPP }}</td>
            <td>{{ minPP }}</td>
            <td>{{ avgPP }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import instance from '../axios'
import BPLChart1 from './BPLChart1/index.vue'
import BPLChart2 from './BPLChart2/index.vue'

let addDialogVisble = ref(false)
let uid = localStorage.getItem('uid')

let riskLevel = ref({
  重度: 1,
  中度: 1,
  轻度: 2,
  正常高值: 1,
  偏低: 2
})

let addBloodPressure = ref({
  SBP: null as number | null,
  DBP: null as number | null,
  recordTime: ''
})

let chartData1 = ref({
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
let maxSBP = ref(140)
let minSBP = ref(90)
let avgSBP = ref(90)
let maxDBP = ref(90)
let minDBP = ref(60)
let avgDBP = ref(60)
let maxPP = ref(50)
let minPP = ref(30)
let avgPP = ref(30)

function DialogVisble() {
  addDialogVisble.value = true
}
function CloseDialog() {
  addDialogVisble.value = false
  addBloodPressure.value.SBP = null
  addBloodPressure.value.DBP = null
  addBloodPressure.value.recordTime = ''
}
function AddRecord() {
  //校验填写表单中的数据
  if (!addBloodPressure.value.SBP) {
    ElMessage.error('请填写高压（收缩压）')
    return
  }
  if (
    addBloodPressure.value.SBP &&
    (addBloodPressure.value.SBP < 0 || addBloodPressure.value.SBP > 300)
  ) {
    ElMessage.error('请检查您的高压（收缩压）是否填写正确')
    return
  }
  if (!addBloodPressure.value.DBP) {
    ElMessage.error('请填写低压（舒张压）')
    return
  }
  if (
    addBloodPressure.value.DBP &&
    (addBloodPressure.value.DBP < 0 || addBloodPressure.value.DBP > 200)
  ) {
    ElMessage.error('请检查您的低压（舒张压）是否填写正确')
    return
  }
  if (!addBloodPressure.value.recordTime || addBloodPressure.value.recordTime == '') {
    ElMessage.error('请填写您的测量时间')
    return
  }

  if (uid) {
    let userId = uid
    let sbp = addBloodPressure.value.SBP
    let dbp = addBloodPressure.value.DBP
    let date = addBloodPressure.value.recordTime

    //上传血压数据
    instance
      .post('/bp/record/insert', { userId: userId, sbp: sbp, dbp: dbp, date: date })
      .then((response) => {
        // 处理成功响应
        ElMessage.success('血压记录已成功添加')
        console.log('Response:', response.data)
        addDialogVisble.value = false
      })
      .catch((error) => {
        // 处理错误响应
        ElMessage.error('血压记录添加失败，请稍后重试')
        console.error('Error:', error)
      })
  }
}

function mapBackendDataToChart(backendData: any, categories: any) {
  // 初始化 data 数组，将每个元素都设为 [null, null]，表示高压和低压的数据
  let data = categories.map(() => [0, 0])

  // 遍历后端返回的数据
  backendData.forEach((record) => {
    // 找到当前 record 的 recordTime 在 categories 中的位置
    let index = categories.indexOf(record.recordTime)

    // 如果找到了对应的日期
    if (index !== -1) {
      // 将该日期的高压（SBP）和低压（DBP）存储到 data 数组中对应的位置
      data[index] = [record.sbp, record.dbp]
    }
  })

  return data
}

function getChartData1() {
  let date = new Date()
  let year = date.getFullYear()
  let month = (date.getMonth() + 1).toString().padStart(2, '0') // 月份补零
  let day = date.getDate().toString().padStart(2, '0') // 日期补零
  let now = year + '-' + month + '-' + day
  console.log(now)
  // 计算7天前的日期
  let pastDate = new Date(date) // 复制当前日期对象
  pastDate.setDate(pastDate.getDate() - 6) // 将日期设为7天前
  let categories = []

  // 使用循环填充past到now之间的日期
  while (pastDate <= date) {
    let tempYear = pastDate.getFullYear()
    let tempMonth = (pastDate.getMonth() + 1).toString().padStart(2, '0') // 月份补零
    let tempDay = pastDate.getDate().toString().padStart(2, '0') // 日期补零

    // 将格式化后的日期加入categories数组
    categories.push(tempYear + '-' + tempMonth + '-' + tempDay)

    // 将日期递增一天
    pastDate.setDate(pastDate.getDate() + 1)
  }
  instance
    .post('/bp/record/uid', { date: now })
    .then((res: any) => {
      let data = mapBackendDataToChart(res.data.data, categories)
      console.log(data)
      chartData1.value.data = data
    })
    .catch((err: any) => {
      console.log('请求血压记录图数据失败', err)
      ElMessage.error('血压记录图表数据加载失败，请检查网络')
    })
  chartData1.value.categories = categories
}

function getChartData2() {
  let date = new Date()
  let year = date.getFullYear()
  let month = (date.getMonth() + 1).toString().padStart(2, '0') // 月份补零
  let day = date.getDate().toString().padStart(2, '0') // 日期补零
  let now = year + '-' + month + '-' + day
  instance.post('/bp/record/riskLevel', { date: now }).then((res: any) => {
    console.log(res)
    // 初始化风险级别计数对象
    let riskLevelCounts = {
      重度: 0,
      中度: 0,
      轻度: 0,
      正常高值: 0,
      偏低: 0
    }

    // 遍历后端返回的data数组，统计每个风险级别的数量
    if (res.data.data && Array.isArray(res.data.data)) {
      res.data.data.forEach((level: string) => {
        if (riskLevelCounts.hasOwnProperty(level)) {
          riskLevelCounts[level] += 1
        }
      })
    }

    // 覆盖riskLevel对象的值
    riskLevel.value = { ...riskLevelCounts }

    console.log('现在的风险次数统计是', riskLevel.value)
  })
}

function getTableData() {
  let date = new Date()
  let year = date.getFullYear()
  let month = (date.getMonth() + 1).toString().padStart(2, '0') // 月份补零
  let day = date.getDate().toString().padStart(2, '0') // 日期补零
  let now = year + '-' + month + '-' + day
  //TODO:图表后端逻辑
  instance.post('/bp/record/table', { date: now }).then((res: any) => {
    console.log('图表的数据是:', res)
    let data = res.data.data
    maxSBP.value = data.maxSBP
    minSBP.value = data.minSBP
    avgSBP.value = data.avgSBP
    maxDBP.value = data.maxDBP
    minDBP.value = data.minDBP
    avgDBP.value = data.avgDBP
    maxPP.value = data.maxPP
    minPP.value = data.minPP
    avgPP.value = data.avgPP
  })
}
onMounted(() => {
  getChartData1()
  getChartData2()
  console.log('更新图数据')
  getTableData()
})
</script>

<style>
.el-button {
  width: 100px;
  height: 30px;
}
.blood-pressure-log-chart {
  display: flex;
  justify-content: center;
  padding: 2%;
  width: 96vw;
  height: 30vh;
  background: white;
}

.blood-pressure-log-detail-table {
  display: flex;
  justify-content: center;
  width: 99vw;
  background: white;
}

table {
  width: 100%;
  font-size: 16px;
  text-align: center;
  border-collapse: collapse;
}

th,
td {
  padding: 8px;
  width: 25%;
  border: 1px solid #ddd;
}

th {
  background-color: #f2f2f2;
}

.badge {
  display: block;
  padding: 2px 6px;
  margin-top: 4px;
  font-size: 12px;
  color: #fff;
  border-radius: 4px;
}

.badge-normal {
  background-color: #4caf50;
}

.badge-moderate {
  background-color: #ffc107;
}

.badge-high {
  background-color: #f44336;
}
</style>
