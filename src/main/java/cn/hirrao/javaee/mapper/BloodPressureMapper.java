package cn.hirrao.javaee.mapper;

import cn.hirrao.javaee.entity.BloodPressure;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BloodPressureMapper extends BaseMapper<BloodPressure> {
    @Select("SELECT DATE(recordTime) as recordDate, AVG(SBP) as SBP , AVG(DBP) as DBP FROM bloodPressureLog WHERE uid= #{uid} AND DATE(recordTime) BETWEEN DATE_SUB(#{date}, INTERVAL 6 DAY)  AND #{date} GROUP BY DATE(recordTime)")
    List<BloodPressure> getBloodPressureList(Long uid, String date);

    @Insert("INSERT INTO bloodPressureLog(bplId, uid, SBP, DBP, recordTime, classification, riskLevel) VALUES(#{bplId},#{uid}, #{SBP}, #{DBP}, #{recordTime}, #{classification}, #{riskLevel})")
    void insertBloodPressure(long bplId, long uid, String date, float sbp, float dbp, String classification, String riskLevel);
}
