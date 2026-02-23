import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
abstract class SimpleBankAccountTest {

    protected static final int INITIAL_BALANCE = 0;
    protected static final int TEST_AMOUNT = 100;
    protected static final int HOLDER_ID = 1;
    protected static final int WRONG_HOLDER_ID = 2;
    protected AccountHolder accountHolder;
    protected BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", HOLDER_ID);
        bankAccount = new SimpleBankAccount(accountHolder, INITIAL_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(INITIAL_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.id(), TEST_AMOUNT);
        assertEquals(TEST_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(accountHolder.id(), TEST_AMOUNT);
        bankAccount.deposit(WRONG_HOLDER_ID, TEST_AMOUNT);
        assertEquals(TEST_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testNegativeDeposit() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(accountHolder.id(), -TEST_AMOUNT));
    }

    @Test
    void testWithdraw() {
        final int withdrawAmount = 70;
        bankAccount.deposit(accountHolder.id(), TEST_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), withdrawAmount);
        assertEquals(TEST_AMOUNT - withdrawAmount, bankAccount.getBalance());
    }

    @Test
    void testWrongWithdraw() {
        final int withdrawAmount = 70;
        bankAccount.deposit(accountHolder.id(), TEST_AMOUNT);
        bankAccount.withdraw(WRONG_HOLDER_ID, withdrawAmount);
        assertEquals(TEST_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testNegativeWithdraw() {
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(accountHolder.id(), -TEST_AMOUNT));
    }

    @BeforeEach
    public abstract void init();
}
