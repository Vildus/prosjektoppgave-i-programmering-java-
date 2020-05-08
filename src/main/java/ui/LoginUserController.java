package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import java.io.IOException;

import javafx.scene.layout.VBox;
import purchase.Customer;
import purchase.IllegalCustomerIDException;


public class LoginUserController {

    public final static String TITLE = "Log in user";

    private SceneChanger sceneChanger;

    public LoginUserController(SceneChanger sceneChanger) {
        this.sceneChanger = sceneChanger;
    }

    @FXML
    private VBox vb;

    @FXML
    private TextField txtSuperUserName;

    @FXML
    private TextField txtSuperUserPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void signInSuperUser(ActionEvent event) {
        String userName = "super";
        String password = "user";
        if (this.txtSuperUserName.getText().matches(userName) && this.txtSuperUserPassword.getText().matches(password)) {
            Scene inventoryScene = this.createInventoryScene();
            this.sceneChanger.change(InventoryController.TITLE, inventoryScene);
        } else {
            Alert.showInfoDialog("User name or password was wrong", "Hint: try \"super\" as user name and \"user\" as password");
        }
    }

    @FXML
    void signInUser(ActionEvent event) {
        String userID = this.txtUserName.getText();
        try {
            Customer.setCurrentCustomerID(userID);
            Scene customerScene = this.createCustomerScene();
            this.sceneChanger.change(CustomerController.TITLE, customerScene);
        } catch (IllegalCustomerIDException e) {
            Alert.showInfoDialog("Illegal user ID", "You must enter an ID that contains between 5-15 characters or digits");
        }
    }


    private Scene createInventoryScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inventory.fxml"));
            InventoryController inventoryController = new InventoryController(this.sceneChanger, () -> {
                sceneChanger.change(TITLE, this.txtSuperUserName.getScene());
            });
            loader.setController(inventoryController);
            return new Scene(loader.load(), 1000, 800);
        } catch (IOException e) {
            Alert.showErrorDialog("Failed to read inventory from disk", e);
            throw new RuntimeException();
        }
    }

    private Scene createCustomerScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer.fxml"));
            CustomerController customerController = new CustomerController(this.sceneChanger, () -> {
                this.sceneChanger.change(TITLE, this.txtSuperUserName.getScene());
            });
            loader.setController(customerController);
            return new Scene(loader.load(), 1000, 800);
        } catch (IOException e) {
            Alert.showErrorDialog("Failed to read inventory from disk", e);
            throw new RuntimeException();
        }
    }

}


