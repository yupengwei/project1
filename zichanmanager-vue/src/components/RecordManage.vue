<template>
  <div class="container">
    <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
      <FormItem prop="borrowRecord">
        <Input type="text" v-model="formInline.usernumber" placeholder="借用者工号">
          <Icon type="ios-person-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="handleSubmit()">查找</Button>
      </FormItem>
    </Form>
    <Table border :columns="columns7" :data="data6"></Table>
    <Page :total="total" :page-size="10" @on-change="changePage"></Page>
  </div>
</template>
<script type="es6">
  export default {
    name: 'UserManage',
    data () {
      return {
        total: '',
        condi: '',
        modal1: false,
        modal2: false,
        currIndex: 0,//最近被点击添加编号副本的图书编号
        formInline: {
          usernumber:''
        },
        columns7: [
          {
            title: '序号',
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
            key: 'biannum'
          },
          {
            title: '资产名称',
            key: 'place'
          },
          {
            title: '供应商',
            key: 'supply'
          },

          {
            title: '借用者工号',
            key: 'usernumber',
            width:'100px',
          },

           {
            title: '借用时间',
            key: 'borrowtime'
          },

          {
            title: '应归时间',
            key: 'backtime'
          },

          {
            title: '是否超期',
            key: 'condi'
          },
          {
            title: '操作',
            key: 'action',
            width: 150,
            align: 'center',
            render: (h, params) => {
              return h('div', [
                h('Button', {
                  props: {
                    type: 'primary',
                    size: 'small'
                  },
                  style: {
                    marginRight: '5px'
                  },
                  on: {
                    click: () => {
                      this.show(params.index)
                    }
                  }
                }, '查看'),
                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      this.reback(params.index)
                    }
                  }
                }, '确认归还')
              ]);
            }
          }
        ],
        data6: [],
        data7: []  //存放从后台请求过来的借阅记录
      }
    },
    mounted(){
      this.request(1);
    },
    methods: {
      handleSubmit() {
        var that=this
        this.$http.post(this.GLOBAL.serverPath + '/user/findBorrowUser',
            {
                usernumber:this.formInline.usernumber
            },
            {
                emulateJSON:true
            }
        ).then((res)=>{
          console.log(res)
           this.data6=[]
           this.data7=res.body
            // that.total=res.body.pageInfo.total
            that.data7.forEach((e)=>{
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
            obj.borrowtime=this.dateParse(e.borrowtime)
            obj.backtime=this.dateParse(e.backtime)
            this.data6.push(obj)
        })
        })
      },
      show(index) {
        this.$Modal.info({
          title: '资产信息',
          content: `资产名称：${this.data6[index].name}<br>资产编号：${this.data6[index].bid}<br>借用者工号：${this.data6[index].usernumber}<br>借用者姓名：${this.data6[index].place}<br>借用时间：${this.data6[index].time}<br>应归还时间：${this.data6[index].time}<br>状态：借出`
        })
      },

      reback (index) {
        var that=this
        console.log(index)
        console.log(this.data6[index].bid)
        this.$http.post(that.GLOBAL.serverPath + '/excise/reback',
          {
            bid:that.data6[index].bid,
          },
          {
            emulateJSON: true
          }
          ).then(function(res){
             console.log(res)
          if(res.data.status == 'yes'){
            that.data6.splice(index,1)
            this.$Message.success('操作成功')
          }else{
            this.$Message.status('操作失败')
          }
        })
      },

      request(currentPage){
        var that=this
        this.$http.post(that.GLOBAL.serverPath + '/excise/getAllBorrowRecords',
        {
          emulateJSON:true
        },
        ).then((res)=>{
          console.log("查看所有记录里面的")
            console.log(res)
            this.data7=res.body
            // that.total=res.body.pageInfo.total
            that.data7.forEach((e)=>{
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
            obj.borrowtime=this.dateParse(e.borrowtime)
            obj.backtime=this.dateParse(e.backtime)
            this.data6.push(obj)
            })
        })},

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
