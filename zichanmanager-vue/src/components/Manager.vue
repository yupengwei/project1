<template>
  <div class="layout">
    <Row type="flex">
      <Col span="5" class="layout-menu-left">
      <Menu active-name="1-1" theme="dark" width="auto" :open-names="['1']">
        <div class="layout-logo-left">
          <h2 style="color:white;">资产管理</h2>
        </div>
        <Submenu name="1">
          <template slot="title">
            <Icon type="ios-navigate"></Icon>
            资产盘点
          </template>
          <MenuItem name="1-1" @click.native="lookBook"><span>资产进出管理</span></MenuItem>
          <MenuItem name="1-2" @click.native="lookRecord"><span>查看借用记录</span></MenuItem>
          <MenuItem name="1-3" @click.native="lookState"><span>查看剩余资产</span></MenuItem>
        </Submenu>

        <Submenu name="2">
          <template slot="title">
           <Icon type="ios-navigate"></Icon>
            资产维护
           </template>
           <MenuItem name="2-1" @click.native="fixState"><span>资产报修</span></MenuItem>
           <MenuItem name="2-2" @click.native="boomState"><span>资产报废</span></MenuItem>
        </Submenu>

        <Submenu name="2">
          <template slot="title">
           <Icon type="ios-navigate"></Icon>
              人员查询
           </template>
           <MenuItem name="3-1" @click.native="personsearch"><span>查找相关人员</span></MenuItem>
            <MenuItem name="3-2" @click.native="ManageCommit"><span>查看用户评论</span></MenuItem>
        </Submenu>
      </Menu>
      </Col>
      <Col span="19">
      <div class="layout-header"></div>
      <div class="layout-breadcrumb">
        <Breadcrumb>
          <BreadcrumbItem href="#">{{one_nav}}</BreadcrumbItem>
          <BreadcrumbItem href="#">{{two_nav}}</BreadcrumbItem>
          <BreadcrumbItem href="#">{{three_nav}}</BreadcrumbItem>
          <i-Button v-on:click="destoryed();">退出系统</i-Button>
        </Breadcrumb>
      </div>
      <div class="layout-content">
        <div class="layout-content-main">
          <template id="bookManage"></template>
          <template id="recordManage"></template>
          <template id="lookState"></template>
          <component :is="currentView"></component>
        </div>
      </div>
       <!-- 下标，不影响 -->
      <div class="layout-copy">
        2017-2018 &copy; 资产管理
      </div>
      </Col>
    </Row>
  </div>
</template>
<script>
  import bookManage from '../components/BookManage.vue'
  import recordManage from '../components/RecordManage.vue'
  import Button from 'iview/src/components/button/button'
  import lookState from '../components/LookState.vue'
  import fixState from '../components/FixState.vue'
  import boomState from '../components/BoomState.vue'
  import personsearch from '../components/PersonSearch.vue'
  import ManageCommit from '../components/ManageCommit.vue'

  export default {
    name: 'Manager',
    data () {
      return {
        msg: 'haha',
        one_nav: '主页',
        two_nav: '后台管理',
        three_nav: '资产管理',
        currentView: 'bookManage'
      }
    },
    mounted () {
      this.msg = window.localStorage.getItem('username')
    },

    methods: {
      lookBook () {
        this.one_nav = '主页'
        this.two_nav = '后台管理'
        this.three_nav = '资产管理'
        this.currentView = 'bookManage'
      },
      lookRecord(){
        this.one_nav = '主页'
        this.two_nav = '资产管理'
        this.three_nav = '借阅记录'
        this.currentView = 'recordManage'
      },

      lookState(){
        this.one_nav = '主页'
        this.two_nav = '资产管理',
        this.three_nav = '状态记录'
        this.currentView = 'lookState'
      },
      fixState(){
        this.one_nav = '主页'
        this.two_nav = '资产维护',
        this.three_nav = '报修状态'
        this.currentView = 'fixState'
      },
       boomState(){
        this.one_nav = '主页'
        this.two_nav = '资产维护',
        this.three_nav = '报废状态'
        this.currentView = 'boomState'
      },

      personsearch(){
        this.one_nav = '主页'
        this.two_nav = '人员查询',
        this.three_nav = '查找人员'
        this.currentView = 'personsearch'
      },

      ManageCommit(){
        this.one_nav = '主页'
        this.two_nav = '人员查询',
        this.three_nav = '查找人员'
        this.currentView = 'ManageCommit'
      },

      destoryed(){
        if (window.confirm("确认吗退出吗？")) {
          window.location = "/";
        }
      }
    },

    components: {
      Button,
      lookState:lookState,
      bookManage: bookManage,
      recordManage: recordManage,
      fixState:fixState,
      boomState:boomState,
      personsearch:personsearch,
      ManageCommit:ManageCommit
    },
  }
</script>
<style scoped>
  .layout{
    border: 1px solid #d7dde4;
    background: #f5f7f9;
    position: relative;
    margin-top:-60px;
  }
  .layout-breadcrumb{
    padding: 10px 15px 0;
    text-align:left;
  }
  .layout-content{
    min-height: 200px;
    margin: 15px;
    overflow: hidden;
    background: #fff;
    border-radius: 4px;
  }
  .layout-content-main{
    padding: 10px;
  }
  .layout-copy{
    text-align: center;
    padding: 10px 0 20px;
    color: #9ea7b4;
  }
  .layout-menu-left{
    background: #464c5b;
  }
  .layout-header{
    height: 60px;
    background: #fff;
    box-shadow: 0 1px 1px rgba(0,0,0,.1);
  }
  .layout-logo-left{
    width: 90%;
    height: 30px;
    background: #5b6270;
    border-radius: 3px;
    margin: 15px auto;
  }

  Button{
    color:green;
    margin-left:660px;
    position: relative;
  }

</style>
