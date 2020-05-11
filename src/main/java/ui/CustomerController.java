package ui;

import components.*;
import inventory.Inventory;
import inventory.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import java.util.List;


public class CustomerController {

    public static final String TITLE = "Data Store";

    private SceneChanger sceneChanger;

    private SceneCloser scenecloser;

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


    public CustomerController(SceneChanger sceneChanger, SceneCloser sceneCloser) {
        this.sceneChanger = sceneChanger;
        this.scenecloser = sceneCloser;
    }

    @FXML
    void goToShoppingBag(ActionEvent event) {
        Scene shoppingBagScene = this.createShoppingBagScene();
        this.sceneChanger.change(ShoppingBagController.TITLE, shoppingBagScene);
    }

    @FXML
    void signOut(ActionEvent event) {
        this.scenecloser.close();
    }

    @FXML
    void navGraphicCard(ActionEvent event) {
        this.filterTableViewByComponentCategory(GraphicCard.CATEGORY);
    }

    @FXML
    void navHardDisk(ActionEvent event) {
        this.filterTableViewByComponentCategory(HardDisk.CATEGORY);
    }

    @FXML
    void navKeyboard(ActionEvent event) {
        this.filterTableViewByComponentCategory(Keyboard.CATEGORY);
    }

    @FXML
    void navMotherboard(ActionEvent event) {
        this.filterTableViewByComponentCategory(Motherboard.CATEGORY);
    }

    @FXML
    void navMouse(ActionEvent event) {
        this.filterTableViewByComponentCategory(Mouse.CATEGORY);
    }

    @FXML
    void navPowerSupply(ActionEvent event) {
        this.filterTableViewByComponentCategory(PowerSupply.CATEGORY);
    }

    @FXML
    void navProcessor(ActionEvent event) {
        this.filterTableViewByComponentCategory(Processor.CATEGORY);
    }

    @FXML
    void navRAM(ActionEvent event) {
        this.filterTableViewByComponentCategory(RAM.CATEGORY);
    }

    @FXML
    void navScreen(ActionEvent event) {
        this.filterTableViewByComponentCategory(Screen.CATEGORY);
    }

    @FXML
    private void initialize() {
        this.initializeTableView();
    }


    private void filterTableViewByComponentCategory(String category) {
        List<Item> items = Inventory.getInstance().getItemsByComponentCategory(category);
        this.tvCustomerInventory.getItems().setAll(items);
    }


    private void initializeTableView() {
        // her binder vi opp getComponentCategory til cellen i tabellen
        this.colCategory.setCellValueFactory(new PropertyValueFactory<>("componentCategory"));
        this.colArticleNumber.setCellValueFactory(new PropertyValueFactory<>("articleNumber"));
        this.colBrand.setCellValueFactory(new PropertyValueFactory<>("componentBrand"));
        this.colModel.setCellValueFactory(new PropertyValueFactory<>("componentModel"));
        this.colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        CustomerController self = this;


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
                                ShoppingBag.getInstance().addItem(new ShoppingBagItem(item, parseQty));
                                Alert.showConfirmationDialog("Added to shoppingbag", String.format("%d pcs of articlenumber: %d is added to shopping bag", parseQty, item.getArticleNumber()));
                            } catch (ItemAvailableStockException e) {
                                Alert.showInfoDialog("Out of stock", "We are sorry, this item is out of stock", e);
                            } catch (NumberFormatException e) {
                                Alert.showInfoDialog("Quantity must be a number", "Please make sure that the quantity is an integer", e);
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


    private void updateTableViewItems(List<Item> items) {
        this.tvCustomerInventory.getItems().setAll(items);
    }


    private Scene createShoppingBagScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("shoppingBag.fxml"));
            ShoppingBagController shoppingBagController = new ShoppingBagController(() -> {
                this.sceneChanger.change(TITLE, this.tvCustomerInventory.getScene());
            }, this.sceneChanger);
            loader.setController(shoppingBagController);
            return new Scene(loader.load(), 1000, 600);
        } catch (Exception e) {
            Alert.showErrorDialog("Unexpected error", e);
            throw new RuntimeException();
        }
    }
}

