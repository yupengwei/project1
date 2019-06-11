package com.lin.appapidemo.controller.shixun;
import  com.lin.appapidemo.mapper.shixun.*;
import  com.lin.appapidemo.model.shixun.*;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.transaction.annotation.Transactional;
import  org.springframework.web.bind.annotation.*;
import  javax.servlet.http.HttpServletRequest;
import  java.text.ParseException;
import  java.text.SimpleDateFormat;
import  java.util.Date;
import  java.util.List;

@RestController
@RequestMapping(value = "/change", method = RequestMethod.POST)
@CrossOrigin
public class changeController {

    @Autowired(required = false)
    private ReaderMapper readerMapper;

    @Autowired(required = false)
    private SubalbumMapper subalbumMapper;

    @Autowired(required = false)
    private StatebumMapper statebumMapper;

    @Autowired(required = false)
    private FixbumMapper fixbumMapper;

    @Autowired(required = false)
    private BoombumMapper boombumMapper;

    @Autowired(required = false)
    private AlbumMapper albumMapper;

    //搜索资产
    @RequestMapping(value = "/search/{rid}",method = RequestMethod.POST)
    public Reader findUser(@PathVariable("rid") int rid, HttpServletRequest request){
        Reader reader=readerMapper.selectById(rid);
        return reader;
    }

    //修改资产
    @RequestMapping(value = "/changed/{rid}", method = RequestMethod.POST)
    public boolean changeUser(@PathVariable("rid") int id,HttpServletRequest request){
        System.out.println("好了..." + id);
        Reader reader=readerMapper.selectById(id);
        reader.setAccount(request.getParameter("account"));
        System.out.println(request.getParameter("account"));
        reader.setName( request.getParameter("name"));
        System.out.println(request.getParameter("name"));
        reader.setSex( request.getParameter("sex"));
        System.out.println(request.getParameter("sex"));
        reader.setCondi(Integer.parseInt(request.getParameter("condi")));
        System.out.println(request.getParameter("condi"));
        reader.setTime(request.getParameter("time"));
        System.out.println(reader);
        boolean b=readerMapper.updateUser(reader);
        System.out.println("执行成功");
        return true;
    }



    //查找出所有损坏的资产
    @RequestMapping(value = "/Findfix",method = RequestMethod.POST)
    public List<Fix> findfix(){
        List<Fix> fixedList=fixbumMapper.selectAll();
        return  fixedList;
    }


    //查找出所有报废的资产
    @RequestMapping(value = "/findBoom",method = RequestMethod.POST)
    public List<Boom> findBoom(){
        List<Boom> boomList=boombumMapper.selectAllBoom();
        return boomList;
    }


    //报修指定的这个资产
    @RequestMapping(value = "/fixed",method = RequestMethod.POST ,produces ="application/json")
    @ResponseBody
    @Transactional
    public State insertFix(@RequestParam("biannum")String biannum){
         //从状态表里查出该资产
         State state=statebumMapper.findOneState(biannum);
         System.out.println(state);
        //向损坏表里添加该资产
         Fix fix=new Fix();
         fix.setBid(state.getBid());
         fix.setBiannum(state.getBiannum());
         fix.setName(state.getName());
         fix.setPlace(state.getPlace());
         fix.setSupply(state.getSupply());
         fix.setTelephone(state.getTelephone());
         fix.setBoomtime(state.getBoomtime());
         fix.setBuytime(state.getBuytime());
         Date date=new Date();
         System.out.println(date);
         System.out.println(fix);
         fixbumMapper.insertFixed(fix);
         System.out.println("加入资产成功");
         //从好状态表里删除该资产
         statebumMapper.deleteStated(biannum);
         System.out.println("删除资产成功");
         return state;
    }


    //报废指定的这个资产
    @RequestMapping(value = "/boomed",method = RequestMethod.POST ,produces ="application/json")
    @ResponseBody
    @Transactional
    public State insertboom(@RequestParam("biannum")String biannum){
        System.out.println("已经进入报废的方法体");
        State state=statebumMapper.findOneState(biannum);
        System.out.println(state);
        Boom boom=new Boom();
        boom.setBid(state.getBid());
        boom.setName(state.getName());
        boom.setPlace(state.getPlace());
        boom.setBoomtime(state.getBoomtime());
        boom.setBuytime(state.getBuytime());
        boom.setSupply(state.getSupply());
        boom.setTelephone(state.getTelephone());
        boom.setBiannum(state.getBiannum());
        System.out.println(boom);


        //加入到报废清单
        boombumMapper.insertBoomed(boom);


        //从好的列表中删除
        statebumMapper.deleteStated(biannum);
        return state;
    }

    //已经维修好的资产进行处理
    @RequestMapping(value = "/wellFix", method = RequestMethod.POST)
    @Transactional
    public String wellFix(@RequestParam("biannum") String biannum){
        System.out.println("已经进入wellFix方法" +biannum);
        Fix fix=fixbumMapper.findFixed(biannum);
        System.out.println(fix);
        State state=new State();
        state.setBid(fix.getBid());
        state.setBiannum(fix.getBiannum());
        state.setTelephone(fix.getTelephone());
        state.setName(fix.getName());
        state.setPlace(fix.getPlace());
        state.setSupply(fix.getSupply());
        state.setBoomtime(fix.getBoomtime());
        state.setBuytime(fix.getBuytime());
        boolean b=statebumMapper.addState(state);
        System.out.println(b);
        fixbumMapper.deleteFixed(biannum);
        return "success";
    }


    //处理已经报废的资产
    @RequestMapping(value = "/deleteBoom", method = RequestMethod.POST)
    @Transactional
    public String deleteBoom(@RequestParam("biannum")String biannum){
        System.out.println("已经进入wellFix方法" +biannum);
        Boom boom=boombumMapper.findBoom(biannum);
        System.out.println(boom);
        State state=new State();
        state.setBid(boom.getBid());
        state.setBiannum(boom.getBiannum());
        state.setName(boom.getName());
        state.setPlace(boom.getPlace());
        state.setSupply(boom.getSupply());
        state.setBuytime(boom.getBuytime());
        state.setBoomtime(boom.getBoomtime());
        state.setTelephone(boom.getTelephone());
        statebumMapper.addState(state);
        System.out.println("加入资产成功");
        boombumMapper.deleteBoom(biannum);
        System.out.println("删除资产成功");
        return "success";
    }



    //管理员修改资产的属性
    @RequestMapping(value = "/AssetsChange/{aid}", method = RequestMethod.POST)
    @ResponseBody
    public Album AssetsChange(@PathVariable("aid") int aid){
         System.out.println( "修改资产.."+aid );
         Album album=albumMapper.selectById(aid);
         System.out.println(album);
         return album;
    }


    
    @RequestMapping(value = "/bookchange/{bid}", method = RequestMethod.POST,produces ="application/json")
    @ResponseBody
    public Album bookchange(@PathVariable("bid") int bid,HttpServletRequest request) throws ParseException {
        System.out.println( "已改资产.."+bid );
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-mm-dd");
        Date date=sdf.parse(request.getParameter("boomtime"));
        Date date1=sdf.parse(request.getParameter("publishtime"));
        Album album=albumMapper.selectById(bid);
        System.out.println(album);
        album.setAuthor(request.getParameter("author"));
        album.setTitle(request.getParameter("title"));
        album.setPublisher(request.getParameter("publisher"));
        album.setTelephone(Long.valueOf(request.getParameter("telephone")));
        album.setPublishtime(date1);
        album.setBiannum(request.getParameter("biannum"));
        album.setBoomtime(date);

        albumMapper.updateAlbum(album);

        State state=statebumMapper.findOneState(request.getParameter("biannum"));
        System.out.println(state);
        state.setBiannum(request.getParameter("biannum"));
        state.setSupply(request.getParameter("author"));
        state.setName(request.getParameter("title"));
        state.setTelephone(Long.valueOf(request.getParameter("telephone")));
        state.setBuytime(date1);
        state.setBoomtime(date);
        state.setPlace(request.getParameter("publisher"));
        statebumMapper.updateState(state);

        System.out.println("修改成功");
        return album;
    }

}


