package thread.executor;

import java.util.concurrent.Callable;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class CallableTask implements Callable<Integer> {

    private String name;
    private int sleepMs = 1000;

    public CallableTask(String name) {
        this.name = name;
    }

    public CallableTask(String name, int sleepMs) {
        this.name = name;
        this.sleepMs = sleepMs;
    }

    @Override
    public Integer call() throws Exception {

        log("execute " + name);
        sleep(sleepMs);
        log("completed" + name);
        return sleepMs;
    }
}
