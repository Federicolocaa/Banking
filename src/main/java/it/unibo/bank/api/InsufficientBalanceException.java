package it.unibo.bank.api;

public class InsufficientBalanceException extends Exception{

    public InsufficientBalanceException(String message){
        super(message);
    }
    
}
