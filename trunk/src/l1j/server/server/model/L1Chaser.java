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

import static l1j.server.server.model.skill.L1SkillId.*;

import java.util.TimerTask;
import java.util.concurrent.ScheduledFuture;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.ActionCodes;
import l1j.server.server.GeneralThreadPool;
import l1j.server.server.datatables.SkillTable;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_EffectLocation;
import l1j.server.server.serverpackets.S_SkillSound;

public class L1Chaser extends TimerTask {
	private static Logger _log = Logger.getLogger(L1Chaser.class.getName());

	private ScheduledFuture<?> _future = null;
	private int _timeCounter = 0;
	private final L1PcInstance _pc;
	private final L1Character _cha;
	private final int _gfxid;

	public L1Chaser(L1PcInstance pc, L1Character cha, int attr, int gfxid) {
		_cha = cha;
		_pc = pc;
		_gfxid = gfxid;
	}

	@Override
	public void run() {
		try {
			if (_cha == null || _cha.isDead()) {
				stop();
				return;
			}
			if (_cha.hasSkillEffect(COUNTER_MAGIC)) {
				_cha.removeSkillEffect(COUNTER_MAGIC);
				int castgfx = SkillTable.getInstance().findBySkillId(
						COUNTER_MAGIC).getCastGfx();
				_cha.broadcastPacket(new S_SkillSound(_cha.getId(), castgfx));
				if (_cha instanceof L1PcInstance) {
					L1PcInstance pc = (L1PcInstance) _cha;
					pc.sendPackets(new S_SkillSound(pc.getId(), castgfx));
				}
				_cha.setChaserHitting(false);
				return;
			}
			_cha.setChaserHitting(true);
			attack();
			_timeCounter++;
			if (_timeCounter >= 3) {
				_cha.setChaserHitting(false);
				stop();
				return;
			}
		} catch (Throwable e) {
			_log.log(Level.WARNING, e.getLocalizedMessage(), e);
		}
	}

	public void begin() {
		// 効果時間が8秒のため、4秒毎のスキルの場合処理時間を考慮すると実際には1回しか効果が現れない
		// よって開始時間を0.9秒後に設定しておく
		_future = GeneralThreadPool.getInstance().scheduleAtFixedRate(this, 0,
				1000);
	}

	public void stop() {
		if (_future != null) {
			_future.cancel(false);
		}
	}

	public void attack() {
		double damage = getDamage(_pc, _cha);
		if (_cha.getCurrentHp() - (int) damage <= 0 && _cha.getCurrentHp() != 1) {
			damage = _cha.getCurrentHp() - 1;
		} else if (_cha.getCurrentHp() == 1) {
			damage = 0;
		}
		S_EffectLocation packet = new S_EffectLocation(_cha.getX(),
				_cha.getY(), _gfxid);
		_pc.sendPackets(packet);
		_pc.broadcastPacket(packet);
		if (_cha instanceof L1PcInstance) {
			L1PcInstance pc = (L1PcInstance) _cha;
			pc.sendPackets(new S_DoActionGFX(pc.getId(),
					ActionCodes.ACTION_Damage));
			pc.broadcastPacket(new S_DoActionGFX(pc.getId(),
					ActionCodes.ACTION_Damage));
			pc.receiveDamage(_pc, damage, false);
		} else if (_cha instanceof L1NpcInstance) {
			L1NpcInstance npc = (L1NpcInstance) _cha;
			npc.broadcastPacket(new S_DoActionGFX(npc.getId(),
					ActionCodes.ACTION_Damage));
			npc.receiveDamage(_pc, (int) damage);
		}
	}

	public double getDamage(L1PcInstance pc, L1Character cha) {
		double dmg = 0;
		L1Magic _magic = new L1Magic(pc, cha);
		if (_gfxid == 7025) {
			dmg = _magic.calcMagicDamage(10146);
		} else if (_gfxid == 7179) {
			dmg = _magic.calcMagicDamage(10147);
		}

		cha.removeSkillEffect(ERASE_MAGIC);

		return dmg;
	}

}
