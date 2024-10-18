package thread.util;

public abstract class ThreadUtils {

    public static void sleep(long millis) {

        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            MyLogger.log("Interruption occurred, " + e.getMessage());
            throw new RuntimeException();
        }
    }
}
