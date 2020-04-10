package io;

import purchase.Order;
import purchase.OrderLine;
import purchase.OrderRegister;
import utils.DateStringConverter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRepository {

    // path to store orders.csv and all [order_id].csv files
    private Path directory;
    private DateStringConverter dateStringConverter;

    public OrderRepository(Path directory) {
        this.directory = directory;
        this.dateStringConverter = new DateStringConverter("dd/MM/yyyy HH:mm:ss");
    }

    // Lager en path fordi det er den samme pathen vi skal lese og skrive fra. Da slipper vi
    // å skrive den to ganger og minsker risiko for stavefeil i strengen "orders.csv"
    private Path getOrderRegisterPath() {
        return Path.of(this.directory.toString(), "orders.csv");
    }

    public void saveOrderRegister(OrderRegister orderRegister) throws IOException {
        Path path = this.getOrderRegisterPath();
        Files.write(path, orderRegisterToCSV(orderRegister).getBytes("utf-8"));

        // Save all orders with order lines
        for (Order order : orderRegister.getOrders()) {
            this.saveOrderLines(order.getOrderNumber(), order.getLines());
        }
    }

    private String orderRegisterToCSV(OrderRegister orderRegister) {
        String output = "";
        for (Order order : orderRegister.getOrders()) {
            String dateStr = this.dateStringConverter.toString(order.getDate());
            output = output + String.format("%d;%s\n", order.getOrderNumber(), dateStr);
        }
        return output;
    }

    public OrderRegister readOrderRegister() throws IOException, ParseException {
        Path path = this.getOrderRegisterPath();
        List<String> lines = Files.readAllLines(path);

        OrderRegister orderRegister = new OrderRegister();

        for (String line : lines) {
            String[] parts = line.split(";");
            String orderNumberStr = parts[0];
            int orderNumber = Integer.parseInt(orderNumberStr);
            String dateStr = parts[1];
            Date date = this.dateStringConverter.fromString(dateStr);
            // read all order lines by "orderNumber"
            List<OrderLine> orderLines = this.readOrderLines(orderNumber);
            Order order = new Order(orderNumber, date, orderLines);

            orderRegister.addOrder(order);
        }

        return orderRegister;
    }

    // getOrderLinesPath gets the path where orderlines for orderNumber is saved on disk
    // Lager en path fordi det er den samme pathen vi skal lese og skrive fra. Da slipper vi
    // å skrive den to ganger og minsker risiko for stavefeil i strengen "order_%d.csv"
    private Path getOrderLinesPath(int orderNumber) {
        return Path.of(this.directory.toString(), String.format("order_%d.csv", orderNumber));
    }

    private void saveOrderLines(int orderNumber, List<OrderLine> lines) throws IOException {
        Path path = this.getOrderLinesPath(orderNumber);
        Files.write(path, this.orderLinesToCSV(lines).getBytes("utf-8"));
    }

    private String orderLinesToCSV(List<OrderLine> lines) {
        String output = "";
        for (OrderLine orderLine : lines) {
            output = output + String.format("%d;%d;%.2f\n", orderLine.getArticleNumber(), orderLine.getAmount(), orderLine.getPricePerUnit());
        }
        return output;
    }

    private List<OrderLine> readOrderLines(int orderNumber) throws IOException {
        Path path = this.getOrderLinesPath(orderNumber);
        List<String> lines = Files.readAllLines(path);
        ArrayList<OrderLine> orderLines = new ArrayList<>();

        for (String line : lines) {
            String[] parts = line.split(";");
            String articleNumberStr = parts[0];
            int articleNumber = Integer.parseInt(articleNumberStr);
            String amountStr = parts[1];
            int amount = Integer.parseInt(amountStr);
            String pricePerUnitStr = parts[2];
            double pricePerUnit = Double.parseDouble(pricePerUnitStr);
            OrderLine orderLine = new OrderLine(articleNumber, amount, pricePerUnit);
            orderLines.add(orderLine);
        }
        return orderLines;
    }
}
