<template>
  <div>
    <el-card class="card" body-style="justify-content: center;">
      <h2 class="tital">用药提醒</h2>

      <div class="searchConditionDiv">
        <p style="margin-top: auto;margin-bottom: auto;">根据药物名称查询：</p>
        <el-input class="conditionInput" v-model="conditionValue" placeholder="" size="small" clearable></el-input>
        <el-button type="primary" style="height: 40px;margin-right: 5px;margin-top: auto;margin-bottom: auto;" @click="searchByCondition">搜索</el-button>
        <el-button type="danger" style="height: 40px;margin-top: auto;margin-bottom: auto;" @click="clearSearchCondition">清空</el-button>
        <el-button type="primary" style="height: 40px;margin-left: auto;margin-top: auto;margin-bottom: auto;" @click="showDrugManage">用药管理</el-button>
      </div>

      <el-table class="table" :data="drugs" row-key="drugId">
        <el-table-column prop="drugId" label="Id" width="200" />
        <el-table-column prop="drugName" label="药物名" width="250" />
        <el-table-column prop="alertTime" label="用药时间" width="150" />
        <el-table-column prop="frequency" label="频率" width="100" />
        <el-table-column prop="dosage" label="用量" width="100"/>
        <el-table-column prop="unit" label="单位" width="100"/>
        <el-table-column prop="isEat" header-align="center" align="center" label="是否食用">
          <template v-slot="scoped">
            <el-radio-group v-model="scoped.row.isEat" @change="eatDrugConfirm(scoped.row)" :disabled="scoped.row.isEat">
              <el-radio :value="true" size="small" label="true">是</el-radio>
              <el-radio :value="false" size="small" label="false">否</el-radio>
            </el-radio-group>     
          </template>
        </el-table-column>
      </el-table>
      
      <el-pagination
        class="paging"
        :page-size="pageSize"
        :current-page="currentPage"
        :page-sizes="[5,10,15,20]"
        layout="sizes,prev,pager,next,jumper,->,total"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
        :total="total">
      </el-pagination>

    </el-card>

    <br/>
    <br/>

    <el-card class="card" v-show="drugManageVisible">
      <h2 class="tital">提醒管理</h2>

      <div class="searchConditionDiv">
        <p style="margin-top: auto;margin-bottom: auto;">根据药物名称查询：</p>
        <el-input class="conditionInput" v-model="conditionValue" placeholder="" size="small" clearable></el-input>
        <el-button class="drugAlertBtn" type="primary" style="height: 40px;margin-right: 5px;" @click="searchByConditionAlert">搜索</el-button>
        <el-button class="drugAlertBtn" type="danger" style="height: 40px;" @click="clearSearchConditionAlert">清空</el-button>
        <el-button class="drugAlertBtn" type="primary" style="height: 40px;margin-left:auto;" @click="addDrugAlertsDialog">添加用药提醒</el-button>
      </div>

      <el-table class="table" :data="drugAlerts">
        <el-table-column type="expand">
          <template #default="props">
            <div m="4">
              <p m="t-0 b-2" style="margin-left: 20px;">提醒id: {{ props.row.alertId }}</p>
              <p m="t-0 b-2" style="margin-left: 20px;">药物id: {{ props.row.drugId }}</p>
              <p m="t-0 b-2" style="margin-left: 20px;">频率: {{ props.row.frequency }}</p>
              <p m="t-0 b-2" style="margin-left: 20px;">剂量: {{ props.row.dosage }} {{ props.row.unit }}</p>
              <el-button m="t-0 b-2" style="margin-left: 20px;" type="danger" @click="deleteDrugAlert(props.row)">删除</el-button>
            </div>
          </template>
        </el-table-column>
        <el-table-column label="时间" prop="alertTime" />
        <el-table-column label="药物名" prop="drugName" />
        <el-table-column label="是否启用" prop="isActive">
          <template v-slot="scoped">
            <el-switch v-model="scoped.row.isActive" active-text="是" inactive-text="否" :active-value="1" :inactive-value="0" @change="updateIsAvtive(scoped.row)"/>
          </template>
        </el-table-column>
      </el-table>

      <el-pagination
        class="paging"
        :page-size="pageSizeAlert"
        :current-page="currentPageAlert"
        :page-sizes="[5,10,15,20]"
        layout="sizes,prev,pager,next,jumper,->,total"
        @current-change="handleCurrentChangeAlert"
        @size-change="handleSizeChangeAlert"
        :total="totalAlert">
      </el-pagination>
    </el-card>

    <el-dialog title="添加用药提醒" v-model="addDrugsDialogVisible" width="50%">
      <el-form ref="form" :model="addDrugAlert" label-width="150px" style="margin-left: auto;margin-right: auto;">
        <el-form-item class="dialogInput" label="药物名" prop="drugName">
          <el-input v-model="addDrugAlert.drugName" placeholder="请输入药物名"></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="用药频次" prop="frequency">
          <el-select class="selectCon" v-model="addDrugAlert.frequency" placeholder="用药频次" size="small">
            <el-option value="每天" label="每天"/>
            <el-option value="每两天" label="每两天"/>
            <el-option value="每三天" label="每三天"/>
            <el-option value="每四天" label="每四天"/>
            <el-option value="每五天" label="每五天"/>
            <el-option value="每六天" label="每六天"/>
            <el-option value="每周" label="每周"/>
          </el-select>
        </el-form-item>
        <el-form-item class="dialogInput" label="单位" prop="unit">
          <el-select class="selectCon" v-model="addDrugAlert.unit" placeholder="单位" size="small">
            <el-option value="毫克" label="毫克"/>
            <el-option value="毫升" label="毫升"/>
            <el-option value="微克" label="微克"/>
            <el-option value="克" label="克"/>
            <el-option value="片" label="片"/>
            <el-option value="支" label="支"/>
            <el-option value="粒" label="粒"/>
            <el-option value="包" label="包"/>
            <el-option value="瓶" label="瓶"/>
          </el-select>
        </el-form-item>
        <el-form-item class="dialogInput" label="剂量" prop="dosage">
          <el-input v-model="addDrugAlert.dosage" placeholder="请输入剂量(小数)"></el-input>
        </el-form-item>
        <el-form-item class="dialogInput" label="是否启用" prop="isActive">
          <el-switch v-model="addDrugAlert.isActive" active-text="是" inactive-text="否" active-value="1" inactive-value="0"/>
        </el-form-item>
        <el-form-item class="dialogInput" label="提醒时间" prop="alertTime">
          <el-time-picker v-model="addDrugAlert.alertTime" value-format="HH:mm:ss" placeholder="提醒时间" />
        </el-form-item>
        <el-form-item align="center">
          <el-button type="primary" size="small" @click="saveDrugAlert">添加</el-button>
          <el-button type="info" size="small" @click="closeAddDialog">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
  import { ElMessage } from 'element-plus';
  import instance from '../axios';
  import { onMounted, ref } from 'vue';
  
  interface drug{
    drugId:string,
    drugName:string,
    frequency:string,
    unit:string,
    dosage:string,
    isActive:string,
    alertId:string,
    alertTime:string,
    eatTime: string,
    isEat:boolean
  }
  let drugs = ref<drug[]>();
  let drugAlerts=ref([]);
  const pageSize = ref(5);
  const currentPage = ref(1);
  const total = ref(0);
  const pageSizeAlert = ref(5);
  const currentPageAlert = ref(1);
  const totalAlert = ref(0);
  const dialogVisible = ref(false);
  const conditionValue = ref('');
  const addDrugsDialogVisible=ref(false);
  const modifyDrug = ref({
    drugName: ref(''),
    frequency: ref(''),
    unit: ref(''),
    dosage: ref(''),
    isActive:ref(''),
    alertTime:ref('')
  });
  const addDrugAlert=ref({
    drugName: ref(''),
    frequency: ref('每天'),
    unit: ref('毫克'),
    dosage: ref(''),
    isActive:ref(''),
    alertTime:ref('')
  });
  const drugManageVisible=ref(false)

  function showDrugManage(){
    console.log(drugManageVisible.value)
    drugManageVisible.value=!drugManageVisible.value
  }

  function checkIsEat(eatTime:String){
    console.log(eatTime)
    const eatDate=eatTime.split('-');
    const date=new Date();
    if(date.getFullYear()-parseInt(eatDate[0])>0)
      return false
    if(date.getMonth()+1-parseInt(eatDate[1])>0)
      return false
    if(date.getDay()+1-parseInt(eatDate[2])>0)
      return false
    return true
  }

  async function eatDrugConfirm(row: any){
    let conf = confirm("是否已经服药？");
    console.log("click")
    console.log(conf)
    if (conf) {
      const date=new Date();
      const year=date.getFullYear();
      const month=date.getMonth()+1;
      const day=date.getDay()+1;
      row.eatTime=`${year}-${month<10?'0'+month:month}-${day<10?'0'+day:day}`
      console.log("row.eatTime"+row.eatTime)
      await instance.post("/drugAlert/updateDrugAlertEatTimeById", {
        alertId:row.alertId,
        eatTime: row.eatTime
      }, {
        headers: {
          'Content-Type': 'application/json',
        },
      })
    }else{
      row.isEat=false;
    }
  }

  async function searchTotalDrug() {
    await instance.post("/drug/getPageDrugInfoTotalByDrugName", {
      uid:localStorage.getItem("uid"),
      drugName: conditionValue.value
    }, {
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((response) => {
      console.log(response);
      total.value = response.data.data;
      console.log(total.value);
    });
  }
  
  async function searchByPage() {
    console.log("uid:"+localStorage.getItem("uid"))
    searchTotalDrug();
    instance.post("/drug/getPageDrugsInfoByDrugName", {
      uid:localStorage.getItem("uid"),
      curPage: currentPage.value,
      size: pageSize.value,
      drugName: conditionValue.value
    }, {
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((response) => {
      console.log(response)
      drugs.value = response.data.data;
      if(drugs.value!=undefined){
          for(let i=0;i<drugs.value.length;++i){
          let isEat=checkIsEat(drugs.value[i].eatTime)
          drugs.value[i]["isEat"]=isEat;
        }
      }
    });
  }

  async function searchTotalDrugAlert() {
    await instance.post("/drug/getPageDrugAlertInfoTotalByDrugName", {
      uid:localStorage.getItem("uid"),
      drugName: conditionValue.value
    }, {
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((response) => {
      console.log(response);
      totalAlert.value = response.data.data;
      console.log(totalAlert.value);
    });
  }
  
  async function searchAlertByPage() {
    console.log("uid:"+localStorage.getItem("uid"))
    searchTotalDrug();
    instance.post("/drug/getPageDrugsAlertInfoByDrugName", {
      uid:localStorage.getItem("uid"),
      curPage: currentPageAlert.value,
      size: pageSizeAlert.value,
      drugName: conditionValue.value
    }, {
      headers: {
        'Content-Type': 'application/json',
      },
    }).then((response) => {
      console.log(response)
      drugAlerts.value = response.data.data;
    });
  }

  onMounted(() => {
    searchTotalDrug();
    searchByPage();
    searchTotalDrugAlert();
    searchAlertByPage();
  });
  
  function searchByCondition() {
    if (!conditionValue.value)
      ElMessage.error('请输入搜索值');
    else {
      searchTotalDrug();
      searchByPage();
    }
  }
  
  function clearSearchCondition() {
    conditionValue.value = '';
    searchTotalDrug();
    searchByPage();
  }
  
  function handleSizeChange(newSize: number) {
    pageSize.value = newSize;
    currentPage.value = 1;
    searchByPage();
  }
  
  function handleCurrentChange(newPage: number) {
    currentPage.value = newPage;
    searchByPage();
  }

  function addDrugAlertsDialog(){
    addDrugsDialogVisible.value=true
  }

  function closeAddDialog(){
    addDrugsDialogVisible.value=false
  }

  async function updateIsAvtive(row: { drugId: any; uid: any; isActive: any; }){
    console.log(row.isActive)
    await instance.post("/drug/updateDrugIsActiveById", {
      drugId:row.drugId,
      uid:localStorage.getItem('uid'),
      isActive: row.isActive
    }, {
      headers: {
        'Content-Type': 'application/json',
      },
    }).then(() => {
      currentPage.value=1;
      searchTotalDrug();
      searchByPage();
    }).catch((error) => {
      ElMessage.error(error);
    });
  }

  async function deleteDrugAlert(row: { alertId: any; drugId: any; }){
    await instance.post("/drug/deleteDrugAndDrugAlertById", {
      alertId:row.alertId,
      uid: localStorage.getItem("uid"),
      drugId:row.drugId
    }, {
      headers: {
        'Content-Type': 'application/json',
      },
    }).then(() => {
      ElMessage.success('添加成功');
      currentPage.value=1;
      currentPageAlert.value=1;
      searchTotalDrug();
      searchByPage();
      searchTotalDrugAlert();
      searchAlertByPage();
    }).catch((error) => {
      ElMessage.error(error);
    });
  }

  function handleSizeChangeAlert(newSize: number) {
    pageSizeAlert.value = newSize;
    currentPageAlert.value = 1;
    searchAlertByPage();
  }
  
  function handleCurrentChangeAlert(newPage: number) {
    currentPageAlert.value = newPage;
    searchAlertByPage();
  }

  function searchByConditionAlert() {
    if (!conditionValue.value)
      ElMessage.error('请输入搜索值');
    else {
      searchTotalDrugAlert();
      searchAlertByPage();
    }
  }
  
  function clearSearchConditionAlert() {
    conditionValue.value = '';
    searchTotalDrugAlert();
    searchAlertByPage();
  }

  async function saveDrugAlert() {
    if(addDrugAlert.value.drugName==''){
      ElMessage.error("药物名不能为空")
    }
    if(addDrugAlert.value.dosage==''){
      ElMessage.error("剂量不能为空")
    }
    if(addDrugAlert.value.alertTime==''){
      ElMessage.error("提醒时间不能为空")
    }
    await instance.post("/drug/insertDrugAndAlert", {
      uid: localStorage.getItem("uid"),
      drugName: addDrugAlert.value.drugName,
      frequency: addDrugAlert.value.frequency,
      unit: addDrugAlert.value.unit,
      dosage: addDrugAlert.value.dosage,
      isActive:addDrugAlert.value.isActive,
      alertTime:addDrugAlert.value.alertTime
    }, {
      headers: {
        'Content-Type': 'application/json',
      },
    }).then(() => {
      ElMessage.success('更新成功');
      if(addDrugAlert.value.isActive=='1'){
        searchTotalDrug()
        searchByPage()
      }
      searchTotalDrugAlert();
      searchAlertByPage();
      addDrugAlert.value={drugName:'', frequency: '每天', unit: '毫克', dosage: '', isActive: '', alertTime: ''}
      dialogVisible.value = false;
    }).catch((error) => {
      ElMessage.error(error);
    });
    addDrugsDialogVisible.value=false;
  }
</script>

<style>
  .tital {
    text-align: center;
  }
  .card {
    padding: 20px;
    margin-top: 20px;
    margin-bottom: 20px;
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
    margin-top: auto;
    margin-bottom: auto;
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
  .eatDrugConfirmDialog{
    display: flex;
    margin-left: auto;
    margin-right: auto;
  }
  .drugAlertBtn{
    margin-top: auto;
    margin-bottom: auto;
  }
</style>