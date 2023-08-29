CREATE TABLE `message`
(
    `id`          bigint(20)      PRIMARY KEY AUTO_INCREMENT,
    `from`        bigint(20)      NOT NULL,
    `to`          bigint(20)      NOT NULL,
    `message_type`        tinyint(1)      NOT NULL,
    `context`     varchar(512)    NOT NULL,
    `create_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` datetime        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
