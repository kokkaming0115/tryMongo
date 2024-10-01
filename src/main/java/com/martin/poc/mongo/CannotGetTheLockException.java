package com.martin.poc.mongo;

public class CannotGetTheLockException extends Exception{

    public CannotGetTheLockException(Exception e) {
        super(e);
    }
    
}
