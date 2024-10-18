package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class LockSupportMainV2 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new parkTest(), "Thread-1");
        thread1.start();

        sleep(100);
        log("Thread-1 state: " + thread1.getState());

    }

    static class parkTest implements Runnable {

        @Override
        public void run() {
            log("park start");
            LockSupport.parkNanos(2_000_000_000); // 2 seconds
            log("park end, state: " + Thread.currentThread().getState());
            log("interrupted state: " + Thread.currentThread().isInterrupted());
        }
    }
}
