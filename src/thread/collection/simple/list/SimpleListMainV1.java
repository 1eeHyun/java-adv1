package thread.collection.simple.list;

import static thread.util.MyLogger.log;

public class SimpleListMainV1 {

    public static void main(String[] args) throws InterruptedException {

//        test(new BasicList());
//        test(new SyncList());
        BasicList basicList = new BasicList();
        SyncProxyList proxyList = new SyncProxyList(basicList);
        test(proxyList);
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        // Store A in list
        Runnable addA = new Runnable() {

            @Override
            public void run() {
                list.add("A");
                log("Thread-1: list.add(A)");
            }
        };

        // Store B in list
        Runnable addB = new Runnable() {

            @Override
            public void run() {
                list.add("B");
                log("Thread-2: list.add(B)");
            }
        };

        Thread thread1 = new Thread(addA, "Thread-1");
        Thread thread2 = new Thread(addB, "Thread-2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        log(list);
    }
}
