
import components.*;
import inventory.Inventory;
import inventory.Item;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import purchase.ShoppingBag;
import ui.CustomerController;
import ui.ShoppingBagController;


import java.io.Console;
import java.io.IOException;

public class Main extends Application {

    private Inventory inventory;
    private ShoppingBag shoppingBag;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene mainScene = this.createMainScene(primaryStage);
        primaryStage.setTitle("Data Store - Build your own PC"); //bytte ut og legge til scenen og tittelen du vil ha
        primaryStage.setScene(mainScene); //bytte ut
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //kommenterer ut de linjene som ikke er spesifikke for din. Og legger til sin scene
    private Scene createMainScene(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/customer.fxml"));
        loader.setController(new CustomerController(this.inventory, ((title, scene) -> {
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
        })));
        return new Scene(loader.load(), 1000, 600);
    }



/*

    private Scene createMainScene(Stage primaryStage) throws IOException {
        try {
            Component component1 = new Mouse("Dell", "M30 silent plus", "USB");
            Item item1 = new Item(component1, 120, 7234567);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/shoppingBag.fxml"));
            ShoppingBagController shoppingBagController = new ShoppingBagController(shoppingBag, 10, () -> {
                System.out.println("bytt vindu nå");
            }, (title, scene) -> {
                primaryStage.setTitle(title);
                primaryStage.setScene(scene);
            });
            loader.setController(shoppingBagController);
            return new Scene(loader.load(), 1000, 600);
        } catch (Exception e) {
            //TODO: Handle somehow
            return null;
        }
    }

 */

}

