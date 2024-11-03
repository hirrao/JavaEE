package cn.hirrao.javaee.mapper

import cn.hirrao.javaee.entity.HeartRate
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

@Mapper
interface HeartRateMapper : BaseMapper<HeartRate?> {
    @Select("SELECT * FROM heartRateLog WHERE uid=#{uid}")
    fun searchHeartRateById(uid: Long?): List<HeartRate?>?

    @Insert("INSERT INTO heartRateLog(hrlId, uid, heartRate,recordTime) VALUES(#{hrlId},#{uid}, #{heartRate}, #{recordTime})")
    fun insertHeartRate(hrlId: Long, uid: Long, heartRate: Float, recordTime: String?)
}
