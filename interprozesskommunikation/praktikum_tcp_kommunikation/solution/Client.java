package _new.interprozesskommunikation.praktikum_tcp_kommunikation.solution;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket client_socket = new Socket(InetAddress.getByName("127.0.0.1"), 1200);
            BufferedReader reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client_socket.getOutputStream()));
            Scanner input = new Scanner(System.in);

            while(true){
                System.out.println("Eingabe: ");
                String input_message = input.nextLine();
                if(input_message.equals("close")){
                    break;
                }
                writer.write(input_message);
                writer.newLine();
                writer.flush();
                String server_message = reader.readLine();
                if(server_message == null){
                    System.out.println("no response from server. closing");
                    break;
                }
                System.out.println(server_message);
            }

            client_socket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }    
}