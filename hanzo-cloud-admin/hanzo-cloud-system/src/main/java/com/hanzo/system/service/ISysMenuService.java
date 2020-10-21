package com.hanzo.system.service;

import com.hanzo.system.dto.SysMenuQueryParam;
import com.hanzo.system.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanzo.system.vo.SysMenuResultVo;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 获取菜单信息
     * @param sysMenuQueryParam
     * @return
     */
    SysMenuResultVo getMenuList(SysMenuQueryParam sysMenuQueryParam);

    /**
     * 新增菜单/按钮
     * @param sysMenu
     */
    void createRole(SysMenu sysMenu);

    /**
     * 修改菜单
     * @param sysMenu
     */
    void updateMenu(SysMenu sysMenu);

    /**
     * 删除菜单
     * @param menuIds
     */
    void deleteMenus(String menuIds);

    /**
     * 导出菜单数据
     * @param sysMenuQueryParam
     * @param response
     */
    void exportMenuExcel(SysMenuQueryParam sysMenuQueryParam, HttpServletResponse response);
}
