CREATE TABLE `planet`
(
    `id`              bigint(20)      PRIMARY KEY AUTO_INCREMENT,
    `master_id`       bigint(20)      NOT NULL,
    `users`           int(10)         NULL DEFAULT 0,
    `type`            tinyint(1)      NULL     DEFAULT 0,
    `icon`            varchar(255)    NULL     DEFAULT '',
    `introduction`    varchar(128)    NULL     DEFAULT NULL,
    `coordinate`      varchar(32)     NOT NULL,
    `authentication`  tinyint(1)      NULL     DEFAULT 0,
    `create_time`     datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`     datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
