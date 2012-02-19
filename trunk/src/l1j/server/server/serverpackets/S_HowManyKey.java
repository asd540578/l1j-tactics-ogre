package l1j.server.server.serverpackets;

import java.io.IOException;

import l1j.server.server.Opcodes;
import l1j.server.server.model.Instance.L1NpcInstance;

public class S_HowManyKey extends ServerBasePacket {

	/*
	 * 【Server】 id:14 size:40 time:1300606757968
	 *  0000	0e cc 1e 00 00 2c 01 00 00 01 00 00 00 01 00 00    .....,..........
	 *  0010	00 08 00 00 00 00 00 69 6e 6e 32 00 00 02 00 24    .......inn2....$
	 *  0020	39 35 35 00 33 30 30 00                            955.300.
	 */

	public S_HowManyKey(L1NpcInstance npc, int price, int min, int max, String htmlId) {
		writeC(Opcodes.S_OPCODE_INPUTAMOUNT);
		writeD(npc.getId());
		writeD(price); // 金額
		writeD(min); // スタート数量
		writeD(min); // スタート数量
		writeD(max); // 購買最大数量
		writeH(0); // ?
		writeS(htmlId); // npc会話htmlId
		writeH(1); // ?
		writeH(0x02); // writeS 数量
		writeS(npc.getName()); // NPC名称
		writeS(String.valueOf(price)); // 表示金額
	}

	@Override
	public byte[] getContent() throws IOException {
		return getBytes();
	}
}