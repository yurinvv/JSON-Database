package server.dbms.command;

import server.dbms.DBMS;
import server.dbms.messages.Request;
import server.dbms.messages.Response;

public class Get implements Command {
    private DBMS dbms;

    public Get(DBMS dbms) {
        this.dbms = dbms;
    }

    @Override
    public Response execute(Request request) {
        String value = dbms.get(request.getKey());
        if (value == null) {
            return new Response(
                    "ERROR",
                    "No such key",
                    null);
        }

        return new Response(
                "OK",
                null,
                value
        );
    }
}
