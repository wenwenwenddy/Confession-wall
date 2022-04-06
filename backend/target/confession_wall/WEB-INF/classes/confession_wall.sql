/*
 Navicat Premium Data Transfer

 Source Server         : SQL
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : confession_wall

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 05/04/2022 14:14:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for userdata
-- ----------------------------
DROP TABLE IF EXISTS `userdata`;
CREATE TABLE `userdata`  (
  `uid` int(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`uid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for confessiondata
-- ----------------------------
DROP TABLE IF EXISTS `confessiondata`;
CREATE TABLE `confessiondata`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date` date NOT NULL,
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `KF_uid`(`uid`) USING BTREE,
  CONSTRAINT `KF_uid` FOREIGN KEY (`uid`) REFERENCES `userdata` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for commentdata
-- ----------------------------
DROP TABLE IF EXISTS `commentdata`;
CREATE TABLE `commentdata`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `uid` int(0) NOT NULL,
  `confessionid` int(0) NOT NULL,
  `content` varchar(10000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_uid`(`uid`) USING BTREE,
  INDEX `FK_confessionid`(`confessionid`) USING BTREE,
  CONSTRAINT `FK_confessionid` FOREIGN KEY (`confessionid`) REFERENCES `confessiondata` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_uid` FOREIGN KEY (`uid`) REFERENCES `userdata` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;