<template>
  <div class="container">
    <Form ref="formInline" :model="formInline" :rules="ruleInline" inline>
      <FormItem prop="account">
        <Input type="text" v-model="formInline.account" placeholder="工号">
           <Icon type="ios-person-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="handleSubmit('formInline')">查找</Button>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="modal1 = true">新添用户</Button>
      </FormItem>
    </Form>
    <Table border :columns="columns7" :data="data6"></Table>
    <Page :total="total" :page-size="10" @on-change="changePage"></Page>
    <Modal
      v-model="modal1"
      title="新添用户"
      @on-ok="ok('formItem2')"
      >
      <Form ref="formItem2" :model="formItem2" :rules="ruleItem2" :label-width="80">
        <FormItem label="工号" prop="account">
          <Input v-model="formItem2.account" placeholder="" ></Input>
        </FormItem>
        <FormItem label="姓名" prop="name">
          <Input v-model="formItem2.name" placeholder=""></Input>
        </FormItem>
         <FormItem label="密码" prop="password">
          <Input v-model="formItem2.password" placeholder="" type="password"></Input>
        </FormItem>
        <FormItem label="性别" prop="sex">
          <RadioGroup v-model="formItem2.sex">
            <Radio label="男">男</Radio>
            <Radio label="女">女</Radio>
          </RadioGroup>
        </FormItem>
        <FormItem label="身份" prop="condi">
          <Select v-model="formItem2.condi">
            <Option value="0">普通用户</Option>
            <Option value="1">资产管理员</Option>
          </Select>
        </FormItem>
         <FormItem label="时间" prop="time">
          <Input v-model="formItem2.time" placeholder="" type="date" ref="input3"></Input>
        </FormItem>
      </Form>
    </Modal>

  <Modal v-model="modal2"  title="更新用户"  @on-ok="change()">
      <Form v-model="formItem3">
        <FormItem label="工号" prop="account">
          <Input v-model="formItem3.account" placeholder="" ref="input1"></Input>
        </FormItem>
        <FormItem label="姓名" prop="name" value="输入">
          <Input v-model="formItem3.name"  placeholder="" ref="input2"></Input>
        </FormItem>
        <FormItem label="性别" prop="sex">
          <RadioGroup v-model="formItem3.sex" ref="input4" >
            <Radio label="男">男</Radio>
            <Radio label="女">女</Radio>
          </RadioGroup>
        </FormItem>
         <FormItem label="密码" prop="password">
          <Input v-model="formItem3.password" placeholder="" type="password" ref="input3"></Input>
        </FormItem>
        <FormItem label="用户角色" prop="condi" ref="input5">
           <Select v-model="formItem3.condi">
            <Option value="0">普通用户</Option>
            <Option value="1">资产管理员</Option>
           </Select>
        </FormItem>
          <FormItem label="时间" prop="time">
          <Input v-model="formItem3.time" type="date" placeholder="" ref="input3"></Input>
        </FormItem>
      </Form>
  </Modal>
  </div>
</template>
<script type="es6">
  export default {
    name: 'UserManage',
    data () {
      return {
        infog:'',
        rid: 0,
        total: '',
        message:'',
        condi: '',
        modal1:false,
        modal2:false,
        ruleInline:'',
        formInline: {
          account: ''
        },
        formItem2: {
          account:'',
          name: '',
          sex: '男',
          condi: '0',
          password:''
        },
         formItem3: {
          account:'',
          name:'',
          sex: '男',
          condi: '0',
          password:'',
          time:''
        },
        ruleItem2: {
          account: [{
            required: true,
            message: '请填写账号！',
            trigger: 'blur'
          }],
          name: [{
            required: true,
            message: '请填写员工姓名',
            trigger: 'blur'
          }]
        },
        columns7: [
          {
            title: '序号',
            key: 'rid',
            width:'80px',
            align:'center'
          },
          {
            title: '工号',
            key: 'account',
            render: (h, params) => {
              return h('div', [
                h('Icon', {
                  props: {
                    type: 'person'
                  }
                }),
                h('strong', params.row.account)
              ]);
            }
          },

          {
            title: '姓名',
            key: 'name',
            align:'center',
          },
          {
            title: '性别',
            align:'center',
            key: 'sex'
          },

          {
            title: '密码',
            key: 'password'
          },

          {
            title: '用户角色',
            key: 'condi',
            width:'90px',
            align:'center',
          },

          {
            title: '入职时间',
            align:'center',
            key: 'time'
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
                       this.search(params)
                    }
                  }
                }, '修改'),

                h('Button', {
                  props: {
                    type: 'error',
                    size: 'small'
                  },
                  on: {
                    click: () => {
                      if(window.confirm("确定删除吗？")){
                          this.remove(params,params.index)
                      }
                    }
                  }
                }, '删除')
              ]);
            }
          }
        ],
        data6: []
      }
    },

    mounted(){
      this.request(1);
    },

    methods: {
      handleSubmit(account){
        console.log(account)
        this.request(1)
      },

    search(params){
          const that=this
          const {rid} = this.data6[params.index]
          console.log("我的rid是"+rid)
          this.$http.post(that.GLOBAL.serverPath+ `/change/search/${rid}`,
          ).then((response)=>{
            console.log(response);
              this.formItem3= response.data
              this.infog=params
              console.log("这是search")
              console.log("这是search+infog")
              this.modal2=true
            }
          )
      },

      change(){
        console.log("已经进入change方法体..")
        console.log(this.infog);
        // var that=this
        var rid = this.infog.row.rid;
        console.log("change方法体"),
        console.log(this.formItem3.time)
         this.$http.post(this.GLOBAL.serverPath +`/change/changed/${rid}`,
            {
                account: this.formItem3.account,
                name: this.formItem3.name,
                sex: this.formItem3.sex,
                condi: this.formItem3.condi,
                password:this.formItem3.password,
                time:this.formItem3.time
            },
            {
                emulateJSON: true
            },
              ).then((res)=>{
                console.log(res);
              }
            )
            window.location.reload();
      },

      show (index) {
        if(this.data6[index].condi==0){
          this.condi='普通用户'
        }else{
          this.condi='资产管理员'
        }

        this.$Modal.info({
          title: '用户信息',
          content: `姓名：${this.data6[index].name}<br>年龄：${this.data6[index].sex}<br>工号：${this.data6[index].account}<br>身份：${this.condi}`
        })
      },

      remove(params,index){
        console.log(params);
        const that = this;
        const { rid } = this.data6[index]
        this.$http.post(that.GLOBAL.serverPath + `/excise/deleteUser/${rid}`,
        )
         this.data6.splice(index, 1)
      },

      request (currentPage){
        var that=this
        this.$http.post(that.GLOBAL.serverPath + '/excise/getAllReaders',

          {
            account: that.formInline.account,
            currentPage: currentPage
          },

          {
            emulateJSON: true
          }
        ).then(function (res) {
          console.log(res.data.pageInfo)
          that.total=res.data.pageInfo.total
          that.data6=res.data.readers
        }).catch((e) => {
          this.$Message.fail('网络有误！')
        })
      },

      changePage: function(page){
        this.request(page)
      },

      ok(name) {
        console.log("我的"+name)
        var that=this
        this.$refs[name].validate((valid) => {
          if (valid) {
            that.$http.post(that.GLOBAL.serverPath + '/excise/addReader',
              {
                account: that.formItem2.account,
                name: that.formItem2.name,
                sex: that.formItem2.sex,
                condi: that.formItem2.condi,
                password:that.formItem2.password,
                time:that.formItem2.time
              },
              {
                emulateJSON: true
              },
              ).then(function(res) {
              console.log(res.data.status)
              if(res.data.status=='ok'){
                that.$Message.success('新增成功')
                that.formInline.account=''
                that.request(1)
              }else{
                that.$Message.error('已存在该工号的用户')
              }

            }).catch((e) => {
              that.$Message.fail('网络有误！')
            })
          }
        })
      }
    }
  }
</script>
