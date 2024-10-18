package thread.control.interrupt;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ThreadStopMainV3 {

    public static void main(String[] args) {
        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(100);
        log("order to stop working thread.interrupt()");
        thread.interrupt();
        log("work thread interrupt state1 = " + thread.isInterrupted());
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) { // does not change interrupt state
                log("Working");
            }

            log("work thread interrupt state2 = " + Thread.currentThread().isInterrupted());

            try {
                log("resource organization");
                Thread.sleep(1000); // exception occurs
                log("resource end");
            } catch (InterruptedException e) {
                log("failed to organize resource - interrupt occurred");
                log("work thread interrupt state3 = " +Thread.currentThread().isInterrupted());
            }

            log("end working");
        }
    }
}
