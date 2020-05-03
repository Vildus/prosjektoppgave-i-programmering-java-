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
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;


public class InventoryController {

    public final String TITLE = "Inventory";

    private SceneChanger sceneChanger;

    private Inventory inventory;

    private InventoryRepository inventoryRepository;

    private Scene customerScene;

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
    private TableColumn<Item, Void> colEditDelete;

    @FXML
    private TextField txtFilter;

    @FXML
    private Label lblInfo;


    //TODO: Lage en path. global path.

    public InventoryController(SceneChanger sceneChanger) throws ClassNotFoundException, IOException {
        this.inventoryRepository = new InventoryRepository();
        try {
            this.inventory = this.inventoryRepository.read();
        } catch (FileNotFoundException e) {
            this.inventory = new Inventory(); //Hvis den ikke finner en fil med et vareregister så vil vi ha et tomt inventory
            this.testFillInventory();
        } //Hvis det ikke finnes noe path så vil vi lage et nytt
        this.sceneChanger = sceneChanger;
        this.customerScene = this.createCustomerScene();
    }

    private void testFillInventory() {
        //Hvis inventory er tomt så fylles inventory med dette test-inventory
        try {
            Component component1 = new Mouse("Dell", "M30 silent plus", "USB");
            Item item1 = new Item(component1, 120, 7234567);
            item1.setInStock(6);
            this.inventory.addItem(item1);

            Component component2 = new Keyboard("HP", "Elite gaming mouse", "USB");
            Item item2 = new Item(component2, 500, 7564739);
            item2.setInStock(10);
            this.inventory.addItem(item2);
        } catch (Exception e) {
            //Test. Slett!
        }
    }

    private void initializeComboBox() {
        this.cbCreateNewItem.setPromptText("Add new item to inventory");
        this.cbCreateNewItem.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Add new item to inventory");
                } else {
                    setText(item);
                }
            }
        });
        this.cbCreateNewItem.getItems().setAll(
                GraphicCard.CATEGORY,
                HardDisk.CATEGORY,
                Keyboard.CATEGORY,
                Motherboard.CATEGORY,
                Mouse.CATEGORY,
                PowerSupply.CATEGORY,
                Processor.CATEGORY,
                RAM.CATEGORY,
                Screen.CATEGORY);
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

                    private final Button btnEdit = new Button("Edit");

                    {
                        btnEdit.setOnAction((ActionEvent event) -> {
                            Item item = this.getTableView().getItems().get(getIndex());
                            Scene editItemScene = self.createEditItemScene(item);
                            self.sceneChanger.change(String.format("Edit item: %d", item.getArticleNumber()), editItemScene);
                        });
                    }

                    private final Button btnDelete = new Button("Delete");

                    {
                        btnDelete.setOnAction((ActionEvent event) -> {
                            Item item = this.getTableView().getItems().get(getIndex());
                            self.inventory.removeItem(item);
                            self.updateTableViewItems(self.inventory.getItems());
                            try {
                                self.inventoryRepository.save(self.inventory);
                            } catch (IOException e) {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Error Dialog");
                                alert.setHeaderText("Failed to save file");
                                alert.setContentText("Ooops, there was an error! The file could not be saved");

                                alert.showAndWait();
                            }
                        });
                    }

                    private final GridPane gridPane = new GridPane();

                    {
                        gridPane.setHgap(5);
                        gridPane.add(btnEdit, 0, 1);
                        gridPane.add(btnDelete, 1, 1);
                    }


                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            this.setGraphic(null);
                        } else {
                            this.setGraphic(gridPane);
                        }
                    }
                };
                return cell;
            }
        };

        colEditDelete.setCellFactory(cellFactory);

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
        String category = cbCreateNewItem.getSelectionModel().getSelectedItem();
        if (category != null) {
            Scene addItemScene = this.createAddItemScene(category);
            this.sceneChanger.change(String.format("Add item: %s", category), addItemScene);
        }
    }


    @FXML
    void signOut(ActionEvent event) {
        this.sceneChanger.change("Data store", this.customerScene);
    }

    private void updateTableViewItems(List<Item> items) {
        this.tvInventory.getItems().setAll(items);
    }

    private Scene createCustomerScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("customer.fxml"));
            CustomerController customerController = new CustomerController(this.inventory, this.sceneChanger);
            loader.setController(customerController);
            return new Scene(loader.load(), 1000, 800);
        } catch (IOException e) {
            //If this happens it means that fxml is corrupt or not found
            throw new RuntimeException();
        }
    }


    private Scene createEditItemScene(Item item) {
        try {
            EditItemController editItemController = new EditItemController(item, () -> {
                this.sceneChanger.change(TITLE, this.tvInventory.getScene());
                this.updateTableViewItems(this.inventory.getItems());
            });
            return new Scene(editItemController.getRoot(), 500, 300);
        } catch (Exception e) {
            // TODO: handle somehow
            return null;
        }

    }

    private Scene createAddItemScene(String category) {
        try {
            AddItemController addItemController = new AddItemController(category, this.inventory, this.inventoryRepository, () -> {
                this.sceneChanger.change(TITLE, this.tvInventory.getScene());
                this.updateTableViewItems(this.inventory.getItems());
                this.cbCreateNewItem.setValue(null);
            });
            return new Scene(addItemController.getRoot(), 900, 1100);
        } catch (Exception e) {
            // TODO: handle somehow
            return null;
        }
    }
}
