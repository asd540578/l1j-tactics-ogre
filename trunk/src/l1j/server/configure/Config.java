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
package l1j.server.configure;

import java.util.Calendar;

import l1j.server.configure.Annotations.Configure;
import l1j.server.configure.CustomLoaders.WarTimeLoader;
import l1j.server.configure.CustomLoaders.WarTimeUnitLoader;
import l1j.server.server.utils.IntRange;

public final class Config {
	private static final String SERVER = "./config/server.properties";
	private static final String RATE = "./config/rates.properties";
	private static final String ALT = "./config/altsettings.properties";
	private static final String CHARACTER = "./config/charsettings.properties";
	private static final String PACK = "./config/pack.properties";

	/** Debug/release mode */
	@Configure(file = SERVER, key = "DebugMode")
	public static boolean DEBUG_MODE = false;

	/** Thread pools size */
	@Configure(file = SERVER, key = "GeneralThreadPoolType")
	public static int THREAD_P_TYPE_GENERAL = 0;

	@Configure(file = SERVER, key = "GeneralThreadPoolSize")
	public static int THREAD_P_SIZE_GENERAL = 0;

	/** Server control */
	@Configure(file = SERVER, key = "GameserverHostname")
	public static String GAME_SERVER_HOST_NAME = "*";

	@Configure(file = SERVER, key = "GameserverPort")
	public static int GAME_SERVER_PORT = 2000;

	@Configure(file = SERVER, key = "Driver")
	public static String DB_DRIVER = "com.mysql.jdbc.Driver";

	@Configure(file = SERVER, key = "URL")
	public static String DB_URL = "jdbc:mysql://localhost/l1jdb?useUnicode=true&characterEncoding=utf8";

	@Configure(file = SERVER, key = "Login")
	public static String DB_LOGIN = "root";

	@Configure(file = SERVER, key = "Password")
	public static String DB_PASSWORD = "";

	@Configure(file = SERVER, key = "TimeZone")
	public static String TIME_ZONE = "JST";

	@Configure(file = SERVER, key = "ClientLanguage")
	public static int CLIENT_LANGUAGE = 4;

	public static String CLIENT_LANGUAGE_CODE;

	public static String[] LANGUAGE_CODE_ARRAY = { "UTF8", "EUCKR", "UTF8",
			"BIG5", "SJIS", "GBK" };

	@Configure(file = SERVER, key = "HostnameLookups")
	public static boolean HOSTNAME_LOOKUPS = false;

	@Configure(file = SERVER, key = "AutomaticKick")
	public static int AUTOMATIC_KICK = 10;

	@Configure(file = SERVER, key = "AutoCreateAccounts")
	public static boolean AUTO_CREATE_ACCOUNTS = true;

	@Configure(file = SERVER, key = "MaximumOnlineUsers")
	public static int MAX_ONLINE_USERS = 30;

	@Configure(file = SERVER, key = "CacheMapFiles")
	public static boolean CACHE_MAP_FILES = false;

	@Configure(file = SERVER, key = "LoadV2MapFiles")
	public static boolean LOAD_V2_MAP_FILES = false;

	@Configure(file = SERVER, key = "CheckMoveInterval")
	public static boolean CHECK_MOVE_INTERVAL = false;

	@Configure(file = SERVER, key = "CheckAttackInterval")
	public static boolean CHECK_ATTACK_INTERVAL = false;

	@Configure(file = SERVER, key = "CheckSpellInterval")
	public static boolean CHECK_SPELL_INTERVAL = false;

	@Configure(file = SERVER, key = "InjusticeCount")
	public static int INJUSTICE_COUNT = 10;

	@Configure(file = SERVER, key = "JusticeCount")
	public static int JUSTICE_COUNT = 4;

	@Configure(file = SERVER, key = "CheckStrictness")
	public static int CHECK_STRICTNESS = 102;

	@Configure(file = SERVER, key = "LoggingWeaponEnchant")
	public static int LOGGING_WEAPON_ENCHANT = 0;

	@Configure(file = SERVER, key = "LoggingArmorEnchant")
	public static int LOGGING_ARMOR_ENCHANT = 0;

	@Configure(file = SERVER, key = "LoggingChatNormal")
	public static boolean LOGGING_CHAT_NORMAL = false;

	@Configure(file = SERVER, key = "LoggingChatWhisper")
	public static boolean LOGGING_CHAT_WHISPER = false;

	@Configure(file = SERVER, key = "LoggingChatShout")
	public static boolean LOGGING_CHAT_SHOUT = false;

	@Configure(file = SERVER, key = "LoggingChatWorld")
	public static boolean LOGGING_CHAT_WORLD = false;

	@Configure(file = SERVER, key = "LoggingChatClan")
	public static boolean LOGGING_CHAT_CLAN = false;

	@Configure(file = SERVER, key = "LoggingChatParty")
	public static boolean LOGGING_CHAT_PARTY = false;

	@Configure(file = SERVER, key = "LoggingChatCombined")
	public static boolean LOGGING_CHAT_COMBINED = false;

	@Configure(file = SERVER, key = "LoggingChatChatParty")
	public static boolean LOGGING_CHAT_CHAT_PARTY = false;

	@Configure(file = SERVER, key = "AutosaveInterval")
	public static int AUTOSAVE_INTERVAL = 1200;

	@Configure(file = SERVER, key = "AutosaveIntervalOfInventory")
	public static int AUTOSAVE_INTERVAL_INVENTORY = 3000;

	@Configure(file = SERVER, key = "SkillTimerImplType")
	public static int SKILLTIMER_IMPLTYPE = 1;

	@Configure(file = SERVER, key = "NpcAIImplType")
	public static int NPCAI_IMPLTYPE = 1;

	@Configure(file = SERVER, key = "TelnetServer")
	public static boolean TELNET_SERVER = false;

	@Configure(file = SERVER, key = "TelnetServerPort")
	public static int TELNET_SERVER_PORT = 23;

	@Configure(file = SERVER, key = "PcRecognizeRange")
	public static int PC_RECOGNIZE_RANGE = 20;

	@Configure(file = SERVER, key = "CharacterConfigInServerSide")
	public static boolean CHARACTER_CONFIG_IN_SERVER_SIDE = true;

	@Configure(file = SERVER, key = "Allow2PC")
	public static boolean ALLOW_2PC = true;

	@Configure(file = SERVER, key = "LevelDownRange")
	public static int LEVEL_DOWN_RANGE = 0;

	@Configure(file = SERVER, key = "SendPacketBeforeTeleport")
	public static boolean SEND_PACKET_BEFORE_TELEPORT = false;

	/** Rate control */
	@Configure(file = RATE, key = "RateXp")
	public static double RATE_XP = 1.0;

	@Configure(file = RATE, key = "RateLawful")
	public static double RATE_LA = 1.0;

	@Configure(file = RATE, key = "RateKarma")
	public static double RATE_KARMA = 1.0;

	@Configure(file = RATE, key = "RateDropAdena")
	public static double RATE_DROP_ADENA = 1.0;

	@Configure(file = RATE, key = "RateDropItems")
	public static double RATE_DROP_ITEMS = 1.0;

	@Configure(file = RATE, key = "EnchantChanceWeapon")
	public static int ENCHANT_CHANCE_WEAPON = 68;

	@Configure(file = RATE, key = "EnchantChanceArmor")
	public static int ENCHANT_CHANCE_ARMOR = 52;

	@Configure(file = RATE, key = "AttrEnchantChance")
	public static int ATTR_ENCHANT_CHANCE = 10;

	@Configure(file = RATE, key = "RateWeightLimit")
	public static double RATE_WEIGHT_LIMIT = 1;

	@Configure(file = RATE, key = "RateWeightLimitforPet")
	public static double RATE_WEIGHT_LIMIT_PET = 1;

	@Configure(file = RATE, key = "RateShopSellingPrice")
	public static double RATE_SHOP_SELLING_PRICE = 1.0;

	@Configure(file = RATE, key = "RateShopPurchasingPrice")
	public static double RATE_SHOP_PURCHASING_PRICE = 1.0;

	@Configure(file = RATE, key = "CreateChanceDiary")
	public static int CREATE_CHANCE_DIARY = 33;

	@Configure(file = RATE, key = "CreateChanceRecollection")
	public static int CREATE_CHANCE_RECOLLECTION = 90;

	@Configure(file = RATE, key = "CreateChanceMysterious")
	public static int CREATE_CHANCE_MYSTERIOUS = 90;

	@Configure(file = RATE, key = "CreateChanceProcessing")
	public static int CREATE_CHANCE_PROCESSING = 90;

	@Configure(file = RATE, key = "CreateChanceProcessingDiamond")
	public static int CREATE_CHANCE_PROCESSING_DIAMOND = 90;

	@Configure(file = RATE, key = "CreateChanceDantes")
	public static int CREATE_CHANCE_DANTES = 50;

	@Configure(file = RATE, key = "CreateChanceAncientAmulet")
	public static int CREATE_CHANCE_ANCIENT_AMULET = 90;

	@Configure(file = RATE, key = "CreateChanceHistoryBook")
	public static int CREATE_CHANCE_HISTORY_BOOK = 50;

	/** AltSettings control */
	@Configure(file = ALT, key = "GlobalChatLevel")
	public static int GLOBAL_CHAT_LEVEL = 30;

	@Configure(file = ALT, key = "WhisperChatLevel")
	public static int WHISPER_CHAT_LEVEL = 5;

	@Configure(file = ALT, key = "AutoLoot")
	public static int AUTO_LOOT = 2;

	@Configure(file = ALT, key = "LootingRange")
	public static int LOOTING_RANGE = 3;

	@Configure(file = ALT, key = "NonPvP")
	public static boolean ALT_NONPVP = true;

	@Configure(file = ALT, key = "AttackMessageOn")
	public static boolean ALT_ATKMSG = true;

	@Configure(file = ALT, key = "ChangeTitleByOneself")
	public static boolean CHANGE_TITLE_BY_ONESELF = false;

	@Configure(file = ALT, key = "MaxClanMember")
	public static int MAX_CLAN_MEMBER = 0;

	@Configure(file = ALT, key = "ClanAlliance")
	public static boolean CLAN_ALLIANCE = true;

	@Configure(file = ALT, key = "MaxPT")
	public static int MAX_PT = 8;

	@Configure(file = ALT, key = "MaxChatPT")
	public static int MAX_CHAT_PT = 8;

	@Configure(file = ALT, key = "SimWarPenalty")
	public static boolean SIM_WAR_PENALTY = true;

	@Configure(file = ALT, key = "GetBack")
	public static boolean GET_BACK = false;

	@Configure(file = ALT, key = "ItemDeletionType")
	public static String ALT_ITEM_DELETION_TYPE = "auto";

	@Configure(file = ALT, key = "ItemDeletionTime")
	public static int ALT_ITEM_DELETION_TIME = 10;

	@Configure(file = ALT, key = "ItemDeletionRange")
	public static int ALT_ITEM_DELETION_RANGE = 5;

	@Configure(file = ALT, key = "GMshop")
	public static boolean ALT_GMSHOP = false;

	@Configure(file = ALT, key = "GMshopMinID")
	public static int ALT_GMSHOP_MIN_ID = 0;

	@Configure(file = ALT, key = "GMshopMaxID")
	public static int ALT_GMSHOP_MAX_ID = 0;

	@Configure(file = ALT, key = "HalloweenIvent")
	public static boolean ALT_HALLOWEENIVENT = true;

	@Configure(file = ALT, key = "JpPrivileged")
	public static boolean ALT_JPPRIVILEGED = false;

	@Configure(file = ALT, key = "TalkingScrollQuest")
	public static boolean ALT_TALKINGSCROLLQUEST = false;

	@Configure(file = ALT, key = "WhoCommand")
	public static boolean ALT_WHO_COMMAND = false;

	@Configure(file = ALT, key = "RevivalPotion")
	public static boolean ALT_REVIVAL_POTION = false;

	@Configure(file = ALT, key = "WarTime", loader = WarTimeLoader.class)
	public static int ALT_WAR_TIME = 2;

	@Configure(file = ALT, key = "WarTime", loader = WarTimeUnitLoader.class)
	public static int ALT_WAR_TIME_UNIT = Calendar.HOUR_OF_DAY;

	@Configure(file = ALT, key = "WarInterval", loader = WarTimeLoader.class)
	public static int ALT_WAR_INTERVAL = 4;

	@Configure(file = ALT, key = "WarInterval", loader = WarTimeUnitLoader.class)
	public static int ALT_WAR_INTERVAL_UNIT = Calendar.DATE;

	@Configure(file = ALT, key = "SpawnHomePoint")
	public static boolean SPAWN_HOME_POINT = true;

	@Configure(file = ALT, key = "SpawnHomePointRange")
	public static int SPAWN_HOME_POINT_RANGE = 8;

	@Configure(file = ALT, key = "SpawnHomePointCount")
	public static int SPAWN_HOME_POINT_COUNT = 2;

	@Configure(file = ALT, key = "SpawnHomePointDelay")
	public static int SPAWN_HOME_POINT_DELAY = 100;

	@Configure(file = ALT, key = "InitBossSpawn")
	public static boolean INIT_BOSS_SPAWN = true;

	@Configure(file = ALT, key = "ElementalStoneAmount")
	public static int ELEMENTAL_STONE_AMOUNT = 300;

	@Configure(file = ALT, key = "HouseTaxInterval")
	public static int HOUSE_TAX_INTERVAL = 10;

	@Configure(file = ALT, key = "MaxDollCount")
	public static int MAX_DOLL_COUNT = 1;

	@Configure(file = ALT, key = "ReturnToNature")
	public static boolean RETURN_TO_NATURE = false;

	@Configure(file = ALT, key = "MaxNpcItem")
	public static int MAX_NPC_ITEM = 8;

	@Configure(file = ALT, key = "MaxPersonalWarehouseItem")
	public static int MAX_PERSONAL_WAREHOUSE_ITEM = 100;

	@Configure(file = ALT, key = "MaxClanWarehouseItem")
	public static int MAX_CLAN_WAREHOUSE_ITEM = 200;

	@Configure(file = ALT, key = "DeleteCharacterAfter7Days")
	public static boolean DELETE_CHARACTER_AFTER_7DAYS = true;

	@Configure(file = ALT, key = "NpcDeletionTime")
	public static int NPC_DELETION_TIME = 10;

	@Configure(file = ALT, key = "DefaultCharacterSlot")
	public static int DEFAULT_CHARACTER_SLOT = 6;

	@Configure(file = ALT, key = "PkLog")
	public static boolean PK_LOG = false;

	@Configure(file = ALT, key = "BossSpawnLog")
	public static boolean BOSS_SPAWN_LOG = false;

	@Configure(file = ALT, key = "BossEndLog")
	public static boolean BOSS_END_LOG = false;

	@Configure(file = ALT, key = "Hell")
	public static boolean HELL = false;

	@Configure(file = ALT, key = "PkCountLimit")
	public static int PK_COUNT_LIMIT = 10;

	@Configure(file = ALT, key = "PetRaceMinPlayer")
	public static int PET_RACE_MIN_PLAYER = 1; // ペットレース設定

	@Configure(file = ALT, key = "PetRaceMaxLap")
	public static int PET_RACE_MAX_LAP = 3; // ペットレース設定

	@Configure(file = ALT, key = "DeathMatchMinPlayer")
	public static int DEATH_MATCH_MIN_PLAYER = 1;// デスマッチ最小人数

	/** CharSettings control */
	@Configure(file = CHARACTER, key = "PrinceMaxHP")
	public static int PRINCE_MAX_HP = 1000;

	@Configure(file = CHARACTER, key = "PrinceMaxMP")
	public static int PRINCE_MAX_MP = 800;

	@Configure(file = CHARACTER, key = "KnightMaxHP")
	public static int KNIGHT_MAX_HP = 1400;

	@Configure(file = CHARACTER, key = "KnightMaxMP")
	public static int KNIGHT_MAX_MP = 600;

	@Configure(file = CHARACTER, key = "ElfMaxHP")
	public static int ELF_MAX_HP = 1000;

	@Configure(file = CHARACTER, key = "ElfMaxMP")
	public static int ELF_MAX_MP = 900;

	@Configure(file = CHARACTER, key = "WizardMaxHP")
	public static int WIZARD_MAX_HP = 800;

	@Configure(file = CHARACTER, key = "WizardMaxMP")
	public static int WIZARD_MAX_MP = 1200;

	@Configure(file = CHARACTER, key = "DarkelfMaxHP")
	public static int DARKELF_MAX_HP = 1000;

	@Configure(file = CHARACTER, key = "DarkelfMaxMP")
	public static int DARKELF_MAX_MP = 900;

	@Configure(file = CHARACTER, key = "DragonKnightMaxHP")
	public static int DRAGONKNIGHT_MAX_HP = 1400;

	@Configure(file = CHARACTER, key = "DragonKnightMaxMP")
	public static int DRAGONKNIGHT_MAX_MP = 600;

	@Configure(file = CHARACTER, key = "IllusionistMaxHP")
	public static int ILLUSIONIST_MAX_HP = 900;

	@Configure(file = CHARACTER, key = "IllusionistMaxMP")
	public static int ILLUSIONIST_MAX_MP = 1100;

	@Configure(file = CHARACTER, key = "Lv50Exp")
	public static int LV50_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv51Exp")
	public static int LV51_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv52Exp")
	public static int LV52_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv53Exp")
	public static int LV53_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv54Exp")
	public static int LV54_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv55Exp")
	public static int LV55_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv56Exp")
	public static int LV56_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv57Exp")
	public static int LV57_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv58Exp")
	public static int LV58_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv59Exp")
	public static int LV59_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv60Exp")
	public static int LV60_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv61Exp")
	public static int LV61_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv62Exp")
	public static int LV62_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv63Exp")
	public static int LV63_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv64Exp")
	public static int LV64_EXP = 1;

	@Configure(file = CHARACTER, key = "Lv65Exp")
	public static int LV65_EXP = 2;

	@Configure(file = CHARACTER, key = "Lv66Exp")
	public static int LV66_EXP = 2;

	@Configure(file = CHARACTER, key = "Lv67Exp")
	public static int LV67_EXP = 2;

	@Configure(file = CHARACTER, key = "Lv68Exp")
	public static int LV68_EXP = 2;

	@Configure(file = CHARACTER, key = "Lv69Exp")
	public static int LV69_EXP = 2;

	@Configure(file = CHARACTER, key = "Lv70Exp")
	public static int LV70_EXP = 4;

	@Configure(file = CHARACTER, key = "Lv71Exp")
	public static int LV71_EXP = 4;

	@Configure(file = CHARACTER, key = "Lv72Exp")
	public static int LV72_EXP = 4;

	@Configure(file = CHARACTER, key = "Lv73Exp")
	public static int LV73_EXP = 4;

	@Configure(file = CHARACTER, key = "Lv74Exp")
	public static int LV74_EXP = 4;

	@Configure(file = CHARACTER, key = "Lv75Exp")
	public static int LV75_EXP = 8;

	@Configure(file = CHARACTER, key = "Lv76Exp")
	public static int LV76_EXP = 8;

	@Configure(file = CHARACTER, key = "Lv77Exp")
	public static int LV77_EXP = 8;

	@Configure(file = CHARACTER, key = "Lv78Exp")
	public static int LV78_EXP = 8;

	@Configure(file = CHARACTER, key = "Lv79Exp")
	public static int LV79_EXP = 16;

	@Configure(file = CHARACTER, key = "Lv80Exp")
	public static int LV80_EXP = 32;

	@Configure(file = CHARACTER, key = "Lv81Exp")
	public static int LV81_EXP = 64;

	@Configure(file = CHARACTER, key = "Lv82Exp")
	public static int LV82_EXP = 128;

	@Configure(file = CHARACTER, key = "Lv83Exp")
	public static int LV83_EXP = 256;

	@Configure(file = CHARACTER, key = "Lv84Exp")
	public static int LV84_EXP = 512;

	@Configure(file = CHARACTER, key = "Lv85Exp")
	public static int LV85_EXP = 1024;

	@Configure(file = CHARACTER, key = "Lv86Exp")
	public static int LV86_EXP = 2048;

	@Configure(file = CHARACTER, key = "Lv87Exp")
	public static int LV87_EXP = 4096;

	@Configure(file = CHARACTER, key = "Lv88Exp")
	public static int LV88_EXP = 8192;

	@Configure(file = CHARACTER, key = "Lv89Exp")
	public static int LV89_EXP = 16384;

	@Configure(file = CHARACTER, key = "Lv90Exp")
	public static int LV90_EXP = 32768;

	@Configure(file = CHARACTER, key = "Lv91Exp")
	public static int LV91_EXP = 65536;

	@Configure(file = CHARACTER, key = "Lv92Exp")
	public static int LV92_EXP = 131072;

	@Configure(file = CHARACTER, key = "Lv93Exp")
	public static int LV93_EXP = 262144;

	@Configure(file = CHARACTER, key = "Lv94Exp")
	public static int LV94_EXP = 524288;

	@Configure(file = CHARACTER, key = "Lv95Exp")
	public static int LV95_EXP = 1048576;

	@Configure(file = CHARACTER, key = "Lv96Exp")
	public static int LV96_EXP = 2097152;

	@Configure(file = CHARACTER, key = "Lv97Exp")
	public static int LV97_EXP = 4194304;

	@Configure(file = CHARACTER, key = "Lv98Exp")
	public static int LV98_EXP = 8388608;

	@Configure(file = CHARACTER, key = "Lv99Exp")
	public static int LV99_EXP = 16777216;

	@Configure(file = PACK, key = "Autoentication", isOptional = true)
	public static boolean LOGINS_TO_AUTOENTICATION = false;

	@Configure(file = PACK, key = "RSA_KEY_E", isOptional = true)
	public static String RSA_KEY_E = null;

	@Configure(file = PACK, key = "RSA_KEY_N", isOptional = true)
	public static String RSA_KEY_D = null;
	//

	/** その他の設定 */

	// NPCから吸えるMP限界
	public static final int MANA_DRAIN_LIMIT_PER_NPC = 40;

	// 一回の攻撃で吸えるMP限界(SOM、鋼鉄SOM）
	public static final int MANA_DRAIN_LIMIT_PER_SOM_ATTACK = 9;

	private static void validate() {
		if (!IntRange.includes(Config.ALT_ITEM_DELETION_RANGE, 0, 5)) {
			throw new IllegalStateException("ItemDeletionRangeの値が設定可能範囲外です。");
		}

		if (!IntRange.includes(Config.ALT_ITEM_DELETION_TIME, 1, 35791)) {
			throw new IllegalStateException("ItemDeletionTimeの値が設定可能範囲外です。");
		}
	}

	static void afterLoad() {
		CLIENT_LANGUAGE_CODE = LANGUAGE_CODE_ARRAY[CLIENT_LANGUAGE];
	}

	public static void load() {
		new ConfigLoader().load(Config.class);
		afterLoad();
		validate();
	}

	private Config() {

	}
}