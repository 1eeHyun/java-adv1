package thread.control;

import static thread.util.ThreadUtils.sleep;

public class CheckedExceptionMain {

    public static void main(String[] args) throws Exception {
        throw new Exception();
    }

    static class CheckedRunnable implements Runnable {

        @Override
        public void run() /* throws Exception */  {
            // throw new Exception();
            sleep(1000);
        }
    }
}
