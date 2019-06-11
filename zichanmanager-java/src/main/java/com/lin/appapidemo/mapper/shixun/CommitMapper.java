package com.lin.appapidemo.mapper.shixun;

import com.lin.appapidemo.model.shixun.Commit;
import com.lin.appapidemo.util.MyMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommitMapper extends MyMapper<Commit> {
    @Insert("insert into t_commit (id,biannum,name,word) values(null,#{biannum},#{name},#{word})")
    boolean  Commitsubmit(Commit commit);

    @Select("select * from t_commit")
    List<Commit> findcommit();

}
