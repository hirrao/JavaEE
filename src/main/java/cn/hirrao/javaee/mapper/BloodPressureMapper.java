package cn.hirrao.javaee.mapper;

import cn.hirrao.javaee.entity.BloodPressure;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BloodPressureMapper extends BaseMapper<BloodPressure> {
    @Select("select * from bloodPressureLog where uid = #{uid}")
    List<BloodPressure> getBloodPressureList(Long uid);
}
