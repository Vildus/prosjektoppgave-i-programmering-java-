package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import purchase.ShoppingBag;
import purchase.ShoppingBagItem;

import java.util.List;

public class ShoppingBagController {

    private SceneChanger sceneChanger;

    private SceneCloser sceneCloser;

    private ShoppingBag shoppingBag;


    @FXML
    private TableView<ShoppingBagItem> tvShoppingBag;

    @FXML
    private TableColumn<ShoppingBagItem, String> colCategory;

    @FXML
    private TableColumn<ShoppingBagItem, Integer> colArticleNumber;

    @FXML
    private TableColumn<ShoppingBagItem, String> colBrand;

    @FXML
    private TableColumn<ShoppingBagItem, String> colModel;

    @FXML
    private TableColumn<ShoppingBagItem, Double> colPrice;

    @FXML
    private TableColumn<ShoppingBagItem, Void> colQty;

    @FXML
    private TableColumn<ShoppingBagItem, Double> colTotalPrice;

    @FXML
    private TableColumn<ShoppingBagItem, Void> colRemoveItem;


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

    @FXML
    public void initialize() {
        this.initializeTableView();
    }

    private void updateTableViewItems(List<ShoppingBagItem> shoppingBagItems) {
        this.tvShoppingBag.getItems().setAll(shoppingBagItems);
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


        Callback<TableColumn<ShoppingBagItem, Void>, TableCell<ShoppingBagItem, Void>> removeItemCellFactory = new Callback<TableColumn<ShoppingBagItem, Void>, TableCell<ShoppingBagItem, Void>>() {
            @Override
            public TableCell<ShoppingBagItem, Void> call(final TableColumn<ShoppingBagItem, Void> param) {
                final TableCell<ShoppingBagItem, Void> cell = new TableCell<ShoppingBagItem, Void>() {


                    private final Button btnRemove = new Button("Remove");

                    {
                        btnRemove.setOnAction((ActionEvent event) -> {
                            ShoppingBagItem shoppingBagItem = this.getTableView().getItems().get(getIndex());
                            self.shoppingBag.removeItem(shoppingBagItem);
                            //TODO : lage getitems i shoppingbag
                            self.updateTableViewItems(self.shoppingBag.getShoppingBagItems());

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

        this.updateTableViewItems(this.shoppingBag.getShoppingBagItems());

        this.lblTotalPrice.setText(String.format("%.2f NOK", this.shoppingBag.getTotalPrice()));
    }
}



