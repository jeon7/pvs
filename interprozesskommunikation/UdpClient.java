package _new.interprozesskommunikation;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

// client가 송신 
// server가 수신 
public class UdpClient {

	public static void main(String[] args) {
		int port = 1200;
		
		try {
			DatagramSocket socket = new DatagramSocket(); // 1. DatagramSocket 객체 생성. port넘버 없이. 
			String message = "client: hello server "; // 1-1 보낼메세지
			byte[] out_buffer = message.getBytes(); // 1-2 보낼메세지 string을 byte 배열로 변환.
			
			DatagramPacket out_packet = new DatagramPacket(out_buffer, out_buffer.length); // 2. 바이트버퍼로 DatagramPacket 객체 생성
			out_packet.setAddress(InetAddress.getByName("127.0.0.1")); // 2-1. 서버 ip셋팅. local host => 127.0.0.1 (둘다상관없음)
			out_packet.setPort(port); // 2-2. 서버 포트넘버 셋팅 
			
			System.out.println("client: starts sending. ");
			socket.send(out_packet); // 3. DatagramSocket 객체에 DatagramPacket 객체를 담아서 send()
			System.out.println("client: finished sending. ");
			socket.close(); // 4. 소켓 닫기 
			System.out.println("client: socket closed ");
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
}
