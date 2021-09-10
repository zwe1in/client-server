package Server_Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import Dao.DataDao;

public class DataHandle implements Runnable {

	private Socket socket = null;
	private InputStream is = null;
	private InputStreamReader isr = null;
	private BufferedReader br = null;
	private OutputStream os = null;
	private PrintWriter pw = null;
	
	public DataHandle(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		try {
			is = socket.getInputStream();
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String info = null;
			JSONObject json = null;
			while((info = br.readLine())!= null) {
				System.out.println("客户端信号："+info);
				json = new JSONObject(info);
			}
			int id = json.getInt("id");
			socket.shutdownInput();
			//返回数据
			DataDao dataDao = new DataDao();
			int count = dataDao.queryCount();
			for(int i = 0; i <=count; i+=200) {				
				List<Map<String,Object>> data = dataDao.queryAllData(i, i+200, id);
				os = socket.getOutputStream();
				JSONArray jarray = new JSONArray(data);
				pw = new PrintWriter(os);
				pw.write(jarray.toString());
				pw.flush();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
                if(pw!=null)
                    pw.close();
                if(os!=null)
                    os.close();
                if(br!=null)
                    br.close();
                if(isr!=null)
                    isr.close();
                if(is!=null)
                    is.close();
                if(socket!=null)
                    socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
	}

}
