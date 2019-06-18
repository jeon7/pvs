package _new.interprozesskommunikation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {

	public static void main(String[] args) {
		int port = 1200;
		
		try {
			Socket client_socket = new Socket(InetAddress.getByName("127.0.0.1"), port); // 1. 소켓객체생성. 서버ip와 포트넘버와 함께. 
			
			OutputStream outputStream = client_socket.getOutputStream(); // 소켓객체 에서 아웃풋 스트림 얻기. 
			System.out.println("client: got OutputStream from socket");
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream); // 바이트스트림 -> 문자스트림 
			System.out.println("client: attached OutputStreamWriter to OutputStream");
			BufferedWriter writer = new BufferedWriter(outputStreamWriter); // 2. 버퍼스트림생성하여 outputStream에 연결.
			System.out.println("client: attached BufferedWriter to OutputStreamWriter");
			
			String message_out = "client: hello server!";
			
			System.out.println("client: starts writing client message.");
			writer.write(message_out); // 3. writer 메소드의 문자열 전체를 출력스트림으로 보냄. 
			System.out.println("client: sent client message. ");
			writer.flush(); // 3-1. 버퍼에 잔류하는 데이터 완전 출력.
			System.out.println("client: bufferedWriter flushed ");
			
			System.out.println("client: socket closed ");
			client_socket.close(); // 4. 소켓닫기 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
