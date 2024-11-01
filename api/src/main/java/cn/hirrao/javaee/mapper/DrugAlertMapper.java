package cn.hirrao.javaee.mapper;

import cn.hirrao.javaee.entity.DrugAlert;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;

@Mapper
public interface DrugAlertMapper extends BaseMapper<DrugAlert> {
    @Insert("INSERT INTO drugAlert VALUES (#{alertId},#{uid},#{drugId}, #{alertTime},#{eatTime})")
    void insertDrugAlert(long alertId, long uid, long drugId, LocalTime alertTime, LocalDate eatTime);

    @Update("UPDATE drug SET isActive=#{isActive} WHERE alertId=#{alertId}")
    void updateDrugAlertIsActiveById(long alertId, int isActive);

    @Update("UPDATE drugAlert SET eatTime=#{eatTime} WHERE alertId=#{alertId}")
    void updateDrugAlertEatTimeById(long alertId, Date eatTime);

    @Delete("DELETE FROM drugAlert WHERE alertId=#{alertId} AND uid=#{uid} AND drugId=#{drugId}")
    void deleteDrugAlertById(long alertId, long uid, long drugId);
}
