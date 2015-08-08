/*
MySQL Data Transfer
Source Host: localhost
Source Database: simulatordb
Target Host: localhost
Target Database: simulatordb
Date: 2009-10-25 0:34:05
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `District_id` int(10) NOT NULL auto_increment COMMENT 'Çø¶ÎID',
  `District_name` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT 'Çø¶ÎÃû³Æ',
  `District_stationnumber` int(4) default '2' COMMENT 'Çø¶ÎÕ¾ÊýÁ¿',
  `District_startstationid` int(10) default '0' COMMENT 'Çø¶Î¿ªÊ¼Õ¾ID',
  `District_endstationid` int(10) default '0' COMMENT 'Çø¶Î½áÊøÕ¾ID',
  `District_railwaybureau` varchar(50) collate utf8_unicode_ci default '' COMMENT 'Çø¶ÎËùÊôÌúÂ·¾Ö',
  PRIMARY KEY  (`District_id`),
  UNIQUE KEY `District_name` (`District_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='ÁÐ³µÇø¶Î±í';

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `Train_id` int(10) NOT NULL default '0' COMMENT 'ÁÐ³µID',
  `Prestation_id` int(10) default '0' COMMENT 'Ç°Õ¾ID',
  `Station_id` int(10) NOT NULL default '0' COMMENT '±¾Õ¾ID',
  `Plan_arrivestationtime` time default NULL COMMENT 'µ½Õ¾Ê±¼ä',
  `Plan_leavestationtime` time default NULL COMMENT 'ÀëÕ¾Ê±¼ä',
  `Plan_predistance` int(11) default '0' COMMENT 'ÉÏÒ»Õ¾¾à±¾Õ¾¾àÀë£¨¹«Àï£©',
  PRIMARY KEY  (`Train_id`,`Station_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='ÁÐ³µÔ­Ê¼¼Æ»®±í';

-- ----------------------------
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `Station_id` int(10) NOT NULL auto_increment COMMENT '³µÕ¾ID',
  `Station_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '³µÕ¾Ãû³Æ',
  `Station_downnumber` int(4) default '1' COMMENT '³µÕ¾ÏÂÐÐ¿ÉÓÃ³µµÀ',
  `Station_upnumber` int(4) default '1' COMMENT '³µÕ¾ÉÏÐÐ¿ÉÓÃ³µµÀ',
  `Station_graph` varchar(100) collate utf8_unicode_ci default 'table' COMMENT 'Õ¾³¡Í¼ table±íÊ¾Õ¾³¡Í¼±£´æÔÚ±íÖÐ£¬·ñÔò±£´æÔÚÎÄ¼þÖÐ',
  PRIMARY KEY  (`Station_id`),
  UNIQUE KEY `Station_name` (`Station_name`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='³µÕ¾±í';

-- ----------------------------
-- Table structure for stationdistrictrelation
-- ----------------------------
DROP TABLE IF EXISTS `stationdistrictrelation`;
CREATE TABLE `stationdistrictrelation` (
  `Station_id` int(10) NOT NULL default '0' COMMENT '³µÕ¾ID',
  `District_id` int(10) NOT NULL default '0' COMMENT 'Çø¶ÎID',
  PRIMARY KEY  (`Station_id`,`District_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='³µÕ¾ÓëÇø¶Î¹ØÏµ±í';

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Student_id` int(10) NOT NULL auto_increment COMMENT 'Ñ§Ô±Ñ§ºÅID',
  `Student_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT 'ÓÃ»§ÕËºÅ',
  `Student_password` varchar(20) collate utf8_unicode_ci default '111111' COMMENT 'ÃÜÂë',
  `Student_role` varchar(10) collate utf8_unicode_ci default '000000' COMMENT '½ÇÉ«',
  PRIMARY KEY  (`Student_id`),
  UNIQUE KEY `Student_name` (`Student_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='Ñ§Ô±±í';

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `Teacher_id` int(10) NOT NULL auto_increment COMMENT '½ÌÊ¦±àºÅID',
  `Teacher_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT 'ÓÃ»§ÕËºÅ',
  `Teacher_password` varchar(20) collate utf8_unicode_ci default '111111' COMMENT 'ÃÜÂë',
  `Teacher_role` varchar(10) collate utf8_unicode_ci default '001001' COMMENT '½ÇÉ« 001001±íÊ¾½ÌÊ¦ 001005±íÊ¾¹ÜÀíÔ±',
  PRIMARY KEY  (`Teacher_id`),
  UNIQUE KEY `Teacher_name` (`Teacher_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='½ÌÊ¦±í';

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `Train_id` int(10) NOT NULL auto_increment COMMENT 'ÁÐ³µID',
  `Train_name` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '³µ´ÎÃû³Æ',
  `Train_direction` int(2) default '1' COMMENT '³µ´Î·½Ïò£¨ÉÏÐÐ0ºÍÏÂÐÐ1£©',
  `Train_maxspeed` int(5) default '100' COMMENT '³µ×î´óËÙ¶È',
  `Train_startstationid` int(10) default '0' COMMENT 'Ê¼·¢Õ¾ID',
  `Train_endstationid` int(10) default '0' COMMENT 'µ½´ïÕ¾ID',
  PRIMARY KEY  (`Train_id`),
  UNIQUE KEY `Train_name` (`Train_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='ÁÐ³µ±í';

-- ----------------------------
-- Table structure for traindistrictrelation
-- ----------------------------
DROP TABLE IF EXISTS `traindistrictrelation`;
CREATE TABLE `traindistrictrelation` (
  `Train_id` int(10) NOT NULL default '0' COMMENT '³µ´Îid',
  `District_id` int(10) NOT NULL default '0' COMMENT 'Çø¶Î id',
  PRIMARY KEY  (`Train_id`,`District_id`),
  UNIQUE KEY `Train_id` (`Train_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='³µ´ÎÓëÇø¶Î¼äµÄ¹ØÏµ ';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `district` VALUES ('1', '北京-郑州', '2', '1', '18', '北京铁路局');
INSERT INTO `district` VALUES ('2', '北京-秦皇岛', '9', '1', '20', '河北铁路局');
INSERT INTO `plan` VALUES ('1', '0', '1', '11:20:00', '11:20:00', '0');
INSERT INTO `plan` VALUES ('1', '1', '2', '12:11:00', '12:11:00', '60');
INSERT INTO `plan` VALUES ('1', '2', '3', '12:30:00', '12:32:00', '20');
INSERT INTO `plan` VALUES ('1', '3', '4', '13:20:00', '13:22:00', '60');
INSERT INTO `plan` VALUES ('1', '4', '5', '14:05:00', '14:18:00', '40');
INSERT INTO `plan` VALUES ('1', '5', '6', '14:37:00', '14:52:00', '20');
INSERT INTO `plan` VALUES ('1', '6', '7', '16:11:00', '16:41:00', '80');
INSERT INTO `plan` VALUES ('1', '7', '8', '16:51:00', '16:51:00', '50');
INSERT INTO `plan` VALUES ('1', '8', '9', '17:49:00', '17:59:00', '60');
INSERT INTO `plan` VALUES ('1', '9', '10', '18:09:00', '18:09:00', '20');
INSERT INTO `plan` VALUES ('1', '10', '11', '18:41:00', '18:43:00', '30');
INSERT INTO `plan` VALUES ('1', '11', '12', '19:38:00', '19:40:00', '60');
INSERT INTO `plan` VALUES ('1', '12', '13', '19:56:00', '19:56:00', '20');
INSERT INTO `plan` VALUES ('1', '13', '14', '20:14:00', '20:14:00', '20');
INSERT INTO `plan` VALUES ('1', '14', '15', '20:32:00', '20:32:00', '20');
INSERT INTO `plan` VALUES ('1', '15', '16', '20:50:00', '20:50:00', '20');
INSERT INTO `plan` VALUES ('1', '16', '17', '21:10:00', '21:12:00', '20');
INSERT INTO `plan` VALUES ('1', '17', '18', '22:40:00', '23:10:00', '80');
INSERT INTO `plan` VALUES ('1', '18', '28', '23:53:00', '23:57:00', '80');
INSERT INTO `plan` VALUES ('3', '0', '1', '10:38:00', '10:38:00', '0');
INSERT INTO `plan` VALUES ('3', '1', '2', '11:31:00', '11:33:00', '60');
INSERT INTO `plan` VALUES ('3', '2', '3', '11:50:00', '11:52:00', '20');
INSERT INTO `plan` VALUES ('3', '3', '4', '12:51:00', '13:01:00', '60');
INSERT INTO `plan` VALUES ('3', '4', '5', '13:25:00', '13:27:00', '40');
INSERT INTO `plan` VALUES ('3', '5', '6', '13:43:00', '13:45:00', '20');
INSERT INTO `plan` VALUES ('3', '6', '7', '15:17:00', '15:47:00', '80');
INSERT INTO `plan` VALUES ('3', '7', '8', '16:09:00', '16:11:00', '50');
INSERT INTO `plan` VALUES ('3', '8', '9', '17:11:00', '17:13:00', '60');
INSERT INTO `plan` VALUES ('3', '9', '10', '17:30:00', '17:32:00', '20');
INSERT INTO `plan` VALUES ('3', '10', '11', '18:05:00', '18:15:00', '30');
INSERT INTO `plan` VALUES ('3', '11', '12', '19:06:00', '19:16:00', '60');
INSERT INTO `plan` VALUES ('3', '12', '13', '19:26:00', '19:28:00', '20');
INSERT INTO `plan` VALUES ('3', '13', '14', '19:45:00', '19:47:00', '20');
INSERT INTO `plan` VALUES ('3', '14', '15', '20:04:00', '20:06:00', '20');
INSERT INTO `plan` VALUES ('3', '15', '16', '20:23:00', '20:25:00', '20');
INSERT INTO `plan` VALUES ('3', '16', '17', '20:42:00', '20:44:00', '20');
INSERT INTO `plan` VALUES ('3', '17', '18', '22:21:00', '22:51:00', '80');
INSERT INTO `plan` VALUES ('8', '0', '1', '07:50:00', '08:20:00', '30');
INSERT INTO `plan` VALUES ('8', '1', '2', '09:00:00', '09:00:00', '60');
INSERT INTO `plan` VALUES ('8', '2', '3', '09:09:00', '09:09:00', '20');
INSERT INTO `plan` VALUES ('8', '3', '4', '09:36:00', '09:36:00', '60');
INSERT INTO `plan` VALUES ('8', '4', '5', '09:54:00', '09:54:00', '40');
INSERT INTO `plan` VALUES ('8', '5', '6', '10:03:00', '10:03:00', '20');
INSERT INTO `plan` VALUES ('8', '6', '7', '10:44:00', '10:49:00', '80');
INSERT INTO `plan` VALUES ('8', '7', '8', '11:05:00', '11:05:00', '50');
INSERT INTO `plan` VALUES ('8', '8', '9', '11:30:00', '11:30:00', '60');
INSERT INTO `plan` VALUES ('8', '9', '10', '11:38:00', '11:38:00', '20');
INSERT INTO `plan` VALUES ('8', '10', '11', '11:50:00', '11:50:00', '30');
INSERT INTO `plan` VALUES ('8', '11', '12', '12:15:00', '12:15:00', '60');
INSERT INTO `plan` VALUES ('8', '12', '13', '12:23:00', '12:23:00', '20');
INSERT INTO `plan` VALUES ('8', '13', '14', '12:31:00', '12:31:00', '20');
INSERT INTO `plan` VALUES ('8', '14', '15', '12:39:00', '12:39:00', '20');
INSERT INTO `plan` VALUES ('8', '15', '16', '12:47:00', '12:47:00', '20');
INSERT INTO `plan` VALUES ('8', '16', '17', '12:59:00', '12:59:00', '30');
INSERT INTO `plan` VALUES ('8', '17', '18', '13:33:00', '13:38:00', '70');
INSERT INTO `plan` VALUES ('10', '0', '1', '09:30:00', '09:30:00', '0');
INSERT INTO `plan` VALUES ('10', '1', '7', '11:32:00', '11:42:00', '280');
INSERT INTO `plan` VALUES ('10', '7', '18', '14:22:00', '14:32:00', '400');
INSERT INTO `station` VALUES ('1', '北京', '6', '7', '');
INSERT INTO `station` VALUES ('2', '涿州', '2', '2', '');
INSERT INTO `station` VALUES ('3', '高碑店', '2', '2', '');
INSERT INTO `station` VALUES ('4', '保定', '3', '3', '');
INSERT INTO `station` VALUES ('5', '望都', '2', '2', '');
INSERT INTO `station` VALUES ('6', '定州', '2', '2', '');
INSERT INTO `station` VALUES ('7', '石家庄', '5', '5', '');
INSERT INTO `station` VALUES ('8', '高邑', '2', '2', '');
INSERT INTO `station` VALUES ('9', '邢台', '2', '2', '');
INSERT INTO `station` VALUES ('10', '沙河市', '2', '2', '');
INSERT INTO `station` VALUES ('11', '邯郸', '2', '2', '');
INSERT INTO `station` VALUES ('12', '安阳', '3', '3', '');
INSERT INTO `station` VALUES ('13', '汤阴', '3', '3', '');
INSERT INTO `station` VALUES ('14', '鹤壁', '3', '3', '');
INSERT INTO `station` VALUES ('15', '淇县', '3', '3', '');
INSERT INTO `station` VALUES ('16', '卫辉', '3', '3', '');
INSERT INTO `station` VALUES ('17', '新乡', '3', '3', '');
INSERT INTO `station` VALUES ('18', '郑州', '8', '8', '');
INSERT INTO `station` VALUES ('20', '秦皇岛', '3', '3', '');
INSERT INTO `station` VALUES ('21', '北戴河', '3', '3', '');
INSERT INTO `station` VALUES ('22', '昌黎', '3', '3', '');
INSERT INTO `station` VALUES ('23', '滦县', '3', '3', '');
INSERT INTO `station` VALUES ('24', '唐山', '3', '3', '');
INSERT INTO `station` VALUES ('25', '塘沽', '3', '3', '');
INSERT INTO `station` VALUES ('26', '天津', '3', '3', '');
INSERT INTO `station` VALUES ('27', '廊坊', '3', '3', '');
INSERT INTO `station` VALUES ('28', '许昌', '3', '3', '');
INSERT INTO `station` VALUES ('29', '漯河', '3', '3', '');
INSERT INTO `stationdistrictrelation` VALUES ('1', '1');
INSERT INTO `stationdistrictrelation` VALUES ('2', '1');
INSERT INTO `stationdistrictrelation` VALUES ('3', '1');
INSERT INTO `stationdistrictrelation` VALUES ('4', '1');
INSERT INTO `stationdistrictrelation` VALUES ('5', '1');
INSERT INTO `stationdistrictrelation` VALUES ('6', '1');
INSERT INTO `stationdistrictrelation` VALUES ('7', '1');
INSERT INTO `stationdistrictrelation` VALUES ('8', '1');
INSERT INTO `stationdistrictrelation` VALUES ('9', '1');
INSERT INTO `stationdistrictrelation` VALUES ('10', '1');
INSERT INTO `stationdistrictrelation` VALUES ('11', '1');
INSERT INTO `stationdistrictrelation` VALUES ('12', '1');
INSERT INTO `stationdistrictrelation` VALUES ('13', '1');
INSERT INTO `stationdistrictrelation` VALUES ('14', '1');
INSERT INTO `stationdistrictrelation` VALUES ('15', '1');
INSERT INTO `stationdistrictrelation` VALUES ('16', '1');
INSERT INTO `stationdistrictrelation` VALUES ('17', '1');
INSERT INTO `stationdistrictrelation` VALUES ('18', '1');
INSERT INTO `stationdistrictrelation` VALUES ('20', '2');
INSERT INTO `stationdistrictrelation` VALUES ('21', '2');
INSERT INTO `stationdistrictrelation` VALUES ('22', '2');
INSERT INTO `stationdistrictrelation` VALUES ('23', '2');
INSERT INTO `stationdistrictrelation` VALUES ('24', '2');
INSERT INTO `stationdistrictrelation` VALUES ('25', '2');
INSERT INTO `stationdistrictrelation` VALUES ('26', '2');
INSERT INTO `stationdistrictrelation` VALUES ('27', '2');
INSERT INTO `stationdistrictrelation` VALUES ('28', '3');
INSERT INTO `stationdistrictrelation` VALUES ('29', '3');
INSERT INTO `student` VALUES ('1', 'user1', '1', '000001');
INSERT INTO `student` VALUES ('2', 'user2', '1', '000001');
INSERT INTO `student` VALUES ('3', 'user3', '1', '000002');
INSERT INTO `teacher` VALUES ('1', 'user1', '1', '001001');
INSERT INTO `teacher` VALUES ('2', 'user2', '2', '001001');
INSERT INTO `teacher` VALUES ('3', 'user3', '3', '001001');
INSERT INTO `teacher` VALUES ('4', 'admin', 'admin', '001005');
INSERT INTO `train` VALUES ('1', 'K117', '1', '110', '1', '28');
INSERT INTO `train` VALUES ('3', 'K749', '1', '110', '1', '18');
INSERT INTO `train` VALUES ('8', 'T12', '2', '160', '27', '18');
INSERT INTO `traindistrictrelation` VALUES ('1', '1');
INSERT INTO `traindistrictrelation` VALUES ('3', '1');
INSERT INTO `traindistrictrelation` VALUES ('8', '1');
INSERT INTO `traindistrictrelation` VALUES ('10', '1');
