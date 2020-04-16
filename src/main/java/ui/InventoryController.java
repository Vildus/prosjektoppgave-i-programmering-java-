package ui;

import components.Component;
import components.Mouse;
import inventory.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class InventoryController {

    Stage primaryStage;

    @FXML
    Button btnEditItem;

    public InventoryController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    public void initialize() {
        btnEditItem.setOnAction(this::handleEditItemAction);
    }

    private void handleEditItemAction(ActionEvent e) {
        Component component1 = new Mouse("Dell", "1", "USB");
        Item item1 = new Item(component1, 120.0, 7234567);
        item1.setInStock(10);

        Scene editItemScene = this.createEditItemScene(item1);
        this.primaryStage.setTitle(String.format("Edit item: %d", item1.getArticleNumber()));
        this.primaryStage.setScene(editItemScene);
    }

    private Scene createEditItemScene(Item item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("editItem.fxml"));
            loader.setController(new EditItemController(item, () -> {
                System.out.println("Close window");
                this.primaryStage.setTitle("Main scene");
                this.primaryStage.setScene(btnEditItem.getScene());
            }));
            return new Scene(loader.load(), 900, 1100);
        } catch (Exception e) {
            // TODO: handle somehow
            return null;
        }
    }
}
