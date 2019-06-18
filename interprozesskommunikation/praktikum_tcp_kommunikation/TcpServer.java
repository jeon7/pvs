package _new.interprozesskommunikation.praktikum_tcp_kommunikation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static int port = 5001;
	public static int server_var = 0;

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("server: serverSocket created. ");
			
			while (true) {
				System.out.println("server: waiting for connection to client. ");
				Socket connection_socket = serverSocket.accept();
				System.out.println("server: connected with client");
				
				InputStream inputStream = connection_socket.getInputStream();
				System.out.println("server: got inputStream from connection_socket. ");
				InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
				System.out.println("server: new inputStreamReader created and attached to InputStream. ");
				BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				System.out.println("server: new bufferedReader created and attached to inputStreamReader. ");
				
				String message_in = bufferedReader.readLine();
				System.out.println("server: read client message with readLine() from bufferedReader. ");
				System.out.println("client_message: " + message_in);
				
				OutputStream outputStream = connection_socket.getOutputStream();
				System.out.println("server: got outputStream from connection_socket. ");
				OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
				System.out.println("server: new OutputStreamWriter created and attached to outputStream. ");
				BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
				System.out.println("server: new BufferedWriter created and attached to OutputStreamWriter. ");
				
				String message_out = "Hello Client";
				bufferedWriter.write(message_out);
				System.out.println("server: wrote server message with write() from bufferedWriter. ");

				bufferedWriter.flush();
				System.out.println("server: bufferedWriter flushed ");
				
				inputStream.close();
				System.out.println("server: inputStream closed ");

				outputStream.close();
				System.out.println("server: outputStream closed ");
				
				connection_socket.close();
				System.out.println("server: connection_socket closed ");
				
			}
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(!serverSocket.isClosed()) {
			try {
				serverSocket.close();
				System.out.println("server: serverSocket closed ");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void increase() {
		server_var++;
	}
	
	public static void decrease() {
		server_var--;
	}

	public static int getServer_var() {
		return server_var;
	}

}
