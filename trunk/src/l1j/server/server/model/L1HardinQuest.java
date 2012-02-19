package l1j.server.server.model;

import java.util.HashMap;
import java.util.logging.Logger;

import l1j.server.server.model.etc.L1HardinQuestInstance;
import l1j.server.server.model.map.L1InstanceMap;

public class L1HardinQuest{
	private static Logger _log = Logger.getLogger(L1HardinQuest.class
			.getName());
	private static HashMap<Integer, L1HardinQuestInstance> _activeMaps;
	private static L1HardinQuest _instance;

	public static L1HardinQuest getInstance() {
		if (_instance == null) {
			_instance = new L1HardinQuest();
		}
		return _instance;
	}
	L1HardinQuest(){
		_activeMaps= new HashMap<Integer, L1HardinQuestInstance>();
	}

	/**
	 * 指定された要素に含まれるインスタンスを返す
	 *
	 * @param x
	 * @return 格納されているインスタンス
	 */
	public L1HardinQuestInstance getActiveMaps(int mapId){
		if (!_activeMaps.containsKey( mapId)) {
			return null;
		}
		return _activeMaps.get(mapId);
	}
	/**
	 * インスタンスマップを生成し、HashMapにリンクを生成
	 * @param mapId コピー元のマップID
	 * @return
	 */
	public short setActiveMaps(int mapId){
		int instanceMapId= L1InstanceMap.getInstance().addInstanceMap(mapId);
		L1HardinQuestInstance obj=new L1HardinQuestInstance((short)instanceMapId);
		_activeMaps.put(instanceMapId,obj);
		return obj.getMapId();
	}
	/**
	 * クエスト終了につき生成したインスタンスマップを開放する
	 * @param mapId
	 */
	public void resetActiveMaps(int mapId){
		if (!_activeMaps.containsKey(mapId)) {
			return;
		}
		_activeMaps.remove(mapId);
		L1InstanceMap.getInstance().removeInstanceMap(mapId);
	}
	/**
	 * 進行中のクエストの件数を取得
	 * @return
	 */
	public int getNumOfActiveMaps(){
		return _activeMaps.size();
	}
}