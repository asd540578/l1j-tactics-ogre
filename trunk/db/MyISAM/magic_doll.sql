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
-- Table structure for `magic_doll`
-- ----------------------------
DROP TABLE IF EXISTS `magic_doll`;
CREATE TABLE `magic_doll` (
  `item_id` int(10) unsigned NOT NULL,
  `note` varchar(45) CHARACTER SET utf8 DEFAULT '',
  `doll_id` int(10) NOT NULL,
  `ac` tinyint(2) NOT NULL DEFAULT '0',
  `hpr` tinyint(2) NOT NULL DEFAULT '0',
  `hpr_time` tinyint(2) NOT NULL DEFAULT '0',
  `mpr` tinyint(2) NOT NULL DEFAULT '0',
  `mpr_time` tinyint(2) NOT NULL DEFAULT '0',
  `hit` tinyint(2) NOT NULL DEFAULT '0',
  `dmg` tinyint(2) NOT NULL DEFAULT '0',
  `dmg_chance` tinyint(2) NOT NULL DEFAULT '0',
  `bow_hit` tinyint(2) NOT NULL DEFAULT '0',
  `bow_dmg` tinyint(2) NOT NULL DEFAULT '0',
  `dmg_reduction` tinyint(2) NOT NULL DEFAULT '0',
  `dmg_reduction_chance` tinyint(2) NOT NULL DEFAULT '0',
  `dmg_evasion_chance` tinyint(2) NOT NULL DEFAULT '0',
  `weight_reduction` tinyint(2) NOT NULL DEFAULT '0',
  `regist_stun` tinyint(2) NOT NULL DEFAULT '0',
  `regist_stone` tinyint(2) NOT NULL DEFAULT '0',
  `regist_sleep` tinyint(2) NOT NULL DEFAULT '0',
  `regist_freeze` tinyint(2) NOT NULL DEFAULT '0',
  `regist_sustain` tinyint(2) NOT NULL DEFAULT '0',
  `regist_blind` tinyint(2) NOT NULL DEFAULT '0',
  `make_itemid` int(10) NOT NULL DEFAULT '0',
  `effect` tinyint(2) NOT NULL DEFAULT '0',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of magic_doll
-- ----------------------------
INSERT INTO `magic_doll` VALUES ('41248', 'マジック ドール：バグベアー', '80106', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '10', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('41249', 'マジック ドール：サキュバス', '80107', '0', '0', '0', '15', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('41250', 'マジック ドール：ウェアウルフ', '80108', '0', '0', '0', '0', '0', '0', '15', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49037', 'マジック ドール：エルダー', '80129', '0', '0', '0', '15', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49038', 'マジック ドール：クラスタシアン', '80130', '0', '0', '0', '0', '0', '0', '15', '3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49039', 'マジック ドール：ストーン ゴーレム', '80131', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '15', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49289', 'マジック ドール：イエティ', '80153', '-3', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '7', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49290', 'マジック ドール：ラミア', '80154', '0', '0', '0', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1');
INSERT INTO `magic_doll` VALUES ('49291', 'マジック ドール：スパルトイ', '80155', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '4', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49292', 'マジック ドール：案山子', '80156', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49293', 'マジック ドール：コカトリス', '80157', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49294', 'マジック ドール：リッチ', '80158', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49295', '鋼鉄ギルドのマジックドール:イエティ', '80159', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49296', 'マジック ドール：シーダンサー', '80160', '0', '40', '1', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49297', 'マジック ドール：ロード', '80161', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49298', 'マジック ドール：アズール ハッチリン', '80162', '0', '20', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '10', '0', '0', '0', '0', '0', '0', '49302', '0');
INSERT INTO `magic_doll` VALUES ('49299', 'マジック ドール：クリムゾン ハッチリン', '80163', '0', '0', '0', '10', '0', '0', '0', '0', '0', '0', '0', '0', '0', '10', '0', '0', '0', '0', '0', '0', '49302', '0');
INSERT INTO `magic_doll` VALUES ('49300', '神秘のプリズム：進化したオスのハッチリン', '80164', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49301', '神秘のプリズム：進化したメスのハッチリン', '80165', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49318', 'マジック ドール：スノーマン', '80169', '0', '0', '0', '2', '0', '0', '2', '0', '0', '2', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `magic_doll` VALUES ('49319', 'マジック ドール：雪合戦の妖精', '80170', '0', '0', '0', '10', '1', '0', '0', '0', '7', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '0');
