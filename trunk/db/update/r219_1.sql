#------------------------------------------------------------------------------#
#実行条件
# 1.一度も実行していない事 
# 2.accounts_backupテーブルが存在しない事

#実行後に正しく移行出来たのを確認して、accounts_backupテーブルを手動で削除する。
#------------------------------------------------------------------------------#

#accountsテーブルの名前を変更 
ALTER TABLE accounts RENAME TO accounts_backup;

#AUTO_INCREMENTが100000001の新しいaccountsテーブルを作成
CREATE TABLE `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `last_activated_at` datetime NOT NULL,
  `access_level` int(11) NOT NULL DEFAULT '0',
  `ip` varchar(20) NOT NULL DEFAULT '',
  `host` varchar(255) NOT NULL DEFAULT '',
  `is_banned` tinyint(1) unsigned NOT NULL DEFAULT '0',
  `character_slot` int(2) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_index` (`name`)
) ENGINE=MyISAM AUTO_INCREMENT=100000001 DEFAULT CHARSET=utf8;

#アカウントのデータを移行 
INSERT INTO accounts (name,`password`,last_activated_at,access_level,ip,host,is_banned,character_slot) (SELECT login,`password`,lastactive,access_level,ip,host,banned,character_slot FROM accounts_backup); 

#account_idカラムのAUTO_INCREMENTを除去 
ALTER TABLE accounts MODIFY id int(11) NOT NULL; 
