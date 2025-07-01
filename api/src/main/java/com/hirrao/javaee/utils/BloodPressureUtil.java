package com.hirrao.javaee.utils;

public class BloodPressureUtil {
    public static String generateClassification(float sbp, float dbp) {
        if (sbp >= 180 || dbp >= 110) {
            return "3级高血压";
        } else if (sbp >= 160 || dbp >= 100) {
            return "2级高血压";
        } else if (sbp >= 140 || dbp >= 90) {
            return "1级高血压";
        } else {
            return "正常";
        }
    }

    public static String generateRiskLevel(float sbp, float dbp) {
        if (sbp >= 180 || dbp >= 110) {
            return "重度";
        } else if (sbp >= 160 || dbp >= 100) {
            return "中度";
        } else if (sbp >= 140 || dbp >= 90) {
            return "轻度";
        } else if (sbp >= 120 || dbp >= 80) {
            return "正常高值";
        } else if (sbp <= 90 || dbp <= 60) {
            return "偏低";
        } else {
            return "正常";
        }
    }
}
