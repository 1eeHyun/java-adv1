package thread.executor.test;

import java.util.concurrent.*;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class NewOrderService {

    private final ExecutorService es = Executors.newFixedThreadPool(10);

    public void order(String orderNo) throws ExecutionException, InterruptedException{

        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        try {

            Future<Boolean> inventoryFuture = es.submit(inventoryWork);
            Future<Boolean> shippingFuture = es.submit(shippingWork);
            Future<Boolean> accountingFuture = es.submit(accountingWork);

            Boolean inventoryResult = inventoryFuture.get();
            Boolean shippingResult = shippingFuture.get();
            Boolean accountingResult = accountingFuture.get();

            if (inventoryResult && shippingResult && accountingResult) {
                log("All order processing has been successfully completed");
            } else {
                log("Some processing has been failed");
            }

        } finally {
            es.close();
        }
    }

    static class InventoryWork implements Callable<Boolean> {

        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {

            log("Inventory update: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class ShippingWork implements Callable<Boolean> {

        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {

            log("Delivery system notification: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class AccountingWork implements Callable<Boolean> {

        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        @Override
        public Boolean call() {

            log("Accounting system update: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}
