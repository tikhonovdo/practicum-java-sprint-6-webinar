package order;

import java.util.HashMap;
import java.util.Map;

public class OrderManager {
    private long lastUsedId = 0;
    private Map<Long, Order> orders = new HashMap<>();

    private long getNextId() {
        return lastUsedId++;
    }

    /**
     * method that creates an order
     * @param order logistics order
     * @return true if order is created
     */
    public boolean createOrder(Order order) {
        if (order.getWeight() <= 0 || order.getWeight() > 20) {
            return false;
        }
        order.setId(getNextId());
        orders.put(order.getId(), order);
        return true;
    }

    public Map<Long, Order> getOrders() {
        return orders;
    }

    /**
     * method that cancels order
     * @param orderId id of existing order
     * @return true if cancellation succeeds
     */
    public boolean cancelOrder(Long orderId) {
        return orders.remove(orderId) != null;
    }
}
