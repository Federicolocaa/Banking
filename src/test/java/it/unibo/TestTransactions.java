package it.unibo;

// Questi import daranno errore finché non creerai le classi!
import it.unibo.bank.api.BankAccount;
import it.unibo.bank.api.InsufficientBalanceException;
import it.unibo.bank.api.TransactionalBankAccount;
import it.unibo.bank.impl.BankAccountImpl;
import it.unibo.bank.impl.TransactionalBankAccountImpl;
import it.unibo.bank.model.Transaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TestTransactions {

    // Testiamo usando l'interfaccia base
    private BankAccount baseAccount;
    // Testiamo usando l'interfaccia specifica
    private TransactionalBankAccount<Transaction> transactionalAccount;

    private final String TEST_IBAN = "IT00A12345B67890123456789012";

    @BeforeEach
    void setUp() {
        // Creiamo la classe di implementazione specifica
        transactionalAccount = new TransactionalBankAccountImpl<>(TEST_IBAN, 1000.0);
        // La assegniamo anche a una variabile di tipo base (Polimorfismo!)
        baseAccount = transactionalAccount;
    }

    @Test
    void testDeposit() {
        assertEquals(1000.0, baseAccount.getBalance());
        baseAccount.deposit(200.0);
        assertEquals(1200.0, baseAccount.getBalance());
    }

    @Test
    void testDepositUncheckedFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            baseAccount.deposit(-100.0);
        });
    }

    @Test
    void testWithdrawSuccess() {
        assertDoesNotThrow(() -> {
            transactionalAccount.withdraw(200.0);
        });
        assertEquals(800.0, transactionalAccount.getBalance());
    }

    @Test
    void testWithdrawUncheckedFail() {
        assertThrows(IllegalArgumentException.class, () -> {
            transactionalAccount.withdraw(0);
        });
    }

    @Test
    void testWithdrawCheckedFail() {
        assertThrows(InsufficientBalanceException.class, () -> {
            transactionalAccount.withdraw(2000.0); // Ho solo 1000
        });
        assertEquals(1000.0, transactionalAccount.getBalance());
    }

    @Test
    void testHistoryIsDefensiveCopy() {
        List<Transaction> history = transactionalAccount.getHistory();
        assertNotNull(history);
        assertEquals(0, history.size());
        
        history.add(new Transaction("Fake transaction", 1.0));
        
        assertEquals(
            0,
            transactionalAccount.getHistory().size(),
            "getHistory() must return a defensive copy, not the original list!"
        );
    }
}