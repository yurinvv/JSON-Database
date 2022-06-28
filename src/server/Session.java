package server;

import server.dbms.command.CmdClient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class Session implements Runnable {

    private Socket socket;
    private ServerSocket server;
    private CmdClient jsonDatabase;
    private ExecutorService executorService;

    public Session(ExecutorService executorService, CmdClient jsonDatabase, Socket socket, ServerSocket server) {
        this.socket = socket;
        this.server = server;
        this.jsonDatabase = jsonDatabase;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try (
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                ) {
            output.writeUTF(jsonDatabase.executeCommand(input.readUTF()));
            if (jsonDatabase.isExitFlag()) {
                socket.close();
                server.close();
                executorService.shutdown();
                System.exit(0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
