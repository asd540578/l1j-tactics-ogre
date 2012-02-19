SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `castle`
-- ----------------------------
DROP TABLE IF EXISTS `castle`;
CREATE TABLE `castle` (
  `castle_id` int(11) NOT NULL DEFAULT '0',
  `name` varchar(45) NOT NULL DEFAULT '',
  `war_time` datetime DEFAULT NULL,
  `tax_rate` int(11) NOT NULL DEFAULT '0',
  `public_money` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`castle_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of castle
-- ----------------------------
INSERT INTO castle VALUES ('1', 'ケント城', '2007-10-03 22:00:00', '10', '0');
INSERT INTO castle VALUES ('2', 'オークの森', '2007-10-04 22:00:00', '10', '0');
INSERT INTO castle VALUES ('3', 'ウィンダウッド城', '2007-10-05 22:00:00', '10', '0');
INSERT INTO castle VALUES ('4', 'ギラン城', '2007-10-06 22:00:00', '10', '0');
INSERT INTO castle VALUES ('5', 'ハイネ城', '2007-10-03 22:00:00', '10', '0');
INSERT INTO castle VALUES ('6', 'ドワーフ城', '2007-10-04 22:00:00', '10', '0');
INSERT INTO castle VALUES ('7', 'アデン城', '2007-10-05 22:00:00', '10', '0');
INSERT INTO castle VALUES ('8', 'ディアド要塞', '2007-10-06 22:00:00', '10', '0');
