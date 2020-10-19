package com.hanzo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hanzo.system.entity.SysRoleMenu;
import com.hanzo.system.mapper.SysRoleMenuMapper;
import com.hanzo.system.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRoleMenusByRoleId(String[] ids) {
        List<String> list = Arrays.asList(ids);
        sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().in(SysRoleMenu::getRoleId,list));
    }
}
