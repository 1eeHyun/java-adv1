package thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class CasMainV1 {

    public static void main(String[] args) {

        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println("start value = " + atomicInteger.get());

        // 1. if expected value = 0, 2. change the value to 1: true
        boolean result1 = atomicInteger.compareAndSet(0, 1);
        System.out.println("result1 = " + result1 + ", value = " + atomicInteger.get());

        // 1. if expected value = 0, 2. change the value to 1: false
        boolean result2 = atomicInteger.compareAndSet(0, 1);
        System.out.println("result2 = " + result2 + ", value = " + atomicInteger.get());
    }
}
