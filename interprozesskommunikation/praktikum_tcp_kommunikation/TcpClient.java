package _new.interprozesskommunikation.praktikum_tcp_kommunikation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TcpClient {
	public static int port = 5001;
	
	public static void main(String[] args) {
		
		Socket client_socket = null;
		
		try {
			InetAddress address = InetAddress.getByName("127.0.0.1");
			client_socket = new Socket(address, port);
			System.out.println("client: client_socket created with InetAddress and port. connected with server. ");

			OutputStream outputStream = client_socket.getOutputStream();
			System.out.println("client: got outputStream from client_socket. ");
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
			System.out.println("client: new outputStreamWriter created and attached to outputStream. ");
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
			System.out.println("client: new bufferedWriter created and attached to outputStreamWriter. ");

			String message_out = "Hello Server! ";
			System.out.println("client: starts writing client message with write() from bufferedWriter. ");
			bufferedWriter.write(message_out);
			System.out.println("client: wrote client message with write() from bufferedWriter. ");
			bufferedWriter.flush();
			System.out.println("client: bufferedWriter flushed ");

			InputStream inputStream = client_socket.getInputStream();
			System.out.println("client: got inputStream from client_socket. ");
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			System.out.println("client: new inputStreamReader created and attached to inputStream. ");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			System.out.println("client: new bufferedReader created and attached to inputStreamReader. ");
			
			String message_in = bufferedReader.readLine();
			System.out.println("client: read client message with readLine() from bufferedReader. ");
			System.out.println("server_message: "+ message_in);
			
			inputStream.close();
			System.out.println("server: inputStream closed ");

			outputStream.close();
			System.out.println("server: outputStream closed ");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(!client_socket.isClosed()) {
			try {
				client_socket.close();
				System.out.println("client: client_socket closed ");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
