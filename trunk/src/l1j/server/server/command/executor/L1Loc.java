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
package l1j.server.server.command.executor;

import java.util.Map;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.datatables.NpcTable;
import l1j.server.server.model.L1Object;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1MonsterInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.map.L1WorldMap;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1Npc;

public class L1Loc implements L1CommandExecutor
{
	private static Logger _log = Logger.getLogger(L1Loc.class.getName());

	private L1Loc()
	{
	}

	public static L1CommandExecutor getInstance()
	{
		return new L1Loc();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg)
	{
		try
		{
			int locx = 0;
			int locy = 0;
			short mapid = 0;
			int gab = 0;
			StringBuilder msgStr = new StringBuilder();
			final String BR = System.getProperty("line.separator");

			try	// 引数が指定されている場合（＝ＮＰＣ）
			{
				StringTokenizer st = new StringTokenizer(arg);
				String targetName = st.nextToken();
				Map<Integer, L1Object> objList = L1World.getInstance()
						.getAllVisibleObjects();
				int count = 0;

				if (pc.isGm()) // 通常キャラで使えないように修正
				{
					// 引数と一致する名前のモンスターを抽出し、座標を取得する。
					msgStr.append("-- モンスターサーチ --" + BR);
					msgStr.append(BR);
					msgStr.append("モンスター名：" + targetName + BR);
					for (L1Object obj : objList.values())
					{
						if (obj instanceof L1MonsterInstance)
						{
							L1MonsterInstance mobObj = (L1MonsterInstance) obj;
							String mobName = mobObj.getName().replace(" " ,"");
							if (mobName.equals(targetName))
							{
								locx = mobObj.getX();
								locy = mobObj.getY();
								mapid = mobObj.getMapId();
								L1WorldMap.getInstance().getMap(mapid)
										.getOriginalTile(locx, locy);
								String msg = String.format(
										"NPCID->" + mobObj.getNpcId()
												+ "   座標->(%d, %d, %d) %d",
										locx, locy, mapid, gab);
								msgStr.append(msg + BR);
								++count;
							}
						}
					}
					msgStr.append(Integer.toString(count) + " Hit!!" + BR);
					msgStr.append(" " + BR);
				}
			}
			catch (Exception e) // 引数が指定されていない場合（＝ＰＣ）
			{
				locx = pc.getX();
				locy = pc.getY();
				mapid = pc.getMapId();
				gab = L1WorldMap.getInstance().getMap(mapid)
						.getOriginalTile(locx, locy);
				String msg = String.format("座標->(%d, %d, %d) %d", locx, locy,
						mapid, gab);
				msgStr.append(msg);
				msgStr.append(" " + BR);
			}
			pc.sendPackets(new S_SystemMessage(msgStr.toString()));
		}
		catch (Exception e)
		{
			_log.log(Level.SEVERE, e.getLocalizedMessage(), e);
		}
	}
}
