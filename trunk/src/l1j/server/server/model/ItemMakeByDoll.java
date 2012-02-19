package l1j.server.server.model;

import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import l1j.server.server.datatables.ItemTable;
import l1j.server.server.model.Instance.L1ItemInstance;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.inventory.L1Inventory;
import l1j.server.server.serverpackets.S_ServerMessage;
import l1j.server.server.templates.L1MagicDoll;

public class ItemMakeByDoll extends TimerTask {
	private static Logger _log = Logger.getLogger(ItemMakeByDoll.class
			.getName());

	private final L1PcInstance _pc;

	public ItemMakeByDoll(L1PcInstance pc) {
		_pc = pc;
	}

	@Override
	public void run() {
		try {
			if (_pc.isDead()) {
				return;
			}
			itemMake();
		} catch (Throwable e) {
			_log.log(Level.WARNING, e.getLocalizedMessage(), e);
		}
	}

	public void itemMake() {
		L1ItemInstance temp = ItemTable.getInstance().createItem(
				L1MagicDoll.getMakeItemId(_pc));
		if (temp != null) {
			if (_pc.getInventory().checkAddItem(temp, 1) == L1Inventory.OK) {
				L1ItemInstance item = _pc.getInventory().storeItem(
						temp.getItemId(), 1);
				//_pc.sendPackets(new S_SkillSound(_pc.getId(), 6319));
				//_pc.broadcastPacket(new S_SkillSound(_pc.getId(), 6319));
				_pc.sendPackets(new S_ServerMessage(403, item.getItem()
						.getName())); // %0 を手に入れました
			}
		}
	}
}
