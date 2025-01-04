<template>
  <div>
    <!-- <h2 class="tital">账号管理</h2> -->
    <el-card body-style="justify-content: center;" class="card">
      <h2 class="tital">账号管理</h2>

      <div class="searchConditionDiv">
        <p style="margin-top: auto; margin-bottom: auto">查询条件：</p>
        <el-select
          v-model="searchCondition"
          class="selectCon"
          clearable
          placeholder="查询条件"
          size="large"
          style="width: 10%; margin-top: auto; margin-bottom: auto"
        >
          <el-option label="uid" value="uid" />
          <el-option label="用户名" value="userName" />
          <el-option label="性别" value="sex" />
          <el-option label="权限" value="permission" />
        </el-select>
        <el-input
          v-model="conditionValue"
          class="conditionInput"
          clearable
          placeholder=""
          size="small"
          style="margin-top: auto; margin-bottom: auto"
        ></el-input>
        <el-button
          style="height: 40px; margin-right: 5px; margin-top: auto; margin-bottom: auto"
          type="primary"
          @click="searchByCondition"
          >搜索
        </el-button>
        <el-button
          style="height: 40px; margin-top: auto; margin-bottom: auto"
          type="danger"
          @click="claerSearchCondition"
          >清空
        </el-button>
      </div>

      <el-table :data="Account" class="table">
        <el-table-column label="uid" prop="uid" width="200" />
        <el-table-column label="用户名" prop="userName" width="180" />
        <el-table-column label="电话号码" prop="phoneNumber" width="160" />
        <el-table-column label="性别" prop="sex" width="75" />
        <el-table-column label="生日" prop="birthday" />
        <el-table-column label="权限" prop="permission" width="75" />
        <el-table-column align="center" header-align="center" label="操作">
          <template #default="scoped">
            <el-button
              class="tableButton"
              icon="el-icon-edit"
              type="primary"
              @click="updateAccount(scoped.row)"
              >更新
            </el-button>
            <el-button
              class="tableButton"
              icon="el-icon-delete"
              type="danger"
              @click="deleteAccount(scoped.row.uid)"
              >删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        v-model:current-page="currentPage"
        v-model:page-size="pageSize"
        v-model:total="total"
        :page-sizes="[5, 10, 15, 20]"
        class="paging"
        layout="sizes,prev,pager,next,jumper,->,total"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
      >
      </el-pagination>
    </el-card>

    <el-dialog v-model="dialogVisble" title="修改账号信息" width="50%">
      <el-form
        ref="form"
        :model="modifyAccount"
        label-width="150px"
        style="margin-left: auto; margin-right: auto"
      >
        <el-form-item class="dialogInput" label="id" prop="uid">
          <el-input v-model="modifyAccount.uid" disabled placeholder="请输入id"></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="姓名" prop="userName">
          <el-input v-model="modifyAccount.userName" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="电话号码" prop="phoneNumber">
          <el-input v-model="modifyAccount.phoneNumber" placeholder="请输入电话号码"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="modifyAccount.sex">
            <el-radio label="男" size="large" value="男">男</el-radio>
            <el-radio label="女" size="large" value="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="生日" prop="birthday">
          <el-date-picker
            v-model="modifyAccount.birthday"
            default-value="2022-01-30 12:45:30"
            placeholder="请选择生日"
            type="datetime"
            value-format="YYYY-MM-DD HH:mm:ss"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="权限" prop="permission">
          <el-radio-group v-model="modifyAccount.permission">
            <el-radio :value="-1" label="-1" size="large">封禁</el-radio>
            <el-radio :value="0" label="0" size="large">普通用户</el-radio>
            <el-radio :value="1" label="1" size="large">管理员</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item align="center">
          <el-button size="small" type="primary" @click="AddRecord">更改</el-button>
          <el-button size="small" type="info" @click="CloseDialog">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ElMessage } from 'element-plus'
import instance from '@/utils/axios'
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
