package it.unibo.bank.impl;

import java.util.ArrayList;
import java.util.List;

import it.unibo.bank.api.InsufficientBalanceException;
import it.unibo.bank.api.TransactionalBankAccount;
import it.unibo.bank.model.Transaction;

public class TransactionalBankAccountImpl<T extends Transaction> extends BankAccountImpl implements TransactionalBankAccount<T>{
    
    private final List<T> history = new ArrayList<>();

    public TransactionalBankAccountImpl(String iban, double initialBalance){
        super(iban, initialBalance);
    }

    @Override
    public List<T> getHistory(){
        return new ArrayList<>(history); // returna una copia difensiva dell'array history
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalanceException{
        if (amount <= 0) { throw new IllegalArgumentException("amount <= 0!");}
        else if (amount > getBalance()) { throw new InsufficientBalanceException("amount > of balance!");}
        else{setBalance(getBalance() - amount);}
    }
}
