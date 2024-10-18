package thread.control.interrupt;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ThreadStopMainV2 {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread thread = new Thread(task, "work");
        thread.start();

        sleep(4000);
        log("order to stop working thread.interrupt()");
        thread.interrupt();
        log("work thread interrupt state1 = " + thread.isInterrupted());

    }

    static class MyTask implements Runnable {

        @Override
        public void run() {

            try {

                while(true) { // does not check interrupt state
                    log("Working");
                    Thread.sleep(3000);
                }

            } catch (InterruptedException e) {
                log("work thread interrupt state2 = " + Thread.currentThread().isInterrupted());
                log("interrupt message=" + e.getMessage());
                log("state=" + Thread.currentThread().getState());
            }

            log("resource organization");
            log("resource end");
        }
    }
}
