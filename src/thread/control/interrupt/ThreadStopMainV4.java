package thread.control.interrupt;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ThreadStopMainV4 {

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

            while (!Thread.interrupted()) { // change interrupt state
                log("Working");
            }

            log("work thread interrupt state2 = " + Thread.currentThread().isInterrupted());

            try {
                log("resource organization");
                Thread.sleep(1000);
                log("resource end");
            } catch (InterruptedException e) {
                log("failed to organize resource - interrupt occurred");
                log("work thread interrupt state3 = " +Thread.currentThread().isInterrupted());
            }

            log("end working");
        }
    }
}
