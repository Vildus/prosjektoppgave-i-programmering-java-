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
        //TODO - Skal skifte til sign inn siden
    }

    @FXML
    void returnToShopping(ActionEvent event) {
        //TODO
    }


    @FXML
    void initialize() {
        this.printSoppingBag();
    }

    private void printSoppingBag() {

        //Order date
        //Order ID
        ArrayList<String> lines = new ArrayList<>();

        for (ShoppingBagItem shoppingBagItem : this.shoppingBag.getShoppingBagItems()) {
            String line = String.format("%s, %s, %s\nPrice: %.2f\n",
                    shoppingBagItem.getComponentCategory(),
                    shoppingBagItem.getComponentBrand(),
                    shoppingBagItem.getComponentModel(),
                    shoppingBagItem.getTotalPrice());
            lines.add(line);
        }
        String output = "";
        output = output + String.join("\n", lines) +
                String.format("\n\n\nTotal order price: %.2f NOK", this.shoppingBag.getTotalPrice());
        this.lblOrderInfo.setText(output);
    }


}
