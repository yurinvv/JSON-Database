package server.dbms.command;

import server.dbms.DBMS;
import server.dbms.messages.Request;
import server.dbms.messages.Response;

public class Delete implements Command {
    private DBMS dbms;

    public Delete(DBMS dbms) {
        this.dbms = dbms;
    }

    @Override
    public Response execute(Request request) {
        String oldValue = dbms.delete(request.getKey());
        if (oldValue == null) {
            return new Response(
                    "ERROR",
                    "No such key",
                    null
            );
        }

        return new Response(
                "OK",
                null,
                null
        );
    }
}
