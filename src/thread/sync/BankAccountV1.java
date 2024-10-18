package thread.sync;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class BankAccountV1 implements BankAccount {

    private int balance;
//     volatile private int balance;

    public BankAccountV1(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {

        log("Start transaction: " + getClass().getSimpleName());

        log("[Start verification] withdrawal amount: " + amount + ", current balance: " + balance);
        if (balance < amount) {
            log("[Fail verification] withdrawal amount: " + amount + ", current balance: " + balance);
            return false;
        }

        log("[Withdrawal completed] withdrawal amount: " + amount + ", current balance: " + balance);
        sleep(1000); // taking time to withdraw
        balance = balance - amount;
        log("[Withdrawal completed] withdrawal amount: " + amount + ", changed balance: " + balance);

        log("End transaction");
        return true;
    }

    @Override
    public int getBalance() {
        return balance;
    }
}
