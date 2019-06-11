/*
SQLyog Ultimate - MySQL GUI v8.2 
MySQL - 5.5.27 : Database - db_appapidemo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_appapidemo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `db_appapidemo`;

/*Table structure for table `t_album` */

DROP TABLE IF EXISTS `t_album`;

CREATE TABLE `t_album` (
  `bid` int(11) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `biannum` varchar(30) NOT NULL DEFAULT '',
  `title` varchar(110) DEFAULT NULL,
  `author` varchar(110) DEFAULT NULL,
  `publisher` varchar(110) DEFAULT NULL,
  `publishtime` date DEFAULT NULL,
  `telephone` bigint(40) DEFAULT NULL,
  `boomtime` date DEFAULT NULL,
  PRIMARY KEY (`bid`,`biannum`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_album` */

insert  into `t_album`(`bid`,`biannum`,`title`,`author`,`publisher`,`publishtime`,`telephone`,`boomtime`) values (00000000001,'NA001','联想7150','联想厂家','北京','2015-12-19',139239333,'2017-12-19'),(00000000002,'NA002','针式打印机','联想厂家','南京','2015-01-13',1349334835,'2018-01-13'),(00000000003,'NA003','台式打印机','联想','南京','2018-01-11',1374398483,'2018-01-11'),(00000000004,'NA004','联想笔记本','联想厂家','北京','2018-01-13',1627323943,'2018-01-13'),(00000000005,'NA005','hp笔记本','hp厂家','安徽','2018-01-13',1634398434,'2019-01-13'),(00000000006,'NA006','小新潮','联想厂家','广西','2012-01-13',1374389849,'2015-01-13'),(00000000008,'NA008','联想7300','联想厂家','北京','2014-01-19',1399230245,'2019-01-19'),(00000000009,'NA009','联想7300','联想厂家','河南','2019-01-19',1839283234,'2019-01-19'),(00000000010,'NA0010','联想940','联想','河南','2015-01-23',1623283848,'2021-01-20'),(00000000012,'NA0012','科达电器','联想','上海','2019-01-21',1932932323,'2019-01-21'),(00000000013,'NA0013','联想L340','NA003','联想','2019-04-25',1734893843,'2019-04-25'),(00000000014,'NA0014','联想450','联想厂家','河南','2015-04-27',4789584,'2019-04-27'),(00000000015,'NA0015','dell760','dell','南京','2015-01-23',13643434773,'2019-01-30'),(00000000016,'NA0016','联想340','联想','上海','2015-01-23',237283928,'2019-01-30'),(00000000017,'NA0017','联想340','联想厂家','北京','2019-01-17',13747384838,'2020-01-19'),(00000000018,'NA0018','联想340','联想厂家','北京','2019-01-17',13747384838,'2020-01-19'),(00000000019,'NA0019','联想340','联想厂家','北京','2019-01-08',1239392932,'2021-01-18'),(00000000020,'NA0020','联想450','联想','北京','2019-01-07',13743834993,'2021-01-13'),(00000000021,'NA0021','桌子','联想','北京','2019-01-28',13666734744,'2020-01-04');

/*Table structure for table `t_boom` */

DROP TABLE IF EXISTS `t_boom`;

CREATE TABLE `t_boom` (
  `bid` int(30) NOT NULL,
  `biannum` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `telephone` bigint(30) DEFAULT NULL,
  `supply` varchar(30) DEFAULT NULL,
  `place` varchar(30) DEFAULT NULL,
  `buytime` date DEFAULT NULL,
  `boomtime` date DEFAULT NULL,
  `managenumber` varchar(30) DEFAULT NULL,
  `managename` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`bid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_boom` */

insert  into `t_boom`(`bid`,`biannum`,`name`,`telephone`,`supply`,`place`,`buytime`,`boomtime`,`managenumber`,`managename`) values (5,'NA005','联想789',147848995,'联想','北京',NULL,'2020-02-28',NULL,NULL),(17,'ergv','NA0023',3434354333,'ergve','rergv','2019-01-30','2021-01-26',NULL,NULL);

/*Table structure for table `t_borrowrecord` */

DROP TABLE IF EXISTS `t_borrowrecord`;

CREATE TABLE `t_borrowrecord` (
  `bid` int(11) NOT NULL AUTO_INCREMENT,
  `biannum` varchar(40) NOT NULL,
  `name` varchar(30) DEFAULT NULL COMMENT '读者账号',
  `place` varchar(20) DEFAULT NULL,
  `supply` varchar(50) DEFAULT NULL,
  `usernumber` varchar(30) DEFAULT NULL,
  `backtime` date DEFAULT NULL,
  `borrowtime` date DEFAULT NULL,
  PRIMARY KEY (`bid`,`biannum`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_borrowrecord` */

insert  into `t_borrowrecord`(`bid`,`biannum`,`name`,`place`,`supply`,`usernumber`,`backtime`,`borrowtime`) values (12,'NA0012','联想小新','北京','联想厂家','gw001','2019-12-01','2019-06-04'),(14,'NA0015','联想7300','北京','联想厂家','gw001','2019-01-02','2019-06-08'),(14,'NA0021','桌子','北京','联想','gw001','2020-01-03','2019-06-10'),(16,'NA0016','联想340','上海','联想','gw001','2020-12-02','2019-06-02');

/*Table structure for table `t_commit` */

DROP TABLE IF EXISTS `t_commit`;

CREATE TABLE `t_commit` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `biannum` varchar(20) NOT NULL,
  `word` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`biannum`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_commit` */

insert  into `t_commit`(`id`,`name`,`biannum`,`word`) values (1,'联想340','NA001','不好使'),(2,'联想450','NA004','漏电'),(3,'dell','NA007','触摸板不灵'),(4,'','NA001','这个太旧了');

/*Table structure for table `t_fix` */

DROP TABLE IF EXISTS `t_fix`;

CREATE TABLE `t_fix` (
  `bid` int(10) NOT NULL,
  `biannum` varchar(40) NOT NULL,
  `name` varchar(40) DEFAULT NULL,
  `supply` varchar(20) DEFAULT NULL,
  `place` varchar(20) DEFAULT NULL,
  `telephone` bigint(30) DEFAULT NULL,
  `buytime` date DEFAULT '0000-00-00' COMMENT '购进时间',
  `boomtime` date DEFAULT '0000-00-00',
  `managename` varchar(30) DEFAULT NULL,
  `managenumber` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`bid`,`biannum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_fix` */

insert  into `t_fix`(`bid`,`biannum`,`name`,`supply`,`place`,`telephone`,`buytime`,`boomtime`,`managename`,`managenumber`) values (6,'NA006','联想890','联想厂家','北京',14373483439,'2019-05-07','2020-07-04',NULL,NULL),(8,'NA008','联想7300','联想厂家','北京',13992302,'2019-04-19','2019-04-19',NULL,NULL),(15,'NA007','dell7380','dell厂家','北京',8348309,'2018-01-13','2018-01-13',NULL,NULL),(18,'sds','NA0022','sdsd','sd',25677777777,'2020-01-01','2019-01-02',NULL,NULL);

/*Table structure for table `t_reader` */

DROP TABLE IF EXISTS `t_reader`;

CREATE TABLE `t_reader` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(110) NOT NULL,
  `password` varchar(110) NOT NULL,
  `name` varchar(110) NOT NULL,
  `sex` varchar(11) NOT NULL,
  `time` varchar(110) NOT NULL,
  `condi` int(11) NOT NULL COMMENT '0:读者 1：图书管理员 2：系统管理员',
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_reader` */

insert  into `t_reader`(`rid`,`account`,`password`,`name`,`sex`,`time`,`condi`) values (1,'super','super','总管','男','2019-04-16 ',2),(2,'manager','manager','Sunny','男','2019-04-16 ',1),(22,'gw001','gw001','合法的','女','2019-04-16 ',0),(25,'gw002','gw002','大赛','男','2019-04-16 ',0),(32,'gw0015','gw007','gw0015','男','2019-06-25',0),(33,'gw005','gw007','gw005','男','2013-04-16',1),(34,'gw008','gw007','gw0083','男','2016-04-15',0),(35,'gw006','gw006','gw006','男','2012-04-20 ',1),(36,'gw007','gw007','gw007','女','2015-04-20',0),(39,'gw0019','gw0019','gw0019','男','2013-03-12',1),(40,'gw0020','gw0020','gw0020','女','2012-03-29',0),(41,'gw0016','gw0016','该w6','男','2016-12-26',0),(44,'gw0013','gw00199','gw0013','男','2019-04-17',0),(46,'gw003','gw003','很少','男','2019-12-02',0),(47,'gw00016','gw0018','和儿科','男','2019-02-03',1);

/*Table structure for table `t_state` */

DROP TABLE IF EXISTS `t_state`;

CREATE TABLE `t_state` (
  `bid` int(20) NOT NULL AUTO_INCREMENT,
  `biannum` varchar(20) NOT NULL DEFAULT '',
  `name` varchar(60) DEFAULT NULL,
  `telephone` bigint(40) DEFAULT NULL,
  `supply` varchar(20) DEFAULT NULL COMMENT '供应商',
  `place` varchar(20) DEFAULT NULL COMMENT '出产地',
  `buytime` date DEFAULT NULL,
  `boomtime` date DEFAULT NULL,
  PRIMARY KEY (`bid`,`biannum`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_state` */

insert  into `t_state`(`bid`,`biannum`,`name`,`telephone`,`supply`,`place`,`buytime`,`boomtime`) values (1,'NA001','联想340',12372734845,'联想','西安','2019-01-08','2020-01-11'),(2,'NA002','针式打印机',1349334835,'联想厂家','南京','2015-01-13','2018-01-13'),(3,'NA003','联想340',12372734845,'联想','张家口','2019-01-08','2020-01-11'),(4,'NA004','联想笔记本',1627323943,'联想厂家','北京','2018-01-13','2018-01-13'),(5,'NA005','hp笔记本',1634398434,'hp厂家','安徽','2018-01-13','2019-01-13'),(6,'NA006','小新潮',1374389849,'联想厂家','广西','2012-01-13','2015-01-13'),(9,'NA009','联想7300',1839283234,'联想厂家','河南','2019-01-19','2019-01-19'),(10,'NA0010','联想940',1623283848,'联想','河南','2015-01-23','2021-01-20'),(13,'NA0013','联想L340',1734893843,'NA003','联想','2019-04-25','2019-04-25'),(13,'NA0036','联想小新',18238828328,'联想厂家','天进','2019-01-07','2021-01-29');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(20) NOT NULL,
  `usernumber` varchar(30) NOT NULL,
  `username` varchar(30) NOT NULL,
  `dept` varchar(30) NOT NULL,
  `position` varchar(30) NOT NULL,
  `telephone` bigint(30) NOT NULL,
  PRIMARY KEY (`usernumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`usernumber`,`username`,`dept`,`position`,`telephone`) values (1,'gw001','呵呵','创业策划','职员',14757883493),(2,'gw002','呵呵','信息技术部','科长',138483934939),(3,'gw003','米娜不','广告传播','职员',13993993494),(4,'gw004','James','国际部','部长',1347347384);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
