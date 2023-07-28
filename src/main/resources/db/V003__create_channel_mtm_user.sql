CREATE TABLE `channel_mtm_user`
(
    `id`              bigint(20)      PRIMARY KEY AUTO_INCREMENT,
    `user_id`         bigint(20)      NOT NULL,
    `channel_id`      bigint(20)      NOT NULL,
    `role`            tinyint(1)      NULL     DEFAULT 0,
    `create_time`     datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`     datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
