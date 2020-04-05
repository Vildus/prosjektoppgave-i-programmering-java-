package purchase;

import java.util.ArrayList;

public class OrderRegister {
    private ArrayList<Order> orders;

    public OrderRegister(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    //vil kun ha en instans av denne
    //order.csv
}
