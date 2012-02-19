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
package l1j.server.server.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.ActionCodes;
import l1j.server.server.IdFactory;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.L1Character;
import l1j.server.server.model.L1Location;
import l1j.server.server.model.L1NpcDeleteTimer;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1NpcInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.random.RandomGeneratorFactory;
import l1j.server.server.serverpackets.S_DoActionGFX;
import l1j.server.server.serverpackets.S_NPCPack;

public class L1SpawnUtil {
	private static Logger _log = Logger.getLogger(L1SpawnUtil.class.getName());

	public static void spawn(L1PcInstance pc, int npcId, int randomRange,
			int timeMillisToDelete) {
		try {
			L1NpcInstance npc = NpcTable.getInstance().newNpcInstance(npcId);
			npc.setId(IdFactory.getInstance().nextId());
			npc.setMap(pc.getMapId());
			if (randomRange == 0) {
				npc.getLocation().set(pc.getLocation());
				npc.getLocation().forward(pc.getHeading());
			} else {
				int tryCount = 0;
				do {
					tryCount++;
					npc.setX(pc.getX() + (int) (Math.random() * randomRange)
							- (int) (Math.random() * randomRange));
					npc.setY(pc.getY() + (int) (Math.random() * randomRange)
							- (int) (Math.random() * randomRange));
					if (npc.getMap().isInMap(npc.getLocation())
							&& npc.getMap().isPassable(npc.getLocation())) {
						break;
					}
					Thread.sleep(1);
				} while (tryCount < 50);

				if (tryCount >= 50) {
					npc.getLocation().set(pc.getLocation());
					npc.getLocation().forward(pc.getHeading());
				}
			}

			npc.setHomeX(npc.getX());
			npc.setHomeY(npc.getY());
			npc.setHeading(pc.getHeading());

			L1World.getInstance().storeObject(npc);
			L1World.getInstance().addVisibleObject(npc);

			npc.updateLight();
			npc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // チャット開始
			if (0 < timeMillisToDelete) {
				L1NpcDeleteTimer timer = new L1NpcDeleteTimer(npc,
						timeMillisToDelete);
				timer.begin();
			}
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}

	public static void summonMonster(L1Character master, int summonId) {
		try {
			L1NpcInstance mob = NpcTable.getInstance().newNpcInstance(summonId);
			mob.setId(IdFactory.getInstance().nextId());
			L1Location loc = master.getLocation().randomLocation(8, false);
			mob.setX(loc.getX());
			mob.setY(loc.getY());
			mob.setHomeX(loc.getX());
			mob.setHomeY(loc.getY());
			short mapid = master.getMapId();
			mob.setMap(mapid);
			int heading = RandomGeneratorFactory.getSharedRandom().nextInt(8);
			mob.setHeading(heading);
			L1World.getInstance().storeObject(mob);
			L1World.getInstance().addVisibleObject(mob);
			L1Object object = L1World.getInstance().findObject(mob.getId());
			L1MonsterInstance newnpc = (L1MonsterInstance) object;
			newnpc.set_storeDroped(true); // 召喚されたモンスターはドロップ無し
			if (summonId == 45061 // カーズドスパルトイ
					|| summonId == 45161 // スパルトイ
					|| summonId == 45181 // スパルトイ
					|| summonId == 45455) { // デッドリースパルトイ
				newnpc.broadcastPacket(new S_DoActionGFX(newnpc.getId(),
						ActionCodes.ACTION_Hide));
				newnpc.setStatus(13);
				newnpc.broadcastPacket(new S_NPCPack(newnpc));
				newnpc.broadcastPacket(new S_DoActionGFX(newnpc.getId(),
						ActionCodes.ACTION_Appear));
				newnpc.setStatus(0);
				newnpc.broadcastPacket(new S_NPCPack(newnpc));
			}
			newnpc.onNpcAI();
			newnpc.updateLight();
			newnpc.startChat(L1NpcInstance.CHAT_TIMING_APPEARANCE); // チャット開始
		} catch (Exception e) {
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}
}
