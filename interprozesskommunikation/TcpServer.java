package _new.interprozesskommunikation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {

	public static void main(String[] args) {
		int port = 1200;
		
		try {
			ServerSocket server_socket = new ServerSocket(port); // 1. 서버소켓 객체생성. 포트넘버와. 
		
			System.out.println("server: waiting for connection with client ");
			Socket connection_socket = server_socket.accept(); // 2. 클라이언트 연결 수락. accept()는 클라이언트가 연결요청하면 통신소켓을 만들고 리턴함.
			System.out.println("server: connected with client ");
			
			InputStream inputStream = connection_socket.getInputStream(); // 3. 소켓에서 inputStream 얻기. 
			System.out.println("server: got InputStream from socket");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // 바이트스트림 -> 문자스트림 
			System.out.println("server: attached InputStreamReader to InputStream");
			BufferedReader reader = new BufferedReader(inputStreamReader); // 버퍼스트림생성하여 inputStream에 연결.
			System.out.println("server: attached BufferedReader to InputStream");
			
			System.out.println("server: starts reading client message.");
			String inputLineString = reader.readLine(); // 4. BufferedReader 메소드인 readLine()으로 문자열을 한꺼번에 읽는다.
			System.out.println("server: client massage: " + inputLineString);
			
			connection_socket.close(); // 5. 연결 소켓닫고 
			System.out.println("server: socket closed. ");
			server_socket.close(); // 6. 서버 소켓도 닫는다. 
			System.out.println("server: server socket closed. ");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
