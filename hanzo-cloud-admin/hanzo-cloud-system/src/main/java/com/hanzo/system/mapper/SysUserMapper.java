package com.hanzo.system.mapper;

import com.hanzo.system.dto.SysUserQueryParam;
import com.hanzo.system.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> findUserDetailList(@Param("sysUserQueryParam") SysUserQueryParam sysUserQueryParam);
}
