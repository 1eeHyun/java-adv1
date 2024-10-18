package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class CallableMainV2 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(1);
        log("call submit()");
        Future<Integer> future = es.submit(new MyCallable());
        log("future immediately return, future = " + future);

        log("future.get() [Blocking] start calling method -> main thread WAITING");
        Integer result = future.get(); // wake up main thread
        log("future.get() [Blocking] completed calling method -> main thread RUNNABLE");

        log("result value = " + result);
        log("future completed, future = " + future);
        es.close();
    }

    static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() {

            log("Start Callable");
            sleep(2000);
            int value = new Random().nextInt(10);
            log("created value = " + value);
            log("Completed Callable");
            return value;
        }
    }
}
