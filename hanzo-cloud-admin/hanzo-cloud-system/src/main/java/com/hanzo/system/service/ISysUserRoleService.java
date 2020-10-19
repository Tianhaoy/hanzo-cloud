package com.hanzo.system.service;

import com.hanzo.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
public interface ISysUserRoleService extends IService<SysUserRole> {
    /**
     *  删除角色用户管理关系
     * @param ids
     */
    void deleteUserRolesByRoleId(String[] ids);
}
