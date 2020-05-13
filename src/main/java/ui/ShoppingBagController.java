package ui;

import inventory.Inventory;
import io.InventoryRepository;
import io.OrderRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import purchase.Order;
import purchase.OrderRegister;
import purchase.ShoppingBag;
import purchase.ShoppingBagItem;

import java.io.IOException;


public class ShoppingBagController {

    public final static String TITLE = "Shopping bag";

    private Scene scene;

    private OrderRepository orderRepository;

    private InventoryRepository inventoryRepository;

    private SceneChanger sceneChanger;

    private SceneCloser sceneCloser;

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


    public ShoppingBagController(SceneCloser sceneCloser, SceneChanger sceneChanger) throws IOException {
        this.sceneChanger = sceneChanger;
        this.sceneCloser = sceneCloser;
        this.orderRepository = new OrderRepository();
        this.inventoryRepository = new InventoryRepository();
    }

    @FXML
    public void initialize() {
        this.initializeTableView();
        this.updateShoppingBagView();
    }

    @FXML
    void close(ActionEvent event) {
        this.sceneCloser.close();
    }

    @FXML
    void checkOut(ActionEvent event) {
        if (this.tvShoppingBag.getItems().size() > 0) {
            OrderRegister orderRegister = OrderRegister.getInstance();
            ShoppingBag shoppingBag = ShoppingBag.getInstance();
            Order order = shoppingBag.createOrder();
            orderRegister.addOrder(order);
            try {
                // orderRegister and inventory must be saved at the same time
                // for consistency. Also if inventory save fails we should rollback
                // the orderRegister save, but for simplicity we assume it all goes well
                this.orderRepository.save(orderRegister);
                this.inventoryRepository.save(Inventory.getInstance());

                Scene orderConfirmationScene = this.createOrderConfirmationScene(order);
                this.sceneChanger.change(OrderConfirmationController.TITLE, orderConfirmationScene);
                shoppingBag.clear();
                this.updateShoppingBagView();
            } catch (IOException e) {
                Alert.showErrorDialog("Failed to save order register", e);
            }
        } else {
            Alert.showInfoDialog("Your shopping bag is empty", "Add items to your shopping bag to proceed");
        }

    }

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
                            ShoppingBag shoppingBag = ShoppingBag.getInstance();
                            shoppingBag.removeItem(shoppingBagItem);
                            self.updateShoppingBagView();

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

        this.updateShoppingBagView();
    }

    private void updateShoppingBagView() {
        ShoppingBag shoppingBag = ShoppingBag.getInstance();
        this.tvShoppingBag.getItems().setAll(shoppingBag.getShoppingBagItems());
        this.lblTotalPrice.setText(String.format("%.2f NOK", shoppingBag.getTotalPrice()));
    }

    private Scene createOrderConfirmationScene(Order order) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("orderConfirmation.fxml"));
            OrderConfirmationController orderConfirmationController = new OrderConfirmationController(order, () -> {
                this.sceneChanger.change(TITLE, this.tvShoppingBag.getScene());
            });
            loader.setController(orderConfirmationController);
            return new Scene(loader.load(), 1000, 600);
        } catch (IOException e) {
            Alert.showErrorDialog("Unexpected error", e);
            //If this happens it means that fxml is corrupt or not found
            throw new RuntimeException();
        }
    }
}



