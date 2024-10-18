package thread.control.volatile1;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class VolatileFlagMain {

    public static void main(String[] args) {

        MyTask task = new MyTask();
        Thread t = new Thread(task, "work");
        log("runFlag = " + task.runFlag);
        t.start();

        sleep(1000);
        log("change runFlag value to false");
        task.runFlag = false;
        log("runFlag = " + task.runFlag);
        log("main end");

        // without using volatile (access to main memory instead of cache memory)
        // does not end the while loop in run() method of work thread
    }

    static class MyTask implements Runnable {

//        boolean runFlag = true;
        volatile boolean runFlag = true;


        @Override
        public void run() {
            log("task start");
            while (runFlag) {

            }

            log("task end");
        }
    }
}
