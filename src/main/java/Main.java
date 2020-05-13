
import components.*;
import inventory.Inventory;
import inventory.Item;
import inventory.ItemAlreadyExistsException;
import io.InventoryReadTask;
import io.OrderRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.Alert;
import ui.LoadingDataStoreController;
import ui.LoginUserController;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Scene mainScene;
        Scene loadDataStoreScene;
        try {
            mainScene = this.createLoginScene(primaryStage);
            loadDataStoreScene = this.createLoadingDataStoreScene();
        } catch (IOException e) {
            Alert.showErrorDialog("Failed to create UI", e);
            Platform.exit();
            return;
        }

        primaryStage.setTitle(LoadingDataStoreController.TITLE);
        primaryStage.setScene(loadDataStoreScene);
        primaryStage.show();

        try {
            OrderRepository orderRepository = new OrderRepository();
            orderRepository.read(); // because OrderRegister is a singleton we ignore the return value
        } catch (FileNotFoundException e) {
            // This is ok, first time we open application
        } catch (Exception e) {
            Alert.showErrorDialog("Failed to read file", e);
            Platform.exit();
            return;
        }

        // As the inventory is needed for both "modes" — super user, and end user
        // we read the inventory here

        InventoryReadTask inventoryReadTask = new InventoryReadTask();

        // handle read inventory success
        inventoryReadTask.setOnSucceeded((event) -> {
            // because the Inventory is implemented as singleton,
            // we don't need the return value (event), as the singleton is "initialized"
            this.showMainScene(primaryStage, mainScene);
        });

        // handle failed read inventory
        inventoryReadTask.setOnFailed((event) -> {
            Throwable error = event.getSource().getException();
            if (error instanceof FileNotFoundException) {
                this.testFillInventory();
                this.showMainScene(primaryStage, mainScene);
            } else {
                Alert.showErrorDialog("Failed to read inventory", error);
                Platform.exit();
            }
        });

        // start reading inventory
        Thread thread = new Thread(inventoryReadTask);
        thread.setDaemon(true);
        thread.start();
    }

    private void showMainScene(Stage primaryStage, Scene scene) {
        primaryStage.setTitle(LoginUserController.TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private Scene createLoginScene(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/loginUser.fxml"));
        LoginUserController controller = new LoginUserController((title, scene) -> {
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
        });
        loader.setController(controller);
        return new Scene(loader.load(), 1000, 600);
    }

    private Scene createLoadingDataStoreScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/loadingDataStore.fxml"));
        LoadingDataStoreController controller = new LoadingDataStoreController();
        loader.setController(controller);
        return new Scene(loader.load(), 1000, 600);
    }

    private void testFillInventory() {
        try {
            //Hvis inventory er tomt så fylles inventory med dette test-inventory
            Component component1 = new Mouse("Dell", "M30 silent plus", "USB");
            Item item1 = new Item(component1, 120, 7234567);
            item1.setInStock(6);
            Inventory.getInstance().addItem(item1);

            Component component2 = new Keyboard("HP", "Elite gaming", "USB");
            Item item2 = new Item(component2, 500, 7564739);
            item2.setInStock(10);
            Inventory.getInstance().addItem(item2);

            Component component3 = new HardDisk("HP", "Elite", "HDD", 500);
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

            Component component6 = new Processor("HP", "Elite gaming", 10, 2000);
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

        } catch (ItemAlreadyExistsException e) {
            //dette går fint. Kun test
        }
    }
}

