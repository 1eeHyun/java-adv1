package thread.control.join;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class JoinMainV4 {

    public static void main(String[] args) throws InterruptedException {

        log("start");
        SumTask task1 = new SumTask(1, 50);

        Thread thread1 = new Thread(task1, "thread-1");

        thread1.start();

        // wait for thread
        log("join(1000) - main thread wait for thread1 for 1 second");
        thread1.join(1000);
        log("main thread waiting end");

        log("task1.result = " + task1.result);
        log("End");
    }

    static class SumTask implements Runnable {

        int startVal;
        int endVal;
        int result = 0;

        public SumTask(int startVal, int endVal) {
            this.startVal = startVal;
            this.endVal = endVal;
        }

        @Override
        public void run() {

            log("Start working");
            sleep(2000);
            int sum = 0;
            for (int i = startVal; i <= endVal; i++) {
                sum += i;
            }

            result = sum;
            log("End working=" + result);
        }
    }
}
