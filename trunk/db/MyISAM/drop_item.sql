SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `drop_item`
-- ----------------------------
DROP TABLE IF EXISTS `drop_item`;
CREATE TABLE `drop_item` (
  `item_id` int(10) NOT NULL DEFAULT '0',
  `drop_rate` float unsigned NOT NULL DEFAULT '0',
  `drop_amount` float unsigned NOT NULL DEFAULT '0',
  `note` varchar(45) NOT NULL DEFAULT '',
  PRIMARY KEY (`item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of drop_item
-- ----------------------------
INSERT INTO drop_item VALUES ('1', '1', '1', 'オーキッシュ ダガー');
INSERT INTO drop_item VALUES ('200001', '1', '1', 'オーキッシュ ダガー');
INSERT INTO drop_item VALUES ('21', '1', '1', 'オーキッシュ ソード');
INSERT INTO drop_item VALUES ('91', '1', '1', 'オーキッシュ スピアー');
INSERT INTO drop_item VALUES ('171', '1', '1', 'オーキッシュ ボウ');
INSERT INTO drop_item VALUES ('200171', '1', '1', 'オーキッシュ ボウ');
INSERT INTO drop_item VALUES ('23', '1', '1', 'ブロード ソード');
INSERT INTO drop_item VALUES ('25', '1', '1', 'シルバー ソード');
INSERT INTO drop_item VALUES ('100025', '1', '1', 'シルバー ソード');
INSERT INTO drop_item VALUES ('26', '1', '1', 'ドワヴィッシュ ソード');
INSERT INTO drop_item VALUES ('27', '1', '1', 'シミター');
INSERT INTO drop_item VALUES ('100027', '1', '1', 'シミター');
INSERT INTO drop_item VALUES ('200027', '1', '1', 'シミター');
INSERT INTO drop_item VALUES ('31', '1', '1', 'ロング ソード');
INSERT INTO drop_item VALUES ('93', '1', '1', 'トライデント');
INSERT INTO drop_item VALUES ('94', '1', '1', 'パルチザン');
INSERT INTO drop_item VALUES ('96', '1', '1', '槍');
INSERT INTO drop_item VALUES ('98', '1', '1', 'グレイブ');
INSERT INTO drop_item VALUES ('100098', '1', '1', 'グレイブ');
INSERT INTO drop_item VALUES ('102', '1', '1', 'ルチェルン ハンマー');
INSERT INTO drop_item VALUES ('100102', '1', '1', 'ルチェルン ハンマー');
INSERT INTO drop_item VALUES ('103', '1', '1', 'ハルバード');
INSERT INTO drop_item VALUES ('100103', '1', '1', 'ハルバード');
INSERT INTO drop_item VALUES ('136', '1', '1', 'アックス');
INSERT INTO drop_item VALUES ('137', '1', '1', 'アタム');
INSERT INTO drop_item VALUES ('138', '1', '1', 'クラブ');
INSERT INTO drop_item VALUES ('139', '1', '1', 'フレイル');
INSERT INTO drop_item VALUES ('140', '1', '1', 'メイス');
INSERT INTO drop_item VALUES ('143', '1', '1', 'バトル アックス');
INSERT INTO drop_item VALUES ('100143', '1', '1', 'バトル アックス');
INSERT INTO drop_item VALUES ('144', '1', '1', 'ドワヴィッシュ アックス');
INSERT INTO drop_item VALUES ('172', '1', '1', 'ボウ');
INSERT INTO drop_item VALUES ('100172', '1', '1', 'ボウ');
INSERT INTO drop_item VALUES ('173', '1', '1', 'ショート ボウ');
INSERT INTO drop_item VALUES ('20034', '1', '1', 'オーキッシュ ヘルム');
INSERT INTO drop_item VALUES ('220034', '1', '1', 'オーキッシュ ヘルム');
INSERT INTO drop_item VALUES ('20072', '1', '1', 'オーキッシュ クローク');
INSERT INTO drop_item VALUES ('20135', '1', '1', 'オーキッシュ リング メイル');
INSERT INTO drop_item VALUES ('220135', '1', '1', 'オーキッシュ リング メイル');
INSERT INTO drop_item VALUES ('20136', '1', '1', 'オーキッシュ チェーン メイル');
INSERT INTO drop_item VALUES ('220136', '1', '1', 'オーキッシュ チェーン メイル');
INSERT INTO drop_item VALUES ('20237', '1', '1', 'オーキッシュ シールド');
INSERT INTO drop_item VALUES ('220237', '1', '1', 'オーキッシュ シールド');
INSERT INTO drop_item VALUES ('20007', '1', '1', 'ドワヴィッシュ アイアン ヘルム');
INSERT INTO drop_item VALUES ('20052', '1', '1', 'ドワヴィッシュ クローク');
INSERT INTO drop_item VALUES ('20223', '1', '1', 'ドワヴィッシュ ラウンド シールド');
INSERT INTO drop_item VALUES ('20043', '1', '1', 'ヘルム');
INSERT INTO drop_item VALUES ('120043', '1', '1', 'ヘルム');
INSERT INTO drop_item VALUES ('220043', '1', '1', 'ヘルム');
INSERT INTO drop_item VALUES ('20089', '1', '1', 'レザー アーマー');
INSERT INTO drop_item VALUES ('20096', '1', '1', 'リング メイル');
INSERT INTO drop_item VALUES ('20147', '1', '1', 'スタデッド レザー アーマー');
INSERT INTO drop_item VALUES ('220147', '1', '1', 'スタデッド レザー アーマー');
INSERT INTO drop_item VALUES ('20122', '1', '1', 'スケイル メイル');
INSERT INTO drop_item VALUES ('220122', '1', '1', 'スケイル メイル');
INSERT INTO drop_item VALUES ('20125', '1', '1', 'チェーン メイル');
INSERT INTO drop_item VALUES ('220125', '1', '1', 'チェーン メイル');
INSERT INTO drop_item VALUES ('20149', '1', '1', 'ブロンズ プレート メイル');
INSERT INTO drop_item VALUES ('120149', '1', '1', 'ブロンズ プレート メイル');
INSERT INTO drop_item VALUES ('20115', '1', '1', 'スプリント メイル');
INSERT INTO drop_item VALUES ('220115', '1', '1', 'スプリント メイル');
INSERT INTO drop_item VALUES ('20101', '1', '1', 'バンデッド メイル');
INSERT INTO drop_item VALUES ('120101', '1', '1', 'バンデッド メイル');
INSERT INTO drop_item VALUES ('220101', '1', '1', 'バンデッド メイル');
INSERT INTO drop_item VALUES ('20239', '1', '1', 'スモール シールド');
INSERT INTO drop_item VALUES ('20242', '1', '1', 'ラージ シールド');
INSERT INTO drop_item VALUES ('120242', '1', '1', 'ラージ シールド');
INSERT INTO drop_item VALUES ('20213', '1', '1', 'ロウ ブーツ');
INSERT INTO drop_item VALUES ('220213', '1', '1', 'ロウ ブーツ');
INSERT INTO drop_item VALUES ('20205', '1', '1', 'ブーツ');
INSERT INTO drop_item VALUES ('20182', '1', '1', 'グローブ');
INSERT INTO drop_item VALUES ('120182', '1', '1', 'グローブ');
INSERT INTO drop_item VALUES ('20162', '1', '1', 'レザー グローブ');
INSERT INTO drop_item VALUES ('40001', '1', '1', 'ランプ');
INSERT INTO drop_item VALUES ('40002', '1', '1', 'ランタン');
INSERT INTO drop_item VALUES ('40005', '1', '1', 'キャンドル');
