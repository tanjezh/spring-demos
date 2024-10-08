DROP TABLE IF EXISTS `money`;

CREATE TABLE `money`
(
    `id`         int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name`       varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
    `money`      int(26) NOT NULL DEFAULT '0' COMMENT '钱',
    `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
    `create_at`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY          `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK
TABLES `money` WRITE;
/*!40000 ALTER TABLE `money` DISABLE KEYS */;

INSERT INTO `money` (`id`, `name`, `money`, `is_deleted`, `create_at`, `update_at`)
VALUES (1, 'tan', 100, 0, '2024-08-25 17:01:40', '2024-08-25 17:01:40');

/*!40000 ALTER TABLE `money` ENABLE KEYS */;
UNLOCK
TABLES;