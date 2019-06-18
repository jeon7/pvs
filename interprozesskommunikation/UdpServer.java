package _new.interprozesskommunikation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

//client가 송신 
//server가 수신 
public class UdpServer {
	public static void main(String[] args) {
		int port = 1200;
		try {
			DatagramSocket socket = new DatagramSocket(port); // 1. DatagramSocket 객체 생성. 메세지 받을 port 넘버도 같이 넘겨줌.
			byte[] in_buffer = new byte[100];	// 1-1. 수신메세지를 담을 바이트버퍼 생성. 
			
			DatagramPacket in_packet = new DatagramPacket(in_buffer, in_buffer.length); // 2. 바이트버퍼로 DatagramPacket 객체 생성 
		
			System.out.println("server: starts receiving. ");
			socket.receive(in_packet); // 3. DatagramSocket 객체에 DatagramPacket 객체를 담아서 receive() 호출 (메세지 받을때까지 block됨)
										// packet 객체안의 바이트버퍼에 수신내용이 담겨져 옴. 
			System.out.println("server: finished receiving. ");

			InetAddress  client_address = in_packet.getAddress(); // 그 메세지 안에서 client주소 꺼냄 
			System.out.println("server: " + new String(in_buffer) + " from " + client_address); 
			
			socket.close(); // 4. 소켓 닫기 
			System.out.println("server: socket closed ");
			
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
