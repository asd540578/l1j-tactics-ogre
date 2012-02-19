#実行条件 
# accountテーブルが新形式になっていること(＝idカラムが追加されていること)
# inventory_itemsテーブルが作成されていて空である事 

#実行後、次の4テーブルは不要なので削除する
# character_items
# character_warehouse
# character_elf_warehouse
# clan_warehouse

#NULL値を埋める 
UPDATE character_items SET charge_count = 0 WHERE charge_count IS NULL; 
UPDATE character_items SET remaining_time = 0 WHERE remaining_time IS NULL; 
UPDATE character_items SET attr_enchant_kind = 0 WHERE attr_enchant_kind IS NULL; 
UPDATE character_items SET attr_enchant_level = 0 WHERE attr_enchant_level IS NULL; 
UPDATE character_warehouse SET charge_count = 0 WHERE charge_count IS NULL; 
UPDATE character_warehouse SET remaining_time = 0 WHERE remaining_time IS NULL; 
UPDATE character_warehouse SET attr_enchant_kind = 0 WHERE attr_enchant_kind IS NULL; 
UPDATE character_warehouse SET attr_enchant_level = 0 WHERE attr_enchant_level IS NULL; 
UPDATE character_elf_warehouse SET charge_count = 0 WHERE charge_count IS NULL; 
UPDATE character_elf_warehouse SET remaining_time = 0 WHERE remaining_time IS NULL; 
UPDATE character_elf_warehouse SET attr_enchant_kind = 0 WHERE attr_enchant_kind IS NULL; 
UPDATE character_elf_warehouse SET attr_enchant_level = 0 WHERE attr_enchant_level IS NULL; 
UPDATE clan_warehouse SET charge_count = 0 WHERE charge_count IS NULL; 
UPDATE clan_warehouse SET remaining_time = 0 WHERE remaining_time IS NULL; 
UPDATE clan_warehouse SET attr_enchant_kind = 0 WHERE attr_enchant_kind IS NULL; 
UPDATE clan_warehouse SET attr_enchant_level = 0 WHERE attr_enchant_level IS NULL; 

#アイテムのデータを移行 
INSERT INTO inventory_items (id,owner_id,location,item_id,item_count,is_equipped,enchant_level,is_identified,durability,charge_count,remaining_time,last_used,is_sealed,attr_enchant_kind,attr_enchant_level)
(SELECT id,char_id,'0',item_id,`count`,is_equipped,enchantlvl,is_id,durability,charge_count,remaining_time,last_used,CASE WHEN bless < 128 THEN 0 ELSE 1 END,attr_enchant_kind,attr_enchant_level FROM character_items) UNION ALL 
(SELECT character_warehouse.id,accounts.id,'1',item_id,`count`,is_equipped,enchantlvl,is_id,durability,charge_count,remaining_time,last_used,CASE WHEN bless < 128 THEN 0 ELSE 1 END,attr_enchant_kind,attr_enchant_level FROM character_warehouse LEFT JOIN accounts ON character_warehouse.account_name = accounts.name) UNION ALL 
(SELECT character_elf_warehouse.id,accounts.id,'2',item_id,`count`,is_equipped,enchantlvl,is_id,durability,charge_count,remaining_time,last_used,CASE WHEN bless < 128 THEN 0 ELSE 1 END,attr_enchant_kind,attr_enchant_level FROM character_elf_warehouse LEFT JOIN accounts ON character_elf_warehouse.account_name = accounts.name) UNION ALL 
(SELECT id,clan_id,'3',item_id,`count`,is_equipped,enchantlvl,is_id,durability,charge_count,remaining_time,last_used,CASE WHEN bless < 128 THEN 0 ELSE 1 END,attr_enchant_kind,attr_enchant_level FROM clan_warehouse LEFT JOIN clan_data ON clan_warehouse.clan_name = clan_data.clan_name) 
ORDER BY id ASC; 
