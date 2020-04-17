package ui;

import components.RAM;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;


public class AddItemController {

    private String componentType;

    private GridPane gridPane;

    // Common inputs
    TextField txtBrand;
    TextField txtModel;

    // RAM input
    TextField txtRAMMemory;

    // Processor
    TextField txtProcessorCount;
    TextField txtProcessorClockRate;


    // @FXML
    //Button btnClose;

    //private SceneCloser closer;

    // This means we cannot create an Edit item controller without an item
    // as it does not make sense to have a "view" (javafx view) without an item to edit
    // this also means we cannot declare the controller in the fxml file / no "fx:controller=ui/EditItemController"
    public AddItemController(String componentType, SceneCloser closer) {
        this.componentType = componentType;
        this.initGridPane();
    }

    public GridPane getRoot() {
        return this.gridPane;
    }


    private void initGridPane() {
        this.gridPane = new GridPane();
        this.gridPane.setPrefWidth(600);
        this.gridPane.setPrefHeight(400);
        this.gridPane.setHgap(10);
        this.gridPane.setVgap(10);
        this.initCommonInput();

        switch (this.componentType) {
            case RAM.TYPE:
                this.initRAMInput();
                break;
        }
    }

    private void initCommonInput() {
        this.txtBrand = this.createLabelInputGridPane("Brand", 0);
        this.txtModel = this.createLabelInputGridPane("Model", 1);
    }

    private void initRAMInput() {
        this.txtModel = this.createLabelInputGridPane("Memory", 2);
    }

    private TextField createLabelInputGridPane(String label, int row) {
        this.gridPane.add(new Label(label), 0, row);
        TextField textField = new TextField();
        this.gridPane.add(textField, 1, row);
        return textField;
    }

}


