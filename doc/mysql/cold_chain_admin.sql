drop database if exists `cold_chain_admin`;
create database if not exists `cold_chain_admin` character set utf8mb4 collate utf8mb4_unicode_ci;

use `cold_chain_admin`;


DROP TABLE IF EXISTS `tb_company`;
CREATE TABLE `tb_company`
(
    `id`               bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `company`          varchar(100) NOT NULL COMMENT '公司名称',
    `abbreviation`     varchar(50)  NOT NULL COMMENT '公司简称',
    `company_number`   varchar(50)  NOT NULL COMMENT '企业编号',
    `company_address`  varchar(100) NOT NULL COMMENT '企业地址',
    `company_phone`    varchar(20)  NOT NULL COMMENT '企业电话',
    `company_type`     varchar(50)  NOT NULL COMMENT '企业类型',
    `management_name`  varchar(50)  NOT NULL COMMENT '质量管理员',
    `management_phone` varchar(20)  NOT NULL COMMENT '质量管理员联系电话',
    `leader`           varchar(50)  NOT NULL COMMENT '负责人',
    `leader_phone`     varchar(20)  NOT NULL COMMENT '负责人电话',
    `message`          varchar(255) NOT NULL COMMENT '备注',
    `web_address`      varchar(100) NOT NULL COMMENT '网址',
    `version`          bigint(20) DEFAULT '0' COMMENT '乐观锁',
    `create_time`      datetime   DEFAULT NULL COMMENT '创建时间',
    `update_time`      datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='企业信息表';


DROP TABLE IF EXISTS `tb_host`;
CREATE TABLE `tb_host`
(
    `id`          varchar(50) NOT NULL COMMENT '主键',
    `host_code`   varchar(50) NOT NULL COMMENT '主机编码',
    `host_name`   varchar(50) NOT NULL COMMENT '公司简称',
    `host_status` varchar(1)  NOT NULL COMMENT '主机状态: 0-正常, 1-停用',
    `host_model`  varchar(50) NOT NULL COMMENT '设备型号',
    `house_id`    varchar(50) NOT NULL COMMENT '库房ID',
    `house_code`  varchar(50) NOT NULL COMMENT '库房编码',
    `house_name`  varchar(50) NOT NULL COMMENT '库房名称',
    `sim_code`    varchar(50) NOT NULL COMMENT 'sim卡号',
    `version`     bigint(20) DEFAULT '0' COMMENT '乐观锁',
    `create_time` datetime   DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `index_host_code` (`host_code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='主机表';


DROP TABLE IF EXISTS `tb_warehouse`;
CREATE TABLE `tb_warehouse`
(
    `id`              varchar(50)    NOT NULL COMMENT '主键',
    `house_code`      varchar(50)    NOT NULL COMMENT '库房编码',
    `house_name`      varchar(50)    NOT NULL COMMENT '库房名称',
    `house_address`   varchar(100)   NOT NULL COMMENT '库房地址',
    `house_type`      varchar(1)     NOT NULL COMMENT '库房类型: 1-冷库, 2-恒温库',
    `company_id`      bigint(20)     NOT NULL COMMENT '组织ID',
    `company_name`    varchar(100)   NOT NULL COMMENT '组织名称',
    `principal_name`  varchar(50)    NOT NULL COMMENT '负责人',
    `principal_phone` varchar(20)    NOT NULL COMMENT '负责人电话',
    `longitude`       decimal(10, 6) NOT NULL COMMENT '经度',
    `latitude`        decimal(10, 6) NOT NULL COMMENT '维度',
    `area_size`       decimal(6, 0)  NOT NULL COMMENT '库房面积',
    `version`         bigint(20) DEFAULT '0' COMMENT '乐观锁',
    `create_time`     datetime   DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `index_house_code` (`house_code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='库房表';


DROP TABLE IF EXISTS `tb_device`;
CREATE TABLE `tb_device`
(
    `id`              varchar(50)  NOT NULL COMMENT '主键',
    `device_code`     varchar(50)  NOT NULL COMMENT '设备编码',
    `device_name`     varchar(50)  NOT NULL COMMENT '设备名称',
    `host_id`         varchar(50)  NOT NULL COMMENT '主机ID',
    `host_name`       varchar(50)  NOT NULL COMMENT '主机名称',
    `host_code`       varchar(50)  NOT NULL COMMENT '主机编码',
    `house_id`        varchar(50)  NOT NULL COMMENT '库房ID',
    `house_name`      varchar(50)  NOT NULL COMMENT '库房名称',
    `house_code`      varchar(50)  NOT NULL COMMENT '库房编码',
    `company_id`      bigint(20)   NOT NULL COMMENT '组织ID',
    `company_name`    varchar(100) NOT NULL COMMENT '组织名称',
    `device_status`   varchar(1)   NOT NULL COMMENT '设备状态: 0-停用, 1-使用, 2-异常',
    `max_tem`         int(6)       NOT NULL COMMENT '温度上限',
    `min_tem`         int(6)       NOT NULL COMMENT '温度下限',
    `max_hum`         int(6)       NOT NULL COMMENT '湿度上限',
    `min_hum`         int(6)       NOT NULL COMMENT '湿度下限',
    `device_interval` int(6)       NOT NULL COMMENT '采集间隔',
    `remarks`         varchar(200) NOT NULL COMMENT '备注信息',
    `version`         bigint(20) DEFAULT '0' COMMENT '乐观锁',
    `create_time`     datetime   DEFAULT NULL COMMENT '创建时间',
    `update_time`     datetime   DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `index_device_code` (`device_code`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='设备表';


DROP TABLE IF EXISTS `tb_quota_realtime`;
CREATE TABLE `tb_quota_realtime`
(
    `device_code`   varchar(50)  NOT NULL COMMENT '设备编码',
    `device_id`     varchar(50)  NOT NULL COMMENT '设备ID',
    `device_name`   varchar(50)  NOT NULL COMMENT '设备名称',
    `host_id`       varchar(50)  NOT NULL COMMENT '主机ID',
    `host_name`     varchar(50)  NOT NULL COMMENT '主机名称',
    `host_code`     varchar(50)  NOT NULL COMMENT '主机编码',
    `house_id`      varchar(50)  NOT NULL COMMENT '库房ID',
    `house_name`    varchar(50)  NOT NULL COMMENT '库房名称',
    `house_code`    varchar(50)  NOT NULL COMMENT '库房编码',
    `company_id`    bigint(20)   NOT NULL COMMENT '组织ID',
    `company_name`  varchar(100) NOT NULL COMMENT '组织名称',
    `device_status` varchar(1)   NOT NULL COMMENT '设备状态: 0-停用, 1-使用, 2-异常',
    `tem`           int(6)       NOT NULL COMMENT '温度',
    `max_tem`       int(6)       NOT NULL COMMENT '温度上限',
    `min_tem`       int(6)       NOT NULL COMMENT '温度下限',
    `hum`           int(6)       NOT NULL COMMENT '湿度',
    `max_hum`       int(6)       NOT NULL COMMENT '湿度上限',
    `min_hum`       int(6)       NOT NULL COMMENT '湿度下限',
    `tem_alert`     int(5)       NOT NULL COMMENT '温度状况: 0-正常, 1-高温, 2-低温',
    `hum_alert`     int(5)       NOT NULL COMMENT '湿度状况: 0-正常, 1-高湿, 2-低湿',
    `longitude`     varchar(30)  NOT NULL COMMENT '经度',
    `latitude`      varchar(30)  NOT NULL COMMENT '维度',
    `create_time`   datetime          DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`device_code`) USING BTREE,
    UNIQUE INDEX `index_device_status` (`device_status`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='实时指标表';

