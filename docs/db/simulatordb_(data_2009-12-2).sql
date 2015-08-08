/*
MySQL Data Transfer
Source Host: localhost
Source Database: simulatordb
Target Host: localhost
Target Database: simulatordb
Date: 2009-12-3 20:41:52
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for dispatch
-- ----------------------------
DROP TABLE IF EXISTS `dispatch`;
CREATE TABLE `dispatch` (
  `Train_name` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '车次名称',
  `District_name` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '区段名称',
  `Prestation_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '前站站名',
  `Station_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '本站站名',
  `Dispatch_arrivestationtime` time default NULL COMMENT '到站时间',
  `Dispatch_leavestationtime` time default NULL COMMENT '离站时间',
  `Operator_name` varchar(20) collate utf8_unicode_ci default NULL COMMENT '操作者姓名',
  PRIMARY KEY  (`Train_name`,`Station_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='列车调度计划表';

-- ----------------------------
-- Table structure for district
-- ----------------------------
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `District_name` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '区段名称',
  `District_stationnumber` int(4) default '2' COMMENT '区段站数量',
  `District_startstationname` varchar(50) collate utf8_unicode_ci NOT NULL default '' COMMENT '区段开始站名称',
  `District_endstationname` varchar(50) collate utf8_unicode_ci NOT NULL default '' COMMENT '区段结束站名称',
  `District_railwaybureau` varchar(50) collate utf8_unicode_ci default '' COMMENT '区段所属铁路局',
  PRIMARY KEY  (`District_name`),
  UNIQUE KEY `District_name` (`District_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='列车区段表';

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
-- Table structure for station
-- ----------------------------
DROP TABLE IF EXISTS `station`;
CREATE TABLE `station` (
  `Station_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '车站名称',
  `Station_downnumber` int(4) default '1' COMMENT '车站下行可用车道',
  `Station_upnumber` int(4) default '1' COMMENT '车站上行可用车道',
  `Station_graph` varchar(100) collate utf8_unicode_ci default 'table' COMMENT '站场图 table表示站场图保存在表中，否则保存在文件中',
  PRIMARY KEY  (`Station_name`),
  UNIQUE KEY `Station_name` (`Station_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车站表';

-- ----------------------------
-- Table structure for stationdistrictrelation
-- ----------------------------
DROP TABLE IF EXISTS `stationdistrictrelation`;
CREATE TABLE `stationdistrictrelation` (
  `Station_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '车站名称',
  `Prestation_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '前站站名',
  `District_name` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '区段名称',
  `Predistance` int(11) NOT NULL default '0' COMMENT '本站距上一站的距离（公里）',
  PRIMARY KEY  (`Station_name`,`District_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车站与区段关系表';

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `Student_id` int(10) NOT NULL auto_increment COMMENT '学员学号',
  `Student_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '用户账号',
  `Student_password` varchar(20) collate utf8_unicode_ci default '111111' COMMENT '密码',
  `Student_role` varchar(10) collate utf8_unicode_ci default '000000' COMMENT '角色',
  PRIMARY KEY  (`Student_id`),
  UNIQUE KEY `Student_name` (`Student_name`)
) ENGINE=InnoDB AUTO_INCREMENT=9120557 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='学员表';

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `Teacher_id` int(10) NOT NULL auto_increment COMMENT '教师编号ID',
  `Teacher_name` varchar(20) collate utf8_unicode_ci NOT NULL COMMENT '用户账号',
  `Teacher_password` varchar(20) collate utf8_unicode_ci default '111111' COMMENT '密码',
  `Teacher_role` varchar(10) collate utf8_unicode_ci default '001001' COMMENT '角色 001001表示教师 001005表示管理员',
  PRIMARY KEY  (`Teacher_id`),
  UNIQUE KEY `Teacher_name` (`Teacher_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='教师表';

-- ----------------------------
-- Table structure for temptrain
-- ----------------------------
DROP TABLE IF EXISTS `temptrain`;
CREATE TABLE `temptrain` (
  `Train_name` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '车次名称',
  `Train_direction` int(2) default '1' COMMENT '车次方向（上行0和下行1）',
  `Train_maxspeed` int(5) default '100' COMMENT '车最大速度',
  `Train_startstationname` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '始发站名称',
  `Train_endstationname` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '到达站名称',
  PRIMARY KEY  (`Train_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='临时列车表';

-- ----------------------------
-- Table structure for train
-- ----------------------------
DROP TABLE IF EXISTS `train`;
CREATE TABLE `train` (
  `Train_name` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '车次名称',
  `Train_direction` int(2) default '1' COMMENT '车次方向（上行0和下行1）',
  `Train_maxspeed` int(5) default '100' COMMENT '车最大速度',
  `Train_startstationname` varchar(50) collate utf8_unicode_ci default '' COMMENT '始发站名称',
  `Train_endstationname` varchar(50) collate utf8_unicode_ci default '' COMMENT '到达站名称',
  PRIMARY KEY  (`Train_name`),
  UNIQUE KEY `Train_name` (`Train_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='列车表';

-- ----------------------------
-- Table structure for traindistrictrelation
-- ----------------------------
DROP TABLE IF EXISTS `traindistrictrelation`;
CREATE TABLE `traindistrictrelation` (
  `Train_name` varchar(10) collate utf8_unicode_ci NOT NULL COMMENT '车次名称',
  `District_name` varchar(50) collate utf8_unicode_ci NOT NULL COMMENT '区段名称',
  PRIMARY KEY  (`Train_name`,`District_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='车次与区段间的关系 ';

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `district` VALUES ('北京-呼和浩特', '5', '北京西', '呼和浩特', '呼和浩特铁路局');
INSERT INTO `plan` VALUES ('1433', '北京-呼和浩特', '北京西', '北京西', '10:20:00', '10:20:00');
INSERT INTO `plan` VALUES ('1433', '北京-呼和浩特', '北京西', '呼和浩特', '15:55:00', '15:55:00');
INSERT INTO `plan` VALUES ('K125', '北京-呼和浩特', '北京西', '北京西', '13:00:00', '13:00:00');
INSERT INTO `plan` VALUES ('K125', '北京-呼和浩特', '大同', '呼和浩特', '16:45:00', '16:45:00');
INSERT INTO `plan` VALUES ('K125', '北京-呼和浩特', '北京西', '大同', '15:20:00', '15:30:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '大同', '乌兰察布', '15:45:00', '15:50:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '北京西', '北京西', '12:00:00', '12:00:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '乌兰察布', '呼和浩特', '16:30:00', '16:30:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '张家口', '大同', '14:36:00', '14:46:00');
INSERT INTO `plan` VALUES ('T281', '北京-呼和浩特', '北京西', '张家口', '12:50:00', '13:05:00');
INSERT INTO `station` VALUES ('乌兰察布', '2', '2', 'table');
INSERT INTO `station` VALUES ('北京西', '5', '4', 'table');
INSERT INTO `station` VALUES ('呼和浩特', '4', '3', 'table');
INSERT INTO `station` VALUES ('大同', '2', '3', 'table');
INSERT INTO `station` VALUES ('张家口', '2', '2', 'table');
INSERT INTO `stationdistrictrelation` VALUES ('乌兰察布', '大同', '北京-呼和浩特', '150');
INSERT INTO `stationdistrictrelation` VALUES ('北京西', '北京西', '北京-呼和浩特', '0');
INSERT INTO `stationdistrictrelation` VALUES ('呼和浩特', '乌兰察布', '北京-呼和浩特', '130');
INSERT INTO `stationdistrictrelation` VALUES ('大同', '张家口', '北京-呼和浩特', '180');
INSERT INTO `stationdistrictrelation` VALUES ('张家口', '北京西', '北京-呼和浩特', '200');
INSERT INTO `student` VALUES ('1', 's1', '1', '001002');
INSERT INTO `student` VALUES ('2', 's2', '1', '001002');
INSERT INTO `student` VALUES ('3', 's3', '1', '001002');
INSERT INTO `student` VALUES ('4', 's4', '1', '001002');
INSERT INTO `student` VALUES ('5', 's5', '1', '001002');
INSERT INTO `student` VALUES ('6', 's6', '1', '001002');
INSERT INTO `student` VALUES ('7', 's7', '1', '001002');
INSERT INTO `student` VALUES ('8', 's8', '1', '001002');
INSERT INTO `student` VALUES ('9', 's9', '1', '001002');
INSERT INTO `student` VALUES ('8120487', '高勋', '3', '001002');
INSERT INTO `student` VALUES ('9120548', '程舰', '2', '001002');
INSERT INTO `student` VALUES ('9120556', '胡恩召', '1', '001002');
INSERT INTO `teacher` VALUES ('1', 'user1', '1', '001001');
INSERT INTO `teacher` VALUES ('2', 'user2', '2', '001001');
INSERT INTO `teacher` VALUES ('3', 'user3', '3', '001001');
INSERT INTO `teacher` VALUES ('4', 'admin', 'admin', '001005');
INSERT INTO `train` VALUES ('1433', '1', '120', '北京西', '呼和浩特');
INSERT INTO `train` VALUES ('K125', '1', '150', '北京西', '呼和浩特');
INSERT INTO `train` VALUES ('T281', '1', '200', '北京西', '呼和浩特');
INSERT INTO `traindistrictrelation` VALUES ('1433', '北京-呼和浩特');
INSERT INTO `traindistrictrelation` VALUES ('K125', '北京-呼和浩特');
INSERT INTO `traindistrictrelation` VALUES ('T281', '北京-呼和浩特');
