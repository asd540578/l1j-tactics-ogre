package l1j.server.server.to.skill;

import static l1j.server.server.model.skill.L1SkillId.ABSOLUTE_BARRIER;
import static l1j.server.server.model.skill.L1SkillId.COUNTER_MAGIC;
import static l1j.server.server.model.skill.L1SkillId.EARTH_BIND;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BLIZZARD;
import static l1j.server.server.model.skill.L1SkillId.FREEZING_BREATH;
import static l1j.server.server.model.skill.L1SkillId.ICE_LANCE;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import l1j.server.server.ActionCodes;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.datatables.SkillEffectTable;
import l1j.server.server.datatables.SkillTable;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1Location;
import l1j.server.server.model.L1Magic;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1DoorInstance;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.Instance.L1PetInstance;
import l1j.server.server.model.Instance.L1SummonInstance;
import l1j.server.server.model.Instance.L1TowerInstance;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_EffectLocation;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1MobSkill;
import l1j.server.server.templates.L1Skill;
import l1j.server.server.templates.L1SkillEffect;
import l1j.server.server.types.Point;

// 特殊技クラスのベースとなるクラス
// ①コンストラクタ
// ②runSkill()
// ③スレッドプール経由のrun()
// の順で実行される。
public abstract class SkillEffect implements Runnable
{
	private static Logger _log = Logger.getLogger(SkillEffect.class.getName());

	// ランダム値用
	protected static final Random dice = new Random(System.currentTimeMillis());

	// クラス変数の定義
	protected L1Character _attacker = null;
	protected L1Character _target = null;
	protected L1Character _center = null;
	protected L1SkillEffect _effect = null;
	protected L1Skill _skill = null;
	protected L1MobSkill _mobskill = null;

	// コンストラクターの定義
	// protectedにしていたがリフレクションで
	// getConstructorで取得できなくなったため
	// publicに変更
	public SkillEffect(L1Character attacker, L1Character target,
			L1SkillEffect effect)
	{
		this._attacker = attacker; // pcInstanceをクラス変数に代入してメゾットから共有使用可能にする
		this._target = target;
		this._center = (target != null) ? target : attacker;
		this._effect = effect;
		this._skill = SkillTable.getInstance().findBySkillId(_effect.getId());

		beforeUse();

		runSkill(); // スキルの実態はこのメソットに書いてあるため呼び出す
	}

	// スキル使用前の処理
	protected boolean beforeUse()
	{
		if (_attacker instanceof L1PcInstance)
		{
			L1PcInstance user = (L1PcInstance) _attacker;
			// 消費MP処理
			if (user.getCurrentMp() < _skill.getMpConsume())
			{
				// MPが足りない
				user.sendPackets(new S_SystemMessage("MPが足りません。(消費MP:"
						+ _skill.getMpConsume() + ")"));
				return false;
			}
			else
			{
				// MPを消費する
				user.setCurrentMp(user.getCurrentMp() - _skill.getMpConsume());
			}

			// 消費Hp処理
			if (user.getCurrentHp() < _skill.getHpConsume())
			{
				// HPが足りない
				user.sendPackets(new S_SystemMessage("Hpが足りません。(消費Hp:"
						+ _skill.getHpConsume() + ")"));
				return false;
			}
			else
			{
				// HPを消費する
				user.setCurrentHp(user.getCurrentHp() - _skill.getHpConsume());
			}
		}
		if (_attacker instanceof L1MonsterInstance)
		{
			L1MonsterInstance user = (L1MonsterInstance) _attacker;
			// 消費MP処理
			if (user.getCurrentMp() < _skill.getMpConsume())
			{
				// ＭＰが足りないため終了
				return false;
			}
			else
			{
				// MPを消費する
				user.setCurrentMp(user.getCurrentMp() - _skill.getMpConsume());
			}
			// 消費HP処理
			if (user.getCurrentHp() < _skill.getHpConsume())
			{
				// HPが足りないため終了
				return false;
			}
			else
			{
				// HPを消費する
				user.setCurrentHp(user.getCurrentHp() - _skill.getHpConsume());
			}
		}
		return true;
	}

	// スキルの実体(戻り値・引数なし)
	protected final void runSkill()
	{
		// スレッドプールへ投げてスレッド管理してもらう。
		GeneralThreadPool.getInstance().execute(this);
	}

	// タイプに応じてエフェクトを作成する
	// pcの想定値はL1PcInstanceかL1MonsterInstance
	public final static void createSkillEffect(L1Character user,
			L1Character target, int id, int type)
	{
		// idとtypeから該当するエフェクトを検索
		L1SkillEffect effect = SkillEffectTable.getInstance().findSkillEffect(
				id, type);

		if (null != effect)
		{
			try
			{
				// クラス名からインスタンスを生成する
				// インスタンス生成後、自動でスレッドから実行される
				Class cls = Class.forName("l1j.server.server.to.skill."
						+ effect.getPattern());
				Class[] types =
				{
						L1Character.class, L1Character.class,
						L1SkillEffect.class
				};
				Constructor<SkillEffect> constructor = null;
				try
				{
					constructor = cls.getConstructor(types);
				}
				catch (SecurityException e)
				{
					throw new RuntimeException(e);
				}
				catch (NoSuchMethodException e)
				{
					throw new RuntimeException(e);
				}

				Object[] args =
				{
						user, target, effect
				};
				Object obj = null;
				try
				{
					obj = constructor.newInstance(args);
				}
				catch (IllegalArgumentException e)
				{
					throw new RuntimeException(e);
				}
				catch (InstantiationException e)
				{
					throw new RuntimeException(e);
				}
				catch (IllegalAccessException e)
				{
					throw new RuntimeException(e);
				}
				catch (InvocationTargetException e)
				{
					throw new RuntimeException(e);
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	// タイプに応じてエフェクトを作成する
	// pcの想定値はL1PcInstanceかL1MonsterInstance
	public final static void createSkillEffect(L1Character user, int id,
			int type)
	{
		createSkillEffect(user, null, id, type);
	}

	/**
	 *
	 * @param x
	 * @param y
	 */
	protected void sendEffect(int x, int y)
	{
		// S_EffectLocationを型だけ定義 このクラスで指定地点にエフェクトを作成する。
		S_EffectLocation packet = null;

		// 毎回読まずに保持しておいてもいいかも？
		Integer[] effects = new Integer[]
		{
				_effect.getEffect_1(), _effect.getEffect_2(),
				_effect.getEffect_3(), _effect.getEffect_4()
		};

		for (int index = 0; index < effects.length; ++index)
		{
			if (null != effects[index])
			{
				// wideBroadcastPacketにすると50セル範囲のpcへエフェクトを知らせることができる
				packet = new S_EffectLocation(x, y, effects[index]);
				if (_attacker instanceof L1PcInstance)
				{
					L1PcInstance pc = (L1PcInstance) _attacker;
					pc.sendPackets(packet);
					pc.wideBroadcastPacket(packet);
				}
				else if (_attacker instanceof L1MonsterInstance)
				{
					L1MonsterInstance mo = (L1MonsterInstance) _attacker;
					mo.wideBroadcastPacket(packet);
				}
				else if (_attacker instanceof L1SummonInstance)
				{
					L1SummonInstance su = (L1SummonInstance) _attacker;
					su.wideBroadcastPacket(packet);
				}
				else if (_attacker instanceof L1PetInstance)
				{
					L1PetInstance pet = (L1PetInstance) _attacker;
					pet.wideBroadcastPacket(packet);
				}
			}
		}
	}

	/**
	 * 【ダメージ処理】 ある地点を中心として範囲オブジェクトを探索し、 ダメージとモーションをセットする
	 *
	 * @param next_loc
	 */
	protected final void damageEffect(L1Location next_loc)
	{
		for (L1Object obj : L1World.getInstance().getVisiblePoint(next_loc,
				_skill.getArea()))
		{
			// ダメージ計算用
			int damage = 0;
			L1Magic magic = null;

			// カンタマ判定
			if (obj instanceof L1Character)
			{
				L1Character judgement_target = (L1Character) obj;
				if (isUseCounterMagic(judgement_target) == true)
					return;
				if (isEffective(judgement_target) == false)
					return;

				// ダメージ計算
				try
				{
					magic = new L1Magic(_attacker, judgement_target);
					// ほんとはSetLeverageしないといけなかった...
					damage = magic.calcMagicDamage(_skill.getSkillId());
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			/* 使用者：プレイヤー */
			if (_attacker instanceof L1PcInstance)
			{
				L1PcInstance attacker = (L1PcInstance) _attacker;
				// ターゲット：プレイヤー
				if (obj instanceof L1PcInstance)
				{
					L1PcInstance target = (L1PcInstance) obj;
					if ((attacker.getClanid() != target.getClanid())
							|| (attacker.getClanid() == 0 || target.getClanid() == 0)
							&& (attacker != target))
					{
						// 被ダメモーション
						if (damage > 0)
						{
							target.sendPackets(new S_DoActionGFX(
									target.getId(), ActionCodes.ACTION_Damage));
							target.broadcastPacket(new S_DoActionGFX(target
									.getId(), ActionCodes.ACTION_Damage));
						}
						magic.commit(damage, 0);
					}
				}
				// ターゲット：モンスター
				else if (obj instanceof L1MonsterInstance)
				{
					L1MonsterInstance target = (L1MonsterInstance) obj;
					// 被ダメモーション
					if (damage > 0)
					{
						target.broadcastPacket(new S_DoActionGFX(
								target.getId(), ActionCodes.ACTION_Damage));
					}
					magic.commit(damage, 0);
				}
			}

			/* 使用者：モンスター */
			if (_attacker instanceof L1MonsterInstance)
			{
				L1MonsterInstance attacker = (L1MonsterInstance) _attacker;
				// ターゲット：プレイヤー
				if (obj instanceof L1PcInstance)
				{
					L1PcInstance target = (L1PcInstance) obj;
					// 被ダメモーション
					if (damage > 0)
					{
						target.sendPackets(new S_DoActionGFX(target.getId(),
								ActionCodes.ACTION_Damage));
						target.broadcastPacket(new S_DoActionGFX(
								target.getId(), ActionCodes.ACTION_Damage));
					}
					magic.commit(damage, 0);
				}

				if (obj instanceof L1SummonInstance)
				{
					L1SummonInstance target = (L1SummonInstance) obj;
					// 被ダメモーション
					if (damage > 0)
					{
						target.broadcastPacket(new S_DoActionGFX(
								target.getId(), ActionCodes.ACTION_Damage));
					}
					magic.commit(damage, 0);
				}

				if (obj instanceof L1PetInstance)
				{
					L1PetInstance target = (L1PetInstance) obj;

					if (damage > 0)
					{
						target.broadcastPacket(new S_DoActionGFX(
								target.getId(), ActionCodes.ACTION_Damage));
					}
					magic.commit(damage, 0);
				}
			}

			/* 使用者：サモン */
			if (_attacker instanceof L1SummonInstance)
			{
				final L1SummonInstance attacker = (L1SummonInstance) _attacker;
				final L1Character master = attacker.getMaster();
				// ターゲット：プレイヤー
				if (obj instanceof L1PcInstance)
				{
					final L1PcInstance target = (L1PcInstance) obj;
					if (target != master)
					{
						boolean bValidTarget = true;
						if (master instanceof L1PcInstance)
						{
							bValidTarget = (target.getClanid() != ((L1PcInstance) master)
									.getClanid());
						}
						// 被ダメモーション
						if (damage > 0 && bValidTarget)
						{
							target.sendPackets(new S_DoActionGFX(
									target.getId(), ActionCodes.ACTION_Damage));
							target.broadcastPacket(new S_DoActionGFX(target
									.getId(), ActionCodes.ACTION_Damage));
						}
						magic.commit(damage, 0);
					}
				}
				// ターゲット：モンスター
				else if (obj instanceof L1MonsterInstance)
				{
					L1MonsterInstance target = (L1MonsterInstance) obj;
					// 被ダメモーション
					if (damage > 0)
					{
						target.broadcastPacket(new S_DoActionGFX(
								target.getId(), ActionCodes.ACTION_Damage));
					}
					magic.commit(damage, 0);
				}
			}
		}

		// スキルの表示間隔 Thread.sleepでスレッドを指定時間停止できる(重要)
		try
		{
			Thread.sleep(_effect.getSleepTime());
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	// ある地点からある地点までの直線距離を返す
	protected static final ConcurrentHashMap<Integer, String> createLineMap(
			Point src, Point target)
	{
		ConcurrentHashMap<Integer, String> lineMap = new ConcurrentHashMap<Integer, String>();

		int E;
		int x;
		int y;
		int i;
		int x0 = src.getX();
		int y0 = src.getY();
		int x1 = target.getX();
		int y1 = target.getY();
		int sx = (x1 > x0) ? 1 : -1;
		int dx = (x1 > x0) ? x1 - x0 : x0 - x1;
		int sy = (y1 > y0) ? 1 : -1;
		int dy = (y1 > y0) ? y1 - y0 : y0 - y1;

		int k = 0;
		x = x0;
		y = y0;
		/* 傾きが1以下の場合 */
		if (dx >= dy)
		{
			E = -dx;
			for (i = 0; i <= dx; i++)
			{
				lineMap.put(k, String.valueOf(x + "," + y));
				x += sx;
				E += 2 * dy;
				if (E >= 0)
				{
					y += sy;
					E -= 2 * dx;
				}
				k++;
			}
			return lineMap;
			/* 傾きが1より大きい場合 */
		}
		else
		{
			E = -dy;
			for (i = 0; i <= dy; i++)
			{
				lineMap.put(k, String.valueOf(x + "," + y));
				y += sy;
				E += 2 * dx;
				if (E >= 0)
				{
					x += sx;
					E -= 2 * dy;
				}
				k++;
			}
			return lineMap;
		}
	}

	// カウンターマジックが発動したか返す
	private boolean isUseCounterMagic(L1Character cha)
	{
		// カウンターマジック有効なスキルでカウンターマジック中
		if (!_skill.ignoresCounterMagic() && cha.hasSkillEffect(COUNTER_MAGIC))
		{
			cha.removeSkillEffect(COUNTER_MAGIC);
			int castgfx = SkillTable.getInstance().findBySkillId(COUNTER_MAGIC)
					.getCastGfx();
			cha.broadcastPacket(new S_SkillSound(cha.getId(), castgfx));
			if (cha instanceof L1PcInstance)
			{
				L1PcInstance pc = (L1PcInstance) cha;
				pc.sendPackets(new S_SkillSound(pc.getId(), castgfx));
			}
			return true;
		}
		return false;
	}

	private boolean isEffective(L1Character cha)
	{
		if (cha.hasSkillEffect(ICE_LANCE))
		{
			return false; // アイスランス中にアイスランス、フリージングブリザード、フリージングブレス
		}

		if (cha.hasSkillEffect(FREEZING_BLIZZARD))
		{
			return false; // フリージングブリザード中にアイスランス、フリージングブリザード、フリージングブレス
		}

		if (cha.hasSkillEffect(FREEZING_BREATH))
		{
			return false; // フリージングブレス中にアイスランス、フリージングブリザード、フリージングブレス
		}

		if (cha.hasSkillEffect(EARTH_BIND))
		{
			return false; // アース バインド中にアース バインド
		}

		if (cha.isDead())
		{
			return false; // ターゲットが死亡している
		}

		if ((cha instanceof L1TowerInstance || cha instanceof L1DoorInstance))
		{
			return false; // ターゲットがガーディアンタワー、ドア
		}

		if (cha.hasSkillEffect(ABSOLUTE_BARRIER))
		{
			return false;
		}

		return true;
	}
}
