package it.unibo.bank.api;

public interface BankAccount {

    public String getIban();

    public double getBalance();

    public void deposit(double amount);
}
