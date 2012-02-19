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

import static l1j.server.server.ActionCodes.*;

import java.util.Arrays;
import java.util.concurrent.ScheduledFuture;

import l1j.server.server.GeneralThreadPool;
import l1j.server.server.IdFactory;
import l1j.server.server.model.L1World;
import l1j.server.server.random.RandomGenerator;
import l1j.server.server.random.RandomGeneratorFactory;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_DollPack;
import l1j.server.server.serverpackets.S_OwnCharStatus;
import l1j.server.server.serverpackets.S_SkillIconGFX;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.templates.L1MagicDoll;
import l1j.server.server.templates.L1Npc;

public class L1DollInstance extends L1NpcInstance {
	private static final long serialVersionUID = 1L;

	public static final int DOLL_TIME = 1800000;

	private static RandomGenerator _random = RandomGeneratorFactory.newRandom();
	private ScheduledFuture<?> _dollFuture;
	private int _itemId;
	private int _itemObjId;
	private boolean _isDelete = false;
	private static final int[] DollAction = { ACTION_Think, ACTION_Aggress,
			ACTION_Salute, ACTION_Cheer };
	private int sleeptime_PT = 10;

	// ターゲットがいない場合の處理
	@Override
	public boolean noTarget() {
		if (_master.isDead()) {
			deleteDoll();
			_isDelete = true;
			return true;
		} else if (_master != null && _master.getMapId() == getMapId()) {
			int dir = moveDirection(_master.getX(), _master.getY());
			if (dir == -1) {
				deleteDoll();
				return true;
			} else {
				if (getLocation().getTileLineDistance(_master.getLocation()) > 3) {
					setDirectionMove(dir);
					setSleepTime(calcSleepTime(getPassispeed(), MOVE_SPEED));
				} else {
					if (sleeptime_PT == 0) {
						broadcastPacket(new S_DoActionGFX(getId(),
								DollAction[_random.nextInt(2)]));
						sleeptime_PT = _random.nextInt(20) + 10;
					} else {
						--sleeptime_PT;
						setSleepTime(500);
					}
				}
			}
		} else {
			deleteDoll();
			_isDelete = true;
			return true;
		}
		return false;
	}

	// 時間計測用
	class DollTimer implements Runnable {
		@Override
		public void run() {
			if (_destroyed) { // 既に破棄されていないかチェック
				return;
			}
			deleteDoll();
		}
	}

	public L1DollInstance(L1Npc template, L1PcInstance master, int itemId,
			int itemObjId) {
		super(template);
		setId(IdFactory.getInstance().nextId());
		setItemId(itemId);
		setItemObjId(itemObjId);
		_dollFuture = GeneralThreadPool.getInstance().schedule(new DollTimer(),
				DOLL_TIME);

		setMaster(master);
		setX((_random.nextInt(5) + master.getX() - 2));
		setY((_random.nextInt(5) + master.getY() - 2));
		setMap(master.getMapId());
		setHeading(_random.nextInt(8));
		L1World.getInstance().storeObject(this);
		L1World.getInstance().addVisibleObject(this);
		for (L1PcInstance pc : L1World.getInstance().getRecognizePlayer(this)) {
			onPerceive(pc);
		}
		master.addDoll(this);
		if (!isAiRunning()) {
			startAI();
		}
		if (L1MagicDoll.isHpRegeneration(_master)) {
			master.startHpRegenerationByDoll();
		}
		if (L1MagicDoll.isMpRegeneration(_master)) {
			master.startMpRegenerationByDoll();
		}
		if (L1MagicDoll.isItemMake(_master)) {
			master.startItemMakeByDoll();
		}
	}

	public void deleteDoll() {
		broadcastPacket(new S_SkillSound(getId(), 5936));
		if (_master != null && _isDelete) {
			L1PcInstance pc = (L1PcInstance) _master;
			pc.sendPackets(new S_SkillIconGFX(56, 0));
			pc.sendPackets(new S_OwnCharStatus(pc));
		}
		if (L1MagicDoll.isHpRegeneration(_master)) {
			((L1PcInstance) _master).stopHpRegenerationByDoll();
		}
		if (L1MagicDoll.isMpRegeneration(_master)) {
			((L1PcInstance) _master).stopMpRegenerationByDoll();
		}
		if (L1MagicDoll.isItemMake(_master)) {
			((L1PcInstance) _master).stopItemMakeByDoll();
		}
		_master.getDollList().remove(getId());
		deleteMe();
	}

	@Override
	public void onPerceive(L1PcInstance perceivedFrom) {
		// 宿屋内判定
		if (perceivedFrom.getMapId() > 10000
				&& perceivedFrom.getInnKeyId() != _master.getInnKeyId()) {
			return;
		}
		perceivedFrom.addKnownObject(this);
		perceivedFrom.sendPackets(new S_DollPack(this, perceivedFrom));
	}

	@Override
	public void onItemUse() {
		if (!isActived()) {
			// １００％の確率でヘイストポーション使用
			useItem(USEITEM_HASTE, 100);
		}
	}

	@Override
	public void onGetItem(L1ItemInstance item, int count) {
		if (getNpcTemplate().get_digestitem() > 0) {
			setDigestItem(item);
		}
		if (Arrays.binarySearch(haestPotions, item.getItem().getItemId()) >= 0) {
			useItem(USEITEM_HASTE, 100);
		}
	}

	public int getItemObjId() {
		return _itemObjId;
	}

	public void setItemObjId(int i) {
		_itemObjId = i;
	}

	public int getItemId() {
		return _itemId;
	}

	public void setItemId(int i) {
		_itemId = i;
	}

}