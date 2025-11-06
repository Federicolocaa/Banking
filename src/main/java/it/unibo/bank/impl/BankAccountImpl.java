package it.unibo.bank.impl;

import it.unibo.bank.api.BankAccount;

public class BankAccountImpl implements BankAccount {

    private final String iban;
    private double balance;

    public BankAccountImpl(final String iban, double initialBalance){
         if (initialBalance < 0) { throw new IllegalArgumentException("Bilancio < di 0!");}
        this.iban = iban;
        this.balance = initialBalance;
    }

    @Override
    public String getIban(){
        return this.iban;
    }

    @Override
    public double getBalance(){
        return this.balance;
    }

    @Override
    public void deposit(double amount){
        if (amount <= 0){throw new IllegalArgumentException("Ammonto <= 0!");}
        else{balance += amount;}
    }

    protected void setBalance(final double newBalance){
        this.balance = newBalance;
    }
}
