<template>
  <div style="display: flex; flex-direction: column; align-items: center">
    <h2>sDJD</h2>

    <el-button id="dialogVisbleBtn" type="primary" size="mini" @click="DialogVisble"
      >上传血压数据</el-button
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
    <div style="display: flex; align-items: center; width: 99vw">
      <div class="blood-pressure-log-chart" style="width: 80%">
        <BPLChart1 />
      </div>
      <div class="blood-pressure-log-chart" style="width: 20%">
        <BPLChart2 />
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
            <td>146/71 <span class="badge badge-moderate">轻度</span></td>
            <td>115/88 <span class="badge badge-normal">正常高值</span></td>
            <td>131 <span class="badge badge-normal">正常高值</span></td>
          </tr>
          <tr>
            <td>舒张压</td>
            <td>88/115 <span class="badge badge-normal">正常高值</span></td>
            <td>71/146 <span class="badge badge-moderate">轻度</span></td>
            <td>80</td>
          </tr>
          <tr>
            <td>脉压差</td>
            <td>75 (146/71) <span class="badge badge-high">偏高</span></td>
            <td>27 (115/88) <span class="badge badge-normal">正常</span></td>
            <td>51 <span class="badge badge-normal">正常</span></td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import instance from '../axios'
import BPLChart1 from './BPLChart1/index.vue'
import BPLChart2 from './BPLChart2/index.vue'

let addDialogVisble = ref(false)
let uid = localStorage.getItem('uid')
let user = ref({
  riskLevel: {
    normal: 1,
    low: 1,
    light: 2,
    middle: 1,
    high: 2,
    get all() {
      return this.normal + this.low + this.light + this.middle + this.high
    }
  },
  averageSBP: 131,
  averageDBP: 88
})

let addBloodPressure = ref({
  SBP: null as number | null,
  DBP: null as number | null,
  recordTime: ''
})

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
  //TODO：向后端传输血压数据的逻辑

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
