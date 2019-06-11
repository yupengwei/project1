<template>
  <div class="container">
  <Form ref="formInline" :model="formInline"  inline  v-show="modal1">
    <FormItem prop="personsearch" width="200px">
      <Input type="text" v-model="formInline.usernumber" style="width:200px" placeholder="工号">
      <Icon type="ios-person-outline" slot="prepend"></Icon>
      </Input>
    </FormItem></br>
     <span></span>
     <FormItem prop="personsearch" >
      <Input type="text" v-model="formInline.username" style="width:200px" placeholder="姓名">
       <Icon type="ios-person-outline" slot="prepend"></Icon>
      </Input>
    </FormItem></br>
     <FormItem prop="personsearch" >
      <Input type="text" v-model="formInline.dept" style="width:200px" placeholder="部门">
      <Icon type="ios-person-outline" slot="prepend"></Icon>
      </Input>
    </FormItem></br>
      <FormItem prop="personsearch" >
      <Input type="text" v-model="formInline.telephone" style="width:200px" placeholder="联系方式">
      <Icon type="ios-person-outline" slot="prepend"></Icon>
      </Input>
    </FormItem></br>
    <FormItem>
      <Button type="success" @click="handleSubmit('formInline')">查找</Button>
    </FormItem>
  </Form>

  <Table border :columns="columns7" :data="data6" v-show="loading"></Table>
  <!-- <Page :total="total" :page-size="10" @on-change="changePage"></Page> -->
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
      formInline: {
        usernumber: '',
        username:'',
        dept:'',
        telephone:'',
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
        this.$http.post(this.GLOBAL.serverPath+'/user/findUserByUsernumber',
            {
                usernumber:this.formInline.usernumber,
                username : this.formInline.username,
                dept:this.formInline.dept,
                telephone:this.formInline.telephone,
            },
            {
              emulateJSON:true
            },
        ).then((res)=>{
            console.log(res)
            this.data6=res.body
            this.modal1=false
            this.loading=true
        })
      },
       changePage: function(page){
        this.request(page)
      },
    }
  }
</script>

