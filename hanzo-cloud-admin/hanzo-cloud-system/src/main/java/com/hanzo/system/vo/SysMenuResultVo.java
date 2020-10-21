package com.hanzo.system.vo;

import com.hanzo.system.dto.SysMenuResultParam;
import lombok.Builder;
import lombok.Data;

import java.util.List;


/**
 * @Author thy
 * @Date 2020/10/21 23:31
 * @Description:菜单/按钮结果集
 */
@Data
@Builder
public class SysMenuResultVo {

    /**
     * 结果List集合
     */
    private List<SysMenuResultParam> results;
}
