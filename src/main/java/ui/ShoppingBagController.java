package ui;

import io.OrderRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import purchase.OrderRegister;
import purchase.ShoppingBag;
import purchase.ShoppingBagItem;

import java.io.IOException;
import java.util.List;

public class ShoppingBagController {

    private OrderRepository orderRepository;

    private OrderRegister orderRegister;

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
        this.orderRegister = new OrderRegister();
        this.orderRegister.addOrder(this.shoppingBag.createOrder());
        try {
            this.orderRepository.saveOrderRegister(this.orderRegister);
            this.sceneChanger.change("Order Confirmation", this.createOrderConfirmationScene());
        } catch (IOException e) {
            Alert.showErrorDialog("Failed to save order register", e);
        }
    }


    public ShoppingBagController(ShoppingBag shoppingBag, SceneCloser sceneCloser, SceneChanger sceneChanger) throws IOException {
        this.shoppingBag = shoppingBag;
        this.sceneChanger = sceneChanger;
        this.sceneCloser = sceneCloser;
        this.orderRepository = new OrderRepository();
        this.orderRegister = new OrderRegister();
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


    private Scene createOrderConfirmationScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderConfirmation.fxml"));
            OrderConfirmationController orderConfirmationController = new OrderConfirmationController(this.shoppingBag);
            loader.setController(orderConfirmationController);
            return new Scene(loader.load(), 1000, 800);
        } catch (IOException e) {
            Alert.showErrorDialog("Unexpected error", e);
            //If this happens it means that fxml is corrupt or not found
            throw new RuntimeException();
        }
    }
}



