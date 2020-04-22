package ui;

import components.*;
import inventory.InvalidPriceArgumentException;
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


public class AddItemController {

    private String componentType;

    private Inventory inventory;

    private InventoryRepository inventoryRepository;

    private VBox vb;

    private GridPane gridPane;

    private SceneCloser closer;


    // Common inputs
    TextField txtBrand;
    TextField txtModel;
    TextField txtArticleNumber;
    TextField txtPrice;
    TextField txtQty;

    //GraphicCard input
    TextField txtGraphicCardMemory;

    //Harddisc input
    TextField txtHarddiscType;

    //Keyboard input
    TextField txtKeyBoardInterfaceType;

    //Motherboard input
    TextField txtMotherboardSizeCategory;

    //Mouse input
    TextField txtMouseInterfaceType;

    //PowerSupply input
    TextField txtPowerSupplyEffect;
    TextField txtPowerSupplyInputVoltage;
    TextField txtPowerSupplyOutputVoltage;

    // Processor
    TextField txtProcessorCount;
    TextField txtProcessorClockRate;

    // RAM input
    TextField txtRAMMemory;

    //Screen input
    TextField txtScreenSize;


    Button btnClose;

    Button btnAddItem;


    // This means we cannot create an item controller without a componentType
    // as it does not make sense to have a "view" (javafx view) without a componentType to edit
    // this also means we cannot declare the controller in the fxml file / no "fx:controller=ui/EditItemController"
    public AddItemController(String componentType, Inventory inventory, InventoryRepository inventoryRepository, SceneCloser closer) {
        this.componentType = componentType;
        this.inventoryRepository = inventoryRepository;
        this.inventory = inventory;
        this.initVBox();
        this.initGridPane();
        this.initCloseButton();
        this.initAddItemButton();
    }

    public Parent getRoot() {
        return this.vb;
    }

    private void initAddItemButton() {
        this.btnAddItem = new Button("Add to inventory");
        this.btnAddItem.setOnAction(this::addItemToInventory);
        int rowCount = this.getRowCount();
        this.gridPane.add(this.btnAddItem, 2, rowCount);
    }

    //TODO: Hva gjør man med qty-input? Legger til på item.InStock?

    private void addItemToInventory(ActionEvent actionEvent) {
        //ComponentInput
        Component component;
        String brand = this.txtBrand.getText();
        String model = this.txtModel.getText();

        //Item input
        Item item;
        double price = Double.parseDouble(this.txtPrice.getText());
        int articleNumber = Integer.parseInt(this.txtArticleNumber.getText());

        switch (componentType) {
            case GraphicCard.TYPE:
                int graphicCardMemory = Integer.parseInt(this.txtGraphicCardMemory.getText());
                component = new GraphicCard(brand, model, graphicCardMemory);
                break;

            case Harddisc.TYPE:
                String harddiscType = this.txtHarddiscType.getText();
                component = new Harddisc(brand, model, harddiscType);
                break;

            case Keyboard.TYPE:
                String keyboardInterfaceType = txtKeyBoardInterfaceType.getText();
                component = new Keyboard(brand, model, keyboardInterfaceType);
                break;

            case Motherboard.TYPE:
                String motherboardSizeCategory = txtMotherboardSizeCategory.getText();
                component = new Motherboard(brand, model, motherboardSizeCategory);
                break;

            case Mouse.TYPE:
                String mouseInterfaceType = txtMouseInterfaceType.getText();
                component = new Mouse(brand, model, mouseInterfaceType);
                break;

            case PowerSupply.TYPE:
                int effect = Integer.parseInt(txtPowerSupplyEffect.getText());
                double inputVoltage = Double.parseDouble(txtPowerSupplyInputVoltage.getText());
                double outputVoltage = Double.parseDouble(txtPowerSupplyOutputVoltage.getText());
                component = new PowerSupply(brand, model, effect, inputVoltage, outputVoltage);
                break;

            case Processor.TYPE:
                int processorCount = Integer.parseInt(txtProcessorCount.getText());
                double processorClockRate = Double.parseDouble(txtProcessorClockRate.getText());
                component = new Processor(brand, model, processorCount, processorClockRate);
                break;

            case RAM.TYPE:
                int ramMemory = Integer.parseInt(txtRAMMemory.getText());
                component = new RAM(brand, model, ramMemory);
                break;

            case Screen.TYPE:
                int screenSize = Integer.parseInt(txtScreenSize.getText());
                component = new Screen(brand, model, screenSize);
                break;

            default:
                throw new RuntimeException(String.format("Unknown component type: %s", this.componentType));
        }

        try {
            item = new Item(component, price, articleNumber);
        } catch (InvalidPriceArgumentException e) {
            //TODO: si ifra til bruker
            return;
        }

        int qty = Integer.parseInt(txtQty.getText());
        item.setInStock(qty);

        this.inventory.addItem(item);
        try {
            this.inventoryRepository.save(this.inventory);
        } catch (IOException e) {
            //TODO: si ifra til bruker
            return;
        }

        this.closer.close();
    }


    private void initCloseButton() {
        this.btnClose = new Button("Cancel");
        //Syntaks for å hente referanse til en metode. Kan ikke sende metode inni metode(SetonAction er en metode). Hvis man
        //skulle sendt inn en metode måtte det vært en lamda
        this.btnClose.setOnAction(this::handleCancel);
        int rowCount = this.getRowCount();
        this.gridPane.add(this.btnClose, 1, rowCount + 2);
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
        this.initCommonInput();

        switch (componentType) {
            case GraphicCard.TYPE:
                this.initGraphicCardInput();
                break;
            case Harddisc.TYPE:
                this.initHarddiscInput();
                break;
            case Keyboard.TYPE:
                this.initKeyboardInput();
                break;
            case Motherboard.TYPE:
                this.initMotherboardInput();
                break;
            case Mouse.TYPE:
                this.initMouseInput();
                break;
            case PowerSupply.TYPE:
                this.initPowerSupplyInput();
                break;
            case Processor.TYPE:
                this.initProcessorInput();
                break;
            case RAM.TYPE:
                this.initRAMInput();
                break;
            case Screen.TYPE:
                this.initScreenInput();
                break;
        }
        this.vb.getChildren().add(this.gridPane);
    }

    private void initCommonInput() {
        this.txtBrand = this.createLabelInputGridPane("Brand", 0);
        this.txtModel = this.createLabelInputGridPane("Model", 1);
        this.txtArticleNumber = this.createLabelInputGridPane("Article number", 2);
        this.txtPrice = this.createLabelInputGridPane("Price", 3);
        this.txtQty = this.createLabelInputGridPane("Quantity", 4);
    }

    private void initGraphicCardInput() {
        this.txtGraphicCardMemory = this.createLabelInputGridPane("Memory", 5);
    }

    private void initHarddiscInput() {
        this.txtHarddiscType = this.createLabelInputGridPane("Type (HDD/SSD)", 5);
    }

    private void initKeyboardInput() {
        this.txtKeyBoardInterfaceType = this.createLabelInputGridPane("Interface type (USB or Bluetooth)", 5);
    }

    private void initMotherboardInput() {
        this.txtMotherboardSizeCategory = this.createLabelInputGridPane("Size category", 5);
    }

    private void initMouseInput() {
        this.txtMouseInterfaceType = this.createLabelInputGridPane("Interface type (USB or Bluetooth)", 5);
    }


    private void initPowerSupplyInput() {
        this.txtPowerSupplyEffect = this.createLabelInputGridPane("Effect", 5);
        this.txtPowerSupplyInputVoltage = this.createLabelInputGridPane("Input voltage", 6);
        this.txtPowerSupplyOutputVoltage = this.createLabelInputGridPane("Output voltage", 7);
    }

    private void initProcessorInput() {
        this.txtModel = this.createLabelInputGridPane("Processor count", 5);
        this.txtModel = this.createLabelInputGridPane("Processor clock rate", 6);
    }

    private void initRAMInput() {
        this.txtModel = this.createLabelInputGridPane("Memory", 5);
    }

    private void initScreenInput() {
        this.txtModel = this.createLabelInputGridPane("Screen size", 5);
    }

    private TextField createLabelInputGridPane(String label, int row) {
        this.gridPane.add(new Label(label), 0, row);
        TextField textField = new TextField();
        this.gridPane.add(textField, 1, row);
        return textField;
    }

    private void handleCancel(ActionEvent e) {
        this.closer.close();
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


