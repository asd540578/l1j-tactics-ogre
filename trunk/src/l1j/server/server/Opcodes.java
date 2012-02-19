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


package l1j.server.server;

public class Opcodes {

	public Opcodes() {
	}
	/**3.3C ClientPacket*/
	public static final int C_OPCODE_EXIT_GHOST = 0;//要求退出觀看模式
	public static final int C_OPCODE_RETURNTOLOGIN = 1;//要求回到選人畫面
	public static final int C_OPCODE_LOGINTOSERVER = 5;//要求進入遊戲
	public static final int C_OPCODE_HIRESOLDIER = 7;//要求僱傭傭兵列表(購買)
	public static final int C_OPCODE_BOOKMARKDELETE = 8;//要求刪除記憶座標
	public static final int C_OPCODE_DROPITEM = 10;//要求丟棄物品
	public static final int C_OPCODE_RESULT = 11;//要求列表物品取得 /3.3C領取寵物
	public static final int C_OPCODE_SELECTTARGET = 13;//要求攻擊指定物件(寵物&召喚)
	public static final int C_OPCODE_COMMONCLICK = 14 ;//要求下一步 ( 公告資訊 )
	public static final int C_OPCODE_SETCASTLESECURITY = 15;//要求設置治安管理
	public static final int C_OPCODE_CLAN = 16;////要求血盟數據(例如盟標)
	public static final int C_OPCODE_FIX_WEAPON_LIST = 18;//要求維修物品清單
	public static final int C_OPCODE_USESKILL = 19;//要求使用技能
	public static final int C_OPCODE_TRADEADDCANCEL = 21;//要求取消交易(個人商店)
	public static final int C_OPCODE_CHANGEPASS = 22;//要求變更賬號密碼(登陸界面)
	public static final int C_OPCODE_DEPOSIT = 24;//要求存入資金
	public static final int C_OPCODE_TRADE = 25;//要求交易(個人)
	public static final int C_OPCODE_MOVE_CHECK = 26;//人物移動回碩檢測 <= 3.3C added
	public static final int C_OPCODE_ENTERPORTAL = 27;//要求傳送 (進入地監)
	public static final int C_OPCODE_DRAWAL = 28;//要求領出資金
	public static final int C_OPCODE_SECOND_PLEDGE = 31;//第二次要求查詢血盟成員
	public static final int C_OPCODE_RANK = 31;//要求給予角色血盟階級
	public static final int C_OPCODE_TRADEADDOK = 32;//要求完成交易(個人)
	public static final int C_OPCODE_PLEDGE = 33;//要求查詢血盟成員
	public static final int C_OPCODE_QUITGAME = 35;//要求離開遊戲
	public static final int C_OPCODE_BANCLAN = 36;//要求驅逐人物離開血盟
	public static final int C_OPCODE_WAREHOUSELOCK = 37;//要求變更倉庫密碼 && 送出倉庫密碼
	public static final int C_OPCODE_TITLE = 39;//要求賦予封號
	public static final int C_OPCODE_PICKUPITEM = 41;//要求撿取物品
	public static final int C_OPCODE_CHARRESET = 42;//要求重置人物點數
	public static final int C_OPCODE_NEWCHAR = 43;//要求創造角色 
	public static final int C_OPCODE_DOOR = 44;//要求開關門
	public static final int C_OPCODE_PETMENU = 45;//要求寵物回報選單
	public static final int C_OPCODE_CLIENTVERSION = 46; //要求登入測試 (接收伺服器版本)
	public static final int C_OPCODE_CREATECLAN = 48;//要求創立血盟
	public static final int C_OPCODE_CHANGECHAR = 50;//要求切換角色
	public static final int C_OPCODE_USEITEM = 51;//要求使用物品
	public static final int C_OPCODE_SKILLBUYOK = 52;//要求學習魔法 完成
	public static final int C_OPCODE_UNKOWN1 = 53;//用戶端自動請求在線公告
	public static final int C_OPCODE_NPCTALK = 55;//要求物件對話視窗
	public static final int C_OPCODE_TELEPORT = 56;//要求傳送 更新周圍物件 (無動畫傳送後)
	public static final int C_OPCODE_SHIP = 58;//要求下船
	public static final int C_OPCODE_CHANGEWARTIME = 102;//修正城堡總管全部功能
	public static final int C_OPCODE_USEPETITEM = 60;//要求使用寵物裝備
	public static final int C_OPCODE_SKILLBUY = 63;//要求學習魔法(金幣)
	public static final int C_OPCODE_ADDBUDDY = 64;//要求新增好友
	public static final int C_OPCODE_BOARDWRITE = 65;//要求寫入公佈欄訊息
	public static final int C_OPCODE_BOARDNEXT = 66;//要求下一頁 (公佈欄)
	public static final int C_OPCODE_FISHCLICK = 67;//要求釣魚收竿
	public static final int C_OPCODE_LEAVECLANE = 69;//要求脫離血盟
	public static final int C_OPCODE_LOGINTOSERVEROK = 70;//登入伺服器OK
	public static final int C_OPCODE_BUDDYLIST = 71;//要求查詢朋友名單
	public static final int C_OPCODE_MOVECHAR = 73;//要求角色移動
	public static final int C_OPCODE_ATTR = 74;//要求點選項目的結果
	public static final int C_OPCODE_BOARDDELETE = 75;//要求刪除公佈欄內容
	public static final int C_OPCODE_DELEXCLUDE = 76;//要求使用開啟名單(拒絕指定人物訊息)
	public static final int C_OPCODE_EXCLUDE = 76;//要求使用拒絕名單(開啟指定人物訊息)
	public static final int C_OPCODE_CHATGLOBAL = 77;//要求使用廣播聊天頻道
	public static final int C_OPCODE_PROPOSE = 78;//要求結婚
	public static final int C_OPCODE_TRADEADDITEM = 79;//要求交易(添加物品)
	public static final int C_OPCODE_CASTLESECURITY = 81;//要求治安管理  OK
	public static final int C_OPCODE_SHOP = 82;//要求開個人商店
	public static final int C_OPCODE_CHAT = 83;//要求使用一般聊天頻道
	public static final int C_OPCODE_PUTSOLDIER = 84;//要求配置已僱用士兵
	public static final int C_OPCODE_LEAVEPARTY = 85;//要求脫離隊伍
	public static final int C_OPCODE_PARTYLIST = 86;//要求查看隊伍
	public static final int C_OPCODE_SENDLOCATION = 87; // 地圖(3.3C 新增)
	public static final int C_OPCODE_BOARDREAD = 88;//要求閱讀佈告單個欄訊息
	public static final int C_OPCODE_CALL = 89;//要求召喚到身邊(gm)
	public static final int C_OPCODE_WAR = 91;//要求宣戰
	public static final int C_OPCODE_CHECKPK = 92;//要求查詢PK次數
	public static final int C_OPCODE_CHANGEHEADING = 93;//要求改變角色面向
	public static final int C_OPCODE_AMOUNT = 94;//要求確定數量選取
	public static final int C_OPCODE_WHO = 95;//要求查詢遊戲人數
	public static final int C_OPCODE_FIGHT = 96;//要求決鬥
	public static final int C_OPCODE_NPCACTION = 97;//要求物件對話視窗結果
	public static final int C_OPCODE_CHARACTERCONFIG = 100;//要求紀錄快速鍵
	public static final int C_OPCODE_ATTACK = 101;//要求角色攻擊
	public static final int C_OPCODE_SELECTWARTIME = 102;//要求選擇 變更攻城時間(but3.3C無使用)
	public static final int C_OPCODE_BOARD = 103;//要求讀取公佈欄
	public static final int C_OPCODE_PRIVATESHOPLIST = 104;//要求個人商店 （物品列表）
	public static final int C_OPCODE_LOGINPACKET = 105;//要求登入伺服器
	public static final int C_OPCODE_SELECTLIST = 106;//要求物品維修
	public static final int C_OPCODE_MAIL = 107;//要求打開郵箱
	public static final int C_OPCODE_EXTCOMMAND = 108;//要求角色表情動作
	public static final int C_OPCODE_DELETECHAR = 110;//要求刪除角色
	public static final int C_OPCODE_DELBUDDY = 112;//要求刪除好友
	public static final int C_OPCODE_ARROWATTACK = 113;//要求使用遠距武器
	public static final int C_OPCODE_EMBLEM = 114;//要求上傳盟標
	public static final int C_OPCODE_BANPARTY = 115;//要求踢出隊伍
	public static final int C_OPCODE_CHATWHISPER = 116;//要求使用密語聊天頻道
	public static final int C_OPCODE_SMS = 117;//要求寄送簡訊
	public static final int C_OPCODE_PUTHIRESOLDIER = 118;//要求配置已僱傭傭兵 OK
	public static final int C_OPCODE_BOOKMARK = 119;//要求增加記憶座標
	public static final int C_OPCODE_PUTBOWSOLDIER = 120;//要求配置城牆上弓手
	public static final int C_OPCODE_KEEPALIVE = 121;//要求更新時間
	public static final int C_OPCODE_TAXRATE = 122;//要求稅收設定封包
	public static final int C_OPCODE_GIVEITEM = 124;//要求給予物品
	public static final int C_OPCODE_JOINCLAN = 125;//要求加入血盟
	public static final int C_OPCODE_DELETEINVENTORYITEM = 126;//要求刪除物品
	public static final int C_OPCODE_RESTART = 127;//要求死亡後重新開始
	public static final int C_OPCODE_CREATEPARTY = 130;//要求邀請加入隊伍(要求創立隊伍)
	public static final int C_OPCODE_CAHTPARTY = 131;//要求人物移出隊伍
	
	/**3.2C ServerPacket*/
	public static final int S_OPCODE_COMMONNEWS2 = 0;
	public static final int S_OPCODE_USEMAP = 71;
	public static final int S_LETTER = 90;

	/**3.3C ServerPacket*/
	public static final int S_OPCODE_BLUEMESSAGE = 0;//藍色訊息 (使用String-h.tbl)
	public static final int S_OPCODE_BLESSOFEVA = 1;//效果圖示 (水底呼吸)
	public static final int S_OPCODE_NPCSHOUT = 3;//非玩家聊天頻道 (一般 & 大喊 )NPC
	public static final int S_OPCODE_RESURRECTION = 4;//物件復活
	public static final int S_OPCODE_BOARDREAD = 5;//佈告欄(訊息閱讀)
	public static final int S_OPCODE_CASTLEMASTER = 6;//角色皇冠 3.3C
	public static final int S_OPCODE_FIX_WEAPON_MENU = 7;//修理武器清單 (3.3C新增)
	public static final int S_OPCODE_SELECTLIST = 7;//損壞武器名單
	public static final int S_OPCODE_ADDSKILL = 8;//增加魔法進魔法名單
	public static final int S_OPCODE_CHARVISUALUPDATE = 9;//物件動作種類 (長時間)
	public static final int S_OPCODE_COMMONNEWS = 10;//公告視窗
	public static final int S_OPCODE_CHARAMOUNT = 11;//角色列表
	public static final int S_OPCODE_PARALYSIS = 12;//魔法效果 : 詛咒類 {編號 }麻痺,癱瘓
	public static final int S_OPCODE_REDMESSAGE = 13; //畫面正中出現紅色/新增未使用
	public static final int S_OPCODE_INPUTAMOUNT = 14;//拍賣公告欄選取金幣數量 (選取物品數量)
	public static final int S_OPCODE_SKILLSOUNDGFX = 15;//產生動畫 [物件]
	public static final int S_OPCODE_IDENTIFYDESC = 16;//物品資訊訊息 {使用String-h.tbl}
	public static final int S_OPCODE_EFFECTLOCATION = 18;//產生動畫 [地點]
	public static final int S_OPCODE_MAIL = 19;//郵件封包
	public static final int S_OPCODE_SHOWRETRIEVELIST = 21;//倉庫物品名單
	public static final int S_OPCODE_HOUSELIST = 22;//血盟小屋名單
	public static final int S_OPCODE_SKILLBUY = 23;//魔法購買 (金幣)
	public static final int S_OPCODE_GLOBALCHAT = 24;//廣播聊天頻道
	public static final int S_OPCODE_SYSMSG = 24;//廣播聊天頻道/伺服器訊息(字串)
	public static final int S_OPCODE_CURSEBLIND = 25;//魔法效果 - 暗盲咒術 {編號}
	public static final int S_OPCODE_INVLIST = 26;//道具欄全物品
	public static final int S_OPCODE_CHARPACK = 27;//物件封包
	public static final int S_OPCODE_DROPITEM = 27;//丟棄物品封包
	public static final int S_OPCODE_SERVERMSG = 29;//伺服器訊息 (行數)/(行數, 附加字串 )
	public static final int S_OPCODE_NEWCHARPACK = 31;//創造角色封包
	public static final int S_OPCODE_DELSKILL = 34;//移除魔法出魔法名單
	public static final int S_OPCODE_LOGINTOGAME = 35;//進入遊戲
	public static final int S_OPCODE_WHISPERCHAT = 36;//要求使用密語聊天頻道
	public static final int S_OPCODE_DRAWAL = 37;//取出城堡寶庫金幣
	public static final int S_OPCODE_CHARLIST = 38;//角色資訊
	public static final int S_OPCODE_EMBLEM = 39;//角色盟徽
	public static final int S_OPCODE_ATTACKPACKET = 40;//物件攻擊
	public static final int S_OPCODE_SPMR = 42;//魔法攻擊力與魔法防禦力
	public static final int S_OPCODE_OWNCHARSTATUS = 43;//角色屬性與能力值
	public static final int S_OPCODE_RANGESKILLS = 44;//範圍魔法
	public static final int S_OPCODE_SHOWSHOPSELLLIST = 45;//NPC物品販賣
	public static final int S_OPCODE_INVIS = 47;//物件隱形 & 現形
	public static final int S_OPCODE_NORMALCHAT = 48;//一般聊天頻道
	public static final int S_OPCODE_SKILLHASTE = 49;//魔法|物品效果 {加速纇}
	public static final int S_OPCODE_TAXRATE = 50;//稅收設定封包
	public static final int S_OPCODE_WEATHER = 51;//遊戲天氣
	public static final int S_OPCODE_HIRESOLDIER = 52;//僱傭傭兵 傭兵名單
	public static final int S_OPCODE_WAR = 53;//血盟戰爭訊息 {編號,血盟名稱,目標血盟名稱}
	public static final int S_OPCODE_TELEPORTLOCK = 54;//人物回碩檢測  OR 傳送鎖定 (無動畫)
	public static final int S_OPCODE_PINKNAME = 55;//角色名稱變紫色
	public static final int S_OPCODE_ITEMSTATUS = 56;//物品狀態更新
	public static final int S_OPCODE_ITEMAMOUNT = 56;//物品可用次數
	public static final int S_OPCODE_PRIVATESHOPLIST = 57;//角色個人商店 {購買}
	public static final int S_OPCODE_DETELECHAROK = 58;//角色移除 [非立即]/7天後
	public static final int S_OPCODE_BOOKMARKS = 59;//角色座標名單
	public static final int S_OPCODE_INITPACKET = 60;//初始化OpCodes
	public static final int S_OPCODE_MOVEOBJECT = 62;//物件移動
	public static final int S_OPCODE_PUTSOLDIER = 63;//配置已僱用士兵
	public static final int S_OPCODE_TELEPORT = 64;//要求傳送 (有動畫)
	public static final int S_OPCODE_STRUP = 65;//力量提升封包
	public static final int S_OPCODE_LAWFUL = 66;//正義值更新
	public static final int S_OPCODE_SELECTTARGET = 67;//選擇一個目標
	public static final int S_OPCODE_ABILITY = 68;//戒指
	public static final int S_OPCODE_HPMETER = 69;//物件血條
	public static final int S_OPCODE_ATTRIBUTE = 70;//物件屬性
	public static final int S_OPCODE_SERVERVERSION = 72;//伺服器版本
	public static final int S_OPCODE_EXP = 73;//經驗值更新封包
	public static final int S_OPCODE_MPUPDATE = 74;//魔力更新
	public static final int S_OPCODE_CHANGENAME = 75;//改變物件名稱
	public static final int S_OPCODE_POLY = 76;//改變外型
	public static final int S_OPCODE_MAPID = 77;//更新角色所在的地圖
	public static final int S_OPCODE_ITEMCOLOR = 79;//物品狀態
	public static final int S_OPCODE_OWNCHARATTRDEF = 80;//角色防禦 & 屬性 更新
	public static final int S_OPCODE_PACKETBOX = 82;//角色選擇視窗/開啟拒絕名單(封包盒子)
	public static final int S_OPCODE_ACTIVESPELLS = 82;
	public static final int S_OPCODE_SKILLICONGFX = 82;
	public static final int S_OPCODE_UNKNOWN2 = 82;
	public static final int S_OPCODE_DELETEINVENTORYITEM = 83;//物品刪除
	public static final int S_OPCODE_RESTART = 84;//角色重新選擇 返回選人畫面 功能未知
	public static final int S_OPCODE_PINGTIME = 85;//Ping Time
	public static final int S_OPCODE_DEPOSIT = 86;//存入資金城堡寶庫 (2)
	public static final int S_OPCODE_TRUETARGET = 88;//魔法動畫 {精準目標}
	public static final int S_OPCODE_HOUSEMAP = 89;//血盟小屋地圖 [地點]
	public static final int S_OPCODE_CHARTITLE = 90;//角色封號
	public static final int S_OPCODE_DEXUP = 92;//敏捷提升封包
	public static final int S_OPCODE_CHANGEHEADING = 94;//物件面向
	public static final int S_OPCODE_BOARD = 96;//佈告欄 (訊息列表)
	public static final int S_OPCODE_LIQUOR = 97;//海底波紋
	public static final int S_OPCODE_TRADESTATUS = 99;//交易狀態
	public static final int S_OPCODE_SPOLY = 100;//特別變身封包
	public static final int S_OPCODE_UNDERWATER = 101;//更新角色所在的地圖 （水下）
	public static final int S_OPCODE_SKILLBRAVE = 102;//魔法|物品效果圖示 {勇敢藥水類}
	public static final int S_OPCODE_PUTHIRESOLDIER = 103;//配置傭兵
	public static final int S_OPCODE_POISON = 104 ;//魔法效果:中毒 { 編號 }
	public static final int S_OPCODE_DISCONNECT = 105;//立即中斷連線
	public static final int S_OPCODE_NEWCHARWRONG = 106;//角色創造失敗
	public static final int S_OPCODE_REMOVE_OBJECT = 107;//物件刪除
	public static final int S_OPCODE_NPC_ATTACKPACKET = 108;//NPC攻擊 用於特殊攻擊?!
	public static final int S_OPCODE_ADDITEM = 110;//物品增加封包
	public static final int S_OPCODE_TRADE = 111;//交易封包
	public static final int S_OPCODE_OWNCHARSTATUS2 = 112;//角色狀態 (2)
	public static final int S_OPCODE_SHOWHTML = 113;//產生對話視窗
	public static final int S_OPCODE_SKILLICONSHIELD = 114;//魔法效果 : 防禦纇
	public static final int S_OPCODE_DOACTIONGFX = 115;//物件動作種類 (短時間)
	public static final int S_OPCODE_TRADEADDITEM = 116;//增加交易物品封包
	public static final int S_OPCODE_YES_NO = 117;//選項封包 {Yes || No}
	public static final int S_OPCODE_HPUPDATE = 118;//體力更新
	public static final int S_OPCODE_SHOWSHOPBUYLIST = 119;//物品購買
	public static final int S_OPCODE_GAMETIME = 120;//更新目前遊戲時間 ( 遊戲時間 )
	public static final int S_OPCODE_PETCTRL = 121;//寵物控制介面移除
	public static final int S_OPCODE_CHARRESET = 121; //角色重置
	public static final int S_OPCODE_SOUND = 122;//撥放音效
	public static final int S_OPCODE_LIGHT = 123;//物件亮度
	public static final int S_OPCODE_LOGINRESULT = 124;//登入狀態
	public static final int S_OPCODE_PUTBOWSOLDIERLIST = 125;//配置牆上弓手
	public static final int S_OPCODE_WARTIME = 126;//攻城時間設定
	public static final int S_OPCODE_ITEMNAME = 127;//物品顯示名稱

/*
	// 3.0 ClientPacket
	public static final int C_OPCODE_BOOKMARK = 0;

	public static final int C_OPCODE_FIGHT = 3;

	public static final int C_OPCODE_KEEPALIVE = 4;

	public static final int C_OPCODE_ATTACK = 5;

	public static final int C_OPCODE_CHANGEHEADING = 6;

	public static final int C_OPCODE_PICKUPITEM = 7;

	public static final int C_OPCODE_SHOP = 8;

	public static final int C_OPCODE_DELBUDDY = 10;

	public static final int C_OPCODE_LEAVEPARTY = 11;

	public static final int C_OPCODE_CHARACTERCONFIG = 14;

	public static final int C_OPCODE_MOVECHAR = 15;

	public static final int C_OPCODE_CHANGECHAR = 16;

	public static final int C_OPCODE_PRIVATESHOPLIST = 17;

	public static final int C_OPCODE_CHAT = 18;

	public static final int C_OPCODE_BOARDREAD = 19;

	public static final int C_OPCODE_TRADEADDITEM = 20;

	public static final int C_OPCODE_PROPOSE = 22;

	public static final int C_OPCODE_HIRESOLDIER = 23;

	public static final int C_OPCODE_BOARD = 24;

	public static final int C_OPCODE_LOGINTOSERVEROK = 25;

	public static final int C_OPCODE_ENTERPORTAL = 26;

	public static final int C_OPCODE_LEAVECLANE = 27;

	public static final int C_OPCODE_CALL = 29;

	public static final int C_OPCODE_TRADE = 30;

	public static final int C_OPCODE_SKILLBUYOK = 31;

	public static final int C_OPCODE_DELEXCLUDE = 32;

	public static final int C_OPCODE_SHIP = 33;

	public static final int C_OPCODE_CLIENTVERSION = 34;

	public static final int C_OPCODE_EXTCOMMAND = 38;

	public static final int C_OPCODE_TRADEADDCANCEL = 41;

	public static final int C_OPCODE_DRAWAL = 42;

	public static final int C_OPCODE_COMMONCLICK = 46;

	public static final int C_OPCODE_SELECTTARGET = 47;

	public static final int C_OPCODE_NEWCHAR = 50;

	public static final int C_OPCODE_FIX_WEAPON_LIST = 51;

	public static final int C_OPCODE_DROPITEM = 52;

	public static final int C_OPCODE_DELETECHAR = 54;

	public static final int C_OPCODE_ADDBUDDY = 56;

	public static final int C_OPCODE_WHO = 57;

	public static final int C_OPCODE_BOARDDELETE = 60;

	public static final int C_OPCODE_TRADEADDOK = 61;

	public static final int C_OPCODE_CREATECLAN = 62;

	public static final int C_OPCODE_ATTR = 63;

	public static final int C_OPCODE_ARROWATTACK = 64;

	public static final int C_OPCODE_NPCACTION = 65;

	public static final int C_OPCODE_TITLE = 66;

	public static final int C_OPCODE_DEPOSIT = 68;

	public static final int C_OPCODE_DELETEINVENTORYITEM = 69;

	public static final int C_OPCODE_CHECKPK = 70;

	public static final int C_OPCODE_BANPARTY = 72;

	public static final int C_OPCODE_CLAN = 73;

	public static final int C_OPCODE_DOOR = 75;

	public static final int C_OPCODE_PLEDGE = 76;

	public static final int C_OPCODE_PARTY = 77;

	public static final int C_OPCODE_RANK = 78;

	public static final int C_OPCODE_TELEPORT = 79;

	public static final int C_OPCODE_CHARRESET = 80;

	public static final int C_OPCODE_RESTART = 82;

	public static final int C_OPCODE_PETMENU = 83;

	public static final int C_OPCODE_BOARDWRITE = 84;

	public static final int C_OPCODE_GIVEITEM = 85;

	public static final int C_OPCODE_BOARDBACK = 87;

	public static final int C_OPCODE_LOGINTOSERVER = 89;

	public static final int C_OPCODE_CHATWHISPER = 92;

	public static final int C_OPCODE_SKILLBUY = 93;

	public static final int C_OPCODE_JOINCLAN = 94;

	public static final int C_OPCODE_RETURNTOLOGIN = 95;

	public static final int C_OPCODE_CHANGEWARTIME = 98;

	public static final int C_OPCODE_WAR = 101;

	public static final int C_OPCODE_BANCLAN = 103;

	public static final int C_OPCODE_RESULT = 104;

	public static final int C_OPCODE_BUDDYLIST = 109;

	public static final int C_OPCODE_TAXRATE = 110;

	public static final int C_OPCODE_USEPETITEM = 111;

	public static final int C_OPCODE_SELECTLIST = 112;

	public static final int C_OPCODE_LOGINPACKET = 113;

	public static final int C_OPCODE_QUITGAME = 114;

	public static final int C_OPCODE_CHATGLOBAL = 115;

	public static final int C_OPCODE_EXCLUDE = 116;

	public static final int C_OPCODE_NPCTALK = 118;

	public static final int C_OPCODE_USEITEM = 119;

	public static final int C_OPCODE_EMBLEM = 120;

	public static final int C_OPCODE_EXIT_GHOST = 121;

	public static final int C_OPCODE_AMOUNT = 124;

	public static final int C_OPCODE_FISHCLICK = 125;

	public static final int C_OPCODE_MAIL = 127;

	public static final int C_OPCODE_BOOKMARKDELETE = 128;

	public static final int C_OPCODE_USESKILL = 129;

	public static final int C_OPCODE_CREATEPARTY = 130;

	public static final int C_OPCODE_CAHTPARTY = 131;

	// 3.0 ServerPacket
	public static final int S_OPCODE_REMOVE_OBJECT = 0;

	public static final int S_OPCODE_CHARPACK = 1;

	public static final int S_OPCODE_DROPITEM = 1;

	public static final int S_OPCODE_POLY = 2;

	public static final int S_OPCODE_SYSMSG = 3;

	public static final int S_OPCODE_GLOBALCHAT = 3;

	public static final int S_OPCODE_DOACTIONGFX = 6;

	public static final int S_OPCODE_EMBLEM = 7;

	public static final int S_OPCODE_INVLIST = 8;

	public static final int S_OPCODE_ITEMNAME = 9;

	public static final int S_OPCODE_POISON = 10;

	public static final int S_OPCODE_TELEPORT = 11;

	public static final int S_OPCODE_SHOWSHOPSELLLIST = 12;

	public static final int S_OPCODE_CHARVISUALUPDATE = 13;

	public static final int S_OPCODE_USEMAP = 14;

	public static final int S_OPCODE_CHANGEHEADING = 15;

	public static final int S_OPCODE_BLESSOFEVA = 17;

	public static final int S_OPCODE_SELECTLIST = 18;

	public static final int S_OPCODE_OWNCHARSTATUS2 = 19;

	public static final int S_OPCODE_SKILLBRAVE = 20;

	public static final int S_OPCODE_TRADEADDITEM = 21;

	public static final int S_OPCODE_INVIS = 22;

	public static final int S_OPCODE_SHOWRETRIEVELIST = 24;

	// ITEMAMOUNTとITEMSTATUSは同じ?
	public static final int S_OPCODE_ITEMAMOUNT = 25;

	public static final int S_OPCODE_ITEMSTATUS = 25;

	public static final int S_OPCODE_WARTIME = 26;

	public static final int S_OPCODE_CHARRESET = 27;

	public static final int S_OPCODE_ADDSKILL = 28;

	public static final int S_OPCODE_NEWCHARWRONG = 29;

	public static final int S_OPCODE_WEATHER = 31;

	public static final int S_OPCODE_CHARTITLE = 32;

	public static final int S_OPCODE_ADDITEM = 33;

	public static final int S_OPCODE_HPUPDATE = 34;

	public static final int S_OPCODE_ATTACKPACKET = 35;

	public static final int S_OPCODE_SHOWHTML = 37;

	public static final int S_OPCODE_CHANGENAME = 38;

	public static final int S_OPCODE_NEWMASTER = 39;

	public static final int S_OPCODE_DISCONNECT = 41;

	public static final int S_OPCODE_LIQUOR = 43;

	public static final int S_OPCODE_RESURRECTION = 44;

	public static final int S_OPCODE_PUTSOLDIER = 45;

	public static final int S_OPCODE_SHOWSHOPBUYLIST = 46;

	public static final int S_OPCODE_WHISPERCHAT = 47;

	public static final int S_OPCODE_SKILLBUY = 48;

	public static final int S_OPCODE_SKILLHASTE = 49;

	public static final int S_OPCODE_NPCSHOUT = 50;

	public static final int S_OPCODE_DEXUP = 51;

	public static final int S_OPCODE_SPMR = 52;

	public static final int S_OPCODE_TRADE = 53;

	public static final int S_OPCODE_SERVERSTAT = 55;

	public static final int S_OPCODE_NEWCHARPACK = 56;

	public static final int S_OPCODE_DELSKILL = 57;

	public static final int S_OPCODE_GAMETIME = 58;

	public static final int S_OPCODE_OWNCHARSTATUS = 59;

	public static final int S_OPCODE_EXP = 95;

	public static final int S_OPCODE_DEPOSIT = 60;

	public static final int S_OPCODE_SELECTTARGET = 61;

	public static final int S_OPCODE_PACKETBOX = 62;

	public static final int S_OPCODE_ACTIVESPELLS = 62;

	public static final int S_OPCODE_SKILLICONGFX = 62;

	public static final int S_OPCODE_LOGINRESULT = 63;

	public static final int S_OPCODE_BLUEMESSAGE = 65;

	public static final int S_OPCODE_COMMONNEWS = 66;

	public static final int S_OPCODE_DRAWAL = 67;

	public static final int S_OPCODE_HIRESOLDIER = 68;

	public static final int S_OPCODE_EFFECTLOCATION = 69;

	public static final int S_OPCODE_TRUETARGET = 70;

	public static final int S_OPCODE_NORMALCHAT = 71;

	public static final int S_OPCODE_HOUSELIST = 72;

	public static final int S_OPCODE_MAPID = 73;

	public static final int S_OPCODE_UNDERWATER = 73;

	public static final int S_OPCODE_DELETEINVENTORYITEM = 75;

	public static final int S_OPCODE_CHARAMOUNT = 80;

	public static final int S_OPCODE_PARALYSIS = 81;

	public static final int S_OPCODE_ATTRIBUTE = 82;

	public static final int S_OPCODE_SOUND = 83;

	public static final int S_OPCODE_DETELECHAROK = 84;

	public static final int S_OPCODE_TELEPORTLOCK = 85;

	public static final int S_OPCODE_ABILITY = 86;

	public static final int S_OPCODE_PINKNAME = 87;

	public static final int S_OPCODE_SERVERVERSION = 89;

	public static final int S_OPCODE_BOARDREAD = 91;

	public static final int S_OPCODE_MPUPDATE = 92;

	public static final int S_OPCODE_BOARD = 93;

	public static final int S_OPCODE_WAR = 94;

	public static final int S_OPCODE_OWNCHARATTRDEF = 96;

	public static final int S_OPCODE_RESTART = 97;

	public static final int S_OPCODE_SERVERMSG = 98;

	public static final int S_OPCODE_IDENTIFYDESC = 99;

	public static final int S_OPCODE_PINGTIME = 100;

	public static final int S_OPCODE_SKILLSOUNDGFX = 101;

	public static final int S_OPCODE_CHARLIST = 102;

	public static final int S_OPCODE_BOOKMARKS = 103;

	public static final int S_OPCODE_HPMETER = 104;

	public static final int S_OPCODE_YES_NO = 105;

	public static final int S_OPCODE_STRUP = 106;

	public static final int S_OPCODE_ITEMCOLOR = 107;

	public static final int S_OPCODE_CURSEBLIND = 110;

	public static final int S_OPCODE_CASTLEMASTER = 111;

	public static final int S_OPCODE_RANGESKILLS = 112;

	public static final int S_OPCODE_HOUSEMAP = 113;

	public static final int S_OPCODE_SKILLICONSHIELD = 114;

	public static final int S_OPCODE_PRIVATESHOPLIST = 115;

	public static final int S_OPCODE_UNKNOWN1 = 116;

	public static final int S_OPCODE_CHARLOCK = 117;

	public static final int S_OPCODE_LAWFUL = 119;

	public static final int S_OPCODE_TAXRATE = 120;

	public static final int S_OPCODE_TRADESTATUS = 122;

	public static final int S_OPCODE_INPUTAMOUNT = 123;

	public static final int S_OPCODE_LIGHT = 124;

	public static final int S_OPCODE_MOVEOBJECT = 126;

	public static final int S_OPCODE_MAIL = 127;
*/
	
	/*
	 * // clientpackets public static final int C_OPCODE_USEITEM = 0;
	 * 
	 * public static final int C_OPCODE_EXCLUDE = 2;
	 * 
	 * public static final int C_OPCODE_CHARACTERCONFIG = 5;
	 * 
	 * public static final int C_OPCODE_CHANGECHAR = 9;
	 * 
	 * public static final int C_OPCODE_SHIP = 10;
	 * 
	 * public static final int C_OPCODE_RANK = 11;
	 * 
	 * public static final int C_OPCODE_MOVECHAR = 12;
	 * 
	 * public static final int C_OPCODE_TAXRATE = 13;
	 * 
	 * public static final int C_OPCODE_WAR = 14;
	 * 
	 * public static final int C_OPCODE_CHATGLOBAL = 15;
	 * 
	 * public static final int C_OPCODE_CLIENTVERSION = 17;
	 * 
	 * public static final int C_OPCODE_AMOUNT = 19;
	 * 
	 * public static final int C_OPCODE_BOARDBACK = 20;
	 * 
	 * public static final int C_OPCODE_ADDBUDDY = 21;
	 * 
	 * public static final int C_OPCODE_FIX_WEAPON_LIST = 22;
	 * 
	 * public static final int C_OPCODE_LOGINPACKET = 23;
	 * 
	 * public static final int C_OPCODE_TRADEADDCANCEL = 25;
	 * 
	 * public static final int C_OPCODE_CHATWHISPER = 27;
	 * 
	 * public static final int C_OPCODE_TRADEADDITEM = 28;
	 * 
	 * public static final int C_OPCODE_JOINCLAN = 29;
	 * 
	 * public static final int C_OPCODE_KEEPALIVE = 30;
	 * 
	 * public static final int C_OPCODE_NEWCHAR = 31;
	 * 
	 * public static final int C_OPCODE_PLEDGE = 32;
	 * 
	 * public static final int C_OPCODE_FIGHT = 34;
	 * 
	 * public static final int C_OPCODE_BOARD = 39;
	 * 
	 * public static final int C_OPCODE_FISHCLICK = 40;
	 * 
	 * public static final int C_OPCODE_ARROWATTACK = 41;
	 * 
	 * public static final int C_OPCODE_BANCLAN = 42;
	 * 
	 * public static final int C_OPCODE_DEPOSIT = 43;
	 * 
	 * public static final int C_OPCODE_PARTY = 44;
	 * 
	 * public static final int C_OPCODE_ENTERPORTAL = 45;
	 * 
	 * public static final int C_OPCODE_DELETEINVENTORYITEM = 49;
	 * 
	 * public static final int C_OPCODE_EXIT_GHOST = 50;
	 * 
	 * public static final int C_OPCODE_SKILLBUY = 51;
	 * 
	 * public static final int C_OPCODE_CHECKPK = 54;
	 * 
	 * public static final int C_OPCODE_USESKILL = 55;
	 * 
	 * public static final int C_OPCODE_SELECTLIST = 58;
	 * 
	 * public static final int C_OPCODE_PICKUPITEM = 59;
	 * 
	 * public static final int C_OPCODE_RESULT = 61;
	 * 
	 * public static final int C_OPCODE_CHANGEWARTIME = 62;
	 * 
	 * public static final int C_OPCODE_PRIVATESHOPLIST = 63;
	 * 
	 * public static final int C_OPCODE_COMMONCLICK = 65;
	 * 
	 * public static final int C_OPCODE_RETURNTOLOGIN = 67;
	 * 
	 * public static final int C_OPCODE_ATTACK = 68;
	 * 
	 * public static final int C_OPCODE_LEAVEPARTY = 69;
	 * 
	 * public static final int C_OPCODE_SHOP = 71;
	 * 
	 * public static final int C_OPCODE_CALL = 72;
	 * 
	 * public static final int C_OPCODE_WHO = 75;
	 * 
	 * public static final int C_OPCODE_LEAVECLANE = 76;
	 * 
	 * public static final int C_OPCODE_EMBLEM = 77;
	 * 
	 * public static final int C_OPCODE_BUDDYLIST = 79;
	 * 
	 * public static final int C_OPCODE_DRAWAL = 80;
	 * 
	 * public static final int C_OPCODE_GIVEITEM = 82;
	 * 
	 * public static final int C_OPCODE_TRADE = 83;
	 * 
	 * public static final int C_OPCODE_PETMENU = 84;
	 * 
	 * public static final int C_OPCODE_TELEPORT = 85;
	 * 
	 * public static final int C_OPCODE_DELETECHAR = 87;
	 * 
	 * public static final int C_OPCODE_NPCACTION = 88;
	 * 
	 * public static final int C_OPCODE_HIRESOLDIER = 90;
	 * 
	 * public static final int C_OPCODE_BOARDDELETE = 91;
	 * 
	 * public static final int C_OPCODE_EXTCOMMAND = 92;
	 * 
	 * public static final int C_OPCODE_TITLE = 93;
	 * 
	 * public static final int C_OPCODE_DOOR = 94;
	 * 
	 * public static final int C_OPCODE_QUITGAME = 98;
	 * 
	 * public static final int C_OPCODE_PROPOSE = 99;
	 * 
	 * public static final int C_OPCODE_CREATECLAN = 100;
	 * 
	 * public static final int C_OPCODE_BOOKMARK = 101;
	 * 
	 * public static final int C_OPCODE_USEPETITEM = 103;
	 * 
	 * public static final int C_OPCODE_BOOKMARKDELETE = 104;
	 * 
	 * public static final int C_OPCODE_BANPARTY = 105;
	 * 
	 * public static final int C_OPCODE_ATTR = 112;
	 * 
	 * public static final int C_OPCODE_CHAT = 113;
	 * 
	 * public static final int C_OPCODE_SELECTTARGET = 114;
	 * 
	 * public static final int C_OPCODE_DROPITEM = 115;
	 * 
	 * public static final int C_OPCODE_BOARDREAD = 116;
	 * 
	 * public static final int C_OPCODE_RESTART = 117;
	 * 
	 * public static final int C_OPCODE_SKILLBUYOK = 118;
	 * 
	 * public static final int C_OPCODE_COMMONCLICK2 = 119; // new addition
	 * 
	 * public static final int C_OPCODE_TRADEADDOK = 120;
	 * 
	 * public static final int C_OPCODE_CHANGEHEADING = 122;
	 * 
	 * public static final int C_OPCODE_DELBUDDY = 123;
	 * 
	 * public static final int C_OPCODE_DELEXCLUDE = 124; // new addition
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVER = 125;
	 * 
	 * public static final int C_OPCODE_BOARDWRITE = 126;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVEROK = 127;
	 * 
	 * public static final int C_OPCODE_NPCTALK = 129;
	 * 
	 * public static final int C_OPCODE_CREATEPARTY = 130;
	 * 
	 * public static final int C_OPCODE_CAHTPARTY = 131; // serverpackets //
	 * public static final int S_OPCODE_NEWCHARWRONG = 0;
	 * 
	 * public static final int S_OPCODE_LETTER = 1;
	 * 
	 * public static final int S_OPCODE_RANGESKILLS = 2;
	 * 
	 * public static final int S_OPCODE_DOACTIONGFX = 3;
	 * 
	 * public static final int S_OPCODE_USEMAP = 5;
	 * 
	 * public static final int S_OPCODE_ITEMSTATUS = 6;
	 * 
	 * public static final int S_OPCODE_SELETESERVER = 7; // new addition
	 * 
	 * public static final int S_OPCODE_INVIS = 8;
	 * 
	 * public static final int S_OPCODE_CHARDELETEOK = 10; // new addition
	 * 
	 * public static final int S_OPCODE_LAWFUL = 12; // 画面中央に青い文字で「Account ・ has
	 * just logged in from」と表示される public static final int S_OPCODE_BLUEMESSAGE2 =
	 * 13;
	 * 
	 * public static final int S_OPCODE_SELECTLIST = 14;
	 * 
	 * public static final int S_OPCODE_BOARDREAD = 15;
	 * 
	 * public static final int S_OPCODE_SKILLBUY = 17; //
	 * 「魔法ヒール(4/0)を習うために渡す材料が不足しています。」と表示される public static final int
	 * S_OPCODE_MATERIAL = 18; // new addition
	 * 
	 * public static final int S_OPCODE_HPUPDATE = 19;
	 * 
	 * public static final int S_OPCODE_SHOWRETRIEVELIST = 20;
	 * 
	 * public static final int S_OPCODE_DELSKILL = 21;
	 * 
	 * public static final int S_OPCODE_NEWCHARPACK = 22;
	 * 
	 * public static final int S_OPCODE_LOGINOK = 23;
	 * 
	 * public static final int S_OPCODE_ADDITEM = 24;
	 * 
	 * public static final int S_OPCODE_TAXRATE = 25;
	 * 
	 * public static final int S_OPCODE_TRADEADDITEM = 26;
	 * 
	 * public static final int S_OPCODE_MAPID = 27;
	 * 
	 * public static final int S_OPCODE_UNDERWATER = 27;
	 * 
	 * public static final int S_OPCODE_YES_NO = 28;
	 * 
	 * public static final int S_OPCODE_DETELECHAROK = 29;
	 * 
	 * public static final int S_OPCODE_TELEPORT = 30;
	 * 
	 * public static final int S_OPCODE_WHISPERCHAT = 33;
	 * 
	 * public static final int S_OPCODE_REMOVE_OBJECT = 34;
	 * 
	 * public static final int S_OPCODE_SERVERVERSION = 35;
	 * 
	 * public static final int S_OPCODE_COMMONNEWS = 36;
	 * 
	 * public static final int S_OPCODE_HOUSELIST = 37;
	 * 
	 * public static final int S_OPCODE_ITEMNAME = 38;
	 * 
	 * public static final int S_OPCODE_DEXUP = 39;
	 * 
	 * public static final int S_OPCODE_SELECTTARGET = 40;
	 * 
	 * public static final int S_OPCODE_EMBLEM = 41;
	 * 
	 * public static final int S_OPCODE_IDENTIFYDESC = 42;
	 * 
	 * public static final int S_OPCODE_PINKNAME = 43;
	 * 
	 * public static final int S_OPCODE_NEWMASTER = 44; // new addition
	 * 
	 * public static final int S_OPCODE_POISON = 45;
	 * 
	 * public static final int S_OPCODE_BOOKMARKS = 49;
	 * 
	 * public static final int S_OPCODE_PRIVATESHOPLIST = 50;
	 * 
	 * public static final int S_OPCODE_TRADE = 51;
	 * 
	 * public static final int S_OPCODE_INPUTAMOUNT = 52;
	 * 
	 * public static final int S_OPCODE_PINGTIME = 53; // new addition
	 * 
	 * public static final int S_OPCODE_WAR = 54;
	 * 
	 * public static final int S_OPCODE_MOVEOBJECT = 55;
	 * 
	 * public static final int S_OPCODE_HOUSEMAP = 56;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPSELLLIST = 57;
	 * 
	 * public static final int S_OPCODE_BLUEMESSAGE = 58;
	 * 
	 * public static final int S_OPCODE_ATTACKPACKET = 59;
	 * 
	 * public static final int S_OPCODE_WARTIME = 60;
	 * 
	 * public static final int S_OPCODE_ITEMAMOUNT = 61;
	 * 
	 * public static final int S_OPCODE_PACKETBOX = 62;
	 * 
	 * public static final int S_OPCODE_ACTIVESPELLS = 62;
	 * 
	 * public static final int S_OPCODE_SKILLICONGFX = 62;
	 * 
	 * public static final int S_OPCODE_CURSEBLIND = 63;
	 * 
	 * public static final int S_OPCODE_COMMONNEWS2 = 64; // new addition
	 * 
	 * public static final int S_OPCODE_STRUP = 65;
	 * 
	 * public static final int S_OPCODE_UNKNOWN1 = 66;
	 * 
	 * public static final int S_OPCODE_SPMR = 67;
	 * 
	 * public static final int S_OPCODE_PUTSOLDIER = 68; // new addition
	 * 
	 * public static final int S_OPCODE_GAMETIME = 69;
	 * 
	 * public static final int S_OPCODE_HPMETER = 70;
	 * 
	 * public static final int S_OPCODE_SYSMSG = 71;
	 * 
	 * public static final int S_OPCODE_GLOBALCHAT = 71;
	 * 
	 * public static final int S_OPCODE_SERVERMSG = 72;
	 * 
	 * public static final int S_OPCODE_TELEPORTLOCK = 73; // new addition
	 * 
	 * public static final int S_OPCODE_CHARPACK = 74;
	 * 
	 * public static final int S_OPCODE_DROPITEM = 74;
	 * 
	 * public static final int S_OPCODE_CHANGENAME = 75; // new addition
	 * 
	 * public static final int S_OPCODE_SKILLHASTE = 77;
	 * 
	 * public static final int S_OPCODE_ADDSKILL = 78;
	 * 
	 * public static final int S_OPCODE_ABILITY = 79;
	 * 
	 * public static final int S_OPCODE_SKILLSOUNDGFX = 80;
	 * 
	 * public static final int S_OPCODE_ATTRIBUTE = 81;
	 * 
	 * public static final int S_OPCODE_INVLIST = 82;
	 * 
	 * public static final int S_OPCODE_CHARVISUALUPDATE = 84;
	 * 
	 * public static final int S_OPCODE_OWNCHARATTRDEF = 85;
	 * 
	 * public static final int S_OPCODE_EFFECTLOCATION = 86;
	 * 
	 * public static final int S_OPCODE_DRAWAL = 87;
	 * 
	 * public static final int S_OPCODE_DISCONNECT = 88;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS = 89;
	 * 
	 * public static final int S_OPCODE_RESURRECTION = 90;
	 * 
	 * public static final int S_OPCODE_EXP = 91;
	 * 
	 * public static final int S_OPCODE_SHOWHTML = 92;
	 * 
	 * public static final int S_OPCODE_TRUETARGET = 93;
	 * 
	 * public static final int S_OPCODE_HIRESOLDIER = 94;
	 * 
	 * public static final int S_OPCODE_LOGINRESULT = 95;
	 * 
	 * public static final int S_OPCODE_BOARD = 96;
	 * 
	 * public static final int S_OPCODE_CHARLOCK = 97; // new addition
	 * 
	 * public static final int S_OPCODE_NEWCHARWRONG = 98; // new addition
	 * 
	 * public static final int S_OPCODE_SHOWSHOPBUYLIST = 99;
	 * 
	 * public static final int S_OPCODE_BLESSOFEVA = 100;
	 * 
	 * public static final int S_OPCODE_RESTART = 101; // new addition
	 * 
	 * public static final int S_OPCODE_DEPOSIT = 102;
	 * 
	 * public static final int S_OPCODE_NORMALCHAT = 103;
	 * 
	 * public static final int S_OPCODE_CHANGEHEADING = 105;
	 * 
	 * public static final int S_OPCODE_UNKNOWN2 = 105;
	 * 
	 * public static final int S_OPCODE_TRADESTATUS = 106;
	 * 
	 * public static final int S_OPCODE_CASTLEMASTER = 107;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS2 = 108;
	 * 
	 * public static final int S_OPCODE_CHARTITLE = 109;
	 * 
	 * public static final int S_OPCODE_PARALYSIS = 111;
	 * 
	 * public static final int S_OPCODE_POLY = 112;
	 * 
	 * public static final int S_OPCODE_SKILLICONSHIELD = 114;
	 * 
	 * public static final int S_OPCODE_SOUND = 116;
	 * 
	 * public static final int S_OPCODE_CHARAMOUNT = 117;
	 * 
	 * public static final int S_OPCODE_CHARLIST = 118;
	 * 
	 * public static final int S_OPCODE_MPUPDATE = 119;
	 * 
	 * public static final int S_OPCODE_DELETEINVENTORYITEM = 120;
	 * 
	 * public static final int S_OPCODE_ITEMCOLOR = 121;
	 * 
	 * public static final int S_OPCODE_SERVERSTAT = 122;
	 * 
	 * public static final int S_OPCODE_WEATHER = 122;
	 * 
	 * public static final int S_OPCODE_LIQUOR = 123;
	 * 
	 * public static final int S_OPCODE_SKILLBRAVE = 124;
	 * 
	 * public static final int S_OPCODE_LIGHT = 126;
	 * 
	 * public static final int S_OPCODE_NPCSHOUT = 127;
	 */

	/*
	 * clientpackets for Episode6
	 * 
	 * public static final int C_OPCODE_QUITGAME = 0;
	 * 
	 * public static final int C_OPCODE_EXCLUDE = 1;
	 * 
	 * public static final int C_OPCODE_SHOP = 2;
	 * 
	 * public static final int C_OPCODE_CHARACTERCONFIG = 3;
	 * 
	 * public static final int C_OPCODE_CHECKPK = 6;
	 * 
	 * public static final int C_OPCODE_PROPOSE = 8;
	 * 
	 * public static final int C_OPCODE_REQUESTCHAT = 9;
	 * 
	 * public static final int C_OPCODE_JOINCLAN = 10;
	 * 
	 * public static final int C_OPCODE_SKILLBUYOK = 12;
	 * 
	 * public static final int C_OPCODE_RETURNTOLOGIN = 14;
	 * 
	 * public static final int C_OPCODE_COMMONCLICK = 15;
	 * 
	 * public static final int C_OPCODE_BOOKMARK = 17;
	 * 
	 * public static final int C_OPCODE_DEPOSIT = 20;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVER = 21;
	 * 
	 * public static final int C_OPCODE_CREATECLAN = 22;
	 * 
	 * public static final int C_OPCODE_REQUESTARROWATTACK = 24;
	 * 
	 * public static final int C_OPCODE_TRADE = 25;
	 * 
	 * public static final int C_OPCODE_TRADEADDCANCEL = 26;
	 * 
	 * public static final int C_OPCODE_REQUESTDOOR = 27;
	 * 
	 * public static final int C_OPCODE_BANPARTY = 28;
	 * 
	 * public static final int C_OPCODE_REQUESTTITLE = 29;
	 * 
	 * public static final int C_OPCODE_NPCTALK = 31;
	 * 
	 * public static final int C_OPCODE_REQUESTRESULT = 32;
	 * 
	 * public static final int C_OPCODE_KEEPALIVE = 34;
	 * 
	 * public static final int C_OPCODE_REQUESTPARTY = 35;
	 * 
	 * public static final int C_OPCODE_REQUESTEMBLEM = 37;
	 * 
	 * public static final int C_OPCODE_BOOKMARKDELETE = 40;
	 * 
	 * public static final int C_OPCODE_DELBUDDY = 42;
	 * 
	 * public static final int C_OPCODE_LEAVECLANE = 43;
	 * 
	 * public static final int C_OPCODE_REQUESTCHANGECHAR = 44;
	 * 
	 * public static final int C_OPCODE_REQUESTCHATGLOBAL = 46;
	 * 
	 * public static final int C_OPCODE_REQUESTWHO = 50;
	 * 
	 * public static final int C_OPCODE_REQUESTBUDDYLIST = 51;
	 * 
	 * public static final int C_OPCODE_USESKILL = 52;
	 * 
	 * public static final int C_OPCODE_FIX_WEAPON_LIST = 53;
	 * 
	 * public static final int C_OPCODE_ADDBUDDY = 54;
	 * 
	 * public static final int C_OPCODE_DELETECHAR = 57;
	 * 
	 * public static final int C_OPCODE_TAXRATE = 60;
	 * 
	 * public static final int C_OPCODE_REQUESTPLEDGE = 61;
	 * 
	 * public static final int C_OPCODE_CHANGEHEADING = 62;
	 * 
	 * public static final int C_OPCODE_REQUESTPICKUPITEM = 63;
	 * 
	 * public static final int C_OPCODE_REQUESTRESTART = 64;
	 * 
	 * public static final int C_OPCODE_TRADEADDOK = 65;
	 * 
	 * public static final int C_OPCODE_BOARD = 66;
	 * 
	 * public static final int C_OPCODE_BOARDREAD = 67;
	 * 
	 * public static final int C_OPCODE_BOARDWRITE = 68;
	 * 
	 * public static final int C_OPCODE_NPCACTION = 70;
	 * 
	 * public static final int C_OPCODE_BOARDDELETE = 72;
	 * 
	 * public static final int C_OPCODE_ENTERPORTAL = 76;
	 * 
	 * public static final int C_OPCODE_USEITEM = 77;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVEROK = 78;
	 * 
	 * public static final int C_OPCODE_SELECTLIST = 79;
	 * 
	 * public static final int C_OPCODE_SKILLBUY = 82;
	 * 
	 * public static final int C_OPCODE_LOGINPACKET = 83;
	 * 
	 * public static final int C_OPCODE_EXTCOMMAND = 86;
	 * 
	 * public static final int C_OPCODE_REQUESTDROPITEM = 87;
	 * 
	 * public static final int C_OPCODE_CALL = 88;
	 * 
	 * public static final int C_OPCODE_REQUESTAMOUNT = 92;
	 * 
	 * public static final int C_OPCODE_REQUESTWAR = 94;
	 * 
	 * public static final int C_OPCODE_REQUESTATTR = 97;
	 * 
	 * public static final int C_OPCODE_CLIENTVERSION = 98;
	 * 
	 * public static final int C_OPCODE_EXIT_GHOST = 99;
	 * 
	 * public static final int C_OPCODE_BANCLAN = 101;
	 * 
	 * public static final int C_OPCODE_REQUESTLEAVEPARTY = 104;
	 * 
	 * public static final int C_OPCODE_REQUESTCHATWHISPER = 107;
	 * 
	 * public static final int C_OPCODE_TRADEADDITEM = 109;
	 * 
	 * public static final int C_OPCODE_DELETEINVENTORYITEM = 112;
	 * 
	 * public static final int C_OPCODE_PRIVATESHOPLIST = 116;
	 * 
	 * public static final int C_OPCODE_BOARDBACK = 118;
	 * 
	 * public static final int C_OPCODE_NEWCHAR = 120;
	 * 
	 * public static final int C_OPCODE_WITHDRAWPET = 121;
	 * 
	 * public static final int C_OPCODE_REQUESTMOVECHAR = 124;
	 * 
	 * public static final int C_OPCODE_DRAWAL = 125;
	 * 
	 * public static final int C_OPCODE_REQUESTATTACK = 126;
	 * 
	 * public static final int C_OPCODE_HIRESOLDIER = 127;
	 * 
	 * public static final int C_OPCODE_GIVEITEM = 128;
	 * 
	 * public static final int C_OPCODE_CHANGEWARTIME = 129;
	 * 
	 * public static final int C_OPCODE_CREATEPARTY = 130;
	 * 
	 * serverpackets for Episode6
	 * 
	 * public static final int S_OPCODE_HPUPDATE = 0;
	 * 
	 * public static final int S_OPCODE_LIGHT = 2;
	 * 
	 * public static final int S_OPCODE_DETELECHAROK = 4;
	 * 
	 * public static final int S_OPCODE_ATTRIBUTE = 7;
	 * 
	 * public static final int S_OPCODE_MOVEOBJECT = 8;
	 * 
	 * public static final int S_OPCODE_TAXRATE = 9;
	 * 
	 * public static final int S_OPCODE_HIRESOLDIER = 10;
	 * 
	 * public static final int S_OPCODE_EFFECTLOCATION = 11;
	 * 
	 * public static final int S_OPCODE_OWNCHARATTRDEF = 12;
	 * 
	 * public static final int S_OPCODE_CHANGECHARNAME = 13;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS2 = 14;
	 * 
	 * public static final int S_OPCODE_LOGINRESULT = 15;
	 * 
	 * public static final int S_OPCODE_DISCONNECT = 17;
	 * 
	 * public static final int S_OPCODE_DELSKILL = 18;
	 * 
	 * public static final int S_OPCODE_CHANGEHEADING = 19;
	 * 
	 * public static final int S_OPCODE_UNKNOWN2 = 19;
	 * 
	 * public static final int S_OPCODE_TRUETARGET = 21;
	 * 
	 * public static final int S_OPCODE_INVLIST = 23;
	 * 
	 * public static final int S_OPCODE_ADDITEM = 24;
	 * 
	 * public static final int S_OPCODE_ITEMCOLOR = 25;
	 * 
	 * public static final int S_OPCODE_CHARAMOUNT = 26;
	 * 
	 * public static final int S_OPCODE_CHARLIST = 27;
	 * 
	 * public static final int S_OPCODE_INVIS = 29;
	 * 
	 * public static final int S_OPCODE_TRADE = 30;
	 * 
	 * public static final int S_OPCODE_HOUSEMAP = 31;
	 * 
	 * public static final int S_OPCODE_EMPLOY = 32;
	 * 
	 * public static final int S_OPCODE_SKILLHASTE = 33;
	 * 
	 * public static final int S_OPCODE_WHOPET = 35;
	 * 
	 * public static final int S_OPCODE_DOACTIONGFX = 36;
	 * 
	 * public static final int S_OPCODE_EMBLEM = 37;
	 * 
	 * public static final int S_OPCODE_CURSEBLIND = 38;
	 * 
	 * public static final int S_OPCODE_SKILLSOUNDGFX = 39;
	 * 
	 * public static final int S_OPCODE_UNKNOWN1 = 40;
	 * 
	 * public static final int S_OPCODE_USEMAP = 41;
	 * 
	 * public static final int S_OPCODE_HOUSELIST = 42;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS = 43;
	 * 
	 * public static final int S_OPCODE_NPCSHOUT = 44;
	 * 
	 * public static final int S_OPCODE_POLY = 45;
	 * 
	 * public static final int S_OPCODE_PARALYSIS = 46;
	 * 
	 * public static final int S_OPCODE_INPUTAMOUNT = 47;
	 * 
	 * public static final int S_OPCODE_BOARD = 48;
	 * 
	 * public static final int S_OPCODE_CHARPACK = 49;
	 * 
	 * public static final int S_OPCODE_DROPITEM = 49;
	 * 
	 * public static final int S_OPCODE_SERVERVERSION = 50;
	 * 
	 * public static final int S_OPCODE_CANTMOVE = 51;
	 * 
	 * public static final int S_OPCODE_RESURRECTION = 52;
	 * 
	 * public static final int S_OPCODE_DRAWAL = 53;
	 * 
	 * public static final int S_OPCODE_LAWFUL = 54;
	 * 
	 * public static final int S_OPCODE_DEPOSIT = 55;
	 * 
	 * public static final int S_OPCODE_PINGSERVER = 56;
	 * 
	 * public static final int S_OPCODE_ATTACKPACKET = 57;
	 * 
	 * public static final int S_OPCODE_CHARINFO3 = 58;
	 * 
	 * public static final int S_OPCODE_SELECTLIST = 59;
	 * 
	 * public static final int S_OPCODE_RETURNLOGIN = 60;
	 * 
	 * public static final int S_OPCODE_CHARTITLE = 62;
	 * 
	 * public static final int S_OPCODE_PRIVATESHOPLIST = 63;
	 * 
	 * public static final int S_OPCODE_SOUND = 65;
	 * 
	 * public static final int S_OPCODE_ITEMNAME = 66;
	 * 
	 * public static final int S_OPCODE_NEWCHARPACK = 67;
	 * 
	 * public static final int S_OPCODE_SERVERSTAT = 68;
	 * 
	 * public static final int S_OPCODE_WEATHER = 68;
	 * 
	 * public static final int S_OPCODE_BUY002 = 69;
	 * 
	 * public static final int S_OPCODE_ADDSKILL = 70;
	 * 
	 * public static final int S_OPCODE_COMMONNEWS = 73;
	 * 
	 * public static final int S_OPCODE_ABILITY = 74;
	 * 
	 * public static final int S_OPCODE_BOARDREAD = 75;
	 * 
	 * public static final int S_OPCODE_MPUPDATE = 76;
	 * 
	 * public static final int S_OPCODE_LOGINOK = 78;
	 * 
	 * public static final int S_OPCODE_SYSMSG = 79;
	 * 
	 * public static final int S_OPCODE_GLOBALCHAT = 79;
	 * 
	 * public static final int S_OPCODE_SHOWHTML = 80;
	 * 
	 * public static final int S_OPCODE_BONUSSTATS = 80;
	 * 
	 * public static final int S_OPCODE_DELETEINVENTORYITEM = 81;
	 * 
	 * public static final int S_OPCODE_MAPID = 82;
	 * 
	 * public static final int S_OPCODE_UNDERWATER = 82;
	 * 
	 * public static final int S_OPCODE_REMOVE_OBJECT = 83;
	 * 
	 * public static final int S_OPCODE_HPMETER = 84;
	 * 
	 * public static final int S_OPCODE_POISON = 85;
	 * 
	 * public static final int S_OPCODE_BLESSOFEVA = 86;
	 * 
	 * public static final int S_OPCODE_CASTLEMASTER = 87;
	 * 
	 * public static final int S_OPCODE_SPMR = 89;
	 * 
	 * public static final int S_OPCODE_GMACCOUNTMSG = 90;
	 * 
	 * public static final int S_OPCODE_CANTMOVEBEFORETELE = 91;
	 * 
	 * public static final int S_OPCODE_CHARVISUALUPDATE = 92;
	 * 
	 * public static final int S_OPCODE_NEWCHARWRONG = 93;
	 * 
	 * public static final int S_OPCODE_WARTIME = 95;
	 * 
	 * public static final int S_OPCODE_GAMETIME = 96;
	 * 
	 * public static final int S_OPCODE_SELECTTARGET = 97;
	 * 
	 * public static final int S_OPCODE_NORMALCHAT = 98;
	 * 
	 * public static final int S_OPCODE_TRADESTATUS = 99;
	 * 
	 * public static final int S_OPCODE_SKILLICONSHIELD = 100;
	 * 
	 * public static final int S_OPCODE_SHOWRETRIEVELIST = 101;
	 * 
	 * public static final int S_OPCODE_SERVERMSG = 102;
	 * 
	 * public static final int S_OPCODE_SKILLBRAVE = 103;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPSELLLIST = 105;
	 * 
	 * public static final int S_OPCODE_WAR = 106;
	 * 
	 * public static final int S_OPCODE_WEIGHT = 107;
	 * 
	 * public static final int S_OPCODE_PACKETBOX = 107;
	 * 
	 * public static final int S_OPCODE_ACTIVESPELLS = 107;
	 * 
	 * public static final int S_OPCODE_SKILLICONGFX = 107;
	 * 
	 * public static final int S_OPCODE_STRUP = 109;
	 * 
	 * public static final int S_OPCODE_LIQUOR = 110;
	 * 
	 * public static final int S_OPCODE_WHISPERCHAT = 112;
	 * 
	 * public static final int S_OPCODE_BLUEMESSAGE = 113;
	 * 
	 * public static final int S_OPCODE_TRADEADDITEM = 115;
	 * 
	 * public static final int S_OPCODE_LETTER = 116;
	 * 
	 * public static final int S_OPCODE_EXP = 118;
	 * 
	 * public static final int S_OPCODE_BOOKMARKS = 119;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPBUYLIST = 120;
	 * 
	 * public static final int S_OPCODE_IDENTIFYDESC = 121;
	 * 
	 * public static final int S_OPCODE_DEXUP = 122;
	 * 
	 * public static final int S_OPCODE_PINKNAME = 123;
	 * 
	 * public static final int S_OPCODE_ITEMSTATUS = 124;
	 * 
	 * public static final int S_OPCODE_ITEMAMOUNT = 125;
	 * 
	 * public static final int S_OPCODE_YES_NO = 126;
	 * 
	 * public static final int S_OPCODE_SKILLBUY = 127;
	 */

	/*
	 * clientpackets for Episode5
	 * 
	 * public static final int C_OPCODE_REQUESTDOOR = 0;
	 * 
	 * public static final int C_OPCODE_REQUESTTITLE = 5;
	 * 
	 * public static final int C_OPCODE_REQUESTRANGEATTACK = 6;
	 * 
	 * public static final int C_OPCODE_BOARDDELETE = 8;
	 * 
	 * public static final int C_OPCODE_REQUESTPLEDGE = 9;
	 * 
	 * public static final int C_OPCODE_CHANGEHEADING = 11;
	 * 
	 * public static final int C_OPCODE_NPCACTION = 12;
	 * 
	 * public static final int C_OPCODE_ASKDISMISSPET = 12;
	 * 
	 * public static final int C_OPCODE_USESKILL = 14;
	 * 
	 * public static final int C_OPCODE_REQUESTEMBLEM = 15;
	 * 
	 * public static final int C_OPCODE_TRADEADDCANCEL = 18;
	 * 
	 * public static final int C_OPCODE_CHANGEWARTIME = 22;
	 * 
	 * public static final int C_OPCODE_BOOKMARK = 25;
	 * 
	 * public static final int C_OPCODE_CREATECLAN = 26;
	 * 
	 * public static final int C_OPCODE_CLIENTVERSION = 27;
	 * 
	 * public static final int C_OPCODE_PROPOSE = 29;
	 * 
	 * public static final int C_OPCODE_SKILLBUY = 31;
	 * 
	 * public static final int C_OPCODE_BOARDBACK = 38;
	 * 
	 * public static final int C_OPCODE_SHOP = 39;
	 * 
	 * public static final int C_OPCODE_BOARDREAD = 40;
	 * 
	 * public static final int C_OPCODE_TRADE = 42;
	 * 
	 * public static final int C_OPCODE_DELETECHAR = 48;
	 * 
	 * public static final int C_OPCODE_KEEPALIVE = 49;
	 * 
	 * public static final int C_OPCODE_REQUESTATTR = 51;
	 * 
	 * public static final int C_OPCODE_LOGINPACKET = 52;
	 * 
	 * public static final int C_OPCODE_REQUESTRESULT = 54;
	 * 
	 * public static final int C_OPCODE_DEPOSIT = 56;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVEROK = 57;
	 * 
	 * public static final int C_OPCODE_SKILLBUYOK = 58;
	 * 
	 * public static final int C_OPCODE_TRADEADDITEM = 61;
	 * 
	 * public static final int C_OPCODE_ADDBUDDY = 63;
	 * 
	 * public static final int C_OPCODE_RETURNTOLOGIN = 65;
	 * 
	 * public static final int C_OPCODE_REQUESTCHAT = 68;
	 * 
	 * public static final int C_OPCODE_TRADEADDOK = 69;
	 * 
	 * public static final int C_OPCODE_CHECKPK = 70;
	 * 
	 * public static final int C_OPCODE_TAXRATE = 74;
	 * 
	 * public static final int C_OPCODE_REQUESTCHANGECHAR = 75;
	 * 
	 * public static final int C_OPCODE_REQUESTBUDDYLIST = 76;
	 * 
	 * public static final int C_OPCODE_REQUESTDROPITEM = 77;
	 * 
	 * public static final int C_OPCODE_REQUESTLEAVEPARTY = 78;
	 * 
	 * public static final int C_OPCODE_REQUESTATTACK = 79;
	 * 
	 * public static final int C_OPCODE_QUITGAME = 81;
	 * 
	 * public static final int C_OPCODE_BANCLAN = 82;
	 * 
	 * public static final int C_OPCODE_BOARD = 84;
	 * 
	 * public static final int C_OPCODE_DELETEINVENTORYITEM = 85;
	 * 
	 * public static final int C_OPCODE_REQUESTCHATWHISPER = 86;
	 * 
	 * public static final int C_OPCODE_REQUESTPARTY = 87;
	 * 
	 * public static final int C_OPCODE_REQUESTPICKUPITEM = 88;
	 * 
	 * public static final int C_OPCODE_REQUESTWHO = 89;
	 * 
	 * public static final int C_OPCODE_GIVEITEM = 90;
	 * 
	 * public static final int C_OPCODE_REQUESTMOVECHAR = 91;
	 * 
	 * public static final int C_OPCODE_BOOKMARKDELETE = 93;
	 * 
	 * public static final int C_OPCODE_REQUESTRESTART = 94;
	 * 
	 * public static final int C_OPCODE_LEAVECLANE = 98;
	 * 
	 * public static final int C_OPCODE_NPCTALK = 100;
	 * 
	 * public static final int C_OPCODE_BANPARTY = 102;
	 * 
	 * public static final int C_OPCODE_DELBUDDY = 106;
	 * 
	 * public static final int C_OPCODE_REQUESTWAR = 109;
	 * 
	 * public static final int C_OPCODE_LOGINTOSERVER = 111;
	 * 
	 * public static final int C_OPCODE_PRIVATESHOPLIST = 113;
	 * 
	 * public static final int C_OPCODE_REQUESTCHATGLOBAL = 114;
	 * 
	 * public static final int C_OPCODE_JOINCLAN = 115;
	 * 
	 * public static final int C_OPCODE_COMMONCLICK = 117;
	 * 
	 * public static final int C_OPCODE_NEWCHAR = 118;
	 * 
	 * public static final int C_OPCODE_EXTCOMMAND = 123;
	 * 
	 * public static final int C_OPCODE_BOARDWRITE = 124;
	 * 
	 * public static final int C_OPCODE_USEITEM = 129;
	 * 
	 * public static final int C_OPCODE_CREATEPARTY = 130;
	 * 
	 * serverpackets for Episode5
	 * 
	 * public static final int S_OPCODE_TPGFX = 0;
	 * 
	 * public static final int S_OPCODE_DELETENEWOBJECT = 0;
	 * 
	 * public static final int S_OPCODE_DELETEOBJECTFROMSCREEN = 0;
	 * 
	 * public static final int S_OPCODE_KILL = 0;
	 * 
	 * public static final int S_OPCODE_UNKNOWN1 = 1;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS2 = 2;
	 * 
	 * public static final int S_OPCODE_CHARSTATUS = 3;
	 * 
	 * public static final int S_OPCODE_SERVERSTAT = 4;
	 * 
	 * public static final int S_OPCODE_SKILLICONSHIELD = 5;
	 * 
	 * public static final int S_OPCODE_NEWCHARWRONG = 7;
	 * 
	 * public static final int S_OPCODE_CASTLEMASTER = 8;
	 * 
	 * public static final int S_OPCODE_RESURRECTION = 9;
	 * 
	 * public static final int S_OPCODE_LAWFUL = 10;
	 * 
	 * public static final int S_OPCODE_CHARAMOUNT = 11;
	 * 
	 * public static final int S_OPCODE_LOGINRESULT = 12;
	 * 
	 * public static final int S_OPCODE_SKILLHASTE = 14;
	 * 
	 * public static final int S_OPCODE_DOACTIONGFX = 15;
	 * 
	 * public static final int S_OPCODE_NPCNORMALCHAT = 16;
	 * 
	 * public static final int S_OPCODE_CURSEBLIND = 17;
	 * 
	 * public static final int S_OPCODE_BOARD = 18;
	 * 
	 * public static final int S_OPCODE_INVLIST = 20;
	 * 
	 * public static final int S_OPCODE_INVENTORYPACK = 20;
	 * 
	 * public static final int S_OPCODE_DELETEOBJECT = 21;
	 * 
	 * public static final int S_OPCODE_HPUPDATE = 23;
	 * 
	 * public static final int S_OPCODE_SHOWPOLYLIST = 23;
	 * 
	 * public static final int S_OPCODE_BLUEMESSAGE = 24;
	 * 
	 * public static final int S_OPCODE_MAPID = 25;
	 * 
	 * public static final int S_OPCODE_UNDERWATER = 25;
	 * 
	 * public static final int S_OPCODE_POLY = 26;
	 * 
	 * public static final int S_OPCODE_WAR = 27;
	 * 
	 * public static final int S_OPCODE_CHARTITLE = 28;
	 * 
	 * public static final int S_OPCODE_NORMALCHAT = 29;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPBUYLIST = 30;
	 * 
	 * public static final int S_OPCODE_MOVEOBJECT = 32;
	 * 
	 * public static final int S_OPCODE_SPMR = 33;
	 * 
	 * public static final int S_OPCODE_SERVERVERSION = 36;
	 * 
	 * public static final int S_OPCODE_EMBLEM = 37;
	 * 
	 * public static final int S_OPCODE_CHARVISUALUPDATE = 39;
	 * 
	 * public static final int S_OPCODE_DISCONNECT = 41;
	 * 
	 * public static final int S_OPCODE_TRADESTATUS = 42;
	 * 
	 * public static final int S_OPCODE_PINKNAME = 44;
	 * 
	 * public static final int S_OPCODE_BOOKMARKS = 45;
	 * 
	 * public static final int S_OPCODE_OWNCHARSTATUS = 46;
	 * 
	 * public static final int S_OPCODE_PARALYSIS = 47;
	 * 
	 * public static final int S_OPCODE_LIQUOR = 49;
	 * 
	 * public static final int S_OPCODE_DELSKILL = 50;
	 * 
	 * public static final int S_OPCODE_GLOBALCHAT = 51;
	 * 
	 * public static final int S_OPCODE_SYSMSG = 51;
	 * 
	 * public static final int S_OPCODE_BLESSOFEVA = 53;
	 * 
	 * public static final int S_OPCODE_SKILLBRAVE = 55;
	 * 
	 * public static final int S_OPCODE_DELETEMOBOBJECT = 55;
	 * 
	 * public static final int S_OPCODE_LIGHT = 56;
	 * 
	 * public static final int S_OPCODE_UNKNOWN2 = 57;
	 * 
	 * public static final int S_OPCODE_CHANGEHEADING = 57;
	 * 
	 * public static final int S_OPCODE_SERVERMSG = 59;
	 * 
	 * public static final int S_OPCODE_TRUETARGET = 60;
	 * 
	 * public static final int S_OPCODE_HPMETER = 61;
	 * 
	 * public static final int S_OPCODE_SENDITEMAMOUNTUPDATE = 62;
	 * 
	 * public static final int S_OPCODE_ADDSKILL = 63;
	 * 
	 * public static final int S_OPCODE_WARHOUSELIST = 64;
	 * 
	 * public static final int S_OPCODE_DETELECHAROK = 66;
	 * 
	 * public static final int S_OPCODE_NEWCHARPACK = 67;
	 * 
	 * public static final int S_OPCODE_CHARPACK = 68;
	 * 
	 * public static final int S_OPCODE_DROPITEM = 68;
	 * 
	 * public static final int S_OPCODE_DELETEINVENTORYITEM = 71;
	 * 
	 * public static final int S_OPCODE_POISON = 75;
	 * 
	 * public static final int S_OPCODE_CHARINVVISUALUPDATE = 79;
	 * 
	 * public static final int S_OPCODE_SHOWHTML = 81;
	 * 
	 * public static final int S_OPCODE_TPUNK1 = 81;
	 * 
	 * public static final int S_OPCODE_EXP = 81;
	 * 
	 * public static final int S_OPCODE_INVIS = 82;
	 * 
	 * public static final int S_OPCODE_DEPOSIT = 83;
	 * 
	 * public static final int S_OPCODE_WHISPERCHAT = 85;
	 * 
	 * public static final int S_OPCODE_DEXUP = 86;
	 * 
	 * public static final int S_OPCODE_MESSAGE = 87;
	 * 
	 * public static final int S_OPCODE_OWNCHARATTRDEF = 88;
	 * 
	 * public static final int S_OPCODE_AUTHSERVERTIME = 89;
	 * 
	 * public static final int S_OPCODES_ABILITY = 93;
	 * 
	 * public static final int S_OPCODE_ATTACKPACKET = 94;
	 * 
	 * public static final int S_OPCODE_ACTIVESPELLS = 96;
	 * 
	 * public static final int S_OPCODE_SHOWRETRIEVELIST = 96;
	 * 
	 * public static final int S_OPCODE_CHARLIST = 97;
	 * 
	 * public static final int S_OPCODE_BECOMEYOURPET = 100;
	 * 
	 * public static final int S_OPCODE_SHOWSELLLIST = 100;
	 * 
	 * public static final int S_OPCODE_TAXRATE = 102;
	 * 
	 * public static final int S_OPCODE_SHOWSHOPSELLLIST = 103;
	 * 
	 * public static final int S_OPCODE_SKILLBUY = 104;
	 * 
	 * public static final int S_OPCODE_SKILLSOUNDGFX = 106;
	 * 
	 * public static final int S_OPCODE_WEATHER = 108;
	 * 
	 * public static final int S_OPCODE_PRIVATESHOPLIST = 107;
	 * 
	 * public static final int S_OPCODE_GAMETIME = 109;
	 * 
	 * public static final int S_OPCODE_BOARDREAD = 110;
	 * 
	 * public static final int S_OPCODE_YES_NO = 111;
	 * 
	 * public static final int S_OPCODE_WARTIME = 113;
	 * 
	 * public static final int S_OPCODE_STRUP = 114;
	 * 
	 * public static final int S_OPCODE_USEMAP = 115;
	 * 
	 * public static final int S_OPCODE_TRADE = 119;
	 * 
	 * public static final int S_OPCODE_TRADEADDITEM = 121;
	 * 
	 * public static final int S_OPCODE_SKILLICONGFX = 123;
	 * 
	 * public static final int S_OPCODE_COMMONNEWS = 125;
	 * 
	 * public static final int S_OPCODE_MPUPDATE = 126;
	 */

}