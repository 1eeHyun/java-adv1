package thread.cas;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static thread.util.MyLogger.log;

public class CasMainV3 {

    private final static int THREAD_COUNT = 5;

    public static void main(String[] args) throws InterruptedException {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                incrementAndGet(atomicInteger);
            }
        };

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {

            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = atomicInteger.get();
        System.out.println(atomicInteger.getClass().getSimpleName() + " result value: " + result);
    }

    private static int incrementAndGet(AtomicInteger atomicInteger) {

        int getValue;
        boolean result;

        do {
            getValue = atomicInteger.get(); // read current value
            log("getValue: " + getValue);
            result = atomicInteger.compareAndSet(getValue, getValue + 1); // CAS operation
            log("result: " + result);
        } while (!result); // until succeeding incrementation

        return getValue + 1;
    }
}
