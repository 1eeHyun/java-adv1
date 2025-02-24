package thread.bounded;

import static thread.util.MyLogger.log;

public class ProducerTask implements Runnable {

    private BoundedQueue queue;
    private String request;

    public ProducerTask(BoundedQueue queue, String request) {
        this.queue = queue;
        this.request = request;
    }

    @Override
    public void run() {
        log("[Production attempt] " + request + " -> " + queue);
        queue.put(request);
        log("[Production completed] " + request + " -> " + queue);
    }
}
