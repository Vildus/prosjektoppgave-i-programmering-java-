package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import purchase.Order;

import java.util.Date;


public class OrderHistoryController {


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

    }

}


