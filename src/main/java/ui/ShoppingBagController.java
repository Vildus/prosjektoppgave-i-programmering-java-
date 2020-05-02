package ui;

import inventory.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import purchase.ItemAvailableStockException;
import purchase.ShoppingBag;

import java.io.IOException;
import java.util.List;

public class ShoppingBagController {

    private SceneChanger sceneChanger;

    private SceneCloser sceneCloser;

    private ShoppingBag shoppingBag;


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
    private TableColumn<Item, Void> colRemoveItem;


    @FXML
    private Label lblTotalPrice;

    @FXML
    void close(ActionEvent event) {
        this.sceneCloser.close();
    }

    @FXML
    void checkOut(ActionEvent event) {
        //this.sceneChanger.change();
    }


    public ShoppingBagController(ShoppingBag shoppingBag, SceneCloser sceneCloser, SceneChanger sceneChanger) {
        this.shoppingBag = shoppingBag;
        this.sceneChanger = sceneChanger;
        this.sceneCloser = sceneCloser;
    }

    private void updateTableViewItems(List<Item> items) {
        this.tvShoppingBag.getItems().setAll(items);
    }


    // LAGE DENNE
    private void initializeTableView() {
        // her binder vi opp getComponentCategory til cellen i tabellen
        this.colCategory.setCellValueFactory(new PropertyValueFactory<>("componentCategory"));
        this.colArticleNumber.setCellValueFactory(new PropertyValueFactory<>("articleNumber"));
        this.colBrand.setCellValueFactory(new PropertyValueFactory<>("componentBrand"));
        this.colModel.setCellValueFactory(new PropertyValueFactory<>("componentModel"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        this.colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        ShoppingBagController self = this;


        //ShoppingBagController self = this;

        // Lager knapp inne i tableview-celler hvor det er data. Kode tatt fra nettet - skjønner den ikke selv men det funker :p
        //google it!
        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> removeItemCellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {


                    private final Button btnRemove = new Button("Remove");

                    {
                        btnRemove.setOnAction((ActionEvent event) -> {
                            Item item = this.getTableView().getItems().get(getIndex());
                            self.shoppingBag.removeItem(item);
                            //TODO : lage getitems i shoppingbag
                            self.updateTableViewItems(self.shoppingBag.getItems());

                        });
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            this.setGraphic(null);
                        } else {
                            this.setGraphic(btnRemove);
                        }
                    }
                };
                return cell;
            }
        };

        colRemoveItem.setCellFactory(removeItemCellFactory);

        this.updateTableViewItems(this.shoppingBag.getItems());
    }


}



