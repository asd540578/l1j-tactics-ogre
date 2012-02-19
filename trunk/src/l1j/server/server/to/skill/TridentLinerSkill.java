package l1j.server.server.to.skill;

import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1Location;
import l1j.server.server.templates.L1SkillEffect;

public class TridentLinerSkill extends SkillBase
{

    // コンストラクターの定義
	public TridentLinerSkill(L1Character user, L1Character target, L1SkillEffect effect)
    {
    	super(user, target ,effect);
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
	// ハッシュマップを作成して動的配列を定義
	ConcurrentHashMap<Integer, String> lineMap = new ConcurrentHashMap<Integer, String>();

	// スキルエフェクト開始地点の定義
	L1Location loc1 = new L1Location(_attacker.getX()
		+ HEADING_TABLE_X[_attacker.getHeading()], _attacker.getY()
		+ HEADING_TABLE_Y[_attacker.getHeading()], _attacker.getMapId());
	// スキルエフェクト終端地点の定義(SKILL_LINE_RADIUS分)
	L1Location loc2 = new L1Location(
		_attacker.getX()
			+ (HEADING_TABLE_X[_attacker.getHeading()] * _effect
				.getLineRadius()),
		_attacker.getY()
			+ (HEADING_TABLE_Y[_attacker.getHeading()] * _effect
				.getLineRadius()), _attacker.getMapId());
	// 開始地点から終了地点までの直線距離の取得
	lineMap = createLineMap(loc1, loc2);

	final int mapid = _attacker.getMapId();

	// 【スキル動作の定義】
	// 直線距離のセル数分だけループ
	for (int i = 0; i < lineMap.size(); i += _effect.getLineInterval())
	{
	    // 直線距離座標はカンマ区切りで保持しているため、(createLineMapメソットがそうしてる)
	    // トークンナイザーでぶった切りるために以下を定義
	    String delim = ",";
	    StringTokenizer st = new StringTokenizer(lineMap.get(i), delim);
	    // 座標分解後のXY座標保持用変数
	    int x = 0;
	    int y = 0;

	    // 座標の分解
	    x = Integer.parseInt(st.nextToken());
	    y = Integer.parseInt(st.nextToken());
	    L1Location next_loc = new L1Location(x, y, mapid);

	    this.sendEffect(x, y);
	    this.damageEffect(next_loc);
	}
    }
}
