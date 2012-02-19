SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_buffs`
-- ----------------------------
DROP TABLE IF EXISTS `character_buffs`;
CREATE TABLE `character_buffs` (
  `char_obj_id` int(10) NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL DEFAULT '0',
  `remaining_time` int(10) NOT NULL DEFAULT '0',
  `poly_id` int(11) NOT NULL DEFAULT '0',
  `attr_kind` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`char_obj_id`,`skill_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='MyISAM free: 10240 kB; MyISAM free: 10240 kB';

-- ----------------------------
-- Records of character_buffs
-- ----------------------------
