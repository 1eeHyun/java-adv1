package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;

public class BoundedQueueV4 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max; // maximum buffer number

    public BoundedQueueV4(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {

        lock.lock();

        try {

            while (queue.size() == max) {
                log("[put] queue is full, producer wait");

                try {
                    condition.await();
                    log("[put] producer awake");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            queue.offer(data);
            log("[put] producer stored data, call signal()");
            condition.signal();

        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {

        lock.lock();
        try {

            while (queue.isEmpty()) {
                log("[take] empty data in queue, consumer wait");

                try {
                    condition.await();
                    log("[take] consumer awake");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = queue.poll();
            log("[take] consumer acquired data, call signal()");
            condition.signal();

            return data;

        } finally {
            lock.unlock();
        }
    }


    /*
        synchronized should be applied in real world
     */
    @Override
    public String toString() {
        return queue.toString();
    }
}
