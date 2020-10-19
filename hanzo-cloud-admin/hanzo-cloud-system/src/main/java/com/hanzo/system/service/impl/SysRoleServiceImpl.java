package com.hanzo.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hanzo.common.constant.CommonConstants;
import com.hanzo.common.constant.StringConstants;
import com.hanzo.common.model.QueryRequest;
import com.hanzo.common.util.SortUtil;
import com.hanzo.system.dto.SysRoleQueryParam;
import com.hanzo.system.entity.SysDept;
import com.hanzo.system.entity.SysRole;
import com.hanzo.system.entity.SysRoleMenu;
import com.hanzo.system.entity.SysUser;
import com.hanzo.system.mapper.SysRoleMapper;
import com.hanzo.system.service.ISysRoleMenuService;
import com.hanzo.system.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanzo.system.service.ISysUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private ISysRoleMenuService sysRoleMenuService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Override
    public List<SysRole> getRoleList(SysRoleQueryParam sysRoleQueryParam) {
        PageHelper.startPage(sysRoleQueryParam.getPageNum(),sysRoleQueryParam.getPageSize());
        QueryRequest queryRequest = new QueryRequest();
        BeanUtils.copyProperties(sysRoleQueryParam, queryRequest);
        SortUtil.handlePageSort(queryRequest, "createTime", CommonConstants.ORDER_DESC, true);
        return sysRoleMapper.getRoleList(sysRoleQueryParam);
    }

    @Override
    public List<SysRole> getRoleOptions() {
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(SysRole::getRoleId);
        return sysRoleMapper.selectList(queryWrapper);
    }

    @Override
    public SysRole findByRoleName(String roleName) {
        return sysRoleMapper.selectOne(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleName,roleName));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createRole(SysRole sysRole) {
        sysRole.setCreateTime(new Date());
        save(sysRole);
        if (StringUtils.isNotBlank(sysRole.getMenuIds())){
            String[] menuIds = StringUtils.split(sysRole.getMenuIds(), StringConstants.COMMA);
            saveRoleMenus(sysRole,menuIds);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(String roleIds) {
        String[] ids = StringUtils.split(roleIds,StringConstants.COMMA);
        List<String> list = Arrays.asList(ids);
        removeByIds(list);
        //删除角色菜单关联数据
        sysRoleMenuService.deleteRoleMenusByRoleId(ids);
        //删除角色用户管理关系
        sysUserRoleService.deleteUserRolesByRoleId(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(SysRole sysRole) {
        sysRole.setModifyTime(new Date());
        updateById(sysRole);
        updateRoleMenus(sysRole);
    }

    @Override
    public void exportRoleExcel(SysRoleQueryParam sysRoleQueryParam, HttpServletResponse response) {
        List<SysRole> sysRoleList = getRoleList(sysRoleQueryParam);
        String file = String.valueOf(System.currentTimeMillis());
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
            String fileName = URLEncoder.encode(file, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), SysRole.class).sheet("橘色数据信息").doWrite(sysRoleList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     *  修改角色&菜单 --先delete再save
     * @param sysRole
     */
    private void updateRoleMenus(SysRole sysRole) {
        sysRoleMenuService.remove(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, sysRole.getRoleId()));
        if (StringUtils.isNotBlank(sysRole.getMenuIds())) {
            String[] menuIds = StringUtils.split(sysRole.getMenuIds(), StringConstants.COMMA);
            saveRoleMenus(sysRole, menuIds);
        }
    }

    /**
     *  保存角色&菜单
     * @param sysRole
     * @param menuIds
     */
    private void saveRoleMenus(SysRole sysRole, String[] menuIds) {
        List<SysRoleMenu> roleMenus = new ArrayList<>();
        Arrays.stream(menuIds).forEach(menuId -> {
            if (StringUtils.isNotBlank(menuId)) {
                SysRoleMenu roleMenu = SysRoleMenu.builder()
                        .menuId(Integer.valueOf(menuId))
                        .roleId(sysRole.getRoleId())
                        .build();
                roleMenus.add(roleMenu);
            }
        });
        sysRoleMenuService.saveBatch(roleMenus);
    }
}
