package com.martin.poc.mongo;

import java.util.concurrent.Callable;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import static com.mongodb.client.model.Filters.eq;

public class Task implements Callable<Integer>{
    
    private MongoClient client;
    private int amount;
    private String lockKeyValue="10003";

    public Task(MongoClient client, String accountNo, int amount){
        this.client = client;
        this.lockKeyValue = accountNo;
        this.amount = amount;
    }

    @Override
    public Integer call() throws Exception {
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
    }

    private void releaseTheLock() {
        print("Release the Lock!" );

        MongoDatabase database=this.client.getDatabase("mongotry");

        MongoCollection<Document> collection = database.getCollection("lock");

        try{
           Bson deleteQuery = eq("accountNo", lockKeyValue);

            collection.deleteOne(deleteQuery);


            print("Released!" );
        }catch(Exception e){
            e.printStackTrace();
        }

        
    }

    private int updateTheAccount(int amount, int i) {
        
        print("Update the account");
        MongoDatabase database=this.client.getDatabase("mongotry");

        MongoCollection<Document> collection = database.getCollection("wallet");
        //get the account Value
        Document r = collection.find(eq("accountNo", this.lockKeyValue)).first();
        int oAmount = r.getInteger("amount");
        print("Current Amount = >" + oAmount);
        //update the amount
        int newAmount = oAmount - amount;
        Document updateQuery = new Document().append("accountNo", this.lockKeyValue);

        Bson updates = Updates.combine(
            Updates.set("amount", newAmount)
        );

        collection.updateOne(updateQuery, updates);
        //save
        print("Update the account ("+ newAmount +") ...... Done!");
        return oAmount - amount;
    }

    private void getLock() throws CannotGetTheLockException{
        print("Get the Lock! " );
        MongoDatabase database=this.client.getDatabase("mongotry");

        MongoCollection<Document> collection = database.getCollection("lock");

        try{
            InsertOneResult result = collection.insertOne(new Document()
            .append("_id", new ObjectId())
            .append("accountNo", lockKeyValue));


            print("Locked"); 
        }catch(Exception e){
            throw new CannotGetTheLockException(e);
        }
        
    }

    private void print(String message){
        System.out.println(Thread.currentThread().getName()+ " => "+ message);
    }


}
