package server.dbms.command;

import server.dbms.messages.Request;
import server.dbms.messages.Response;

public class Invoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public Response executeCommand(Request request) {
        return command.execute(request);
    }
}
