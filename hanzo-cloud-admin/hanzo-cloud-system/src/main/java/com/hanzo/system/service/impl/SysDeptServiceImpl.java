package com.hanzo.system.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hanzo.common.constant.CommonConstants;
import com.hanzo.common.constant.StringConstants;
import com.hanzo.common.model.QueryRequest;
import com.hanzo.common.util.SortUtil;
import com.hanzo.system.dto.SysDeptQueryParam;
import com.hanzo.system.dto.SysDeptResultParam;
import com.hanzo.system.entity.SysDept;
import com.hanzo.system.mapper.SysDeptMapper;
import com.hanzo.system.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hanzo.system.util.TreeUtil;
import com.hanzo.system.vo.SysDeptResultVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author thy
 * @since 2020-10-12
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Override
    public SysDeptResultVo getDeptList(SysDeptQueryParam sysDeptQueryParam){
        List<SysDept> sysDeptList = getAllDeptList(sysDeptQueryParam);
        List<SysDeptResultParam > sysDeptResultParam = new ArrayList<>();
        BeanUtils.copyProperties(sysDeptList,sysDeptResultParam);
        //递归实现树形结构
        List data = new TreeUtil().data(sysDeptResultParam);
        return SysDeptResultVo.builder()
                .results(data)
                .build();
    }

    @Override
    public void createDept(SysDept sysDept) {
        sysDept.setCreateTime(new Date());
        save(sysDept);
    }

    @Override
    public void deleteDept(String deptIds) {
        String[] ids = deptIds.split(StringConstants.COMMA);
        List<String> list = Arrays.asList(ids);
        removeByIds(list);
    }

    @Override
    public void updateDept(SysDept sysDept) {
        sysDept.setModifyTime(new Date());
        updateById(sysDept);
    }

    @Override
    public void exportDeptExcel(SysDeptQueryParam sysDeptQueryParam, HttpServletResponse response) {
        List<SysDept> sysDeptList = getAllDeptList(sysDeptQueryParam);
        String file = String.valueOf(System.currentTimeMillis());
        // 这里 需要指定写用哪个class去写，然后写到第一个sheet，名字为模板 然后文件流会自动关闭
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyExcel没有关系
            String fileName = URLEncoder.encode(file, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), SysDept.class).sheet("部门数据信息").doWrite(sysDeptList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 查询部门信息集合
     * @param sysDeptQueryParam
     * @return
     */
    private List<SysDept> getAllDeptList(SysDeptQueryParam sysDeptQueryParam) {
        QueryWrapper<SysDept> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(sysDeptQueryParam.getDeptName())) {
            queryWrapper.lambda().like(SysDept::getDeptName,sysDeptQueryParam.getDeptName());
        }
        QueryRequest queryRequest = new QueryRequest();
        BeanUtils.copyProperties(sysDeptQueryParam, queryRequest);
        SortUtil.handleWrapperSort(queryRequest, queryWrapper, "orderNum", CommonConstants.ORDER_ASC, true);
        return sysDeptMapper.selectList(queryWrapper);
    }
}
