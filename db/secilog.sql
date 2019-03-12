/*
 Navicat Premium Data Transfer

 Source Server         : 123.206.18.117_3309
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : 123.206.18.117:3309
 Source Schema         : secilog

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 12/03/2019 17:33:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for alert
-- ----------------------------
DROP TABLE IF EXISTS `alert`;
CREATE TABLE `alert` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alert_code` varchar(64) DEFAULT NULL,
  `alert_rule_id` bigint(20) DEFAULT NULL,
  `rule_name` varchar(255) DEFAULT NULL,
  `alert_date` datetime DEFAULT NULL,
  `alert_last_date` datetime DEFAULT NULL,
  `display_title` varchar(255) DEFAULT NULL,
  `display_detail` varchar(4000) DEFAULT NULL,
  `base_custom_user_id` bigint(20) DEFAULT NULL,
  `base_custom_id` bigint(20) DEFAULT NULL,
  `custom_name` varchar(255) DEFAULT NULL,
  `base_custom_object_id` bigint(20) DEFAULT NULL,
  `object_address` varchar(255) DEFAULT NULL,
  `alert_type` varchar(255) DEFAULT NULL,
  `degree` int(11) DEFAULT '0',
  `alert_source` varchar(64) DEFAULT NULL,
  `event_count` int(11) DEFAULT '0',
  `process_flag` char(1) DEFAULT NULL,
  `process_type` varchar(64) DEFAULT NULL,
  `ticket_id` bigint(20) DEFAULT NULL,
  `ticket_no` varchar(64) DEFAULT '',
  `merge_value` varchar(64) DEFAULT NULL,
  `memo` varchar(2048) DEFAULT NULL,
  `order_status` int(11) DEFAULT NULL,
  `opera_date` datetime DEFAULT NULL,
  `completion_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for alert_event_log
-- ----------------------------
DROP TABLE IF EXISTS `alert_event_log`;
CREATE TABLE `alert_event_log` (
  `alert_id` bigint(20) NOT NULL,
  `event_id` bigint(20) NOT NULL,
  KEY `fk_reference_14` (`event_id`),
  KEY `fk_reference_15` (`alert_id`),
  CONSTRAINT `fk_reference_14` FOREIGN KEY (`event_id`) REFERENCES `event_log` (`id`),
  CONSTRAINT `fk_reference_15` FOREIGN KEY (`alert_id`) REFERENCES `alert` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for alert_rule
-- ----------------------------
DROP TABLE IF EXISTS `alert_rule`;
CREATE TABLE `alert_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_upd_by` bigint(20) DEFAULT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `rule_code` varchar(64) DEFAULT NULL,
  `rule_name` varchar(255) DEFAULT NULL,
  `rule_condition` varchar(500) DEFAULT NULL,
  `rule_detail` varchar(4000) DEFAULT NULL,
  `display_detail` varchar(4000) DEFAULT NULL,
  `is_inside` char(1) DEFAULT NULL,
  `active_flag` char(1) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of alert_rule
-- ----------------------------
BEGIN;
INSERT INTO `alert_rule` VALUES (1, 1, '2013-07-17 00:00:01', NULL, NULL, 'passwordGuess', '密码猜测', '((result = fail) AND (eventType = loginin))', '{\"alertDes\":\"目标IP ${targetIp} 在 ${eventDate} 时候产生了密码猜测攻击告警，攻击ip为${sourceIp}\",\"alertType\":\"业务告警\",\"id\":1001,\"level\":3,\"matchConfig\":{\"eventCount\":2,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"result\",\"operate\":\"EQUAL\",\"value\":\"fail\"},\"id\":1,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"loginin\"},\"id\":2,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":100,\"spanTime\":180000},\"mergeField\":\"sourceIp,targetIp,userName\",\"remark\":\"日志来源:syslog日志，判断依据:syslog中登录失败的次数大于一定的阈值\",\"ruleCode\":\"PasswordGuessRule\",\"ruleName\":\"密码猜测攻击\",\"ruleType\":\"MANY\",\"timing\":\"\"}', '目标IP ${targetIp} 在 ${eventDate} 时候产生了密码猜测攻击告警，攻击ip为${sourceIp}', 'Y', 'Y', '密码猜测攻击');
INSERT INTO `alert_rule` VALUES (2, 1, '2013-07-17 00:00:01', NULL, NULL, 'passwordGuessSuccess', '密码猜测成功', '((result = fail) AND (eventType = loginin)) followby ((result = success) AND (eventType = loginin))', '{\"alertDes\":\"目标IP ${targetIp} 在 ${eventDate} 时候产生了密码猜测，并成功登陆。告警，攻击ip为${sourceIp}\",\"alertType\":\"业务告警\",\"id\":1001,\"level\":4,\"matchConfig\":{\"eventCount\":2,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"result\",\"operate\":\"EQUAL\",\"value\":\"fail\"},\"id\":1,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"loginin\"},\"id\":2,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":100,\"spanTime\":180000},\"matchConfigTwo\":{\"eventCount\":5,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"result\",\"operate\":\"EQUAL\",\"value\":\"success\"},\"id\":1,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"loginin\"},\"id\":2,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":100,\"spanTime\":180000},\"mergeField\":\"sourceIp,targetIp,userName\",\"remark\":\"日志来源:syslog日志，判断依据:syslog中登录失败的次数大于一定的阈值\",\"ruleCode\":\"PasswordGuessSuccessRule\",\"ruleName\":\"密码猜测成功\",\"ruleType\":\"FOLLOWBY\",\"timing\":\"\"}\r\n\r\n', '目标IP ${targetIp} 在 ${eventDate} 时候产生了密码猜测，并成功登陆。告警，攻击ip为${sourceIp}', 'Y', 'Y', '密码猜测攻击成功');
INSERT INTO `alert_rule` VALUES (3, 1, '2013-07-17 00:00:01', NULL, NULL, 'AccountGuessRule', '账号猜测攻击', '((result = accountfail) AND (eventType = loginin))', '{\"alertDes\":\"目标IP ${targetIp} 在 ${eventDate} 时候产生了账号猜测攻击告警，攻击ip为${sourceIp}\",\"alertType\":\"业务告警\",\"id\":1011,\"level\":3,\"matchConfig\":{\"eventCount\":3,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"result\",\"operate\":\"EQUAL\",\"value\":\"accountfail\"},\"id\":1,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"loginin\"},\"id\":2,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":100,\"spanTime\":180000},\"mergeField\":\"sourceIp,targetIp,userName\",\"processRule\":{\"ruleConfig\":null},\"remark\":\"日志来源:syslog日志，判断依据:syslog中账号失败的次数大于一定的阈值\",\"ruleCode\":\"AccountGuessRule\",\"ruleName\":\"账号猜测攻击\",\"ruleType\":\"MANY\",\"timing\":\"\"}', '目标IP ${targetIp} 在 ${eventDate} 时候产生了账号猜测攻击告警，攻击ip为${sourceIp}', 'Y', 'Y', '日志来源:syslog日志，判断依据:syslog中账号失败的次数大于一定的阈值');
INSERT INTO `alert_rule` VALUES (4, 1, '2013-07-17 00:00:01', NULL, NULL, 'OutWorkTimeRule', '非上班时间登录', '((result = success) AND (eventType = loginin) AND (time in OUTWORKTIME))', '{\"alertDes\":\"目标IP ${targetIp} 在 ${eventDate} 时候产生了非上班时间登录告警，攻击ip为${sourceIp}\",\"alertType\":\"业务告警\",\"id\":1012,\"level\":2,\"matchConfig\":{\"eventCount\":0,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"result\",\"operate\":\"EQUAL\",\"value\":\"success\"},\"id\":1,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"loginin\"},\"id\":2,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"time\",\"operate\":\"IN\",\"value\":\"OUTWORKTIME\"},\"id\":3,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":0,\"spanTime\":0},\"mergeField\":\"sourceIp,targetIp,userName\",\"processRule\":{\"ruleConfig\":null},\"remark\":\"日志来源:syslog日志，判断依据:syslog中有非上班时间登录成功的记录\",\"ruleCode\":\"OutWorkTimeRule\",\"ruleName\":\"非上班时间登录\",\"ruleType\":\"SINGLE\",\"timing\":\"\"}', '目标IP ${targetIp} 在 ${eventDate} 时候产生了非上班时间登录告警，攻击ip为${sourceIp}\")', 'Y', 'Y', '日志来源:syslog日志，判断依据:syslog中有非上班时间登录成功的记录');
INSERT INTO `alert_rule` VALUES (5, 1, '2013-07-17 00:00:01', NULL, NULL, 'OutWorkSiteRule', '非上班地点登录', '((result = success) AND (eventType = loginin) AND (sourceIp in WORKSITE))', '{\"alertDes\":\"目标IP ${targetIp} 在 ${eventDate} 时候产生了非上班地点登录告警，攻击ip为${sourceIp}\",\"alertType\":\"业务告警\",\"id\":1012,\"level\":2,\"matchConfig\":{\"eventCount\":0,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"result\",\"operate\":\"EQUAL\",\"value\":\"success\"},\"id\":1,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"loginin\"},\"id\":2,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"sourceIp\",\"operate\":\"NOTIN\",\"value\":\"WORKSITE\"},\"id\":3,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":0,\"spanTime\":0},\"mergeField\":\"sourceIp,targetIp,userName\",\"processRule\":{\"ruleConfig\":null},\"remark\":\"日志来源:syslog日志，判断依据:syslog中有非上班地点登录成功的记录\",\"ruleCode\":\"OutWorkSiteRule\",\"ruleName\":\"非上班地点登录\",\"ruleType\":\"SINGLE\",\"timing\":\"\"}', '目标IP ${targetIp} 在 ${eventDate} 时候产生了非上班地点登录告警，攻击ip为${sourceIp}', 'Y', 'Y', '日志来源:syslog日志，判断依据:syslog中有非上班时间登录成功的记录');
INSERT INTO `alert_rule` VALUES (6, 1, '2013-07-17 00:00:01', NULL, NULL, 'sensitiveOperateRule', '敏感文件操作', '((eventType = command) AND (targetProcess in SENSIFILE))', '{\"alertDes\":\"目标IP ${targetIp} 在 ${eventDate} 时候产生了敏感文件操作行为告警，攻击ip为${sourceIp}\",\"alertType\":\"业务告警\",\"id\":1012,\"level\":2,\"matchConfig\":{\"eventCount\":0,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"command\"},\"id\":2,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"targetProcess\",\"operate\":\"IN\",\"value\":\"SENSIFILE\"},\"id\":3,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":0,\"spanTime\":0},\"mergeField\":\"sourceIp,sourcePort,targetIp,userName\",\"processRule\":{\"ruleConfig\":null},\"remark\":\"日志来源:syslog日志，判断依据:syslog中有操作了敏感文件的记录\",\"ruleCode\":\"sensitiveOperateRule\",\"ruleName\":\"敏感文件操作\",\"ruleType\":\"SINGLE\",\"timing\":\"\"}', '目标IP ${targetIp} 在 ${eventDate} 时候产生了敏感文件操作告警，攻击ip为${sourceIp}', 'Y', 'Y', '日志来源:syslog日志，判断依据:syslog中有中有操作了敏感文件的记录');
INSERT INTO `alert_rule` VALUES (7, 1, '2013-07-17 00:00:01', NULL, NULL, 'highriskOperateRule', '高危命令操作', '((eventType = command) AND (targetProcess in HIGHRISK))\r\n', '{\"alertDes\":\"目标IP ${targetIp} 在 ${eventDate} 时候产生了高危操作行为告警，攻击ip为${sourceIp}\",\"alertType\":\"业务告警\",\"id\":1012,\"level\":2,\"matchConfig\":{\"eventCount\":0,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"command\"},\"id\":2,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"targetProcess\",\"operate\":\"IN\",\"value\":\"HIGHRISK\"},\"id\":3,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":0,\"spanTime\":0},\"mergeField\":\"sourceIp,sourcePort,targetIp,userName\",\"processRule\":{\"ruleConfig\":null},\"remark\":\"日志来源:syslog日志，判断依据:syslog中有高危命令操作的记录\",\"ruleCode\":\"highriskOperateRule\",\"ruleName\":\"高危命令操作\",\"ruleType\":\"SINGLE\",\"timing\":\"\"}\r\n', '目标IP ${targetIp} 在 ${eventDate} 时候产生了高危命令操作告警，攻击ip为${sourceIp}', 'Y', 'Y', '日志来源:syslog日志，判断依据:syslog中有中有高危命令操作的记录');
INSERT INTO `alert_rule` VALUES (8, 1, '2013-07-17 00:00:01', NULL, NULL, 'hostScanRule', '主机扫描', '((eventType = firewall) AND (sourcePort not in LEGALPORT))\r\n', '{\"alertDes\":\"主机${sourceIp} 在 ${eventDate} 时候产生了主机扫描操作。\",\"alertType\":\"业务告警\",\"compareField\":\"targetIp\",\"id\":1012,\"level\":2,\"matchConfig\":{\"eventCount\":5,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"firewall\"},\"id\":2,\"operate\":\"AND\",\"parent\":null},{\"children\":[],\"data\":{\"field\":\"sourcePort\",\"operate\":\"NOTIN\",\"value\":\"LEGALPORT\"},\"id\":3,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":500,\"spanTime\":180000},\"mergeField\":\"sourceIp,targetPort\",\"processRule\":{\"ruleConfig\":null},\"remark\":\"日志来源:syslog日志，判断依据:syslog中有主机扫描操作\",\"ruleCode\":\"hostScanRule\",\"ruleName\":\"主机扫描\",\"ruleType\":\"ONETOMANY\",\"timing\":\"\"}\r\n', '主机${sourceIp} 在 ${eventDate} 时候产生了主机扫描操作告警。', 'Y', 'Y', '日志来源:syslog日志，判断依据:syslog中有中有主机扫描操作的记录');
INSERT INTO `alert_rule` VALUES (9, 1, '2013-07-17 00:00:01', NULL, NULL, 'portScanRule', '端口扫描', '((eventType = firewall))\r\n', '{\"alertDes\":\"主机${sourceIp} 在 ${eventDate} 时候产生了端口扫描操作\",\"alertType\":\"业务告警\",\"compareField\":\"targetPort\",\"id\":1012,\"level\":2,\"matchConfig\":{\"eventCount\":5,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"firewall\"},\"id\":2,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":500,\"spanTime\":180000},\"mergeField\":\"sourceIp,targetIp\",\"processRule\":{\"ruleConfig\":null},\"remark\":\"日志来源:syslog日志，判断依据:syslog中有端口扫描操作\",\"ruleCode\":\"portScanRule\",\"ruleName\":\"端口扫描\",\"ruleType\":\"ONETOMANY\",\"timing\":\"\"}\r\n', '主机${sourceIp} 在 ${eventDate} 时候产生了端口扫描操作告警。', 'Y', 'Y', '日志来源:syslog日志，判断依据:syslog中有中有端口扫描操作的记录');
INSERT INTO `alert_rule` VALUES (10, 1, '2013-07-17 00:00:01', NULL, NULL, 'illegalOutConnectRule', '非法外联', '((eventType = firewall) AND ((sourcePort not in LEGALPORT) AND (targetPort not in LEGALPORT)))\r\n', '{\"alertDes\":\"主机${sourceIp} 在 ${eventDate} 时候产生了非法外联操作，连接端口为${sourcePort}\",\"alertType\":\"业务告警\",\"id\":1012,\"level\":2,\"matchConfig\":{\"eventCount\":0,\"matchRoot\":{\"children\":[{\"children\":[],\"data\":{\"field\":\"eventType\",\"operate\":\"EQUAL\",\"value\":\"firewall\"},\"id\":2,\"operate\":\"AND\",\"parent\":null},{\"children\":[{\"children\":[],\"data\":{\"field\":\"sourcePort\",\"operate\":\"NOTIN\",\"value\":\"LEGALPORT\"},\"id\":3,\"operate\":\"AND\",\"parent\":null,},{\"children\":[],\"data\":{\"field\":\"targetPort\",\"operate\":\"NOTIN\",\"value\":\"LEGALPORT\"},\"id\":3,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":1,\"operate\":\"AND\",\"parent\":null}],\"data\":null,\"id\":0,\"operate\":\"AND\",\"parent\":null},\"operateType\":\"GREATER\",\"processCount\":0,\"spanTime\":0},\"mergeField\":\"sourceIp,protocol\",\"processRule\":{\"ruleConfig\":null},\"remark\":\"日志来源:syslog日志，判断依据:syslog中有非法外联操作\",\"ruleCode\":\"illegalOutConnectRule\",\"ruleName\":\"非法外联\",\"ruleType\":\"SINGLE\",\"timing\":\"\"}\r\n', '主机${sourceIp} 在 ${eventDate} 时候产生了非法外联操作，连接端口为${sourcePort}', 'Y', 'Y', '日志来源:syslog日志，判断依据:syslog中有中有非法外联的记录');
COMMIT;

-- ----------------------------
-- Table structure for alert_ticket
-- ----------------------------
DROP TABLE IF EXISTS `alert_ticket`;
CREATE TABLE `alert_ticket` (
  `alert_id` bigint(20) DEFAULT NULL,
  `ticket_id` bigint(20) DEFAULT NULL,
  KEY `fk_reference_17` (`alert_id`),
  KEY `fk_reference_18` (`ticket_id`),
  CONSTRAINT `fk_reference_17` FOREIGN KEY (`alert_id`) REFERENCES `alert` (`id`),
  CONSTRAINT `fk_reference_18` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for base_dic
-- ----------------------------
DROP TABLE IF EXISTS `base_dic`;
CREATE TABLE `base_dic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dic_code` varchar(255) NOT NULL,
  `dic_name` varchar(255) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_dic
-- ----------------------------
BEGIN;
INSERT INTO `base_dic` VALUES (1, 'OUTWORKTIME', 'outworktime', '非上班时间');
INSERT INTO `base_dic` VALUES (2, 'WORKSITE', 'worsite', '上班地点');
INSERT INTO `base_dic` VALUES (3, 'SENSIFILE', 'sensifile', '敏感文件');
INSERT INTO `base_dic` VALUES (4, 'HIGHRISK', 'highrisk', '高危命令');
INSERT INTO `base_dic` VALUES (5, 'LEGALPORT', 'legalport', '合法端口');
COMMIT;

-- ----------------------------
-- Table structure for base_dic_detail
-- ----------------------------
DROP TABLE IF EXISTS `base_dic_detail`;
CREATE TABLE `base_dic_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_upd_by` bigint(20) DEFAULT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `base_dic_id` bigint(20) DEFAULT NULL,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(4000) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  `is_internal` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reference_55` (`base_dic_id`),
  CONSTRAINT `fk_reference_55` FOREIGN KEY (`base_dic_id`) REFERENCES `base_dic` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of base_dic_detail
-- ----------------------------
BEGIN;
INSERT INTO `base_dic_detail` VALUES (1, 1, '2013-07-17 00:00:01', NULL, NULL, 1, '0-28800', '晚上', '0-86400', '0点到8点', 'Y');
INSERT INTO `base_dic_detail` VALUES (2, 1, '2013-07-17 00:00:01', NULL, NULL, 1, '72000-86400', '下午', '72000-86400', '20点到24点', 'Y');
INSERT INTO `base_dic_detail` VALUES (3, 1, '2013-07-17 00:00:01', NULL, NULL, 2, '127.0.0.1', 'home', '127.0.0.1', 'home', 'Y');
INSERT INTO `base_dic_detail` VALUES (4, 1, '2013-07-17 00:00:01', NULL, NULL, 3, 'passwd', 'passwd', 'passwd', '密码文件', 'Y');
INSERT INTO `base_dic_detail` VALUES (5, 1, '2013-07-17 00:00:01', NULL, NULL, 3, 'rsyslog.conf', 'rsyslog.conf', 'rsyslog.conf', 'syslog配置文件', 'Y');
INSERT INTO `base_dic_detail` VALUES (6, 1, '2013-07-17 00:00:01', NULL, NULL, 4, 'passwd', 'passwd', 'passwd', '修改密码', 'Y');
INSERT INTO `base_dic_detail` VALUES (7, 1, '2013-07-17 00:00:01', NULL, NULL, 4, 'rm -rf', 'rm', 'rm', '批量删除', 'Y');
INSERT INTO `base_dic_detail` VALUES (8, 1, '2013-07-17 00:00:01', NULL, NULL, 5, '22', 'ssh', 'ssh', 'ssh', 'Y');
INSERT INTO `base_dic_detail` VALUES (9, 1, '2013-07-17 00:00:01', NULL, NULL, 5, '514', 'syslog', 'syslog', 'syslog', 'Y');
COMMIT;

-- ----------------------------
-- Table structure for base_safe_asset
-- ----------------------------
DROP TABLE IF EXISTS `base_safe_asset`;
CREATE TABLE `base_safe_asset` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `last_upd_by` bigint(20) DEFAULT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `safe_asset_code` varchar(255) NOT NULL,
  `safe_asset_name` varchar(255) DEFAULT NULL,
  `factory` varchar(255) DEFAULT NULL,
  `isvm` char(1) DEFAULT NULL,
  `vmid` varchar(255) DEFAULT NULL,
  `base_safe_asset_type_id` bigint(20) DEFAULT NULL,
  `safe_asset_type` varchar(64) DEFAULT NULL,
  `sm_province_id` bigint(20) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `version` varchar(255) DEFAULT NULL,
  `database_version` varchar(255) DEFAULT NULL,
  `model` varchar(255) DEFAULT NULL,
  `userid` bigint(20) DEFAULT NULL,
  `user_name` varchar(64) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `ip2` varchar(64) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `loginuser` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `template` varchar(500) DEFAULT NULL,
  `active_flag` char(1) NOT NULL,
  `added_sg_id` varchar(64) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `safe_asset_code` (`safe_asset_code`),
  KEY `fk_reference_37` (`base_safe_asset_type_id`),
  CONSTRAINT `fk_reference_37` FOREIGN KEY (`base_safe_asset_type_id`) REFERENCES `base_safe_asset_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for event_log
-- ----------------------------
DROP TABLE IF EXISTS `event_log`;
CREATE TABLE `event_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `software_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(64) DEFAULT NULL,
  `collect_ip` varchar(64) DEFAULT NULL,
  `collect_name` varchar(255) DEFAULT NULL,
  `collect_type` varchar(64) DEFAULT NULL,
  `collect_date` datetime DEFAULT NULL,
  `collect_end_date` datetime DEFAULT NULL,
  `collect_hostname` varchar(255) DEFAULT NULL,
  `event_type` varchar(64) DEFAULT NULL,
  `event_detail_type` varchar(64) DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `event_desc` varchar(500) DEFAULT NULL,
  `event_count` int(11) DEFAULT '0',
  `event_date` datetime DEFAULT NULL,
  `end_event_date` datetime DEFAULT NULL,
  `level` int(1) DEFAULT NULL,
  `protocol` varchar(64) DEFAULT NULL,
  `source_ip` varchar(64) DEFAULT NULL,
  `source_port` int(11) DEFAULT '0',
  `source_hostname` varchar(255) DEFAULT NULL,
  `source_process` varchar(255) DEFAULT NULL,
  `source_user` varchar(255) DEFAULT NULL,
  `target_ip` varchar(255) DEFAULT NULL,
  `target_port` int(11) DEFAULT '0',
  `target_hostname` varchar(255) DEFAULT NULL,
  `target_process` varchar(255) DEFAULT NULL,
  `target_user` varchar(255) DEFAULT NULL,
  `host_ip` varchar(255) DEFAULT NULL,
  `host_name` varchar(255) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `http_url` varchar(500) DEFAULT NULL,
  `http_method` varchar(64) DEFAULT NULL,
  `http_session_id` varchar(255) DEFAULT NULL,
  `logid` varchar(255) DEFAULT NULL,
  `detail_table_id` varchar(128) DEFAULT NULL,
  `quartz_collect_log_id` bigint(20) DEFAULT NULL,
  `content` varchar(15000) DEFAULT NULL,
  `base_custom_user_id` bigint(20) DEFAULT NULL,
  `base_custom_id` bigint(20) DEFAULT NULL,
  `service_type` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_safe_asset_config
-- ----------------------------
DROP TABLE IF EXISTS `log_safe_asset_config`;
CREATE TABLE `log_safe_asset_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base_safe_asset_id` bigint(20) DEFAULT NULL,
  `protocol` varchar(64) DEFAULT NULL COMMENT 'ssh,sftp,ftp',
  `ip` varchar(64) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `login_user` varchar(64) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `log_path` varchar(255) DEFAULT NULL,
  `log_name` varchar(255) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reference_23` (`base_safe_asset_id`),
  CONSTRAINT `fk_reference_23` FOREIGN KEY (`base_safe_asset_id`) REFERENCES `base_safe_asset` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for log_safe_asset_service
-- ----------------------------
DROP TABLE IF EXISTS `log_safe_asset_service`;
CREATE TABLE `log_safe_asset_service` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `base_safe_asset_id` bigint(20) DEFAULT NULL,
  `service_code` varchar(64) DEFAULT NULL,
  `service_name` varchar(255) DEFAULT NULL,
  `port` varchar(500) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reference_22` (`base_safe_asset_id`),
  CONSTRAINT `fk_reference_22` FOREIGN KEY (`base_safe_asset_id`) REFERENCES `base_safe_asset` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sm_config
-- ----------------------------
DROP TABLE IF EXISTS `sm_config`;
CREATE TABLE `sm_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_upd_by` bigint(20) DEFAULT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `config_code` varchar(64) NOT NULL,
  `config_value` varchar(255) NOT NULL,
  `active_flag` char(1) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sm_log
-- ----------------------------
DROP TABLE IF EXISTS `sm_log`;
CREATE TABLE `sm_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `operate_by` bigint(20) NOT NULL,
  `operate_code` varchar(255) DEFAULT NULL,
  `operate_name` varchar(255) DEFAULT NULL,
  `operate_date` datetime NOT NULL,
  `operate_ip` varchar(64) DEFAULT NULL,
  `operate_type` varchar(64) NOT NULL,
  `object_code` varchar(255) NOT NULL,
  `object_name` varchar(255) NOT NULL,
  `object_id` bigint(20) DEFAULT NULL,
  `object_value` varchar(255) DEFAULT NULL,
  `result` varchar(64) DEFAULT NULL,
  `content` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sm_log
-- ----------------------------
BEGIN;
INSERT INTO `sm_log` VALUES (1, 1, 'admin', 'admin', '2019-03-12 17:18:29', '0:0:0:0:0:0:0:1', 'login', 'auth', '管理员登录', NULL, NULL, 'fail', 'null登录失败！');
INSERT INTO `sm_log` VALUES (2, 1, 'admin', 'admin', '2019-03-12 17:18:47', '0:0:0:0:0:0:0:1', 'login', 'auth', '管理员登录', NULL, NULL, 'fail', 'null登录失败！');
INSERT INTO `sm_log` VALUES (3, 1, 'admin', 'admin', '2019-03-12 17:18:50', '0:0:0:0:0:0:0:1', 'login', 'auth', '管理员登录', NULL, NULL, 'fail', 'null登录失败！');
INSERT INTO `sm_log` VALUES (4, 1, 'admin', 'admin', '2019-03-12 17:18:54', '0:0:0:0:0:0:0:1', 'login', 'auth', '管理员登录', NULL, NULL, 'fail', 'null登录失败！');
INSERT INTO `sm_log` VALUES (5, 1, 'admin', 'admin', '2019-03-12 17:21:28', '0:0:0:0:0:0:0:1', 'login', 'auth', '管理员登录', NULL, NULL, 'fail', 'null登录失败！');
INSERT INTO `sm_log` VALUES (6, 1, 'admin', 'admin', '2019-03-12 17:21:36', '0:0:0:0:0:0:0:1', 'login', 'auth', '管理员登录', NULL, NULL, 'fail', 'null登录失败！');
INSERT INTO `sm_log` VALUES (7, 1, 'admin', 'admin', '2019-03-12 17:21:42', '0:0:0:0:0:0:0:1', 'login', 'auth', '管理员登录', NULL, NULL, 'fail', 'null登录失败！');
INSERT INTO `sm_log` VALUES (8, 1, 'admin', 'admin', '2019-03-12 17:21:45', '0:0:0:0:0:0:0:1', 'login', 'auth', '管理员登录', NULL, NULL, 'fail', 'null登录失败！');
INSERT INTO `sm_log` VALUES (9, 1, 'admin', 'admin', '2019-03-12 17:24:19', '0:0:0:0:0:0:0:1', 'login', 'auth', '管理员登录', NULL, NULL, 'fail', 'null登录失败！');
INSERT INTO `sm_log` VALUES (10, 1, 'admin', 'admin', '2019-03-12 17:29:34', '0:0:0:0:0:0:0:1', 'login', 'auth', '用户登录', NULL, NULL, 'success', 'admin登录成功！');
COMMIT;

-- ----------------------------
-- Table structure for sm_menu
-- ----------------------------
DROP TABLE IF EXISTS `sm_menu`;
CREATE TABLE `sm_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_upd_by` bigint(20) DEFAULT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `menu_code` varchar(255) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `parent_menu_id` bigint(20) DEFAULT NULL,
  `menu_link` varchar(4000) NOT NULL,
  `taxis_no` int(11) DEFAULT NULL,
  `active_flag` char(1) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_fk_sm_menu_8` (`parent_menu_id`),
  CONSTRAINT `fk_fk_sm_menu_8` FOREIGN KEY (`parent_menu_id`) REFERENCES `sm_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sm_menu
-- ----------------------------
BEGIN;
INSERT INTO `sm_menu` VALUES (1, 1, '2013-07-17 00:00:01', NULL, NULL, 'home', '首页', NULL, '/', 1, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (5, 1, '2013-07-17 00:00:01', NULL, NULL, 'safemonitor', '安全监控', NULL, '', 5, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (10, 1, '2013-07-17 00:00:01', NULL, NULL, 'eventlog', '安全事件', 5, '/safemonitor/eventlog', 10, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (15, 1, '2013-07-17 00:00:01', NULL, NULL, 'alert', '安全告警', 5, '/safemonitor/alert', 15, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (20, 1, '2013-07-17 00:00:01', NULL, NULL, 'ticket', '工单管理', 5, '/safemonitor/ticket', 20, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (25, 1, '2013-07-17 00:00:01', NULL, NULL, 'config', '安全配置', NULL, '', 25, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (30, 1, '2013-07-17 00:00:01', NULL, NULL, 'alertrule', '告警规则', 25, '/config/alertrule', 30, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (35, 1, '2013-07-17 00:00:01', NULL, NULL, 'safeconfig', '安全配置', 25, '/config/safeconfig', 35, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (40, 1, '2013-07-17 00:00:01', NULL, NULL, 'asset', '资产管理', NULL, '', 40, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (45, 1, '2013-07-17 00:00:01', NULL, NULL, 'safeasset', '安全资产', 25, '/safeasset/asset', 45, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (50, 1, '2013-07-17 00:00:01', NULL, NULL, 'system', '系统管理', NULL, '', 50, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (55, 1, '2013-07-17 00:00:01', NULL, NULL, 'org', '组织管理', 50, '/organize', 55, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (60, 1, '2013-07-17 00:00:01', NULL, NULL, 'role', '角色管理', 50, '/role', 60, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (65, 1, '2013-07-17 00:00:01', NULL, NULL, 'user', '用户管理', 50, '/account/user', 65, 'Y', NULL);
INSERT INTO `sm_menu` VALUES (70, 1, '2013-07-17 00:00:01', NULL, NULL, 'systemlog', '系统日志', 50, '/log/systemlog', 70, 'Y', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sm_organization
-- ----------------------------
DROP TABLE IF EXISTS `sm_organization`;
CREATE TABLE `sm_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_upd_by` bigint(20) DEFAULT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `group_name` varchar(255) NOT NULL,
  `parent_organization_id` bigint(20) DEFAULT NULL,
  `group_level` int(11) NOT NULL,
  `name_path` varchar(4000) NOT NULL,
  `id_path` varchar(4000) NOT NULL,
  `active_flag` char(1) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sm_organization
-- ----------------------------
BEGIN;
INSERT INTO `sm_organization` VALUES (1, 1, '2013-07-17 00:00:01', NULL, NULL, '赛克蓝德', NULL, 0, '', '', 'Y', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sm_role
-- ----------------------------
DROP TABLE IF EXISTS `sm_role`;
CREATE TABLE `sm_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_upd_by` bigint(20) DEFAULT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `role_code` varchar(64) NOT NULL,
  `role_name` varchar(64) DEFAULT NULL,
  `active_flag` char(1) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sm_role
-- ----------------------------
BEGIN;
INSERT INTO `sm_role` VALUES (1, 1, '2013-07-17 00:00:01', NULL, NULL, 'admin', 'admin', 'Y', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sm_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sm_role_menu`;
CREATE TABLE `sm_role_menu` (
  `sm_role_id` bigint(20) NOT NULL,
  `sm_menu_id` bigint(20) NOT NULL,
  KEY `fk_reference_28` (`sm_menu_id`),
  KEY `fk_reference_29` (`sm_role_id`),
  CONSTRAINT `fk_reference_28` FOREIGN KEY (`sm_menu_id`) REFERENCES `sm_menu` (`id`),
  CONSTRAINT `fk_reference_29` FOREIGN KEY (`sm_role_id`) REFERENCES `sm_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sm_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sm_role_menu` VALUES (1, 1);
INSERT INTO `sm_role_menu` VALUES (1, 5);
INSERT INTO `sm_role_menu` VALUES (1, 10);
INSERT INTO `sm_role_menu` VALUES (1, 15);
INSERT INTO `sm_role_menu` VALUES (1, 20);
INSERT INTO `sm_role_menu` VALUES (1, 25);
INSERT INTO `sm_role_menu` VALUES (1, 30);
INSERT INTO `sm_role_menu` VALUES (1, 35);
INSERT INTO `sm_role_menu` VALUES (1, 40);
INSERT INTO `sm_role_menu` VALUES (1, 45);
INSERT INTO `sm_role_menu` VALUES (1, 50);
INSERT INTO `sm_role_menu` VALUES (1, 55);
INSERT INTO `sm_role_menu` VALUES (1, 60);
INSERT INTO `sm_role_menu` VALUES (1, 65);
INSERT INTO `sm_role_menu` VALUES (1, 70);
COMMIT;

-- ----------------------------
-- Table structure for sm_user
-- ----------------------------
DROP TABLE IF EXISTS `sm_user`;
CREATE TABLE `sm_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_by` bigint(20) NOT NULL,
  `created_date` datetime NOT NULL,
  `last_upd_by` bigint(20) DEFAULT NULL,
  `last_upd_date` datetime DEFAULT NULL,
  `user_code` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `salt` varchar(64) DEFAULT NULL,
  `sm_organization_id` bigint(20) DEFAULT NULL,
  `password_recover_key` varchar(255) DEFAULT NULL,
  `admin_flag` varchar(64) DEFAULT NULL,
  `gender` varchar(64) DEFAULT NULL,
  `mobile` varchar(64) DEFAULT NULL,
  `phone` varchar(64) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `safe_question` varchar(64) DEFAULT NULL,
  `safe_answer` varchar(255) DEFAULT NULL,
  `locked_date` datetime DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `login_failure_count` int(11) DEFAULT NULL,
  `state` varchar(64) NOT NULL,
  `active_flag` char(1) NOT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_usercode` (`user_code`),
  KEY `fk_reference_10` (`sm_organization_id`),
  CONSTRAINT `fk_reference_10` FOREIGN KEY (`sm_organization_id`) REFERENCES `sm_organization` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sm_user
-- ----------------------------
BEGIN;
INSERT INTO `sm_user` VALUES (1, 1, '2013-07-17 00:00:01', NULL, '2019-03-12 17:29:04', 'admin', 'admin', '53b1fd93e0acbab6168e72cfee5838c07ad4e423', '13b3494d1c7cce63', 1, NULL, NULL, '1', NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, 'active', 'Y', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sm_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sm_user_role`;
CREATE TABLE `sm_user_role` (
  `sm_user_id` bigint(20) NOT NULL,
  `sm_role_id` bigint(20) NOT NULL,
  KEY `fk_reference_16` (`sm_user_id`),
  KEY `fk_sm_user_role_sm_role` (`sm_role_id`),
  CONSTRAINT `fk_reference_16` FOREIGN KEY (`sm_user_id`) REFERENCES `sm_user` (`id`),
  CONSTRAINT `fk_sm_user_role_sm_role` FOREIGN KEY (`sm_role_id`) REFERENCES `sm_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sm_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sm_user_role` VALUES (1, 1);
COMMIT;

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` date DEFAULT NULL,
  `create_user` varchar(255) DEFAULT '0',
  `process_date` date DEFAULT NULL,
  `process_user` varchar(255) DEFAULT NULL,
  `create_mail` varchar(255) DEFAULT NULL,
  `create_phone` varchar(255) DEFAULT NULL,
  `level` char(10) DEFAULT NULL,
  `active_flag` char(1) NOT NULL,
  `ticket_name` varchar(255) DEFAULT NULL,
  `message` varchar(4000) NOT NULL,
  `process_type` varchar(64) DEFAULT NULL,
  `process_remark` varchar(4000) DEFAULT NULL,
  `report_files` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
