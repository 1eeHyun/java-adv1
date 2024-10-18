package thread.executor.future;

import java.util.concurrent.*;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class FutureCancelMain {

//    private static boolean mayInterruptIfRunning = true; // change
private static boolean mayInterruptIfRunning = false;

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1);
        Future<String> future = es.submit(new MyTask());
        log("Future.state: " + future.state());

        // try to cancel after 3 seconds
        sleep(3000);

        // call cancel()
        log("future.cancel(" + mayInterruptIfRunning + ") calling");
        boolean cancelResult1= future.cancel(mayInterruptIfRunning);
        log("Future.state: " + future.state());
        log("cancel(" + mayInterruptIfRunning + ") result: " + cancelResult1);

        // result
        try {
            log("Future result: " + future.get());
        } catch (CancellationException e) { // runtime exception
            log("Future is already cancelled");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        es.close();
    }

    static class MyTask implements Callable<String> {

        @Override
        public String call() throws Exception {

            try {

                for (int i = 0; i < 10; i++) {
                    log("Working: " + i);
                    Thread.sleep(1000); // 1 second
                }

            } catch (InterruptedException e) {
                log("Interrupt occurred");
                return "Interrupted";
            }

            return "Completed";
        }
    }
}
