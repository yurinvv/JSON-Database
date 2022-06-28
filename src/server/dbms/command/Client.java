package server.dbms.command;

import com.google.gson.Gson;
import server.dbms.DBMS;
import server.dbms.messages.Request;
import server.dbms.messages.Response;

public class Client {
    private Invoker invoker;
    private DBMS receiver;
    private Command get;
    private Command set;
    private Command delete;
    private Command exit;
    private boolean exitFlag;

    public Client(String path) {
        invoker = new Invoker();
        receiver = new DBMS(path);
        get = new Get(receiver);
        set = new Set(receiver);
        delete = new Delete(receiver);
        exit = new Exit();
        exitFlag = false;
    }

    public String executeCommand(String requestMsg) {
        Gson gson = new Gson();
        Request request = gson.fromJson(requestMsg, Request.class);

        switch (request.getType()) {
            case "set":
                invoker.setCommand(set);
                return gson.toJson(invoker.executeCommand(request));
            case "get":
                invoker.setCommand(get);
                return gson.toJson(invoker.executeCommand(request));
            case "delete":
                invoker.setCommand(delete);
                return gson.toJson(invoker.executeCommand(request));
            case "exit":
                exitFlag = true;
                invoker.setCommand(exit);
                return gson.toJson(invoker.executeCommand(request));
            default:
                return gson.toJson(new Response("ERROR", null, null));
        }
    }

    public boolean isExitFlag() {
        return exitFlag;
    }

    public void setExitFlag(boolean exitFlag) {
        this.exitFlag = exitFlag;
    }
}
