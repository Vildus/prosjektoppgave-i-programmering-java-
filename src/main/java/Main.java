
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
            Item item1 = new Item(component1, 495, 7234567);
            item1.setInStock(6);
            Inventory.getInstance().addItem(item1);

            Component component2 = new Keyboard("HP", "Elite gaming", "USB");
            Item item2 = new Item(component2, 1000, 7564739);
            item2.setInStock(10);
            Inventory.getInstance().addItem(item2);

            Component component3 = new HardDisk("HP", "Elite", "HDD", 500);
            Item item3 = new Item(component3, 2000, 7564738);
            item3.setInStock(14);
            Inventory.getInstance().addItem(item3);

            Component component4 = new Motherboard("HP", "Elite gaming", "Small");
            Item item4 = new Item(component4, 3000, 7564745);
            item4.setInStock(60);
            Inventory.getInstance().addItem(item4);

            Component component5 = new PowerSupply("HP", "Elite gaming", 2, 123.5, 123.4);
            Item item5 = new Item(component5, 700, 7564730);
            item5.setInStock(30);
            Inventory.getInstance().addItem(item5);

            Component component6 = new Processor("HP", "Elite gaming", 10, 2000);
            Item item6 = new Item(component6, 5000, 7564734);
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
            item10.setInStock(6);
            Inventory.getInstance().addItem(item10);

            Component component11 = new GraphicCard("HP", "gaming", 250);
            Item item11 = new Item(component11, 20, 1325785);
            item11.setInStock(12);
            Inventory.getInstance().addItem(item11);

            Component component13 = new Mouse("Intel", "Gaming", "Bluetooth");
            Item item13 = new Item(component13, 300, 12345678);
            item13.setInStock(9);
            Inventory.getInstance().addItem(item13);

            Component component14 = new Mouse("Dell", "Ergonomic", "USB");
            Item item14 = new Item(component14, 899, 7230000);
            item14.setInStock(23);
            Inventory.getInstance().addItem(item14);

            Component component15 = new Mouse("HP", "M30 silent plus2", "Bluetooth");
            Item item15 = new Item(component15, 199, 7234111);
            item15.setInStock(34);
            Inventory.getInstance().addItem(item15);

            Component component16 = new GraphicCard("Asus", "Radeon RX", 6);
            Item item16 = new Item(component16, 4099, 1325999);
            item16.setInStock(12);
            Inventory.getInstance().addItem(item16);

            Component component17 = new GraphicCard("Sapphire", "XT Nitro", 8);
            Item item17 = new Item(component17, 5700, 1674222);
            item17.setInStock(6);
            Inventory.getInstance().addItem(item17);

            Component component18 = new GraphicCard("Zotac", "GetForce  ", 4);
            Item item18 = new Item(component18, 2499, 77788999);
            item18.setInStock(14);
            Inventory.getInstance().addItem(item18);

            Component component19 = new Screen("HP", "Elite gaming2", 54);
            Item item19 = new Item(component19, 2000, 7564666);
            item19.setInStock(43);
            Inventory.getInstance().addItem(item19);

            Component component20 = new Screen("Samsung", "Curved", 32);
            Item item20 = new Item(component20, 4995, 90283746);
            item20.setInStock(3);
            Inventory.getInstance().addItem(item20);

            Component component21 = new Screen("Samsung", "ThinkVision", 27);
            Item item21 = new Item(component21, 2790, 77711999);
            item21.setInStock(5);
            Inventory.getInstance().addItem(item21);

            Component component22 = new Screen("Acer", "Curved", 38);
            Item item22 = new Item(component22, 10099, 71235678);
            item22.setInStock(3);
            Inventory.getInstance().addItem(item22);

            Component component23 = new Keyboard("Razer", "Gaming", "USB");
            Item item23 = new Item(component23, 1999, 7564555);
            item23.setInStock(10);
            Inventory.getInstance().addItem(item23);

            Component component24 = new Keyboard("Logitech", "MX Keys", "Bluetooth");
            Item item24 = new Item(component24, 1299, 89986655);
            item24.setInStock(13);
            Inventory.getInstance().addItem(item24);

            Component component25 = new Keyboard("Ducky One", "Gaming mini", "USB");
            Item item25 = new Item(component25, 599, 7560044);
            item25.setInStock(8);
            Inventory.getInstance().addItem(item25);

            Component component26 = new Motherboard("Asus", "Rog Maximus", "Small");
            Item item26 = new Item(component26, 4800, 11133555);
            item26.setInStock(23);
            Inventory.getInstance().addItem(item26);

            Component component27 = new Motherboard("Intel", "Nuc", "Medium");
            Item item27 = new Item(component27, 5300, 44455522);
            item27.setInStock(23);
            Inventory.getInstance().addItem(item27);

            Component component28 = new Motherboard("Gigabyte", "X570", "Large");
            Item item28 = new Item(component28, 6000, 66644888);
            item28.setInStock(17);
            Inventory.getInstance().addItem(item28);

            Component component29 = new HardDisk("Seagate", "SkyHawk", "HDD", 64);
            Item item29 = new Item(component29, 890, 33221144);
            item29.setInStock(45);
            Inventory.getInstance().addItem(item29);

            Component component30 = new HardDisk("Kingston", "KC2000", "SDD", 500);
            Item item30 = new Item(component30, 6000, 99900333);
            item30.setInStock(10);
            Inventory.getInstance().addItem(item30);

            Component component31 = new HardDisk("Samsung", "970 Pro", "SDD", 300);
            Item item31 = new Item(component31, 2490, 44332167);
            item31.setInStock(67);
            Inventory.getInstance().addItem(item31);

            Component component32 = new PowerSupply("ChiefTek", "AC Power", 85, 240, 12);
            Item item32 = new Item(component32, 549, 78330004);
            item32.setInStock(13);
            Inventory.getInstance().addItem(item32);

            Component component33 = new PowerSupply("Airtame 2", "Wireless", 80, 100, 12);
            Item item33 = new Item(component33, 300, 55588990);
            item33.setInStock(30);
            Inventory.getInstance().addItem(item33);

            Component component34 = new PowerSupply("Chieftek", "AC Power2", 90, 300, 24);
            Item item34 = new Item(component34, 800, 88990022);
            item34.setInStock(35);
            Inventory.getInstance().addItem(item34);


            Component component35 = new Processor("Intel Core", "9600K", 1, 4);
            Item item35 = new Item(component35, 4000, 77553322);
            item35.setInStock(3);
            Inventory.getInstance().addItem(item35);

            Component component36 = new Processor("Intel Pentium", "Gold", 3, 3);
            Item item36 = new Item(component36, 6000, 75677788);
            item36.setInStock(7);
            Inventory.getInstance().addItem(item36);

            Component component37 = new Processor("AMD Ryzen", "3800X", 1, 4);
            Item item37 = new Item(component37, 3500, 44556677);
            item37.setInStock(43);
            Inventory.getInstance().addItem(item37);

            Component component38 = new RAM("Kingston", "Value", 8);
            Item item38 = new Item(component38, 600, 7569905);
            item38.setInStock(34);
            Inventory.getInstance().addItem(item38);

            Component component39 = new RAM("Corsair", "LPX", 16);
            Item item39 = new Item(component39, 1260, 11122334);
            item39.setInStock(23);
            Inventory.getInstance().addItem(item39);

            Component component40 = new RAM("HyperX", "Fury", 32);
            Item item40 = new Item(component40, 4500, 55660011);
            item40.setInStock(6);
            Inventory.getInstance().addItem(item40);


        } catch (ItemAlreadyExistsException e) {
            //dette går fint. Kun test
        }
    }
}

