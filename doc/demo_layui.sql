/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.26 : Database - demo_security
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demo_security` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `demo_security`;

/*Table structure for table `t_sys_permission` */

DROP TABLE IF EXISTS `t_sys_permission`;

CREATE TABLE `t_sys_permission` (
  `id` bigint NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `modify_time` datetime(6) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_permission` */

insert  into `t_sys_permission`(`id`,`create_time`,`modify_time`,`url`) values 
(1,'2021-11-01 08:32:11.000000','2021-11-01 08:32:43.000000','/**'),
(2,'2021-11-01 08:33:00.000000','2021-11-01 08:33:02.000000','/users/**');

/*Table structure for table `t_sys_role` */

DROP TABLE IF EXISTS `t_sys_role`;

CREATE TABLE `t_sys_role` (
  `id` bigint NOT NULL,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `modify_time` datetime(6) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_role` */

insert  into `t_sys_role`(`id`,`code`,`create_time`,`modify_time`,`name`,`remark`) values 
(1,'admin','2021-10-25 09:32:40.000000','2021-10-25 09:32:43.000000','admin','管理员'),
(2,'user','2021-10-25 09:37:02.000000','2021-10-25 09:37:05.000000','user','普通用户');

/*Table structure for table `t_sys_role_permission` */

DROP TABLE IF EXISTS `t_sys_role_permission`;

CREATE TABLE `t_sys_role_permission` (
  `role_id` bigint NOT NULL,
  `permission_id` bigint NOT NULL,
  KEY `FK74t6b2m3hvcypexhkuinaiu2k` (`permission_id`),
  KEY `FKbqr2ew547n1y29pyhbm5rmqyj` (`role_id`),
  CONSTRAINT `FK74t6b2m3hvcypexhkuinaiu2k` FOREIGN KEY (`permission_id`) REFERENCES `t_sys_permission` (`id`),
  CONSTRAINT `FKbqr2ew547n1y29pyhbm5rmqyj` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_role_permission` */

insert  into `t_sys_role_permission`(`role_id`,`permission_id`) values 
(1,1),
(2,2);

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

CREATE TABLE `t_sys_user` (
  `id` bigint NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `modify_time` datetime(6) DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `last_login_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`id`,`create_time`,`modify_time`,`nick_name`,`password`,`username`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`enabled`,`last_login_time`) values 
(1,'2021-11-01 09:48:32.000000','2021-11-01 09:48:35.000000','管理员','$2a$10$f60DT./ul.ntoj413kjexuEA86.hAK0z56diT3DKU3emnH53cvOMW','admin','','','','','2021-11-01 09:50:50.000000');

/*Table structure for table `t_sys_user_role` */

DROP TABLE IF EXISTS `t_sys_user_role`;

CREATE TABLE `t_sys_user_role` (
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY `FKpshjnr5jb9asjww3lc7vk46dq` (`user_id`),
  KEY `FKl2o4hxlyp8d0nt2guqsu1qssr` (`role_id`),
  CONSTRAINT `FKl2o4hxlyp8d0nt2guqsu1qssr` FOREIGN KEY (`role_id`) REFERENCES `t_sys_role` (`id`),
  CONSTRAINT `FKpshjnr5jb9asjww3lc7vk46dq` FOREIGN KEY (`user_id`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_user_role` */

insert  into `t_sys_user_role`(`role_id`,`user_id`) values 
(1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
