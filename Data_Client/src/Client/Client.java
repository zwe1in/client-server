package Client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("101.132.130.199", 8089);
			
			//�����豸id��
			Scanner in = new Scanner(System.in);
			System.out.println("�豸id�б�Ϊ��2016��2017��2018��2020��2021��2022");
			System.out.println("������Ҫ��ȡ���ݶ�Ӧ���豸ID");
			int id = in.nextInt();
			JSONObject json = new JSONObject();
			json.put("id", id);
			//������Ϣ
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write(json.toString());
			pw.flush();
			socket.shutdownOutput();
			
			//�ӷ���˽�����Ϣ
			InputStream is = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String info = null;
			JSONArray jarray = null; 
			while((info = reader.readLine())!= null) {
				jarray = new JSONArray(info);
				System.out.println("�������ݣ�"+info);
			}
			//��̨��������
			DataHandle dh = new DataHandle(jarray);
			dh.run();
			reader.close();
			is.close();
			os.close();
			pw.close();
			socket.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
