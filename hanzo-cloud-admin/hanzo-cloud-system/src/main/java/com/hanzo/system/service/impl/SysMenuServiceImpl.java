package com.hanzo.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.hanzo.common.constant.CommonConstants;
import com.hanzo.common.constant.StringConstants;
import com.hanzo.system.dto.SysMenuQueryParam;
import com.hanzo.system.dto.SysMenuResultParam;
import com.hanzo.system.entity.SysMenu;
import com.hanzo.system.mapper.SysMenuMapper;
import com.hanzo.system.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanzo.system.service.ISysRoleMenuService;
import com.hanzo.system.util.CopyUtil;
import com.hanzo.system.util.TreeUtil;
import com.hanzo.system.vo.SysMenuResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;

    @Override
    public SysMenuResultVo getMenuList(SysMenuQueryParam sysMenuQueryParam) {
        List<SysMenu> sysMenuList = getAllMenuList(sysMenuQueryParam);
        List<SysMenuResultParam> sysMenuResultParam = CopyUtil.copy(sysMenuList,SysMenuResultParam.class);
        //递归实现树形结构
        List data = new TreeUtil().menuData(sysMenuResultParam);
        return SysMenuResultVo.builder()
                .results(data)
                .build();
    }

    @Override
    public void createRole(SysMenu sysMenu) {
        sysMenu.setCreateTime(new Date());
        this.setMenu(sysMenu);
        save(sysMenu);
    }

    @Override
    public void updateMenu(SysMenu sysMenu) {
        sysMenu.setModifyTime(new Date());
        this.setMenu(sysMenu);
        sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void deleteMenus(String menuIds) {
        String[] ids = StringUtils.split(menuIds, StringConstants.COMMA);
        List<String> list = Arrays.asList(ids);
        this.delete(list);
    }

    @Override
    public void exportMenuExcel(SysMenuQueryParam sysMenuQueryParam, HttpServletResponse response) {
        List<SysMenu> sysMenuList = getAllMenuList(sysMenuQueryParam);
        String file = String.valueOf(System.currentTimeMillis());
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
            String fileName = URLEncoder.encode(file, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), SysMenu.class).sheet("菜单数据信息").doWrite(sysMenuList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 查询菜单信息集合
     * @param sysMenuQueryParam
     * @return
     */
    private List<SysMenu> getAllMenuList(SysMenuQueryParam sysMenuQueryParam) {
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(sysMenuQueryParam.getMenuName())) {
            queryWrapper.like(SysMenu::getMenuName,sysMenuQueryParam.getMenuName());
        }
        queryWrapper.orderByAsc(SysMenu::getMenuId);
        return sysMenuMapper.selectList(queryWrapper);
    }

    /**
     * 处理增加菜单/按钮
     * @param sysMenu
     */
    private void setMenu(SysMenu sysMenu) {
        if (sysMenu.getParentId() == null) {
            sysMenu.setParentId(CommonConstants.TOP_MENU_ID);
        }
        if (CommonConstants.TYPE_BUTTON.equals(sysMenu.getType())) {
            sysMenu.setPath(null);
            sysMenu.setIcon(null);
            sysMenu.setComponent(null);
            sysMenu.setOrderNum(null);
        }
    }

    /**
     * 递归处理父子菜单删除
     * @param menuIds
     */
    private void delete(List<String> menuIds) {
        removeByIds(menuIds);
        //删除角色菜单关联数据
        sysRoleMenuService.deleteRoleMenusByMenuId(menuIds);
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysMenu::getParentId, menuIds);
        List<SysMenu> menus = sysMenuMapper.selectList(queryWrapper);
        if (CollectionUtils.isNotEmpty(menus)) {
            List<String> menuIdList = new ArrayList<>();
            menus.forEach(m -> menuIdList.add(String.valueOf(m.getMenuId())));
            this.delete(menuIdList);
        }
    }
}
