package com.lin.appapidemo.mapper.shixun;

import com.lin.appapidemo.model.shixun.Boom;
import com.lin.appapidemo.model.shixun.Fix;
import com.lin.appapidemo.model.shixun.State;
import com.lin.appapidemo.util.MyMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoombumMapper extends MyMapper<Boom> {

    @Select("select * from t_boom")
    List<Boom>  selectAllBoom();

    @Insert("INSERT INTO t_boom(bid,biannum,name,telephone,supply,place,buytime,boomtime) VALUES(#{bid},#{biannum},#{name},#{telephone},#{supply},#{place},#{buytime},#{boomtime})")
    boolean insertBoomed(Boom boom);


    @Select("select * from t_boom where biannum=#{biannum}")
    Boom findBoom(String biannum);


    @Delete("delete from t_boom where biannum=#{biannum}")
    boolean deleteBoom(String biannum);

}
