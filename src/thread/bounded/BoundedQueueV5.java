package thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static thread.util.MyLogger.log;

public class BoundedQueueV5 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max; // maximum buffer number

    public BoundedQueueV5(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {

        lock.lock();

        try {

            while (queue.size() == max) {
                log("[put] queue is full, producer wait");

                try {
                    producerCondition.await();
                    log("[put] producer awake");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            queue.offer(data);
            log("[put] producer stored data, call signal()");
            consumerCondition.signal();

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
                    consumerCondition.await();
                    log("[take] consumer awake");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            String data = queue.poll();
            log("[take] consumer acquired data, call signal()");
            producerCondition.signal();

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
