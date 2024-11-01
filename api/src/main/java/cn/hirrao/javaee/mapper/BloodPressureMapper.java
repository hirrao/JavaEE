package cn.hirrao.javaee.mapper;

import cn.hirrao.javaee.entity.BloodPressure;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface BloodPressureMapper extends BaseMapper<BloodPressure> {
    @Select("SELECT DATE(recordTime) as recordTime, AVG(SBP) as SBP , AVG(DBP) as DBP FROM bloodPressureLog WHERE uid= #{uid} AND DATE(recordTime) BETWEEN DATE(?, '-6 days') AND DATE(?) GROUP BY DATE(recordTime) ORDER BY DATE(recordTime);")
    List<BloodPressure> getBloodPressureList(Long uid, LocalDate date);

    @Insert("INSERT INTO bloodPressureLog(bplId, uid, SBP, DBP, recordTime, classification, riskLevel) VALUES(#{bplId},#{uid}, #{sbp}, #{dbp}, #{recordTime}, #{classification}, #{riskLevel})")
    void insertBloodPressure(long bplId, long uid, float sbp, float dbp, String recordTime, String classification, String riskLevel);

    @Select("SELECT riskLevel FROM bloodPressureLog WHERE uid= #{uid} AND DATE(recordTime) BETWEEN DATE(?, '-6 days') AND DATE(?) ORDER BY riskLevel;")
    List<String> getRiskLevel(Long uid, LocalDate date);
}
