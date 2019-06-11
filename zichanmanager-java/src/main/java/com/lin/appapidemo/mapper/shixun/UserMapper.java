package com.lin.appapidemo.mapper.shixun;

import com.lin.appapidemo.model.shixun.User;
import com.lin.appapidemo.util.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper extends MyMapper<User>{

    @Select( "<script> select * from t_user where <if test= \"#{usernumber}!= null \"> usernumber=#{usernumber} </if>" +
            "<if test= \"#{username}!=null\"> or username=#{username} </if>"+
            "<if test= \"#{dept}!= null \"> or dept=#{dept} </if>"+
            "<if test= \"#{telephone}!= null \"> or telephone = #{telephone} </if>" +
            "</script>"
           )

    List<User> findUserByUsernumber(@Param("usernumber") String usernumber, @Param("username") String username, @Param("dept") String dept, @Param("telephone") Long telephone);



//    @Select("select * from t_user where usernumber=#{usernumber}")
//    List<User> findBorrowUser(String usernumber);

    boolean Update(User user);


}

