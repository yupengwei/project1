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
  <Table border :columns="columns7" :data="data6" ref="table"></Table><br>
      <Button type="primary" size="large" @click="exportData(1)"><Icon type="ios-download-outline"></Icon>导出源数据</Button>
      <Button type="primary" size="large" @click="exportData(2)"><Icon type="ios-download-outline"></Icon> 导出排序后数据</Button>
      <Button type="primary" size="large" @click="exportData(3)"><Icon type="ios-download-outline"></Icon> 导出自定义的数据</Button><br><br>
      <Page :total="total" :page-size="10" @on-change="changePage"></Page>
</div>
</template>

<script>
  export default {
  name: 'boomState',
  data() {
    return {
      currentPage:1,
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
          key: 'biannum'
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
          title: '联系方式',
          key: 'telephone'
        },
        {
          title: '所处地',
          key: 'place'
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
                  type: 'error',
                  size: 'small'
                },
                style: {
                  marginRight: '5px'
                },
                on: {
                  click: () => {
                   if(window.confirm("确定使用报废的资产吗")){
                       this.show(params)
                   }
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
        console.log("show方法中的"+params)
         console.log(params)
        // var it=params.row.biannum
        this.$http.post( this.GLOBAL.serverPath+ '/change/deleteBoom',
            {
                biannum:params.row.biannum
            },
            {
              emulateJSON:true
            }
        )
        this.data6.splice(params.index, 1)
    },


     handleSubmit(account) {
        this.request(1)
      },

     request (currentPage){
       console.log("已经发送request请求")
        var that=this
        this.$http.post(that.GLOBAL.serverPath + '/change/findBoom',
          {
            emulateJSON: true
          }
        ).then(function (res) {
          console.log(res.data)
          // that.total=res.data.pageInfo.total
          that.data6=res.data
          // that.data7=res.data
          // that.data7.forEach((e) =>{
            // let obj = {}
            // obj.bid = e.bid
            // obj.name = e.name
            // obj.supply = e.supply
            // obj.place = e.place
            // that.data6.push(obj)
            // var time = new Date().getTime();
            // obj.time= time
            // var da = e.time;
            // da = new Date(da);
            // obj.time = da.getFullYear()+'-'+ (da.getMonth()+1)+'-'+da.getDate()+'';
            // if(e.state ==1 ){
            //   obj.state = '未过保质期'
            // }else{
            //   var delayDay = Math.ceil((time - e.time)/86400000)
            //   obj.state = '过期'+ Math.floor((Math.random()*50)) +'天'
            // }
          // })
        })
      },

      changePage: function(page){
        this.request(page)
      },
      exportData (type){
                if (type === 1) {
                    this.$refs.table.exportCsv({
                        filename: 'The original data'
                    });
                } else if (type === 2){
                    this.$refs.table.exportCsv({
                        filename: 'Sorting and filtering data',
                        original: false
                    });
                } else if (type === 3){
                    this.$refs.table.exportCsv({
                        filename: 'Custom data',
                        columns: this.columns7.filter((col, index) => index < 4),
                        data: this.data7.filter((data, index) => index < 4)
                    });
                }
            }
    },
}
</script>
