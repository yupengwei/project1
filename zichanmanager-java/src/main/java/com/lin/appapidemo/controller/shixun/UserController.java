package com.lin.appapidemo.controller.shixun;
import com.lin.appapidemo.mapper.shixun.*;
import com.lin.appapidemo.model.shixun.*;
import net.minidev.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value ="/user",method = RequestMethod.POST)
public class UserController {

    @Autowired(required = false)
    UserMapper userMapper;

    @Autowired(required = false)
    BorrowrecordMapper borrowrecordMapper;

    @Autowired(required = false)
    StatebumMapper statebumMapper;

    @Autowired(required = false)
    FixbumMapper fixbumMapper;

    @Autowired(required = false)
    CommitMapper commitMapper;

    @RequestMapping(value = "/findUserByUsernumber", method = RequestMethod.POST)
    @ResponseBody
    public List<User> findUserByUsernumber(@Param("usernumber")String usernumber,@Param("username")String username,@Param("dept")String dept,@Param("telephone") Long telephone){
        System.out.println(usernumber+username+dept+telephone);
        List<User> list = userMapper.findUserByUsernumber(usernumber,username,dept,telephone);
        return list;
    }


    //管理员查看借用人的记录
    @RequestMapping(value="/findBorrowUser", method= RequestMethod.POST)
    @ResponseBody
    public List<Borrowrecord> findBorrowUser(@Param("usernumber") String usernumber){
        List<Borrowrecord> list = borrowrecordMapper.findAllMyborrow(usernumber);
        System.out.println(list);
        return list;
    }


    @RequestMapping(value="/findstayed", method= RequestMethod.POST)
    @ResponseBody
    public JSONArray findstayed(@Param("biannum") String biannum){
        State state = statebumMapper.findOneState(biannum);
        JSONArray array= new JSONArray();
        JSONArray array1=array.appendElement(state);
        System.out.println(array1);
        return array1;
    }


    @RequestMapping(value="/usercommit", method= RequestMethod.POST)
    @ResponseBody
    public boolean usercommit(HttpServletRequest request){
        String biannum=request.getParameter("biannum");
        String name=request.getParameter("name");
        String word=request.getParameter("word");
        Commit commit=new Commit(biannum,name,word);
        commitMapper.Commitsubmit(commit);
        return  true;
    }


    @RequestMapping(value="/findusercommit", method= RequestMethod.POST)
    @ResponseBody
    public List<Commit> findusercommit(){
        List<Commit> list=commitMapper.findcommit();
        System.out.println("已经搜索所有用户评论");
        return list;
    }


    @RequestMapping(value = "/UpdateUser",method = RequestMethod.POST)
    @ResponseBody
    public List<Commit> UpdateUser(){
        User1 user1=null;
        user1.setAge(12).setUserid("gw001").setUsername("rdfhjkd").setPassword("hdfj");
       return null;
    }

}


