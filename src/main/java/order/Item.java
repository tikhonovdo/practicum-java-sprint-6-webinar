package order;

public class Item {
    String name;
    int count;

    public Item(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }
}
