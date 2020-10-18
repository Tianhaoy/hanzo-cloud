package com.hanzo.system.service;

import com.hanzo.system.dto.SysDeptQueryParam;
import com.hanzo.system.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hanzo.system.vo.SysDeptResultVo;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
public interface ISysDeptService extends IService<SysDept> {

    /**
     * 获取部门信息
     * @param sysDeptQueryParam
     * @return
     */
    SysDeptResultVo getDeptList(SysDeptQueryParam sysDeptQueryParam);

    /**
     * 添加部门
     * @param sysDept
     */
    void createDept(SysDept sysDept);

    /**
     * 删除部门
     * @param deptIds
     */
    void deleteDept(String deptIds);

    /**
     * 修改部门
     * @param sysDept
     */
    void updateDept(SysDept sysDept);

    /**
     * 导出部门数据
     * @param sysDeptQueryParam
     * @param response
     */
    void exportDeptExcel(SysDeptQueryParam sysDeptQueryParam, HttpServletResponse response);
}
