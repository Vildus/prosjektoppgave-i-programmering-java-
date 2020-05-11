package purchase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderRegister {

    private static final OrderRegister INSTANCE = new OrderRegister();

    private List<Order> orders;

    private OrderRegister() {
        this.orders = new ArrayList<>();
    }

    public static OrderRegister getInstance() {
        return INSTANCE;
    }

    public List<Order> getOrders(String customerID) {
        return this.orders.stream().filter((order -> {
            return order.getCustomerID.equals(customerID);
        })).collect(Collectors.toList());
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
