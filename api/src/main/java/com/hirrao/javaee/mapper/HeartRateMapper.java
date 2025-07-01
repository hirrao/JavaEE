package com.hirrao.javaee.mapper;

import com.hirrao.javaee.entity.HeartRate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HeartRateMapper extends BaseMapper<HeartRate> {

    @Select("SELECT * FROM heartRateLog WHERE uid=#{uid}")
    List<HeartRate> searchHeartRateById(Long uid);
    @Insert("INSERT INTO heartRateLog(hrlId, uid, heartRate,recordTime) VALUES(#{hrlId},#{uid}, #{heartRate}, #{recordTime})")
    void insertHeartRate(long hrlId, long uid, float heartRate,String recordTime);
}
