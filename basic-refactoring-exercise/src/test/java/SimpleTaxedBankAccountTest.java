import example.model.AccountHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleTaxedBankAccountTest extends SimpleBankAccountTest {

    private static final int WITHDRAWAL_FEE = 1;

    @BeforeEach
    @Override
    public void init() {
        accountHolder = new AccountHolder("Mario", "Rossi", HOLDER_ID);
        this.bankAccount = new SimpleTaxedBankAccount(accountHolder, INITIAL_BALANCE, WITHDRAWAL_FEE);
    }

    @Test
    @Override
    public void testWithdraw() {
        final int withdrawAmount = 70;
        bankAccount.deposit(accountHolder.id(), TEST_AMOUNT);
        bankAccount.withdraw(accountHolder.id(), withdrawAmount);
        assertEquals(TEST_AMOUNT - withdrawAmount - WITHDRAWAL_FEE, bankAccount.getBalance());
    }
}
