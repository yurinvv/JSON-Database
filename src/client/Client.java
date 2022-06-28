package client;

import com.beust.jcommander.JCommander;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private static String ADDRESS = "127.0.0.1";
    private static int PORT = 23456;
    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        try (
               Socket socket = new Socket(InetAddress.getByName(ADDRESS), PORT);
               DataInputStream input = new DataInputStream(socket.getInputStream());
               DataOutputStream output = new DataOutputStream(socket.getOutputStream());
               ) {
           System.out.println("Client started!");
           String msg = arguments.getRequestAsString();
           output.writeUTF(msg);
           System.out.println("Sent: " + msg);
           System.out.println("Received: " + input.readUTF());

       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
