<template>
  <div class="container">
  <Form ref="formInline" :model="formInline"  inline  v-show="modal1" >
    <label style="font-size:16px; margin-right:10px">资产编号</label>
    <FormItem prop="personsearch" width="200px">
      <Input type="text" v-model="formInline.biannum" style="width:200px" placeholder="资产编号">
      </Input>
    </FormItem></br>
    <label style="font-size:16px; margin-right:10px">资产名称</label>
     <FormItem prop="personsearch" >
      <Input type="text" v-model="formInline.name" style="width:200px" placeholder="资产名称" />
      </Input>
    </FormItem></br>
     <FormItem prop="personsearch" >
      <Input type="textarea" v-model="formInline.word" style="width:360px" placeholder="您的建议"/>
    </FormItem></br>

    <FormItem>
      <Button type="success" @click="handleSubmit('formInline')">提交</Button>
    </FormItem>

  </Form>

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
          title: '工号',
          key:'usernumber'
        },
        {
          title: '姓名',
          key: 'username'
        },
        {
          title: '部门',
          key: 'dept'
        },
        {
          title: '职位',
          key: 'position'
        },
        {
          title: '联系方式',
          key: 'telephone'
        },
      ],
      data6: [],
      data7: [] //存放从后台请求过来的记录
    }
  },
     methods:{
      handleSubmit(account){
        console.log("是的已经提交")
        console.log(this.formInline.usernumber)
        this.$http.post(this.GLOBAL.serverPath+'/user/usercommit',
            {
                biannum: this.formInline.biannum,
                name:this.formInline.name,
                word:this.formInline.word,
            },
            {
              emulateJSON:true
            },
        ).then((res)=>{
            console.log(res)
            this.$Message.success("您已提交，感谢您提的建议")
        })
      },
       changePage: function(page){
        this.request(page)
      },
    }
  }
</script>

