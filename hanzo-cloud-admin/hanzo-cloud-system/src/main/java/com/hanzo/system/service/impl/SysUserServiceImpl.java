package com.hanzo.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.hanzo.common.constant.CommonConstants;
import com.hanzo.common.constant.StringConstants;
import com.hanzo.system.dto.SysUserQueryParam;
import com.hanzo.system.dto.SysUserUpdateProfileParam;
import com.hanzo.system.entity.SysUser;
import com.hanzo.system.entity.SysUserRole;
import com.hanzo.system.mapper.SysUserMapper;
import com.hanzo.system.service.ISysUserRoleService;
import com.hanzo.system.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * 用户表 服务实现类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<SysUser> findUserDetailList(SysUserQueryParam sysUserQueryParam) {
        PageHelper.startPage(sysUserQueryParam.getPageNum(),sysUserQueryParam.getPageSize());
        return sysUserMapper.findUserDetailList(sysUserQueryParam);
    }

    @Override
    public SysUser findByName(String username) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUsername, username);
        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createUser(SysUser sysUser) {
        sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        sysUser.setCreateTime(new Date());
        save(sysUser);
        String[] roles = StringUtils.split(sysUser.getRoleId(),StringConstants.COMMA);
        saveUserRoles(sysUser, roles);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUser(SysUser sysUser) {
        sysUser.setModifyTime(new Date());
        updateById(sysUser);
        updateUserRoles(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteUser(String userIds) {
        String[] ids = StringUtils.split(userIds,StringConstants.COMMA);
        List<String> list = Arrays.asList(ids);
        removeByIds(list);
        deleteUserRolesByUserIds(ids);
    }

    @Override
    public void updateProfile(SysUserUpdateProfileParam sysUserUpdateProfileParam) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserUpdateProfileParam,sysUser);
        //TODO 后期需要根据oauth2判断下当前登录用户是否与要修改的用户一致 获取在线用户信息
        updateById(sysUser);
    }

    @Override
    public void updateAvatar(String avatar) {
        SysUser sysUserUpdateAvatarParam = SysUser.builder()
                .avatar(avatar)
                .build();
        //TODO 后期需要从oauth2中获取当前用户id
        Integer userId = 1;
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserId,userId);
        update(sysUserUpdateAvatarParam,queryWrapper);
    }

    @Override
    public boolean checkPassword(String password) {
        //TODO 后期需要从oauth2中获取当前用户id
        Integer userId = 10;
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserId,userId);
        SysUser sysUser = getOne(queryWrapper);
        return passwordEncoder.matches(password,sysUser.getPassword());
    }

    @Override
    public void updatePassword(String password) {
        SysUser sysUserUpdatePasswordParam = SysUser.builder()
                .password(passwordEncoder.encode(password))
                .build();
        //TODO 后期需要从oauth2中获取当前用户id
        Integer userId = 1;
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getUserId,userId);
        update(sysUserUpdatePasswordParam,queryWrapper);
    }

    @Override
    public void resetPassword(String userIds) {
        SysUser sysUserResetPasswordParam = SysUser.builder()
                .password(passwordEncoder.encode(CommonConstants.DEFAULT_PASSWORD))
                .build();
        String[] ids = StringUtils.split(userIds,StringConstants.COMMA);
        List<String> list = Arrays.asList(ids);
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysUser::getUserId,list);
        update(sysUserResetPasswordParam,queryWrapper);
    }

    @Override
    public void exportUserExcel(SysUserQueryParam sysUserQueryParam, HttpServletResponse response) {
        PageHelper.startPage(sysUserQueryParam.getPageNum(),sysUserQueryParam.getPageSize());
        List<SysUser> sysUserList = sysUserMapper.findUserDetailList(sysUserQueryParam);
        String file = String.valueOf(System.currentTimeMillis());
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
            String fileName = URLEncoder.encode(file, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), SysUser.class).sheet("用户数据信息").doWrite(sysUserList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 根据userId删除角色
     * @param userIds
     */
    private void deleteUserRolesByUserIds(String [] userIds) {
        List<String> list = Arrays.asList(userIds);
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(SysUserRole::getUserId, list);
        sysUserRoleService.remove(queryWrapper);
    }

    /**
     * 修改用户&角色 --先delete再save
     * @param sysUser
     */
    private void updateUserRoles(SysUser sysUser) {
        String[] roles = StringUtils.split(sysUser.getRoleId(),StringConstants.COMMA);
        deleteUserRolesByUserId(sysUser.getUserId());
        saveUserRoles(sysUser, roles);
    }

    /**
     * 根据userId删除用户的角色
     * @param userId
     */
    private void deleteUserRolesByUserId(Integer userId) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        sysUserRoleService.remove(queryWrapper);
    }

    /**
     *  保存用户&角色
     * @param sysUser
     * @param roles
     */
    private void saveUserRoles(SysUser sysUser, String[] roles) {
        List<SysUserRole> userRoles = new ArrayList<>();
        Arrays.stream(roles).forEach(roleId -> {
            SysUserRole userRole = SysUserRole.builder()
                    .userId(sysUser.getUserId())
                    .roleId(Integer.valueOf(roleId))
                    .build();
            userRoles.add(userRole);
        });
        sysUserRoleService.saveBatch(userRoles);
    }
}
