package thread.cas.spinlock;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class SpinLockBad {

    private volatile boolean lock = false;

    public void lock() {
        log("Trying to acquire a lock");
        while(true) {
            if (!lock) {            // 1. check lock usage
                sleep(100);
                lock = true;        // 2. change lock value
                break;
            } else {

                // until getting a lock
                log("Failed to acquire the lock - spin wait");
            }
        }

        log("Lock acquired");
    }

    public void unlock() {
        lock = false;
        log("Returned lock");
    }
}
