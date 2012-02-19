package l1j.server.server.model.map;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import l1j.server.server.model.map.L1WorldMap;

/**
 * @author Owner
 *
 */
public class L1InstanceMap {
		private static final Logger _log = Logger
		.getLogger(L1InstanceMap.class.getName());
		private int _curId;

		private Object _monitor = new Object();

		private static final short FIRST_ID =10000 ;
		private static final short LAST_ID =12000 ;

		private static L1InstanceMap _instance;
		public static L1InstanceMap getInstance() {
			if (_instance == null) {
				_instance = new L1InstanceMap();
			}
			return _instance;
		}

	private final List<Integer> _instanceMapList = new ArrayList<Integer>();

	L1InstanceMap(){
		_curId=FIRST_ID;
	}
	/**
	 * インスタンスマップを追加する
	 *
	 * @param mapId 基準となるマップ（コピー元）
	 * @return 生成したマップのID
	 */
	public int addInstanceMap(int mapId) {
		L1Map instanceMap = L1WorldMap.getInstance().getMap((short)mapId).clone();
		int nextId=nextId();
		while(_instanceMapList.contains(nextId)){
			nextId=nextId();
		}
		instanceMap.setId(nextId);
		L1WorldMap.getInstance().addMap(instanceMap);
		_instanceMapList.add(instanceMap.getId());
		return instanceMap.getId();
	}

	/**
	 * インスタンスマップを削除する
	 *
	 * @param mapId 削除対象のマップのID
	 * @return true 成功 false 失敗
	 */
	public boolean removeInstanceMap(int mapId) {
		if (!_instanceMapList.contains( mapId)) {
			return false;
		}
		 for (Iterator<Integer> i =_instanceMapList.listIterator(); i.hasNext();) {
			  int key=i.next();  //次の要素の呼び出し
			  if(key==mapId){
				  i.remove();  //要素の削除
				  break;
			  }
		 }
		L1WorldMap.getInstance().removeMap(mapId);
		return true;
	}

	public List<Integer> getInstanceMap() {
		return _instanceMapList;
	}

	public int getNumOfInstanceMaps() {
		return _instanceMapList.size();
	}

	public int nextId() {
		synchronized (_monitor) {
			if(_curId>LAST_ID){
				_curId=FIRST_ID;
			}
			return _curId++;
		}
	}
}
