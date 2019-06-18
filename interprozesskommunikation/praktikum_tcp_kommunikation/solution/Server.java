package _new.interprozesskommunikation.praktikum_tcp_kommunikation.solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static int value = 0;
    public static void main(String[] args) {
        try {
            ServerSocket server_socket = new ServerSocket(1200);
            Socket connect_socket = server_socket.accept();
            System.out.println("Client connected: " + connect_socket.getInetAddress());
            BufferedReader reader = new BufferedReader(new InputStreamReader(connect_socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connect_socket.getOutputStream()));

            while(!connect_socket.isClosed()){
                String client_message = reader.readLine();
                if(client_message == null){
                    System.out.println("no response from client. closing");
                    break;
                }
                if(client_message.equals("increase")){
                    value++;
                    writer.write("done");
                    writer.newLine();
                    writer.flush();
                    System.out.println("Value incremented");
                } else if(client_message.equals("decrease")){
                    value--;
                    writer.write("done");
                    writer.newLine();
                    writer.flush();
                    System.out.println("Value decremented");
                } else if(client_message.equals("get")){
                    writer.write("Value: " + value);
                    writer.newLine();
                    writer.flush();
                    System.out.println("Value sent to client");
                } else {
                    writer.write("Unknown message: " + client_message);
                    writer.newLine();
                    writer.flush();
                    System.out.println("Unknown message: " + client_message);
                }
            }
            
            connect_socket.close();
            server_socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}