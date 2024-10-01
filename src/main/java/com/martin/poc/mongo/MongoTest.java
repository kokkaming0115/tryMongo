package com.martin.poc.mongo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class MongoTest {

    public static void main(String[] args) {
        Integer amount = 10000;

        ExecutorService executor = Executors.newFixedThreadPool(20);

        Callable<Integer> task = () -> {
            boolean cannotGetTheLock = true;
            while(cannotGetTheLock){
                try{
                    getLock();
                    cannotGetTheLock=false;
                }catch(CannotGetTheLockException e){
                    e.printStackTrace();
                    print("sleeping");
                    Thread.sleep(1000);
                    print("Wake up");
                }
                
            }
            
            updateTheAccount(amount, 10);
            
            releaseTheLock();

            return amount;

        };

        for(int i=0; i<100; i++){
            executor.submit(task);
        }

        executor.shutdown();
    }

    private static void releaseTheLock() {
        print("Release the Lock!" );
    }

    private static int updateTheAccount(int amount, int i) {
        amount+=i;
        print("Update the account");
        return amount;
    }

    private static void getLock() throws CannotGetTheLockException{
        print("Get the Lock! " );
        print("Locked"); 
        
    }

    private static void print(String message){
        System.out.println(Thread.currentThread().getName()+ " => "+ message);
    }

    private static void createConnection(){
        String uri = "mongodb+srv://user:password@cluster.example.mongodb.net/";
        MongoClient mongoClient = MongoClients.create(uri);
    }

}
