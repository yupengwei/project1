<template>
<div class="container">
  <Form ref="formInline" :model="formInline" inline>
    <FormItem prop="lookState">
      <Input type="text" v-model="formInline.raccount" placeholder="资产编号">
      <Icon type="ios-person-outline" slot="prepend"></Icon>
      </Input>
    </FormItem>
    <FormItem>
      <Button type="primary" @click="handleSubmit('formInline')">查找</Button>
    </FormItem>
  </Form>
  <Table border :columns="columns7" :data="data6" ></Table>
  <Page :total="total" :page-size="10" @on-change="changePage"></Page>
</div>
</template>

<script>
  export default {
  name: 'fixState',
  data() {
    return {
      currentPage:'',
      total: '',
      condi: '',
      currIndex: 0, //最近被点击添加编号副本的图书编号
      formInline: {
        raccount: '',
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
          key:'biannum'
        },
        {
          title: '资产名称',
          key: 'name'
        },
        {
          title: '供应商',
          key: 'supply'
        },
        {
          title: '所处地',
          key: 'place'
        },
        {
          title: '联系方式',
          key: 'telephone'
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
                    if(window.confirm("正在维修中确认维修好了吗"))
                        this.show(params)
                  }
                }
              }, '删除'),
            ]);
          }
        }
      ],
      data6: [],
      data7: [] //存放从后台请求过来的记录
    }
  },

     mounted(){
      this.request(1);
     },
     methods:{
        fixState() {
        this.one_nav = '主页'
        this.two_nav = '后台管理'
        this.three_nav = '资产管理'
        this.currentView = 'bookManage'
      },

       show(params){
        console.log("show删除方法中的"+params)
        this.$http.post( this.GLOBAL.serverPath+ '/change/wellFix',
            {
                biannum:params.row.biannum
            },
            {
              emulateJSON:true
            }

        ).then((res)=>{
            console.log(res)
        })
         this.data6.splice(params.index, 1)
    },
     handleSubmit(account) {
        var that=this
        this.$http.post(this.GLOBAL.serverPath + '/user/findfixedrecord',
            {
               biannum:this.formInline.raccount
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
          obj.buytime=this.dateParse(e.buytime)
          obj.boomtime=this.dateParse(e.boomtime)
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

     request (currentPage){
       console.log("已经发送request请求")
        var that=this
        this.$http.post(that.GLOBAL.serverPath + '/change/Findfix',
          {
            emulateJSON: true
          }
        ).then(function (res) {
          console.log(res)
          this.data6=res.data
        })
      },

       changePage:function(page){
        this.request(page)
      },
    }
  }

</script>
