package com.hanzo.system.service;

import com.hanzo.system.dto.SysRoleQueryParam;
import com.hanzo.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 获取角色信息
     * @param sysRoleQueryParam
     * @return
     */
    List<SysRole> getRoleList(SysRoleQueryParam sysRoleQueryParam);

    /**
     * 获取所有角色
     * @return
     */
    List<SysRole> getRoleOptions();

    /**
     * 根据角色名查找
     * @param roleName
     * @return
     */
    SysRole findByRoleName(String roleName);

    /**
     * 创建角色
     * @param sysRole
     */
    void createRole(SysRole sysRole);

    /**
     * 删除角色
     * @param roleIds
     */
    void deleteRole(String roleIds);

    /**
     * 修改角色
     * @param sysRole
     */
    void updateRole(SysRole sysRole);

    /**
     * 导出角色数据
     * @param sysRoleQueryParam
     * @param response
     */
    void exportRoleExcel(SysRoleQueryParam sysRoleQueryParam, HttpServletResponse response);
}
