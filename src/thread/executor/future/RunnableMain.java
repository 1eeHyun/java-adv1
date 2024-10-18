package thread.executor.future;

import java.util.Random;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class RunnableMain {

    public static void main(String[] args) throws InterruptedException {

        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task, "Thread-1");
        thread.start();
        thread.join();
        int result = task.value;
        log("result value = " + result);
    }

    static class MyRunnable implements Runnable {

        int value;

        @Override
        public void run() {

            log("Start Runnable");
            sleep(2000);
            value = new Random().nextInt(10);
            log("created value = " + value);
            log("Completed Runnable");
        }
    }
}
