
import components.*;
import inventory.Inventory;
import inventory.Item;
import inventory.exceptions.ItemAlreadyExistsException;
import io.InventoryReadTask;
import io.OrderRepository;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import ui.Alert;
import ui.Common;
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
                TestData.fillInventory();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/loginUser.fxml"));
        LoginUserController controller = new LoginUserController((title, scene) -> {
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
        });
        loader.setController(controller);
        return new Scene(loader.load(), Common.SCENE_WIDTH, Common.SCENE_HEIGHT);
    }

    private Scene createLoadingDataStoreScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/loadingDataStore.fxml"));
        LoadingDataStoreController controller = new LoadingDataStoreController();
        loader.setController(controller);
        return new Scene(loader.load(), Common.SCENE_WIDTH, Common.SCENE_HEIGHT);
    }
}

