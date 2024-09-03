<template>
  <div>
    <!-- <h2 class="tital">账号管理</h2> -->
    <el-card class="card" style="width: 80%" body-style="justify-content: center;">
      <h2 class="tital">账号管理</h2>

      <el-table :data="Account" style="width: 100%">
        <el-table-column prop="uid" label="uid" width="180" />
        <el-table-column prop="userName" label="用户名" width="180" />
        <el-table-column prop="phoneNumber" label="电话号码" width="180" />
        <el-table-column prop="sex" label="性别" />
        <el-table-column prop="birthday" label="生日" />
        <el-table-column prop="permission" label="权限" />
        <el-table-column header-align="center" align="center" label="操作">
          <template v-slot="scoped">
            <el-button type="primary" size="mini" icon="el-icon-edit">更新</el-button>
            <el-button type="danger" size="mini" icon="el-icon-delete"
              @click="deleteAccount(scoped.row.uid)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination v-model:page-size="pageSize" v-model:current-page="currentPage" :page-sizes="[5, 10, 15, 20]"
        layout="sizes,prev,pager,next,jumper,->,total" @current-change="handleCurrentChange"
        @size-change="handleSizeChange" :total="total">
      </el-pagination>
    </el-card>

    <el-dialog title="修改账号信息" v-model="dialogVisble" width="80%">
      <el-form ref="modify-form" :model="modifyAccount" label-width="150px">
        <el-form-item label="id" prop="uid">
          <el-input v-model="modifyAccount.uid" placeholder="请输入id"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="userName">
          <el-input v-model="modifyAccount.userName" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="phoneNumber">
          <el-input v-model="modifyAccount.phoneNumber" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="userName">
          <el-input v-model="modifyAccount.userName" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="userName">
          <el-input v-model="modifyAccount.userName" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="测量时间" prop="recordTime">
          <el-date-picker v-model="addBloodPressure.recordTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择测量时间"></el-date-picker>
        </el-form-item>
        <el-form-item align="center">
          <el-button type="primary" size="mini" @click="AddRecord">添加</el-button>
          <el-button type="info" size="mini" @click="CloseDialog">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import instance from '../axios';
import { onMounted, ref } from 'vue';

// const Account=[{ id: 1, date: '2024-08-29', dosage: 120, eat: true },
//     { id: 2, date: '2024-08-28', dosage: 125, eat: true },
//     { id: 3, date: '2024-08-29', dosage: 120, eat: true },
//     { id: 4, date: '2024-08-28', dosage: 125, eat: true },
//     { id: 5, date: '2024-08-29', dosage: 120, eat: true },
//     { id: 6, date: '2024-08-28', dosage: 125, eat: true },
//     { id: 7, date: '2024-08-29', dosage: 120, eat: true },
//     { id: 8, date: '2024-08-28', dosage: 125, eat: true }];
let Account: any[] = [];
let pageSize = 5;
let currentPage = 1;
let total = 0;
let dialogVisble = false;
const modifyAccount = {
  uid: "",
  userName: "",
  phoneNumber: "",
  sex: "",
  birthday: "",
  permission: "",
  recordTime: ""
}
const addBloodPressure = ref({
  recordTime: ""
});


function searchByPage() {
  instance.post("/user/accountsInfo", {
    curPage: currentPage,
    size: pageSize,
  }, {
    headers: {
      'Content-Type': 'application/json',
    },
  }).then((response) => {
    console.log(response.data)
    //   Account=response.data.data.records;
    //   total=response.data.data.total;
    Account = response.data.records;
    total = response.data.total;
  })
}

onMounted(() => {
  console.log("onMounted")
  searchByPage()
})

function handleSizeChange(newSize: number) {
  // handle the size change logic here
  currentPage = 1
  searchByPage()
}

function handleCurrentChange(newPage: number) {
  // handle the current change logic here
  searchByPage()
}

function deleteAccount(uid: number) {
  let conf = confirm("是否删除")
  if (conf) {
    alert("删除成功")
  } else {

  }
}
function AddRecord() {
}

function CloseDialog() {
}
</script>

<style></style>