drop database if exists `cold_chain_user`;
create database if not exists `cold_chain_user` character set utf8mb4 collate utf8mb4_unicode_ci;

use `cold_chain_user`;

DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`
(
    `id`             bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username`       varchar(50)  NOT NULL COMMENT '用户登录名',
    `password`       varchar(300) NOT NULL COMMENT '用户密码',
    `nickname`       varchar(50)  NOT NULL COMMENT '用户名',
    `create_user_id` bigint(20) DEFAULT '0' COMMENT '创建人ID',
    `salt`           varchar(20)  NOT NULL COMMENT '加密盐',
    `email`          varchar(100) NOT NULL COMMENT '邮箱',
    `mobile`         varchar(100) NOT NULL COMMENT '手机号',
    `is_status`      varchar(1)   NOT NULL COMMENT '状态: 0正常 1冻结',
    `is_deleted`     varchar(1)   NOT NULL COMMENT '是否删除: 0未删除, 1删除',
    `version`        bigint(20) DEFAULT '0' COMMENT '乐观锁',
    `create_time`    datetime   DEFAULT NULL COMMENT '创建时间',
    `update_time`    datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='系统用户表';

INSERT INTO `tb_user`(`id`, `username`, `password`, `nickname`, `create_user_id`, `salt`, `email`, `mobile`, `is_status`, `is_deleted`, `version`, `create_time`, `update_time`) VALUES (1, 'mikuhuyo', '4a3f6aecc9222e9bf5f32425d8eb6b9a', '菜狗菜菜子', 1, 'bgX%rDCw1M', 'yueliminvc@outlook.com', '15777777777', '0', '0', 0, '2019-11-04 12:50:00', '2019-11-04 12:50:00');