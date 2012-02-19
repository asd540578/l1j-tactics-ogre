/*
Navicat MySQL Data Transfer

Source Server         : l1j
Source Server Version : 50145
Source Host           : localhost:3306
Source Database       : l1jdb

Target Server Type    : MYSQL
Target Server Version : 50145
File Encoding         : 65001

Date: 2010-06-16
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `inn_key`
-- ----------------------------
DROP TABLE IF EXISTS `inn_key`;
CREATE TABLE `inn_key` (
  `item_obj_id` int(11) NOT NULL,
  `key_id` int(11) NOT NULL,
  `npc_id` int(10) DEFAULT NULL,
  `hall` tinyint(2) DEFAULT NULL,
  `due_time` datetime DEFAULT NULL,
  PRIMARY KEY (`item_obj_id`,`key_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of inn_key
-- ----------------------------
