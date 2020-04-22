package ui;

import components.*;
import inventory.Inventory;
import inventory.Item;
import io.InventoryRepository;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class InventoryController {

    private Stage stage;

    private Inventory inventory;

    private InventoryRepository inventoryRepository;


    @FXML
    private ComboBox<String> cbCreateNewItem;

    @FXML
    private TableView<Item> tvInventory;

    @FXML
    private TableColumn<Item, String> colCategory;

    @FXML
    private TableColumn<Item, Integer> colArticleNumber;

    @FXML
    private TableColumn<Item, String> colBrand;

    @FXML
    private TableColumn<Item, String> colModel;

    @FXML
    private TableColumn<Item, Double> colPrice;

    @FXML
    private TableColumn<Item, Integer> colInStock;

    @FXML
    private TableColumn<Item, Void> colEdit;

    @FXML
    private TableColumn<Item, Void> colDelete;

    @FXML
    private TextField txtFilter;

    @FXML
    void signOutOfInventory(ActionEvent event) {

    }


    //TODO: Lage en path. global path.
// Legge til denne i koden til sluttbruker main.
    public InventoryController(Stage stage) throws ClassNotFoundException, IOException {
        this.inventoryRepository = new InventoryRepository();
        try {
            this.inventory = this.inventoryRepository.read();
        } catch (FileNotFoundException e) {
            this.inventory = new Inventory(); //Hvis den ikke finner en fil med et vareregister så vil vi ha et tomt inventory
            this.testFillInventory();
        } //Hvis det ikke finnes noe path så vil vi lage et nytt

        this.stage = stage;
    }

    private void testFillInventory() {
        //Hvis inventory er tomt så fylles inventory med dette test-inventory
        Component component1 = new Mouse("Dell", "M30 silent plus", "USB");
        Item item1 = new Item(component1, 120, 7234567);
        item1.setInStock(6);
        this.inventory.addItem(item1);

        Component component2 = new Keyboard("HP", "Elite gaming mouse", "USB");
        Item item2 = new Item(component2, 500, 7564739);
        item2.setInStock(10);
        this.inventory.addItem(item2);
    }

    private void initializeComboBox() {
        this.cbCreateNewItem.getItems().setAll(GraphicCard.TYPE,
                Harddisc.TYPE,
                Keyboard.TYPE,
                Motherboard.TYPE,
                Mouse.TYPE,
                PowerSupply.TYPE,
                Processor.TYPE,
                RAM.TYPE,
                Screen.TYPE);
    }


    @FXML
    public void initialize() {
        this.initializeTableView();
        this.initializeComboBox();
    }

    private void initializeTableView() {
        // her binder vi opp getComponentCategory til cellen i tabellen
        this.colCategory.setCellValueFactory(new PropertyValueFactory<>("componentCategory"));
        this.colArticleNumber.setCellValueFactory(new PropertyValueFactory<>("articleNumber"));
        this.colBrand.setCellValueFactory(new PropertyValueFactory<>("componentBrand"));
        this.colModel.setCellValueFactory(new PropertyValueFactory<>("componentModel"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.colInStock.setCellValueFactory(new PropertyValueFactory<>("inStock"));

        InventoryController self = this;

        // Lager knapp inne i tableview-celler hvor det er data. Kode tatt fra nettet - skjønner den ikke selv men det funker :p
        //google it!
        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Item item = this.getTableView().getItems().get(getIndex());
                            Scene editItemScene = self.createEditItemScene(item);
                            self.stage.setTitle(String.format("Edit item: %d", item.getArticleNumber()));
                            self.stage.setScene(editItemScene);
                        });
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            this.setGraphic(null);
                        } else {
                            this.setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colEdit.setCellFactory(cellFactory);

        this.updateTableViewItems(this.inventory.getItems());
    }

    @FXML
    private void txtFilterKeyTyped(KeyEvent event) {
        String search = this.txtFilter.getText();
        if (search.isEmpty()) {
            this.updateTableViewItems(this.inventory.getItems());
        } else {
            this.updateTableViewItems(inventory.filter(search));
        }
    }

    @FXML
    private void cbCreateNewItemAction(ActionEvent e) {
        String componentType = cbCreateNewItem.getSelectionModel().getSelectedItem().toString();
        Scene addItemScene = this.createAddItemScene(componentType);
        this.stage.setTitle(String.format("Add item: %s", componentType));
        this.stage.setScene(addItemScene);
    }

    private void updateTableViewItems(List<Item> items) {
        this.tvInventory.getItems().setAll(items);
    }

    private Scene createEditItemScene(Item item) {
        try {
            EditItemController editItemController = new EditItemController(item, () -> {
                System.out.println("Close window");
                this.stage.setTitle("Main scene");
                this.stage.setScene(this.tvInventory.getScene());
                this.updateTableViewItems(this.inventory.getItems());
            });
            return new Scene(editItemController.getRoot(), 900, 1100);
        } catch (Exception e) {
            // TODO: handle somehow
            return null;
        }

    }

    private Scene createAddItemScene(String componentType) {
        try {
            AddItemController addItemController = new AddItemController(componentType, this.inventory, this.inventoryRepository, () -> {
                System.out.println("Close window");
                this.stage.setTitle("Main scene");
                this.stage.setScene(this.tvInventory.getScene());
                this.updateTableViewItems(this.inventory.getItems());
            });
            return new Scene(addItemController.getRoot(), 900, 1100);
        } catch (Exception e) {
            // TODO: handle somehow
            return null;
        }
    }
}
