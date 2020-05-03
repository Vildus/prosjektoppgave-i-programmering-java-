package ui;

import components.*;
import inventory.Inventory;
import inventory.Item;
import inventory.ItemAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import purchase.ItemAvailableStockException;
import purchase.ShoppingBag;
import purchase.ShoppingBagItem;

import java.io.IOException;
import java.util.List;


public class CustomerController {

    public final String TITLE = "Data Store";

    private Inventory inventory;

    private GridPane gridPane;

    private ShoppingBag shoppingBag;

    private SceneChanger sceneChanger;

    @FXML
    private Label lblNotifyMessage;

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
    private TableColumn<Item, Void> colQty;


    public CustomerController(Inventory inventory, SceneChanger sceneChanger) {
        this.sceneChanger = sceneChanger;
        this.inventory = inventory;
    }

    public Parent getRoot() {
        return this.gridPane;
    }


    @FXML
    void goToShoppingBag(ActionEvent event) {
        Scene scene = this.createShoppingBagScene();
        this.sceneChanger.change("Shopping bag", scene);
    }

    @FXML
    void btnAdmin(ActionEvent event) {
        Scene scene = this.createLoginSuperUserScene();
        this.sceneChanger.change("Sign in", scene);
    }


    @FXML
    void navGraphicCard(ActionEvent event) {
        this.filterTabelViewByComponentCategory(GraphicCard.CATEGORY);
    }

    @FXML
    void navHardDisk(ActionEvent event) {
        this.filterTabelViewByComponentCategory(HardDisk.CATEGORY);
    }

    @FXML
    void navKeyboard(ActionEvent event) {
        this.filterTabelViewByComponentCategory(Keyboard.CATEGORY);
    }

    @FXML
    void navMotherboard(ActionEvent event) {
        this.filterTabelViewByComponentCategory(Motherboard.CATEGORY);
    }

    @FXML
    void navMouse(ActionEvent event) {
        this.filterTabelViewByComponentCategory(Mouse.CATEGORY);
    }

    @FXML
    void navPowerSupply(ActionEvent event) {
        this.filterTabelViewByComponentCategory(PowerSupply.CATEGORY);
    }

    @FXML
    void navProcessor(ActionEvent event) {
        this.filterTabelViewByComponentCategory(Processor.CATEGORY);
    }

    @FXML
    void navRAM(ActionEvent event) {
        this.filterTabelViewByComponentCategory(RAM.CATEGORY);
    }

    @FXML
    void navScreen(ActionEvent event) {
        this.filterTabelViewByComponentCategory(Screen.CATEGORY);
    }

    @FXML
    private void initialize() throws ItemAlreadyExistsException {
        this.testFillInventory(); // denne må byttes ut med en ordentlig instans av inventory.
        this.initializeTableView();
        this.shoppingBag = new ShoppingBag(this.inventory);
    }


    private void filterTabelViewByComponentCategory(String category) {
        List<Item> items = this.inventory.getItemsByComponentCategory(category);
        this.tvCustomerInventory.getItems().setAll(items);
    }

    private void testFillInventory() throws ItemAlreadyExistsException {
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

        Component component3 = new HardDisk("HP", "Elite", "HDD");
        Item item3 = new Item(component3, 600, 7564738);
        item3.setInStock(14);
        this.inventory.addItem(item3);

        Component component4 = new Motherboard("HP", "Elite gaming", "Small");
        Item item4 = new Item(component4, 1200, 7564745);
        item4.setInStock(60);
        this.inventory.addItem(item4);

        Component component5 = new PowerSupply("HP", "Elite gaming", 2, 123.5, 123.4);
        Item item5 = new Item(component5, 1200, 7564730);
        item5.setInStock(30);
        this.inventory.addItem(item5);

        Component component6 = new Processor("HP", "Elite gaming", 10, 20.5);
        Item item6 = new Item(component6, 1200, 7564734);
        item6.setInStock(10);
        this.inventory.addItem(item6);

        Component component7 = new RAM("HP", "Elite gaming", 100);
        Item item7 = new Item(component7, 1200, 7564767);
        item7.setInStock(2);
        this.inventory.addItem(item7);

        Component component8 = new Screen("HP", "Elite gaming", 50);
        Item item8 = new Item(component8, 1000, 7564778);
        item8.setInStock(56);
        this.inventory.addItem(item8);

        Component component9 = new GraphicCard("HP", "Elite gaming", 250);
        Item item9 = new Item(component9, 2000, 1325535);
        item9.setInStock(12);
        this.inventory.addItem(item9);

        Component component10 = new Mouse("Dell", "Mus", "USB");
        Item item10 = new Item(component1, 120, 72345690);
        item1.setInStock(6);
        this.inventory.addItem(item10);

        Component component11 = new GraphicCard("HP", "gaming", 250);
        Item item11 = new Item(component11, 20, 1325785);
        item11.setInStock(12);
        this.inventory.addItem(item11);
    }

    private void initializeTableView() {
        // her binder vi opp getComponentCategory til cellen i tabellen
        this.colCategory.setCellValueFactory(new PropertyValueFactory<>("componentCategory"));
        this.colArticleNumber.setCellValueFactory(new PropertyValueFactory<>("articleNumber"));
        this.colBrand.setCellValueFactory(new PropertyValueFactory<>("componentBrand"));
        this.colModel.setCellValueFactory(new PropertyValueFactory<>("componentModel"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        CustomerController self = this;

        // Lager knapp inne i tableview-celler hvor det er data. Kode tatt fra nettet - skjønner den ikke selv men det funker :p
        //google it!
        Callback<TableColumn<Item, Void>, TableCell<Item, Void>> cellFactory = new Callback<TableColumn<Item, Void>, TableCell<Item, Void>>() {
            @Override
            public TableCell<Item, Void> call(final TableColumn<Item, Void> param) {
                final TableCell<Item, Void> cell = new TableCell<Item, Void>() {

                    private final TextField txtQty = new TextField("1");


                    private final Button btnBuy = new Button("Buy");

                    {
                        btnBuy.setOnAction((ActionEvent event) -> {
                            Item item = this.getTableView().getItems().get(getIndex());
                            try {
                                int parseQty = Integer.parseInt(txtQty.getText());
                                shoppingBag.addItem(new ShoppingBagItem(item, parseQty));
                                lblNotifyMessage.setText(String.format("%d item with articlenumber: %d added to shopping bag", parseQty, item.getArticleNumber()));
                            } catch (ItemAvailableStockException e) {
                                lblNotifyMessage.setText("Out of Stock!");
                            } catch (NumberFormatException e) {
                                lblNotifyMessage.setText("Quantity must be a number");
                            }

                        });
                    }

                    private final GridPane gridPane = new GridPane();

                    {
                        ColumnConstraints col50 = new ColumnConstraints();
                        col50.setPercentWidth(50);

                        gridPane.setHgap(5);
                        gridPane.getColumnConstraints().addAll(col50, col50);
                        gridPane.add(txtQty, 0, 1);
                        gridPane.add(btnBuy, 1, 1);
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
        colQty.setCellFactory(cellFactory);
    }


    private Scene createLoginSuperUserScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("loginSuperUser.fxml"));
            LoginSuperUserController loginSuperUserController = new LoginSuperUserController(() -> {
                this.sceneChanger.change("Inventory", this.createInventoryScene());
            }, () -> {
                this.sceneChanger.change("Data Store", this.tvCustomerInventory.getScene());
            });
            loader.setController(loginSuperUserController);
            return new Scene(loader.load(), 500, 600);
        } catch (IOException e) {
            //If this happens it means that fxml is corrupt or not found
            throw new RuntimeException();
        }
    }

    private void updateTableViewItems(List<Item> items) {
        this.tvCustomerInventory.getItems().setAll(items);
    }


    private Scene createInventoryScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inventory.fxml"));
            InventoryController inventoryController = new InventoryController(this.sceneChanger);
            loader.setController(inventoryController);
            return new Scene(loader.load(), 1000, 800);
        } catch (IOException e) {
            //If this happens it means that fxml is corrupt or not found
            throw new RuntimeException();
        } catch (ClassNotFoundException e) {
            //This means that something is wrong with serialized jobj file
            throw new RuntimeException();
        }
    }


    private Scene createShoppingBagScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shoppingBag.fxml"));
            ShoppingBagController shoppingBagController = new ShoppingBagController(this.shoppingBag, () -> {
                this.sceneChanger.change(TITLE, this.tvCustomerInventory.getScene());
            }, this.sceneChanger);
            loader.setController(shoppingBagController);
            return new Scene(loader.load(), 1000, 800);
        } catch (Exception e) {
            //TODO: Handle somehow
            return null;
        }
    }
}


            /*
        private Scene createEditItemScene(Item item){
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
*/

