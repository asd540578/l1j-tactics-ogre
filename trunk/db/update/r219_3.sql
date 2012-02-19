#TODO trunkへマージする際に、ファイル名をリビジョン番号に変更する。

#------------------------------------------------------------------------------#
#実行後、beginnerテーブルは不要なので削除する。
#------------------------------------------------------------------------------#

# 新テーブル作成
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

# データ移行
INSERT beginner_items (id, item_id, item_count, charge_count, enchant_level, note, class_initial) (SELECT id, item_id, count, charge_count, enchantlvl, item_name, activate FROM beginner);
