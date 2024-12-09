DROP DATABASE IF EXISTS `my_sensitive_word`;
CREATE DATABASE `my_sensitive_word`;

USE `my_sensitive_word`;

DROP TABLE IF EXISTS `sensitive_word`;
CREATE TABLE `sensitive_word`
(
    `word` varchar(64) NOT NULL COMMENT '敏感词'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='敏感词表';