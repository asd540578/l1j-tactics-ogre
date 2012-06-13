/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package l1j.server.server.model;

import static l1j.server.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.AREA_OF_SILENCE;
import static l1j.server.server.model.skill.L1SkillId.BONE_BREAK;
import static l1j.server.server.model.skill.L1SkillId.CANCELLATION;
import static l1j.server.server.model.skill.L1SkillId.CONFUSION;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_0_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_1_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_2_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_3_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_4_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_5_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_6_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_1_7_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_2_0_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_2_1_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_2_2_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_2_3_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_2_4_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_2_5_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_2_6_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_2_7_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_3_0_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_3_1_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_3_2_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_3_3_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_3_4_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_3_5_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_3_6_S;
import static l1j.server.server.model.skill.L1SkillId.COOKING_3_7_S;
import static l1j.server.server.model.skill.L1SkillId.COUNTER_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.COUNTER_MIRROR;
import static l1j.server.server.model.skill.L1SkillId.CURSE_BLIND;
import static l1j.server.server.model.skill.L1SkillId.CURSE_PARALYZE;
import static l1j.server.server.model.skill.L1SkillId.CURSE_POISON;
import static l1j.server.server.model.skill.L1SkillId.DARKNESS;
import static l1j.server.server.model.skill.L1SkillId.DARK_BLIND;
import static l1j.server.server.model.skill.L1SkillId.DECAY_POTION;
import static l1j.server.server.model.skill.L1SkillId.DISEASE;
import static l1j.server.server.model.skill.L1SkillId.DRAGON_SKIN;
import static l1j.server.server.model.skill.L1SkillId.EARTH_BIND;
import static l1j.server.server.model.skill.L1SkillId.ELEMENTAL_FALL_DOWN;
import static l1j.server.server.model.skill.L1SkillId.ENTANGLE;
import static l1j.server.server.model.skill.L1SkillId.ERASE_MAGIC;
import static l1j.server.server.model.skill.L1SkillId.FINAL_BURN;
import static l1j.server.server.model.skill.L1SkillId.FIRE_WALL;
import static l1j.server.server.model.skill.L1SkillId.FOG_OF_SLEEPING;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BREATH;
import static l1j.server.server.model.skill.L1SkillId.GUARD_BRAKE;
import static l1j.server.server.model.skill.L1SkillId.HORROR_OF_DEATH;
import static l1j.server.server.model.skill.L1SkillId.ICE_LANCE;
import static l1j.server.server.model.skill.L1SkillId.ILLUSION_AVATAR;
import static l1j.server.server.model.skill.L1SkillId.IMMUNE_TO_HARM;
import static l1j.server.server.model.skill.L1SkillId.JOY_OF_PAIN;
import static l1j.server.server.model.skill.L1SkillId.MANA_DRAIN;
import static l1j.server.server.model.skill.L1SkillId.MASS_SHOCK_STUN;
import static l1j.server.server.model.skill.L1SkillId.MASS_SLOW;
import static l1j.server.server.model.skill.L1SkillId.MIND_BREAK;
import static l1j.server.server.model.skill.L1SkillId.PANIC;
import static l1j.server.server.model.skill.L1SkillId.PATIENCE;
import static l1j.server.server.model.skill.L1SkillId.PHANTASM;
import static l1j.server.server.model.skill.L1SkillId.POLLUTE_WATER;
import static l1j.server.server.model.skill.L1SkillId.REDUCTION_ARMOR;
import static l1j.server.server.model.skill.L1SkillId.RESIST_FEAR;
import static l1j.server.server.model.skill.L1SkillId.RETURN_TO_NATURE;
import static l1j.server.server.model.skill.L1SkillId.SHOCK_STUN;
import static l1j.server.server.model.skill.L1SkillId.SLOW;
import static l1j.server.server.model.skill.L1SkillId.STATUS_CURSE_BARLOG;
import static l1j.server.server.model.skill.L1SkillId.STATUS_CURSE_YAHEE;
import static l1j.server.server.model.skill.L1SkillId.STATUS_HOLY_MITHRIL_POWDER;
import static l1j.server.server.model.skill.L1SkillId.STATUS_HOLY_WATER;
import static l1j.server.server.model.skill.L1SkillId.STATUS_HOLY_WATER_OF_EVA;
import static l1j.server.server.model.skill.L1SkillId.STRIKER_GALE;
import static l1j.server.server.model.skill.L1SkillId.TAMING_MONSTER;
import static l1j.server.server.model.skill.L1SkillId.THUNDER_GRAB;
import static l1j.server.server.model.skill.L1SkillId.WEAKNESS;
import static l1j.server.server.model.skill.L1SkillId.WEAPON_BREAK;
import static l1j.server.server.model.skill.L1SkillId.WIND_SHACKLE;

import java.util.logging.Logger;

import l1j.server.configure.Config;
import l1j.server.server.ActionCodes;
import l1j.server.server.WarTimeController;
import l1j.server.server.datatables.SkillTable;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.random.RandomGenerator;
import l1j.server.server.random.RandomGeneratorFactory;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.templates.L1MagicDoll;
import l1j.server.server.templates.L1Skill;

public class L1Magic {
	private static Logger _log = Logger.getLogger(L1Magic.class.getName());

	private int _calcType;

	private final int PC_PC = 1;

	private final int PC_NPC = 2;

	private final int NPC_PC = 3;

	private final int NPC_NPC = 4;

	private L1PcInstance _pc = null;

	private L1PcInstance _targetPc = null;

	private L1NpcInstance _npc = null;

	private L1NpcInstance _targetNpc = null;

	private int _leverage = 10; // 1/10倍で表現する。

	private static RandomGenerator _random = RandomGeneratorFactory.newRandom();

	public void setLeverage(int i) {
		_leverage = i;
	}

	private int getLeverage() {
		return _leverage;
	}

	public L1Magic(L1Character attacker, L1Character target) {
		if (attacker instanceof L1PcInstance) {
			if (target instanceof L1PcInstance) {
				_calcType = PC_PC;
				_pc = (L1PcInstance) attacker;
				_targetPc = (L1PcInstance) target;
			} else {
				_calcType = PC_NPC;
				_pc = (L1PcInstance) attacker;
				_targetNpc = (L1NpcInstance) target;
			}
		} else {
			if (target instanceof L1PcInstance) {
				_calcType = NPC_PC;
				_npc = (L1NpcInstance) attacker;
				_targetPc = (L1PcInstance) target;
			} else {
				_calcType = NPC_NPC;
				_npc = (L1NpcInstance) attacker;
				_targetNpc = (L1NpcInstance) target;
			}
		}
	}

	/* ■■■■■■■■■■■■■■■ 魔法共通関数 ■■■■■■■■■■■■■■ */
	private int getSpellPower() {
		int spellPower = 0;
		if (_calcType == PC_PC || _calcType == PC_NPC) {
			spellPower = _pc.getSp();
		} else if (_calcType == NPC_PC || _calcType == NPC_NPC) {
			spellPower = _npc.getSp();
		}
		return spellPower;
	}

	private int getMagicLevel() {
		int magicLevel = 0;
		if (_calcType == PC_PC || _calcType == PC_NPC) {
			magicLevel = _pc.getMagicLevel();
		} else if (_calcType == NPC_PC || _calcType == NPC_NPC) {
			magicLevel = _npc.getMagicLevel();
		}
		return magicLevel;
	}

	private int getMagicBonus() {
		int magicBonus = 0;
		if (_calcType == PC_PC || _calcType == PC_NPC) {
			magicBonus = _pc.getMagicBonus();
		} else if (_calcType == NPC_PC || _calcType == NPC_NPC) {
			magicBonus = _npc.getMagicBonus();
		}
		return magicBonus;
	}

	private int getLawful() {
		int lawful = 0;
		if (_calcType == PC_PC || _calcType == PC_NPC) {
			lawful = _pc.getLawful();
		} else if (_calcType == NPC_PC || _calcType == NPC_NPC) {
			lawful = _npc.getLawful();
		}
		return lawful;
	}

	private int getTargetMr() {
		int mr = 0;
		if (_calcType == PC_PC || _calcType == NPC_PC) {
			mr = _targetPc.getMr();
		} else {
			mr = _targetNpc.getMr();
		}
		return mr;
	}

	/* ■■■■■■■■■■■■■■ 成功判定 ■■■■■■■■■■■■■ */
	// ●●●● 確率系魔法の成功判定 ●●●●
	// 計算方法
	// 攻撃側ポイント：LV + ((MagicBonus * 3) * 魔法固有係数)
	// 防御側ポイント：((LV / 2) + (MR * 3)) / 2
	// 攻撃成功率：攻撃側ポイント - 防御側ポイント
	public boolean calcProbabilityMagic(int skillId) {
		int probability = 0;
		boolean isSuccess = false;

		// 攻撃者がGM権限の場合100%成功
		if (_pc != null && _pc.isGm()) {
			return true;
		}

		if (_calcType == PC_NPC && _targetNpc != null) {
			int npcId = _targetNpc.getNpcTemplate().get_npcId();
			if (npcId >= 45912 && npcId <= 45915 // 恨みに満ちたソルジャー＆ソルジャーゴースト
					&& !_pc.hasSkillEffect(STATUS_HOLY_WATER)) {
				return false;
			}
			if (npcId == 45916 // 恨みに満ちたハメル将軍
					&& !_pc.hasSkillEffect(STATUS_HOLY_MITHRIL_POWDER)) {
				return false;
			}
			if (npcId == 45941 // 呪われた巫女サエル
					&& !_pc.hasSkillEffect(STATUS_HOLY_WATER_OF_EVA)) {
				return false;
			}
			if (npcId == 45752 // バルログ(変身前)
					&& !_pc.hasSkillEffect(STATUS_CURSE_BARLOG)) {
				return false;
			}
			if (npcId == 45753 // バルログ(変身後)
					&& !_pc.hasSkillEffect(STATUS_CURSE_BARLOG)) {
				return false;
			}
			if (npcId == 45675 // ヤヒ(変身前)
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				return false;
			}
			if (npcId == 81082 // ヤヒ(変身後)
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				return false;
			}
			if (npcId == 45625 // 混沌
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				return false;
			}
			if (npcId == 45674 // 死
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				return false;
			}
			if (npcId == 45685 // 堕落
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				return false;
			}
			if (npcId >= 46068 && npcId <= 46091 // 欲望の洞窟側mob
					&& _pc.getTempCharGfx() == 6035) {
				return false;
			}
			if (npcId >= 46092 && npcId <= 46106 // 影の神殿側mob
					&& _pc.getTempCharGfx() == 6034) {
				return false;
			}
		}

		if (!checkZone(skillId)) {
			return false;
		}
		if (skillId == CANCELLATION) {
			if (_calcType == PC_PC && _pc != null && _targetPc != null) {
				// 自分自身の場合は100%成功
				if (_pc.getId() == _targetPc.getId()) {
					return true;
				}
				// 同じクランの場合は100%成功
				if (_pc.getClanid() > 0
						&& (_pc.getClanid() == _targetPc.getClanid())) {
					return true;
				}
				// 同じパーティの場合は100%成功
				if (_pc.isInParty()) {
					if (_pc.getParty().isMember(_targetPc)) {
						return true;
					}
				}
				// それ以外の場合、セーフティゾーン内では無効
				if (_pc.getZoneType() == 1 || _targetPc.getZoneType() == 1) {
					return false;
				}
			}
			// 対象がNPC、使用者がNPCの場合は100%成功
			if (_calcType == PC_NPC || _calcType == NPC_PC
					|| _calcType == NPC_NPC) {
				return true;
			}
		}

		// WBかカーパラかスローはMOB相手には無効
		if (skillId == WEAPON_BREAK || skillId == CURSE_PARALYZE || skillId == SLOW) {
			if(_calcType == PC_NPC)
			{
				return false;
			}
		}
		// アースバインド中はキャンセレーション以外無効
		if (_calcType == PC_PC || _calcType == NPC_PC) {
			if (_targetPc.hasSkillEffect(EARTH_BIND)) {
				if (skillId != CANCELLATION) {
					return false;
				}
			}
		} else {
			if (_targetNpc.hasSkillEffect(EARTH_BIND)) {
				if (skillId != CANCELLATION) {
					return false;
				}
			}
		}

		probability = calcProbability(skillId);

		int rnd = _random.nextInt(100) + 1;
		if (probability > 90) {
			probability = 90; // 最高成功率を90%とする。
		}

		if(_calcType == PC_PC)
		{
			if(probability < 2)
			{
				probability = 2;
			}
		}

		if (probability >= rnd) {
			isSuccess = true;
		} else {
			isSuccess = false;
		}

		// 確率系魔法メッセージ
		if (!Config.ALT_ATKMSG) {
			return isSuccess;
		}
		if (Config.ALT_ATKMSG) {
			if ((_calcType == PC_PC || _calcType == PC_NPC) && !_pc.isGm()) {
				return isSuccess;
			}
			if ((_calcType == PC_PC || _calcType == NPC_PC)
					&& !_targetPc.isGm()) {
				return isSuccess;
			}
		}

		String msg0 = "";
		String msg1 = "に";
		String msg2 = "";
		String msg3 = "";
		String msg4 = "";

		if (_calcType == PC_PC || _calcType == PC_NPC) { // アタッカーがＰＣの場合
			msg0 = _pc.getName();
		} else if (_calcType == NPC_PC) { // アタッカーがＮＰＣの場合
			msg0 = _npc.getName();
		}

		msg2 = "probability:" + probability + "%";
		if (_calcType == NPC_PC || _calcType == PC_PC) { // ターゲットがＰＣの場合
			msg4 = _targetPc.getName();
		} else if (_calcType == PC_NPC) { // ターゲットがＮＰＣの場合
			msg4 = _targetNpc.getName();
		}
		if (isSuccess == true) {
			msg3 = "成功";
		} else {
			msg3 = "失敗";
		}

		if (_calcType == PC_PC || _calcType == PC_NPC) { // アタッカーがＰＣの場合
			_pc.sendPackets(new S_ServerMessage(166, msg0, msg1, msg2, msg3,
					msg4)); // \f1%0が%4%1%3 %2
		}
		if (_calcType == NPC_PC || _calcType == PC_PC) { // ターゲットがＰＣの場合
			_targetPc.sendPackets(new S_ServerMessage(166, msg0, msg1, msg2,
					msg3, msg4)); // \f1%0が%4%1%3 %2
		}

		return isSuccess;
	}

	private boolean checkZone(int skillId) {
		if (_pc != null && _targetPc != null) {
			if (_pc.getZoneType() == 1 || _targetPc.getZoneType() == 1) { // セーフティーゾーン
				if (skillId == WEAPON_BREAK || skillId == SLOW
						|| skillId == CURSE_PARALYZE || skillId == MANA_DRAIN
						|| skillId == DARKNESS || skillId == WEAKNESS
						|| skillId == DISEASE || skillId == DECAY_POTION
						|| skillId == MASS_SLOW || skillId == ENTANGLE
						|| skillId == ERASE_MAGIC || skillId == EARTH_BIND
						|| skillId == AREA_OF_SILENCE
						|| skillId == WIND_SHACKLE || skillId == STRIKER_GALE
						|| skillId == SHOCK_STUN || skillId == MASS_SHOCK_STUN
						|| skillId == FOG_OF_SLEEPING || skillId == ICE_LANCE
						|| skillId == FREEZING_BLIZZARD
						|| skillId == FREEZING_BREATH
						|| skillId == POLLUTE_WATER
						|| skillId == ELEMENTAL_FALL_DOWN
						|| skillId == RETURN_TO_NATURE || skillId == BONE_BREAK
						|| skillId == CONFUSION || skillId == MIND_BREAK
						|| skillId == JOY_OF_PAIN || skillId == CURSE_POISON
						|| skillId == CURSE_BLIND || skillId == GUARD_BRAKE
						|| skillId == RESIST_FEAR || skillId == HORROR_OF_DEATH
						|| skillId == PHANTASM || skillId == PANIC) {
					return false;
				}
			}
		}
		return true;
	}

	private int calcProbability(int skillId) {
		L1Skill l1skills = SkillTable.getInstance().findBySkillId(skillId);
		int attackLevel = 0;
		int defenseLevel = 0;
		int probability = 0;

		if (_calcType == PC_PC || _calcType == PC_NPC) {
			attackLevel = _pc.getLevel();
		} else {
			attackLevel = _npc.getLevel();
		}

		if (_calcType == PC_PC || _calcType == NPC_PC) {
			defenseLevel = _targetPc.getLevel();
		} else {
			defenseLevel = _targetNpc.getLevel();
			if (skillId == RETURN_TO_NATURE) {
				if (_targetNpc instanceof L1SummonInstance) {
					L1SummonInstance summon = (L1SummonInstance) _targetNpc;
					defenseLevel = summon.getMaster().getLevel();
				}
			}
		}

		if (skillId == ELEMENTAL_FALL_DOWN || skillId == RETURN_TO_NATURE
				|| skillId == ENTANGLE || skillId == ERASE_MAGIC
				|| skillId == AREA_OF_SILENCE || skillId == WIND_SHACKLE
				|| skillId == STRIKER_GALE || skillId == POLLUTE_WATER
				|| skillId == EARTH_BIND) {
			// 成功確率は 魔法固有係数 × LV差 + 基本確率
			probability = (int) (((l1skills.getProbabilityDice()) / 10D) * (attackLevel - defenseLevel))
					+ l1skills.getProbabilityValue();

			// オリジナルINTによる魔法命中
			if (_calcType == PC_PC || _calcType == PC_NPC) {
				probability += 2 * _pc.getOriginalMagicHit();
			}
		} else if (skillId == SHOCK_STUN || skillId == MASS_SHOCK_STUN) {
			// 成功確率は 基本確率 + LV差1毎に+-2%
			// t.s 2012/06/10 mod start
			// probability = l1skills.getProbabilityValue()
			// + (attackLevel - defenseLevel) * 2;
			// t.s 2012/06/10 mod end
			if(_calcType == PC_NPC)
			{
    			probability = l1skills.getProbabilityValue()
				+ (attackLevel - (defenseLevel + 5)) * 2;
			}
			else
			{
    			probability = l1skills.getProbabilityValue()
    					+ (attackLevel - defenseLevel) * 2;
			}

			// オリジナルINTによる魔法命中
			if (_calcType == PC_PC || _calcType == PC_NPC) {
				probability += 2 * _pc.getOriginalMagicHit();
			}
		} else if (skillId == COUNTER_BARRIER) {
			/* t.s 2011/09/09 mod start */
			/* 反射側(at) > 反射される側(de) ・・・ (attack - defence) / 2 */
			/* 反射側(at) < 反射される側(de) ・・・ (attack - defence) */
			// 成功確率は 基本確率 + LV差1毎に+-1%
			if (attackLevel > defenseLevel) {
				probability = l1skills.getProbabilityValue()
						+ (attackLevel - defenseLevel) / 2;
			} else {
				probability = l1skills.getProbabilityValue()
						+ (attackLevel - defenseLevel);
			}

			/* t.s 2011/09/09 mod end */

			// オリジナルINTによる魔法命中
			if (_calcType == PC_PC || _calcType == PC_NPC) {
				probability += 2 * _pc.getOriginalMagicHit();
			}
		} else if (skillId == GUARD_BRAKE || skillId == RESIST_FEAR
				|| skillId == HORROR_OF_DEATH) {
			int dice = l1skills.getProbabilityDice();
			int value = l1skills.getProbabilityValue();
			int diceCount = 0;
			diceCount = getMagicBonus() + getMagicLevel();

			if (diceCount < 1) {
				diceCount = 1;
			}

			for (int i = 0; i < diceCount; i++) {
				probability += (_random.nextInt(dice) + 1 + value);
			}

			probability = probability * getLeverage() / 10;

			// オリジナルINTによる魔法命中
			if (_calcType == PC_PC || _calcType == PC_NPC) {
				probability += 2 * _pc.getOriginalMagicHit();
			}

			if (probability >= getTargetMr()) {
				probability = 100;
			} else {
				probability = 0;
			}
		} else if (skillId == THUNDER_GRAB) { // サンダーグラップ
			// 成功確率は probability_value(50%) * (自分のレベル / 対象のレベル) + ランダム(0～-20)
			probability = l1skills.getProbabilityValue()
					* (attackLevel / Math.max(1, defenseLevel))
					- _random.nextInt(21);

			// オリジナルINTによる魔法命中
			if (_calcType == PC_PC || _calcType == PC_NPC) {
				probability += 2 * _pc.getOriginalMagicHit();
			}
			// } else if (skillId == BONE_BREAK) {
			// probability = l1skills.getProbabilityValue();
		} else if (skillId == PHANTASM) { // ファンタズム
			// 成功確率は npc=probability_value / pc=probability_value+20%
			if (_calcType == PC_NPC) {
				probability = l1skills.getProbabilityValue();
			} else {
				probability = l1skills.getProbabilityValue() + 20;
			}
		} else if (skillId == DARKNESS){ // Ｄネスは対ＭＯＢ用
			int dice = l1skills.getProbabilityDice();
			int diceCount = 0;
			if (_calcType == PC_PC) { // 対人はその他確率魔法と同等
				if (_pc.isWizard()) {
					diceCount = Math.min(getMagicBonus()-5,15) + getMagicLevel() + 1;
				} else if (_pc.isElf()) {
					diceCount = Math.min(getMagicBonus()-5,15) + getMagicLevel() - 1;
				} else {
					diceCount = Math.min(getMagicBonus()-5,15) + getMagicLevel() - 1;
				}
			} else { // ＭＯＢへの魔法だけ確率をアップ
				diceCount = Math.min(getMagicBonus()-3,20) + getMagicLevel();
			}
			if (diceCount < 1) {
				diceCount = 1;
			}

			for (int i = 0; i < diceCount; i++) {
				probability += (_random.nextInt(dice) + 1);
			}
			probability = probability * getLeverage() / 10;

			// オリジナルINTによる魔法命中
			if (_calcType == PC_PC || _calcType == PC_NPC) {
			//	probability += 2 * _pc.getOriginalMagicHit();
			}

			if(getTargetMr() >= 160)
			{
				probability -= 150;
				probability -= (getTargetMr() - 150) / 3;
			}
			else
			{
				probability -= getTargetMr();
			}
		} else if (skillId == SLOW){ // そこそこかかってもいい系
			int dice = l1skills.getProbabilityDice();
			int diceCount = 0;
			if (_calcType == PC_PC) {
				if (_pc.isWizard()) {
					diceCount = Math.min(getMagicBonus()-5,17) + getMagicLevel() + 1;
				} else if (_pc.isElf()) {
					diceCount = Math.min(getMagicBonus()-5,17) + getMagicLevel() - 1;
				} else {
					diceCount = Math.min(getMagicBonus()-5,17) + getMagicLevel() - 1;
				}
			} else {
				diceCount = Math.min(getMagicBonus()-2,15) + getMagicLevel();
			}
			if (diceCount < 1) {
				diceCount = 1;
			}

			for (int i = 0; i < diceCount; i++) {
				probability += (_random.nextInt(dice) + 1);
			}
			probability = probability * getLeverage() / 10;

			// オリジナルINTによる魔法命中
			if (_calcType == PC_PC || _calcType == PC_NPC) {
			//	probability += 2 * _pc.getOriginalMagicHit();
			}

			if(getTargetMr() >= 160)
			{
				probability -= 150;
				probability -= (getTargetMr() - 150) / 3;
			}
			else
			{
				probability -= getTargetMr();
			}
		} else if (skillId == FOG_OF_SLEEPING || skillId == MANA_DRAIN){ // あまりかかって欲しくない系
			int dice = l1skills.getProbabilityDice();
			int diceCount = 0;
			if (_calcType == PC_PC || _calcType == PC_NPC) {
				if (_pc.isWizard()) {
					diceCount = Math.min(getMagicBonus()-5,14) + getMagicLevel() + 1;
				} else if (_pc.isElf()) {
					diceCount = Math.min(getMagicBonus()-5,14) + getMagicLevel() - 1;
				} else {
					diceCount = Math.min(getMagicBonus()-5,14) + getMagicLevel() - 1;
				}
			} else {
				diceCount = Math.min(getMagicBonus()-2,15) + getMagicLevel();
			}
			if (diceCount < 1) {
				diceCount = 1;
			}

			for (int i = 0; i < diceCount; i++) {
				probability += (_random.nextInt(dice) + 1);
			}
			probability = probability * getLeverage() / 10;

			// オリジナルINTによる魔法命中
			if (_calcType == PC_PC || _calcType == PC_NPC) {
			//	probability += 2 * _pc.getOriginalMagicHit();
			}

			if(getTargetMr() >= 160)
			{
				probability -= 150;
				probability -= (getTargetMr() - 150) / 3;
			}
			else
			{
				probability -= getTargetMr();
			}

			// ＭＲが100以上ある場合は最大5％にする。（イレース無でほぼかからないよう）
			if(_calcType == PC_PC && getTargetMr() >= 100)
			{
				probability = Math.min(probability ,5);
			}
		} else { // その他ちょっとかかってもいい系（キャンセ等）
			int dice = l1skills.getProbabilityDice();
			int diceCount = 0;
			if (_calcType == PC_PC || _calcType == PC_NPC) {
				if (_pc.isWizard()) {
					diceCount = Math.min(getMagicBonus()-5,15) + getMagicLevel() + 1;
				} else if (_pc.isElf()) {
					diceCount = Math.min(getMagicBonus()-5,15) + getMagicLevel() - 1;
				} else {
					diceCount = Math.min(getMagicBonus()-5,15) + getMagicLevel() - 1;
				}
			} else {
				diceCount = Math.min(getMagicBonus(),16) + getMagicLevel();
			}
			if (diceCount < 1) {
				diceCount = 1;
			}

			for (int i = 0; i < diceCount; i++) {
				probability += (_random.nextInt(dice) + 1);
			}
			probability = probability * getLeverage() / 10;

			// オリジナルINTによる魔法命中
			if (_calcType == PC_PC || _calcType == PC_NPC) {
//				probability += 2 * _pc.getOriginalMagicHit();
			}

			if(getTargetMr() >= 160)
			{
				probability -= 150;
				probability -= (getTargetMr() - 150) / 3;
			}
			else
			{
				probability -= getTargetMr();
			}

			if (skillId == TAMING_MONSTER) {
				double probabilityRevision = 1;
				if ((_targetNpc.getMaxHp() * 1 / 4) > _targetNpc.getCurrentHp()) {
					probabilityRevision = 1.3;
				} else if ((_targetNpc.getMaxHp() * 2 / 4) > _targetNpc
						.getCurrentHp()) {
					probabilityRevision = 1.2;
				} else if ((_targetNpc.getMaxHp() * 3 / 4) > _targetNpc
						.getCurrentHp()) {
					probabilityRevision = 1.1;
				}
				probability *= probabilityRevision;
			}
		}

		// 状態異常に対する耐性
		if (skillId == EARTH_BIND) {
			if (_calcType == PC_PC || _calcType == NPC_PC) {
				probability -= _targetPc.getRegistSustain();
			}
		} else if (skillId == SHOCK_STUN || skillId == MASS_SHOCK_STUN) {
			// || skillId == BONE_BREAK) {
			if (_calcType == PC_PC || _calcType == NPC_PC) {
				probability -= 2 * _targetPc.getRegistStun();
			}
		} else if (skillId == CURSE_PARALYZE) {
			if (_calcType == PC_PC || _calcType == NPC_PC) {
				probability -= _targetPc.getRegistStone();
			}
		} else if (skillId == FOG_OF_SLEEPING) {
			if (_calcType == PC_PC || _calcType == NPC_PC) {
				probability -= _targetPc.getRegistSleep();
			}
		} else if (skillId == ICE_LANCE || skillId == FREEZING_BLIZZARD
				|| skillId == FREEZING_BREATH) {
			if (_calcType == PC_PC || _calcType == NPC_PC) {
				probability -= _targetPc.getRegistFreeze();
			}
		} else if (skillId == CURSE_BLIND || skillId == DARKNESS
				|| skillId == DARK_BLIND) {
			if (_calcType == PC_PC || _calcType == NPC_PC) {
				probability -= _targetPc.getRegistBlind();
			}
		}

		return probability;
	}

	/* ■■■■■■■■■■■■■■ 魔法ダメージ算出 ■■■■■■■■■■■■■■ */

	public int calcMagicDamage(int skillId) {
		int damage = 0;
		if (_calcType == PC_PC || _calcType == NPC_PC) {
			damage = calcPcMagicDamage(skillId);
		} else if (_calcType == PC_NPC || _calcType == NPC_NPC) {
			damage = calcNpcMagicDamage(skillId);
		}
		damage = calcMrDefense(damage);
		damage = calcExceptionMagicDamage(skillId, damage);

		return damage;
	}

	// ●●●● プレイヤー へのファイアーウォールの魔法ダメージ算出 ●●●●
	public int calcPcFireWallDamage() {
		int dmg = 0;
		double attrDeffence = calcAttrResistance(L1Skill.ATTR_FIRE);
		L1Skill l1skills = SkillTable.getInstance().findBySkillId(FIRE_WALL);
		dmg = (int) ((1.0 - attrDeffence) * l1skills.getDamageValue());

		if (_targetPc.hasSkillEffect(ABSOLUTE_BARRIER)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(ICE_LANCE)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(FREEZING_BLIZZARD)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(FREEZING_BREATH)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(EARTH_BIND)) {
			dmg = 0;
		}

		if (dmg < 0) {
			dmg = 0;
		}

		return dmg;
	}

	// ●●●● ＮＰＣ へのファイアーウォールの魔法ダメージ算出 ●●●●
	public int calcNpcFireWallDamage() {
		int dmg = 0;
		double attrDeffence = calcAttrResistance(L1Skill.ATTR_FIRE);
		L1Skill l1skills = SkillTable.getInstance().findBySkillId(FIRE_WALL);
		dmg = (int) ((1.0 - attrDeffence) * l1skills.getDamageValue());

		if (_targetNpc.hasSkillEffect(ICE_LANCE)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(FREEZING_BLIZZARD)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(FREEZING_BREATH)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(EARTH_BIND)) {
			dmg = 0;
		}

		if (dmg < 0) {
			dmg = 0;
		}

		return dmg;
	}

	// ●●●● プレイヤー・ＮＰＣ から プレイヤー への魔法ダメージ算出 ●●●●
	private int calcPcMagicDamage(int skillId) {
		int dmg = 0;
		if (skillId == FINAL_BURN) {
			if (_calcType == PC_PC || _calcType == PC_NPC) {
				dmg = _pc.getCurrentMp();
			} else {
				dmg = _npc.getCurrentMp();
			}
		} else {
			dmg = calcMagicDiceDamage(skillId);
			dmg = (dmg * getLeverage()) / 10;
		}

		dmg -= _targetPc.getDamageReductionByArmor(); // 防具によるダメージ軽減

		// TODO マジックドール効果 　ダメージリダクション
		dmg -= L1MagicDoll.getDamageReductionByDoll(_targetPc);

		if (_targetPc.hasSkillEffect(COOKING_1_0_S) // 料理によるダメージ軽減
				|| _targetPc.hasSkillEffect(COOKING_1_1_S)
				|| _targetPc.hasSkillEffect(COOKING_1_2_S)
				|| _targetPc.hasSkillEffect(COOKING_1_3_S)
				|| _targetPc.hasSkillEffect(COOKING_1_4_S)
				|| _targetPc.hasSkillEffect(COOKING_1_5_S)
				|| _targetPc.hasSkillEffect(COOKING_1_6_S)
				|| _targetPc.hasSkillEffect(COOKING_2_0_S)
				|| _targetPc.hasSkillEffect(COOKING_2_1_S)
				|| _targetPc.hasSkillEffect(COOKING_2_2_S)
				|| _targetPc.hasSkillEffect(COOKING_2_3_S)
				|| _targetPc.hasSkillEffect(COOKING_2_4_S)
				|| _targetPc.hasSkillEffect(COOKING_2_5_S)
				|| _targetPc.hasSkillEffect(COOKING_2_6_S)
				|| _targetPc.hasSkillEffect(COOKING_3_0_S)
				|| _targetPc.hasSkillEffect(COOKING_3_1_S)
				|| _targetPc.hasSkillEffect(COOKING_3_2_S)
				|| _targetPc.hasSkillEffect(COOKING_3_3_S)
				|| _targetPc.hasSkillEffect(COOKING_3_4_S)
				|| _targetPc.hasSkillEffect(COOKING_3_5_S)
				|| _targetPc.hasSkillEffect(COOKING_3_6_S)) {
			dmg -= 5;
		}
		if (_targetPc.hasSkillEffect(COOKING_1_7_S) // デザートによるダメージ軽減
				|| _targetPc.hasSkillEffect(COOKING_2_7_S)
				|| _targetPc.hasSkillEffect(COOKING_3_7_S)) {
			dmg -= 5;
		}

		if (_targetPc.hasSkillEffect(REDUCTION_ARMOR)) {
			int targetPcLv = _targetPc.getLevel();
			if (targetPcLv < 70) {
				targetPcLv = 70;
			}
			dmg -= (int)((targetPcLv - 70) / 4) * 3 + 3;
		}
		if (_targetPc.hasSkillEffect(DRAGON_SKIN)) {
			int targetPcLv = _targetPc.getLevel();
			if (targetPcLv < 70) {
				targetPcLv = 70;
			}
			dmg -= (int)((targetPcLv - 70) / 5) * 2 + 2;
		}

		if (_targetPc.hasSkillEffect(PATIENCE)) {
			dmg -= 2;
		}

		if (_calcType == NPC_PC) { // ペット、サモンからプレイヤーに攻撃
			boolean isNowWar = false;
			int castleId = L1CastleLocation.getCastleIdByArea(_targetPc);
			if (castleId > 0) {
				isNowWar = WarTimeController.getInstance().isNowWar(castleId);
			}
			if (!isNowWar) {
				if (_npc instanceof L1PetInstance) {
					dmg /= 8;
				}
				if (_npc instanceof L1SummonInstance) {
					L1SummonInstance summon = (L1SummonInstance) _npc;
					if (summon.isExsistMaster()) {
						dmg /= 8;
					}
				}
			}
		}

		if (_targetPc.hasSkillEffect(IMMUNE_TO_HARM)) {
			dmg /= 2;
		}
		if (_targetPc.hasSkillEffect(ABSOLUTE_BARRIER)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(ICE_LANCE)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(FREEZING_BLIZZARD)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(FREEZING_BREATH)) {
			dmg = 0;
		}
		if (_targetPc.hasSkillEffect(EARTH_BIND)) {
			dmg = 0;
		}

		if (_targetPc.hasSkillEffect(COUNTER_MIRROR)) {
			if (_calcType == PC_PC) {
				if (_targetPc.getWis() >= _random.nextInt(100)) {
					_pc.sendPackets(new S_DoActionGFX(_pc.getId(),
							ActionCodes.ACTION_Damage));
					_pc.broadcastPacket(new S_DoActionGFX(_pc.getId(),
							ActionCodes.ACTION_Damage));
					_targetPc.sendPackets(new S_SkillSound(_targetPc.getId(),
							4395));
					_targetPc.broadcastPacket(new S_SkillSound(_targetPc
							.getId(), 4395));
					_pc.receiveDamage(_targetPc, dmg, false);
					dmg = 0;
					_targetPc.killSkillEffectTimer(COUNTER_MIRROR);
				}
			} else if (_calcType == NPC_PC) {
				int npcId = _npc.getNpcTemplate().get_npcId();
				if (npcId == 45681 || npcId == 45682 || npcId == 45683
						|| npcId == 45684) {
				} else if (!_npc.getNpcTemplate().get_IsErase()) {
				} else {
					if (_targetPc.getWis() >= _random.nextInt(100)) {
						_npc.broadcastPacket(new S_DoActionGFX(_npc.getId(),
								ActionCodes.ACTION_Damage));
						_targetPc.sendPackets(new S_SkillSound(_targetPc
								.getId(), 4395));
						_targetPc.broadcastPacket(new S_SkillSound(_targetPc
								.getId(), 4395));
						_npc.receiveDamage(_targetPc, dmg);
						dmg = 0;
						_targetPc.killSkillEffectTimer(COUNTER_MIRROR);
					}
				}
			}
		}

		if (dmg < 0) {
			dmg = 0;
		}
		return dmg;
	}

	// ●●●● プレイヤー・ＮＰＣ から ＮＰＣ へのダメージ算出 ●●●●
	private int calcNpcMagicDamage(int skillId) {
		int dmg = 0;
		if (skillId == FINAL_BURN) {
			if (_calcType == PC_PC || _calcType == PC_NPC) {
				dmg = _pc.getCurrentMp();
			} else {
				dmg = _npc.getCurrentMp();
			}
		} else {
			dmg = calcMagicDiceDamage(skillId);
			dmg = (dmg * getLeverage()) / 10;
		}

		if (_calcType == PC_NPC) { // プレイヤーからペット、サモンに攻撃
			boolean isNowWar = false;
			int castleId = L1CastleLocation.getCastleIdByArea(_targetNpc);
			if (castleId > 0) {
				isNowWar = WarTimeController.getInstance().isNowWar(castleId);
			}
			if (!isNowWar) {
				if (_targetNpc instanceof L1PetInstance) {
					dmg /= 8;
				}
				if (_targetNpc instanceof L1SummonInstance) {
					L1SummonInstance summon = (L1SummonInstance) _targetNpc;
					if (summon.isExsistMaster()) {
						dmg /= 8;
					}
				}
			}
		}

		if (_targetNpc.hasSkillEffect(ICE_LANCE)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(FREEZING_BLIZZARD)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(FREEZING_BREATH)) {
			dmg = 0;
		}
		if (_targetNpc.hasSkillEffect(EARTH_BIND)) {
			dmg = 0;
		}

		if (_calcType == PC_NPC && _targetNpc != null) {
			int npcId = _targetNpc.getNpcTemplate().get_npcId();
			if (npcId >= 45912 && npcId <= 45915 // 恨みに満ちたソルジャー＆ソルジャーゴースト
					&& !_pc.hasSkillEffect(STATUS_HOLY_WATER)) {
				dmg = 0;
			}
			if (npcId == 45916 // 恨みに満ちたハメル将軍
					&& !_pc.hasSkillEffect(STATUS_HOLY_MITHRIL_POWDER)) {
				dmg = 0;
			}
			if (npcId == 45941 // 呪われた巫女サエル
					&& !_pc.hasSkillEffect(STATUS_HOLY_WATER_OF_EVA)) {
				dmg = 0;
			}
			if (npcId == 45752 // バルログ(変身前)
					&& !_pc.hasSkillEffect(STATUS_CURSE_BARLOG)) {
				dmg = 0;
			}
			if (npcId == 45753 // バルログ(変身後)
					&& !_pc.hasSkillEffect(STATUS_CURSE_BARLOG)) {
				dmg = 0;
			}
			if (npcId == 45675 // ヤヒ(変身前)
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				dmg = 0;
			}
			if (npcId == 81082 // ヤヒ(変身後)
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				dmg = 0;
			}
			if (npcId == 45625 // 混沌
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				dmg = 0;
			}
			if (npcId == 45674 // 死
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				dmg = 0;
			}
			if (npcId == 45685 // 堕落
					&& !_pc.hasSkillEffect(STATUS_CURSE_YAHEE)) {
				dmg = 0;
			}
			if (npcId >= 46068 && npcId <= 46091 // 欲望の洞窟側mob
					&& _pc.getTempCharGfx() == 6035) {
				dmg = 0;
			}
			if (npcId >= 46092 && npcId <= 46106 // 影の神殿側mob
					&& _pc.getTempCharGfx() == 6034) {
				dmg = 0;
			}
		}

		return dmg;
	}

	// ●●●● damage_dice、damage_dice_count、damage_value、SPから魔法ダメージを算出 ●●●●
	private int calcMagicDiceDamage(int skillId) {
		L1Skill l1skills = SkillTable.getInstance().findBySkillId(skillId);
		int dice = l1skills.getDamageDice();
		int diceCount = l1skills.getDamageDiceCount();
		double value = l1skills.getDamageValue();
		int magicDamage = 0;
		int charaIntelligence = 0;

		for (int i = 0; i < diceCount; i++) {
			magicDamage += (_random.nextInt(dice) + 1);
		}
		magicDamage += value;

		if (_calcType == PC_PC || _calcType == PC_NPC) {
			int weaponAddDmg = 0; // 武器による追加ダメージ
			L1ItemInstance weapon = _pc.getWeapon();
			if (weapon != null) {
				weaponAddDmg = weapon.getItem().getMagicDmgModifier();
			}
			magicDamage += weaponAddDmg;
		}

		if (_calcType == PC_PC || _calcType == PC_NPC) {
			int spByItem = _pc.getSp() - _pc.getTrueSp(); // アイテムによるSP変動
			charaIntelligence = _pc.getInt() + spByItem - 12;
		} else if (_calcType == NPC_PC || _calcType == NPC_NPC) {
			int spByItem = _npc.getSp() - _npc.getTrueSp(); // アイテムによるSP変動
			charaIntelligence = _npc.getInt() + spByItem - 12;
		}
		if (charaIntelligence < 1) {
			charaIntelligence = 1;
		}

		double attrDeffence = calcAttrResistance(l1skills.getAttr());

		double coefficient = (1.0 - attrDeffence + charaIntelligence * 3.0 / 32.0);
		if (coefficient < 0) {
			coefficient = 0;
		}

		magicDamage *= coefficient;

		if (_calcType == PC_PC || _calcType == NPC_PC) { // 連続魔法ダメージ軽減
			long nowTime = System.currentTimeMillis();
			long oldTime = _targetPc.getOldTime();

			if (oldTime != 0) {
				long interval = nowTime - oldTime;
				int index = _targetPc.getNumberOfDamaged() - 1;

				if (2000 > interval) {
					double coefficient_r = 2.0 / 3.0;
					if (index == 0) {
						_targetPc.setOldTime(nowTime);
					}
					if (index > 8) {
						index = 8;
					}
					double coefficient_R = Math.pow(coefficient_r, index);

					magicDamage *= coefficient_R;
					if (index < 8) {
						_targetPc.addNumberOfDamaged(1);
					}
				} else {
					if (4000 > interval && index > 0) {
						_targetPc.setNumberOfDamaged(2);
						_targetPc.setOldTime(oldTime + 2000);
					} else {
						_targetPc.setNumberOfDamaged(1);
						_targetPc.setOldTime(nowTime);
					}
				}
			} else {
				_targetPc.addNumberOfDamaged(1);
				_targetPc.setOldTime(nowTime);
			}
		}

		double criticalCoefficient = 1.5; // 魔法クリティカル
		int rnd = _random.nextInt(100) + 1;
		if (_calcType == PC_PC || _calcType == PC_NPC) {
			if (l1skills.getSkillLevel() <= 6) {
				if (rnd <= (10 + _pc.getOriginalMagicCritical())) {
					magicDamage *= criticalCoefficient;
				}
			}
		}

		if (_calcType == PC_PC || _calcType == PC_NPC) { // オリジナルINTによる魔法ダメージ
			magicDamage += _pc.getOriginalMagicDamage();
		}
		if (_calcType == PC_PC || _calcType == PC_NPC) { // アバターによる追加ダメージ
			if (_pc.hasSkillEffect(ILLUSION_AVATAR)) {
				int level = _pc.getSkillEffectLevel(ILLUSION_AVATAR);
				magicDamage += (level > 70)? (level - 70) / 3 + 15 : 15;

			}
		}

		return magicDamage;
	}

	// ●●●● ヒール回復量（対アンデッドにはダメージ）を算出 ●●●●
	public int calcHealing(int skillId) {
		L1Skill l1skills = SkillTable.getInstance().findBySkillId(skillId);
		int dice = l1skills.getDamageDice();
		double value = l1skills.getDamageValue();
		int magicDamage = 0;

		int magicBonus = getMagicBonus();
		/* t.s 2011/12/27 mod start */
		/* MBの上限を変更 */
		// if (magicBonus > 10) {
		// magicBonus = 10;
		// }
		if (magicBonus > 11) {
			magicBonus = 11;
		}
		/* t.s 2011/12/27 mod start */

		int diceCount = (int) (value + magicBonus);
		for (int i = 0; i < diceCount; i++) {
			magicDamage += (_random.nextInt(dice) + 1);
		}

		double alignmentRevision = 1.0;
		if (getLawful() > 0) {
			alignmentRevision += (getLawful() / 32768.0);
		}

		magicDamage *= alignmentRevision;

		magicDamage = (magicDamage * getLeverage()) / 10;
		/* t.s 2011/12/27 add start */
		/* レベルによる回復量の増幅を追加（ (Lv/80)^2 ) */
		int attackLevel = 0;
		final int min_level = 70;
		if (_calcType == PC_PC || _calcType == PC_NPC) {
			attackLevel = _pc.getLevel();
		} else {
			attackLevel = _npc.getLevel();
		}
		magicDamage *= (attackLevel > min_level) ? attackLevel
				/ (double) min_level : 1;
		/* t.s 2011/12/27 add end */

		return magicDamage;
	}

	// ●●●● ＭＲによるダメージ軽減 ●●●●
	private int calcMrDefense(int dmg) {
		/* t.s 2011/09/25 mod start */
		// MRによる軽減を少なくする
		// int mr = getTargetMr();
		int mr = getTargetMr() > 160 ? 160 : getTargetMr();
		/* t.s 2011/09/25 mod end */

		double mrFloor = 0;
		if (_calcType == PC_PC || _calcType == PC_NPC) {
			if (mr <= 100) {
				mrFloor = Math.floor((mr - _pc.getOriginalMagicHit()) / 2);
			} else if (mr >= 100) {
				mrFloor = Math.floor((mr - _pc.getOriginalMagicHit()) / 10);
			}
			double mrCoefficient = 0;
			if (mr <= 100) {
				mrCoefficient = 1 - 0.01 * mrFloor;
			} else if (mr >= 100) {
				mrCoefficient = 0.6 - 0.01 * mrFloor;
			}
			dmg *= mrCoefficient;
		} else if (_calcType == NPC_PC || _calcType == NPC_NPC) {
			int rnd = _random.nextInt(100) + 1;
			if (mr >= rnd) {
				dmg /= 2;
			}
		}

		return dmg;
	}

	// ●●●● 属性によるダメージ軽減 ●●●●
	// attr:0.無属性魔法,1.地魔法,2.火魔法,4.水魔法,8.風魔法(,16.光魔法)
	private double calcAttrResistance(int attr) {
		int resist = 0;
		if (_calcType == PC_PC || _calcType == NPC_PC) {
			if (attr == L1Skill.ATTR_EARTH) {
				resist = _targetPc.getEarth();
			} else if (attr == L1Skill.ATTR_FIRE) {
				resist = _targetPc.getFire();
			} else if (attr == L1Skill.ATTR_WATER) {
				resist = _targetPc.getWater();
			} else if (attr == L1Skill.ATTR_WIND) {
				resist = _targetPc.getWind();
			}
		} else if (_calcType == PC_NPC || _calcType == NPC_NPC) {
		}

		int resistFloor = (int) (0.32 * Math.abs(resist));
		if (resist >= 0) {
			resistFloor *= 1;
		} else {
			resistFloor *= -1;
		}

		double attrDeffence = resistFloor / 32.0;

		return attrDeffence;
	}

	/* ■■■■■■■■■■■■■■■ 計算結果反映 ■■■■■■■■■■■■■■■ */

	public void commit(int damage, int drainMana) {
		if (_calcType == PC_PC || _calcType == NPC_PC) {
			commitPc(damage, drainMana);
		} else if (_calcType == PC_NPC || _calcType == NPC_NPC) {
			commitNpc(damage, drainMana);
		}

		// ダメージ値及び命中率確認用メッセージ
		if (!Config.ALT_ATKMSG) {
			return;
		}
		if (Config.ALT_ATKMSG) {
			if ((_calcType == PC_PC || _calcType == PC_NPC) && !_pc.isGm()) {
				return;
			}
			if ((_calcType == PC_PC || _calcType == NPC_PC)
					&& !_targetPc.isGm()) {
				return;
			}
		}

		String msg0 = "";
		String msg1 = "に";
		String msg2 = "";
		String msg3 = "";
		String msg4 = "";

		if (_calcType == PC_PC || _calcType == PC_NPC) {// アタッカーがＰＣの場合
			msg0 = _pc.getName();
		} else if (_calcType == NPC_PC) { // アタッカーがＮＰＣの場合
			msg0 = _npc.getName();
		}

		if (_calcType == NPC_PC || _calcType == PC_PC) { // ターゲットがＰＣの場合
			msg4 = _targetPc.getName();
			msg2 = "THP" + _targetPc.getCurrentHp();
		} else if (_calcType == PC_NPC) { // ターゲットがＮＰＣの場合
			msg4 = _targetNpc.getName();
			msg2 = "THp" + _targetNpc.getCurrentHp();
		}

		msg3 = damage + "与えた";

		if (_calcType == PC_PC || _calcType == PC_NPC) { // アタッカーがＰＣの場合
			_pc.sendPackets(new S_ServerMessage(166, msg0, msg1, msg2, msg3,
					msg4)); // \f1%0が%4%1%3 %2
		}
		if (_calcType == NPC_PC || _calcType == PC_PC) { // ターゲットがＰＣの場合
			_targetPc.sendPackets(new S_ServerMessage(166, msg0, msg1, msg2,
					msg3, msg4)); // \f1%0が%4%1%3 %2
		}
	}

	// ●●●● プレイヤーに計算結果を反映 ●●●●
	private void commitPc(int damage, int drainMana) {
		if (_calcType == PC_PC) {
			if (drainMana > 0 && _targetPc.getCurrentMp() > 0) {
				if (drainMana > _targetPc.getCurrentMp()) {
					drainMana = _targetPc.getCurrentMp();
				}
				int newMp = _pc.getCurrentMp() + drainMana;
				_pc.setCurrentMp(newMp);
			}
			_targetPc.receiveManaDamage(_pc, drainMana);
			_targetPc.receiveDamage(_pc, damage, true);
		} else if (_calcType == NPC_PC) {
			_targetPc.receiveDamage(_npc, damage, true);
		}
	}

	// ●●●● ＮＰＣに計算結果を反映 ●●●●
	private void commitNpc(int damage, int drainMana) {
		if (_calcType == PC_NPC) {
			if (drainMana > 0) {
				int drainValue = _targetNpc.drainMana(drainMana);
				int newMp = _pc.getCurrentMp() + drainValue;
				_pc.setCurrentMp(newMp);
			}
			_targetNpc.ReceiveManaDamage(_pc, drainMana);
			_targetNpc.receiveDamage(_pc, damage);
		} else if (_calcType == NPC_NPC) {
			_targetNpc.receiveDamage(_npc, damage);
		}
	}

	// MRに依存しない魔法ダメージ計算処理
	private int calcExceptionMagicDamage(int skillId, int dmg) {

		L1Skill l1skills = SkillTable.getInstance().findBySkillId(skillId);
		if (skillId == MIND_BREAK) {
			// マインドブレク sp * value
			int sp = getSpellPower();
			dmg = (int) (l1skills.getDamageValue() * sp);
			// MPを5減少させる
			if (_calcType == PC_PC || _calcType == NPC_PC) {
				_targetPc.setCurrentMp(_targetPc.getCurrentMp() - 5);
			} else if (_calcType == NPC_NPC || _calcType == PC_NPC) {
				_targetNpc.setCurrentMp(_targetNpc.getCurrentMp() - 5);
			}
		} else if (skillId == CONFUSION) {
			// コンフュージョン sp * value
			int sp = getSpellPower();
			dmg = (int) (l1skills.getDamageValue() * sp);
		} else if (skillId == JOY_OF_PAIN) {
			// ジョイオブペイン (MaxHp-currentHp)/5
			dmg = ((_pc.getMaxHp() - _pc.getCurrentHp()) / 5);
		}

		return dmg;
	}
}