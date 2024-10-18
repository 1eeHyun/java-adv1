package thread.bounded;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static thread.util.MyLogger.log;

public class BoundedQueueV6_2 implements BoundedQueue {

    private BlockingQueue<String> queue;

    public BoundedQueueV6_2(int max) {
        this.queue = new ArrayBlockingQueue<>(max);
    }

    @Override
    public void put(String data) {
        boolean result = queue.offer(data); // do not wait
        log("Result for saving data = " + result);
    }

    @Override
    public String take() {
        return queue.poll(); // do not wait
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
