package purchase;

import java.util.ArrayList;
import java.util.Date;

public class Order {
    private int orderNumber;
    //eventuelt kundenummer
    private Date date;

    private ArrayList<OrderLine> lines;

    public Order(int orderNumber, ArrayList<OrderLine> lines) {
        this.orderNumber = orderNumber;
        this.lines = lines;
        this.date = new Date();
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public Date getDate() {
        return this.date;
    }

    public ArrayList<OrderLine> getLines() {
        return this.lines;
    }

    //For hver gang vi lagrer en ny ordre så lagres en ny linje i orders
    //en ordre kan lagres til CSV

    //TO-CSV på ordre. En FIL = en ordre. En ordre består av flere ordrelinjer.
}
