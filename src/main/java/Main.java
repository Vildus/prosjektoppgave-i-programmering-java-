
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.InventoryController;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene mainScene = this.createMainScene(primaryStage);
        primaryStage.setTitle("Main scene"); //bytte ut og legge til scenen og tittelen du vil ha
        primaryStage.setScene(mainScene); //bytte ut
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    //kommenterer ut de linjene som ikke er spesifikke for din. Og legger til sin scene
    private Scene createMainScene(Stage primaryStage) throws IOException, ClassNotFoundException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/customer.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/inventory.fxml"));
        loader.setController(new InventoryController(primaryStage));
        return new Scene(loader.load(), 900, 1100);
    }
}

