package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class BoundedQueueV2 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max; // maximum buffer number

    public BoundedQueueV2(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {

        // still does not work since thread is in critical section with lock and keep looping
        while (queue.size() == max) {
            log("[put] queue is full, producer wait");
            sleep(1000); // TIMED_WAITING
        }

        queue.offer(data);
    }

    @Override
    public synchronized String take() {

        // still does not work since thread is in critical section with lock and keep looping
        while (queue.isEmpty()) {
            log("[take] empty data in queue, consumer wait");
            sleep(1000);
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
