package purchase;

import java.util.ArrayList;
import java.util.List;

public class OrderRegister {
    private List<Order> orders;

    public OrderRegister() {
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void addOrder(Order order) {
        int orderNumber = this.orders.size() + 1;
        order.setOrderNumber(orderNumber);
        this.orders.add(order);
    }

    @Override
    public String toString() {
        ArrayList<String> strOrders = new ArrayList<>();
        for (Order order : this.orders) {
            strOrders.add(order.toString());
        }
        return String.format("%s",
                String.join("\n", strOrders));
    }
}
