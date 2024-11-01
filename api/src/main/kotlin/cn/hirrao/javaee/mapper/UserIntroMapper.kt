package cn.hirrao.javaee.mapper;

import cn.hirrao.javaee.entity.UserIntro;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserIntroMapper extends BaseMapper<UserIntro> {
    @Select("select * from userIntro where uid = #{uid}")
    UserIntro findUserIntro(long uid);

    @Update("update userIntro set intro=#{intro} where uid = #{uid}")
    void updateUserIntro(long uid, String intro);

    @Insert("insert into userIntro(uid, intro) values(#{uid}, #{intro})")
    void setUserIntro(long uid, String intro);
}
