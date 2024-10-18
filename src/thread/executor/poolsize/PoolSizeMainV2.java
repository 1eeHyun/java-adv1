package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static thread.executor.ExecutorUtils.printState;
import static thread.util.MyLogger.log;

public class PoolSizeMainV2 {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(2);
//        ExecutorService es = new ThreadPoolExecutor(2, 2,
//                0L, TimeUnit.MILLISECONDS,
//                new LinkedBlockingDeque<Runnable>());

        log("== create pool ==");
        printState(es);

        for (int i = 1; i <= 6; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        es.close();
        log("== completed ==");
    }
}
