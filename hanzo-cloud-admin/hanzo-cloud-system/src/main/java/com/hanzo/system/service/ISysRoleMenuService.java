package com.hanzo.system.service;

import com.hanzo.system.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {

    /**
     *  删除角色菜单关联数据
     * @param ids
     */
    @Transactional(rollbackFor = Exception.class)
    void deleteRoleMenusByRoleId(String[] ids);
}
