package server.dbms;

import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DBMS {
    //Object-Relational Mapping
    private Map<String,String> dbMap;
    private String filePath;
    private ReadWriteLock lock;
    private Lock readLock;
    private Lock writeLock;

    public DBMS(String filePath) {
        this.filePath = filePath;
        this.dbMap = new HashMap<>();
        receiveData();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    //ORM method
    private void receiveData() {
        try {
            String dbAsString = new String(Files.readAllBytes(Paths.get(filePath)));
            Map<String, String> tmp = new Gson().fromJson(dbAsString, Map.class);
            dbMap = tmp == null ? dbMap : tmp;
        } catch (IOException e) {
            System.out.println("Cannot open file for reading: " + e.getMessage());
        }
    }

    //ORM method
    private void synchronize() {
        File file = new File(filePath);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(new Gson().toJson(dbMap));
        } catch (IOException e) {
            System.out.println("Cannot open file for writing: " + e.getMessage());
        }
    }

    public void set(String key, String value) {
        writeLock.lock();
        try {
            dbMap.put(key, value);
            synchronize();
        } finally {
            writeLock.unlock();
        }
    }

    public String delete(String key) {
        String oldValue = dbMap.remove(key);
        synchronize();
        return oldValue;
    }

    public String get(String key) {
        return dbMap.get(key);
    }
}
