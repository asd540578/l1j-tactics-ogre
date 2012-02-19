SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `cooking_ingredients`
-- ----------------------------
DROP TABLE IF EXISTS `cooking_ingredients`;
CREATE TABLE `cooking_ingredients` (
  `id` int(11) NOT NULL,
  `cooking_recipe_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cooking_ingredients
-- ----------------------------
INSERT INTO `cooking_ingredients` VALUES ('1', '0', '40057', '1');
INSERT INTO `cooking_ingredients` VALUES ('2', '1', '41275', '1');
INSERT INTO `cooking_ingredients` VALUES ('3', '2', '41263', '1');
INSERT INTO `cooking_ingredients` VALUES ('4', '2', '41265', '1');
INSERT INTO `cooking_ingredients` VALUES ('5', '3', '41274', '1');
INSERT INTO `cooking_ingredients` VALUES ('6', '3', '41267', '1');
INSERT INTO `cooking_ingredients` VALUES ('7', '4', '40062', '1');
INSERT INTO `cooking_ingredients` VALUES ('8', '4', '40069', '1');
INSERT INTO `cooking_ingredients` VALUES ('9', '4', '40064', '1');
INSERT INTO `cooking_ingredients` VALUES ('10', '5', '40056', '1');
INSERT INTO `cooking_ingredients` VALUES ('11', '5', '40060', '1');
INSERT INTO `cooking_ingredients` VALUES ('12', '5', '40061', '1');
INSERT INTO `cooking_ingredients` VALUES ('13', '6', '41276', '1');
INSERT INTO `cooking_ingredients` VALUES ('14', '7', '40499', '1');
INSERT INTO `cooking_ingredients` VALUES ('15', '7', '40060', '1');
INSERT INTO `cooking_ingredients` VALUES ('16', '8', '49040', '1');
INSERT INTO `cooking_ingredients` VALUES ('17', '8', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('18', '9', '49041', '1');
INSERT INTO `cooking_ingredients` VALUES ('19', '9', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('20', '10', '49042', '1');
INSERT INTO `cooking_ingredients` VALUES ('21', '10', '41265', '1');
INSERT INTO `cooking_ingredients` VALUES ('22', '10', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('23', '11', '49043', '1');
INSERT INTO `cooking_ingredients` VALUES ('24', '11', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('25', '12', '49044', '1');
INSERT INTO `cooking_ingredients` VALUES ('26', '12', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('27', '13', '49045', '1');
INSERT INTO `cooking_ingredients` VALUES ('28', '13', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('29', '14', '49046', '1');
INSERT INTO `cooking_ingredients` VALUES ('30', '14', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('31', '15', '49047', '1');
INSERT INTO `cooking_ingredients` VALUES ('32', '15', '40499', '1');
INSERT INTO `cooking_ingredients` VALUES ('33', '15', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('34', '16', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('35', '16', '49243', '1');
INSERT INTO `cooking_ingredients` VALUES ('36', '16', '49260', '1');
INSERT INTO `cooking_ingredients` VALUES ('37', '17', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('38', '17', '49243', '1');
INSERT INTO `cooking_ingredients` VALUES ('39', '17', '49261', '1');
INSERT INTO `cooking_ingredients` VALUES ('40', '18', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('41', '18', '49243', '1');
INSERT INTO `cooking_ingredients` VALUES ('42', '18', '49262', '1');
INSERT INTO `cooking_ingredients` VALUES ('43', '19', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('44', '19', '49243', '1');
INSERT INTO `cooking_ingredients` VALUES ('45', '19', '49263', '1');
INSERT INTO `cooking_ingredients` VALUES ('46', '20', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('47', '20', '49243', '1');
INSERT INTO `cooking_ingredients` VALUES ('48', '20', '49264', '1');
INSERT INTO `cooking_ingredients` VALUES ('49', '21', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('50', '21', '49243', '1');
INSERT INTO `cooking_ingredients` VALUES ('51', '21', '49265', '1');
INSERT INTO `cooking_ingredients` VALUES ('52', '22', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('53', '22', '49243', '1');
INSERT INTO `cooking_ingredients` VALUES ('54', '22', '49266', '1');
INSERT INTO `cooking_ingredients` VALUES ('55', '23', '49048', '1');
INSERT INTO `cooking_ingredients` VALUES ('56', '23', '49243', '1');
INSERT INTO `cooking_ingredients` VALUES ('57', '23', '49267', '1');
