package thread.sync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class BankAccountV4 implements BankAccount {

    private int balance;
    private final Lock lock = new ReentrantLock();

    public BankAccountV4(int balance) {
        this.balance = balance;
    }

    @Override
    public boolean withdraw(int amount) {

        log("Start transaction: " + getClass().getSimpleName());

        lock.lock(); // use ReentrantLock -> acquire lock

        try {

            log("[Start verification] withdrawal amount: " + amount + ", current balance: " + balance);
            if (balance < amount) {
                log("[Verification fail] withdrawal amount: " + amount + ", current balance: " + balance);
                return false;
            }

            log("[Withdrawal completed] withdrawal amount: " + amount + ", current balance: " + balance);
            sleep(1000); // taking time to withdraw
            balance = balance - amount;
            log("[Withdrawal completed] withdrawal amount: " + amount + ", changed balance: " + balance);

        } finally {
            lock.unlock(); // use ReentrantLock -> unlock(must be used)
        }


        log("End transaction");
        return true;
    }

    @Override
    public int getBalance() {

        lock.lock();

        try {
            return balance;
        } finally {
            lock.unlock();
        }
    }
}
