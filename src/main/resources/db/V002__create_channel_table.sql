CREATE TABLE `channel`
(
    `id`              bigint(20) PRIMARY KEY AUTO_INCREMENT,
    `channel_name`    varchar(32)     NULL     DEFAULT '',
    `channel_number`  varchar(32)     NULL     DEFAULT '',
    `channel_type`    varchar(32)     null     DEFAULT '',
    `create_time`     datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`     datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
