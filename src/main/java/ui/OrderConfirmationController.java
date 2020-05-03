package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import purchase.ShoppingBag;
import purchase.ShoppingBagItem;

import java.util.ArrayList;

public class OrderConfirmationController {

    private ShoppingBag shoppingBag;

    public OrderConfirmationController(ShoppingBag shoppingBag) {
        this.shoppingBag = shoppingBag;
    }

    @FXML
    private Label lblOrderInfo;

    @FXML
    void signOut(ActionEvent event) {

    }

    @FXML
    void returnToShopping(ActionEvent event) {

    }

    @FXML
    void viewOrderHistory(ActionEvent event) {

    }

    @FXML
    void initialize() {
        this.printSoppingBag();
    }

    private void printSoppingBag() {
        ArrayList<String> lines = new ArrayList<>();

        for (ShoppingBagItem shoppingBagItem : this.shoppingBag.getShoppingBagItems()) {
            String line = String.format("%s, %s, %s\t\t\tPrice: %.2f",
                    shoppingBagItem.getComponentCategory(),
                    shoppingBagItem.getComponentBrand(),
                    shoppingBagItem.getComponentModel(),
                    shoppingBagItem.getTotalPrice());
            lines.add(line);
        }
        String output = "";
        output = output + String.join("\n", lines) +
                String.format("\n\nTotal order price: %.2f", this.shoppingBag.getTotalPrice());
        this.lblOrderInfo.setText(output);
    }


}
