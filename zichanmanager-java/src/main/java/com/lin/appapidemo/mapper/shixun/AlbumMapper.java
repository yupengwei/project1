package com.lin.appapidemo.mapper.shixun;

import com.lin.appapidemo.model.shixun.Album;
import com.lin.appapidemo.model.shixun.Borrowrecord;
import com.lin.appapidemo.model.shixun.Reader;
import com.lin.appapidemo.util.MyMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AlbumMapper extends MyMapper<Album>{
    @Select("select * from  t_album where bid=#{bid}")
    @Results({
            @Result(id=true,column="bid",property = "bid"),
            @Result(column = "title",property = "title"),
            @Result(column = "author",property = "author"),
            @Result(column = "publisher",property = "publisher"),
            @Result(column = "publishtime",property = "publishtime"),
            @Result(column = "num",property = "num"),
            @Result(column = "telephone",property = "telephone"),
            @Result(column = "time",property = "time"),
    })
    Album selectById(@Param("bid") int bid);
    /**
     *根据图书title模糊查询，及该图书所有的副本编号
     *@params:
     *@return:
     *@date: 22:00 2018/1/11
     **/
//    @Select("<script> "+
//            "select * from t_album"+
//            " <where> "+
//            " title like concat('%',concat(#{title},'%'))"+
//            " </where> "+
//            " </script> "
//    )
//    @Select("select * from t_album where title like concat('%',concat(#{title},'%'))")


    @Select("select * from t_album where biannum = #{biannum} ")
    List<Album> selectByName(@Param("biannum") String biannum);

     @Delete("delete from  t_album  where bid= #{bid}")
     boolean deleteAlbumByBid(@Param("bid") int bid);

     @Delete("delete from  t_album  where biannum= #{biannum}")
     boolean deleteAlbumByBiannum(@Param("biannum") int biannum);


     @Update("update t_album set author =#{author}, biannum=#{biannum},publisher=#{publisher},title=#{title}, telephone=#{telephone},publishtime =#{publishtime},boomtime=#{boomtime} where bid= #{bid}")
     boolean updateAlbum(Album album);


     @Select("select * from t_album")
     List<Album> findAllalbum();

     @Insert("insert into t_album(bid,biannum,title,author,publisher,publishtime,telephone,boomtime) values (null,#{biannum},#{title},#{author},#{publisher},#{publishtime},#{telephone},#{boomtime})")
     Boolean addAlbum(Album album);

}
