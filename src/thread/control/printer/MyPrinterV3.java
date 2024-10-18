package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static thread.util.MyLogger.log;

public class MyPrinterV3 {

    public static void main(String[] args) throws InterruptedException {

        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);

        while(true) {

            log("Input document to print. exit (q): ");

            String input = userInput.nextLine();
            if (input.equals("q")) {
                printerThread.interrupt();
                break;
            }

            printer.addJob(input);
        }

    }

    static class Printer implements Runnable {
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {

            while(!Thread.interrupted()) {
                if (jobQueue.isEmpty()) {
                    Thread.yield(); // added
                    continue;
                }

                try {

                    String job = jobQueue.poll();
                    log("Output start: " + job + " , waiting document: " + jobQueue);
                    Thread.sleep(3000); // needed time to print
                    log("Output end: " + job);

                } catch(InterruptedException e) {

                    log("interrupt!");
                    break;
                }
            }
            log("Printer end");
        }

        public void addJob(String input){
            jobQueue.offer(input);
        }
    }
}
