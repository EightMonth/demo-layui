package com.example.demolayui.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demolayui.entity.SysMenu;
import com.example.demolayui.mapper.MenuMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author kezhijie@wuhandsj.com
 * @date 2022/5/30 15:10
 */
@Service
@Transactional
public class MenuService extends ServiceImpl<MenuMapper, SysMenu> {


    public SysMenu tree() {
        SysMenu root = rootMenu();
        root.setChild(treeRecursion(root));
        return root;
    }

    private List<SysMenu> treeRecursion(SysMenu menu){
        List<SysMenu> items = baseMapper.findByParenId(menu.getId());
        if (!CollectionUtils.isEmpty(items)) {
            items.forEach(p -> p.setChild(treeRecursion(p)));
        }
        return items;
    }

    public void update(SysMenu menu) {

    }

    public void delete(SysMenu menu) {

    }

    private SysMenu load(Long id) {
        return baseMapper.selectById(id);
    }

    private SysMenu rootMenu() {
        return baseMapper.findByCode("sys_init");
    }

}
