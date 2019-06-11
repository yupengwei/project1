package com.lin.appapidemo.controller.shixun;

import com.lin.appapidemo.model.shixun.User1;
import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class UserControllerTest {


    @Test
    @RequestMapping(value = "/UpdateUser", method = RequestMethod.POST)
    @ResponseBody
    public void UpdateUser(){
        User1 user1=new User1();
        user1.setAge(12).setUserid("gw001").setUsername("rdfhjkd").setPassword("hdfj");
        System.out.println(user1.toString());
    }


}


