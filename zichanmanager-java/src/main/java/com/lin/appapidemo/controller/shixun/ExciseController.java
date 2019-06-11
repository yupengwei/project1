package com.lin.appapidemo.controller.shixun;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lin.appapidemo.mapper.shixun.*;
import com.lin.appapidemo.model.shixun.*;
import com.lin.appapidemo.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/excise")
@CrossOrigin
public class ExciseController {
    @Autowired(required = false)
    private ReaderMapper readerMapper;
    @Autowired(required = false)
    private AlbumMapper albumMapper;
    @Autowired(required = false)
    private SubalbumMapper subalbumMapper;
    @Autowired(required = false)
    private BorrowrecordMapper borrowrecordMapper;
    @Autowired(required = false)
    private StatebumMapper statebumMapper;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Map<String,Object> login(@RequestParam("account")String account,@RequestParam("password")String password){
        Map<String,Object> map=new HashMap<>();
        Reader reader=readerMapper.selectWholeByAccount(account);
        if(reader!=null){
            System.out.println("hahaha"+reader.getPassword().equals(password));
            if(reader.getPassword().equals(password)){
                System.out.println("hahaha"+reader.getPassword().equals(password));
                map.put("result","yes");
                map.put("loginUser",reader);
                if(reader.getCondi()==0){
                    map.put("condi",0);
                }else if(reader.getCondi()==1){
                    map.put("condi",1);
                }else{
                    map.put("condi",2);
                }
                return map;
            }
        }
        map.put("result","no");
        return map;
    }

    @RequestMapping(value = "/getAllReaders",method = RequestMethod.POST)
    public Map<String,Object> getAllReaders(@RequestParam("account")String account,@RequestParam("currentPage")int currentPage){
        Map<String,Object> map=new HashMap<>();
        PageHelper.startPage(currentPage,10);
        List<Reader> list=readerMapper.selectByAccount(account);
        PageInfo<Reader> pageInfo=new PageInfo<>(list);
        map.put("readers",list);
        map.put("pageInfo",pageInfo);
        return map;
    }

    @RequestMapping(value = "/getAllAlbums",method = RequestMethod.POST)
    public Map<String,Object> getAllAlbums(@RequestParam("currentPage") int currentPage){
          PageHelper.startPage(currentPage,10);
          Map<String,Object> map=new HashMap<>();
          List<Album> list=albumMapper.findAllalbum();
          PageInfo<Album> pageInfo=new PageInfo<>(list);
          map.put("album",list);
          map.put("pageInfo",pageInfo);
          return map;
    }


    @RequestMapping(value = "/getAllgoodAlbums",method = RequestMethod.POST)
    public List<State> getAllgoodAlbums(){
        List<State> stateList=statebumMapper.selectAllState();
        return stateList;
    }


    @RequestMapping(value = "/FindAllAlbumsByTitle", method = RequestMethod.POST,produces ="application/json")
    public List<Album> FindAllAlbumsByTitle(@RequestParam("biannum") String biannum){
        System.out.println("已进入模糊查找方法");
        System.out.println(biannum);
        List<Album> album=albumMapper.selectByName(biannum);
        System.out.println(album);
        return album;
    }


    @RequestMapping(value = "/getAllBorrowRecords",method = RequestMethod.POST)
    public List<Borrowrecord> getAllBorrowRecords(){
        System.out.println("进入查看记录方法体");
        List<Borrowrecord> list= borrowrecordMapper.findAllborrow();
        System.out.println(list);
        return list;
    }


    @RequestMapping(value = "/getAllMyBorrowRecords",method = RequestMethod.POST)
    public List<Borrowrecord> getAllMyBorrowRecords(HttpServletRequest request){
        System.out.println("查看我的记录");
        List<Borrowrecord> list= borrowrecordMapper.findAllMyborrow(request.getParameter("account"));
        System.out.println(list);
        return  list;
    }


    @RequestMapping(value = "/addReader",method = RequestMethod.POST)
    public Map<String,Object> addReader(@RequestParam("account")String account, @RequestParam("name")String name, @RequestParam("sex")String sex, @RequestParam("condi")int condi, @RequestParam("password") String password, @RequestParam("time")String time){
        Map<String,Object> map=new HashMap<>();
        if(readerMapper.selectWholeByAccount(account)!=null){
            map.put("status","no");
        }else{
            readerMapper.insert(new Reader(account,password,name,sex,time,condi));
            map.put("status","ok");
        }
        return map;
    }


    @RequestMapping(value = "/addAlbum",method = RequestMethod.POST)
    public Boolean addAlbum(@RequestParam("title")String title, @RequestParam("author")String author, @RequestParam("publisher")String publisher, @RequestParam("publishtime") String publishtime, @RequestParam("telephone") String telephone, @RequestParam("biannum") String biannum, @RequestParam("boomtime") String  boomtime) throws ParseException {
        System.out.println("已经进入该方法体");
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        System.out.println(boomtime);
        Date boomtime1=sdf.parse(boomtime);
        System.out.println(boomtime1);
        Date publishtime1=sdf.parse(publishtime);
        Long telephone1=Long.valueOf(telephone);
        System.out.println(telephone1);
        Album album=new Album();
        album.setBiannum(biannum);
        album.setTitle(title);
        album.setAuthor(author);
        album.setPublisher(publisher);
        album.setPublishtime(publishtime1);
        album.setTelephone(telephone1);
        album.setBoomtime(boomtime1);

        State state=new State();
        state.setBiannum(biannum);
        state.setName(title);
        state.setPlace(publisher);
        state.setBoomtime(boomtime1);
        state.setBuytime(publishtime1);
        state.setTelephone(telephone1);
        state.setSupply(author);
        albumMapper.addAlbum(album);
        statebumMapper.addState(state);
        System.out.println("插入成功");
        return true;
    }


    @RequestMapping(value = "/addSubAlbum",method = RequestMethod.POST)
    public Map<String,Object> addSubAlbum(@RequestParam("bid")int bid,@RequestParam("number")String number){
        Map<String,Object> map=new HashMap<>();
        if(subalbumMapper.selectByNumber(number)!=null){
            map.put("status","no");
        }else{
            Album album=albumMapper.selectById(bid);
            album.setBiannum(album.getBiannum()+1);
            albumMapper.updateByPrimaryKey(album);
            subalbumMapper.insert(new Subalbum(bid,number,0,DateTimeUtil.getDate()));
            map.put("status","ok");
        }
        return map;
    }

    @RequestMapping(value = "/borrow/{bid}", method = RequestMethod.POST)
    @Transactional
    @ResponseBody
    public Borrowrecord borrow(@PathVariable("bid") int bid, @RequestParam("backtime") String backtime, HttpServletRequest request) throws ParseException {
        Borrowrecord borrowrecord=new Borrowrecord();
        System.out.println("已经进入借用方法");
        System.out.println(backtime);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date=sdf.parse(backtime);
        System.out.println(date);
        State state=statebumMapper.findAtState(bid);
        System.out.println(state);
        System.out.println(state.getBid());
        borrowrecord.setBid(state.getBid());
        borrowrecord.setBiannum(state.getBiannum());
        System.out.println(state.getBiannum());
        borrowrecord.setUsernumber(request.getParameter("usernumber"));
        borrowrecord.setName(state.getName());
        borrowrecord.setPlace(state.getPlace());
        borrowrecord.setBorrowtime(new Date());
        borrowrecord.setSupply(state.getSupply());
        borrowrecord.setBacktime(date);
        borrowrecord.setUsernumber(request.getParameter("usernumber"));
        System.out.println(borrowrecord);
        borrowrecordMapper.addBorrowrecord(borrowrecord);
        statebumMapper.deleteByStated(bid);
        System.out.println("成功借用");
        return borrowrecord;
    }



    @RequestMapping(value = "/reback", method = RequestMethod.POST)
    @Transactional
    public Map<String,Object> reback(@RequestParam("bid")int bid){
        System.out.println("已进入发放体");
        System.out.println(bid);
        Map<String,Object> map=new HashMap<>();
        if(bid!=0){
            Album album=albumMapper.selectById(bid);
            System.out.println(album);
            State state=new State();
            System.out.println(album.getBid());
            state.setBid(album.getBid());
            state.setTelephone(album.getTelephone());
            state.setBuytime(album.getPublishtime());
            state.setBiannum(album.getBiannum());
            state.setPlace(album.getPublisher());
            state.setSupply(album.getAuthor());
            state.setName(album.getTitle());
            state.setBoomtime(album.getBoomtime());
            statebumMapper.insert(state);
            borrowrecordMapper.deleteByBid(bid);
            map.put("status","yes");
        }else{
            map.put("status","no");
        }
        return map;
    }


    @RequestMapping(value = "/deleteUser/{rid}",  method = RequestMethod.POST)
    public void deleteUser(@PathVariable("rid")Integer rid, HttpServletRequest request){
        boolean b=readerMapper.deleteUserById(rid);
        System.out.println("删除用户");
    }



    @RequestMapping(value = "/deleteAlbum/{bid}", method = RequestMethod.POST)
    public boolean deleteAlbumByAid(@PathVariable("bid")Integer bid){
        boolean b=albumMapper.deleteAlbumByBid(bid);
        System.out.println("删除资产");
        boolean b1=statebumMapper.deleteByStated(bid);
        System.out.println("删除成功");
        return true;
    }




}

