package ui;

import components.*;
import inventory.Inventory;
import inventory.Item;
import inventory.ItemAlreadyExistsException;
import io.InventoryRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import purchase.ItemAvailableStockException;
import purchase.ShoppingBag;

import java.io.IOException;


public class CustomerController {

    private Inventory inventory;

    private Item item;

    private InventoryRepository inventoryRepository;

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

    public CustomerController(SceneChanger sceneChanger) {
        this.sceneChanger = sceneChanger;
    }


    @FXML
    void btnAddToBasket(ActionEvent event) {
        //Dette funker ikke. Man kan ikke legge på IO-Exception i signaturen på et ActionEvent, det matcher ikke fxml'en
        //Når denne metoden kalles skal vi legge til items i basket og kalle på metoden "createBasketScene" - som ikke har blitt laget enda
        //Parent basketParent = FXMLLoader.load(getClass().getResource("basket.fxml"));
        //Scene basketScene = new Scene(basketParent);

        //Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //window.setScene(basketScene);
        //window.show();
    }

    @FXML
    void btnAdmin(ActionEvent event)  {
        Scene scene = this.createLoginSuperUserScene();
        this.sceneChanger.change("Sign in", scene);
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
    private void initialize() throws ItemAlreadyExistsException {
        this.testFillInventory(); // denne må byttes ut med en ordentlig instans av inventory.
        this.initializeTableView();
        this.shoppingBag = new ShoppingBag(this.inventory);
    }


    //Her lagde jeg en metode så man slipper å skrive for-løkken under alle knappene i navbaren
    private void getComponentFromNavBar(String componentType) {
        for (int i = 0; i < this.inventory.getItems().size(); i++) {
            if (inventory.getItems().get(i).getComponentCategory().equals(componentType)) {
                this.tvCustomerInventory.getItems().setAll(inventory.getItems().get(i), item);
            }
        }
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

        Component component3 = new Harddisc("HP", "Elite", "HDD");
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
                            System.out.println("hei");
                            int parseQty = Integer.parseInt(txtQty.getText());
                            Item item = this.getTableView().getItems().get(getIndex());
                            try {
                                shoppingBag.setItem(item, parseQty);
                                lblNotifyMessage.setText(parseQty + " item with articlenumber: " + item.getArticleNumber() + " added to basket");
                                System.out.println("Item er lagt til"); //denne linjen kan fjernes når handlekurven er laget
                            } catch (ItemAvailableStockException e) {
                                lblNotifyMessage.setText("Out of Stock!");
                                //TODO: Handle somehow
                            }

                        });
                    }

                    private final GridPane gridPane = new GridPane();

                    {
                        gridPane.setHgap(5);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginSuperUser.fxml"));
        LoginSuperUserController loginSuperUserController = new LoginSuperUserController(() -> {
            this.sceneChanger.change("Inventory", this.createInventoryScene());
        }, () -> {
            this.sceneChanger.change("Data Store", this.tvCustomerInventory.getScene());
        });
        loader.setController(loginSuperUserController);
        try {
            return new Scene(loader.load(), 900, 1100);
        } catch (IOException e) {
            //If this happens it means that something is seriously wrong
            throw new RuntimeException();
        }
    }


    private Scene createInventoryScene() {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("inventory.fxml"));
            return new Scene(parent, 900, 1100);
        } catch (IOException e) {
            //If this happens it means that something is seriously wrong
            throw new RuntimeException();
        }
    }

    /*private Scene createShoppingBagScene(Item item, int qty ) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shoppingBag.fxml"));
            ShoppingBagController shoppingBagController = new ShoppingbagController(item, qty () -> {
                System.out.println("Close window");
                this.stage.setTitle("Main scene");
                this.stage.setScene(this.tvCustomerInventory.getScene());
            });
            loader.setController(shoppingBagController);
            return new Scene(loader.load(), 900, 1100);
        } catch (Exception e) {
            // TODO: handle somehow
            return null;
        }
    }*/


}
