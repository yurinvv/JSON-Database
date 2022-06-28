package server.dbms.command;

import server.dbms.DBMS;
import server.dbms.messages.Request;
import server.dbms.messages.Response;

public class Set implements Command {
    private DBMS dbms;

    public Set(DBMS dbms) {
        this.dbms = dbms;
    }

    @Override
    public Response execute(Request request) {
        dbms.set(request.getKey(), request.getValue());
        return new Response("OK", null, null);
    }
}
