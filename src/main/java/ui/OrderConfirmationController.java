package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import purchase.Customer;
import purchase.Order;
import purchase.ShoppingBag;
import purchase.ShoppingBagItem;

import java.util.ArrayList;
import java.util.Date;

public class OrderConfirmationController {

    public static final String TITLE = "Order confirmation";

    private Order order;

    private SceneCloser sceneCloser;

    public OrderConfirmationController(Order order, SceneCloser sceneCloser) {
        this.order = order;
        this.sceneCloser = sceneCloser;
    }

    @FXML
    private TextArea txtOrderInfo;


    @FXML
    void returnToShopping(ActionEvent event) {
        this.sceneCloser.close();
    }

    @FXML
    void initialize() {
        this.printShoppingBag();
    }

    private void printShoppingBag() {
        ArrayList<String> lines = new ArrayList<>();

        for (ShoppingBagItem shoppingBagItem : ShoppingBag.getInstance().getShoppingBagItems()) {
            String line = String.format("%s, %s, %s\nPrice: %.2f\n",
                    shoppingBagItem.getComponentCategory(),
                    shoppingBagItem.getComponentBrand(),
                    shoppingBagItem.getComponentModel(),
                    shoppingBagItem.getTotalPrice());
            lines.add(line);
        }

        String customerID = Customer.getCurrentCustomerID();
        int orderNumber = this.order.getOrderNumber();
        Date date = this.order.getDate();
        String output = "";
        output = output + String.join("\n", lines) +
                String.format("\n\n\nTotal order price: %.2f NOK", ShoppingBag.getInstance().getTotalPrice());
        this.txtOrderInfo.setText(String.format("Customer ID: %s\n\nOrder number: %d\nDate: %s\n\nOrder info:\n\n%s", customerID, orderNumber, date, output));
    }
}
