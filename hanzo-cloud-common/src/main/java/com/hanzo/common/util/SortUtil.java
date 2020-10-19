package com.hanzo.common.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.github.pagehelper.PageHelper;
import com.hanzo.common.constant.CommonConstants;
import com.hanzo.common.model.QueryRequest;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author thy
 * @Date 2020/10/18 15:07
 * @Description:处理排序 for Mybatis Plus
 */
public class SortUtil {

    /**
     * 处理排序 for mybatis-plus
     *
     * @param request           QueryRequest
     * @param wrapper           wrapper
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handleWrapperSort(QueryRequest request, QueryWrapper<?> wrapper, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        String sortField = request.getField();
        if (camelToUnderscore) {
            sortField = HanZoUtil.camelToUnderscore(sortField);
            defaultSort = HanZoUtil.camelToUnderscore(defaultSort);
        }
        if (StringUtils.isNotBlank(request.getField())
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(request.getField(), "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), CommonConstants.ORDER_DESC)) {
                wrapper.orderByDesc(sortField);
            } else {
                wrapper.orderByAsc(sortField);
            }
        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, CommonConstants.ORDER_DESC)) {
                    wrapper.orderByDesc(defaultSort);
                } else {
                    wrapper.orderByAsc(defaultSort);
                }
            }
        }
    }

    /**
     * 处理排序（分页情况下） for mybatis-plus
     *
     * @param request           QueryRequest
     * @param defaultSort       默认排序的字段
     * @param defaultOrder      默认排序规则
     * @param camelToUnderscore 是否开启驼峰转下划线
     */
    public static void handlePageSort(QueryRequest request, String defaultSort, String defaultOrder, boolean camelToUnderscore) {
        String sortField = request.getField();
        if (camelToUnderscore) {
            sortField = HanZoUtil.camelToUnderscore(sortField);
            defaultSort = HanZoUtil.camelToUnderscore(defaultSort);
        }
        if (StringUtils.isNotBlank(request.getField())
                && StringUtils.isNotBlank(request.getOrder())
                && !StringUtils.equalsIgnoreCase(request.getField(), "null")
                && !StringUtils.equalsIgnoreCase(request.getOrder(), "null")) {
            if (StringUtils.equals(request.getOrder(), CommonConstants.ORDER_DESC)) {
                String orderBy = sortField + " " + CommonConstants.ORDER_DESC ;
                PageHelper.orderBy(orderBy);
            } else {
                String orderBy = sortField + " " + CommonConstants.ORDER_ASC ;
                PageHelper.orderBy(orderBy);
            }
        } else {
            if (StringUtils.isNotBlank(defaultSort)) {
                if (StringUtils.equals(defaultOrder, CommonConstants.ORDER_DESC)) {
                    String orderBy = defaultSort + " " + CommonConstants.ORDER_DESC ;
                    PageHelper.orderBy(orderBy);
                } else {
                    String orderBy = defaultSort + " " + CommonConstants.ORDER_ASC ;
                    PageHelper.orderBy(orderBy);
                }
            }
        }
    }
}
