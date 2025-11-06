package it.unibo.bank.api;

import java.util.List;

import it.unibo.bank.model.Transaction;

public interface TransactionalBankAccount <T extends Transaction> extends BankAccount{
    
    public void withdraw(double amount) throws InsufficientBalanceException;

    public List<T> getHistory();
}
