package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    protected double balance;
    protected final AccountHolder holder;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (amount < 0)
            throw new IllegalArgumentException();
        if (checkUser(userID)) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (amount < 0)
            throw new IllegalArgumentException();
        if (checkUser(userID) && isWithdrawAllowed(amount))
            this.balance -= amount;
    }

    protected boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount;
    }

    protected boolean checkUser(final int id) {
        return this.holder.id() == id;
    }
}
