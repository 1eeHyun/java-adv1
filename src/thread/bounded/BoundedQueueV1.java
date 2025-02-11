package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;

public class BoundedQueueV1 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max; // maximum buffer number

    public BoundedQueueV1(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {

        if (queue.size() == max) {
            log("[put] queue is full, throw away: " + data);
            return;
        }

        queue.offer(data);
    }

    @Override
    public synchronized String take() {

        if (queue.isEmpty()) {
            return null;
        }

        return queue.poll();
    }


    /*
        synchronized should be applied in real world
     */
    @Override
    public String toString() {
        return queue.toString();
    }
}
