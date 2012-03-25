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

package l1j.server.server.model.Instance;

import static l1j.server.server.model.skill.L1SkillId.BLESS_WEAPON;
import static l1j.server.server.model.skill.L1SkillId.ENCHANT_WEAPON;
import static l1j.server.server.model.skill.L1SkillId.HOLY_WEAPON;
import static l1j.server.server.model.skill.L1SkillId.SHADOW_FANG;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Timer;
import java.util.TimerTask;

import l1j.server.server.datatables.FurnitureSpawnTable;
import l1j.server.server.datatables.InnKeyTable;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.datatables.LetterTable;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.datatables.PetTable;
import l1j.server.server.datatables.RaceTicketTable;
import l1j.server.server.model.L1EquipmentTimer;
import l1j.server.server.model.L1ItemOwnerTimer;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.serverpackets.S_OwnCharStatus;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1Armor;
import l1j.server.server.templates.L1InventoryItem;
import l1j.server.server.templates.L1Item;
import l1j.server.server.templates.L1Npc;
import l1j.server.server.templates.L1Pet;
import l1j.server.server.utils.BinaryOutputStream;
import l1j.server.server.utils.IntRange;

// Referenced classes of package l1j.server.server.model:
// L1Object, L1PcInstance

public class L1ItemInstance extends L1Object
{
	private static final long serialVersionUID = 1L;

	private final L1InventoryItem _inventoryItem;

	private L1Item _item;

	private int _lastWeight;

	private L1PcInstance _pc;

	private boolean _isRunning = false;

	private EnchantTimer _timer;

	@Override
	public int getId()
	{
		return _inventoryItem.getId();
	}

	@Override
	public void setId(int id)
	{
		_inventoryItem.setId(id);
	}

	public L1ItemInstance()
	{
		_inventoryItem = new L1InventoryItem();
		setCount(1);
	}

	public L1ItemInstance(L1Item item, int count)
	{
		this();
		setItem(item);
		setCount(count);
	}

	public L1ItemInstance(L1InventoryItem item)
	{
		_inventoryItem = item;
		_item = (ItemTable.getInstance().getTemplate(item.getItemId()));
	}

	/**
	 * アイテムが確認(鑑定)済みであるかを返す。
	 *
	 * @return 確認済みならtrue、未確認ならfalse。
	 */
	public boolean isIdentified()
	{
		return _inventoryItem.isIdentified();
	}

	/**
	 * アイテムが確認(鑑定)済みであるかを設定する。
	 *
	 * @param identified
	 *            確認済みならtrue、未確認ならfalse。
	 */
	public void setIdentified(boolean identified)
	{
		_inventoryItem.setIdentified(identified);
	}

	/**
	 * @return true アイテムは封印済み<br>
	 *         false アイテムは通常
	 */
	public boolean isSealed()
	{
		return _inventoryItem.isSealed();
	}

	/**
	 * @param isSealed
	 *            true アイテムは封印済み<br>
	 *            false アイテムは通常
	 */
	public void setSealed(boolean isSealed)
	{
		_inventoryItem.setSealed(isSealed);
	}

	public String getName()
	{
		return _item.getName();
	}

	/**
	 * アイテムの個数を返す。
	 *
	 * @return アイテムの個数
	 */
	public int getCount()
	{
		return _inventoryItem.getItemCount();
	}

	/**
	 * アイテムの個数を設定する。
	 *
	 * @param count
	 *            アイテムの個数
	 */
	public void setCount(int count)
	{
		_inventoryItem.setItemCount(count);
	}

	/**
	 * アイテムが装備されているかを返す。
	 *
	 * @return アイテムが装備されていればtrue、装備されていなければfalse。
	 */
	public boolean isEquipped()
	{
		return _inventoryItem.isEquipped();
	}

	/**
	 * アイテムが装備されているかを設定する。
	 *
	 * @param equipped
	 *            アイテムが装備されていればtrue,装備されていなければfalse。
	 */
	public void setEquipped(boolean equipped)
	{
		_inventoryItem.setEquipped(equipped);
	}

	public L1Item getItem()
	{
		return _item;
	}

	public void setItem(L1Item item)
	{
		_item = item;
		_inventoryItem.setItemId(item.getItemId());
	}

	public int getItemId()
	{
		return _inventoryItem.getItemId();
	}

	public void setItemId(int itemId)
	{
		_inventoryItem.setItemId(itemId);
	}

	public boolean isStackable()
	{
		return _item.isStackable();
	}

	@Override
	public void onAction(L1PcInstance player)
	{
	}

	public int getEnchantLevel()
	{
		return _inventoryItem.getEnchantLevel();
	}

	public void setEnchantLevel(int enchantLevel)
	{
		_inventoryItem.setEnchantLevel(enchantLevel);
	}

	public int get_gfxid()
	{
		return _item.getGfxId();
	}

	public int getDurability()
	{
		return _inventoryItem.getDurability();
	}

	public int getChargeCount()
	{
		return _inventoryItem.getChargeCount();
	}

	public void setChargeCount(int chargeCount)
	{
		_inventoryItem.setChargeCount(chargeCount);
	}

	public int getRemainingTime()
	{
		return _inventoryItem.getRemainingTime();
	}

	public void setRemainingTime(int remainingTime)
	{
		_inventoryItem.setRemainingTime(remainingTime);
	}

	public void setLastUsed(Timestamp lastUsed)
	{
		_inventoryItem.setLastUsed(lastUsed);
	}

	public Timestamp getLastUsed()
	{
		return _inventoryItem.getLastUsed();
	}

	public int getLastWeight()
	{
		return _lastWeight;
	}

	public void setLastWeight(int weight)
	{
		_lastWeight = weight;
	}

	public int getStatusForPacket()
	{
		// 0:祝福 1:通常 2:呪い 3:未鑑定
		// 128:祝福&封印 129:&封印 130:呪い&封印 131:未鑑定&封印
		int bless = 0;
		if (_item.getItemId() <= 100000)
		{
			bless = 1;
		}
		else if (_item.getItemId() < 200000)
		{
			bless = 0;
		}
		else
		{
			bless = 2;
		}
		if (!isIdentified())
		{
			bless = 3;
		}
		if (isSealed())
		{
			bless += 128;
		}
		return bless;
	}

	public void setAttrEnchantKind(int attrEnchantKind)
	{
		_inventoryItem.setAttrEnchantKind(attrEnchantKind);
	}

	public int getAttrEnchantKind()
	{
		return _inventoryItem.getAttrEnchantKind();
	}

	public void setAttrEnchantLevel(int attrEnchantLevel)
	{
		_inventoryItem.setAttrEnchantLevel(attrEnchantLevel);
	}

	public int getAttrEnchantLevel()
	{
		return _inventoryItem.getAttrEnchantLevel();
	}

	public int getMr()
	{
		int mr = _item.get_mdef();
		if (getItemId() == 20011 || getItemId() == 20110
				|| getItemId() == 21108 || getItemId() == 120011)
		{
			mr += getEnchantLevel();
		}
		if (getItemId() == 20056 || getItemId() == 120056
				|| getItemId() == 220056)
		{
			mr += getEnchantLevel() * 2;
		}
		return mr;
	}

	/*
	 * 耐久性、0~127まで -の値は許可しない。
	 */
	public void setDurability(int durability)
	{
		_inventoryItem.setDurability(IntRange.ensure(durability, 0, 127));
	}

	public int getWeight()
	{
		if (getItem().getWeight() == 0)
		{
			return 0;
		}
		else
		{
			return Math.max(getCount() * getItem().getWeight() / 1000, 1);
		}
	}

	/**
	 * 鞄や倉庫で表示される形式の名前を個数を指定して取得する。<br>
	 */
	public String getNumberedViewName(int count)
	{
		StringBuilder name = new StringBuilder(getNumberedName(count));
		int itemType2 = getItem().getType2();
		int itemId = getItem().getItemId();

		if (itemId == 40314 || itemId == 40316)
		{ // ペットのアミュレット
			L1Pet pet = PetTable.getInstance().getTemplate(getId());
			if (pet != null)
			{
				L1Npc npc = NpcTable.getInstance().getTemplate(pet.getNpcId());
				// name.append("[Lv." + pet.get_level() + " "
				// + npc.get_nameid() + "]");
				name.append("[Lv." + pet.getLevel() + " " + pet.getName()
						+ "]HP" + pet.getHp() + " " + npc.get_nameid());
			}
		}

		if (getItem().getType2() == 0 && getItem().getType() == 2)
		{ // light系アイテム
			if (isNowLighting())
			{
				name.append(" ($10)");
			}
			if (itemId == 40001 || itemId == 40002)
			{ // ランプorランタン
				if (getRemainingTime() <= 0)
				{
					name.append(" ($11)");
				}
			}
		}

		if (isEquipped())
		{
			if (itemType2 == 1)
			{
				name.append(" ($9)"); // 装備(Armed)
			}
			else if (itemType2 == 2)
			{
				name.append(" ($117)"); // 装備(Worn)
			}
			else if (itemType2 == 0 && getItem().getType() == 11)
			{ // petitem
				name.append(" ($117)"); // 装備(Worn)
			}
		}
		return name.toString();
	}

	/**
	 * 鞄や倉庫で表示される形式の名前を返す。<br>
	 * 例:+10 カタナ (装備)
	 */
	public String getViewName()
	{
		return getNumberedViewName(getCount());
	}

	/**
	 * ログに表示される形式の名前を返す。<br>
	 * 例:アデナ(250) / +6 ダガー
	 */
	public String getLogName()
	{
		return getNumberedName(getCount());
	}

	/* t.s 2011/09/23 add start */
	/**
	 * 個数を指定してログに表示される形式の名前を返す <br>
	 */
	public String getLogName(int count)
	{
		return getNumberedName(count);
	}

	/* t.s 2011/09/23 add end */

	/**
	 * ログに表示される形式の名前を、個数を指定して取得する。
	 */
	public String getNumberedName(int count)
	{
		StringBuilder name = new StringBuilder();

		if (isIdentified())
		{
			if (getItem().getType2() == 1)
			{ // 武器
				int attrEnchantLevel = getAttrEnchantLevel();
				if (attrEnchantLevel > 0)
				{
					String attrStr = null;
					switch (getAttrEnchantKind())
					{
					case 1: // 地
						if (attrEnchantLevel == 1)
						{
							attrStr = "$6124";
						}
						else if (attrEnchantLevel == 2)
						{
							attrStr = "$6125";
						}
						else if (attrEnchantLevel == 3)
						{
							attrStr = "$6126";
						}
						else if (attrEnchantLevel == 4)
						{
							attrStr = "$6141";
						}
						else if (attrEnchantLevel == 5)
						{
							attrStr = "$6142";
						}
						break;
					case 2: // 火
						if (attrEnchantLevel == 1)
						{
							attrStr = "$6115";
						}
						else if (attrEnchantLevel == 2)
						{
							attrStr = "$6116";
						}
						else if (attrEnchantLevel == 3)
						{
							attrStr = "$6117";
						}
						else if (attrEnchantLevel == 4)
						{
							attrStr = "$6135";
						}
						else if (attrEnchantLevel == 5)
						{
							attrStr = "$6136";
						}
						break;
					case 4: // 水
						if (attrEnchantLevel == 1)
						{
							attrStr = "$6118";
						}
						else if (attrEnchantLevel == 2)
						{
							attrStr = "$6119";
						}
						else if (attrEnchantLevel == 3)
						{
							attrStr = "$6120";
						}
						else if (attrEnchantLevel == 4)
						{
							attrStr = "$6137";
						}
						else if (attrEnchantLevel == 5)
						{
							attrStr = "$6138";
						}
						break;
					case 8: // 風
						if (attrEnchantLevel == 1)
						{
							attrStr = "$6121";
						}
						else if (attrEnchantLevel == 2)
						{
							attrStr = "$6122";
						}
						else if (attrEnchantLevel == 3)
						{
							attrStr = "$6123";
						}
						else if (attrEnchantLevel == 4)
						{
							attrStr = "$6139";
						}
						else if (attrEnchantLevel == 5)
						{
							attrStr = "$6140";
						}
						break;
					case 16: // 光
						if (attrEnchantLevel == 1)
						{
							attrStr = "$6143";
						}
						else if (attrEnchantLevel == 2)
						{
							attrStr = "$6144";
						}
						else if (attrEnchantLevel == 3)
						{
							attrStr = "$6145";
						}
						else if (attrEnchantLevel == 4)
						{
							attrStr = "$6146";
						}
						else if (attrEnchantLevel == 5)
						{
							attrStr = "$6147";
						}
						break;
					case 32: // 闇
						if (attrEnchantLevel == 1)
						{
							attrStr = "$6148";
						}
						else if (attrEnchantLevel == 2)
						{
							attrStr = "$6149";
						}
						else if (attrEnchantLevel == 3)
						{
							attrStr = "$6150";
						}
						else if (attrEnchantLevel == 4)
						{
							attrStr = "$6151";
						}
						else if (attrEnchantLevel == 5)
						{
							attrStr = "$6152";
						}
						break;
					default:
						break;
					}
					name.append(attrStr + " ");
				}
			}
			if (getItem().getType2() == 1 || getItem().getType2() == 2)
			{ // 武器・防具
				if (getEnchantLevel() >= 0)
				{
					name.append("+" + getEnchantLevel() + " ");
				}
				else if (getEnchantLevel() < 0)
				{
					name.append(String.valueOf(getEnchantLevel()) + " ");
				}
			}
		}
		if (isIdentified())
		{
			name.append(_item.getIdentifiedNameId());
		}
		else
		{
			name.append(_item.getUnidentifiedNameId());
		}
		if (isIdentified())
		{
			if (getItem().getMaxChargeCount() > 0)
			{
				name.append(" (" + getChargeCount() + ")");
			}
			if (getItem().getItemId() == 20383)
			{ // 騎馬用ヘルム
				name.append(" (" + getChargeCount() + ")");
			}
			if (getItem().getMaxUseTime() > 0 && getItem().getType2() != 0)
			{ // 武器防具で使用時間制限あり
				name.append(" (" + getRemainingTime() + ")");
			}
		}

		if (count > 1)
		{
			name.append(" (" + count + ")");
		}

		return name.toString();
	}

	// 宿屋のキー名称
	public String getInnKeyName()
	{
		StringBuilder name = new StringBuilder();
		name.append(" #");
		String chatText = String.valueOf(getKeyId());
		String s1 = "";
		String s2 = "";
		for (int i = 0; i < chatText.length(); i++)
		{
			if (i >= 5)
			{
				break;
			}
			s1 = s1 + String.valueOf(chatText.charAt(i));
		}
		name.append(s1);
		for (int i = 0; i < chatText.length(); i++)
		{
			if ((i % 2) == 0)
			{
				s1 = String.valueOf(chatText.charAt(i));
			}
			else
			{
				s2 = s1 + String.valueOf(chatText.charAt(i));
				name.append(Integer.toHexString(Integer.valueOf(s2))
						.toLowerCase()); // 16進数
			}
		}
		return name.toString();
	}

	/**
	 * アイテムの状態からサーバーパケットで利用する形式のバイト列を生成し、返す。
	 */
	public byte[] getStatusBytes()
	{
		int itemType2 = getItem().getType2();
		int itemId = getItemId();
		BinaryOutputStream os = new BinaryOutputStream();

		if (itemType2 == 0)
		{ // etcitem
			switch (getItem().getType())
			{
			case 2: // light
				os.writeC(22); // 明るさ
				os.writeH(getItem().getLightRange());
				break;
			case 7: // food
				os.writeC(21);
				// 栄養
				os.writeH(getItem().getFoodVolume());
				break;
			case 0: // arrow
			case 15: // sting
				os.writeC(1); // 打撃値
				os.writeC(getItem().getDmgSmall());
				os.writeC(getItem().getDmgLarge());
				break;
			case 11: // 使用可能ペット：[ハイペット]
				os.writeC(7);
				os.writeC(128);
				os.writeC(23); // 材質
				break;
			default:
				os.writeC(23); // 材質
				break;
			}
			os.writeC(getItem().getMaterial());
			os.writeD(getWeight());

		}
		else if (itemType2 == 1 || itemType2 == 2)
		{ // weapon | armor
			if (itemType2 == 1)
			{ // weapon
				// 打撃値
				os.writeC(1);
				os.writeC(getItem().getDmgSmall());
				os.writeC(getItem().getDmgLarge());
				os.writeC(getItem().getMaterial());
				os.writeD(getWeight());
			}
			else if (itemType2 == 2)
			{ // armor
				// AC
				os.writeC(19);
				int ac = ((L1Armor) getItem()).get_ac();
				if (ac < 0)
				{
					ac = ac - ac - ac;
				}
				os.writeC(ac);
				os.writeC(getItem().getMaterial());
				if (getItem().getType2() == 2 && getItem().getType() >= 8
						&& getItem().getType() <= 12)
				{
					os.writeC(((L1Armor) getItem()).getGrade());
					// 装飾品グレード 　 0:上級 1:中級 2:下級 3:特
				}
				else
				{
					os.writeC(-1);
				}
				os.writeD(getWeight());
			}
			// 強化数
			if (getEnchantLevel() != 0)
			{
				os.writeC(2);
				os.writeC(getEnchantLevel());
			}
			// 損傷度
			if (getDurability() != 0)
			{
				os.writeC(3);
				os.writeC(getDurability());
			}
			// 両手武器
			if (getItem().isTwohandedWeapon())
			{
				os.writeC(4);
			}
			// 攻撃成功
			if (itemType2 == 1)
			{ // weapon
				if (getItem().getHitModifier() != 0)
				{
					os.writeC(5);
					os.writeC(getItem().getHitModifier());
				}
			}
			else if (itemType2 == 2)
			{ // armor
				if (getItem().getHitModifierByArmor() != 0)
				{
					os.writeC(5);
					os.writeC(getItem().getHitModifierByArmor());
				}
			}
			// 追加打撃
			if (itemType2 == 1)
			{ // weapon
				if (getItem().getDmgModifier() != 0)
				{
					os.writeC(6);
					os.writeC(getItem().getDmgModifier());
				}
			}
			else if (itemType2 == 2)
			{ // armor
				if (getItem().getDmgModifierByArmor() != 0)
				{
					os.writeC(6);
					os.writeC(getItem().getDmgModifierByArmor());
				}
			}
			// 使用可能
			int bit = 0;
			bit |= getItem().isUseRoyal() ? 1 : 0;
			bit |= getItem().isUseKnight() ? 2 : 0;
			bit |= getItem().isUseElf() ? 4 : 0;
			bit |= getItem().isUseMage() ? 8 : 0;
			bit |= getItem().isUseDarkelf() ? 16 : 0;
			bit |= getItem().isUseDragonknight() ? 32 : 0;
			bit |= getItem().isUseIllusionist() ? 64 : 0;
			// bit |= getItem().isUseHiPet() ? 64 : 0; // ハイペット
			os.writeC(7);
			os.writeC(bit);
			// 弓の命中率補正
			if (getItem().getBowHitModifierByArmor() != 0)
			{
				os.writeC(24);
				os.writeC(getItem().getBowHitModifierByArmor());
			}
			// 弓のダメージ補正
			if (getItem().getBowDmgModifierByArmor() != 0)
			{
				os.writeC(35);
				os.writeC(getItem().getBowDmgModifierByArmor());
			}
			// MP吸収
			if (itemId == 126 || itemId == 127)
			{ // マナスタッフ、鋼鉄のマナスタッフ
				os.writeC(16);
			}
			// HP吸収
			if (itemId == 262)
			{ // ディストラクション
				os.writeC(34);
			}
			// STR~CHA
			if (getItem().get_addstr() != 0)
			{
				os.writeC(8);
				os.writeC(getItem().get_addstr());
			}
			if (getItem().get_adddex() != 0)
			{
				os.writeC(9);
				os.writeC(getItem().get_adddex());
			}
			if (getItem().get_addcon() != 0)
			{
				os.writeC(10);
				os.writeC(getItem().get_addcon());
			}
			if (getItem().get_addwis() != 0)
			{
				os.writeC(11);
				os.writeC(getItem().get_addwis());
			}
			if (getItem().get_addint() != 0)
			{
				os.writeC(12);
				os.writeC(getItem().get_addint());
			}
			if (getItem().get_addcha() != 0)
			{
				os.writeC(13);
				os.writeC(getItem().get_addcha());
			}
			// HP, MP
			if (getItem().get_addhp() != 0)
			{
				os.writeC(14);
				os.writeH(getItem().get_addhp());
			}
			if (getItem().get_addmp() != 0)
			{
				os.writeC(32);
				os.writeC(getItem().get_addmp());
			}
			// MR
			if (getMr() != 0)
			{
				os.writeC(15);
				os.writeH(getMr());
			}
			// SP(魔力)
			if (getItem().get_addsp() != 0)
			{
				os.writeC(17);
				os.writeC(getItem().get_addsp());
			}
			// ヘイスト
			if (getItem().isHasteItem())
			{
				os.writeC(18);
			}
			// 火の属性
			if (getItem().get_defense_fire() != 0)
			{
				os.writeC(27);
				os.writeC(getItem().get_defense_fire());
			}
			// 水の属性
			if (getItem().get_defense_water() != 0)
			{
				os.writeC(28);
				os.writeC(getItem().get_defense_water());
			}
			// 風の属性
			if (getItem().get_defense_wind() != 0)
			{
				os.writeC(29);
				os.writeC(getItem().get_defense_wind());
			}
			// 地の属性
			if (getItem().get_defense_earth() != 0)
			{
				os.writeC(30);
				os.writeC(getItem().get_defense_earth());
			}
			// 凍結耐性
			if (getItem().get_regist_freeze() != 0)
			{
				os.writeC(15);
				os.writeH(getItem().get_regist_freeze());
				os.writeC(33);
				os.writeC(1);
			}
			// 石化耐性
			if (getItem().get_regist_stone() != 0)
			{
				os.writeC(15);
				os.writeH(getItem().get_regist_stone());
				os.writeC(33);
				os.writeC(2);
			}
			// 睡眠耐性
			if (getItem().get_regist_sleep() != 0)
			{
				os.writeC(15);
				os.writeH(getItem().get_regist_sleep());
				os.writeC(33);
				os.writeC(3);
			}
			// 暗闇耐性
			if (getItem().get_regist_blind() != 0)
			{
				os.writeC(15);
				os.writeH(getItem().get_regist_blind());
				os.writeC(33);
				os.writeC(4);
			}
			// スタン耐性
			if (getItem().get_regist_stun() != 0)
			{
				os.writeC(15);
				os.writeH(getItem().get_regist_stun());
				os.writeC(33);
				os.writeC(5);
			}
			// ホールド耐性
			if (getItem().get_regist_sustain() != 0)
			{
				os.writeC(15);
				os.writeH(getItem().get_regist_sustain());
				os.writeC(33);
				os.writeC(6);
			}
			// 経験値ボーナス
			if (getItem().getExpBonus() != 0)
			{
				os.writeC(36);
				os.writeC(getItem().getExpBonus());
			}
			// HP自然回復
			if (getItem().get_addhpr() != 0)
			{
				os.writeC(37);
				os.writeC(getItem().get_addhpr());
			}
			// MP自然回復
			if (getItem().get_addmpr() != 0)
			{
				os.writeC(38);
				os.writeC(getItem().get_addmpr());
			}
			// 幸運
			// if (getItem.getLuck() != 0) {
			// os.writeC(20);
			// os.writeC(val);
			// }
			// 種類
			// if (getItem.getDesc() != 0) {
			// os.writeC(25);
			// os.writeH(val); // desc.tbl ID
			// }
			// レベル
			// if (getItem.getLevel() != 0) {
			// os.writeC(26);
			// os.writeH(val);
			// }
		}
		return os.getBytes();
	}

	class EnchantTimer extends TimerTask
	{

		public EnchantTimer()
		{
		}

		@Override
		public void run()
		{
			try
			{
				int type = getItem().getType();
				int type2 = getItem().getType2();
				int itemId = getItem().getItemId();
				if (_pc != null && _pc.getInventory().checkItem(itemId))
				{
					if (type == 2 && type2 == 2 && isEquipped())
					{
						_pc.addAc(3);
						_pc.sendPackets(new S_OwnCharStatus(_pc));
					}
				}
				setAcByMagic(0);
				setDmgByMagic(0);
				setHolyDmgByMagic(0);
				setHitByMagic(0);
				_pc.sendPackets(new S_ServerMessage(308, getLogName()));
				_isRunning = false;
				_timer = null;
			}
			catch (Exception e)
			{
			}
		}
	}

	private int _acByMagic = 0;

	public int getAcByMagic()
	{
		return _acByMagic;
	}

	public void setAcByMagic(int i)
	{
		_acByMagic = i;
	}

	private int _dmgByMagic = 0;

	public int getDmgByMagic()
	{
		return _dmgByMagic;
	}

	public void setDmgByMagic(int i)
	{
		_dmgByMagic = i;
	}

	private int _holyDmgByMagic = 0;

	public int getHolyDmgByMagic()
	{
		return _holyDmgByMagic;
	}

	public void setHolyDmgByMagic(int i)
	{
		_holyDmgByMagic = i;
	}

	private int _hitByMagic = 0;

	public int getHitByMagic()
	{
		return _hitByMagic;
	}

	public void setHitByMagic(int i)
	{
		_hitByMagic = i;
	}

	public void setSkillArmorEnchant(L1PcInstance pc, int skillId, int skillTime)
	{
		int type = getItem().getType();
		int type2 = getItem().getType2();
		if (_isRunning)
		{
			_timer.cancel();
			int itemId = getItem().getItemId();
			if (pc != null && pc.getInventory().checkItem(itemId))
			{
				if (type == 2 && type2 == 2 && isEquipped())
				{
					pc.addAc(3);
					pc.sendPackets(new S_OwnCharStatus(pc));
				}
			}
			setAcByMagic(0);
			_isRunning = false;
			_timer = null;
		}

		if (type == 2 && type2 == 2 && isEquipped())
		{
			pc.addAc(-3);
			pc.sendPackets(new S_OwnCharStatus(pc));
		}
		setAcByMagic(3);
		_pc = pc;
		_timer = new EnchantTimer();
		(new Timer()).schedule(_timer, skillTime);
		_isRunning = true;
	}

	public void setSkillWeaponEnchant(L1PcInstance pc, int skillId,
			int skillTime)
	{
		if (getItem().getType2() != 1)
		{
			return;
		}
		if (_isRunning)
		{
			_timer.cancel();
			setDmgByMagic(0);
			setHolyDmgByMagic(0);
			setHitByMagic(0);
			_isRunning = false;
			_timer = null;
		}

		switch (skillId)
		{
		case HOLY_WEAPON:
			setHolyDmgByMagic(1);
			setHitByMagic(1);
			break;

		case ENCHANT_WEAPON:
			setDmgByMagic(2);
			break;

		case BLESS_WEAPON:
			setDmgByMagic(2);
			setHitByMagic(2);
			break;

		case SHADOW_FANG:
			/* t.s 2011/12/24 add start */
			// setDmgByMagic(5);
			setDmgByMagic(8);
			/* t.s 2011/12/24 add end */
			break;

		default:
			break;
		}

		_pc = pc;
		_timer = new EnchantTimer();
		(new Timer()).schedule(_timer, skillTime);
		_isRunning = true;
	}

	private int _itemOwnerId = 0;

	public int getItemOwnerId()
	{
		return _itemOwnerId;
	}

	public void setItemOwnerId(int i)
	{
		_itemOwnerId = i;
	}

	public void startItemOwnerTimer(L1PcInstance pc)
	{
		setItemOwnerId(pc.getId());
		L1ItemOwnerTimer timer = new L1ItemOwnerTimer(this, 10000);
		timer.begin();
	}

	private L1EquipmentTimer _equipmentTimer;

	public void startEquipmentTimer(L1PcInstance pc)
	{
		if (getRemainingTime() > 0)
		{
			_equipmentTimer = new L1EquipmentTimer(pc, this);
			Timer timer = new Timer(true);
			timer.scheduleAtFixedRate(_equipmentTimer, 1000, 1000);
		}
	}

	public void stopEquipmentTimer(L1PcInstance pc)
	{
		if (getRemainingTime() > 0)
		{
			_equipmentTimer.cancel();
			_equipmentTimer = null;
		}
	}

	private boolean _isNowLighting = false;

	public boolean isNowLighting()
	{
		return _isNowLighting;
	}

	public void setNowLighting(boolean flag)
	{
		_isNowLighting = flag;
	}

	// 宿屋関連
	private int _keyId = 0;

	public int getKeyId()
	{
		return _keyId;
	}

	public void setKeyId(int i)
	{
		_keyId = i;
	}

	private int _innNpcId = 0;

	public int getInnNpcId()
	{
		return _innNpcId;
	}

	public void setInnNpcId(int i)
	{
		_innNpcId = i;
	}

	private boolean _isHall;

	public boolean checkRoomOrHall()
	{
		return _isHall;
	}

	public void setHall(boolean i)
	{
		_isHall = i;
	}

	private Timestamp _dueTime;

	public Timestamp getDueTime()
	{
		return _dueTime;
	}

	public void setDueTime(Timestamp i)
	{
		_dueTime = i;
	}

	public void save()
	{
		_inventoryItem.save();
	}

	public void save(Connection con)
	{
		_inventoryItem.save(con);
	}

	public void delete()
	{
		onDelete();
		_inventoryItem.delete();
	}

	public void delete(Connection con)
	{
		onDelete();
		_inventoryItem.delete(con);
	}

	public void setOwner(int ownerId, int location)
	{
		_inventoryItem.setOwnerId(ownerId);
		_inventoryItem.setLocation(location);
	}

	public int getOwnerId()
	{
		return _inventoryItem.getOwnerId();
	}

	public int getOwnerLocation()
	{
		return _inventoryItem.getLocation();
	}

	private void onDelete()
	{
		int itemId = getItem().getItemId();
		if (itemId == 40314 || itemId == 40316)
		{ // ペットのアミュレット
			PetTable.getInstance().deletePet(getId());
		}
		else if (itemId >= 49016 && itemId <= 49025)
		{ // 便箋
			LetterTable lettertable = new LetterTable();
			lettertable.deleteLetter(getId());
		}
		else if (itemId >= 41383 && itemId <= 41400)
		{ // 家具
			for (L1Object l1object : L1World.getInstance().getObject())
			{
				if (l1object instanceof L1FurnitureInstance)
				{
					L1FurnitureInstance furniture = (L1FurnitureInstance) l1object;
					if (furniture.getItemObjId() == getId())
					{ // 既に引き出している家具
						FurnitureSpawnTable.getInstance().deleteFurniture(
								furniture);
					}
				}
			}
		}
		else if (getItemId() == 40309)
		{// レースチケット
			RaceTicketTable.getInstance().deleteTicket(getId());
		}
		else if (getItem().getItemId() == 40312)
		{// 宿屋のキー記録
			InnKeyTable.deleteKey(this);
		}
	}
}