package com.hanzo.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * @Author thy
 * @Date 2020/10/18 14:40
 * @Description:部门架构结果参数
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDeptResultParam implements Serializable {


    /**
     * 部门ID
     */
    private Integer deptId;

    /**
     * 上级部门ID
     */
    private Integer parentId;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 排序
     */
    private Double orderNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 子节点集合
     */
    private List<SysDeptResultParam> children;

    /**
     * 是否有父节点
     */
    private boolean hasParent;

    /**
     * 是否有子节点
     */
    private boolean hasChildren;
}
