package Server_Client;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(8089);
			System.out.println("等待链接.....");
			Socket socket = new Socket();
			while(true) {
				//客户端监听
				socket = server.accept();				
				//响应线程
				DataHandle dh = new DataHandle(socket);
				dh.run();
				InetAddress address = socket.getInetAddress();
				System.out.println("当前的客户端IP:"+address.getHostAddress());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
