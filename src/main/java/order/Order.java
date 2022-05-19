package order;

import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private final Date createdAt;
    private final List<Item> items;
    private final long price;
    private final double weight;

    public Order(List<Item> items, long price, double weight) {
        this.id = null;
        this.createdAt = new Date();
        this.items = items;
        this.price = price;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public List<Item> getItems() {
        return items;
    }

    public long getPrice() {
        return price;
    }
}
