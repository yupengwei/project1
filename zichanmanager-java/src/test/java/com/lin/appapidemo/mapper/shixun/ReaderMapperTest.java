package com.lin.appapidemo.mapper.shixun;

import com.lin.appapidemo.model.shixun.Reader;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
public class ReaderMapperTest {
    @Autowired(required = false)
    private ReaderMapper readerMapper;
    public void selectWholeByAccount(){
      System.out.println(System.currentTimeMillis());
    }

}