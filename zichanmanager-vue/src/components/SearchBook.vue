<template>
  <div class="container">
    <Form ref="formInline" :model="formInline" inline>
      <FormItem prop="account">
        <Input type="text" v-model="formInline.title" placeholder="资产名称">
        <Icon type="search" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="handleSubmit('formInline')">查找</Button>
      </FormItem>
    </Form>

     <Modal
      v-model="modal1"
      title="用户信息"
      width="500"
      scrollable="true"
      ok-text="提交"
      @on-ok="borrow()"
      >
      <Form ref="formItem2" :model="formItem2" :rules="ruleItem2" :label-width="82">
        <FormItem label="归还日期" prop="backTime">
          <Input v-model="formItem2.backtime" type="date" placeholder=""></Input>
        </FormItem>
      </Form>
    </Modal>
    <Table border :columns="columns7" :data="data6"></Table>
    <Page :total="total" :page-size="10" @on-change="changePage"></Page>
  </div>
</template>

<script type="es6">
  export default {
    name: 'SearchBook',
    data () {
      return {
        params:'',
        total: '',
        condi: '',
        modal1: false,
        modal2: false,
        currIndex: 0,//最近被点击添加编号副本的图书编号
        formInline: {
          title: ''
        },
        formItem2: {
          title: '',
          author: '',
          publisher: '',
          publishtime: '',
          descri: '',
          backtime:'',
        },
        ruleItem2: {
          title: [{
            required: true,
            message: '请填写资产名称！',
            trigger: 'blur'
          }],
          author: [{
            required: true,
            message: '请填资产供应商',
            trigger: 'blur'
          }],
          borrownumber: [{
            required: true,
            message: '请填写借用人姓名',
            trigger: 'blur'
          }],
          borrowname: [{
            required: true,
            message: '请填写借用人工号'
          }],
          descri: [{
            required: true,
            message: '请填资产描述',
            trigger: 'blur'
          }]
        },
        formItem3: {
          num: ''
        },
        ruleItem3: {
          num: [{
            required: true,
            message: '请填写资产副本编号！',
            trigger: 'blur'
          }]
        },
        columns7: [
          {
            title: '序号',
            key: 'bid',
            width:'80px',
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
            title: '联系方式',
            key: 'telephone'
          },

          {
            title: '供应商',
            key: 'supply'
          },

          {
            title: '资产所在地',
            key: 'place'
          },
           {
            title: '购进时间',
            key: 'buytime'
          },
           {
            title: '使用年限',
            key: 'boomtime'
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
                    size: 'small',
                    disabled: this.data6[params.index].unBorrowed
                  },
                  on: {
                    click:() => {
                      this.modal1=true
                      this.params=params
                    }
                  }
                },'借用')
              ]);
            }
          }
        ],
        data6: [],
        data7: []
      }
    },
    mounted(){
      this.request(1);
    },
    methods: {
      handleSubmit(account) {
        this.request(1)
      },
      show (index) {
        this.$Modal.info({
          title: '资产信息',
          width: '500',
          content: `资产名称：${this.data6[index].name}<br>供应商：${this.data6[index].supply}<br>供应商所在地：${this.data6[index].place}<br>购进时间：${this.data6[index].time}`
        })
      },
      borrow() {
        console.log("借阅资产方法")
        var bid=this.params.row.bid
        console.log(this.params)
        var that=this
        this.$http.post(that.GLOBAL.serverPath + `/excise/borrow/${bid}`,
          {
            backtime:this.formItem2.backtime,
            usernumber:window.localStorage.getItem("account")
          },
          {
            emulateJSON: true
          }
        ).then(function (res) {
           this.data6.splice(this.params.index, 1)
           this.$Message.success("成功借用")
        })
      },
      request(currentPage){
         var that=this
         this.$http.post(that.GLOBAL.serverPath + '/excise/getAllgoodAlbums',
          {
            emulateJSON:true
          },
          ).then((res)=>{
            console.log(res)
            this.data6=[]
            this.data7=res.body
            this.data7.forEach((e)=>{
               let obj={}
               obj=e
               e.buytime=this.dateParse(e.buytime)
               e.boomtime=this.dateParse(e.boomtime)
               obj.buytime=e.buytime
               obj.boomtime=e.boomtime
               this.data6.push(obj)
            })
          })
      },
      dateParse(t){
         var time = new Date(t)
         var y=time.getFullYear()
         var m=time.getMonth()+1
         var d=time.getDate()
         t= y+"-"+m+"-"+d
         return t
      },
      changePage: function(page){
        this.request(page)
      }
    }
  }
</script>
