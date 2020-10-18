package com.hanzo.system.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Size;

/**
 * <p>
 * 部门表
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDept implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 部门ID
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    @ApiModelProperty(value = "部门ID", name = "deptId", required = true)
    @ExcelProperty(value = "部门ID",index = 0)
    private Integer deptId;

    /**
     * 上级部门ID
     */
    @ApiModelProperty(value = "上级部门ID", name = "parentId", required = true)
    @ExcelProperty(value = "上级部门ID",index = 1)
    private Integer parentId;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称", name = "deptName", required = true)
    @Size(min = 3, max = 10, message = "部门名称长度为3位-10位")
    @ExcelProperty(value = "部门名称",index = 2)
    private String deptName;

    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", name = "orderNum", required = true)
    @ExcelProperty(value = "排序",index = 3)
    private Double orderNum;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间",index = 4)
    private Date createTime;

    /**
     * 修改时间
     */
    @ExcelIgnore
    private Date modifyTime;


}
