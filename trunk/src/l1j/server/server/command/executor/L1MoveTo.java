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

import java.util.StringTokenizer;
import java.util.logging.Logger;

import l1j.server.server.model.L1Teleport;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.map.L1Map;
import l1j.server.server.model.map.L1WorldMap;
import l1j.server.server.serverpackets.S_SystemMessage;

public class L1MoveTo implements L1CommandExecutor
{
	private static Logger _log = Logger.getLogger(L1MoveTo.class.getName());

	private L1MoveTo()
	{
	}

	public static L1CommandExecutor getInstance()
	{
		return new L1MoveTo();
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg)
	{
		try
		{
			StringTokenizer st = new StringTokenizer(arg);
			short mapid = Short.parseShort(st.nextToken());
			L1Map map = L1WorldMap.getInstance().getMap(mapid);
			int locx = map.getX() + map.getWidth() / 2;
			int locy = map.getY() + map.getHeight() / 2;
			int before_locx = pc.getX();
			int before_locy = pc.getY();
			int before_mapid = pc.getMapId();
			L1Teleport.teleport(pc, locx, locy, mapid, 5, true);
			pc.sendPackets(new S_SystemMessage("座標(" + before_locx + ", "
					+ before_locy + ", " + before_mapid + ")から 座標(" + locx + ", "
					+ locy + ", " + mapid + ")に移動しました。"));
		}
		catch (Exception e)
		{
			pc.sendPackets(new S_SystemMessage(cmdName + " マップID と入力して下さい。"));
		}
	}
}
