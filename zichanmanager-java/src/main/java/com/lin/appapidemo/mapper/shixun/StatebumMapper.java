package com.lin.appapidemo.mapper.shixun;

import com.lin.appapidemo.model.shixun.State;
import com.lin.appapidemo.util.MyMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StatebumMapper extends MyMapper<State> {

    //查看所有状态好的资产
    @Select("select * from t_state")
    List<State> selectAllState();


    //向所有好的状态表里放入制定数据
    @Insert("INSERT INTO t_state(bid,biannum,name,telephone,supply,place,buytime,boomtime) VALUES(null,#{biannum},#{name},#{telephone},#{supply},#{place},#{buytime},#{boomtime} ) ")
//    boolean addState(@Param("bid") int bid, @Param("biannum") String biannum, @Param("name") String name, @Param("telephone") String telephone, @Param("supply") String supply, @Param("place") String place, @Param("buytime") String buytime,@Param("boomtime") String boomtime);
    boolean addState(State state);

    @Insert("INSERT INTO t_state(bid,number,supply,place,time,name) VALUES(#{bid},#{number},#{supply},#{place},#{time},#{name});")
    boolean addanotherState(State state);

    //从好的资产表里删除制定的数据
    @Delete("delete from t_state where biannum=#{biannum}")
    boolean deleteStated(@Param("biannum")String biannum);

    @Delete("delete from t_state where bid=#{bid}")
    boolean deleteByStated(@Param("bid")int bid);


    @Select("select * from  t_state where biannum=#{biannum}")
    State findOneState(String biannum);


    @Select("select * from  t_state where bid=#{bid}")
    State findAtState(@Param("bid") int bid);

    @Update("update t_state set name=#{name},telephone=#{telephone},supply=#{supply},place=#{place},buytime=#{buytime},boomtime=#{boomtime} where biannum=#{biannum}")
    boolean updateState(State state);
}
