<template>
    <div>
        <h2> sDJD</h2>

        <el-button id="dialogVisbleBtn" type="primary" size="mini" @click="DialogVisble">上传血压数据</el-button>

        <el-dialog title="添加血压记录" v-model="addDialogVisble" width="80%">
            <el-form ref="form" :model="addBloodPressure" label-width="80px">
                <el-form-item label="高压（收缩压）" prop="SBP">
                    <el-input v-model="addBloodPressure.SBP" placeholder="请输入高压（收缩压）"></el-input>
                </el-form-item>
                <el-form-item label="低压（舒张压）" prop="DBP">
                    <el-input v-model="addBloodPressure.DBP" placeholder="请输入低压（舒张压）"></el-input>
                </el-form-item>
                <el-form-item label="测量时间" prop="" recordTime>
                    <el-date-picker v-model="addBloodPressure.recordTime" type="datetime"
                        value-format="yyyy-MM-dd HH:mm:ss" placeholder="请选择测量时间"></el-date-picker>
                </el-form-item>
                <el-form-item align="center">
                    <el-button type="primary" size="mini" @click="AddRecord">添加</el-button>
                    <el-button type="info" size="mini" @click="CloseDialog">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>

        <div ref="chart" style="width: 100%;height: 400px;background-color: white;"></div>
    </div>
</template>

<script lang="ts">
import * as echarts from 'echarts';

export default {
    name: 'HistoryList',
    data() {
        return {
            addDialogVisble: false,
            addBloodPressure: {
                SBP: "",
                DBP: "",
                recordTime: ""
            }
        };
    },
    mounted() {
        this.initChart();
    },
    methods: {
        DialogVisble() {
            this.addDialogVisble = true;
        },
        CloseDialog() {
            this.addDialogVisble = false;
            this.addBloodPressure.SBP = ""
            this.addBloodPressure.DBP = ""
            this.addBloodPressure.recordTime = ""
        },
        AddRecord() {
            this.addDialogVisble = false;
            this.addBloodPressure.SBP = ""
            this.addBloodPressure.DBP = ""
            this.addBloodPressure.recordTime = ""
        },
        initChart() {
            //var chartDom = document.getElementById('main');
            var myChart = echarts.init(this.$refs.chart as HTMLElement);
            var option;

            option = {
                title: {
                    text: '血压变化图'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: ['舒张压', '收缩压']
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
                },
                yAxis: {
                    type: 'value'
                },
                series: [
                    {
                        name: '舒张压',
                        type: 'line',
                        stack: 'Total',
                        data: [320, 332, 301, 334, 390, 330, 320]
                    },
                    {
                        name: '收缩压',
                        type: 'line',
                        stack: 'Total',
                        data: [820, 932, 901, 934, 1290, 1330, 1320]
                    }
                ]
            };

            option && myChart.setOption(option);
        }
    }
}
</script>

<style>
.el-button {
    width: 100px;
    height: 30px;
}
</style>