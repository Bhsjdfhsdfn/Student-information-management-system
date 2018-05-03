/*
SQLyog - MySQL GUI v8.14 
MySQL - 5.1.49-community : Database - mydb
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mydb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `mydb`;

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `cardno` varchar(50) DEFAULT NULL,
  `realname` varchar(50) DEFAULT NULL,
  `memo` varchar(100) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `ctime` varchar(20) DEFAULT NULL,
  `stuno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`password`,`phone`,`address`,`cardno`,`realname`,`memo`,`sex`,`ctime`,`stuno`) values (1,'a','a','15825312305','广东广州','4522261995563214','来来来','哈哈哈哈或或或或或或或或或或或或或或或或或或','男','2018-03-05 20:29:04','20113317'),(2,'1','1','1','1','1','1','1','1','2018-03-05','20113315'),(3,'a1','a1','15825312306','1',NULL,'12','12','12','12','12');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
