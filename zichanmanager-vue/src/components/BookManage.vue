<template>
  <div class="container">
    <Form ref="formInline" :model="formInline" :rules="ruleInline" inline >
      <FormItem prop="biannum">
        <Input type="text" v-model="formInline.biannum" placeholder="资产编号">
            <Icon type="ios-person-outline" slot="prepend"></Icon>
        </Input>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="handleSubmit">查找</Button>
      </FormItem>
      <FormItem>
        <Button type="primary" @click="modal1 = true">新添资产</Button>
      </FormItem>
    </Form>
    <Modal
      v-model="modal1"
      title="新添资产"
      width="500"
      scrollable="true"
      ok-text="添加"
      @on-ok="okadd('formItem2')"
      >
      <Form ref="formItem2" :model="formItem2" :rules="ruleItem2" :label-width="80">
        <FormItem label="资产名称" prop="title">
          <Input v-model="formItem2.title" placeholder=""></Input>
        </FormItem>
        <FormItem label="资产编号" prop="biannum">
          <Input v-model="formItem2.biannum" placeholder=""></Input>
        </FormItem>
        <FormItem label="供应商" prop="author">
          <Input v-model="formItem2.author" placeholder=""></Input>
        </FormItem>
        <FormItem label="供应商所在地" prop="publisher">
          <Input v-model="formItem2.publisher" placeholder=""></Input>
        </FormItem>
        <FormItem label="联系方式" prop="telephone">
          <Input v-model="formItem2.telephone" placeholder="" maxlength="11" minlength="11"></Input>
        </FormItem>
        <FormItem label="购进时间" prop="time">
           <Input v-model="formItem2.publishtime" type="date" placeholder=""></Input>
        </FormItem>
        <FormItem label="使用年限" prop="boomtime">
           <Input v-model="formItem2.boomtime"  type="date" placeholder=""></Input>
        </FormItem>
      </Form>
    </Modal>
    <Modal
       v-model="modal2"
       title="资产修改"
       ok-text="确定"
       @on-ok="bookchange()"
    >
      <Form ref="formItem3" :model="formItem3" :rules="ruleItem3" :label-width="80">
        <FormItem label="序号" prop="bid">
          <Input v-model="formItem3.bid" placeholder=""></Input>
        </FormItem>
        <FormItem label="资产编号" prop="biannum">
          <Input v-model="formItem3.biannum" placeholder=""></Input>
        </FormItem>
        <FormItem label="资产名称" prop="name">
          <Input v-model="formItem3.title" placeholder=""></Input>
        </FormItem>
        <FormItem label="资产供应商" prop="author">
          <Input v-model="formItem3.author" placeholder=""></Input>
        </FormItem>
        <FormItem label="供应商所在地" prop="publisher">
          <Input v-model="formItem3.publisher" placeholder=""></Input>
        </FormItem>
         <FormItem label="购进时间" prop="publishtime">
          <Input v-model="formItem3.publishtime" placeholder=""></Input>
        </FormItem>
         <FormItem label="联系方式" prop="telephone">
          <Input v-model="formItem3.telephone" placeholder=""></Input>
        </FormItem>
         <FormItem label="使用年限" prop="boomtime">
          <Input v-model="formItem3.boomtime"  placeholder=""></Input>
        </FormItem>
      </Form>
    </Modal>
     <Table border :columns="columns7" :data="data6" ref="table"></Table>
     <Page :total="total" :page-size="10"  @on-change="changePage"></Page></br>
     <Button type="primary" size="large" @click="exportData(1)"><Icon type="ios-download-outline"></Icon>导出源数据</Button>
      <Button type="primary" size="large" @click="exportData(2)"><Icon type="ios-download-outline"></Icon> 导出排序后数据</Button>
      <Button type="primary" size="large" @click="exportData(3)"><Icon type="ios-download-outline"></Icon> 导出自定义的数据</Button><br><br>
  </div>
</template>
<script type="es6">
  export default {
    name: 'UserManage',
    data () {
      return {
        currentPage:'',
        total: '',
        condi: '',
        infog:'',
        modal1: false,
        modal2: false,
        content:'',
        currIndex: 0,//最近被点击添加编号副本的图书编号
         formItem2: {
          title: '',
          author: '',
          publisher: '',
          publishtime: '',
          biannum:'',
          telephone:'',
        },
        formInline: {
          title: '',
          biannum:''
        },

        ruleItem2: {
          title: [{
            required: true,
            message: '请填写资产名！',
            trigger: 'blur'
          }],
          author: [{
            required: true,
            message: '请填资产供应商',
            trigger: 'blur'
          }],
          publisher: [{
            required: true,
            message: '请填写供应商所在地',
            trigger: 'blur'
          }],
          publishtime: [{
            required: true,
            message: '请填写资产购进时间'
          }],
         telephone:[{
            required:true,
            message:'请您填写十一位正确的电话号码'
         }
         ],

        },
        formItem3: {
          bid:'',
          biannum:'',
          title:'',
          author:'',
          publisher:'',
          publishtime:'',
          telephone:'',
          boomtime:'',
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
            width: 70,
            render: (h,params) =>{
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
            key: 'biannum',
            width:'120px'
          },

          {
            title: '资产名称',
             key: 'title',
             align:'center',
          },

          {
            title: '资产供应商',
            key: 'author',
            width:'120px',
          },
          {
            title: '供应商所在地',
            key: 'publisher',
            width:'120px',
            align:'center',
          },

          {
            title: '联系方式',
            key: 'telephone',
            align:'center',
          },

          {
            title: '购进时间',
            key: 'publishtime',
            align:'center',
          },

          {
            title: '使用年限',
            key: 'boomtime',
            align:'center',
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
                      this.formItem3=params.row
                      this.infog=params.row
                      this.modal2=true
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
                          this.remove(params.index)
                      }
                    }
                  }
                }, '删除')
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
      bookchange(){
        var bid = this.infog.bid
        console.log("修改方法里面的"+bid);
        this.$http.post(this.GLOBAL.serverPath+ `/change/bookchange/${bid}`,

        {
           biannum:this.formItem3.biannum,
           publisher:this.formItem3.publisher,
           author:this.formItem3.author,
           title:this.formItem3.title,
           publishtime:this.formItem3.publishtime,
           boomtime:this.formItem3.boomtime,
           telephone:this.formItem3.telephone,
        },

        {
            emulateJSON:true
        },

        ).then((res)=>{
           console.log(res)
           window.location.reload();
        }
        )
      },

      handleSubmit() {
          var that=this
          console.log(this.formInline.biannum)
          this.$http.post(that.GLOBAL.serverPath+ '/excise/FindAllAlbumsByTitle',

            {
                biannum:that.formInline.biannum
            },

            {
                emulateJSON:true
            },

          ).then(({ data, body })=>{
            console.log("查询方法")
            console.log(body);
            this.data6 = data;
          }

          )
      },
      show(index) {
        this.$Modal.info({
          title: '资产信息',
          width: '500',
          content: `资产名称：${this.data6[index].title}<br>供应商名称：${this.data6[index].author}<br>供应商所在地：${this.data6[index].publisher}<br>借用时间：${this.data6[index].publishtime}<br>`
        })
      },
      remove (index) {
        const that = this;
        const { bid } = this.data6[index]
        this.$http.post(that.GLOBAL.serverPath + `/excise/deleteAlbum/${bid}`,
        )
          this.data6.splice(index, 1)
          this.$Message.success('Successfully delete');
      },

      request(currentPage){
         var that=this
          this.$http.post(that.GLOBAL.serverPath + '/excise/getAllAlbums',
          {
              currentPage:currentPage
          },
          {
              emulateJSON:true
          },
          ).then((res)=>{
            console.log("request分页函数");
            console.log(res)
            that.total=res.body.pageInfo.total
            that.data6=[]
            that.data7=res.body.album
            console.log("开始遍历数组")
            that.data7.forEach((e) => {
            console.log(e)
            let obj={}
            obj.bid=e.bid
            obj.author=e.author
            obj.biannum=e.biannum
            obj.publisher=e.publisher
            obj.telephone=e.telephone
            obj.title=e.title
            console.log(e.boomtime)
            obj.boomtime=this.dateParse(e.boomtime)
            obj.publishtime=this.dateParse(e.publishtime)
            that.data6.push(obj)
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
          console.log(page)
          this.request(page)
      },
      okadd(name){
        var that=this
        this.$refs[name].validate((valid) => {
        if (valid) {
         this.$http.post(that.GLOBAL.serverPath + '/excise/addAlbum',
            {
                title: that.formItem2.title,
                author: that.formItem2.author,
                publisher: that.formItem2.publisher,
                publishtime: that.formItem2.publishtime,
                biannum: that.formItem2.biannum,
                telephone:that.formItem2.telephone,
                boomtime:that.formItem2.boomtime,
            },
            {
                emulateJSON:true
            },
            ).then((res)=>{
            console.log(res);
            window.location.reload();
            }
            )
        }else{
            that.$Message.error('已存在该资产的编号')
        }
        })
    },

      ok2 (name) {
        var that=this
        that.$http.post(that.GLOBAL.serverPath + '/excise/addSubAlbum',
              {
                bid: that.currIndex,
                number: that.formItem3.num
              },
              {
                emulateJSON: true
              }
            ).then(function (res) {
              console.log(res.data.status)
              if(res.data.status=='ok'){
                that.$Message.success('新增成功')
                that.formItem3.num=''
                that.request(1)
              }else{
                that.$Message.error('新增失败！查看是否存在相同编号')
              }
            }).catch((e) => {
              that.$Message.fail('网络有误！')
            })
      },

      onEditorChange({editor,html,text}){
        // 富文本编辑器，文本改变时，设置字段值
        console.log(editor,html,text)
        this.content = html
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
    }
  }
</script>

