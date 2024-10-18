package thread.executor.future;

import java.util.Random;
import java.util.concurrent.*;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class CallableMainV1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<Integer> future = es.submit(new MyCallable());
        Integer result = future.get();
        log("result value = " + result);
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
