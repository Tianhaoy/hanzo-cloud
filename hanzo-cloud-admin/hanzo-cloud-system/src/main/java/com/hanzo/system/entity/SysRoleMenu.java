package com.hanzo.system.entity;

import java.io.Serializable;

import lombok.*;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 菜单/按钮ID
     */
    private Integer menuId;


}
