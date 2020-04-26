package ui;

import components.*;
import inventory.InvalidPriceArgumentException;
import inventory.Inventory;
import inventory.Item;
import inventory.ItemAlreadyExistsException;
import io.InventoryRepository;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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

    Label lblInfo;


    // This means we cannot create an item controller without a componentType
    // as it does not make sense to have a "view" (javafx view) without a componentType to edit
    // this also means we cannot declare the controller in the fxml file / no "fx:controller=ui/EditItemController"
    public AddItemController(String componentType, Inventory inventory, InventoryRepository inventoryRepository, SceneCloser closer) {
        this.componentType = componentType;
        this.inventoryRepository = inventoryRepository;
        this.inventory = inventory;
        this.closer = closer;
        this.initVBox();
        this.initGridPane();
        this.initCloseButton();
        this.initAddItemButton();
        this.initInfoLabel();
    }

    public Parent getRoot() {
        return this.vb;
    }

    private void initAddItemButton() {
        this.btnAddItem = new Button("Add to inventory");
        this.btnAddItem.setOnAction(this::addItemToInventory);
        int rowCount = this.getRowCount();
        this.gridPane.add(this.btnAddItem, 1, rowCount - 1);
    }


    private void addItemToInventory(ActionEvent actionEvent) {
        //Component input
        Component component;
        String brand = this.txtBrand.getText();
        String model = this.txtModel.getText();

        //Item input
        Item item;
        double price;
        int articleNumber;

        try {
            price = Double.parseDouble(this.txtPrice.getText());
        } catch (NumberFormatException e) {
            this.lblInfo.setText("The price can only contain digits");
            return;
        }

        try {
            articleNumber = Integer.parseInt(this.txtArticleNumber.getText());
        } catch (NumberFormatException e) {
            this.lblInfo.setText("The article number can only contain digits");
            return;
        }

        try {
            switch (componentType) {
                case GraphicCard.TYPE:
                    int graphicCardMemory;
                    try {
                        graphicCardMemory = Integer.parseInt(this.txtGraphicCardMemory.getText());
                    } catch (NumberFormatException e) {
                        this.lblInfo.setText("Graphic card memory can only contain digits");
                        return;
                    }
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
                    int effect;
                    try {
                        effect = Integer.parseInt(txtPowerSupplyEffect.getText());
                    } catch (NumberFormatException e) {
                        this.lblInfo.setText("Effect can only contain digits");
                        return;
                    }

                    double inputVoltage;
                    try {
                        inputVoltage = Double.parseDouble(txtPowerSupplyInputVoltage.getText());
                    } catch (NumberFormatException e) {
                        this.lblInfo.setText("Input voltage can only contain digits");
                        return;
                    }

                    double outputVoltage;
                    try {
                        outputVoltage = Double.parseDouble(txtPowerSupplyOutputVoltage.getText());
                    } catch (NumberFormatException e) {
                        this.lblInfo.setText("Output voltage can only contain digits");
                        return;
                    }
                    component = new PowerSupply(brand, model, effect, inputVoltage, outputVoltage);
                    break;

                case Processor.TYPE:
                    int processorCount;
                    try {
                        processorCount = Integer.parseInt(txtProcessorCount.getText());
                    } catch (NumberFormatException e) {
                        this.lblInfo.setText("Count can only contain digits");
                        return;
                    }

                    double processorClockRate;
                    try {
                        processorClockRate = Double.parseDouble(txtProcessorClockRate.getText());
                    } catch (NumberFormatException e) {
                        this.lblInfo.setText("Clock rate can only contain digits");
                        return;
                    }
                    component = new Processor(brand, model, processorCount, processorClockRate);
                    break;

                case RAM.TYPE:
                    int ramMemory;
                    try {
                        ramMemory = Integer.parseInt(txtRAMMemory.getText());
                    } catch (NumberFormatException e) {
                        this.lblInfo.setText("Memory can only contain digits");
                        return;
                    }
                    component = new RAM(brand, model, ramMemory);
                    break;

                case Screen.TYPE:
                    int screenSize;
                    try {
                        screenSize = Integer.parseInt(txtScreenSize.getText());
                    } catch (NumberFormatException e) {
                        this.lblInfo.setText("Screen size can only contain digits");
                        return;
                    }
                    component = new Screen(brand, model, screenSize);
                    break;

                default:
                    throw new RuntimeException(String.format("Unknown component type: %s", this.componentType));
            }

        } catch (IllegalBrandArgumentException e) {
            this.lblInfo.setText("The field cannot be left blank. You must enter in the brand");
            return;
        } catch (IllegalModelArgumentException e) {
            this.lblInfo.setText("The field cannot be left blank. You must enter in the Model");
            return;
        }

        try {
            item = new Item(component, price, articleNumber);
        } catch (InvalidPriceArgumentException e) {
            this.lblInfo.setText("The price must be higher than 0");
            return;
        }

        try {
            int qty = Integer.parseInt(txtQty.getText());
            item.setInStock(qty);
        } catch (Exception e) {
            this.lblInfo.setText("Quantity must be a digit");
            return;
        }

        try {
            this.inventory.addItem(item);
        } catch (ItemAlreadyExistsException e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("An item with this article number exists already");
            alert.setContentText("To update price or quantity for an item that already exists go back to inventory");
            alert.showAndWait();
            return;
        }

        try {
            this.inventoryRepository.save(this.inventory);
        } catch (IOException e) {
            this.lblInfo.setText("Failed to save file");
            // Hva annet kan man si? Man får jo ikke gjort noe? MIkael!!
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
        this.gridPane.add(this.btnClose, 2, rowCount + 1);
    }

    private void initInfoLabel() {
        this.lblInfo = new Label();
        this.lblInfo.autosize();
        this.vb.getChildren().add(this.lblInfo);
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
        this.txtProcessorCount = this.createLabelInputGridPane("Processor count", 5);
        this.txtProcessorClockRate = this.createLabelInputGridPane("Processor clock rate", 6);
    }

    private void initRAMInput() {
        this.txtRAMMemory = this.createLabelInputGridPane("Memory", 5);
    }

    private void initScreenInput() {
        this.txtScreenSize = this.createLabelInputGridPane("Screen size", 5);
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


