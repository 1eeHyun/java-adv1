package thread.cas.spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

import static thread.util.MyLogger.log;

public class SpinLock {

    private final AtomicBoolean lock = new AtomicBoolean(false);

    public void lock() {
        log("Trying to acquire a lock");
        while(!lock.compareAndSet(false, true)) {

            // wait until acquiring a lock
            log("Failed to acquire a lock - spin wait");
        }

        log("Lock acquired");
    }

    public void unlock() {
        lock.set(false);
        log("Returned lock");
    }
}
