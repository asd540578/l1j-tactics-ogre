/*
ｇ * This program is free software; you can redistribute it and/or modify
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

package l1j.server.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.logging.Logger;

import l1j.server.configure.Config;
import l1j.server.server.datatables.CastleTable;
import l1j.server.server.datatables.CharacterTable;
import l1j.server.server.datatables.ChatLogTable;
import l1j.server.server.datatables.ClanTable;
import l1j.server.server.datatables.CookingRecipeTable;
import l1j.server.server.datatables.DoorTable;
import l1j.server.server.datatables.DropItemTable;
import l1j.server.server.datatables.DropTable;
import l1j.server.server.datatables.FurnitureSpawnTable;
import l1j.server.server.datatables.GetBackRestartTable;
import l1j.server.server.datatables.InnTable;
import l1j.server.server.datatables.IpTable;
import l1j.server.server.datatables.ItemTable;
import l1j.server.server.datatables.MagicDollTable;
import l1j.server.server.datatables.MailTable;
import l1j.server.server.datatables.MapsTable;
import l1j.server.server.datatables.MobGroupTable;
import l1j.server.server.datatables.MobSkillChatTable;
import l1j.server.server.datatables.MobSkillTable;
import l1j.server.server.datatables.NPCTalkDataTable;
import l1j.server.server.datatables.NpcActionTable;
import l1j.server.server.datatables.NpcChatTable;
import l1j.server.server.datatables.NpcSpawnTable;
import l1j.server.server.datatables.NpcTable;
import l1j.server.server.datatables.PetTable;
import l1j.server.server.datatables.PetTypeTable;
import l1j.server.server.datatables.PolyTable;
import l1j.server.server.datatables.RaceTicketTable;
import l1j.server.server.datatables.ResolventTable;
import l1j.server.server.datatables.ShopTable;
import l1j.server.server.datatables.SkillEffectTable;
import l1j.server.server.datatables.SkillTable;
import l1j.server.server.datatables.SpawnTable;
import l1j.server.server.datatables.SprListTable;
import l1j.server.server.datatables.SprTable;
import l1j.server.server.datatables.UBSpawnTable;
import l1j.server.server.datatables.WeaponSkillTable;
import l1j.server.server.model.Dungeon;
import l1j.server.server.model.ElementalStoneGenerator;
import l1j.server.server.model.Getback;
import l1j.server.server.model.L1BossCycle;
import l1j.server.server.model.L1CastleLocation;
import l1j.server.server.model.L1DeleteItemOnGround;
import l1j.server.server.model.L1NpcRegenerationTimer;
import l1j.server.server.model.L1World;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.model.gametime.L1GameTimeClock;
import l1j.server.server.model.item.L1TreasureBox;
import l1j.server.server.model.map.L1WorldMap;
import l1j.server.server.model.trap.L1WorldTraps;
import l1j.server.server.utils.SystemUtil;

// Referenced classes of package l1j.server.server:
// ClientThread, Logins, RateTable, IdFactory,
// LoginController, GameTimeController, Announcements,
// MobTable, SpawnTable, SkillsTable, PolyTable,
// TeleportLocations, ShopTable, NPCTalkDataTable, NpcSpawnTable,
// IpTable, Shutdown, NpcTable, MobGroupTable, NpcShoutTable

public class GameServer extends Thread {
	private ServerSocket _serverSocket;
	private static Logger _log = Logger.getLogger(GameServer.class.getName());
	private LoginController _loginController;
	private static int YesNoCount = 0;

	@Override
	public void run() {
		System.out.println("利用メモリ: " + SystemUtil.getUsedMemoryMB() + "MB");
		System.out.println("クライアント接続待機中...");
		while (true) {
			try {
				Socket socket = _serverSocket.accept();
				System.out.println("接続試行中IP " + socket.getInetAddress());
				String host = socket.getInetAddress().getHostAddress();
				if (IpTable.getInstance().isBannedIp(host)) {
					_log.info("banned IP(" + host + ")");
				} else {
					ClientThread client = new ClientThread(socket);
					GeneralThreadPool.getInstance().execute(client);
				}
			} catch (IOException ioexception) {
			}
		}
	}

	private static GameServer _instance;

	private GameServer() {
		super("GameServer");
	}

	public static GameServer getInstance() {
		if (_instance == null) {
			_instance = new GameServer();
		}
		return _instance;
	}

	private void puts(String message, Object... args) {
		String msg = (args.length == 0) ? message : String
				.format(message, args);
		System.out.println(msg);
	}

	private void printStartupMessage() {
		puts("サーバーセッティング: サーバーソケット生成");

		puts("┌───────────────────────────────┐");
		puts("│  Lineage 3.5C  開発　 By L1J For All User\t\t    　　│");
		puts("└───────────────────────────────┘" + "\n");

		puts("●●●●〈サーバー設定〉●●●●");
		puts("");
		puts("┌「経験値」: %s【倍】", Config.RATE_XP);
		puts("├「アライメント」: %s【倍】", Config.RATE_LA);
		puts("├「カルマ」: %s【倍】", Config.RATE_KARMA);
		puts("├「ドロップ率」: %s【倍】", Config.RATE_DROP_ITEMS);
		puts("├「取得アデナ」: %s【倍】", Config.RATE_DROP_ADENA);
		puts("├「武器エンチャント成功率」: %s【%%】", Config.ENCHANT_CHANCE_WEAPON);
		puts("├「防具エンチャント成功率」: %s【%%】", Config.ENCHANT_CHANCE_ARMOR);
		puts("├「属性強化成功率」: %s【%%】", Config.ATTR_ENCHANT_CHANCE);
		puts("├「全体チャット可能Lv」: %s", Config.GLOBAL_CHAT_LEVEL);
		puts("├「Non-PvP設定」: %s", Config.ALT_NONPVP ? "無効（PvP可能）" : "有効（PvP不可）");
		puts("└「接続人数制限」： 最大%d人", Config.MAX_ONLINE_USERS);

		puts("┌───────────────────────────────┐");
		puts("│  Lineage 3.5C  開発　 By L1J For All User\t\t    　　│");
		puts("└───────────────────────────────┘" + "\n");
	}

	public void initialize() throws Exception {
		printStartupMessage();

		String host = Config.GAME_SERVER_HOST_NAME;
		int port = Config.GAME_SERVER_PORT;
		if (!"*".equals(host)) {
			InetAddress inetaddress = InetAddress.getByName(host);
			inetaddress.getHostAddress();
			_serverSocket = new ServerSocket(port, 50, inetaddress);
		} else {
			_serverSocket = new ServerSocket(port);
		}

		IdFactory.getInstance();
		L1WorldMap.getInstance();
		_loginController = LoginController.getInstance();
		_loginController.setMaxAllowedOnlinePlayers(Config.MAX_ONLINE_USERS);

		// 全キャラクターネームロード
		CharacterTable.getInstance().loadAllCharName();

		// オンライン状態リセット
		CharacterTable.clearOnlineStatus();

		// ゲーム時間時計
		L1GameTimeClock.init();

		// UBタイムコントローラー
		UbTimeController ubTimeContoroller = UbTimeController.getInstance();
		GeneralThreadPool.getInstance().execute(ubTimeContoroller);

		// 戦争タイムコントローラー
		WarTimeController warTimeController = WarTimeController.getInstance();
		GeneralThreadPool.getInstance().execute(warTimeController);

		// 精霊の石生成
		if (Config.ELEMENTAL_STONE_AMOUNT > 0) {
			ElementalStoneGenerator elementalStoneGenerator = ElementalStoneGenerator
					.getInstance();
			GeneralThreadPool.getInstance().execute(elementalStoneGenerator);
		}

		// ホームタウン
		HomeTownTimeController.getInstance();

		// アジト競売タイムコントローラー
		AuctionTimeController auctionTimeController = AuctionTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(auctionTimeController);

		// アジト税金タイムコントローラー
		HouseTaxTimeController houseTaxTimeController = HouseTaxTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(houseTaxTimeController);

		// 釣りタイムコントローラー
		FishingTimeController fishingTimeController = FishingTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(fishingTimeController);

		// NPCチャットタイムコントローラー
		NpcChatTimeController npcChatTimeController = NpcChatTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(npcChatTimeController);

		// ライトタイムコントローラー
		LightTimeController lightTimeController = LightTimeController
				.getInstance();
		GeneralThreadPool.getInstance().execute(lightTimeController);

		//
		// CrackTimeController crackTimeController = CrackTimeController
		// .getInstance();
		// GeneralThreadPool.getInstance().execute(crackTimeController);

		Announcements.getInstance();
		AnnouncementsCycle.getInstance();

		MobSkillTable.initialize();
		/* t.s 2011/09/27 add start */
		MobSkillChatTable.initialize();
		/* t.s 2011/09/27 add end */
		/* t.s 2012/01/30 add start */
		SkillEffectTable.initialize();
		/* t.s 2012/01/30 add end */
		NpcTable.initialize();
		new L1DeleteItemOnGround().initialize();

		DoorTable.initialize();
		SpawnTable.getInstance();
		MobGroupTable.getInstance();
		SkillTable.initialize();
		PolyTable.getInstance();
		ItemTable.getInstance();
		DropTable.getInstance();
		DropItemTable.getInstance();
		ShopTable.getInstance();
		NPCTalkDataTable.getInstance();
		L1World.getInstance();
		L1WorldTraps.getInstance();
		Dungeon.getInstance();
		NpcSpawnTable.getInstance();
		IpTable.getInstance();
		MapsTable.getInstance();
		UBSpawnTable.getInstance();
		PetTable.getInstance();
		ClanTable.getInstance();
		CastleTable.getInstance();
		L1CastleLocation.setCastleTaxRate(); // これはCastleTable初期化後でなければいけない
		GetBackRestartTable.getInstance();
		// DoorSpawnTable.getInstance();
		GeneralThreadPool.getInstance();
		L1NpcRegenerationTimer.getInstance();
		ChatLogTable.getInstance();
		WeaponSkillTable.getInstance();
		NpcActionTable.load();
		GMCommandsConfig.load();
		Getback.loadGetBack();
		PetTypeTable.load();
		L1BossCycle.load();
		L1TreasureBox.load();
		SprTable.getInstance();
		ResolventTable.getInstance();
		FurnitureSpawnTable.getInstance();
		NpcChatTable.getInstance();
		MailTable.getInstance();
		SprListTable.getInstance();
		RaceTicketTable.getInstance();
		/* t.s 2011/09/13 Del Start */
		// L1BugBearRace.getInstance();
		/* t.s 2011/09/13 Del End */
		InnTable.getInstance();
		MagicDollTable.getInstance();
		CookingRecipeTable.initialize();
		System.out.println("ローディング完了");
		Runtime.getRuntime().addShutdownHook(Shutdown.getInstance());

		this.start();
	}

	/**
	 * オンライン中のプレイヤー全てに対してkick、キャラクター情報の保存をする。
	 */
	public void disconnectAllCharacters() {
		Collection<L1PcInstance> players = L1World.getInstance()
				.getAllPlayers();
		for (L1PcInstance pc : players) {
			pc.getNetConnection().setActiveChar(null);
			pc.getNetConnection().kick();
		}
		// 全員Kickした後に保存処理をする
		for (L1PcInstance pc : players) {
			ClientThread.quitGame(pc);
			L1World.getInstance().removeObject(pc);
		}
	}

	private class ServerShutdownThread extends Thread {
		private final int _secondsCount;

		public ServerShutdownThread(int secondsCount) {
			_secondsCount = secondsCount;
		}

		@Override
		public void run() {
			L1World world = L1World.getInstance();
			try {
				int secondsCount = _secondsCount;
				world.broadcastServerMessage("ただいまより、サーバーをシャットダウンします。");
				world.broadcastServerMessage("安全な場所でログアウトしてください");
				while (0 < secondsCount) {
					if (secondsCount <= 30) {
						world.broadcastServerMessage("ゲームが" + secondsCount
								+ "秒後にシャットダウンします。ゲームを中断してください。");
					} else {
						if (secondsCount % 60 == 0) {
							world.broadcastServerMessage("ゲームが" + secondsCount
									/ 60 + "分後にシャットダウンします。");
						}
					}
					Thread.sleep(1000);
					secondsCount--;
				}
				shutdown();
			} catch (InterruptedException e) {
				world.broadcastServerMessage("シャットダウンが中断されました。サーバーは通常稼動中です。");
				return;
			}
		}
	}

	private ServerShutdownThread _shutdownThread = null;

	public synchronized void shutdownWithCountdown(int secondsCount) {
		if (_shutdownThread != null) {
			// 既にシャットダウン要求が行われている
			// TODO エラー通知が必要かもしれない
			return;
		}
		_shutdownThread = new ServerShutdownThread(secondsCount);
		GeneralThreadPool.getInstance().execute(_shutdownThread);
	}

	public void shutdown() {
		disconnectAllCharacters();
		System.exit(0);
	}

	public synchronized void abortShutdown() {
		if (_shutdownThread == null) {
			// シャットダウン要求が行われていない
			// TODO エラー通知が必要かもしれない
			return;
		}

		_shutdownThread.interrupt();
		_shutdownThread = null;
	}

	public static int getYesNoCount() {
		YesNoCount += 1;
		return YesNoCount;
	}
}