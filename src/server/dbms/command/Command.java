package server.dbms.command;

import server.dbms.messages.Request;
import server.dbms.messages.Response;

public interface Command {
    Response execute(Request request);
}
