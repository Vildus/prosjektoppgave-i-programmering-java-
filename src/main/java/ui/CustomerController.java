package ui;

import components.*;
import inventory.Inventory;
import inventory.Item;
import io.InventoryRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;


public class CustomerController {

    private Inventory inventory;

    private Item item;

    private InventoryRepository inventoryRepository;

    @FXML
    private HBox hbNav;

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
        String componentType = GraphicCard.TYPE;
        this.getComponentFromNavBar(componentType);
    }


    @FXML
    void navHarddisc(ActionEvent event) {
        String componentType = Harddisc.TYPE;
        this.getComponentFromNavBar(componentType);
    }

    @FXML
    void navKeyboard(ActionEvent event) {
        String componentType = Keyboard.TYPE;
        this.getComponentFromNavBar(componentType);
    }


    @FXML
    void navMotherboard(ActionEvent event) {
        String componentType = Motherboard.TYPE;
        this.getComponentFromNavBar(componentType);
    }


    @FXML
    void navMouse(ActionEvent event) {
        String componentType = Mouse.TYPE;
        this.getComponentFromNavBar(componentType);
    }

    @FXML
    void navPowerSupply(ActionEvent event) {
        String componentType = PowerSupply.TYPE;
        this.getComponentFromNavBar(componentType);
    }

    @FXML
    void navProcessor(ActionEvent event) {
        String componentType = Processor.TYPE;
        this.getComponentFromNavBar(componentType);

    }

    @FXML
    void navRAM(ActionEvent event) {
        String componentType = RAM.TYPE;
        this.getComponentFromNavBar(componentType);

    }

    @FXML
    void navScreen(ActionEvent event) {
        String componentType = Screen.TYPE;
        this.getComponentFromNavBar(componentType);
    }

    @FXML
    private void initialize() {
        this.testFillInventory();
        this.initializeTableView();
    }


    //Her lagde jeg en metode så man slipper å skrive for-løkken under alle knappene i navbaren
    private void getComponentFromNavBar(String componentType) {
        for (int i = 0; i < this.inventory.getItems().size(); i++) {
            if (inventory.getItems().get(i).getComponentCategory().equals(componentType)) {
                this.tvCustomerInventory.getItems().setAll(inventory.getItems().get(i), item);
            }
        }
    }


    private void testFillInventory() {
        inventory = new Inventory();
        //Hvis inventory er tomt så fylles inventory med dette test-inventory
        Component component1 = new Mouse("Dell", "M30 silent plus", "USB");
        Item item1 = new Item(component1, 120, 7234567);
        item1.setInStock(6);
        this.inventory.addItem(item1);

        Component component2 = new Keyboard("HP", "Elite gaming", "USB");
        Item item2 = new Item(component2, 500, 7564739);
        item2.setInStock(10);
        this.inventory.addItem(item2);

        Component component3 = new Harddisc("HP", "Elite", "HDD");
        Item item3 = new Item(component3, 600, 7564739);
        item3.setInStock(14);
        this.inventory.addItem(item3);

        Component component4 = new Motherboard("HP", "Elite gaming", "Small");
        Item item4 = new Item(component4, 1200, 7564739);
        item4.setInStock(60);
        this.inventory.addItem(item4);

        Component component5 = new PowerSupply("HP", "Elite gaming", 2, 123.5, 123.4);
        Item item5 = new Item(component5, 1200, 7564739);
        item5.setInStock(30);
        this.inventory.addItem(item5);

        Component component6 = new Processor("HP", "Elite gaming", 10, 20.5);
        Item item6 = new Item(component6, 1200, 7564739);
        item6.setInStock(10);
        this.inventory.addItem(item6);

        Component component7 = new RAM("HP", "Elite gaming", 100);
        Item item7 = new Item(component7, 1200, 7564739);
        item7.setInStock(2);
        this.inventory.addItem(item7);

        Component component8 = new Screen("HP", "Elite gaming", 50);
        Item item8 = new Item(component8, 1000, 7564739);
        item8.setInStock(56);
        this.inventory.addItem(item8);
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
