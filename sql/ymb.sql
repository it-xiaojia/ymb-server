/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 50736
 Source Host           : localhost:3306
 Source Schema         : ymb

 Target Server Type    : MySQL
 Target Server Version : 50736
 File Encoding         : 65001

 Date: 07/10/2022 14:44:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ymb_article
-- ----------------------------
DROP TABLE IF EXISTS `ymb_article`;
CREATE TABLE `ymb_article`  (
  `article_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文章ID',
  `user_id` int(11) NOT NULL COMMENT '作者ID',
  `val_title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标题',
  `val_publish_date` datetime(0) NOT NULL COMMENT '发布日期',
  `val_update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `val_section` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '内容',
  `category_id` int(11) NOT NULL COMMENT '分类ID',
  `article_status_code` int(11) NULL DEFAULT 1 COMMENT '文章状态代码',
  PRIMARY KEY (`article_id`) USING BTREE,
  INDEX `fk_user_id`(`user_id`) USING BTREE,
  INDEX `fk_category_id`(`category_id`) USING BTREE,
  INDEX `fk_article_status`(`article_status_code`) USING BTREE,
  CONSTRAINT `fk_category_id` FOREIGN KEY (`category_id`) REFERENCES `ymb_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `ymb_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_article
-- ----------------------------
INSERT INTO `ymb_article` VALUES (34, 2, 'Java变量', '2022-09-14 11:26:24', '2022-09-14 11:26:27', 'int a = 0;', 2, 1);
INSERT INTO `ymb_article` VALUES (35, 2, '安卓手机使用', '2022-09-14 11:31:12', '2022-09-14 11:31:17', '测试内容', 3, 1);

-- ----------------------------
-- Table structure for ymb_article_label
-- ----------------------------
DROP TABLE IF EXISTS `ymb_article_label`;
CREATE TABLE `ymb_article_label`  (
  `article_id` int(11) NOT NULL COMMENT '文章ID',
  `label_id` int(11) NOT NULL COMMENT '标签ID',
  INDEX `fk_label_id`(`label_id`) USING BTREE,
  INDEX `fk_article_id`(`article_id`) USING BTREE,
  CONSTRAINT `fk_article_id` FOREIGN KEY (`article_id`) REFERENCES `ymb_article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_label_id` FOREIGN KEY (`label_id`) REFERENCES `ymb_label` (`label_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '文章标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_article_label
-- ----------------------------
INSERT INTO `ymb_article_label` VALUES (34, 1);
INSERT INTO `ymb_article_label` VALUES (34, 3);
INSERT INTO `ymb_article_label` VALUES (35, 2);

-- ----------------------------
-- Table structure for ymb_auth
-- ----------------------------
DROP TABLE IF EXISTS `ymb_auth`;
CREATE TABLE `ymb_auth`  (
  `auth_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID',
  `val_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '权限名称',
  `val_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '权限URL',
  `parent_auth_id` int(11) NULL DEFAULT NULL COMMENT '父权限ID',
  `val_icon_class` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'el-icon-document' COMMENT '图标类',
  `val_has_child` int(255) NULL DEFAULT 0 COMMENT '是否有子权限：0无，1有',
  PRIMARY KEY (`auth_id`) USING BTREE,
  INDEX `fk_parent_auth_id`(`parent_auth_id`) USING BTREE,
  CONSTRAINT `fk_parent_auth_id` FOREIGN KEY (`parent_auth_id`) REFERENCES `ymb_auth` (`auth_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_auth
-- ----------------------------
INSERT INTO `ymb_auth` VALUES (1, '仪表盘', 'index', NULL, 'dashboard', 0);
INSERT INTO `ymb_auth` VALUES (2, '开发管理', NULL, NULL, 'code', 1);
INSERT INTO `ymb_auth` VALUES (4, '日志管理', 'log', 2, 'profile', 0);
INSERT INTO `ymb_auth` VALUES (5, '系统管理', NULL, NULL, 'setting', 1);
INSERT INTO `ymb_auth` VALUES (6, '角色管理', 'role', 5, 'team', 0);
INSERT INTO `ymb_auth` VALUES (7, '权限管理', 'auth', 5, 'safety', 0);
INSERT INTO `ymb_auth` VALUES (8, '用户管理', 'user', 5, 'user', 0);
INSERT INTO `ymb_auth` VALUES (9, '博客管理', NULL, NULL, 'read', 1);
INSERT INTO `ymb_auth` VALUES (10, '标签管理', 'label', 9, 'tags', 0);
INSERT INTO `ymb_auth` VALUES (11, '分类管理', 'category', 9, 'apartment', 0);
INSERT INTO `ymb_auth` VALUES (12, '文章管理', 'article', 9, 'file-markdown', 0);

-- ----------------------------
-- Table structure for ymb_category
-- ----------------------------
DROP TABLE IF EXISTS `ymb_category`;
CREATE TABLE `ymb_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `val_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '分类名称',
  `create_user_id` int(11) NOT NULL COMMENT '创建分类的用户ID',
  `parent_category_id` int(11) NULL DEFAULT NULL COMMENT '父分类ID',
  PRIMARY KEY (`category_id`) USING BTREE,
  INDEX `fk_parent_category_id`(`parent_category_id`) USING BTREE,
  INDEX `fk_category_create_user_id`(`create_user_id`) USING BTREE,
  CONSTRAINT `fk_category_create_user_id` FOREIGN KEY (`create_user_id`) REFERENCES `ymb_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_parent_category_id` FOREIGN KEY (`parent_category_id`) REFERENCES `ymb_category` (`category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '文章分类专栏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_category
-- ----------------------------
INSERT INTO `ymb_category` VALUES (1, 'Java', 2, NULL);
INSERT INTO `ymb_category` VALUES (2, '基础语法', 2, 1);
INSERT INTO `ymb_category` VALUES (3, '安卓', 2, NULL);

-- ----------------------------
-- Table structure for ymb_dict
-- ----------------------------
DROP TABLE IF EXISTS `ymb_dict`;
CREATE TABLE `ymb_dict`  (
  `val_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典键',
  `val_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '字典值',
  `dict_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `dict_category_id` int(11) NULL DEFAULT NULL COMMENT '字典分类ID',
  PRIMARY KEY (`dict_id`) USING BTREE,
  INDEX `fk_dict_category_id`(`dict_category_id`) USING BTREE,
  CONSTRAINT `fk_dict_category_id` FOREIGN KEY (`dict_category_id`) REFERENCES `ymb_dict_category` (`dict_category_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_dict
-- ----------------------------
INSERT INTO `ymb_dict` VALUES ('0', '未登录', 1, 1);
INSERT INTO `ymb_dict` VALUES ('1', '已登录', 2, 1);
INSERT INTO `ymb_dict` VALUES ('0', '普通', 3, 2);
INSERT INTO `ymb_dict` VALUES ('0', '未发布', 4, 3);
INSERT INTO `ymb_dict` VALUES ('1', '已发布', 5, 3);

-- ----------------------------
-- Table structure for ymb_dict_category
-- ----------------------------
DROP TABLE IF EXISTS `ymb_dict_category`;
CREATE TABLE `ymb_dict_category`  (
  `dict_category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '字典分类ID',
  `val_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '字典分类名称',
  PRIMARY KEY (`dict_category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '字典分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_dict_category
-- ----------------------------
INSERT INTO `ymb_dict_category` VALUES (1, '用户账号状态');
INSERT INTO `ymb_dict_category` VALUES (2, '日志等级');
INSERT INTO `ymb_dict_category` VALUES (3, '文章状态');

-- ----------------------------
-- Table structure for ymb_label
-- ----------------------------
DROP TABLE IF EXISTS `ymb_label`;
CREATE TABLE `ymb_label`  (
  `label_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `val_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`label_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_label
-- ----------------------------
INSERT INTO `ymb_label` VALUES (1, '开发');
INSERT INTO `ymb_label` VALUES (2, '计算机');
INSERT INTO `ymb_label` VALUES (3, '软件');

-- ----------------------------
-- Table structure for ymb_log
-- ----------------------------
DROP TABLE IF EXISTS `ymb_log`;
CREATE TABLE `ymb_log`  (
  `log_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '日志ID',
  `val_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '日志名称',
  `val_sesion` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '日志内容',
  `val_markup_date` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录时间',
  `log_level_code` int(11) NULL DEFAULT 1 COMMENT '日志级别代码',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `fk_level`(`log_level_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_log
-- ----------------------------
INSERT INTO `ymb_log` VALUES (1, '测试日志', '这是一条普通日志', '2022-09-14 17:50:01', 1);

-- ----------------------------
-- Table structure for ymb_role
-- ----------------------------
DROP TABLE IF EXISTS `ymb_role`;
CREATE TABLE `ymb_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `val_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名称',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_role
-- ----------------------------
INSERT INTO `ymb_role` VALUES (1, '管理员');

-- ----------------------------
-- Table structure for ymb_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `ymb_role_auth`;
CREATE TABLE `ymb_role_auth`  (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `auth_id` int(11) NOT NULL COMMENT '权限ID',
  INDEX `fk_role_id`(`role_id`) USING BTREE,
  INDEX `fk_auth_id`(`auth_id`) USING BTREE,
  CONSTRAINT `fk_auth_id` FOREIGN KEY (`auth_id`) REFERENCES `ymb_auth` (`auth_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_role_id` FOREIGN KEY (`role_id`) REFERENCES `ymb_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_role_auth
-- ----------------------------
INSERT INTO `ymb_role_auth` VALUES (1, 1);
INSERT INTO `ymb_role_auth` VALUES (1, 2);
INSERT INTO `ymb_role_auth` VALUES (1, 4);
INSERT INTO `ymb_role_auth` VALUES (1, 5);
INSERT INTO `ymb_role_auth` VALUES (1, 6);
INSERT INTO `ymb_role_auth` VALUES (1, 7);
INSERT INTO `ymb_role_auth` VALUES (1, 8);
INSERT INTO `ymb_role_auth` VALUES (1, 9);
INSERT INTO `ymb_role_auth` VALUES (1, 10);
INSERT INTO `ymb_role_auth` VALUES (1, 11);
INSERT INTO `ymb_role_auth` VALUES (1, 12);

-- ----------------------------
-- Table structure for ymb_user
-- ----------------------------
DROP TABLE IF EXISTS `ymb_user`;
CREATE TABLE `ymb_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `val_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '账号',
  `val_password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `val_username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '晓梦' COMMENT '用户姓名或昵称',
  `account_status_code` int(11) NULL DEFAULT 1 COMMENT '账号状态代码',
  `user_role_id` int(11) NULL DEFAULT NULL COMMENT '用户角色ID',
  `val_last_login_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '上次登录成功的时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `fk_user_role_id`(`user_role_id`) USING BTREE,
  INDEX `fk_user_status`(`account_status_code`) USING BTREE,
  CONSTRAINT `fk_user_role_id` FOREIGN KEY (`user_role_id`) REFERENCES `ymb_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ymb_user
-- ----------------------------
INSERT INTO `ymb_user` VALUES (2, '5515463185720365ca1d15bccaf95ece', '382ffb56ccea7e2b246d7a0af36264cb', '晓梦', 1, 1, '2022-10-07 14:34:17');

SET FOREIGN_KEY_CHECKS = 1;
