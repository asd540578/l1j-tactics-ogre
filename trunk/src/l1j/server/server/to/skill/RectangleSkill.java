package l1j.server.server.to.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1Location;
import l1j.server.server.templates.L1SkillEffect;
import l1j.server.server.types.Point;

public class RectangleSkill extends SkillBase
{

	/**
	 * 周毎に表示させる座標のリスト
	 */
	private static Map<Integer, List<Point>> rounds;
	/**
	 * クラスロード段階で読込む最大周
	 */
	private static final int MAX_ROUND = 20;

	/**
	 * エフェクトを表示する座標のパターンを作成する ※１周あたりのパターン数はradius^2個存在する
	 * ここでは半周分作成して残りは座標*-1で残り半周を処理する
	 */
	static
	{
		rounds = new HashMap<Integer, List<Point>>();

		// radiusが0の時だけ重複するので個別処理
		ArrayList<Point> pt_list = new ArrayList<Point>();
		pt_list.add(new Point(0, 0));
		rounds.put(0, pt_list);

		// ループ処理でエフェクトを発生させる座標を作成する
		for (int radius = 1; radius < MAX_ROUND; ++radius)
		{
			/* x座標とy座標をそれぞれ一旦作成 */
			List<Integer> x_list = new ArrayList<Integer>();
			List<Integer> y_list = new ArrayList<Integer>();

			for (int i = 0; i < radius; ++i)
			{
				x_list.add(i);
			}
			for (int i = radius; i > 0; --i)
			{
				x_list.add(i);
			}
			for (int i = -radius; i < radius; ++i)
			{
				y_list.add(i);
			}

			/* 求めた座標からLocationを作る */
			pt_list = new ArrayList<Point>();
			for (int i = 0; i < x_list.size() && i < y_list.size(); ++i)
			{
				Point loc = new Point(x_list.get(i), y_list.get(i));
				pt_list.add(loc);
			}

			for (int i = 0; i < x_list.size() && i < y_list.size(); ++i)
			{
				Point loc = new Point(x_list.get(i) * -1, y_list.get(i) * -1);
				pt_list.add(loc);
			}
			rounds.put(radius, pt_list);
		}

	}

	// コンストラクターの定義
	public RectangleSkill(L1Character user, L1Character target,
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
		for (int radius = 0; radius < _effect.getLineRadius(); radius += _effect
				.getLineInterval())
		{
			L1Location next_loc = null;
			List<Point> pt_list = rounds.get(radius);
			for (int i = 0; i < pt_list.size(); ++i)
			{
				Point pt = pt_list.get(i);

				final int x = center_x + pt.getX();
				final int y = center_y + pt.getY();
				this.sendEffect(x, y);
				next_loc = new L1Location(x, y, mapid);
				this.damageEffect(next_loc);
			}
		}
	}
}
