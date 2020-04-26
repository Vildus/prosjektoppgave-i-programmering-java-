import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.CustomerController;


import java.io.IOException;

public class Main extends Application {

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ui/inventory.fxml"));
        loader.setController(new CustomerController(((title, scene) -> {
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
        })));
        return new Scene(loader.load(), 1000, 600);
    }
}

