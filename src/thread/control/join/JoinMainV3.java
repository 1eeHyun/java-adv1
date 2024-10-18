package thread.control.join;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class JoinMainV3 {

    public static void main(String[] args) throws InterruptedException {

        log("start");
        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        Thread thread1 = new Thread(task1, "thread-1");
        Thread thread2 = new Thread(task2, "thread-2");

        thread1.start();
        thread2.start();

        // wait for thread
        log("join() - main thread wait for thread1, thread2's terminating time");
        thread1.join();
        thread2.join();
        log("main thread waiting end");


        log("task1.result = " + task1.result);
        log("task2.result = " + task2.result);

        int sumAll= task1.result + task2.result;
        log("task1 + task2 = " + sumAll);
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
