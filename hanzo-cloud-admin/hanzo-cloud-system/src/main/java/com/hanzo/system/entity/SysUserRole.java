package com.hanzo.system.entity;

import java.io.Serializable;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户角色关联表
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
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 角色ID
     */
    private Integer roleId;


}
