import example.model.AccountHolder;
import example.model.SimpleBankAccount;

public class SimpleTaxedBankAccount extends SimpleBankAccount {

    public final double withdrawalFee;

    public SimpleTaxedBankAccount(AccountHolder holder, double balance, double withdrawalFee) {
        super(holder, balance);
        this.withdrawalFee = withdrawalFee;
    }

    @Override
    public void withdraw(final int id, double amount) {
        amount += this.withdrawalFee;
        super.withdraw(id, amount);
    }

}
