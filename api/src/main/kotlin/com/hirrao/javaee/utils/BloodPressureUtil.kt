package com.hirrao.javaee.utils

object BloodPressureUtil {

    fun generateClassification(sbp: Float, dbp: Float): String {
        return when {
            sbp >= 180 || dbp >= 110 -> "3级高血压"
            sbp >= 160 || dbp >= 100 -> "2级高血压"
            sbp >= 140 || dbp >= 90 -> "1级高血压"
            else -> "正常"
        }
    }


    fun generateRiskLevel(sbp: Float, dbp: Float): String {
        return when {
            sbp >= 180 || dbp >= 110 -> "重度"
            sbp >= 160 || dbp >= 100 -> "中度"
            sbp >= 140 || dbp >= 90 -> "轻度"
            sbp >= 120 || dbp >= 80 -> "正常高值"
            sbp <= 90 || dbp <= 60 -> "偏低"
            else -> "正常"
        }
    }
}
