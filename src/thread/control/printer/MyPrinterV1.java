package thread.control.printer;

import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class MyPrinterV1 {

    public static void main(String[] args) throws InterruptedException {

        Printer printer = new Printer();
        Thread printerThread = new Thread(printer, "printer");
        printerThread.start();

        Scanner userInput = new Scanner(System.in);

        while(true) {

            log("Input document to print. exit (q): ");

            String input = userInput.nextLine();
            if (input.equals("q")) {
                printer.work = false;
                break;
            }

            printer.addJob(input);
        }

    }

    static class Printer implements Runnable {

        volatile boolean work = true;
        Queue<String> jobQueue = new ConcurrentLinkedQueue<>();

        @Override
        public void run() {

            while(work) {
                if (jobQueue.isEmpty()) {
                    continue;
                }

                String job = jobQueue.poll();
                log("Output start: " + job + " , waiting document: " + jobQueue);
                sleep(3000);
                log("Output end: " + job);
            }
            log("Printer end");
        }

        public void addJob(String input){
            jobQueue.offer(input);
        }
    }
}
