package Server_Client;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8089);
			System.out.println("�ȴ�����.....");
			Socket socket = new Socket();
			while(true) {
				//�ͻ��˼���
				socket = server.accept();				
				//��Ӧ�߳�
				DataHandle dh = new DataHandle(socket);
				dh.run();
				InetAddress address = socket.getInetAddress();
				System.out.println("��ǰ�Ŀͻ���IP:"+address.getHostAddress());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
