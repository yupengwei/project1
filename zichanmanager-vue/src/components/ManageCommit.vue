<template>
  <div class="container">
     <Table border :columns="columns7" :data="data6" ></Table>
     <Page :total="total" :page-size="10" @on-change="changePage"></Page>
</div>
</template>

<script>
  export default {
  name: 'personsearch',
  data() {
    return {
      loading:false,
      modal1:true,
      currentPage:'',
      total: '',
      condi: '',
      currIndex: 0, //最近被点击添加编号副本的图书编号
      formInline:{
        biannum: '',
        name:'',
        word:'',
      },
      columns7:[
        {
          title: '序号',
          key: 'id',
        },

        {
          title: '资产编号',
          key:'biannum',
        },

        {
          title: '资产名称',
          key: 'name'
        },

        {
          title: '用户留言',
          key: 'word',
        },

      ],
      data6: [],
      data7: [] //存放从后台请求过来的记录
    }
  },

    mounted(){
      this.handleSubmit()
    },
    methods:{
      handleSubmit(){
        console.log("是的已经提交")
        console.log(this.formInline.usernumber)
        this.$http.post(this.GLOBAL.serverPath+'/user/findusercommit',
            {
              emulateJSON:true
            },
        ).then((res)=>{
            console.log(res)
            this.data6=res.data
        })
      },

      changePage: function(page){
        this.request(page)
      },

    }
  }
</script>

