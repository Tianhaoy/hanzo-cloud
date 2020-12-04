package com.hanzo.auth.mapper;

import com.hanzo.auth.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author thy
 * @since 2020-10-24
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return 用户
     */
    SysUser findSysUserByName(@Param("username") String username);

    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户
     */
    SysUser findSysUserByUserId(@Param("userId") Integer userId);
}
