package l1j.server.server.to.skill;

import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1Location;
import l1j.server.server.templates.L1SkillEffect;

public class RandomSkill extends SkillEffect
{
	// コンストラクターの定義
	public RandomSkill(L1Character user, L1Character target,
			L1SkillEffect effect)
	{
		super(user, target, effect);
	}

	// スキルの実態(戻り値・引数なし)
	public void run()
	{
		final int center_x = _center.getX();
		final int center_y = _center.getY();
		final int mapid = _center.getMapId();
		// 【スキル動作の定義】
		// 固定回数分ループ
		for (int i = 0; i < _effect.getCount(); i++)
		{
			// 座標の分解
			int random_x = dice.nextInt(_effect.getLineRadius());
			int random_y = dice.nextInt(_effect.getLineRadius());
			int x = center_x + (dice.nextBoolean() == true ? random_x : -random_x);
			int y = center_y + (dice.nextBoolean() == true ? random_y : -random_y);

			if(i == 0) // 初回だけはターゲットに当てる
			{
				x = _center.getX();
				y = _center.getY();
			}
			L1Location next_loc = new L1Location(x, y, mapid);

			this.sendEffect(x, y);
			this.damageEffect(next_loc);
		}
	}
}
