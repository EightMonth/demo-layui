## 菜单管理
本项目使用无菜单、权限管理方式
每当新增一个菜单时，需要手动处理好菜单（t_sys_menu）及权限(t_sys_permission、t_sys_menu_permission)的数据.
在角色管理界面进行菜单授权。

权限控制只到菜单级别，角色可关联多个菜单授权给用户

```sql
-- 添加菜单
insert into t_sys_menu(code, title, icon, href, target, parent_id) VALUES
('role-manager', '角色管理', 'fa fa-user', 'role/list', '_self', 1);
-- 添加菜单对应的权限
insert into t_sys_permission(create_time, modify_time, url, code, type, name) VALUES
(now(), now(), '/role/**', 'role:**', 'interface', '角色管理');
-- 添加菜单权限关联
insert into t_sys_menu_permission(menu_id, permission_id) values(3,11);
-- 授权菜单，可页面处理
insert into t_sys_role_menu(role_id, menu_id) VALUES (1, 3);
```

## 权限配置详细
INSERT INTO t_sys_permission 
(id, create_time,            modify_time,           url,         code,       type,        name) VALUES
(12, '2022-06-14 07:12:16', '2022-06-14 07:12:16', '/menus/**', 'menus:**', 'interface', '菜单接口');

code和url的格式需要保持一致