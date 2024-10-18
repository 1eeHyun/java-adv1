package thread.cas.increment;

public class IncrementPerformanceMain {

    public static final long COUNT = 100_000_000;

    public static void main(String[] args) throws InterruptedException {
        test(new BasicInteger());    // use CPU cache (cannot use with multi-threads)
        test(new VolatileInteger()); // use Main memory (cannot use with multi-threads)
        test(new SyncInteger());     // use critical section (can use with multi-threads)
        test(new MyAtomicInteger()); // faster than synchronized and Lock(ReentrantLock)
    }

    private static void test(IncrementInteger incrementInteger) {

        long startMs = System.currentTimeMillis();

        for (long i = 0; i < COUNT; i++) {
            incrementInteger.increment();
        }
        long endMs = System.currentTimeMillis();

        System.out.println(incrementInteger.getClass().getSimpleName() + ": ms=" + (endMs - startMs));

    }
}
