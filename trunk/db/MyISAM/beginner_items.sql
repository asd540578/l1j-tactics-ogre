SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `beginner_items`
-- ----------------------------
DROP TABLE IF EXISTS `beginner_items`;
CREATE TABLE `beginner_items` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `item_id` int(11) NOT NULL DEFAULT '0',
  `item_count` int(11) NOT NULL DEFAULT '0',
  `charge_count` int(11) NOT NULL DEFAULT '0',
  `enchant_level` int(11) NOT NULL DEFAULT '0',
  `note` varchar(50) NOT NULL DEFAULT '',
  `class_initial` char(1) NOT NULL DEFAULT 'A',
  PRIMARY KEY (`id`),
  KEY `class_initial_index` (`class_initial`) USING BTREE
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of beginner_items
-- ----------------------------
INSERT INTO `beginner_items` VALUES ('1', '40005', '1', '0', '0', 'キャンドル', 'A');
INSERT INTO `beginner_items` VALUES ('2', '40005', '1', '0', '0', 'キャンドル', 'A');
INSERT INTO `beginner_items` VALUES ('3', '40641', '1', '0', '0', 'トーキングスクロール', 'A');
INSERT INTO `beginner_items` VALUES ('4', '40383', '1', '0', '0', '地図：歌う島', 'P');
INSERT INTO `beginner_items` VALUES ('5', '40378', '1', '0', '0', '地図：エルフの森', 'E');
INSERT INTO `beginner_items` VALUES ('6', '40380', '1', '0', '0', '地図：シルバーナイトの村', 'E');
INSERT INTO `beginner_items` VALUES ('7', '40384', '1', '0', '0', '地図：隠された渓谷', 'K');
INSERT INTO `beginner_items` VALUES ('8', '40383', '1', '0', '0', '地図：歌う島', 'W');
INSERT INTO `beginner_items` VALUES ('9', '40389', '1', '0', '0', '地図：沈黙の洞窟', 'D');
INSERT INTO `beginner_items` VALUES ('10', '40383', '1', '0', '0', '地図：歌う島', 'D');
