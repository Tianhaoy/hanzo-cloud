package com.hanzo.system.vo;

import com.hanzo.system.dto.SysDeptResultParam;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @Author thy
 * @Date 2020/10/18 14:37
 * @Description:部门架构结果集
 */
@Data
@Builder
public class SysDeptResultVo {

    /**
     * 结果List集合
     */
    private List<SysDeptResultParam> results;
}
