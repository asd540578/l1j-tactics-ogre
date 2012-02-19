package l1j.server.server.templates;

import java.sql.ResultSet;
import java.sql.SQLException;

import l1j.server.server.utils.IntRange;
import l1j.server.server.utils.L1QueryUtil.EntityFactory;

public class L1MobSkill {
	private int _npcId;
	private int _actionNo;
	private String _name;
	private int _type;
	private int _triRnd;
	private int _triHp;
	private int _triCompanionHp;
	private int _triRange;
	private int _triCount;
	private int _changeTarget;
	private int _range;
	private int _areaWidth;
	private int _areaHeight;
	private int _leverage;
	private int _skillId;
	private int _gfxId;
	private int _actId;
	private int _summonId;
	private int _summonMin;
	private int _summonMax;
	private int _polyId;
	public static final int TYPE_NONE = 0;
	public static final int TYPE_PHYSICAL_ATTACK = 1;
	public static final int TYPE_MAGIC_ATTACK = 2;
	public static final int TYPE_SUMMON = 3;
	public static final int TYPE_POLY = 4;

	public static final int CHANGE_TARGET_NO = 0;
	public static final int CHANGE_TARGET_COMPANION = 1;
	public static final int CHANGE_TARGET_ME = 2;
	public static final int CHANGE_TARGET_RANDOM = 3;

	private L1MobSkill() {
	}

	public int getNpcId() {
		return _npcId;
	}

	public int getActionNo() {
		return _actionNo;
	}

	public String getName() {
		return _name;
	}

	/**
	 * @return スキルのタイプ 0→何もしない、1→物理攻撃、2→魔法攻撃、3→サモン
	 */
	public int getType() {
		return _type;
	}

	/**
	 * @return スキル発動条件：ランダムな確率（0%～100%）でスキル発動
	 */
	public int getTriggerRandom() {
		return _triRnd;
	}

	/**
	 * @return スキル発動条件：HPがこの%以下で発動
	 */
	public int getTriggerHp() {
		return _triHp;
	}

	/**
	 * @return スキル発動条件：同族のHPがこの%以下で発動
	 */
	public int getTriggerCompanionHp() {
		return _triCompanionHp;
	}

	/**
	 * @return スキル発動条件：triRange<0の場合、対象との距離がabs(triRange)以下のとき発動
	 *         triRange>0の場合、対象との距離がtriRange以上のとき発動
	 */
	public int getTriggerRange() {
		return _triRange;
	}

	/**
	 * @return スキル発動条件：スキルの発動回数がtriCount以下のとき発動
	 */
	public int getTriggerCount() {
		return _triCount;
	}

	/**
	 * @return スキル発動時、ターゲットを変更するか?
	 */
	public int getChangeTarget() {
		return _changeTarget;
	}

	/**
	 * @return rangeまでの距離ならば攻撃可能、物理攻撃をするならば近接攻撃の場合でも1以上
	 */
	public int getRange() {
		return _range;
	}

	/**
	 * @return 範囲攻撃の横幅、単体攻撃ならば0、範囲攻撃するならば0以上。
	 *         WidthとHeightの設定は攻撃者からみて横幅をWidth、奥行きをHeightとする。
	 *         Widthは+-あるので、1を指定すれば、ターゲットを中心として左右1までが対象となる。
	 */
	public int getAreaWidth() {
		return _areaWidth;
	}

	/**
	 * @return 範囲攻撃の高さ、単体攻撃ならば0、範囲攻撃するならば1以上
	 */
	public int getAreaHeight() {
		return _areaHeight;
	}

	/**
	 * @return ダメージの倍率、1/10で表す。物理攻撃、魔法攻撃共に有効
	 */
	public int getLeverage() {
		return _leverage;
	}

	/**
	 * @return 魔法を使う場合のSkillId
	 */
	public int getSkillId() {
		return _skillId;
	}

	/**
	 * @return 物理攻撃のモーショングラフィック
	 */
	public int getGfxId() {
		return _gfxId;
	}

	/**
	 * @return 物理攻撃のグラフィックのアクションID
	 */
	public int getActId() {
		return _actId;
	}

	/**
	 * @return サモンするモンスターのNPCID
	 */
	public int getSummonId() {
		return _summonId;
	}

	/**
	 * @return サモンするモンスターの最小数
	 */
	public int getSummonMin() {
		return _summonMin;
	}

	/**
	 * @return サモンするモンスターの最大数
	 */
	public int getSummonMax() {
		return _summonMax;
	}

	/**
	 * @return サモンするモンスター数を保持したIntRangeオブジェクト
	 */
	public IntRange getSummonCountRange() {
		return new IntRange(_summonMin, _summonMax);
	}

	/**
	 * @return 強制変身させるPolyID
	 */
	public int getPolyId() {
		return _polyId;
	}

	/**
	 * distanceが指定idxスキルの発動条件を満たしているか
	 */
	public boolean isTriggerDistance(int distance) {
		int triggerRange = getTriggerRange();

		if ((triggerRange < 0 && distance <= Math.abs(triggerRange))
				|| (triggerRange > 0 && distance >= triggerRange)) {
			return true;
		}
		return false;
	}

	public static class Factory implements EntityFactory<L1MobSkill> {
		@Override
		public L1MobSkill fromResultSet(ResultSet rs) throws SQLException {
			L1MobSkill result = new L1MobSkill();
			result._npcId = rs.getInt("mobid");
			result._actionNo = rs.getInt("actNo");
			result._name = rs.getString("mobname");
			result._type = rs.getInt("Type");
			result._triRnd = rs.getInt("TriRnd");
			result._triHp = rs.getInt("TriHp");
			result._triCompanionHp = rs.getInt("TriCompanionHp");
			result._triRange = rs.getInt("TriRange");
			result._triCount = rs.getInt("TriCount");
			result._changeTarget = rs.getInt("ChangeTarget");
			result._range = rs.getInt("Range");
			result._areaWidth = rs.getInt("AreaWidth");
			result._areaHeight = rs.getInt("AreaHeight");
			result._leverage = rs.getInt("Leverage");
			result._skillId = rs.getInt("SkillId");
			result._gfxId = rs.getInt("GfxId");
			result._actId = rs.getInt("ActId");
			result._summonId = rs.getInt("SummonId");
			result._summonMin = rs.getInt("SummonMin");
			result._summonMax = rs.getInt("SummonMax");
			result._polyId = rs.getInt("PolyId");
			return result;
		}
	}
}
