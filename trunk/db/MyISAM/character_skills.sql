SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `character_skills`
-- ----------------------------
DROP TABLE IF EXISTS `character_skills`;
CREATE TABLE `character_skills` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `char_obj_id` int(10) NOT NULL DEFAULT '0',
  `skill_id` int(10) unsigned NOT NULL DEFAULT '0',
  `skill_name` varchar(45) NOT NULL DEFAULT '',
  `is_active` int(10) DEFAULT NULL,
  `activetimeleft` int(10) DEFAULT NULL,
  PRIMARY KEY (`char_obj_id`,`skill_id`),
  KEY `key_id` (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='MyISAM free: 10240 kB; MyISAM free: 10240 kB';

-- ----------------------------
-- Records of character_skills
-- ----------------------------
