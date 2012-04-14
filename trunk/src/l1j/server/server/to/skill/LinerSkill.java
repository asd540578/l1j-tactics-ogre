package l1j.server.server.to.skill;

import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1Location;
import l1j.server.server.templates.L1SkillEffect;

public class LinerSkill extends SkillEffect
{
	// 何マス分処理したか
	private int _roads = 0;

	// コンストラクターの定義
	public LinerSkill(L1Character attacker, L1Character target, L1SkillEffect effect)
	{
		super(attacker, target, effect);
	}

	// スキルの実態(戻り値・引数なし)
	public void run()
	{
		// 【スキル発動準備】
		// 向き 0.左上 1.上 2.右上 3.右 4.右下 5.下 6.左下 7.左
		// 向きによる座標増減を定数として定義しておく
		byte HEADING_TABLE_X[] =
		{
				0, 1, 1, 1, 0, -1, -1, -1
		};
		byte HEADING_TABLE_Y[] =
		{
				-1, -1, 0, 1, 1, 1, 0, -1
		};

		// エフェクトを出現させるマップ
		// 帰還した場合におかしくならないようここで固定する
		final int mapid = _attacker.getMapId();

		// スキルエフェクト開始地点の定義
		final L1Location start = new L1Location(_attacker.getX()
				+ HEADING_TABLE_X[_attacker.getHeading()], _attacker.getY()
				+ HEADING_TABLE_Y[_attacker.getHeading()], mapid);

		// ターゲット無（方向のみで攻撃）
		if (_target == null)
		{
			// スキルエフェクト終端地点の定義
			final L1Location end = new L1Location(_attacker.getX()
					+ HEADING_TABLE_X[_attacker.getHeading()]
					* _effect.getLineRadius(), _attacker.getY()
					+ HEADING_TABLE_Y[_attacker.getHeading()]
					* _effect.getLineRadius(), mapid);
			doAction(start ,end ,mapid);
		}

		// ターゲット有
		if (_target != null)
		{
			// スキルエフェクト中間地点の定義（ターゲット）
			final L1Location half = new L1Location(_target.getX(),
					_target.getY(), mapid);

			// スキルエフェクト終端地点の定義(SKILL_LINE_RADIUS分)
			int x_slope = 0, y_slope = 0;
			try
			{

				x_slope = _effect.getLineRadius()
						/ (_target.getX() - _attacker.getX());
			}
			catch (Exception e)
			{
				x_slope = 0;
			}
			try
			{
				y_slope = _effect.getLineRadius()
						/ (_target.getY() - _attacker.getY());
			}
			catch (Exception e)
			{
				y_slope = 0;
			}
			final L1Location end = new L1Location(_attacker.getX() + x_slope
					* _effect.getLineRadius(), _attacker.getY() + y_slope
					* _effect.getLineRadius(), mapid);
			// ロケーションを元にルート計算してエフェクト表示
			doAction(start, half, mapid);
			doAction(half, end, mapid);
		}
	}

	// 直線計算、ダメージ計算、エフェクト表示を行う
	private void doAction(L1Location src, L1Location dest, Integer mapid)
	{

		// ハッシュマップを作成して動的配列を定義
		ConcurrentHashMap<Integer, String> lineMap = createLineMap(src, dest);

		for (int i = 0; i < lineMap.size() && _roads < _effect.getLineRadius(); i += _effect
				.getLineInterval())
		{
			// 直線距離座標はカンマ区切りで保持しているため、(createLineMapメソットがそうしてる)
			// トークンナイザーでぶった切るために以下を定義
			String delim = ",";
			StringTokenizer st = new StringTokenizer(lineMap.get(i), delim);

			// 座標の分解
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			L1Location next_loc = new L1Location(x, y, mapid);

			this.sendEffect(x, y);
			this.damageEffect(next_loc);
		}
	}
}
