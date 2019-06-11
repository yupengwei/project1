package com.lin.appapidemo.mapper.shixun;

import com.lin.appapidemo.model.shixun.Fix;
import com.lin.appapidemo.util.MyMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FixbumMapper extends MyMapper<Fix> {


    @Select("select * from t_fix")
    List<Fix> selectAllFix();

    @Insert("INSERT INTO t_fix(bid,biannum,name,supply,place,telephone,buytime,boomtime) VALUES( #{bid},#{biannum},#{name},#{supply},#{place},#{telephone},#{buytime},#{boomtime})")
    boolean insertFixed(Fix fix);

    @Delete("delete from t_fix where biannum=#{biannum}")
    boolean deleteFixed(@Param("biannum")String biannum);

    @Select("select * from  t_fix where biannum=#{biannum}")
    Fix  findFixed(@Param("biannum") String biannum);


}
