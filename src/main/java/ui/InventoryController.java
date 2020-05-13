package ui;

import io.InventoryRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

import components.*;
import inventory.Inventory;
import inventory.Item;

public class InventoryController {

    public static final String TITLE = "Inventory";

    private SceneChanger sceneChanger;

    private SceneCloser sceneCloser;

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
    private TableColumn<Item, String> colDesc;

    @FXML
    private TableColumn<Item, Double> colPrice;

    @FXML
    private TableColumn<Item, Integer> colInStock;

    @FXML
    private TableColumn<Item, Void> colEditDelete;

    @FXML
    private TextField txtFilter;

    public InventoryController(SceneChanger sceneChanger, SceneCloser sceneCloser) {
        this.sceneChanger = sceneChanger;
        this.sceneCloser = sceneCloser;
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
        this.colCategory.setCellValueFactory(new PropertyValueFactory<>("componentCategory"));
        this.colArticleNumber.setCellValueFactory(new PropertyValueFactory<>("articleNumber"));
        this.colBrand.setCellValueFactory(new PropertyValueFactory<>("componentBrand"));
        this.colModel.setCellValueFactory(new PropertyValueFactory<>("componentModel"));
        this.colDesc.setCellValueFactory(new PropertyValueFactory<>("shortDescription"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        this.colInStock.setCellValueFactory(new PropertyValueFactory<>("inStock"));

        InventoryController self = this;

        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private final Button btnEdit = new Button("Edit");

                    {
                        btnEdit.setOnAction((ActionEvent event) -> {
                            Item item = this.getTableView().getItems().get(getIndex());
                            Scene editItemScene = self.createEditItemScene(item);
                            self.sceneChanger.change(String.format(EditItemController.TITLE, item.getArticleNumber()), editItemScene);
                        });
                    }

                    private final Button btnDelete = new Button("Delete");

                    {
                        btnDelete.setOnAction((ActionEvent event) -> {
                            Inventory inventory = Inventory.getInstance();
                            Item item = this.getTableView().getItems().get(getIndex());
                            inventory.removeItem(item);
                            self.updateTableViewItems(inventory.getItems());
                            try {
                                InventoryRepository inventoryRepository = new InventoryRepository();
                                inventoryRepository.save(inventory);
                            } catch (IOException e) {
                                Alert.showErrorDialog("Failed to save file", e);
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

        this.updateTableViewItems(Inventory.getInstance().getItems());
    }

    @FXML
    private void txtFilterKeyTyped(KeyEvent event) {
        Inventory inventory = Inventory.getInstance();
        String search = this.txtFilter.getText();
        if (search.isEmpty()) {
            this.updateTableViewItems(inventory.getItems());
        } else {
            this.updateTableViewItems(inventory.filter(search));
        }
    }

    @FXML
    private void cbCreateNewItemAction(ActionEvent e) {
        String category = cbCreateNewItem.getSelectionModel().getSelectedItem();
        if (category != null) {
            Scene addItemScene = this.createAddItemScene(category);
            this.sceneChanger.change(String.format(AddItemController.TITLE, category), addItemScene);
        }
    }

    @FXML
    void signOut(ActionEvent event) {
        this.sceneCloser.close();
    }

    private void updateTableViewItems(List<Item> items) {
        this.tvInventory.getItems().setAll(items);
    }

    private Scene createEditItemScene(Item item) {
        try {
            EditItemController editItemController = new EditItemController(item, () -> {
                this.sceneChanger.change(TITLE, this.tvInventory.getScene());
                this.updateTableViewItems(Inventory.getInstance().getItems());
            });
            return new Scene(editItemController.getRoot(), Common.SCENE_WIDTH, Common.SCENE_HEIGHT);
        } catch (Exception e) {
            Alert.showErrorDialog("Unexpected error", e);
            throw new RuntimeException();
        }
    }

    private Scene createAddItemScene(String category) {
        try {
            AddItemController addItemController = new AddItemController(category, () -> {
                this.sceneChanger.change(TITLE, this.tvInventory.getScene());
                this.updateTableViewItems(Inventory.getInstance().getItems());
                this.cbCreateNewItem.setValue(null);
            });
            return new Scene(addItemController.getRoot(), Common.SCENE_WIDTH, Common.SCENE_HEIGHT);
        } catch (Exception e) {
            Alert.showErrorDialog("Unexpected error", e);
            throw new RuntimeException();
        }
    }
}
