# Istruzioni Laboratorio: Conto Corrente Transazionale

## Parte 1: Il Modello (`api` e `model`)
1.  Crea l'interfaccia `BankAccount` (package `it.unibo.bank.api`):
    * Definisce `String getIban()`, `double getBalance()`.
    * Definisce `void deposit(double amount)`.
2.  Crea la classe `Transaction` (package `it.unibo...bank.model`):
    * Campi: `private final String description`, `private final double amount`.
    * Crea un costruttore e i getter `getDescription()` e `getAmount()`.

## Parte 2: L'Eccezione (`api`)
1.  Crea la **checked exception** `InsufficientBalanceException` (package `it.unibo.bank.api`).
2.  Deve **estendere `Exception`**.
3.  Deve avere un costruttore `public InsufficientBalanceException(String message)`.

## Parte 3: La Classe Base (`impl`)
1.  Crea la classe `BankAccountImpl` (package `it.unibo.bank.impl`) che **implementa `BankAccount`**.
2.  Campi: `private final String iban`, `private double balance`.
3.  **Costruttore:** `public BankAccountImpl(String iban, double initialBalance)` (lancia `IllegalArgumentException` se `initialBalance < 0`).
4.  Implementa `getIban()`, `getBalance()`.
5.  Implementa `deposit(double amount)`: lancia `IllegalArgumentException` se `amount <= 0`, altrimenti aggiorna il saldo.
6.  **Metodo Chiave:** Aggiungi un metodo `protected void setBalance(double newBalance)` (per le sottoclassi).

## Parte 4: L'Interfaccia Specifica (`api`)
1.  Crea l'interfaccia `TransactionalBankAccount<T extends Transaction>` (package `it.unibo.bank.api`).
2.  Questa interfaccia deve **estendere `BankAccount`**.
3.  Deve definire due nuovi metodi:
    * `void withdraw(double amount) throws InsufficientBalanceException`
    * `List<T> getHistory()`

## Parte 5: L'Implementazione Specifica (`impl`)
1.  Crea la classe `TransactionalBankAccountImpl<T extends Transaction>` (package `it.unibo.bank.impl`).
2.  Questa classe deve **estendere `BankAccountImpl`** e **implementare `TransactionalBankAccount<T>`**.
3.  **Campo:** Aggiungi `private final List<T> history`.
4.  **Costruttore:** `public TransactionalBankAccountImpl(String iban, double initialBalance)`:
    * Chiama `super(iban, initialBalance)`.
    * Inizializza `history` (es. `new ArrayList<>()`).
5.  **Implementa `getHistory()`**:
    * Deve restituire una **copia difensiva** della lista (`new ArrayList<>(this.history)`).
6.  **Implementa `withdraw(double amount)`**:
    * Lancia `IllegalArgumentException` se `amount <= 0`.
    * Lancia `InsufficientBalanceException` se `amount > getBalance()`.
    * Se i controlli passano, usa `setBalance(getBalance() - amount)`.