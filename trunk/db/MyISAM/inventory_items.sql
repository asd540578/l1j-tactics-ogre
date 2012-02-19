SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `inventory_items`
-- ----------------------------
DROP TABLE IF EXISTS `inventory_items`;
CREATE TABLE `inventory_items` (
  `id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL,
  `location` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_count` int(11) NOT NULL,
  `is_equipped` tinyint(1) NOT NULL,
  `enchant_level` int(11) NOT NULL,
  `is_identified` tinyint(1) NOT NULL,
  `durability` int(11) NOT NULL,
  `charge_count` int(11) NOT NULL,
  `remaining_time` int(11) NOT NULL,
  `last_used` datetime DEFAULT NULL,
  `is_sealed` tinyint(1) NOT NULL,
  `attr_enchant_kind` int(11) NOT NULL,
  `attr_enchant_level` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `owner_index` (`owner_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inventory_items
-- ----------------------------
