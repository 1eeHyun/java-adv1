package thread.executor.future;

import java.util.concurrent.*;

import static thread.util.MyLogger.log;

public class SumTaskMainV2_Bad {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        SumTask task1 = new SumTask(1, 50);
        SumTask task2 = new SumTask(51, 100);
        ExecutorService es = Executors.newFixedThreadPool(2);

        Future<Integer> future1 = es.submit(task1);
        Integer sum1 = future1.get(); // 2 seconds

        Future<Integer> future2 = es.submit(task2);
        Integer sum2 = future2.get(); // 2 seconds


        log("task1.result = " + sum1);
        log("task2.result = " + sum2);

        int sumAll = sum1 + sum2;
        log("task1 + task2 = " + sumAll);
        log("End");

        es.close();
    }

    static class SumTask implements Callable<Integer> {

        int startValue;
        int endValue;

        public SumTask(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() throws InterruptedException {

            log("Start working");
            Thread.sleep(2000);
            int sum = 0;

            for (int i = startValue; i<= endValue; i++) {
                sum += i;
            }

            log("End working, result = " + sum);
            return sum;
        }
    }
}
