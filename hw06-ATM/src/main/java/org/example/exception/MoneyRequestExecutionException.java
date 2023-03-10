package org.example.exception;

public class MoneyRequestExecutionException extends RuntimeException{
    private static final String MESSAGE = "Unable to complete request! ";
    public MoneyRequestExecutionException(){
        super(MESSAGE);

    }

}
