package com.hanzo.system.mapper;

import com.hanzo.system.dto.SysRoleQueryParam;
import com.hanzo.system.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 获取角色信息
     * @param sysRoleQueryParam
     * @return
     */
    List<SysRole> getRoleList(SysRoleQueryParam sysRoleQueryParam);
}
