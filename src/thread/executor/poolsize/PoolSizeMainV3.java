package thread.executor.poolsize;

import thread.executor.RunnableTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static thread.executor.ExecutorUtils.printState;
import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class PoolSizeMainV3 {

    public static void main(String[] args) {

//        ExecutorService es = Executors.newCachedThreadPool();
        ExecutorService es = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                3, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        log("== create pool ==");
        printState(es);

        for (int i = 1; i <= 4; i++) {
            String taskName = "task" + i;
            es.execute(new RunnableTask(taskName));
            printState(es, taskName);
        }

        sleep(3000);
        log("== Works completed ==");
        printState(es);

        sleep(3000);
        log("== maximumPoolSize exceeded waiting time ==");
        printState(es);

        es.close();
        log("== shutdown completed ==");
    }
}
