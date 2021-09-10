package Client;

import org.json.JSONArray;


import Dao.DataDao;

public class DataHandle implements Runnable {

	private JSONArray jarray = null;
	private DataDao dataDao = null;
	
	public DataHandle(JSONArray jarray) {
		this.jarray = jarray;
		dataDao = new DataDao();
	}
	
	@Override
	public void run() {
		//����json���飬�������ݿ�
		for(int i = 0; i < jarray.length(); i++) {			
			dataDao.add(jarray.getJSONObject(i));
		}
	}

}
