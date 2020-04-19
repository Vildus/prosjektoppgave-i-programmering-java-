package ui;

import components.Component;
import components.Keyboard;
import components.Mouse;
import inventory.Inventory;
import inventory.Item;
import io.InventoryRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerController {

    private Inventory inventory;

    private InventoryRepository inventoryRepository;

    @FXML
    private TableView<Item> tvCustomerInventory;

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
    private TableColumn<Item, Integer> colQty;

    @FXML
    void btnAddToBasket(ActionEvent event) {

    }

    @FXML
    void btnAdmin(ActionEvent event) {

    }

    @FXML
    void navGraphicCard(ActionEvent event) {


    }

    @FXML
    void navHarddisc(ActionEvent event) {

    }

    @FXML
    void navKeyboard(ActionEvent event) {

    }

    @FXML
    void navMotherboard(ActionEvent event) {

    }

    @FXML
    void navMouse(ActionEvent event) {
        String componentType = navMouse();

    }

    @FXML
    void navPowerSupply(ActionEvent event) {

    }

    @FXML
    void navProcessor(ActionEvent event) {

    }

    @FXML
    void navRAM(ActionEvent event) {

    }

    @FXML
    void navScreen(ActionEvent event) {

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

    @FXML
    private void initialize() {
        initializeTableView();
    }


    private void initializeTableView() {
        // her binder vi opp getComponentCategory til cellen i tabellen
        this.colCategory.setCellValueFactory(new PropertyValueFactory<>("componentCategory"));
        this.colArticleNumber.setCellValueFactory(new PropertyValueFactory<>("articleNumber"));
        this.colBrand.setCellValueFactory(new PropertyValueFactory<>("componentBrand"));
        this.colModel.setCellValueFactory(new PropertyValueFactory<>("componentModel"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

}
