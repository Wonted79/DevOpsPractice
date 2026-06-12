-- ============================================================
-- 데이터베이스 생성
-- ============================================================
CREATE DATABASE IF NOT EXISTS dockerex1
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE dockerex1;

-- ============================================================
-- items 테이블 생성
-- ============================================================
CREATE TABLE IF NOT EXISTS items
(
    id          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '아이템 ID',
    name        VARCHAR(255) NOT NULL                COMMENT '아이템 이름',
    description VARCHAR(255)                         COMMENT '아이템 설명',
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  COMMENT = '아이템 테이블';
