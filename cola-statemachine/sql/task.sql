/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80300
 Source Host           : localhost:0
 Source Schema         : solano

 Target Server Type    : MySQL
 Target Server Version : 80300
 File Encoding         : 65001

 Date: 09/07/2024 03:16:53
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `state` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of task
-- ----------------------------
BEGIN;
INSERT INTO `task` (`id`, `state`, `name`) VALUES (1, 'init', '单程优化');
INSERT INTO `task` (`id`, `state`, `name`) VALUES (2, 'init', '多程对齐');
INSERT INTO `task` (`id`, `state`, `name`) VALUES (3, 'init', '常规作业');
INSERT INTO `task` (`id`, `state`, `name`) VALUES (4, 'init', '常规质检');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
