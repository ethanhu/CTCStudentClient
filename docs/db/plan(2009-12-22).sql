/*
MySQL Data Transfer
Source Host: localhost
Source Database: simulatordb
Target Host: localhost
Target Database: simulatordb
Date: 2009-12-22 13:36:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `Train_name` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '车次名称',
  `District_name` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '区段名称',
  `Prestation_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '前站站名',
  `Station_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '本站站名',
  `Plan_arrivestationtime` time default NULL COMMENT '到站时间',
  `Plan_leavestationtime` time default NULL COMMENT '离站时间',
  PRIMARY KEY  (`Train_name`,`Station_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='列车原始计划表';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `plan` VALUES ('1433', '北京-呼和浩特', '北京西', '北京西', '00:20:00', '00:20:00');
INSERT INTO `plan` VALUES ('1433', '北京-呼和浩特', '北京西', '呼和浩特', '01:55:00', '01:55:00');
INSERT INTO `plan` VALUES ('K125', '北京-呼和浩特', '北京西', '北京西', '01:00:00', '01:00:00');
INSERT INTO `plan` VALUES ('K125', '北京-呼和浩特', '大同', '呼和浩特', '02:45:00', '02:45:00');
INSERT INTO `plan` VALUES ('K125', '北京-呼和浩特', '北京西', '大同', '01:20:00', '01:40:00');
INSERT INTO `plan` VALUES ('K29', '呼和浩特-大同', '呼和浩特', '呼和浩特', '00:35:00', '00:35:00');
INSERT INTO `plan` VALUES ('K29', '呼和浩特-大同', '集宁', '大同', '01:55:00', '01:55:00');
INSERT INTO `plan` VALUES ('K29', '呼和浩特-大同', '呼和浩特', '集宁', '01:20:00', '01:35:00');
INSERT INTO `plan` VALUES ('K67', '呼和浩特-大同', '呼和浩特', '呼和浩特', '00:45:00', '00:45:00');
INSERT INTO `plan` VALUES ('K67', '呼和浩特-大同', '集宁', '大同', '02:25:00', '02:25:00');
INSERT INTO `plan` VALUES ('K67', '呼和浩特-大同', '呼和浩特', '集宁', '01:50:00', '01:50:00');
INSERT INTO `plan` VALUES ('T11', '呼和浩特-大同', '呼和浩特', '呼和浩特', '00:20:00', '00:20:00');
INSERT INTO `plan` VALUES ('T11', '呼和浩特-大同', '集宁', '大同', '01:35:00', '01:35:00');
INSERT INTO `plan` VALUES ('T11', '呼和浩特-大同', '呼和浩特', '集宁', '00:50:00', '01:00:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '大同', '乌兰察布', '02:55:00', '03:05:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '北京西', '北京西', '01:15:00', '01:15:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '乌兰察布', '呼和浩特', '03:30:00', '03:30:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '张家口', '大同', '02:36:00', '02:46:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '北京西', '张家口', '01:50:00', '02:00:00');
