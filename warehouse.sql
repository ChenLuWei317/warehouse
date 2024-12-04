/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : warehouse

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 04/12/2024 11:43:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for 软工2202_09_05_29人员表
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29人员表`;
CREATE TABLE `软工2202_09_05_29人员表`  (
  `人员代码` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `密码` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `姓名` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `性别` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `出生日期` date NULL DEFAULT NULL,
  `身份证号` varchar(18) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `籍贯` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `家庭地址` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `联系电话` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `备注` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`人员代码`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29人员表
-- ----------------------------
INSERT INTO `软工2202_09_05_29人员表` VALUES ('1', 'admin', 'admin', '男', '2019-10-01', '3', '丰富', '方法', '1111', NULL);
INSERT INTO `软工2202_09_05_29人员表` VALUES ('2', '123abc', 'xxx', '男', '1999-11-26', '350125201911260011', 'fjut', 'ds', '13635228932', NULL);
INSERT INTO `软工2202_09_05_29人员表` VALUES ('2411148253', '777', '777', '女', '2017-11-07', '777', '777', '777', '777', NULL);

-- ----------------------------
-- Table structure for 软工2202_09_05_29单号计数
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29单号计数`;
CREATE TABLE `软工2202_09_05_29单号计数`  (
  `日期` date NOT NULL,
  `进仓计数` int NULL DEFAULT 0,
  `出仓计数` int NULL DEFAULT 0,
  PRIMARY KEY (`日期`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29单号计数
-- ----------------------------
INSERT INTO `软工2202_09_05_29单号计数` VALUES ('2024-09-19', 2, 1);
INSERT INTO `软工2202_09_05_29单号计数` VALUES ('2024-12-04', 0, 2);

-- ----------------------------
-- Table structure for 软工2202_09_05_29权限管理
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29权限管理`;
CREATE TABLE `软工2202_09_05_29权限管理`  (
  `权限代码` int NOT NULL,
  `人员代码` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  INDEX `人员代码`(`人员代码`) USING BTREE,
  INDEX `权限代码`(`权限代码`) USING BTREE,
  CONSTRAINT `软工2202_09_05_29权限管理_ibfk_2` FOREIGN KEY (`人员代码`) REFERENCES `软工2202_09_05_29人员表` (`人员代码`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `软工2202_09_05_29权限管理_ibfk_3` FOREIGN KEY (`权限代码`) REFERENCES `软工2202_09_05_29权限表` (`权限代码`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29权限管理
-- ----------------------------
INSERT INTO `软工2202_09_05_29权限管理` VALUES (1, '1');
INSERT INTO `软工2202_09_05_29权限管理` VALUES (2, '1');
INSERT INTO `软工2202_09_05_29权限管理` VALUES (3, '1');
INSERT INTO `软工2202_09_05_29权限管理` VALUES (4, '1');

-- ----------------------------
-- Table structure for 软工2202_09_05_29权限表
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29权限表`;
CREATE TABLE `软工2202_09_05_29权限表`  (
  `权限代码` int NOT NULL AUTO_INCREMENT,
  `权限名称` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `备注` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`权限代码`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29权限表
-- ----------------------------
INSERT INTO `软工2202_09_05_29权限表` VALUES (1, '用户管理', NULL);
INSERT INTO `软工2202_09_05_29权限表` VALUES (2, '物料进出仓', NULL);
INSERT INTO `软工2202_09_05_29权限表` VALUES (3, '权限管理', NULL);
INSERT INTO `软工2202_09_05_29权限表` VALUES (4, '报表打印', NULL);

-- ----------------------------
-- Table structure for 软工2202_09_05_29物料表
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29物料表`;
CREATE TABLE `软工2202_09_05_29物料表`  (
  `物料代码` int NOT NULL AUTO_INCREMENT,
  `物料名称` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `规格型号` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `计量单位` varchar(8) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `库存数量` int NULL DEFAULT 0,
  `备注` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`物料代码`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29物料表
-- ----------------------------
INSERT INTO `软工2202_09_05_29物料表` VALUES (1, '拯救者r720', 'i5 1050', '台', 2223, NULL);
INSERT INTO `软工2202_09_05_29物料表` VALUES (2, 'dd', 'asjdlak', '只', 188, NULL);
INSERT INTO `软工2202_09_05_29物料表` VALUES (3, '小米手环2', '黑色', '个', 2666, '无');
INSERT INTO `软工2202_09_05_29物料表` VALUES (4, '苹果', '阿富汗', '千克', 0, '无');
INSERT INTO `软工2202_09_05_29物料表` VALUES (5, '雪碧', '箱', '卢', 3000, '无');

-- ----------------------------
-- Table structure for 软工2202_09_05_29计量单位
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29计量单位`;
CREATE TABLE `软工2202_09_05_29计量单位`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `单位` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `单位`(`单位`) USING BTREE,
  INDEX `单位_2`(`单位`, `id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29计量单位
-- ----------------------------
INSERT INTO `软工2202_09_05_29计量单位` VALUES (2, '个');
INSERT INTO `软工2202_09_05_29计量单位` VALUES (19, '件');
INSERT INTO `软工2202_09_05_29计量单位` VALUES (5, '千克');
INSERT INTO `软工2202_09_05_29计量单位` VALUES (4, '只');
INSERT INTO `软工2202_09_05_29计量单位` VALUES (3, '台');
INSERT INTO `软工2202_09_05_29计量单位` VALUES (1, '箱');

-- ----------------------------
-- Table structure for 软工2202_09_05_29进出仓表
-- ----------------------------
DROP TABLE IF EXISTS `软工2202_09_05_29进出仓表`;
CREATE TABLE `软工2202_09_05_29进出仓表`  (
  `单号` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `类型` enum('进仓','出仓') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `物料代码` int NOT NULL,
  `经办人` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `日期` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `数量` int NOT NULL,
  `备注` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `管理员` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `审核状态` enum('审核中','通过','未通过') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '审核中',
  PRIMARY KEY (`单号`, `物料代码`) USING BTREE,
  INDEX `物料代码`(`物料代码`) USING BTREE,
  INDEX `经办人`(`经办人`) USING BTREE,
  INDEX `管理员`(`管理员`) USING BTREE,
  CONSTRAINT `软工2202_09_05_29进出仓表_ibfk_1` FOREIGN KEY (`物料代码`) REFERENCES `软工2202_09_05_29物料表` (`物料代码`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `软工2202_09_05_29进出仓表_ibfk_2` FOREIGN KEY (`经办人`) REFERENCES `软工2202_09_05_29人员表` (`人员代码`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `软工2202_09_05_29进出仓表_ibfk_3` FOREIGN KEY (`管理员`) REFERENCES `软工2202_09_05_29人员表` (`人员代码`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of 软工2202_09_05_29进出仓表
-- ----------------------------
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911241', '进仓', 2, '1', '2024-12-04 11:41:50', 100, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911242', '进仓', 1, '1', '2024-12-04 11:41:52', 100, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911243', '进仓', 3, '1', '2024-12-04 11:41:52', 0, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911251', '进仓', 1, '1', '2024-12-04 11:41:53', 100, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911252', '进仓', 1, '1', '2024-12-04 11:41:54', 100, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911253', '进仓', 1, '1', '2024-12-04 11:41:54', 500, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201911253', '进仓', 2, '1', '2024-12-04 11:41:55', 100, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912061', '进仓', 1, '1', '2024-12-04 11:41:55', 1223, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912062', '进仓', 3, '2', '2024-12-04 11:41:56', 2666, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912063', '进仓', 5, '2', '2024-12-04 11:41:56', 1000, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912064', '进仓', 5, '2', '2024-12-04 11:41:57', 1000, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('1201912065', '进仓', 5, '1', '2024-12-04 11:41:57', 1000, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('120240919001', '进仓', 3, '1', '2024-12-04 11:41:58', 120, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('120240919002', '进仓', 4, '1', '2024-12-04 11:41:59', 120, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('220240919001', '出仓', 3, '1', '2024-12-04 11:41:59', 222, NULL, '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('220241204001', '出仓', 2, '1', '2024-12-04 11:42:00', 12, '无', '1', '审核中');
INSERT INTO `软工2202_09_05_29进出仓表` VALUES ('220241204002', '出仓', 2, '1', '2024-12-04 11:42:03', 1000, '1', '1', '审核中');

-- ----------------------------
-- Procedure structure for 添加单号
-- ----------------------------
DROP PROCEDURE IF EXISTS `添加单号`;
delimiter ;;
CREATE PROCEDURE `添加单号`(IN 数量s INT,
  IN 类型s INT,
	IN 日期s VARCHAR(20))
BEGIN
  INSERT INTO 软工1702_21_22_进出单号表 VALUES(数量s,类型s,日期s);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for 统计查询
-- ----------------------------
DROP PROCEDURE IF EXISTS `统计查询`;
delimiter ;;
CREATE PROCEDURE `统计查询`(IN 开始时间 VARCHAR(20),
	IN 结束时间 VARCHAR(20))
BEGIN
  select `物料名称`, SUM(`数量`) as 总和 
  from 软工1702_21_22_进出仓表
  RIGHT JOIN 软工1702_21_22_物料表
  ON `软工1702_21_22_进出仓表`.`物料代码` = `软工1702_21_22_物料表`.`物料代码` 
  AND `日期` >= 开始时间 AND `日期` <= 结束时间
  GROUP BY(`物料名称`);
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for 账单查询
-- ----------------------------
DROP PROCEDURE IF EXISTS `账单查询`;
delimiter ;;
CREATE PROCEDURE `账单查询`(IN 日期s VARCHAR(20),IN 物料代码s varchar(20))
BEGIN
	select `软工1702_21_22_物料表`.`物料代码`,物料名称,规格型号,计量单位,类型,数量 
  from `软工1702_21_22_物料表`,`软工1702_21_22_进出仓表`
  where `软工1702_21_22_物料表`.`物料代码` = 物料代码s
  AND`软工1702_21_22_物料表`.`物料代码` = `软工1702_21_22_进出仓表`.`物料代码`
  AND `日期` > 日期s;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for 进出仓开单
-- ----------------------------
DROP PROCEDURE IF EXISTS `进出仓开单`;
delimiter ;;
CREATE PROCEDURE `进出仓开单`(IN 单号s VARCHAR(20),
  IN 次数s INT,
	IN 类型s INT,
  IN 物料代码s VARCHAR(20),
  IN 操作人员代码s VARCHAR(20),
  IN 日期s DATE,
  IN 开单数量s INT, 
	IN 备注s VARCHAR(255))
BEGIN
  IF 类型s = 1 THEN
    INSERT
    INTO `软工1702_21_22_进出仓表`
    VALUES(单号s,次数s,类型s,物料代码s,操作人员代码s,日期s,开单数量s,备注s);
  ELSEIF 类型s = 2 THEN
    INSERT
    INTO `软工1702_21_22_进出仓表`
    VALUES(单号s,次数s,类型s,物料代码s,操作人员代码s,日期s,开单数量s,备注s);
  END IF;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table 软工2202_09_05_29人员表
-- ----------------------------
DROP TRIGGER IF EXISTS `生成人员代码`;
delimiter ;;
CREATE TRIGGER `生成人员代码` BEFORE INSERT ON `软工2202_09_05_29人员表` FOR EACH ROW BEGIN
  DECLARE last_seq INT DEFAULT 0;
  
  -- 获取当前最大人员代码，如果没有则默认为0
  SELECT COALESCE(MAX(CAST(SUBSTRING(`人员代码`, 7) AS UNSIGNED)), 0) INTO last_seq
  FROM `软工2202_09_05_29人员表`;
  
  -- 更新序号
  SET last_seq = last_seq + 1;
  
  -- 生成人员代码，前缀为'P'，接下来是当前日期，最后是自增序号
  SET NEW.`人员代码` = CONCAT(DATE_FORMAT(NOW(), '%y%m%d'), LPAD(last_seq, 4, '0'));
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table 软工2202_09_05_29进出仓表
-- ----------------------------
DROP TRIGGER IF EXISTS `生成单号`;
delimiter ;;
CREATE TRIGGER `生成单号` BEFORE INSERT ON `软工2202_09_05_29进出仓表` FOR EACH ROW BEGIN
  DECLARE new_count INT;
  -- 根据类型确定是进仓还是出仓
  IF NEW.类型 = '进仓' THEN
    -- 获取当天的进仓计数
    SELECT `进仓计数` INTO new_count FROM `软工2202_09_05_29单号计数` WHERE `日期` = DATE(NEW.日期);
    IF new_count IS NULL THEN
      -- 如果当天没有记录，则初始化计数
      INSERT INTO `软工2202_09_05_29单号计数` (`日期`, `进仓计数`) VALUES (DATE(NEW.日期), 0);
      SET new_count = 1;
    ELSE
      -- 否则，计数加1
      SET new_count = new_count + 1;
    END IF;
    -- 更新辅助表中的计数
    UPDATE `软工2202_09_05_29单号计数` SET `进仓计数` = new_count WHERE `日期` = DATE(NEW.日期);
  ELSEIF NEW.类型 = '出仓' THEN
    -- 获取当天的出仓计数
    SELECT `出仓计数` INTO new_count FROM `软工2202_09_05_29单号计数` WHERE `日期` = DATE(NEW.日期);
    IF new_count IS NULL THEN
      -- 如果当天没有记录，则初始化计数
      INSERT INTO `软工2202_09_05_29单号计数` (`日期`, `出仓计数`) VALUES (DATE(NEW.日期), 0);
      SET new_count = 1;
    ELSE
      -- 否则，计数加1
      SET new_count = new_count + 1;
    END IF;
    -- 更新辅助表中的计数
    UPDATE `软工2202_09_05_29单号计数` SET `出仓计数` = new_count WHERE `日期` = DATE(NEW.日期);
  END IF;
  
  -- 构造单号
  SET NEW.单号 = CONCAT(
    IF(NEW.类型 = '进仓', '1', '2'),
    DATE_FORMAT(NEW.日期, '%Y%m%d'),
    LPAD(new_count, 3, '0')
  );
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
