package com.martin.poc.mongo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoTest {

    public static void main(String[] args) {
        
        MongoClient dbcon = createConnection();
        ExecutorService executor = Executors.newFixedThreadPool(20);

        for(int i=0; i<5; i++){
            Task task = new Task(dbcon, "10001", 10);
            executor.submit(task);
        }

        executor.shutdown();
    }

    

    private static MongoClient createConnection(){
        String uri = "mongodb://localhost:27017";

      
        return MongoClients.create(uri);
    }

}
