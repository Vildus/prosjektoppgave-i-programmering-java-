package ui;

import inventory.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

// https://stackoverflow.com/questions/30814258/javafx-pass-parameters-while-instantiating-controller-class

public class EditItemController {
    @FXML
    Button btnClose;

    private Item item;
    private SceneCloser closer;

    // This means we cannot create an Edit item controller without an item
    // as it does not make sense to have a "view" (javafx view) without an item to edit
    // this also means we cannot declare the controller in the fxml file / no "fx:controller=ui/EditItemController"
    public EditItemController(Item item, SceneCloser closer) {
        this.item = item;
        this.closer = closer;
    }

    @FXML
    public void initialize() {
        btnClose.setOnAction(this::handleClose);
    }

    private void handleClose(ActionEvent e) {
        this.closer.close();
    }
}
