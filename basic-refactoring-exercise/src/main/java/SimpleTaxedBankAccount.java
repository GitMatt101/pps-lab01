import example.model.AccountHolder;
import example.model.SimpleBankAccount;

public class SimpleTaxedBankAccount extends SimpleBankAccount {

    private final double withdrawalFee;

    public SimpleTaxedBankAccount(AccountHolder holder, double balance, double withdrawalFee) {
        super(holder, balance);
        this.withdrawalFee = withdrawalFee;
    }

    @Override
    public void withdraw(final int id, double amount) {
        if (amount <= 0)
            return;
        amount += this.withdrawalFee;
        super.withdraw(id, amount);
    }

}
