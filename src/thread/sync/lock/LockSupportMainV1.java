package thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new parkTest(), "Thread-1");
        thread1.start();

        sleep(100);
        log("Thread-1 state: " + thread1.getState());

        log("main -> unpark(Thread-1)");
        LockSupport.unpark(thread1); // 1. use unpark
        thread1.interrupt(); // 2. use interrupt
    }

    static class parkTest implements Runnable {

        @Override
        public void run() {
            log("park start");
            LockSupport.park();
            log("park end, state: " + Thread.currentThread().getState());
            log("interrupted state: " + Thread.currentThread().isInterrupted());
        }
    }
}
