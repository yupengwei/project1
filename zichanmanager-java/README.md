# appapidemo

> A spring-boot project

## 项目说明

- 此项目为资产管理系统后端，使采用的是springboot+spring+mybatis等技术实现数据持久化以及api服务调用，前端使用vue.js,vue-resource,vue-router,iView2.0UI框架,vue-quill-editor等技术实现前台页面，由于前期没有上传后台项目springboot的application.porperties文件，故此次一并上传，大家就不必到issue中寻找了。

## 项目启动
- 1.数据库：mysql5.6执行以下脚本,前台项目下脚本文件--sbm.sql
- 2.后台启动：导入项目，进入控制台，到项目所在路径，执行命令：mvn clean spring-boot:run
- 3.前台启动：导入项目，进入控制台，到项目所在路径，执行命令：npm run dev，访问地址：http://localhost:8075  进入到登录界面，打开数据库中t_reader表获取用户名和密码
