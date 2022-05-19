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
        order.setId(getNextId());
        orders.put(order.getId(), order);
        return true;
    }

    public Map<Long, Order> getOrders() {
        return orders;
    }
}
