ALTER TABLE character_buff RENAME character_buffs;
ALTER TABLE character_buffs MODIFY poly_id int NOT NULL DEFAULT 0;
ALTER TABLE character_buffs ADD attr_kind int NOT NULL DEFAULT 0;
