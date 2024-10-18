package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static thread.util.MyLogger.log;

public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max; // maximum buffer number

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {

        while (queue.size() == max) {
            log("[put] queue is full, producer wait");
            try {
                wait(); // RUNNABLE -> WAITING until notify(), return lock
                log("[put] producer awake");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        queue.offer(data);
        log("[put] producer stored data, call notify()");
        notify(); // a waiting thread, WAIT -> BLOCKED
    }

    @Override
    public synchronized String take() {

        while (queue.isEmpty()) {
            log("[take] empty data in queue, consumer wait");

            try {
                wait(); // until notify()
                log("[take] consumer awake");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        String data = queue.poll();
        log("[take] consumer acquired data, call notify()");
        notify(); // a waiting thread, WAIT -> BLOCKED

        return data;
    }


    /*
        synchronized should be applied in real world
     */
    @Override
    public String toString() {
        return queue.toString();
    }
}
