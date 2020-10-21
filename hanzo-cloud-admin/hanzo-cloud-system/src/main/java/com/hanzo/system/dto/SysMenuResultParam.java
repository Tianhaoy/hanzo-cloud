package com.hanzo.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author thy
 * @Date 2020/10/21 23:32
 * @Description:菜单/按钮结果集
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuResultParam implements Serializable {

    /**
     * 菜单/按钮ID
     */
    private Integer menuId;

    /**
     * 上级菜单ID
     */
    private Integer parentId;

    /**
     * 菜单/按钮名称
     */
    private String menuName;

    /**
     * 对应路由path
     */
    private String path;

    /**
     * 对应路由组件component
     */
    private String component;

    /**
     * 权限标识
     */
    private String perms;

    /**
     * 图标
     */
    private String icon;

    /**
     * 类型 0菜单 1按钮
     */
    private String type;

    /**
     * 排序
     */
    private Double orderNum;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime modifyTime;

    /**
     * 子节点集合
     */
    private List<SysMenuResultParam> children;

    /**
     * 是否有父节点
     */
    private boolean hasParent;

    /**
     * 是否有子节点
     */
    private boolean hasChildren;
}
