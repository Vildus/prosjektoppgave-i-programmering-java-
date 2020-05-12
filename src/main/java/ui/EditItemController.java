package ui;

import inventory.Inventory;
import inventory.Item;
import io.InventoryRepository;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;


public class EditItemController {

    public static final String TITLE = "Edit item: %d";

    Button btnClose;
    Button btnUpdate;

    private Item item;
    private SceneCloser closer;
    private GridPane gridPane;
    private VBox vb;

    TextField txtPrice;
    TextField txtInStock;


    // This means we cannot create an Edit item controller without an item
    // as it does not make sense to have a "view" (javafx view) without an item to edit
    // this also means we cannot declare the controller in the fxml file / no "fx:controller=ui/EditItemController"
    public EditItemController(Item item, SceneCloser closer) {
        this.item = item;
        this.closer = closer;
        this.initVBox();
        this.initGridPane();
        this.initCloseButton();
        this.initUpdateItemButton();
    }

    public Parent getRoot() {
        return this.vb;
    }


    private void initCloseButton() {
        this.btnClose = new Button("Close");
        //Syntaks for å hente referanse til en metode. Kan ikke sende metode inni metode(SetonAction er en metode). Hvis man
        //skulle sendt inn en metode måtte det vært en lamda
        this.btnClose.setOnAction(this::handleClose);
        int rowCount = this.getRowCount();
        this.gridPane.add(this.btnClose, 2, rowCount + 1);
    }

    private void handleClose(ActionEvent e) {
        this.closer.close();
    }

    private void initUpdateItemButton() {
        this.btnUpdate = new Button("Update item");
        this.btnUpdate.setOnAction(this::updateItem);
        int rowCount = this.getRowCount();
        this.gridPane.add(this.btnUpdate, 1, rowCount - 1);
    }


    //TODO: Implementer metoden under når man trykker på oppdater

    private void updateItem(ActionEvent actionEvent) {
        double price;
        try {
            price = Double.parseDouble(this.txtPrice.getText());
        } catch (NumberFormatException e) {
            Alert.showInfoDialog("Price must be a number", "Please make sure that the price is a number", e);
            return;
        }

        int insStock;
        try {
            insStock = Integer.parseInt(this.txtInStock.getText());
        } catch (NumberFormatException e) {
            Alert.showInfoDialog("Instock must be a number", "Please make sure that instock is an integer", e);
            return;
        }

        this.item.setPrice(price);
        this.item.setInStock(insStock);

        try {
            InventoryRepository inventoryRepository = new InventoryRepository();
            inventoryRepository.save(Inventory.getInstance());
            this.closer.close();
        } catch (IOException e) {
            Alert.showErrorDialog("Failed to save file", e);
        }
    }


    private void initVBox() {
        this.vb = new VBox();
        this.vb.setPadding(new Insets(80, 50, 50, 80));
        //vb.setSpacing(10); mellom elementene
    }

    private void initGridPane() {
        this.gridPane = new GridPane();
        this.gridPane.setPrefWidth(600);
        this.gridPane.setPrefHeight(400);
        this.gridPane.setHgap(10);
        this.gridPane.setVgap(10);
        this.initInputFields();
        this.vb.getChildren().add(this.gridPane);
    }

    private TextField createLabelInputGridPane(String label, int row) {
        this.gridPane.add(new Label(label), 0, row);
        TextField textField = new TextField();
        this.gridPane.add(textField, 1, row);
        return textField;
    }

    private void initInputFields() {
        this.txtPrice = this.createLabelInputGridPane("Price", 0);
        this.txtInStock = this.createLabelInputGridPane("In stock", 1);

        this.txtPrice.setText("" + item.getPrice());
        this.txtInStock.setText("" + item.getInStock());
    }


    private int getRowCount() {
        int numRows = this.gridPane.getRowConstraints().size();
        for (int i = 0; i < this.gridPane.getChildren().size(); i++) {
            Node child = this.gridPane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if (rowIndex != null) {
                    numRows = Math.max(numRows, rowIndex + 1);
                }
            }
        }
        return numRows;
    }

}



