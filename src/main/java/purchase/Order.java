package purchase;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderNumber;
    private int customerNumber;
    private Date date;
    private List<OrderLine> lines;

    public Order(List<OrderLine> lines, int customerNumber) {
        this.lines = lines;
        this.date = new Date();
        this.customerNumber =customerNumber;
    }

    public int getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDate() {
        return this.date;
    }

    public List<OrderLine> getLines() {
        return this.lines;
    }

    @Override
    public String toString() {
        ArrayList<String> strLines = new ArrayList<>();
        for (OrderLine line : this.lines) {
            strLines.add(line.toString());
        }
        return String.format("Order number: %d, date: %s\n%s",
                this.orderNumber,
                this.getDate().toString(),
                String.join("\n", strLines));
    }

    //For hver gang vi lagrer en ny ordre så lagres en ny linje i orders
    //en ordre kan lagres til CSV

    //TO-CSV på ordre. En FIL = en ordre. En ordre består av flere ordrelinjer.
}
