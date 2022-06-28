package server;

import server.dbms.command.CmdClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static String ADDRESS = "127.0.0.1";
    private static int PORT = 23456;
    private static String dbPath = "C:\\Users\\Yurin\\Documents\\Father\\JAVA\\lab\\Set\\JSON Database\\JSON Database\\task\\src\\server\\data\\db.json";

    public static void main(String[] args) {
        CmdClient jsonDatabase = new CmdClient(dbPath);

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        System.out.println("Server started!");
        try (ServerSocket server = new ServerSocket(PORT)) {
            while (!server.isClosed()) {
                Session session = new Session(executorService, jsonDatabase, server.accept(), server);
                if (!server.isClosed()) {
                    executorService.submit(session);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
