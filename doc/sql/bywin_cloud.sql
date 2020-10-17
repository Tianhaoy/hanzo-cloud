-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `user_id`         int(20)   NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `username`        varchar(50)  NOT NULL COMMENT '用户名',
    `password`        varchar(128) NOT NULL COMMENT '密码',
    `dept_id`         int(20)   DEFAULT NULL COMMENT '部门ID',
    `email`           varchar(128) DEFAULT NULL COMMENT '邮箱',
    `mobile`          varchar(20)  DEFAULT NULL COMMENT '联系电话',
    `status`          char(1)      NOT NULL COMMENT '状态 0锁定 1有效',
    `create_time`     datetime     NOT NULL COMMENT '创建时间',
    `modify_time`     datetime     DEFAULT NULL COMMENT '修改时间',
    `last_login_time` datetime     DEFAULT NULL COMMENT '最近访问时间',
    `sex`             char(1)      DEFAULT NULL COMMENT '性别 0男 1女 2保密',
    `is_tab`          char(1)      DEFAULT NULL COMMENT '是否开启tab，0关闭 1开启',
    `theme`           varchar(10)  DEFAULT NULL COMMENT '主题',
    `avatar`          varchar(100) DEFAULT NULL COMMENT '头像',
    `description`     varchar(100) DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`user_id`) USING BTREE,
) ENGINE = InnoDB
  AUTO_INCREMENT = 18
  DEFAULT CHARSET = utf8
  ROW_FORMAT = DYNAMIC COMMENT ='用户表';