<template>
  <!-- <div>
    <h2>注册</h2>

    <el-card  style="width: 80%">
      <el-table :data="Drag" style="width: 80%">
        <el-table-column prop="id" label="id" width="180" />
        <el-table-column prop="date" label="日期" width="180" />
        <el-table-column prop="dosage" label="剂量" width="180" />
        <el-table-column prop="eat" label="是否食用" />
      </el-table>
      <el-pagination
        :page-size.sync="pageSize"
        :current-page.sync="currentPage"
        :page-sizes="[5,10,15,20]"
        layout="sizes,prev,pager,next,jumper,->,total"
        @current-change=""
        @size-change=""
        :total="total">
      </el-pagination>
    </el-card>
  </div> -->
  <section>
    <h2>注册</h2>

    <el-card  style="width: 80%">
    <el-table :data="Drag" style="width: 80%">
        <el-table-column prop="id" label="id" width="180" />
        <el-table-column prop="date" label="日期" width="180" />
        <el-table-column prop="dosage" label="剂量" width="180" />
        <el-table-column prop="eat" label="是否食用" />
    </el-table>
    <el-pagination
        :page-size.sync="pageSize"
        :current-page.sync="currentPage"
        :page-sizes="[5,10,15,20]"
        layout="sizes,prev,pager,next,jumper,->,total"
        @current-change=""
        @size-change=""
        :total="total">
    </el-pagination>
    </el-card>
  </section>
</template>

<script setup lang="ts">
  const Drag=[{ id: 1, date: '2024-08-29', dosage: 120, eat: true },
        { id: 2, date: '2024-08-28', dosage: 125, eat: true },
        { id: 3, date: '2024-08-29', dosage: 120, eat: true },
        { id: 4, date: '2024-08-28', dosage: 125, eat: true },
        { id: 5, date: '2024-08-29', dosage: 120, eat: true },
        { id: 6, date: '2024-08-28', dosage: 125, eat: true },
        { id: 7, date: '2024-08-29', dosage: 120, eat: true },
        { id: 8, date: '2024-08-28', dosage: 125, eat: true }];
    const pageSize=5;
    const currentPage=1;
    const total=8;
</script>

<style>
.section{
    padding: 20px;
}
.h2{
    text-align: center;
}
.el-card{
    width: 80%;
    display: flex;
    justify-content: center;
    align-items: center;
}
.el-pagination{
    margin: 10px;
}
</style>