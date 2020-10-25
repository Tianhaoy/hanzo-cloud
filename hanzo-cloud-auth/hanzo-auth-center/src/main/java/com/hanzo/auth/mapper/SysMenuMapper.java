package com.hanzo.auth.mapper;

import com.hanzo.auth.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author thy
 * @since 2020-10-24
 */
@Repository
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 获取用户权限集
     * @param username 用户名
     * @return 权限集合
     */
    List<SysMenu> findUserPermissions(@Param("username") String username);
}
