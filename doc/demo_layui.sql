
CREATE DATABASE /*!32312 IF NOT EXISTS*/`demo_security` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `demo_security`;

/*Table structure for table `t_sys_menu` */

create table t_sys_menu
(
    id        bigint auto_increment
        primary key,
    code      varchar(255) null,
    title     varchar(255) null,
    icon      varchar(255) null,
    href      varchar(255) null,
    target    varchar(255) null,
    parent_id bigint       null
);

INSERT INTO t_sys_menu (id, code, title, icon, href, target, parent_id) VALUES (1, 'sys_init', '系统菜单', 'fa fa-address-book', '/page/white', '_self', -1);
INSERT INTO t_sys_menu (id, code, title, icon, href, target, parent_id) VALUES (2, 'user-manager', '用户管理', 'fa fa-user', 'user/list', '_self', 1);
INSERT INTO t_sys_menu (id, code, title, icon, href, target, parent_id) VALUES (3, 'role-manager', '角色管理', 'fa fa-user', 'role/list', '_self', 1);

create table t_sys_menu_permission
(
    menu_id       bigint null,
    permission_id bigint null
);

INSERT INTO t_sys_menu_permission (menu_id, permission_id) VALUES (2, 2);
INSERT INTO t_sys_menu_permission (menu_id, permission_id) VALUES (1, 4);
INSERT INTO t_sys_menu_permission (menu_id, permission_id) VALUES (1, 5);
INSERT INTO t_sys_menu_permission (menu_id, permission_id) VALUES (1, 6);
INSERT INTO t_sys_menu_permission (menu_id, permission_id) VALUES (1, 7);
INSERT INTO t_sys_menu_permission (menu_id, permission_id) VALUES (1, 8);
INSERT INTO t_sys_menu_permission (menu_id, permission_id) VALUES (1, 9);
INSERT INTO t_sys_menu_permission (menu_id, permission_id) VALUES (3, 11);
INSERT INTO t_sys_menu_permission (menu_id, permission_id) VALUES (3, 12);

create table t_sys_permission
(
    id          bigint auto_increment
        primary key,
    create_time datetime(6)  null,
    modify_time datetime(6)  null,
    url         varchar(255) null,
    code        varchar(255) null,
    type        varchar(255) null,
    name        varchar(255) null
);

INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (1, '2021-11-01 08:32:11', '2021-11-01 08:32:43', '/page/menu.html', 'page:menu', 'interface', '菜单模板页');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (2, '2021-11-01 08:33:00', '2021-11-01 08:33:02', '/user/**', 'user:**', 'interface', '用户管理权限');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (3, '2022-06-06 13:50:32', '2022-06-06 13:50:37', '/page/welcome-1.html', 'page:welcom-1', 'interface', '首页模板页');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (4, '2022-06-06 14:06:04', '2022-06-06 14:06:07', '/menus/index_info', 'menus:index_info', 'interface', '菜单数据接口');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (5, '2022-06-06 15:13:08', '2022-06-06 15:13:13', '/', '/', 'interface', '首页');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (6, '2022-06-06 16:38:49', '2022-06-06 16:38:52', '/page/white', 'page:white', 'interface', '403页面');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (7, '2022-06-06 17:16:04', '2022-06-06 17:16:09', '/user/setting', 'user:setting', 'interface', '用户主页');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (8, '2022-06-06 17:16:07', '2022-06-06 17:16:11', '/user/password', 'user:password', 'interface', '修改密码页面');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (9, '2022-06-07 09:49:18', '2022-06-07 09:49:28', '/user/modify_password', 'user:modify_password', 'interface', '修改接口');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (11, '2022-06-08 15:47:22', '2022-06-08 15:47:22', '/role/**', 'role:**', 'interface', '角色管理');
INSERT INTO t_sys_permission (id, create_time, modify_time, url, code, type, name) VALUES (12, '2022-06-14 07:12:16', '2022-06-14 07:12:16', '/menus/**', 'menus:**', 'interface', '菜单接口');

create table t_sys_role
(
    id          bigint auto_increment
        primary key,
    code        varchar(255) null,
    create_time datetime(6)  null,
    modify_time datetime(6)  null,
    name        varchar(255) null,
    remark      varchar(255) null
);

INSERT INTO t_sys_role (id, code, create_time, modify_time, name, remark) VALUES (1, 'admin', '2021-10-25 09:32:40', '2021-10-25 09:32:43', '管理员', '管理员');
INSERT INTO t_sys_role (id, code, create_time, modify_time, name, remark) VALUES (2, 'default', '2022-06-06 13:51:45', '2022-06-06 13:51:48', '默认角色', '默认角色');

create table t_sys_role_menu
(
    role_id bigint not null,
    menu_id bigint not null
);

create index FK74t6b2m3hvcypexhkuinaiu2k
    on t_sys_role_menu (menu_id);

create index FKbqr2ew547n1y29pyhbm5rmqyj
    on t_sys_role_menu (role_id);

INSERT INTO t_sys_role_menu (role_id, menu_id) VALUES (2, 1);
INSERT INTO t_sys_role_menu (role_id, menu_id) VALUES (1, 1);
INSERT INTO t_sys_role_menu (role_id, menu_id) VALUES (1, 2);
INSERT INTO t_sys_role_menu (role_id, menu_id) VALUES (1, 3);

create table t_sys_user
(
    id                      bigint auto_increment
        primary key,
    create_time             datetime(6)  null,
    modify_time             datetime(6)  null,
    nick_name               varchar(255) null,
    password                varchar(255) null,
    username                varchar(255) null,
    account_non_expired     bit          null,
    account_non_locked      bit          null,
    credentials_non_expired bit          null,
    enabled                 bit          not null,
    last_login_time         datetime(6)  null
);

INSERT INTO t_sys_user (id, create_time, modify_time, nick_name, password, username, account_non_expired, account_non_locked, credentials_non_expired, enabled, last_login_time) VALUES (1, '2021-11-01 09:48:32', '2022-06-06 17:40:13.716000', '管理员', '$2a$10$f60DT./ul.ntoj413kjexuEA86.hAK0z56diT3DKU3emnH53cvOMW', 'admin', true, true, true, true, '2022-07-13 17:03:40.788000');

create table t_sys_user_role
(
    role_id bigint not null,
    user_id bigint not null
);

create index FKl2o4hxlyp8d0nt2guqsu1qssr
    on t_sys_user_role (role_id);

create index FKpshjnr5jb9asjww3lc7vk46dq
    on t_sys_user_role (user_id);

INSERT INTO t_sys_user_role (role_id, user_id) VALUES (1, 1);