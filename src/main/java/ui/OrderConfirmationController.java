package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import purchase.Customer;
import purchase.ShoppingBag;
import purchase.ShoppingBagItem;

import java.util.ArrayList;

public class OrderConfirmationController {


    public static final String TITLE = "Order confirmation";


    private SceneCloser sceneCloser;

    public OrderConfirmationController(SceneCloser sceneCloser) {
        this.sceneCloser = sceneCloser;
    }

    @FXML
    private Label lblOrderInfo;


    @FXML
    void returnToShopping(ActionEvent event) {
        this.sceneCloser.close();
    }

    @FXML
    void initialize() {
        this.printShoppingBag();
    }

    private void printShoppingBag() {

//TODO: Order date, Customer ID
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
        String output = "";
        output = output + String.join("\n", lines) +
                String.format("\n\n\nTotal order price: %.2f NOK", ShoppingBag.getInstance().getTotalPrice());
        this.lblOrderInfo.setText(String.format("Customer ID: %s\n\nOrder info:\n\n%s", customerID, output));
    }


}
