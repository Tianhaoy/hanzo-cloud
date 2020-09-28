package com.hanzo.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @Author thy
 * @Date 2020/9/19 0:11
 * @Description:
 */
@Data
public class sysClient {

    @TableId(value = "c_id",type = IdType.INPUT)
    private Integer cId;

    private String cNameEng;

    private String cNameCn;

    private String cDetail;

    private Integer cLock;

}
