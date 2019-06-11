<template>
  <div class="container">
      <!-- <Modal
      v-model="modal1"
      title="用户信息"
      width="500"
      scrollable="true"
      ok-text="提交"
      @on-ok="request"
      >
    <Form ref="formItem1" :model="formItem1" :rules="ruleItem2" :label-width="82">
        <FormItem label="借用人工号" prop="borrownumber">
          <Input v-model="formItem1.borrownumber" placeholder=""></Input>
        </FormItem>
        <FormItem label="借用截止" prop="borrowname">
          <Input v-model="formItem1.endtime"  type="date" placeholder=""></Input>
        </FormItem>
      </Form>
    </Modal> -->
     <Table border :columns="columns7" :data="data6" ></Table>
     <Page :total="total" :page-size="10" @on-change="changePage"></Page>
  </div>
</template>
<script type="es6">
  export default {
    name: 'MyRecord',
    data () {
      return {
        times:'',
        total: '',
        condi: '',
        currIndex: 0,//最近被点击添加编号副本的资产编号
        formInline: {
          raccount: ''
        },
        formItem1:{
          borrownumber:'',
          endtime:'',
        },
        columns7: [
          {
            title: '编号',
            key: 'bid',
            render: (h, params) => {
              return h('div', [
                h('Icon', {
                  props: {
                    type: 'document-text'
                  }
                }),
                h('strong', params.row.bid)
              ]);
            }
          },
           {
            title: '资产编号',
            key: 'biannum'
            },
          {
            title: '资产名称',
            key: 'name'
          },
          {
            title: '资产所在地',
            key: 'place'
          },
          {
            title: '供应商',
            key: 'supply'
          },
          {
            title: '借用者编号',
            key: 'supply'
          },
          {
            title: '借用者工号',
            key: 'usernumber'
          },
          {
            title: '借用时间',
            key: 'borrowtime'
          },
          {
            title: '应归还时间',
            key: 'backtime'
          },
          {
            title: '是否超期',
            key: 'condi',
          }
        ],
        data6: [],
        data7: [] //存放从后台请求过来的借阅记录
      }
    },
    mounted(){
      console.log("测试方法")
      console.log(window.localStorage)
      console.log(window.localStorage.getItem("account"))
      this.request();
      console.log("初始化方法")
      var date=new Date();
      console.log(date.valueOf())
      var ntime=Math.floor((date.valueOf()-1556441722760)/86400)
      console.log(ntime)
    },
    methods: {
        request(){
        var that=this
        this.$http.post(that.GLOBAL.serverPath + '/excise/getAllMyBorrowRecords',
          {
            account: window.localStorage.getItem("account")
          },
          {
            emulateJSON: true
          }
          ).then((res)=>{
            console.log("looklook")
            console.log(res)
            this.data6=[]
            this.data7=res.body
            this.data7.forEach((e)=>{
            let obj={}
            obj=e
            var date=new Date();
            console.log(date)
            console.log(date.valueOf()+"--"+ e.backtime)
            var ntime=Math.floor((date.valueOf()-e.backtime)/86400000)
            if(ntime>0){
              obj.condi="超期"+ntime+"天"
            }else{
              obj.condi="未超期"
            }
            e.borrowtime=this.dateParse(e.borrowtime)
            e.backtime=this.dateParse(e.backtime)
            obj.borrowtime=e.borrowtime
            obj.backtime=e.backtime
            this.data6.push(obj)
        })
        })
      },

      dateParse(t){
         var time = new Date(t);
         var y=time.getFullYear();
         var m=time.getMonth()+1
         var d=time.getDate()
         t= y+"-"+m+"-"+d
         return t
      },

      changePage: function(page){
        this.request(page)
      },
    }
  }
</script>

