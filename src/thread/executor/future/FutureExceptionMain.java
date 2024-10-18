package thread.executor.future;

import java.util.concurrent.*;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class FutureExceptionMain {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(1);
        log("Send work");
        Future<Integer> future = es.submit(new ExCallable());
        sleep(1000);

        try {

            log("Try to call future.get(), future.state(): " + future.state());
            Integer result = future.get();
            log("result value = " + result);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {

            log("e = " + e);
            Throwable cause = e.getCause(); // original exception
            log("cause = " + cause);
        }

        es.close();
    }

    static class ExCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {

            log("Execute Callable, exception occurred");
            throw new IllegalStateException("ex!");
        }
    }
}
