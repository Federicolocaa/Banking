package it.unibo.bank.model;

public class Transaction {
    
    private final String description;
    private final double amount;

    public Transaction(final String description, final double amount){
        this.description = description;
        this.amount = amount;
    }

    public String getDescription(){
        return this.description;
    }

    public double getAmount(){
        return this.amount;
    }
}
