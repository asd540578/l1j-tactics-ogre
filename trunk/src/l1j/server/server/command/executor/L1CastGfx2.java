/*
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.	 See the
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

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.logging.Logger;

import l1j.server.server.GeneralThreadPool;
import l1j.server.server.datatables.SprListTable;
import l1j.server.server.model.Instance.L1PcInstance;
import l1j.server.server.serverpackets.S_SkillSound;
import l1j.server.server.serverpackets.S_SystemMessage;

public class L1CastGfx2 implements L1CommandExecutor {
   private static Logger _log = Logger.getLogger(L1CastGfx2.class.getName());

   private L1CastGfx2() {
   }

   public static L1CommandExecutor getInstance() {
	  return new L1CastGfx2();
   }

   private int _startIndex;
   private int _sec;
   private int _count;
   private L1PcInstance _pc;
   private ArrayList<Integer> _sprList; 
   @Override
   public void execute(L1PcInstance pc, String cmdName, String arg) {
	  try {
		 StringTokenizer stringtokenizer = new StringTokenizer(arg);
		 int sprid = Integer.parseInt(stringtokenizer.nextToken());
		 _sec = Integer.parseInt(stringtokenizer.nextToken());
		 _count  = Integer.parseInt(stringtokenizer.nextToken());
		 _pc = pc;
		 
		 //開始spridがテーブルにあるか線形検索　ない場合は、+1づつ増加させて発見するまでループさせる
		 _sprList = SprListTable.getInstance().getTemplate();
		 boolean search_flag = false; 
		 for(;;){
			 for(int i = 0;i < _sprList.size();i++){
				 if(sprid == _sprList.get(i)){
					 search_flag = true;
					 _startIndex = i;
					 break;
				 }
			 }
			 if(search_flag){
				 break;
			 }
			 sprid++;
		 }
		 
		 class castgfxStart implements Runnable{
			public void run(){
				int cnt = 0;
				for(;;){
					 _pc.sendPackets(new S_SkillSound(_pc.getId(), _sprList.get(_startIndex)));
					 _pc.broadcastPacket(new S_SkillSound(_pc.getId(), _sprList.get(_startIndex)));
					 _pc.sendPackets(new S_SystemMessage(String.valueOf(_sprList.get(_startIndex))));
					 _startIndex++;
					 
					 cnt++;
					 if(_count != 0 && cnt >= _count){
						 return;
					 }
					try {
						Thread.sleep(_sec);
					} catch (InterruptedException e) {
						// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
				}
			}
		 }
		 castgfxStart cgs = new castgfxStart();
		 GeneralThreadPool.getInstance().execute(cgs);

	  } catch (Exception e) {
		 pc.sendPackets(new S_SystemMessage(cmdName + " 開始するgfxid,表示間隔(1000ms),表示する回数(0で最後まで) と入力して下さい。"));
	  }
   }
}