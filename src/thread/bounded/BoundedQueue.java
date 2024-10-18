package thread.bounded;

/*
    Buffer role
 */
public interface BoundedQueue {

    void put(String data); // store data in buffer
    String take();         // take data from buffer
}
