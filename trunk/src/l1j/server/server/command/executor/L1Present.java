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

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import l1j.server.L1DatabaseFactory;
import l1j.server.server.IdFactory;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SystemMessage;
import l1j.server.server.templates.L1Account;
import l1j.server.server.templates.L1InventoryItem;
import l1j.server.server.templates.L1Item;
import l1j.server.server.utils.L1SQLException;
import l1j.server.server.utils.SQLUtil;
import l1j.server.server.utils.collections.Lists;

public class L1Present implements L1CommandExecutor {
	private static Logger _log = Logger.getLogger(L1Present.class.getName());

	private L1Present() {
	}

	public static L1CommandExecutor getInstance() {
		return new L1Present();
	}

	private static void present(String accountName, int itemid, int enchant,
			int count) {
		L1Item temp = ItemTable.getInstance().getTemplate(itemid);
		if (temp == null) {
			return;
		}

		List<L1Account> accounts = Lists.newArrayList();
		if (accountName.compareToIgnoreCase("*") == 0) {
			accounts = L1Account.findAll();
		} else {
			accounts.add(L1Account.findByName(accountName));
		}

		present(accounts, temp, enchant, count);
	}

	public static void present(int minLevel, int maxLevel, int itemid,
			int enchant, int count) {
		L1Item temp = ItemTable.getInstance().getTemplate(itemid);
		if (temp == null) {
			return;
		}

		List<L1Account> accounts = L1Account.findByCharacterLevel(minLevel,
				maxLevel);
		present(accounts, temp, enchant, count);
	}

	private static void present(List<L1Account> accountList,
			L1Item itemTemplate, int enchantLevel, int count) {
		Connection con = null;
		try {
			con = L1DatabaseFactory.getInstance().getConnection();
			con.setAutoCommit(false);

			for (L1Account account : accountList) {
				L1InventoryItem item = new L1InventoryItem();
				item.setOwnerId(account.getId());
				item.setEnchantLevel(enchantLevel);
				if (itemTemplate.isStackable()) {
					item.setId(IdFactory.getInstance().nextId());
					item.setItemCount(count);
					item.save(con);
				} else {
					for (int i = 0; i < count; i++) {
						item.setId(IdFactory.getInstance().nextId());
						item.setItemCount(1);
						item.save(con);
					}
				}
			}

			con.commit();
			con.setAutoCommit(true);
		} catch (SQLException e) {
			SQLUtil.rollback(con);
			throw new L1SQLException(".present処理中にエラーが発生しました。", e);
		} finally {
			SQLUtil.close(con);
		}
	}

	@Override
	public void execute(L1PcInstance pc, String cmdName, String arg) {
		try {
			StringTokenizer st = new StringTokenizer(arg);
			String account = st.nextToken();
			int itemid = Integer.parseInt(st.nextToken(), 10);
			int enchant = Integer.parseInt(st.nextToken(), 10);
			int count = Integer.parseInt(st.nextToken(), 10);

			L1Item temp = ItemTable.getInstance().getTemplate(itemid);
			if (temp == null) {
				pc.sendPackets(new S_SystemMessage("存在しないアイテムIDです。"));
				return;
			}

			present(account, itemid, enchant, count);
			pc.sendPackets(new S_SystemMessage(temp.getIdentifiedNameId() + "を"
					+ count + "個プレゼントしました。", true));
		} catch (Exception e) {
			pc
					.sendPackets(new S_SystemMessage(
							".present アカウント名 アイテムID エンチャント数 アイテム数 と入力してください。（アカウント名=*で全て）"));
		}
	}
}
