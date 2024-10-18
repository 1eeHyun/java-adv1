package thread.executor.test;

import static thread.util.MyLogger.log;
import static thread.util.ThreadUtils.sleep;

public class OldOrderService {

    public void order(String orderNo) {

        InventoryWork inventoryWork = new InventoryWork(orderNo);
        ShippingWork shippingWork = new ShippingWork(orderNo);
        AccountingWork accountingWork = new AccountingWork(orderNo);

        boolean inventoryResult = inventoryWork.call();
        Boolean shippingResult = shippingWork.call();
        Boolean accountingResult = accountingWork.call();

        if (inventoryResult && shippingResult && accountingResult) {
            log("All order processing has been successfully completed");
        } else {
            log("Some processing has been failed");
        }
    }

    static class InventoryWork {

        private final String orderNo;

        public InventoryWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public boolean call() {

            log("Inventory update: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class ShippingWork {

        private final String orderNo;

        public ShippingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {

            log("Delivery system notification: " + orderNo);
            sleep(1000);
            return true;
        }
    }

    static class AccountingWork {

        private final String orderNo;

        public AccountingWork(String orderNo) {
            this.orderNo = orderNo;
        }

        public Boolean call() {
            log("Accounting system update: " + orderNo);
            sleep(1000);
            return true;
        }
    }
}
