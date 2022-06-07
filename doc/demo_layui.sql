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

/*Table structure for table `t_sys_menu` */

DROP TABLE IF EXISTS `t_sys_menu`;

CREATE TABLE `t_sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `icon` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `href` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `target` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `parent_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_menu` */

insert  into `t_sys_menu`(`id`,`code`,`title`,`icon`,`href`,`target`,`parent_id`) values 
(1,'sys_init','系统菜单','fa fa-address-book','/page/white','_self',0),
(4,'first','一级菜单','fa fa-home','page/welcome-1.html','_self',1),
(6,'second','二级菜单','fa fa-window-maximize','page/menu.html','_self',1),
(8,'user-manager','用户管理','fa fa-user','user/list','_self',1),
(9,'menu-manager','菜单管理','fa fa-bars','menus/list','_self',1);

/*Table structure for table `t_sys_menu_permission` */

DROP TABLE IF EXISTS `t_sys_menu_permission`;

CREATE TABLE `t_sys_menu_permission` (
  `menu_id` bigint DEFAULT NULL,
  `permission_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_menu_permission` */

insert  into `t_sys_menu_permission`(`menu_id`,`permission_id`) values 
(8,2),
(6,1),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8),
(1,9),
(4,3),
(9,10);

/*Table structure for table `t_sys_permission` */

DROP TABLE IF EXISTS `t_sys_permission`;

CREATE TABLE `t_sys_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime(6) DEFAULT NULL,
  `modify_time` datetime(6) DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `code` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_permission` */

insert  into `t_sys_permission`(`id`,`create_time`,`modify_time`,`url`,`code`,`type`,`name`) values 
(1,'2021-11-01 08:32:11.000000','2021-11-01 08:32:43.000000','/page/menu.html','page:menu','interface','菜单模板页'),
(2,'2021-11-01 08:33:00.000000','2021-11-01 08:33:02.000000','/user/**','user:**','interface','用户管理权限'),
(3,'2022-06-06 13:50:32.000000','2022-06-06 13:50:37.000000','/page/welcome-1.html','page:welcom-1','interface','首页模板页'),
(4,'2022-06-06 14:06:04.000000','2022-06-06 14:06:07.000000','/menus/index_info','menus:index_info','interface','菜单数据接口'),
(5,'2022-06-06 15:13:08.000000','2022-06-06 15:13:13.000000','/','/','interface','首页'),
(6,'2022-06-06 16:38:49.000000','2022-06-06 16:38:52.000000','/page/white','page:white','interface','403页面'),
(7,'2022-06-06 17:16:04.000000','2022-06-06 17:16:09.000000','/user/setting','user:setting','interface','用户主页'),
(8,'2022-06-06 17:16:07.000000','2022-06-06 17:16:11.000000','/user/password','user:password','interface','修改密码页面'),
(9,'2022-06-07 09:49:18.000000','2022-06-07 09:49:28.000000','/user/modify_password','user:modify_password','interface','修改接口'),
(10,'2022-06-07 14:48:20.000000','2022-06-07 14:48:22.000000','/menus/**','menus:**','interface','菜单管理');

/*Table structure for table `t_sys_role` */

DROP TABLE IF EXISTS `t_sys_role`;

CREATE TABLE `t_sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `modify_time` datetime(6) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_role` */

insert  into `t_sys_role`(`id`,`code`,`create_time`,`modify_time`,`name`,`remark`) values 
(1,'admin','2021-10-25 09:32:40.000000','2021-10-25 09:32:43.000000','admin','管理员'),
(2,'user','2021-10-25 09:37:02.000000','2021-10-25 09:37:05.000000','user','普通用户'),
(3,'default','2022-06-06 13:51:45.000000','2022-06-06 13:51:48.000000','default','默认角色');

/*Table structure for table `t_sys_role_menu` */

DROP TABLE IF EXISTS `t_sys_role_menu`;

CREATE TABLE `t_sys_role_menu` (
  `role_id` bigint NOT NULL,
  `menu_id` bigint NOT NULL,
  KEY `FKbqr2ew547n1y29pyhbm5rmqyj` (`role_id`),
  KEY `FK74t6b2m3hvcypexhkuinaiu2k` (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_role_menu` */

insert  into `t_sys_role_menu`(`role_id`,`menu_id`) values 
(1,1),
(1,4),
(1,6),
(1,8),
(2,1),
(2,8),
(3,1),
(1,9);

/*Table structure for table `t_sys_user` */

DROP TABLE IF EXISTS `t_sys_user`;

CREATE TABLE `t_sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_user` */

insert  into `t_sys_user`(`id`,`create_time`,`modify_time`,`nick_name`,`password`,`username`,`account_non_expired`,`account_non_locked`,`credentials_non_expired`,`enabled`,`last_login_time`) values 
(1,'2021-11-01 09:48:32.000000','2022-06-06 17:40:13.716000','管理员','$2a$10$f60DT./ul.ntoj413kjexuEA86.hAK0z56diT3DKU3emnH53cvOMW','admin','','','','','2022-06-07 16:19:16.355000'),
(10,'2022-06-06 13:33:37.386000','2022-06-07 11:12:37.324000','普通用户','$2a$10$oi4iiIQRTvnqIeAkruT7EejHcjld.DoYbyGZ8XyeCGv3dqjFVL1py','user','','','','','2022-06-07 14:24:52.193000');

/*Table structure for table `t_sys_user_role` */

DROP TABLE IF EXISTS `t_sys_user_role`;

CREATE TABLE `t_sys_user_role` (
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY `FKpshjnr5jb9asjww3lc7vk46dq` (`user_id`),
  KEY `FKl2o4hxlyp8d0nt2guqsu1qssr` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

/*Data for the table `t_sys_user_role` */

insert  into `t_sys_user_role`(`role_id`,`user_id`) values 
(1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
