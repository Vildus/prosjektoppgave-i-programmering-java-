
import javafx.application.Application;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.Alert;
import ui.LoginUserController;


import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        this.readDataStore();
        Scene mainScene = this.createMainScene(primaryStage);
        primaryStage.setTitle(LoginUserController.TITLE);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Scene createMainScene(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/loginUser.fxml"));
        loader.setController(new LoginUserController((title, scene) -> {
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
        }));
        return new Scene(loader.load(), 600, 600);
    }

    private void handleOpenFileTaskSucceeded(WorkerStateEvent event) {
        String content = (String) event.getSource().getValue();
        txtEditor.setHtmlText(content);
        this.setDisable(false);
    }


    private void handleOpenFileTaskFailed(WorkerStateEvent event) {
        Throwable error = event.getSource().getException();
        txtInfo.setText(String.format("Failed to open file: %s", error.getMessage()));
        this.setDisable(false);
    }

    private void readDataStore() {
        try {
            InventoryRepository inventoryRepository = new InventoryRepository();
        } catch (IOException e) {
            Alert.showErrorDialog("Failed to read file", e);

        }
        try {
            OrderRepository orderRepository = new OrderRepository();
        } catch (IOException e) {
            Alert.showErrorDialog("Failed to read file", e);
        }
    }





    private void testFillInventory() throws ItemAlreadyExistsException {
        //Hvis inventory er tomt så fylles inventory med dette test-inventory
        Component component1 = new Mouse("Dell", "M30 silent plus", "USB");
        Item item1 = new Item(component1, 120, 7234567);
        item1.setInStock(6);
        Inventory.getInstance().addItem(item1);

        Component component2 = new Keyboard("HP", "Elite gaming", "USB");
        Item item2 = new Item(component2, 500, 7564739);
        item2.setInStock(10);
        Inventory.getInstance().addItem(item2);

        Component component3 = new HardDisk("HP", "Elite", "HDD");
        Item item3 = new Item(component3, 600, 7564738);
        item3.setInStock(14);
        Inventory.getInstance().addItem(item3);

        Component component4 = new Motherboard("HP", "Elite gaming", "Small");
        Item item4 = new Item(component4, 1200, 7564745);
        item4.setInStock(60);
        Inventory.getInstance().addItem(item4);

        Component component5 = new PowerSupply("HP", "Elite gaming", 2, 123.5, 123.4);
        Item item5 = new Item(component5, 1200, 7564730);
        item5.setInStock(30);
        Inventory.getInstance().addItem(item5);

        Component component6 = new Processor("HP", "Elite gaming", 10, 20.5);
        Item item6 = new Item(component6, 1200, 7564734);
        item6.setInStock(10);
        Inventory.getInstance().addItem(item6);

        Component component7 = new RAM("HP", "Elite gaming", 100);
        Item item7 = new Item(component7, 1200, 7564767);
        item7.setInStock(2);
        Inventory.getInstance().addItem(item7);

        Component component8 = new Screen("HP", "Elite gaming", 50);
        Item item8 = new Item(component8, 1000, 7564778);
        item8.setInStock(56);
        Inventory.getInstance().addItem(item8);

        Component component9 = new GraphicCard("HP", "Elite gaming", 250);
        Item item9 = new Item(component9, 2000, 1325535);
        item9.setInStock(12);
        Inventory.getInstance().addItem(item9);

        Component component10 = new Mouse("Dell", "Mus", "USB");
        Item item10 = new Item(component10, 120, 72345690);
        item1.setInStock(6);
        Inventory.getInstance().addItem(item10);

        Component component11 = new GraphicCard("HP", "gaming", 250);
        Item item11 = new Item(component11, 20, 1325785);
        item11.setInStock(12);
        Inventory.getInstance().addItem(item11);
    }

}

