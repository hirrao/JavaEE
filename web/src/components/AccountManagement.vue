<template>
  <div>
    <!-- <h2 class="tital">账号管理</h2> -->
    <el-card class="card" body-style="justify-content: center;">
      <h2 class="tital">账号管理</h2>

      <div class="searchConditionDiv">
        <p style="margin-top: auto; margin-bottom: auto">查询条件：</p>
        <el-select
          class="selectCon"
          v-model="searchCondition"
          placeholder="查询条件"
          size="large"
          style="width: 10%; margin-top: auto; margin-bottom: auto"
          clearable
        >
          <el-option value="uid" label="uid" />
          <el-option value="userName" label="用户名" />
          <el-option value="sex" label="性别" />
          <el-option value="permission" label="权限" />
        </el-select>
        <el-input
          class="conditionInput"
          v-model="conditionValue"
          placeholder=""
          size="small"
          style="margin-top: auto; margin-bottom: auto"
          clearable
        ></el-input>
        <el-button
          type="primary"
          style="height: 40px; margin-right: 5px; margin-top: auto; margin-bottom: auto"
          @click="searchByCondition"
          >搜索</el-button
        >
        <el-button
          type="danger"
          style="height: 40px; margin-top: auto; margin-bottom: auto"
          @click="claerSearchCondition"
          >清空</el-button
        >
      </div>

      <el-table class="table" :data="Account">
        <el-table-column prop="uid" label="uid" width="200" />
        <el-table-column prop="userName" label="用户名" width="180" />
        <el-table-column prop="phoneNumber" label="电话号码" width="160" />
        <el-table-column prop="sex" label="性别" width="75" />
        <el-table-column prop="birthday" label="生日" />
        <el-table-column prop="permission" label="权限" width="75" />
        <el-table-column header-align="center" align="center" label="操作">
          <template v-slot="scoped">
            <el-button
              class="tableButton"
              type="primary"
              icon="el-icon-edit"
              @click="updateAccount(scoped.row)"
              >更新</el-button
            >
            <el-button
              class="tableButton"
              type="danger"
              icon="el-icon-delete"
              @click="deleteAccount(scoped.row.uid)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        class="paging"
        v-model:page-size="pageSize"
        v-model:current-page="currentPage"
        :page-sizes="[5, 10, 15, 20]"
        layout="sizes,prev,pager,next,jumper,->,total"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        v-model:total="total"
      >
      </el-pagination>
    </el-card>

    <el-dialog title="修改账号信息" v-model="dialogVisble" width="50%">
      <el-form
        ref="form"
        :model="modifyAccount"
        label-width="150px"
        style="margin-left: auto; margin-right: auto"
      >
        <el-form-item class="dialogInput" label="id" prop="uid">
          <el-input v-model="modifyAccount.uid" placeholder="请输入id" disabled></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="姓名" prop="userName">
          <el-input v-model="modifyAccount.userName" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="电话号码" prop="phoneNumber">
          <el-input v-model="modifyAccount.phoneNumber" placeholder="请输入电话号码"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="modifyAccount.sex">
            <el-radio value="男" size="large" label="男">男</el-radio>
            <el-radio value="女" size="large" label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker
            v-model="modifyAccount.birthday"
            default-value="2022-01-30 12:45:30"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
            placeholder="请选择生日"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="权限" prop="permission">
          <el-radio-group v-model="modifyAccount.permission">
            <el-radio :value="-1" size="large" label="-1">封禁</el-radio>
            <el-radio :value="0" size="large" label="0">普通用户</el-radio>
            <el-radio :value="1" size="large" label="1">管理员</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item align="center">
          <el-button type="primary" size="small" @click="AddRecord">更改</el-button>
          <el-button type="info" size="small" @click="CloseDialog">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import instance from '../axios'
import { onMounted, ref } from 'vue'

let Account = ref([])
const pageSize = ref(5)
const currentPage = ref(1)
const total = ref(0)
const dialogVisble = ref(false)
const searchCondition = ref('')
const conditionValue = ref('')
const modifyAccount = ref({
  uid: ref(''),
  userName: ref(''),
  phoneNumber: ref(''),
  sex: ref(''),
  birthday: ref(''),
  permission: ref('')
})

function updateAccount(row: {
  uid: string
  userName: string
  phoneNumber: string
  sex: string
  birthday: string
  permission: string
}) {
  dialogVisble.value = true
  modifyAccount.value = row
  console.log(modifyAccount.value)
  console.log(modifyAccount.value.permission)
}

async function deleteAccount(id: any) {
  let conf = confirm('是否删除')
  if (conf) {
    await instance
      .post(
        '/user/deleteUser',
        {
          uid: id.toString()
        },
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )
      .catch((error) => {
        ElMessage.error(error)
      })
    ElMessage('删除成功')
    searchByPage()
  }
}

function searchByPage() {
  instance
    .post(
      '/user/searchUserByCondition',
      {
        curPage: currentPage.value,
        size: pageSize.value,
        searchCondition: searchCondition.value,
        conditionValue: conditionValue.value
      },
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    )
    .then((response) => {
      console.log(response.data)
      //   Account=response.data.data.records;
      //   total=response.data.data.total;
      Account.value = response.data.data.records
      total.value = response.data.data.total
      console.log(Account)
    })
}

function searchByCondition() {
  if (searchCondition.value == '') ElMessage.error('请选择查询条件')
  else if (conditionValue.value == '') ElMessage.error('请输入搜索值')
  else {
    instance
      .post(
        '/user/searchUserByCondition',
        {
          curPage: currentPage.value,
          size: pageSize.value,
          searchCondition: searchCondition.value,
          conditionValue: conditionValue.value
        },
        {
          headers: {
            'Content-Type': 'application/json'
          }
        }
      )
      .then((response) => {
        console.log(response.data)
        Account.value = response.data.data.records
        total.value = response.data.data.total
        console.log(Account)
      })
  }
}

function claerSearchCondition() {
  searchCondition.value = ''
  conditionValue.value = ''
  searchByPage()
}

onMounted(() => {
  console.log('onMounted')
  searchByPage()
})

function handleSizeChange(newSize: number) {
  currentPage.value = 1
  searchByPage()
}

function handleCurrentChange(newPage: number) {
  searchByPage()
}

async function AddRecord() {
  await instance
    .post(
      '/user/modifyUserInfo',
      {
        uid: modifyAccount.value.uid.toString(),
        userName: modifyAccount.value.userName,
        phoneNumber: modifyAccount.value.phoneNumber,
        sex: modifyAccount.value.sex,
        birthday: modifyAccount.value.birthday,
        permission: modifyAccount.value.permission
      },
      {
        headers: {
          'Content-Type': 'application/json'
        }
      }
    )
    .catch((error) => {
      ElMessage.error(error)
    })
  searchByPage()
  dialogVisble.value = false
}

function CloseDialog() {
  dialogVisble.value = false
}
</script>

<style>
.tital {
  text-align: center;
}
.card {
  margin-left: auto;
  margin-right: auto;
  width: 90%;
  min-height: 80%;
}
.table {
  margin-left: auto;
  margin-right: auto;
  width: 100%;
}
.tableButton {
  width: 30%;
  height: 30%;
}
.paging {
  margin-left: auto;
  margin-right: auto;
  width: 100%;
}
.searchConditionDiv {
  margin-top: 20px;
  margin-bottom: 20px;
  display: flex;
}

.selectCon {
  width: 10%;
}
.conditionInput {
  width: 20%;
  margin-left: 10px;
}
.el-input.el-input--small.conditionInput {
  width: 20%;
  height: 40px;
  margin-left: 10px;
  margin-right: 10px;
}
.dialogInput {
  width: 70%;
}
</style>
