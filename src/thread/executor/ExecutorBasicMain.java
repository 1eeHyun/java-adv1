package thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class ExecutorBasicMain {

    public static void main(String[] args) {

        ExecutorService es = new ThreadPoolExecutor(2, 2, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

        log("== Initial State ==");
        printState(es);
        es.execute(new RunnableTask("taskA"));
        es.execute(new RunnableTask("taskB"));
        es.execute(new RunnableTask("taskC"));
        es.execute(new RunnableTask("taskD"));
        log("== Working ==");
        printState(es);

        sleep(3000);
        log("== Completed ==");
        printState(es);

        es.close();
        log("== shutdown completed ==");
        printState(es);
    }

}
