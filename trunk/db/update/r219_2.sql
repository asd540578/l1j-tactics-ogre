#------------------------------------------------------------------------------#
#実行条件
# 1.一度も実行していない事 
# 2.characters_backupテーブルが存在しない事

#実行後に正しく移行出来たのを確認して、characters_backupテーブルを手動で削除する。
#------------------------------------------------------------------------------#

#バックアップテーブル作成
CREATE TABLE characters_backup LIKE characters;
INSERT characters_backup SELECT * FROM characters;

#インデックス削除
ALTER TABLE characters DROP INDEX key_id;

#カラム名変更
ALTER TABLE characters CHANGE objid id int NOT NULL;
ALTER TABLE characters CHANGE char_name name varchar(45) NOT NULL;

#アカウントIDのカラム追加
ALTER TABLE characters ADD account_id int NOT NULL AFTER id;

#アカウントIDを設定
UPDATE characters SET account_id = (SELECT id FROM accounts WHERE accounts.name = account_name);

#アカウント名のカラム削除
ALTER TABLE characters DROP account_name;

#インデックス作成
ALTER TABLE characters ADD UNIQUE INDEX key_index(account_id, name);