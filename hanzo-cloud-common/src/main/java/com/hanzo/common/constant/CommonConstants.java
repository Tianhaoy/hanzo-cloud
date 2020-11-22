package com.hanzo.common.constant;

/**
 * @Author thy
 * @Date 2020/9/28 16:26
 * @Description:公共常量类
 */
public interface CommonConstants {

    /**
     * 用户状态：有效
     */
    String STATUS_VALID = "1";
    /**
     * 用户状态：锁定
     */
    String STATUS_LOCK = "0";

    /**
     * 默认头像
     */
    String DEFAULT_AVATAR = "default.jpg";

    /**
     * 性别男
     */
    String SEX_MALE = "0";

    /**
     * 性别女
     */
    String SEX_FEMALE = "1";

    /**
     * 性别保密
     */
    String SEX_UNKNOW = "2";

    /**
     * 默认密码
     */
    String DEFAULT_PASSWORD = "123456";

    /**
     * 注册用户默认角色ID 可查看，新增，导出，不可修改，删除
     */
    Integer REGISTER_ROLE_ID = 3;

    /**
     * 测试导出文档路径
     */
    String SYS_USER_EXCEL_PATH = "F:\\java demo\\git demo\\hanzo\\data\\excel\\user\\";

    /**
     * 排序规则：降序
     */
    String ORDER_DESC = "desc";
    /**
     * 排序规则：升序
     */
    String ORDER_ASC = "asc";

    /**
     * 菜单
     */
    String TYPE_MENU = "0";

    /**
     * 按钮
     */
    String TYPE_BUTTON = "1";

    /**
     * 顶级菜单ID
     */
    Integer TOP_MENU_ID = 0;
}
