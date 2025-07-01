package com.hirrao.javaee.mapper;

import com.hirrao.javaee.entity.BloodPressureTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;

public interface BloodPressureTableMapper extends BaseMapper<BloodPressureMapper> {
    @Select("SELECT AVG(SBP) as avgSBP, MIN(SBP) as minSBP, MAX(SBP) as maxSBP, AVG(DBP) as avgDBP, MIN(DBP) as minDBP, MAX(DBP) as maxDBP, AVG(SBP-DBP) as avgPP, MIN(SBP-DBP) as minPP, MAX(SBP-DBP) as maxPP FROM bloodPressureLog WHERE uid= #{uid} AND DATE(recordTime) BETWEEN DATE_SUB(#{date}, INTERVAL 6 DAY)  AND #{date};")
    BloodPressureTable getBloodPressureTable(LocalDate date, Long uid);
}
