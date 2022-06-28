package client;

import com.beust.jcommander.Parameter;
import com.google.gson.Gson;
import server.dbms.messages.Request;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Arguments {
    private static String path = "C:\\Users\\Yurin\\Documents\\Father\\JAVA\\lab\\Set\\JSON Database\\JSON Database\\task\\src\\client\\data\\";

    @Parameter(names = "-t", description = "the type of the request")
    private String type;
    @Parameter(names = "-k", description = "the key of the value")
    private String key;
    @Parameter(names = "-v", description = "the value to save in the database")
    private String value;
    @Parameter(names = "-in", description = "read a request from a file")
    private String filePath;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getRequestAsString() throws IOException {
        if (filePath == null) {
            return new Gson().toJson(new Request(type, key, value));
        } else {
            return new String(Files.readAllBytes(Paths.get(path + filePath)));
        }
    }
}
