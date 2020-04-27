package ui;

import inventory.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ShoppingBagController {

    @FXML
    private TableView<Item> tvShoppingBag;

    @FXML
    private TableColumn<Item, String> colCategory;

    @FXML
    private TableColumn<Item, Integer> colArticleNumber;

    @FXML
    private TableColumn<Item, String> colBrand;

    @FXML
    private TableColumn<Item, String> colModel;

    @FXML
    private TableColumn<Item, Double> colPrice;

    @FXML
    private TableColumn<Item, Void> colQty;

    @FXML
    private TableColumn<Item, Double> colTotalPrice;

    @FXML
    private TableColumn<Item, Void> colDelete;

    @FXML
    void btnCheckout(ActionEvent event) {

    }

}

