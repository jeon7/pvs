package _new.interprozesskommunikation.praktikum_tcp_kommunikation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClientPractice {

	public static void main(String[] args) {
		int port = 1200;
		
		try {
			InetAddress address = InetAddress.getByName("127.0.0.1");
			Socket client_socket = new Socket(address, port);
			System.out.println("client: client_socket created with InetAddress and port ");
			
			OutputStream outputStream = client_socket.getOutputStream();
			System.out.println("client: got outputStream from client_socket. ");
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			System.out.println("client: new outputStreamWriter created and attached to outputStream. ");
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			System.out.println("client: new bufferedWriter created and attached to outputStreamWriter. ");
			
			String message_out = "Hello Server! ";
			bufferedWriter.write(message_out);
			System.out.println("client: write client message with write() from bufferedWriter. ");
			
			bufferedWriter.flush();
			System.out.println("client: bufferedWriter flushed ");

			client_socket.close();
			System.out.println("client: socket closed ");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
