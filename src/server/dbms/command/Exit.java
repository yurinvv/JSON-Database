package server.dbms.command;

import server.dbms.messages.Request;
import server.dbms.messages.Response;

public class Exit implements Command {
    @Override
    public Response execute(Request request) {
        return new Response(
                "OK",
                null,
                null
        );
    }
}
