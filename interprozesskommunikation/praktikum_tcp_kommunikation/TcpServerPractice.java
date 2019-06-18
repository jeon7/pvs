package _new.interprozesskommunikation.praktikum_tcp_kommunikation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerPractice {

	public static void main(String[] args) {
		int port = 1200;
		
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("server: serverSocket created. waiting for connection to client. ");
			Socket connection_socket = serverSocket.accept();
			System.out.println("server: when connected with client, accept() returns connection_socket "
								+ "that contains client message. ");
			
			InputStream inputStream = connection_socket.getInputStream();
			System.out.println("server: got inputStream from connection_socket. ");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			System.out.println("server: new inputStreamReader created and attached to InputStream. ");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			System.out.println("server: new bufferedReader created and attached to inputStreamReader. ");
			
			String message_in = bufferedReader.readLine();
			System.out.println("server: read client message with readLine() from bufferedReader. ");
			
			System.out.println("client_message: "+ message_in);
			
			connection_socket.close();
			System.out.println("server: connection_socket closed ");
			serverSocket.close();
			System.out.println("server: server socket closed. ");

					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
