package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static thread.util.MyLogger.log;

public class ExecutorShutdownMain {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("taskD"));
        es.execute(new RunnableTask("longTask", 100_000)); // wait for 100 seconds
        printState(es);

        log("== shutdown start ==");
        shutdownAndAwaitTermination(es);
        log("== shutdown completed ==");
        printState(es);
    }

    private static void shutdownAndAwaitTermination(ExecutorService es) {

        // non-blocking, does not accept new work. processing or waiting works will be completed.
        es.shutdown();
        try {

            log("Attempt to shut down the service normally");
            if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                // too long time
                log("Failed to shut down -> attempt to force shut down");
                es.shutdownNow();
                // wait for cancel
                if (!es.awaitTermination(10, TimeUnit.SECONDS)) {
                    log("Service is not terminated.");
                }
            }
        } catch (InterruptedException e) {
            // awaitTermination(): current thread may be interrupted
            es.shutdownNow();
        }
    }
}
