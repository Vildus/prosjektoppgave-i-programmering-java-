package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import purchase.Customer;
import purchase.Order;
import purchase.OrderRegister;

import java.util.Date;


public class OrderHistoryController {

    public static final String TITLE = "Order history";

    private SceneCloser sceneCloser;

    public OrderHistoryController(SceneCloser sceneCloser) {
        this.sceneCloser = sceneCloser;
    }


    @FXML
    private Button btnClose;

    @FXML
    private TableView<Order> tvOrderHistory;

    @FXML
    private TableColumn<Order, Integer> colOrderNumber;

    @FXML
    private TableColumn<Order, Date> colDate;

    @FXML
    private TableColumn<Order, Double> colTotalPrice;

    @FXML
    private Label lblCustomerID;

    @FXML
    void handleClose(ActionEvent event) {
        this.sceneCloser.close();
    }

    @FXML
    void initialize() {
        this.lblCustomerID.setText(Customer.getCurrentCustomerID());
        this.initializeTableView();
    }


    private void initializeTableView() {
        // her binder vi opp getComponentCategory til cellen i tabellen
        this.colOrderNumber.setCellValueFactory(new PropertyValueFactory<>("orderNumber"));
        this.colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.colTotalPrice.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        this.tvOrderHistory.getItems().setAll(OrderRegister.getInstance().getOrders(Customer.getCurrentCustomerID()));
    }
}

